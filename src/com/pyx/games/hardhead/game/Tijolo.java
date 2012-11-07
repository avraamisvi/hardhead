package com.pyx.games.hardhead.game;

import android.graphics.Bitmap;

import com.pyx.games.hardhead.Engine;
import com.pyx.games.hardhead.core.Factory;
import com.pyx.games.hardhead.core.Screen;
import com.pyx.games.hardhead.core.SoundManager;

public class Tijolo extends BlocoQuebravel {

	public Bitmap normal;
	public Bitmap quebrado;
	
	int pancadas;
	
	long firstCollision;
	long lastCollision;
	
	public Tijolo(String id) {
		super(id);
		
		setNormal(Factory.get().T);
		quebrado = Factory.get().T1;
		setSound(SoundManager.TIJOLO_CRASH);
	}

	@Override
	public void colidiu() {
		super.colidiu();
		
		HardHead hd = (HardHead) Factory.get().getPLayerAvatar();
		
		if(hd.getCapaceteRes() > 0) {
			pancadas = pancadas+hd.capaceteForca;
			firstCollision = System.currentTimeMillis();
		} else {
			pancadas++;
		}
			
		if(pancadas > 2) {
			pancadas = 2;
		}
		
		gerarPaw();
			
			int cx = getRect().centerX();
			int cy = getRect().centerY();
			int b = getRect().bottom;
			int t = getRect().top;				
			int l = getRect().left;
			int r = getRect().right;
			
			if(pancadas == 1) {
				setImage(quebrado);
				
				Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().T_pedaco, l, b, -180, 280), 2);
				Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().T_pedaco, cx, b, 0, 280), 2);
				Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().T_pedaco, r, b, 180, 280), 2);
				//Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().T_pedaco, x, b, 30, 180), 2);
				
				firstCollision = System.currentTimeMillis();
			} else if(pancadas == 2) {
				setRemove(true);
				
				Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().T_pedaco, l, b, -80, 280), 2);
				Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().T_pedaco, cx, b, 0, 280), 2);
				Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().T_pedaco, r, b, 80, 280), 2);
				
				Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().T_pedaco, l, t, -50, 260), 2);
				Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().T_pedaco, cx, t, 0, 260), 2);
				Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().T_pedaco, r, t, 50, 260), 2);
				
				Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().T_pedaco, l, cy, -120, 254), 2);
				Engine.get().getScene().add(Factory.get().getPedacoTijolo(Factory.get().T_pedaco, r, cy, 120, 240), 2);
				
				lastCollision = System.currentTimeMillis();
			}
	}


	@Override
	public void updateBehaviour(long delay) throws Exception {		
		
	}
	
	public void setNormal(Bitmap normal) {
		this.normal = normal;
		setImage(normal);
	}

	@Override
	public int getPoint() {
		int ret = 200;
		
		if(lastCollision - firstCollision < 5000) {
			ret = ret*3;
		} else if(lastCollision - firstCollision < 10000) {
			ret = ret*2;
		} 
		
		return ret;
	}
	
	public int getMaxPoint() {
		return 200*3;
	}

	@Override
	public int getMinPoint() {
		return 200;
	}	
	
}
