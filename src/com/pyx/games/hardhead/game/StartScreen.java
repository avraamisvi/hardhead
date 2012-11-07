package com.pyx.games.hardhead.game;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;

import com.pyx.games.hardhead.Engine;
import com.pyx.games.hardhead.core.Factory;
import com.pyx.games.hardhead.core.Scene;
import com.pyx.games.hardhead.core.StaticSprite;

public class StartScreen extends Scene {

	private int maxTime = 3000;
	private int past;
	private boolean change = false;
	private RectF rect;
	private RectF rectRed;
	private Paint paint;
	private Paint paintRed;
	private Paint paintWhite;
	private boolean sair;
	
	@Override
	public void initialize() {
		
		int w = Engine.get().getDisplay().getWidth();
		int h = Engine.get().getDisplay().getHeight();
		
		rect = new RectF(w*.25f, h-h*.20f, w-w*.25f, (h-h*.20f)+h*.05f);
		
		rectRed = new RectF(rect.left+2, rect.top+2, rect.left+3, rect.bottom-2);
		
		paintRed = new Paint();
		paintRed.setARGB(255, 255, 0, 0);

		paintWhite = new Paint();
		paintWhite.setARGB(255, 255, 255, 255);			
		
		paint = new Paint();
		paint.setARGB(255, 0, 0, 0);
		
		
		StaticSprite logo = new StaticSprite("lgo");
		
		logo.setImage(Factory.get().logo);
		
		logo.setX(Engine.get().getDisplay().getWidth()/2 - logo.getW()/2);
		logo.setY(Engine.get().getDisplay().getHeight()/2 - logo.getH()/2);
		
		this.createLayer();
		this.add(logo, 0);
		
	}
	
	float perc;
	@Override
	public void updateBehaviour(long delay) throws Exception {
		//perc = WordsHunter.get().getDictionary().getPercentComplete();
		
		//if(perc >= .99f) {
			past += delay;
			
			if(past > maxTime && !change) {
				change = true;
				Engine.get().changeScene(Factory.get().getMenuScreen());
			}
		//} else { 		
			//rectRed.right = (rect.left + rect.width()*perc) + 1;
			//Log.w(WordsHunter.TAG, "Perc:"+perc);
		//}
		
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		return true;
	}

	@Override
	protected Paint getPaint() {
		return null;
	}
}
