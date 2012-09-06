package com.example.addbuttonprogramatically;

import android.util.Log;

public class ConnectFourApplication extends android.app.Application
{
	private String tag = "Application";
	public String gameState;
	public int[][] mat = new int[10][10];
	public static int userId=0;
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
	
	public void populateGameMatrix(String gameState)
	{
		this.gameState = gameState;
	}
	
	

}
