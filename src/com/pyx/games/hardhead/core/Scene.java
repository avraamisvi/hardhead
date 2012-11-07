package com.pyx.games.hardhead.core;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;

import com.pyx.games.hardhead.Engine;

public abstract class Scene {
	
	private List<Layer> layers;
	private Bitmap background;
	private Integer backgroundColor;
	private View view;
	public boolean isLevel = false;
	
	public Scene() {
		layers = new ArrayList<Layer>();
		layers.add(new Layer());
	}
	
	
	public void draw(Canvas canvas) {
		
		//canvas.rotate(Engine.get().getRotation());
		if(getPaint() != null) {
			canvas.drawPaint(getPaint());
		} else if(getBackground() != null) {
			canvas.drawBitmap(background, 0, 0, null);
		} else if(getBackgroundColor() != null) {
			Paint paint = new Paint(); 
			paint.setColor(getBackgroundColor()); 
			paint.setStyle(Style.FILL); 
			canvas.drawPaint(paint);
//			paint.reset();
		} 
		
		for (Layer layer : layers) {
			layer.draw(canvas);
		}
	}
	
	protected abstract Paint getPaint();
	
	public void update(long delay) throws Exception {
		
		updateBehaviour(delay);
		
		if(layers != null) {
			 for (Layer layer : layers) {
				 layer.update(delay);
			 }		 
		}
	}
	
	public void createLayer() {
		createLayer(false, false, 0, 0);
	}
	
	public void createLayer(boolean fixX, boolean fixY, float xv, float yv) {
		layers.add(new Layer());
	}
	
	public void add(SceneObject actor, int layer) {
		
		Layer layerInst = layers.get(layer);
		layerInst.add(actor);
	}
	
	public void remove(SceneObject actor, int layer) {
		
		Layer layerInst = layers.get(layer);
		layerInst.remove(actor);
	}
	
	public abstract void updateBehaviour(long delay) throws Exception;

	public void setBackground(Bitmap background) {
		this.background = background;
	}

	public Bitmap getBackground() {
		return background;
	}

	public void setBackgroundColor(Integer backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Integer getBackgroundColor() {
		return backgroundColor;
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		return false;
	}


	public void initialize() {
		
	}
	
	public void destroy() {
		layers = null;
		background = null;
		backgroundColor = null;
		view = null;
	}
	
	public void onShowMenu() {
		
	}
	
	public void onBack() {
		
	}

	public void setView(View view) {
		this.view = view;
	}
	
	public View getView() {
		return view;
	}
	
	public void printMemory() {
		/*ActivityManager actvityManager = (ActivityManager) WordsHunter.get().getSystemService( WordsHunter.ACTIVITY_SERVICE );
		ActivityManager.MemoryInfo mInfo = new ActivityManager.MemoryInfo ();
		actvityManager.getMemoryInfo( mInfo );
		// Print to log and read in DDMS
		Log.i( "WORDHUNTER", " minfo.availMem " + mInfo.availMem );
		Log.i( "WORDHUNTER", " minfo.lowMemory " + mInfo.lowMemory );
		Log.i( "WORDHUNTER", " minfo.threshold " + mInfo.threshold );		*/
	}
}
