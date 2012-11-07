package com.pyx.games.hardhead.core;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import com.pyx.games.hardhead.Engine;
import com.pyx.games.hardhead.R;

public class SoundManager {
	 
	static private SoundManager _instance;
	private static SoundPool mSoundPool;
	private static HashMap<Integer, Integer> mSoundPoolMap;
	private static AudioManager  mAudioManager;
	private static Context mContext;
 
	
	public static int CLANG = 1;
	public static int BELL = 2;
	public static int MUSICA = 3;
	public static int BOING = 4;
	public static int PUM = 5;
	public static int PUNCH = 6;
	public static int TIJOLO_CRASH = 7;
	public static int CONCRETO_CRASH = 8;
	public static int VIDRO_CRASH = 9;
	public static int OOOO = 10;
	public static int AEEE = 11;
	public static int PYX = 12;
	public static int PLUOF = 13;
	
	private SoundManager()
	{
	}
 
	/**
	 * Requests the instance of the Sound Manager and creates it
	 * if it does not exist.
	 *
	 * @return Returns the single instance of the SoundManager
	 */
	static synchronized public SoundManager getInstance()
	{
	    if (_instance == null)
	      _instance = new SoundManager();
	    return _instance;
	 }
 
	/**
	 * Initialises the storage for the sounds
	 *
	 * @param theContext The Application context
	 */
	public static  void initSounds(Context theContext)
	{
		 mContext = theContext;
	     mSoundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);
	     mSoundPoolMap = new HashMap();
	     mAudioManager = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);
	} 
 
	/**
	 * Add a new Sound to the SoundPool
	 *
	 * @param Index - The Sound Index for Retrieval
	 * @param SoundID - The Android ID for the Sound asset.
	 */
	public static void addSound(int Index,int SoundID)
	{
		mSoundPoolMap.put(Index, mSoundPool.load(mContext, SoundID, 1));
	}
 
	/**
	 * Loads the various sound assets
	 * Currently hardcoded but could easily be changed to be flexible.
	 */
	public static void loadSounds()
	{
		mSoundPoolMap.put(BOING, mSoundPool.load(mContext, R.raw.boing, 1));
		mSoundPoolMap.put(CLANG, mSoundPool.load(mContext, R.raw.clang, 1));
		mSoundPoolMap.put(BELL, mSoundPool.load(mContext, R.raw.bell, 1));
		mSoundPoolMap.put(PUM, mSoundPool.load(mContext, R.raw.pum, 1));
		mSoundPoolMap.put(PUNCH, mSoundPool.load(mContext, R.raw.punch, 1));
		mSoundPoolMap.put(TIJOLO_CRASH, mSoundPool.load(mContext, R.raw.tijolo_crash, 1));
		mSoundPoolMap.put(CONCRETO_CRASH, mSoundPool.load(mContext, R.raw.concreto_crash, 1));
		mSoundPoolMap.put(VIDRO_CRASH, mSoundPool.load(mContext, R.raw.vidro_crash, 1));
		mSoundPoolMap.put(AEEE, mSoundPool.load(mContext, R.raw.aeeeeee, 1));
		mSoundPoolMap.put(OOOO, mSoundPool.load(mContext, R.raw.ooooo, 1));
//		mSoundPoolMap.put(MUSICA, mSoundPool.load(mContext, R.raw.harhead3, 1));
		mSoundPoolMap.put(PYX, mSoundPool.load(mContext, R.raw.pyx, 1));
		mSoundPoolMap.put(PLUOF, mSoundPool.load(mContext, R.raw.pluouf, 1));
	}
 
	/**
	 * Plays a Sound
	 *
	 * @param index - The Index of the Sound to be played
	 * @param speed - The Speed to play not, not currently used but included for compatibility
	 */
	public static void playSound(int index,float speed)
	{
		if(Engine.get().isSound()) {
		     float streamVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		     streamVolume = streamVolume / mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		     mSoundPool.play(mSoundPoolMap.get(index), streamVolume, streamVolume, 1, 0, speed);
		}
	}
 
	/**
	 * Stop a Sound
	 * @param index - index of the sound to be stopped
	 */
	public static void stopSound(int index)
	{
		mSoundPool.stop(mSoundPoolMap.get(index));
	}
 
	/**
	 * Deallocates the resources and Instance of SoundManager
	 */
	public static void cleanup()
	{
		mSoundPool.release();
		mSoundPool = null;
	    mSoundPoolMap.clear();
	    mAudioManager.unloadSoundEffects();
	    _instance = null;
 
	}
 
}