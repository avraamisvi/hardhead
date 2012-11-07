package com.pyx.games.hardhead.core;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;

import com.pyx.games.hardhead.Engine;

public class SceneObject {

	Scene scene;
	Rect rect;
	private String clazz;
	private String id;
	private float x = 0;
	private float y = 0;
	
	private float h = 0;
	private float w = 0;
	
	private boolean remove = false;
	private boolean canColide = true;
	private boolean visible = true;
	
	public boolean breakerColide = false;
	
	public SceneObject(String clazz, String id) {
		super();
		this.clazz = clazz;
		this.id = id;
	}
	
	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void draw(Canvas canvas) {
		canvas.drawRect(getRect(), null);
	}

	public void update(long delay) {
		try {
			updateBehaviour(delay);
		} catch (Exception e) {
			Engine.get().performingHandlingException(e);
		}
	}

	public void colision(SceneObject target, long delay) {
		if(canColide) {
			try {
				colisionBehaviour(target, delay);
			} catch (Exception e) {
				Engine.get().performingHandlingException(e);
			}
		}
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public Scene getScene() {
		return scene;
	}

	public void updateBehaviour(long delay) throws Exception {}

	public void colisionBehaviour(SceneObject target, long delay) throws Exception {}

	public void setCanColide(boolean canColide) {
		this.canColide = canColide;
	}

	public boolean isCanColide() {
		return canColide;
	}

	public float getH() {
		return h;
	}

	public void setH(float h) {
		this.h = h;
	}

	public float getW() {
		return w;
	}

	public void setW(float w) {
		this.w = w;
	}
	
	public RectF getRectF() {
		return new RectF(x,y,w+x,y+h);
	}

	public boolean isRemove() {
		return remove;
	}

	public void setRemove(boolean remove) {
		this.remove = remove;
	}
	
	public void destroy() {
		
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void colidiu() {
	}	
	
	public Rect getRect() {
		if(rect == null) {
			rect = new Rect((int)x, (int)y, (int)(x+w),(int)(y+h));
		}
		
		rect.set((int)x, (int)y, (int)(x+w),(int)(y+h));
		return rect;
	}
}