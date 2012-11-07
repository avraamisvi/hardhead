package com.pyx.games.hardhead.game;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;

import com.pyx.games.hardhead.Engine;
import com.pyx.games.hardhead.core.SceneObject;

public class AreaTouch extends SceneObject {

	private Paint pregion;
	
	public AreaTouch() {
		super("AreaTouch", "AreaTouchRight");
		
		pregion =  new Paint();
		pregion.setARGB(80, 0, 0, 255);
	
		float w = Engine.get().getDisplay().getWidth();
		float h = Engine.get().getDisplay().getHeight();
		
		setW(w);
		setH(h/4);
		
		setY(h-getH());
		setX(0);
		
	}

	@Override
	public void draw(Canvas canvas) {		
		//canvas.drawRect(getX(), getY(), getW()+getX(), getH()+getY(), pregion);
	}
	
	public void onTouchEvent(MotionEvent event, Level level) {
		
	}
}
