package com.pyx.games.hardhead.game;

import com.pyx.games.hardhead.core.Factory;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class NewPoint extends StaticPhysicObject {

	long movementCarryOverY, targetMovementAmountY; 
	int pixelsToMoveY;

	long movementCarryOverX, targetMovementAmountX; 
	int pixelsToMoveX;
	private int vx, vy;
	private int rvx, rvy;
	private int accx = 20;
	private int accy = 20;
	
	private String point;
	private int r, g, b;
	
	private Paint paintText = new Paint();
	private long pass;
	
	public NewPoint(String id, int vx, int vy, String point, int r, int g, int b) {
		super(id);
//		setImage(img);
		setCanColide(false);
		
		this.r = r; 
		this.g = g;
		this.b = b;
		
		this.vx = vx; 
		this.vy = vy;
		
		accx = vx/10;
		accy = vy/10;		
		p = new Paint();
		this.point = point;
		
		paintText.setColor(Color.WHITE);
		paintText.setTypeface(Factory.get().systemFont);
		paintText.setTextSize(16);
		paintText.setAntiAlias(true);
	}


	public void drawNumber(Canvas c, int x, int y) {
		
		Utils.drawText(x, y, point, paintText, c, r, g, b);
		
		/*String v = point;
		Bitmap numb = null;
		int w = 0;
		
		for(int i = 0; i < v.length(); i++) {
			
			switch (v.charAt(i)) {
			case '1':
				numb = Factory.get().one;
				break;
			case '2':
				numb = Factory.get().two;
				break;
			case '3':
				numb = Factory.get().three;
				break;
			case '4':
				numb = Factory.get().four;
				break;
			case '5':
				numb = Factory.get().five;
				break;
			case '6':
				numb = Factory.get().six;
				break;
			case '7':
				numb = Factory.get().seven;
				break;
			case '8':
				numb = Factory.get().eight;
				break;
			case '9':
				numb = Factory.get().nine;
				break;
			case '0':
				numb = Factory.get().zero;
				break;				
			default:
				break;
			}
			
			c.drawBitmap(numb, x+w, y, null);
			w += numb.getWidth();
		}*/
	}
	
	@Override
	public void updateBehaviour(long delay) throws Exception {		
		updateMovimento(delay);
		
		pass += delay;
		
		if(pass >= 100) {
			if(p.getAlpha()>0) {
				int d = p.getAlpha()-17;
				p.setAlpha(d>=0?d:0);
			} else {
				this.setRemove(true);
			}
			pass = 0;
		}
	}
	
	@Override
	public void draw(Canvas canvas) {		
		drawNumber(canvas, (int)getX(), (int)getY());				
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
