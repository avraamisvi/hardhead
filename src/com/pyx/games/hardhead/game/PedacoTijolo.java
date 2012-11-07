package com.pyx.games.hardhead.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

public class PedacoTijolo extends StaticPhysicObject {

	long movementCarryOverY, targetMovementAmountY; 
	int pixelsToMoveY;

	long movementCarryOverX, targetMovementAmountX; 
	int pixelsToMoveX;
	private int vx, vy;
	private int rvx, rvy;
	private int accx = 20;
	private int accy = 20;
	private long pass;
	
	
	public PedacoTijolo(String id, Bitmap img, int vx, int vy) {
		super(id);
		setImage(img);
		setCanColide(false);
		
		this.vx = vx; 
		this.vy = vy;
		
		accx = vx/10;
		accy = vy/10;		
		p = new Paint();
	}

	@Override
	public void updateBehaviour(long delay) throws Exception {		
		updateMovimento(delay);
		
		pass += delay;
		
		if(pass >= 80) {
			if(p.getAlpha()>0) {
				p.setAlpha(p.getAlpha()-17);
			} else {
				this.setRemove(true);
			}
			
			pass = 0;
		}
	}
	
	@Override
	public void draw(Canvas canvas) {		
		canvas.drawBitmap(getImage(), getX(), getY(), p);				
	}
	
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
