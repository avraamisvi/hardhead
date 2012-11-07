package com.pyx.games.hardhead.game;

import java.util.Random;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.Log;

import com.pyx.games.hardhead.Engine;
import com.pyx.games.hardhead.R;
import com.pyx.games.hardhead.core.Scene;
import com.pyx.games.hardhead.core.SceneObject;

public class HardHead extends SceneObject {

	private Bitmap angryFace;
	private Bitmap fallFace;
	private Bitmap normalFace;

	private Bitmap body;
	private Bitmap face;
	
	private Bitmap capacete;
	
	int elapsed = 0;
	int angryfaceTime = 0;
	float forcex =  0.3f;
	float forcey =  0.3f;
	
	private int capaceteRes;
	
	public int capaceteForca;
	
	public int fx = 1, fy = 1;
	
	int VELOCIDADE_X = 180;
	int VELOCIDADE_Y = 200;
	int VELOCIDADE_Y_PERC = 50;
	
	int velYMax = 800;
	
	Random random = new Random();
	
	private float velY = 120, velX = 120;
	private Paint p;
	private int dir = 1;	
	
	private long movementCarryOverY, targetMovementAmountY; 
	private int pixelsToMoveY;

	long movementCarryOverX, targetMovementAmountX; 
	int pixelsToMoveX;	
	
	private int touches = 0;
	private float cdy;
	private float cdx;
	
	static final int LEFT = 1, RIGHT = 2, TOP = 3, BOTTOM = 4;
	
	public HardHead() {
		super("Player", "HardHead");
		
		p = new Paint();
		p.setARGB(255, 255, 0, 0);
		p.setStyle(Style.STROKE);
	}
	
	public void load(Resources res) {
		angryFace = BitmapFactory.decodeResource(res, R.drawable.angry_face);
		fallFace = BitmapFactory.decodeResource(res, R.drawable.fall_face);
		normalFace = BitmapFactory.decodeResource(res, R.drawable.normal_face);
		body = BitmapFactory.decodeResource(res, R.drawable.body);
		
		face = angryFace;
		setW(body.getWidth());
		setH(body.getHeight());
	}
	
	@Override
	public void draw(Canvas canvas) {
		
		float x = getX();
		float y = getY();
		
		canvas.drawBitmap(body, x, y, null);
		canvas.drawBitmap(face, x+body.getWidth()/2-face.getWidth()/2, y+body.getHeight()/2-face.getHeight()/2, null);
				
//		canvas.drawRect(getRect(), p);
		if(capacete!=null && capaceteRes > 0) {
									
			canvas.drawBitmap(capacete, x+cdx, y+cdy, null);
		}
		
	}
	
	public int getDir() {
		return dir;
	}

	public void init() {
		velY = VELOCIDADE_Y;
		velX = VELOCIDADE_X;
		fy = 1;
		timePass = 0;
		capacete = null;
		capaceteRes = 0;
	}
	
	
	@Override
	public void updateBehaviour(long delay) throws Exception {
		
		timePass += delay;
		Scene scen = Engine.get().getScene();
				
		if(scen.isLevel && !((Level)scen).wait 
				&& !((Level)scen).gameOver 
				&& !((Level)scen).pause
				&& !((Level)scen).nextLevel) {
			
				updateMovimento(delay);
				
				if(face != angryFace) {
					if(fy > 0) {
						if(face != fallFace) {
							face = fallFace;
						}
					} else if(fy < 0) {
						if(face != normalFace) {
							face = normalFace;
						}			
					}
				} else {
					if(angryfaceTime <= 0) {
						if(face != fallFace) {
							face = fallFace;
						}				
					}
					
					angryfaceTime-=delay;
				}
			}
	}

	
	public void updateMovimento(long delay) {
		targetMovementAmountY = (long) ((delay * getVelY()) + movementCarryOverY);
		pixelsToMoveY = (int) (targetMovementAmountY / 1000);
		movementCarryOverY += (int) (targetMovementAmountY % 1000);
		
//		Log.i(Engine.TAG, "movementCarryOverY:" + movementCarryOverY);
//		Log.i(Engine.TAG, "targetMovementAmountY:" + targetMovementAmountY);
		
		if (movementCarryOverY > 1000) {
			movementCarryOverY = movementCarryOverY % 1000;
			pixelsToMoveY++;
			//Log.i(Engine.TAG, "movementCarryOverY:" + movementCarryOverY);
		}
	
//		Log.i(Engine.TAG, "pixelsToMoveY:" + pixelsToMoveY);
		
		setY(getY()+(pixelsToMoveY*fy));
		
		targetMovementAmountX = (long) ((delay * getVelX()) + movementCarryOverX);
		pixelsToMoveX = (int) (targetMovementAmountX / 1000);
		movementCarryOverX = (int) (targetMovementAmountX % 1000);
		if (movementCarryOverX > 1000) {
			movementCarryOverX += movementCarryOverX % 1000;
			pixelsToMoveX++;
		}		

		setX(getX()+(pixelsToMoveX*fx));
		
//		Log.i(Engine.TAG, "X:" + getX());
//		Log.i(Engine.TAG, "VX:" + getVelX());
	}
	
	
	public void setForce(float fx, float fy){
		forcex = fx;
		forcey = fy;
	}
	
	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

//	public void collisionOccured(CollisionEvent event, Body body) {
//		angryfaceTime = 300;
//		face = angryFace;
//		touches++;
//	}

	
	public int getTouches() {
		return touches;
	}
	
	public void setTouches(int touches) {
		this.touches = touches;
	}
	
	long timePass;
	
	public void colidiu(SceneObject obj) {
		obj.colidiu();
		
//		int rand = random.nextInt();
		
		velX = VELOCIDADE_X;
		
//		rand = random.nextInt();
		
//		if(rand%2 > 0) {
//			velY = VELOCIDADE_1;
//		} else {
//			velY = VELOCIDADE_2;
//		}				
		
		
		if(timePass > 15000) {
			timePass = 0;
			if(velY < velYMax) {				
				velY = velY+VELOCIDADE_Y_PERC;
			}
		}
		
		angryfaceTime = 300;
		face = angryFace;
		
		
		if (obj instanceof Breaker) {
//
//			if (this.getRect().centerX() > obj.getRect().centerX() && this.getRect().centerX() < obj.getRect().right) {// && obj.getRect().centerX() < this.getRect().centerX()
//				velX += (velX * ((float)this.getRect().centerX()/(float)obj.getRect().right));
//				
////				Log.i(Engine.TAG, "(this.getRect().centerX()/obj.getRect().right):"  +((float)this.getRect().centerX()/(float)obj.getRect().right));
//			} 
//			
//			if (this.getRect().centerX() < obj.getRect().centerX() && this.getRect().centerX() > obj.getRect().left) {// && obj.getRect().centerX() > this.getRect().centerX() 
//				velX += (velX * ((float)obj.getRect().left/(float)this.getRect().centerX()));
//				
////				Log.i(Engine.TAG, "(this.getRect().centerX()/obj.getRect().right):"  + (obj.getRect().left/this.getRect().centerX()));
//			}
			Breaker brk = (Breaker) obj;
			
			velX += Math.abs(brk.getX() - brk.getOldx());
			//Log.i(Engine.TAG, "velX"  + velX);
//			velY = Math.abs(brk.getY() - brk.getOldy());
			
			if (obj.getRect().right <= this.getRect().centerX()) {
				fx = 1;
				fy = -1;
			} else if (obj.getRect().left >= this.getRect().centerX()) {
				fx = -1;
				fy = -1;
			}
			
			if (obj.getRect().top >= this.getRect().centerY()) {
				fy = -1;
			} else if (obj.getRect().bottom <= this.getRect().centerY()) {
				fy = 1;
			}
		} else {

			if (obj instanceof BlocoQuebravel && capaceteRes > 0) {
				capaceteRes--;
			}
			
			if (obj.getRect().right <= this.getRect().centerX()) {
				 int dif = Math.abs(obj.getRect().right - this.getRect().left);
				 setX(getX()+dif);
				fx = 1;
			} else if (obj.getRect().left >= this.getRect().centerX()) {
				fx = -1;
				int dif = Math.abs(obj.getRect().left - this.getRect().right);
				setX(getX()-dif);
			} else if (obj.getRect().top >= this.getRect().centerY()) {
				fy = -1;
				 int dif = Math.abs(obj.getRect().top - this.getRect().bottom);
				 setY(getY()-dif);				
			} else if (obj.getRect().bottom <= this.getRect().centerY()) {
				fy = 1;
				 int dif = Math.abs(obj.getRect().bottom - this.getRect().top);
				 setY(getY()+dif);				
			}
		}
		
//		Log.i(Engine.TAG, "velY:" + velY + " | velX:" + velX);
	}
	
	@Override
	public void colisionBehaviour(SceneObject target, long delay) throws Exception {	
		colidiu(target);
	}
	
	public void setCapacete(Bitmap capacete, int resistencia, int capaceteForca) {
		this.capacete = capacete;
		this.capaceteRes = resistencia;
		this.capaceteForca = capaceteForca;
		
		switch(resistencia) {
		case 1:
			cdx = -Math.abs((capacete.getWidth() - getW())/2);
			cdy = -(getH()/3);
			break;
		case 3:
			cdx = -Math.abs((capacete.getWidth() - getW())/2);
			cdy = -getH()/4.5f;			
			break;			
		case 5:
			cdx = -Math.abs((capacete.getWidth() - getW())/2);
			cdy = -getH()/4;			
			break;
		case 7:
			cdx = -3;
			cdy = -(getH()*2/3);
			break;
		}
		
	}
	
	public int getCapaceteRes() {
		return capaceteRes;
	}
}
