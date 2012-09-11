package com.example.addbuttonprogramatically;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends Activity implements OnClickListener{
	
	TextView registerTextView;
	Context context;
	Intent intent;
	static int userId = 0;
	
	Button buttonLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginpage);
		
		context = getApplicationContext();
		
		registerTextView = (TextView) findViewById(R.id.linktoregister);
		registerTextView.setOnClickListener(this);
		
		buttonLogin = (Button) findViewById(R.id.loginbutton);
		buttonLogin.setBackgroundResource(R.drawable.button);
		buttonLogin.setTextColor(Color.WHITE);
		
		buttonLogin.setOnClickListener(this);
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	public void onClick(View v) {
		
		if(registerTextView.getId() == v.getId())
		{
			intent = new Intent(context, registerActivity.class);
			startActivity(intent);
		}
		else if(buttonLogin.getId() == v.getId())
		{
			Toast.makeText(context, "click on", Toast.LENGTH_SHORT).show();
		}
		
	}

}
