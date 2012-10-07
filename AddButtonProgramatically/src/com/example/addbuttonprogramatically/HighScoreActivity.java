package com.example.addbuttonprogramatically;


import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputFilter.LengthFilter;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class HighScoreActivity extends Activity 
{
	LinearLayout linearLayout;
	ConnectFourApplication connectFourApplication;
	DbHelper dbHelper;
	SQLiteDatabase db;
	
	
	
	Cursor cursor;
	ListView scoreListView;
	SimpleCursorAdapter adapter;
	
	static final String[] FROM = { DbHelper.USER_NAME };
	static final int[] TO = { R.id.username };
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		connectFourApplication = (ConnectFourApplication)getApplication();
		dbHelper = connectFourApplication.dbHelper;
		
		scoreListView = (ListView) findViewById(R.id.listview);
		
		setContentView(R.layout.highscore);
		popuLatePage();
	}
	private void popuLatePage() 
	{
		
		/*
		
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
		
		*/
		
		

		db = dbHelper.getReadableDatabase();
		String orderBy =  "win "+"DESC";
		Cursor cursor = db.query(DbHelper.TABLE, null, null, null, null, null, orderBy);
		
		
		Log.d("start", cursor.getCount()+"");
		
		
		startManagingCursor(cursor);
		adapter = new SimpleCursorAdapter(this, R.layout.scorerow, cursor, FROM, TO);
		scoreListView.setAdapter(adapter);
		
		
	}
	
	
}
