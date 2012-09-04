package com.example.addbuttonprogramatically;

import android.util.Log;

public class Application extends android.app.Application
{
	String tag = "Application";
	@Override
	public void onCreate() 
	{
		// TODO Auto-generated method stub
		super.onCreate();
		Log.d(tag, "In application on create");
	}

	@Override
	public void onTerminate() 
	{
		// TODO Auto-generated method stub
		super.onTerminate();
	}
	
	

}
