package com.pyx.games.hardhead.game;

import com.pyx.games.hardhead.core.Factory;
import com.pyx.games.hardhead.core.SceneObject;
import com.pyx.games.hardhead.core.SoundManager;

public class Breaker extends StaticPhysicObject {

	
	private float oldx;
	private float oldy;
	
	public Breaker() {
		super("Breaker");	
	}

	public void init() {		
		setSound(SoundManager.BELL);
	}

	@Override
	public void colisionBehaviour(SceneObject target, long delay)
			throws Exception {
		HardHead hd = (HardHead) Factory.get().getPLayerAvatar();
		Capacete cp = (Capacete) target;
		
		hd.setCapacete(cp.getImage(), cp.resistencia, cp.forca);
		cp.setRemove(true);
		SoundManager.playSound(SoundManager.BELL, 1);
	}
	
	@Override
	public void setX(float x) {
		oldx = getX();
		super.setX(x);		
	}
	
	@Override
	public void setY(float y) {
		oldy = getY();
		super.setY(y);
	}

	public float getOldx() {
		return oldx;
	}

	public float getOldy() {
		return oldy;
	}
	
}
