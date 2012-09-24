package com.example.addbuttonprogramatically;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity extends Activity implements OnClickListener{
	
	TextView registerTextView;
	Context context;
	Intent intent;
	static int userId = 0;
	EditText passwordEditText,userNameEditText;
	ConnectFourApplication connectFourApplication;
	Button buttonLogin;
	DbHelper dbHelper;
	SQLiteDatabase db;
	private String userName,passWord;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginpage);
		
		context = getApplicationContext();
		connectFourApplication = (ConnectFourApplication) getApplication();
		dbHelper = connectFourApplication.dbHelper;
		
		passwordEditText = (EditText) findViewById(R.id.passwordEditText);
		userNameEditText = (EditText) findViewById(R.id.userNameEditText);
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
			userName = userNameEditText.getText().toString();
			passWord = passwordEditText.getText().toString();
			
			db = dbHelper.getReadableDatabase();
			String where = DbHelper.USER_NAME+" = ?"+" AND "+DbHelper.PASSWORD+" = ?";
			String[] whereArgs = {userName,passWord};
			Cursor cursor = db.query(DbHelper.TABLE, null, where, whereArgs, null, null, null);
			if(cursor.getCount()>0)
			{
				cursor.moveToFirst();
				connectFourApplication.userName = this.userName;
				connectFourApplication.userId = cursor.getInt(cursor.getColumnIndex(DbHelper.USER_ID));
				connectFourApplication.gameState = cursor.getString(cursor.getColumnIndex(DbHelper.GAME_STATE));
				connectFourApplication.row = cursor.getInt(cursor.getColumnIndex(DbHelper.ROW));
				connectFourApplication.column = cursor.getInt(cursor.getColumnIndex(DbHelper.COLUMN));
				connectFourApplication.difficulty = cursor.getInt(cursor.getColumnIndex(DbHelper.DIFFICULTY));
				connectFourApplication.totalGame = cursor.getInt(cursor.getColumnIndex(DbHelper.TOTAL_GAME));
				connectFourApplication.win = cursor.getInt(cursor.getColumnIndex(DbHelper.WIN));
				connectFourApplication.loose = cursor.getInt(cursor.getColumnIndex(DbHelper.LOOSE));		
				Toast.makeText(context, "You have Successfully logged in "+userName, Toast.LENGTH_SHORT).show();
				finish();
			}
			else
			{
				Toast.makeText(context, "Wrong User Name or Password", Toast.LENGTH_SHORT).show();
			}
			
		}
		
	}

}
