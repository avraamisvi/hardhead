package com.pyx.games.hardhead.core;

import java.util.HashMap;

import com.pyx.games.hardhead.Engine;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

public class Database {
	
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "hardhead";
    private static final String POINT_TABLE = "POINT";
    private static final String LEVEL_COLUMN = "LEVEL";
    private static final String POINTS_COLUMN = "POINTS";
    private static final String STARS_COLUMN = "STARS";
    
	private DictionaryOpenHelper helper;
	private SQLiteDatabase wdb;
	private SQLiteDatabase rdb;
	private HashMap<String, String> tableMapPoints = buildMapUser();
	
	public static int COMPLETE = -1;
	boolean loading = false;
	ContentValues initialValues = new ContentValues();
	
	public void initialize(Context context) {
		
		helper = new DictionaryOpenHelper(context);
	}	
	
	HashMap<String, String> buildMapUser() {
		HashMap<String, String> cols = new HashMap<String, String>();
		
		cols.put(LEVEL_COLUMN, LEVEL_COLUMN);
		cols.put(POINTS_COLUMN, POINTS_COLUMN);
		cols.put(STARS_COLUMN, STARS_COLUMN);
		
		return cols; 
	}
    
    
    public long setLevelPoints(int level, int points, int stars) {
    	
    	ContentValues values = new ContentValues();
    	values.put(LEVEL_COLUMN, level);
    	values.put(POINTS_COLUMN, points);
    	values.put(STARS_COLUMN, stars);
    	
    	long ret = -1;
    	if(wdb == null)
    		wdb = helper.getWritableDatabase();
    	
		try {
			
			wdb.beginTransaction();
			
			if (getLevelPoints(level) <= 0) {
				
//				Log.i(Engine.TAG, "SAVE:" + level + "points" + points);
				ret = wdb.insert(POINT_TABLE, null, values);

			} else {
				
//				Log.i(Engine.TAG, "UPDATE:" + level + "points" + points);
				ret = wdb.update(POINT_TABLE, values, null, null);				

			}

		} catch (Throwable e) {
			Engine.get().performingHandlingException(e);
		} finally {
			wdb.setTransactionSuccessful();
			wdb.endTransaction();
			//db.close();
		}
		
		return ret;
    } 
    
    
    public int getLevelPoints(int level) {
    	
    	int ret = 0;
    	
    	if(rdb == null)
    		rdb = helper.getReadableDatabase();
    	
    	try {
	        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
	        
	        builder.setTables(POINT_TABLE);
	        builder.setProjectionMap(tableMapPoints);
	
	        Cursor cursor = builder.query(rdb, new String[]{POINTS_COLUMN}, LEVEL_COLUMN + " = ? ", new String[]{""+level}, null, null, null);
	
	        if (cursor == null) {
	        } else if (!cursor.moveToFirst()) {
	            cursor.close();
	        } else if(cursor.getCount() <= 0) {
	        	cursor.close();
	        } else {
	        	ret = cursor.getInt(0);
	        }
	        
    	} catch (Throwable e) {
    		Engine.get().performingHandlingException(e);
		} finally {
			//rdb.close();
		}
    	
    	return ret;
    }
    
    public int getLevelStars(int level) {
    	try {
	        SQLiteQueryBuilder builder = new SQLiteQueryBuilder();
	        
	        builder.setTables(POINT_TABLE);
	        builder.setProjectionMap(tableMapPoints);
	
	        Cursor cursor = builder.query(helper.getReadableDatabase(), new String[]{STARS_COLUMN}, LEVEL_COLUMN + " = ? ", new String[]{""+level}, null, null, null);
	
	        if (cursor == null) {
	            return 0;
	        } else if (!cursor.moveToFirst()) {
	            cursor.close();
	            return 0;
	        } else if(cursor.getCount() <= 0) {
	        	cursor.close();
	        	return 0;
	        }
	        
	                
	        return cursor.getInt(0);
    	} catch (Throwable e) {
    		Engine.get().performingHandlingException(e);
		}
    	
    	return 0;
    }    
    
	class DictionaryOpenHelper extends SQLiteOpenHelper {	    
	    
	    private static final String POINT_TABLE_CREATE = "CREATE TABLE " + POINT_TABLE + " (" + LEVEL_COLUMN+" INT, " + STARS_COLUMN + " INT, " + POINTS_COLUMN+" INT);";
	    
	    DictionaryOpenHelper(Context context) {
	        super(context, DATABASE_NAME, null, DATABASE_VERSION);	        
	    }

        
	    @Override
	    public void onCreate(SQLiteDatabase db) {
	    	db.execSQL(POINT_TABLE_CREATE);
	    }

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//            Log.w("WORDHUNTER", "Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
//            db.execSQL("DROP TABLE IF EXISTS " + WORDS_TABLE);
//            db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
//            onCreate(db);
		}
	}
	
}
