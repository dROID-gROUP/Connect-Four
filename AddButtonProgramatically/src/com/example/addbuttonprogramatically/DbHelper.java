package com.example.addbuttonprogramatically;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DbHelper extends SQLiteOpenHelper 
{
	private String TAG = "DbHelper";
	static final String dbName = "connectFour.db";
	static final String table = "SAVE_GAME";
	static final String userId = "USER_ID";
	static final String gameState = "GAME_STATE";
	static final int dbVersion = 1;
	Context context;
	public DbHelper(Context context)
	{
		super(context, dbName, null, dbVersion);
		Log.d(TAG, "In DBHelper On Create in the constructor");
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		// TODO Auto-generated method stub
		String sql = "create table "+ table + " ("+ userId +" int primary key, "+gameState+" text";
		//Log.d(TAG, "In DBHelper On Create before execsql");
		db.execSQL(sql);
		Log.d(TAG, "In DBHelper On Create");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		// TODO Auto-generated method stub
		
	}
	

}
