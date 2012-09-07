package com.example.addbuttonprogramatically;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DbHelper extends SQLiteOpenHelper 
{
	private String tag = "DbHelper";
	static final String dbName = "connectFour.db";
	static final String table = "SAVE_GAME";
	static final String userId = "USER_ID";
	static final String gameState = "GAME_STATE";
	static final int dbVersion = 1;
	SQLiteDatabase db;
	Context context;
	public DbHelper(Context context)
	{
		super(context, dbName, null, dbVersion);
		Log.d(tag, "In DBHelper On Create in the constructor");
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		String sql = "create table "+ table + " ("+ userId +" int primary key, "+gameState+" text)";
		db.execSQL(sql);
		Log.d(tag, "In DBHelper On Create");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}

	public String getUserGameState() 
	{
		String str="";
		String sql = "select "+DbHelper.gameState+" from "+DbHelper.table+" where "+DbHelper.userId+" = "+ConnectFourApplication.userId;
		db = this.getReadableDatabase();
		Cursor cursor = db.query(table, null, null, null, null, null, null);
		if(cursor.moveToFirst())
		{
			str = cursor.getString(cursor.getColumnIndex(gameState));
		}
		
		String whereClause = DbHelper.userId+" = ?";
		String[] whereArgs = {ConnectFourApplication.userId+""};
		delete(DbHelper.table,whereClause,whereArgs);
		
		Log.d(tag, "in dbhelper getUserGameState userid= "+DbHelper.userId+" gamestate = "+str);
		return str;
	}

	//Delete all save games of the user
	public void delete(String tableName, String whereClause, String[] whereArgs) 
	{
		db = this.getWritableDatabase();
		int deletedRow = db.delete(tableName, whereClause, whereArgs);
		Log.d(tag,"Deleted Row = "+deletedRow);
	}
	

}
