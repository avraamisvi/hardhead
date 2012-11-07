package com.pyx.games.hardhead.core;

import android.content.Context;
import android.graphics.Canvas;
import android.text.format.Time;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.pyx.games.hardhead.Engine;

public class Screen extends SurfaceView implements SurfaceHolder.Callback {
	
	Scene scene;
	GameThread gameThread;
	Context context;
	SurfaceHolder holder;
	
	public Screen(Context context, Scene scene) {
		super(context);
		this.scene = scene;
		
        holder = getHolder();
        holder.addCallback(this);
        this.context = context;
        
		this.scene.setView(this);
		this.scene.initialize();
		gameThread = new GameThread(holder, context);
		
		setFocusable(true); 
	}
	
//	@Override
//	protected void onDraw(Canvas canvas) {
//		super.onDraw(canvas);
//		
//		scene.draw(canvas);
//	}
	
	public Scene getScene() {
		return scene;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		//Log.w(Engine.TAG, "Motion2:" + event.getAction());
		return scene.onTouchEvent(event);
	}
	
	public void onMinimize() {
		Engine.get().stopMusic();	
	}	
	
	public void onRestore() {
		if(scene != null && !scene.isLevel) {
			Engine.get().startMusic();	
		}
	}	
	
	public void changeScene(Scene scene) {
		scene.setView(this);
		scene.initialize();
		//TODO FADE
		Scene old = this.scene;
		this.scene = scene;
		old.destroy();
		old = null;
		System.gc();
		
		
		printMemory();
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

	public void surfaceChanged(SurfaceHolder holder, int arg1, int arg2, int arg3) {
		
	}

	public void surfaceCreated(SurfaceHolder holder) {

			if(gameThread.isQuit())
				gameThread = new GameThread(holder, context);
				
				gameThread.start();
			

	}

	public void surfaceDestroyed(SurfaceHolder holder) {		
		
        boolean retry = true;
        gameThread.setQuit(true);
        while (retry) {
            try {
            	gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
            	Engine.get().performingHandlingException(e);
            }
        }		
	}
	
	class GameThread extends Thread {
		
        /** Handle to the surface manager object we interact with */
        SurfaceHolder mSurfaceHolder;
        Context context; 
    	boolean quit = false;
    	long delay;
    
        int FRAMES_PER_SECOND = 25;
        int SKIP_TICKS = 1000 / FRAMES_PER_SECOND;
        
        long next_game_tick = System.currentTimeMillis();
        long sleep_time = 0;        
        
        public GameThread(SurfaceHolder mSurfaceHolder, Context context) {
			this.mSurfaceHolder = mSurfaceHolder;
			this.context = context;
		}

		@Override
        public void run() {
            while (!quit) {
                Canvas c = null;
                try {
                    c = mSurfaceHolder.lockCanvas(null);
                    synchronized (mSurfaceHolder) {
                    		scene.update(SKIP_TICKS);
                    		scene.draw(c);
                    		delay = 0;
                    }
                } catch (Exception e) {
                	Engine.get().performingHandlingException(e);
				} finally {
                    // do this in a finally so that if an exception is thrown
                    // during the above, we don't leave the Surface in an
                    // inconsistent state
                    if (c != null) {
                        mSurfaceHolder.unlockCanvasAndPost(c);
                    }
                }
				
		        next_game_tick += SKIP_TICKS;
		        sleep_time = next_game_tick - System.currentTimeMillis();
		        
		        try {		
			        if( sleep_time >= 0 ) {
			        	Thread.sleep( sleep_time );
			        }			
		        }catch(Exception e) {
		        	Engine.get().performingHandlingException(e);
		        }		        
            }
        }

		public boolean isQuit() {
			return quit;
		}

		public void setQuit(boolean quit) {
			this.quit = quit;
		}
		
	}
	
}
