package com.example.addbuttonprogramatically;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HighScoreActivity extends Activity 
{
	LinearLayout linearLayout;
	ConnectFourApplication connectFourApplication;
	DbHelper dbHelper;
	SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		linearLayout = new LinearLayout(this);
		connectFourApplication = (ConnectFourApplication)getApplication();
		dbHelper = connectFourApplication.dbHelper;
		popuLatePage();
		setContentView(linearLayout);
	}
	private void popuLatePage() 
	{
		db = dbHelper.getReadableDatabase();
		String orderBy =  "win "+"DESC";
		Cursor cursor = db.query(DbHelper.TABLE, null, null, null, null, null, orderBy);
		if(cursor.getCount()>0)
		{
			cursor.moveToFirst();
			int lenth = cursor.getCount();
			String userName;
			int win;
			int gamePlayed;
			for(int i=0;i<lenth;i++)
			{
				TextView textView = new TextView(this);
				userName = cursor.getString(cursor.getColumnIndex("user_name"));
				gamePlayed = cursor.getInt(cursor.getColumnIndex("total_game"));
				win = cursor.getInt(cursor.getColumnIndex("win"));
				textView.setText(userName+"    "+gamePlayed+"    "+win);
				linearLayout.addView(textView);
			}
		}
	}
	
	
}
