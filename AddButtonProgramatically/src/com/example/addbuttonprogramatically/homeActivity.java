package com.example.addbuttonprogramatically;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class homeActivity extends Activity implements OnClickListener
{
	private String tag = "homeActivity";
	Button button_login;
	Button button_register;
	Button button_newgame;
	Button button_resumegame;
	Button button_highscores;
	Button button_aboutus;
	Button button_gamesetting;
	Context context;
	Intent intent;
	static DbHelper dbHelper;
	ConnectFourApplication connectFourApplication;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
		Log.d(tag,"Before");
		Log.d(tag,"After");
		button_newgame = (Button) findViewById(R.id.startnewgame);
		button_resumegame = (Button) findViewById(R.id.resumegame);
		button_gamesetting = (Button) findViewById(R.id.gamesetting);
		button_highscores = (Button) findViewById(R.id.highscore);
		button_register = (Button) findViewById(R.id.registeruser);
		button_login = (Button) findViewById(R.id.login);
		button_aboutus = (Button) findViewById(R.id.aboutus);
		
		button_newgame.setBackgroundResource(R.drawable.button);
		button_newgame.setTextColor(Color.WHITE);
		button_resumegame.setBackgroundResource(R.drawable.button);
		button_resumegame.setTextColor(Color.WHITE);
		button_gamesetting.setBackgroundResource(R.drawable.button);
		button_gamesetting.setTextColor(Color.WHITE);
		button_highscores.setBackgroundResource(R.drawable.button);
		button_highscores.setTextColor(Color.WHITE);
		button_register.setBackgroundResource(R.drawable.button);
		button_register.setTextColor(Color.WHITE);
		button_login.setBackgroundResource(R.drawable.button);
		button_login.setTextColor(Color.WHITE);
		button_aboutus.setBackgroundResource(R.drawable.button);
		button_aboutus.setTextColor(Color.WHITE);
		
		
		button_newgame.setOnClickListener(this);
		button_resumegame.setOnClickListener(this);
		button_gamesetting.setOnClickListener(this);
		button_highscores.setOnClickListener(this);
		button_register.setOnClickListener(this);
		button_login.setOnClickListener(this);
		button_aboutus.setOnClickListener(this);
		
		context = getApplicationContext();
		
		Log.d(tag, "before get");
		connectFourApplication = (ConnectFourApplication)getApplication();
		Log.d(tag, "after get");
		
		Log.d(tag, "On create homeActivity");
		dbHelper = new DbHelper(this);
		Log.d(tag, "On create homeActivity after dbhelper");
		connectFourApplication.dbHelper = dbHelper;
		connectFourApplication.userId=0;
		connectFourApplication.getUserInfo(connectFourApplication.userId);
	}



	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	public void onClick(View v) 
	{	
		
		if(v.getId() == button_login.getId())
		{
			intent = new Intent(context, loginActivity.class);
			startActivity(intent);
		}
		else if (v.getId() == button_register.getId()) 
		{
			intent = new Intent(context, registerActivity.class);
			startActivity(intent);
		}
		else if(v.getId() == button_newgame.getId())
		{
			connectFourApplication.getUserInfo(connectFourApplication.userId);
			connectFourApplication.applicationGameMatrixInitialize();
			intent = new Intent(context, gameActivity.class);
			startActivity(intent);
		}
		else if(v.getId() == button_resumegame.getId())
		{
			connectFourApplication.getUserInfo(connectFourApplication.userId);
			if(connectFourApplication.gameState.equals(""))
			{
				Toast.makeText(getApplicationContext(), "No Save Game Available", Toast.LENGTH_SHORT).show();				
				return;
			}
			if(connectFourApplication.gameState.length()!=connectFourApplication.row*connectFourApplication.column)
			{
				Toast.makeText(getApplicationContext(), "Previously Saved Game Doesnt Match With THe Current Board Configuaration", Toast.LENGTH_SHORT).show();				
				return;
			}
			
			connectFourApplication.populateGameMatrix(connectFourApplication.gameState);
			intent = new Intent(context, gameActivity.class);
			startActivity(intent);
		}
		else if(v.getId() == button_gamesetting.getId())
		{
			intent = new Intent(context, settingActivity.class);
			startActivity(intent);
		}		
		
	}

}
