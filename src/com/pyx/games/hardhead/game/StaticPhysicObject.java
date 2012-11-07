package com.pyx.games.hardhead.game;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;

import com.pyx.games.hardhead.core.SoundManager;
import com.pyx.games.hardhead.core.StaticSprite;

public class StaticPhysicObject extends StaticSprite {

	public Paint p;
	private int sound = -1;
	
	public StaticPhysicObject(String id) {
		super(id);
		p = new Paint();
		p.setARGB(255, 255, 0, 0);
		p.setStyle(Style.STROKE);
//		sound = SoundManager.
	}
	
	@Override
	public void draw(Canvas canvas) {
		
		if(isVisible()) {
			canvas.drawBitmap(getImage(), getX(), getY(), null);
		}
		
//		canvas.drawRect(getRect(), p);
				
	}
	
//	public void collisionOccured(CollisionEvent event, Body body) {
//		PhysicObject obj = (PhysicObject) body.getUserData();
//		
//		if(obj instanceof HardHead) {
////			ROVector2f norm = event.getNormal();
////			ROVector2f velo = body.getVelocity();
//						
//			float fx = 1;
//			float fy = 1;
//			
//			if(event.getPoint().getX() >= getX()+getW()/2) {
//				fx = 1;
////				Log.w(Engine.TAG, "X>");
//			} else if(event.getPoint().getX() <= getX()-getW()/2) {
//				fx = -1;
////				Log.w(Engine.TAG, "X<");
//			}
//			
//			if(event.getPoint().getY() >= getY()+getH()/2) {
//					fy = 1;
////					Log.w(Engine.TAG, "Y>");
//			} else if(event.getPoint().getY() <= getY()-getH()/2) {					
//					fy = -1;
////					Log.w(Engine.TAG, "Y<");
//			}
//
//			
//			float vx = (Engine.get().getRand().nextFloat()+0.3f)*fx;
//			float vy = (Engine.get().getRand().nextFloat()+0.3f)*fy;
//
////			Log.w(Engine.TAG, "Velo2:" + vx + " Y:" + vy);
//			
//			body.stop();
//			body.adjustVelocity(new Vector2f(vx, vy));
//			
//			if(sound != -1) {
//				SoundManager.playSound(sound, 1);
//			}
//			
//		}
//	
//	}	
	public void colidiu() {
		if(sound > 0)
			SoundManager.playSound(sound, 1);
	}	
	
	public void setSound(int sound) {
		this.sound = sound;
	}
}
