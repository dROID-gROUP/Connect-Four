package com.example.addbuttonprogramatically;

import javax.xml.datatype.Duration;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class registerActivity extends Activity implements OnClickListener
{
	
	private TextView loginTextView;
	private EditText userName,emailAddress,passWord,rePassWord;
	private Context context;
	private Intent intent;
	private Button buttonRegistration;
	private DbHelper dbHelper;
	private ConnectFourApplication connectFourApplication;
	private SQLiteDatabase db;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registrationpage);
		
		context = getApplicationContext();
		
		loginTextView = (TextView) findViewById(R.id.linktologin);
		loginTextView.setOnClickListener(this);
		
		buttonRegistration = (Button) findViewById(R.id.registrationbutton);
		buttonRegistration.setBackgroundResource(R.drawable.button_style);
//		buttonRegistration.setTextColor(Color.WHITE);
		
		
		buttonRegistration.setOnClickListener(this);
		
		userName = (EditText) findViewById(R.id.usernameedittext);
		emailAddress = (EditText) findViewById(R.id.emailaddressEdittext);
		passWord = (EditText) findViewById(R.id.passwordedittext);
		rePassWord = (EditText) findViewById(R.id.retypepasswordedittext);
		connectFourApplication = (ConnectFourApplication) getApplication();
		
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
		else if(buttonRegistration.getId() == v.getId())
		{
			String userName = this.userName.getText().toString();
			String emailAdd = emailAddress.getText().toString();
			String passWord = this.passWord.getText().toString();
			String rePassWord = this.rePassWord.getText().toString();
			this.dbHelper = connectFourApplication.dbHelper;
			try
			{
				db = dbHelper.getReadableDatabase();
				String whereCaluse = DbHelper.USER_NAME+" = ?";
				String[] whereArgs = {userName+""}; 
				Cursor cursor = db.query(DbHelper.TABLE, null, whereCaluse, whereArgs, null, null, null);
				if(cursor.getCount()>0)
				{
					Toast.makeText(this, "This User Name already exists", Toast.LENGTH_SHORT);
					db.close();
					return;
				}
				if(!passWord.equals(rePassWord))
				{
					Toast.makeText(this, "Two password doesn't match", Toast.LENGTH_SHORT);
					db.close();
					return;
				}
				cursor = db.query(DbHelper.TABLE, null, null, null, null, null, null);
				connectFourApplication.userId = cursor.getCount()+1;

				db = dbHelper.getWritableDatabase();
				ContentValues  values = new ContentValues();
				values.put(DbHelper.USER_ID, connectFourApplication.userId);
				values.put(DbHelper.USER_NAME,userName);
				values.put(DbHelper.EMAIL_ADDRESS,emailAdd);
				values.put(DbHelper.PASSWORD, passWord);
				values.put(DbHelper.GAME_STATE, "");
				values.put(DbHelper.ROW, connectFourApplication.row);
				values.put(DbHelper.COLUMN, connectFourApplication.column);
				values.put(DbHelper.DIFFICULTY, 5);
				values.put(DbHelper.TOTAL_GAME,0);
				values.put(DbHelper.WIN, 0);
				values.put(DbHelper.LOOSE,0);
				db.insertWithOnConflict(DbHelper.TABLE, null, values, SQLiteDatabase.CONFLICT_IGNORE);
				Toast.makeText(context, "You have registered successfully "+userName, Toast.LENGTH_SHORT).show();
			}
			finally
			{
				db.close();
			}
			finish();
			
		}
		
	}

}
