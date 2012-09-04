package com.example.addbuttonprogramatically;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class homeActivity extends Activity implements OnClickListener{

	Button button_login;
	Button button_register;
	Button button_newgame;
	Button button_resumegame;
	Button button_highscores;
	Button button_aboutus;
	Button button_gamesetting;
	Context context;
	Intent intent;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
		
		button_newgame = (Button) findViewById(R.id.startnewgame);
		button_resumegame = (Button) findViewById(R.id.resumegame);
		button_gamesetting = (Button) findViewById(R.id.gamesetting);
		button_highscores = (Button) findViewById(R.id.highscore);
		button_register = (Button) findViewById(R.id.registeruser);
		button_login = (Button) findViewById(R.id.login);
		button_aboutus = (Button) findViewById(R.id.aboutus);
		
		button_newgame.setOnClickListener(this);
		button_resumegame.setOnClickListener(this);
		button_gamesetting.setOnClickListener(this);
		button_highscores.setOnClickListener(this);
		button_register.setOnClickListener(this);
		button_login.setOnClickListener(this);
		button_aboutus.setOnClickListener(this);
		
		context = getApplicationContext();
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	public void onClick(View v) {
		
		
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
