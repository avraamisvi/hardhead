package com.pyx.games.hardhead.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.pyx.games.hardhead.core.Factory;
import com.pyx.games.hardhead.core.SceneObject;;

public class Paw extends SceneObject {

	static int id_ = 0;
	long pass;
	Bitmap image;
	
	public Paw() {
		super("Paw", id_+"paw");
		id_++;
		image = Factory.get().paw1;
		setCanColide(false);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(image, getX()-image.getWidth()/2, getY()-image.getHeight()/2, null);
	}
	
	@Override
	public void updateBehaviour(long delay) throws Exception {
		pass += delay;
//		if(pass == 0) {
//			pass = System.currentTimeMillis();
//		} else { 			
			if(pass > 240) {
				setRemove(true);
			} else if(pass > 160) {
				image = Factory.get().paw3;
			} else if(pass > 105) {
				image = Factory.get().paw2;
			}
//		}
	}
}

