package com.pyx.games.hardhead.game;

import com.pyx.games.hardhead.core.Factory;

import android.graphics.Bitmap;
import android.graphics.Canvas;


public class Fumaca extends StaticPhysicObject {
	
	private long pass;
	private int frame;
	static int id_ = 0;

	private Bitmap frames[]; 
		
	public Fumaca() {
		super("Fumaca"+id_);
		id_++;
		frames = new Bitmap[]{Factory.get().fumaca1, Factory.get().fumaca2, Factory.get().fumaca3};
		setCanColide(false);
	}

	@Override
	public void updateBehaviour(long delay) throws Exception {		
		pass += delay;
		
		if(pass >= 100) {
			frame++;
			if(frame > 2) {
				frame = 2;
				this.setRemove(true);
			}
			pass=0;
		}
	}
	
	
	@Override
	public void draw(Canvas canvas) {
		
		float wx = (frames[frame].getWidth() - Factory.get().getPLayerAvatar().getW())/2;
		float hy = (frames[frame].getHeight() - Factory.get().getPLayerAvatar().getH())/2;
		
		canvas.drawBitmap(frames[frame], Factory.get().getPLayerAvatar().getX()-wx, Factory.get().getPLayerAvatar().getY()-hy, null);
	}
}
