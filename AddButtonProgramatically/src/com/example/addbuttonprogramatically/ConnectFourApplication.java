package com.example.addbuttonprogramatically;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ConnectFourApplication extends android.app.Application
{
	private String tag = "Application";
	public String gameState,userName;
	public int[][] mat = new int[10][10];
	public int userId=0;
	public int row,column,difficulty,win,loose,totalGame;
	public int turn=0;
	DbHelper dbHelper;
	SQLiteDatabase db;
	
	@Override
	public void onCreate() 
	{
		super.onCreate();
		Log.d(tag, "In application on create");
	}
	
	@Override
	public void onTerminate() 
	{
		super.onTerminate();
	}
	void applicationGameMatrixInitialize()
	{
		this.gameState="";
		for(int i=0;i<row;i++)
		{
			for(int j=0;j<column;j++)
			{
				mat[i][j]=0;
				this.gameState=this.gameState.concat("0");
			}
		}
	}
	
	public void getUserInfo(int userId) 
	{
		try
		{
			gameState="";
			String sql = "select "+DbHelper.GAME_STATE+" from "+DbHelper.TABLE+" where "+DbHelper.USER_ID+" = "+userId;
			db = dbHelper.getReadableDatabase();
			String whereCaluse = DbHelper.USER_ID+" = ?";
			String[] whereArgs = {userId+""};
			Cursor cursor = db.query(DbHelper.TABLE, null, whereCaluse, whereArgs, null, null, null);
			if(cursor.moveToFirst())
			{
				userName = cursor.getString(cursor.getColumnIndex(DbHelper.USER_NAME));
				gameState = cursor.getString(cursor.getColumnIndex(DbHelper.GAME_STATE));
				row = cursor.getInt(cursor.getColumnIndex(DbHelper.ROW));
				column = cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMN));
				difficulty = cursor.getInt(cursor.getColumnIndex(DbHelper.DIFFICULTY));
				totalGame = cursor.getInt(cursor.getColumnIndex(DbHelper.TOTAL_GAME));
				win = cursor.getInt(cursor.getColumnIndex(DbHelper.WIN));
				loose = cursor.getInt(cursor.getColumnIndex(DbHelper.LOOSE));		
				turn = cursor.getInt(cursor.getColumnIndex(DbHelper.TURN));
			}
		}
		finally
		{
			db.close();
		}
		
		Log.d(tag, "in dbhelper getUserGameState userid= "+userId+" gamestate = "+gameState+" row = "+row+" column = "+column+" userName = "+userName);
		return;
	}
	
	public void populateGameMatrix(String gameState)
	{
		this.gameState = gameState;
		int k=0;
		for(int i=0;i<row;i++)
			for(int j=0;j<column;j++)
			{
				mat[i][j] = gameState.charAt(k++)-48;
			}
	}	

}
