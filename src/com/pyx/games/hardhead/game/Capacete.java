package com.pyx.games.hardhead.game;

import android.graphics.Bitmap;

public class Capacete extends StaticPhysicObject {

	long movementCarryOverY, targetMovementAmountY; 
	int pixelsToMoveY;

	long movementCarryOverX, targetMovementAmountX; 
	int pixelsToMoveX;
	private int vx, vy;
	private int rvx, rvy;
	private int accx = 20;
	private int accy = 20;
	
	public int resistencia;
	public int forca; 
	
	public Capacete(String id, Bitmap img, int resistencia, int forca) {
		super(id);
		setImage(img);
		setCanColide(false);
		breakerColide = true;
		this.resistencia = resistencia;
		this.forca = forca;
		
		this.vx = 0; 
		this.vy = 120;
		
		accx = vx/10;
		accy = vy/10;		
//		p = new Paint();
	
	}
	
	@Override
	public void updateBehaviour(long delay) throws Exception {		
		updateMovimento(delay);

		if(this.getX() > 850) {
			this.setRemove(true);
		}
		
//		if(p.getAlpha()>0) {
//			p.setAlpha(p.getAlpha()-17);
//		} else {
//			this.setRemove(true);
//		}
	}
	
//	@Override
//	public void draw(Canvas canvas) {		
//		drawNumber(canvas, (int)getX(), (int)getY());				
//	}
	
	public void updateMovimento(long delay) {
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
}
