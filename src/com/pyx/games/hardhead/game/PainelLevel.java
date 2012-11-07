package com.pyx.games.hardhead.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import com.pyx.games.hardhead.Engine;
import com.pyx.games.hardhead.core.Factory;

public class PainelLevel extends StaticPhysicObject {

	long movementCarryOverY, targetMovementAmountY; 
	int pixelsToMoveY;

	long movementCarryOverX, targetMovementAmountX; 
	int pixelsToMoveX;
	private int vx, vy;
	private int rvx, rvy;
	private int accx = 20;
	private int accy = 20;
	
	private String point;
	
	int middleh = 0;
	int hscreen = 0;
	
	Bitmap panel;
	Bitmap starPoint;
	Bitmap starGrey;
	
	Bitmap btnMenu;
	Bitmap btnReiniciar;
	Bitmap btnNext;
	
	private boolean show;
	
	Level level;
	int estrelas = 1;
	
	Paint paintText;
	boolean stop = true;
	boolean visivel = false;
	
	public PainelLevel(Level level) {
		super("PainelLevel");
		setCanColide(false);
		
		this.vx = 0; 
		this.vy = -180;
		
		accx = 0;
		accy = vy/10;		
		p = new Paint();
		
		panel = Factory.get().panelFase;
		starPoint  = Factory.get().starPoint;
		starGrey = Factory.get().starGrey;
		
		btnMenu = Factory.get().btnMenu;
		btnReiniciar = Factory.get().btnRestart;
		btnNext = Factory.get().btnNext;
		
		middleh = Engine.get().getDisplay().getHeight()/2;
		hscreen = Engine.get().getDisplay().getHeight()+panel.getHeight()/2;
		
		setY(Engine.get().getDisplay().getHeight());
		setX(Engine.get().getDisplay().getWidth()/2);
		
		this.level = level;
		
		paintText = new Paint();
		paintText.setTypeface(Factory.get().systemFont);
		paintText.setTextSize(14);
		paintText.setAntiAlias(true);
	}
	
	@Override
	public void updateBehaviour(long delay) throws Exception {
		if(!stop)
			updateMovimento(delay);

	}
	
	@Override
	public void draw(Canvas canvas) {		
		
		if(!visivel)
			return;
		
		int xp = (int) (getX() - panel.getWidth()/2);
		int yp = (int) (getY() - panel.getHeight()/2);
		int gapw = 0;
		
		canvas.drawBitmap(panel, xp, yp, paint);
		
		if(!level.pause) {
			
			for (int i = 0; i < 3; i++) {
				if(i < estrelas) {
					canvas.drawBitmap(starPoint, xp+73+gapw, yp+96, null);
				} else {
					canvas.drawBitmap(starGrey, xp+73+gapw, yp+96, null);
				}
				
				gapw += starPoint.getWidth()+10;
			}
		
							
			if(!level.nextLevel)
				Utils.drawText(xp+73, yp+64, "Level Failed!", paintText, canvas, 255, 255, 255);
			else
				Utils.drawText(xp+73, yp+64, "You Won!", paintText, canvas, 255, 255, 255);
			
			Utils.drawText(xp+73, yp+84, "Hiscore: " + Engine.get().hiScore, paintText, canvas, 255, 255, 255);
			
		} else {
			Utils.drawText(xp+73, yp+64, "Game Paused!", paintText, canvas, 255, 255, 255);
		}
		
		if(!level.nextLevel)
			canvas.drawBitmap(btnReiniciar, xp+25.5f, yp+132, null);
		else
			canvas.drawBitmap(btnNext, xp+25.5f, yp+132, null);
		
		canvas.drawBitmap(btnMenu, xp+25.5f, yp+132+btnReiniciar.getHeight(), null);
	}
	
	public void updateMovimento(long delay) {
		
		if(show) {
			if(getY() < middleh) {
				stop = true;
			}
		} else {
			if(getY() > hscreen) {
				stop = true;
				if(level.pause)
					level.pause = false;
				
				visivel = false;
			}
		}
			
			
			targetMovementAmountY = (long) ((delay * rvy) + movementCarryOverY);
			pixelsToMoveY = (int) (targetMovementAmountY / 1000);
			movementCarryOverY = (int) (targetMovementAmountY % 1000);
			if (movementCarryOverY > 500) {
				movementCarryOverY = movementCarryOverY + 1000;
				pixelsToMoveY++;
			}
		
			setY(getY()+(pixelsToMoveY));
			
			targetMovementAmountX = (long) ((delay * rvx) + movementCarryOverX);
			pixelsToMoveX = (int) (targetMovementAmountX / 1000);
			movementCarryOverX = (int) (targetMovementAmountX % 1000);
			if (movementCarryOverX > 500) {
				movementCarryOverX = movementCarryOverX + 1000;
				pixelsToMoveX++;
			}		
	
			setX(getX()+(pixelsToMoveX));
			
			
			rvx = accx + rvx;
			rvy = accy + rvy;
			
			if(Math.abs(rvx) > Math.abs(vx)) {
				rvx = vx;
			}
			
			if(Math.abs(rvy) > Math.abs(vy)) {
				rvy = vy;
			}		
		
//		Log.i(Engine.TAG, "X:" + getX());
//		Log.i(Engine.TAG, "VX:" + getVelX());
	}
	
	public void show() {
		visivel = true;
		show = true;
		stop = false;
		
		this.vy = -480;
		accy = vy/3;	
		
		if(Engine.get().hiScore == 0) {
			estrelas = 0;
		} else {
			if(Engine.get().levelPoints < level.maxLevelPoints/2) {
				estrelas = 1;
			} else if(Engine.get().levelPoints < level.maxLevelPoints) {
				estrelas = 2;
			} else {
				estrelas = 3;
			}
		}
	}
	
	public void hide() {
		if(!level.nextLevel) {
			if(stop) {
				stop = false;
				show = false;
				this.vy = 480;
				accy = vy/3;
			}
		}
	}
	
	public void touchPressed(int x, int y) {
		
		if(!stop)
			return;
		
		int xp = (int) (getX() - panel.getWidth()/2);
		int yp = (int) (getY() - panel.getHeight()/2);
		
		Rect r1 = new Rect(xp+25, yp+132, (int)(btnReiniciar.getWidth()+xp+25), yp+132+btnReiniciar.getHeight());		
		
		if(level.nextLevel) {
			if(r1.contains(x, y)) {
				btnNext = Factory.get().btnNext1;
			} else {
				btnNext = Factory.get().btnNext;
			}
		} else {		
			if(r1.contains(x, y)) {
				btnReiniciar = Factory.get().btnRestart1;
			} else {
				btnReiniciar = Factory.get().btnRestart;
			}
		}
		
		int yp2 = yp+133+btnReiniciar.getHeight();				
		
		r1 = new Rect(xp+25, yp2, (int)(btnMenu.getWidth()+xp+25), yp2+btnMenu.getHeight());
		
		if(r1.contains(x, y)) {
			btnMenu = Factory.get().btnMenu1;
		} else {
			btnMenu = Factory.get().btnMenu;
		}		
	}	
	
	public void touchReleased(int x, int y) {
		
		if(!stop)
			return;
		
		int xp = (int) (getX() - panel.getWidth()/2);
		int yp = (int) (getY() - panel.getHeight()/2);
		
		Rect r1 = new Rect(xp+25, yp+132, (int)(btnReiniciar.getWidth()+xp+25), yp+132+btnReiniciar.getHeight());
		
		if(!level.nextLevel) { 
			if(r1.contains(x, y)) {
				Engine.get().changeScene(Factory.get().getScene(level.levelNumber));
			} else {
				btnReiniciar = Factory.get().btnRestart;
			}
		} else {
			if(r1.contains(x, y)) {
				if(level.levelNumber < 25) {
					Engine.get().changeScene(Factory.get().getScene(level.levelNumber+1));
				} else {
					Engine.get().changeScene(Factory.get().getScene(1));
				}
			} else {
				btnNext = Factory.get().btnNext;
			}			
		}
		
		int yp2 = yp+133+btnReiniciar.getHeight();				
		
		r1 = new Rect(xp+25, yp2, (int)(btnMenu.getWidth()+xp+25), yp2+btnMenu.getHeight());
		
		if(r1.contains(x, y)) {
			Engine.get().changeScene(Factory.get().getMenuScreen());
		} else {
			btnMenu = Factory.get().btnMenu;
		}	
	}
}
