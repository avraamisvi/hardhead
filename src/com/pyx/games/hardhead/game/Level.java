package com.pyx.games.hardhead.game;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.view.MotionEvent;

import com.pyx.games.hardhead.Engine;
import com.pyx.games.hardhead.R;
import com.pyx.games.hardhead.core.Factory;
import com.pyx.games.hardhead.core.Layer;
import com.pyx.games.hardhead.core.Scene;
import com.pyx.games.hardhead.core.SceneObject;
import com.pyx.games.hardhead.core.SoundManager;
import com.pyx.games.hardhead.core.StaticSprite;

public class Level extends Scene {

	private HardHead hardHead;
	private Bitmap bmp[];

	public Breaker breaker;
	private RectF areaTouch;
	private LinearGradient gradient;
	private Paint backg = new Paint();

	private Paint initialImagePaint = new Paint();

	private Paint paintText = new Paint();

	Layer blocos = new Layer();

	public boolean wait = true;
	public boolean nextImage = false;
	public boolean fadeOut = false;
	public boolean gameOver = false;

	Bitmap initialImage;

	PainelLevel painelLevel;

	public int maxLevelPoints;

	public int levelNumber;

	public boolean pause = false;

	public boolean nextLevel = false;

	public int countBlocos = 0;

	public Level() {
		isLevel = true;
		float w = Engine.get().getDisplay().getWidth();
		float h = Engine.get().getDisplay().getHeight();

		gradient = new LinearGradient(0, 0, w, h, Color.rgb(42, 127, 255),
				Color.rgb(255, 255, 255), TileMode.REPEAT);

		backg.setShader(gradient);
		initialImage = Factory.get().ready;
		initialImagePaint.setAlpha(0);

		paintText.setColor(Color.WHITE);
		paintText.setTypeface(Factory.get().systemFont);
		paintText.setTextSize(12);

		painelLevel = new PainelLevel(this);
	}

	@Override
	public void initialize() {
		super.initialize();

		setBackgroundColor(Color.rgb(180, 238, 255));
		hardHead = (HardHead) Factory.get().getPLayerAvatar();

		hardHead.fy = 1;
		hardHead.init();

		// hardHead.setX(280);//x=370
		// hardHead.setY(400);//x=370

		createLayer();
		createLayer();
		createLayer();

		add(hardHead, 1);
		bmp = new Bitmap[7];
		bmp[0] = BitmapFactory.decodeResource(Factory.get().getRes(),
				R.drawable.bloco_vermelho);
		bmp[1] = BitmapFactory.decodeResource(Factory.get().getRes(),
				R.drawable.bloco_ciano);
		bmp[2] = BitmapFactory.decodeResource(Factory.get().getRes(),
				R.drawable.bloco_amarelo);
		bmp[3] = BitmapFactory.decodeResource(Factory.get().getRes(),
				R.drawable.bloco_azul);
		bmp[4] = BitmapFactory.decodeResource(Factory.get().getRes(),
				R.drawable.bloco_rosa);
		bmp[5] = BitmapFactory.decodeResource(Factory.get().getRes(),
				R.drawable.bloco_verde);
		bmp[6] = BitmapFactory.decodeResource(Factory.get().getRes(),
				R.drawable.bloco_vermelho);

		createBreaker();
		// createBumbos();
		createWalls();
		createBlocoAco();

		createClouds();

		float w = Engine.get().getDisplay().getWidth();
		float h = Engine.get().getDisplay().getHeight();

		areaTouch = new RectF(0, h / 4, w, h);

		Engine.get().stopMusic();
		
//		Engine.get().database.setLevelPoints(levelNumber, 1000, 3);
	}

	private void createClouds() {
		int h = Engine.get().getDisplay().getHeight();
		int w = Engine.get().getDisplay().getWidth();

		StaticSprite sp = new StaticSprite("Cloud");
		Bitmap bmp = BitmapFactory.decodeResource(Factory.get().getRes(),
				R.drawable.nuvem);

		sp.setImage(bmp);
		sp.setCanColide(false);

		sp.setX(sp.getW() - sp.getW() / 4);
		sp.setY(h - sp.getH() + sp.getH() / 3);

		this.add(sp, 0);

		sp = new StaticSprite("Cloud3");

		sp.setImage(bmp);
		sp.setCanColide(false);

		sp.setX((sp.getW() * 2) - sp.getW() / 3);
		sp.setY(h - sp.getH() + sp.getH() / 3);

		this.add(sp, 0);

		sp = new StaticSprite("Cloud2");

		sp.setImage(bmp);
		sp.setCanColide(false);

		sp.setX(0);
		sp.setY(h - sp.getH() + sp.getH() / 4);

		this.add(sp, 0);
	}

	public void addBloco(SceneObject obj) {
		add(obj, 0);
	}

	public int getEstrelas() {
		int estrelas;

		if (Engine.get().levelPoints < maxLevelPoints / 2) {
			estrelas = 1;
		} else if (Engine.get().levelPoints < maxLevelPoints) {
			estrelas = 2;
		} else {
			estrelas = 3;
		}

		return estrelas;
	}

	float percAlph = 0;
	int restCount = 0;
	private boolean voltou;
	private int voltouCount = 2;
	
	@Override
	public void update(long delay) throws Exception {

		int count = (int) (delay / 5);
		restCount += delay % 5;

		if (restCount >= 5) {
			count += restCount / 5;
			restCount = restCount % 5;
		}

		delay = 5;
		for (int i = 0; i < count; i++) {

			super.update(delay);
			painelLevel.update(delay);
		}
			if (countBlocos <= 0 && !nextLevel) {

				if (Engine.get().levelPoints > Engine.get().hiScore) {
					Engine.get().hiScore = Engine.get().levelPoints;
					Engine.get().database.setLevelPoints(levelNumber,
							Engine.get().levelPoints, getEstrelas());
				}

				nextLevel = true;
				painelLevel.show();
				SoundManager.playSound(SoundManager.AEEE, 1);
			}

//			if ((Math.hardHead.getX() > Engine.get().getDisplay().getWidth() && Engine
//					.get().levelLife > 0) || hardHead.getX() < 0) {
//
//				hardHead.setX(Engine.get().getDisplay().getWidth()/2);
//				hardHead.setY(breaker.getY() - 160);
//				hardHead.fy = 1;				
//
//			}

			if (hardHead.getY() > Engine.get().getDisplay().getHeight() + 100
					&& Engine.get().levelLife > 0) {

				hardHead.setTouches(hardHead.getTouches() + 1);

				hardHead.init();
				hardHead.setX(Engine.get().getDisplay().getWidth()/2);
				hardHead.setY(breaker.getY() - 160);

				if (Engine.get().levelLife > 0) {
					Engine.get().levelLife--;
				}
				
				int hx = hardHead.getRect().centerX();
				int hy = hardHead.getRect().centerY();
				
				Engine.get().getScene().add(Factory.get().getFumaca(hx, hy), 2);
				
				SoundManager.playSound(SoundManager.PLUOF, 1);
				
				wait = true;
				voltou = true;
				voltouCount = 1;
			} else if (hardHead.getY() > Engine.get().getDisplay().getHeight() + 100) {
				if (!gameOver) {
					painelLevel.show();
					gameOver = true;
					SoundManager.playSound(SoundManager.OOOO, 1);
				}
			}

			// if (gameOver || pause) {
			
			// }

			if (wait && !voltou) {

				if (percAlph > 1) {
					percAlph = 1;
				} else if (percAlph < 0) {
					percAlph = 0;
				}

				if (!fadeOut) {
					if (initialImagePaint.getAlpha() < 255) {
						initialImagePaint.setAlpha((int) (255 * percAlph));
						percAlph += 0.05f;
					} else {
						SoundManager.playSound(SoundManager.BELL, 1);
						fadeOut = true;
					}
				} else {
					if (initialImagePaint.getAlpha() > 0) {
						initialImagePaint.setAlpha((int) (255 * percAlph));
						percAlph -= 0.05f;
					} else {
						if (!nextImage) {
							nextImage = true;
							fadeOut = false;
							initialImage = Factory.get().go;
							percAlph = 0;
						} else {
							wait = false;
						}
					}
				}
			}
		}

	public void createWalls() {
		StaticSprite s = new StaticSprite("teste");
		int hg = 0;
		Random rnd = new Random(5);
		int h = Engine.get().getDisplay().getHeight();
		int w = Engine.get().getDisplay().getWidth();
		float wbmp = 0;

		while (hg <= h) {

			int r = Math.abs(rnd.nextInt() % 6);

			wbmp = bmp[r].getWidth();

			s = createBlockWall(25 - wbmp, hg, r);
			add(s, 1);

			r = Math.abs(rnd.nextInt() % 6);

			s = createBlockWall(w - 25, hg, r);

			add(s, 1);

			hg += s.getH();

		}

	}

	private StaticSprite createBlockWall(float x, float y, int r) {

		StaticPhysicObject s;
		s = new Parede("STP" + y);
		s.setImage(bmp[r]);

		s.setX(x);
		s.setY(y - 25);
		// s.setSound(SoundManager.PUNCH);

		return s;
	}

	public void createBlocoAco() {
		int w = Engine.get().getDisplay().getWidth();
		float wbmp = 0;
		Bitmap bmp = Factory.get().blocoAco;

		StaticPhysicObject s = null;

		for (int i = 0; i < 6; i++) {
			s = new StaticPhysicObject("aco" + i);
			s.setImage(bmp);
			s.setSound(SoundManager.CLANG);

			wbmp = s.getW();
			s.setX(s.getW() * i);
			s.setY(0 - s.getH() / 2);

			add(s, 0);
		}

	}

	public void createBreaker() {
		int w = Engine.get().getDisplay().getWidth();
		int h = Engine.get().getDisplay().getHeight();

		Breaker s = new Breaker();
		s.setImage(BitmapFactory.decodeResource(Factory.get().getRes(),
				R.drawable.breaker_green_peq));
		s.init();

		s.setX(w / 2 - s.getW() / 2);
		s.setY(h - 120);
		// s.setPosition(w/2-s.getW()/2, h-120);

		breaker = s;
		add(s, 1);
	}

	@Override
	public void onBack() {

		if (!pause) {
			if (!pause && !gameOver) {
				pause = true;
				painelLevel.show();
			}
		} else {
			painelLevel.hide();
		}
	}

	@Override
	public void onShowMenu() {
		if (!pause && !gameOver) {
			pause = true;
			painelLevel.show();
		}
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);

		blocos.draw(canvas);
		drawStatusPanel(canvas);

		if (wait) {
			float w = Engine.get().getDisplay().getWidth();
			float h = Engine.get().getDisplay().getHeight();

			canvas.drawBitmap(initialImage,
					w / 2 - initialImage.getWidth() / 2,
					h / 2 - initialImage.getHeight() / 2, initialImagePaint);
		}

		// if (gameOver || pause) {
		painelLevel.draw(canvas);
		// }
		
//		Utils.drawText(280, 50, Engine.get().fps + " FPS", paintText, canvas, 255, 255, 255);
	}

	public void drawStatusPanel(Canvas canvas) {
		int x = (Factory.get().scoreBmp.getWidth() / 2) + 5;
		int y = Factory.get().scoreBmp.getHeight() + 5;

		canvas.drawBitmap(Factory.get().scoreBmp, x, y, null);
		// Log.i(Engine.TAG,
		// "Engine.get().levelPoints: "+Engine.get().levelPoints);
		drawNumber(canvas, x + Factory.get().scoreBmp.getWidth(), y,
				Engine.get().levelPoints);

		canvas.drawBitmap(Factory.get().lifeBmp, x, 2 * y + 5, null);
		drawNumber(canvas, x + Factory.get().lifeBmp.getWidth(), y * 2 + 5,
				Engine.get().levelLife);

	}

	public void drawNumber(Canvas c, int x, int y, int value) {
		String v = String.valueOf(value);
		// c.drawText(v, x, y, paintText);

		int w = 0;

		Bitmap numb = null;

		for (int i = 0; i < v.length(); i++) {

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

			c.drawBitmap(numb, x + w, y, null);
			w += numb.getWidth();
		}
	}

	boolean down = false;
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		if (event.getAction() == MotionEvent.ACTION_MOVE) {// ||
															// event.getAction()
															// ==
															// MotionEvent.ACTION_DOWN
			float x = event.getX(), y = event.getY();

			if (areaTouch.contains(x, y)) {
				breaker.setX(x - breaker.getW() / 2);
			}

		} else if (event.getAction() == MotionEvent.ACTION_UP) {

			if (gameOver || pause || nextLevel) {
				float x = event.getX(), y = event.getY();
				painelLevel.touchReleased((int) x, (int) y);
			}

			if(voltou) {
				voltouCount--;
			}
		} else if (event.getAction() == MotionEvent.ACTION_DOWN) {

			if (gameOver || pause || nextLevel) {
				float x = event.getX(), y = event.getY();
				painelLevel.touchPressed((int) x, (int) y);
			}
			
			if(voltou) {
				if(voltouCount <= 0) {
					wait = false;
					voltou = false;
				}
				voltouCount--;
			}			
		}

		return true;
	}

	@Override
	protected Paint getPaint() {

		return backg;
	}

	@Override
	public void updateBehaviour(long delay) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
