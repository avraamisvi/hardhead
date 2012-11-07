package com.pyx.games.hardhead.core;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Animation {
	
	private String name;
	
	private List<Bitmap> images = new ArrayList<Bitmap>();
	
	private int frame = 0;
	
	private long timePassed = 0;
	
	private boolean repeat = true;
	
	/**
	 * Duration
	 */
	private long frameDuration = 80;
	
	public void setImages(List<Bitmap> images) {
		this.images = images;
	}
	
	public List<Bitmap> getImages() {
		return images;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void draw(Canvas canvas, float x, float y) {
		canvas.drawBitmap(images.get(frame), x, y, null);
	}
	
	public void update(long delay) {
		
		if (timePassed >= frameDuration) {
			
			if (frame < (images.size() - 1)) {
				frame++;
			} else if (repeat) {
				frame = 0;
			}
			
			timePassed = 0;
		}
		
		timePassed = timePassed + delay;
	}
	
	public void addImage(Bitmap image) {
		images.add(image);
	}
	
	public void start() {
		timePassed = 0;
		frame = 0;
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	public boolean isRepeat() {
		return repeat;
	}

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}

	public long getFrameDuration() {
		return frameDuration;
	}

	public void setFrameDuration(long frameDuration) {
		this.frameDuration = frameDuration;
	}

}
