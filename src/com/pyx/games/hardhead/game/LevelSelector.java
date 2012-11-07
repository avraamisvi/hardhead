package com.pyx.games.hardhead.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import com.pyx.games.hardhead.Engine;
import com.pyx.games.hardhead.core.Factory;
import com.pyx.games.hardhead.core.SceneObject;

public class LevelSelector extends SceneObject {

	public Bitmap background;
	public int estrelas;
	private int level;
	private Paint paintText = new Paint();
	private int fw;

	public LevelSelector(int level) {
		super("SceneObject", "LevelSelector");
		background = Factory.get().escolhaFase;

		paintText.setColor(Color.WHITE);
		paintText.setTypeface(Factory.get().systemFont);
		paintText.setTextSize(28);
		paintText.setAntiAlias(true);

		this.level = level;
		
		estrelas = Engine.get().database.getLevelStars(level);
//		Log.i(Engine.TAG, "level: " + level + " ESTRELAS:" + estrelas);
	}

	@Override
	public void draw(Canvas canvas) {
		canvas.drawBitmap(background, getX(), getY(), null);

		Utils.drawText((int) (getX() + background.getWidth() / 2 - fw / 2),
				(int) (getY() + background.getHeight() / 2), "" + level,
				paintText, canvas, 255, 255, 255);

		int xp = (int) (getX() - background.getWidth() / 2);
		int yp = (int) (getY() + background.getHeight() / 2);

		
		int wstr = Factory.get().miniStar.getWidth()+2*3;
		
		int gapw = (background.getWidth()/2-wstr/2)/2;

		for (int i = 0; i < 3; i++) {
			if (i < estrelas) {
				canvas.drawBitmap(Factory.get().miniStar, getX() + gapw, yp + 9, null);
			} else {
				canvas.drawBitmap(Factory.get().miniStarDvt, getX() + gapw, yp + 9, null);
			}

			gapw += Factory.get().miniStar.getWidth() + 2;
		}

		// canvas.drawText(""+level, getX()+background.getWidth()/2,
		// getY()+background.getHeight()/2, paintText);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
		Rect bounds = new Rect();
		String l = "" + level;
		paintText.getTextBounds(l, 0, l.length(), bounds);

		fw = bounds.width();
	}

}

// canvas.drawBitmap(background, 0, 0, null);