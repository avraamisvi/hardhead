package com.pyx.games.hardhead.game;

import android.graphics.Bitmap;

import com.pyx.games.hardhead.core.Factory;
import com.pyx.games.hardhead.core.SoundManager;

public class BlocoAco extends StaticPhysicObject {

	public Bitmap normal;
	
	public BlocoAco(String id) {
		super(id);
		
		setNormal(Factory.get().blocoAco);
		setSound(SoundManager.CLANG);
	}

	int brilhoTime = 0;
	@Override
	public void updateBehaviour(long delay) throws Exception {		
		
	}
	
	public void setNormal(Bitmap normal) {
		this.normal = normal;
		setImage(normal);
	}
	
//	@Override
//	public void draw(Canvas canvas) {
//		
//		Vector2f pts[] = ((Box)getBody().getShape()).getPoints(getBody().getPosition(), getBody().getRotation());
//		
//		if(isVisible()) {
//			canvas.drawBitmap(getImage(), getX()-getW()/2, getY()-getH()/2, null);
//		}
//				
//		Utils.drawBox(pts, p, canvas);
//	}	
	
}
