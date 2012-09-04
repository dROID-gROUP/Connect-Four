package com.example.addbuttonprogramatically;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper 
{
	static final String dbName = "connectfour.db";
	static final int dbVersion = 0;
	public DbHelper(Context context)
	{
		super(context, dbName, null, dbVersion);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	

}
