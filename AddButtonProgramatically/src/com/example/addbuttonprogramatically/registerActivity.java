package com.example.addbuttonprogramatically;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class registerActivity extends Activity implements OnClickListener{
	
	TextView loginTextView;
	Context context;
	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registrationpage);
		
		context = getApplicationContext();
		
		loginTextView = (TextView) findViewById(R.id.linktologin);
		loginTextView.setOnClickListener(this);
		
		
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	public void onClick(View v) {
		
		if(loginTextView.getId() == v.getId())
		{
			
			intent = new Intent(context, loginActivity.class);
			startActivity(intent);
			
		}
		
	}

}
