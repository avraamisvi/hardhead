package com.pyx.games.hardhead.game;

import com.pyx.games.hardhead.core.SoundManager;


public class Parede extends StaticPhysicObject {
	
	public Parede(String id) {
		super(id);		
		setSound(SoundManager.PUNCH);
	}

//	@Override
//	public void collisionOccured(CollisionEvent event, Body body) {
//		
//		PhysicObject obj = (PhysicObject) body.getUserData();
//		
//		if(obj instanceof HardHead) {
//						
//			float fx = 1;
//			
//			if(event.getPoint().getX() >= getX()+getW()/2) {
//				fx = 1;
//			} else if(event.getPoint().getX() <= getX()-getW()/2) {
//				fx = -1;
//			}
//
//			
//			float vx = (Engine.get().getRand().nextFloat()+0.3f)*fx;
//			float vy = body.getVelocity().getY();
//			
//			body.stop();
//			body.adjustVelocity(new Vector2f(vx, vy));
//			
//			SoundManager.playSound(SoundManager.PUNCH, 1);
//			
//		}		
		
		
//		if(obj instanceof HardHead) {
//			ROVector2f norm = event.getNormal();
//			ROVector2f velo = body.getVelocity();
//			Vector2f delt = new Vector2f(1000*norm.getX(), 1000*norm.getY());
//			
//			body.adjustVelocity(new Vector2f(-velo.getX(), -velo.getY()));
//			body.adjustVelocity(new Vector2f(1000*norm.getX(), 1000*norm.getY()));
//			
//			obj.addForce(delt.x, delt.y);
//			
//			SoundManager.playSound(SoundManager.PUNCH, 1);			
//		}
//	}


	
}
