package com.pyx.games.hardhead.game;

import java.util.ArrayList;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.view.MotionEvent;

import com.pyx.games.hardhead.Engine;
import com.pyx.games.hardhead.R;
import com.pyx.games.hardhead.core.Factory;
import com.pyx.games.hardhead.core.Scene;
import com.pyx.games.hardhead.core.StaticSprite;

public class MenuSelecao extends Scene {

	private LinearGradient gradient;
	private Paint backg; 
//	PainelInicial painel;
	ArrayList<LevelSelector> levels = new ArrayList<LevelSelector>();	
	float mx, my;
	
	public MenuSelecao() {
		
		int gapw = (Engine.get().getDisplay().getWidth() - ((Factory.get().escolhaFase.getWidth()*4)+60))/2;
		int w = gapw;
		int h = 20;
		
		for(int i = 1; i <= 25; i++) {
			LevelSelector mnu = new LevelSelector(i);
			
			mnu.setLevel(i);
			levels.add(mnu);
			
			mnu.setX(w);
			mnu.setY(h);
			
			w += mnu.background.getWidth() + 20;
			
			if(w >= 454) {
				h += mnu.background.getHeight() + 20;
				w = gapw;
			}
		}
		
		gradient = new LinearGradient(0, 0, Engine.get().getDisplay().getWidth(), Engine.get().getDisplay().getHeight(), Color.rgb(42, 127, 255), Color.rgb(255, 255, 255), TileMode.REPEAT);
		backg = new Paint();
		backg.setShader(gradient);
		
		createClouds();
	}

	@Override
	protected Paint getPaint() {
		return backg;
	}

	private void createClouds() {
		int h = Engine.get().getDisplay().getHeight();
//		int w = Engine.get().getDisplay().getWidth();
		
		StaticSprite sp = new StaticSprite("Cloud");
		Bitmap bmp = BitmapFactory.decodeResource(Factory.get().getRes(), R.drawable.nuvem);
		
		sp.setImage(bmp);
		sp.setCanColide(false);
		
		sp.setX(sp.getW()-sp.getW()/4);
		sp.setY(h-sp.getH()+sp.getH()/3);
		
		this.add(sp, 0);

		sp = new StaticSprite("Cloud3");		
		
		sp.setImage(bmp);
		sp.setCanColide(false);
		
		sp.setX((sp.getW()*2)-sp.getW()/3);
		sp.setY(h-sp.getH()+sp.getH()/3);
		
		this.add(sp, 0);		
		
		
		sp = new StaticSprite("Cloud2");		
		
		sp.setImage(bmp);
		sp.setCanColide(false);
		
		sp.setX(0);
		sp.setY(h-sp.getH()+sp.getH()/4);
		
		this.add(sp, 0);		
	}
	
	@Override
	public void updateBehaviour(long delay) throws Exception {
		for (LevelSelector sel : levels) {
			Rect r = new Rect((int)sel.getX(), (int)sel.getY(), (int)(sel.background.getWidth()+sel.getX()), (int)(sel.background.getHeight()+sel.getY()));				
			
			if(r.contains((int)mx, (int)my)) {
				Engine.get().changeScene(Factory.get().getScene(sel.getLevel()));
			}
		}
		
		mx = 0;
		my = 0;	
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		
		for (LevelSelector sel : levels) {
			sel.draw(canvas);
		}
			
	}
	
	@Override
	public void onBack() {
		Engine.get().changeScene(Factory.get().getMenuScreen());
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_UP) {

			mx = event.getX();
			my = event.getY();

		} else if (event.getAction() == MotionEvent.ACTION_DOWN) {

			mx = event.getX();
			my = event.getY();
		}

		return true;
	}
}
