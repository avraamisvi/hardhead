package com.pyx.games.hardhead.game;

import android.util.Log;

import com.pyx.games.hardhead.Engine;
import com.pyx.games.hardhead.core.Factory;

public abstract class BlocoQuebravel extends StaticPhysicObject {

	int [] caps = {0,1,0,1,0,1,0,2,2,1,2,3,4,3,4,3};
	
	public BlocoQuebravel(String id) {
		super(id);
	}
	
	@Override
	public void destroy() {
		
		int points = getPoint();
		Engine.get().addPoint(points);
		
		int r, g, b;
		
		if(points >= getMinPoint()*3) {
			r = 0;
			g = 212;
			b = 0;			
		} else if(points >= getMinPoint()*2) {
			r = 255;
			g = 255;
			b = 0;		
		} else {
			r = 255;
			g = 255;
			b = 255;
		}
		
		NewPoint p = Factory.get().getNewPoint((int)(getX()+getW()/2), (int)(getY()+getH()/2), 0, -80, String.valueOf(points), r, g, b);
		Engine.get().getScene().add(p, 2);
		
		if(Engine.get().getScene().isLevel) {
			Level le = (Level) Engine.get().getScene();
			
			le.countBlocos--;
		}
		
		if(getMaxPoint() > getMinPoint()) {
			int capn = Math.abs(Engine.get().getRand().nextInt()%caps.length);
		
			if(capn > caps.length-1) {
				capn = 0;
			}
			
			int hx = (int) (getX()+getW()/2);
			int hy = (int) (getY()+getH()/2);
			
			try {
			
			switch(caps[capn]) {
				case 1:
					Engine.get().getScene().add(Factory.get().getCapacete(Factory.get().capaceteObra, hx, hy, 2, 1), 2);			
					break;			
				case 2:
					Engine.get().getScene().add(Factory.get().getCapacete(Factory.get().capaceteFute, hx, hy, 2, 3), 2);
					break;			
				case 3:
					Engine.get().getScene().add(Factory.get().getCapacete(Factory.get().capaceteMoto, hx, hy, 3, 5), 2);
					break;			
				case 4:
					Engine.get().getScene().add(Factory.get().getCapacete(Factory.get().capaceteBroca, hx, hy, 4, 7), 2);
					break;
				}
			} catch(Exception e) {
				Log.i(Engine.TAG, "doido " + capn + " " + (caps.length-2));
			}
		}
	}

	public abstract int getPoint();
	
	public abstract int getMaxPoint();
	
	public int getMinPoint() {
		return 100;
	}
	
	public void gerarPaw() {
//		int hx = (int) (Factory.get().getPLayerAvatar().getX()+Factory.get().getPLayerAvatar().getW()/2);
//		int hy = (int) (Factory.get().getPLayerAvatar().getY()+Factory.get().getPLayerAvatar().getH()/2);

		int hx = (int) (getX()+getW()/2);
		int hy = (int) (getY()+getH()/2);
		
		Engine.get().getScene().add(Factory.get().getPaw(hx, hy), 2);
	}
	
}
