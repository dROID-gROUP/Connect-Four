package com.example.addbuttonprogramatically;

import java.util.Vector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DbHelper extends SQLiteOpenHelper 
{
	private String tag = "DbHelper";
	static final String DB_NAME = "connectFour.db";
	static final String TABLE = "user_info";
	static final String USER_ID = "user_id";;
	static final String USER_NAME = "user_name";
	static final String EMAIL_ADDRESS = "email_address";
	static final String PASSWORD = "password";
	static final String GAME_STATE = "game_state";
	static final String ROW = "row";
	static final String COLUMN = "column";
	static final String DIFFICULTY = "difficulty";
	static final String TOTAL_GAME = "total_game";
	static final String WIN = "win";
	static final String LOOSE = "loose";
	static final String TURN = "turn";
	static final int DBVERSION = 9;
	
	
	SQLiteDatabase db;
	Context context;
	public DbHelper(Context context)
	{
		super(context, DB_NAME, null, DBVERSION);
		Log.d(tag, "In DBHelper On Create in the constructor");
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		String sql = "create table "+ TABLE + " ( _id INTEGER primary key AUTOINCREMENT, "+ USER_ID +" int, "+USER_NAME+" text, "+EMAIL_ADDRESS+" text, "+PASSWORD+" text, "+GAME_STATE+" text, "+ROW+" int, "+COLUMN+" int, "+DIFFICULTY+" int, "+
					  TOTAL_GAME+" int, "+WIN+" int, "+LOOSE+" int, "+TURN+" int"+")";
		db.execSQL(sql);
		ContentValues  values = new ContentValues();
		values.put(USER_ID, 0);
		values.put(USER_NAME, "Guest");
		values.put(EMAIL_ADDRESS,"");
		values.put(PASSWORD, "");
		values.put(GAME_STATE, "");
		values.put(ROW, 5);
		values.put(COLUMN, 5);
		values.put(DIFFICULTY, 5);
		values.put(TOTAL_GAME,0);
		values.put(WIN, 0);
		values.put(LOOSE,0);
		values.put(TURN, 0);
		db.insertWithOnConflict(TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
		Log.d(tag, "In DBHelper On Create");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		String sql = "Drop table if exists "+TABLE;
		db.execSQL(sql);
		onCreate(db);
	}
	
	
	//Delete all save games of the user
	public void delete(String tableName, String whereClause, String[] whereArgs) 
	{
		db = this.getWritableDatabase();
		int deletedRow = db.delete(tableName, whereClause, whereArgs);
		Log.d(tag,"Deleted Row = "+deletedRow);
	}
	
}
