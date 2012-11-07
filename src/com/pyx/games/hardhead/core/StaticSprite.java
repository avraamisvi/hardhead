package com.pyx.games.hardhead.core;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

public class StaticSprite extends SceneObject {
	
	protected Paint paint; 
	
	public StaticSprite(String id) {
		super("STATIC", id);
	}

	private Bitmap image;
	
	
	@Override
	public void draw(Canvas canvas) {
		if(isVisible()) {
				if(paint != null)
					canvas.drawBitmap(image, getX(), getY(), paint);
				else
					canvas.drawBitmap(image, getX(), getY(), null);
		}
			
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
		setW(image.getWidth());
		setH(image.getHeight());
	}
	
	public float getRotation() {
		return 0;
	}
	
	public float getDeltaX() {
		return 0;
	}
	
	public float getDeltaY() {
		return 0;
	}	
		
}
