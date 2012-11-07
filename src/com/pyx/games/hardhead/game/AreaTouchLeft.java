package com.pyx.games.hardhead.game;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.pyx.games.hardhead.Engine;
import com.pyx.games.hardhead.core.SceneObject;

public class AreaTouchLeft extends SceneObject {

	private Paint pregion;
	
	public AreaTouchLeft() {
		super("AreaTouch", "AreaTouchLeft");
		
		pregion =  new Paint();
		pregion.setARGB(80, 255, 0, 0);
	
		float w = Engine.get().getDisplay().getWidth();
		float h = Engine.get().getDisplay().getHeight();
		
		setW(92);
		setH(92);
		
		setY(h-getH());
		setX(0);
		
	}

	@Override
	public void draw(Canvas canvas) {		
		canvas.drawRect(getX(), getY(), getW()+getX(), getH()+getY(), pregion);
	}
	
	
	public void onTouchEvent(MotionEvent event, Level level) {
		
	}
}
