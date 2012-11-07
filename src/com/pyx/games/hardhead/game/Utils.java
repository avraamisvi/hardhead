package com.pyx.games.hardhead.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;

import com.pyx.games.hardhead.Engine;

public class Utils {
	
	public static void drawCircle(float x, float y, float r, Paint p, Canvas canvas) {
		if(Engine.get().isDebug()) {
			canvas.drawCircle(x, y, r, p);
		}
	}	
	
	public static float toRadians(float degrees) {
		return degrees * 0.0174532925f;
	}

	public static void drawText(int x, int y, String text, Paint paintText, Canvas canvas, int r, int g , int b) {
		paintText.setColor(Color.BLACK);
		paintText.setStyle(Style.FILL_AND_STROKE);
		paintText.setStrokeWidth(2);
		
		canvas.drawText(text, x, y, paintText);
		
		paintText.setColor(Color.rgb(r,g,b));
		paintText.setStyle(Style.FILL);
		paintText.setStrokeWidth(0);
		
		canvas.drawText(text, x, y, paintText);		
	}
}
