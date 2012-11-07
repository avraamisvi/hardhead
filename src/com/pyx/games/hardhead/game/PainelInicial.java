package com.pyx.games.hardhead.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.pyx.games.hardhead.Engine;
import com.pyx.games.hardhead.core.Factory;

public class PainelInicial extends StaticPhysicObject {

	Bitmap btnPlay;
	
	public PainelInicial() {
		super("PainelInicial");
		
		int xh = Engine.get().getDisplay().getWidth()/2;
		int yh = Engine.get().getDisplay().getHeight();
		
		btnPlay = Factory.get().btnPlay;
		
		setY(yh - btnPlay.getHeight()*3);
		setX(xh - btnPlay.getWidth()/2);
		
	}
	
	@Override
	public void draw(Canvas canvas) {		
		
		canvas.drawBitmap(btnPlay, getX(), getY(), null);
	}	
	
	
	public void touchPressed(int x, int y) {
		
		Rect r1 = new Rect((int)getX(), (int)getY(), (int)(btnPlay.getWidth()+getX()), (int)(getY()+btnPlay.getHeight()));		
		
		if(r1.contains(x, y)) {
			btnPlay = Factory.get().btnPlaySel;
		} else {
			btnPlay = Factory.get().btnPlay;
		}		
	}	
	
	public void touchReleased(int x, int y) {
		
		Rect r1 = new Rect((int)getX(), (int)getY(), (int)(btnPlay.getWidth()+getX()), (int)(getY()+btnPlay.getHeight()));
		
		if(r1.contains(x, y)) {
			Engine.get().changeScene(Factory.get().getMenuSelecao());
		} else {
			btnPlay = Factory.get().btnPlay;
		}
	}
}
