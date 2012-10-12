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
	
	static final String[] FROM = { DbHelper.USER_NAME, DbHelper.WIN, DbHelper.TOTAL_GAME };
	static final int[] TO = { R.id.username, R.id.win, R.id.gameplayed };
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.highscore);
		
		connectFourApplication = (ConnectFourApplication)getApplication();
		dbHelper = connectFourApplication.dbHelper;
		
		scoreListView = (ListView) findViewById(R.id.listview);
		popuLatePage();
		
	}
	private void popuLatePage() 
	{

		
		db = dbHelper.getReadableDatabase();
		String orderBy =  "win "+"DESC";
		Cursor cursor = db.query(DbHelper.TABLE, null, null, null, null, null, orderBy);
		
		startManagingCursor(cursor);
		adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.scorerow, cursor, FROM, TO);
		scoreListView.setAdapter(adapter);
		db.close();
		
		
		
	}
	
	
}
