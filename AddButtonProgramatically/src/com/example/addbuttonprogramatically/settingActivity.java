package com.example.addbuttonprogramatically;

import android.R.string;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class settingActivity extends Activity implements android.view.View.OnClickListener , OnItemSelectedListener{
	
	
	Spinner spinnerForDifficulty;
	Spinner spinnerForRow;
	Spinner spinnerForColumn;
	
	RadioGroup radioGroupForFirstTurn;
	Button buttonSetting;
	
	Intent intent;
	Context context;
	
	String[] itemsForDifficulty;
	String[] itemsForRow;
	String[] itemsForColumn;
	
	int difficultyLimit = 7;
	int rowLimit = 10;
	int columnLimit = 10;
	
	int selectedDifficulty;
	int selectedRow;
	int selectedColumn;
	String firstTurn;
	ConnectFourApplication connectFourApplication;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.settingpage);
		context = getApplicationContext();
		
		spinnerForDifficulty = (Spinner) findViewById(R.id.spinnerForDifficulty);
		spinnerForRow		 = (Spinner) findViewById(R.id.spinnerForRow);
		spinnerForColumn	 = (Spinner) findViewById(R.id.spinnerForColumn);
		
		buttonSetting 		 = (Button) findViewById(R.id.buttonSetting);
		buttonSetting.setBackgroundResource(R.drawable.button);
		buttonSetting.setTextColor(Color.WHITE);
		
		buttonSetting.setOnClickListener(this);
		
		radioGroupForFirstTurn = (RadioGroup) findViewById(R.id.radioGroup);
		
		addItemsOnSpinners();
		
	}
	
	
	public void addItemsOnSpinners()
	{
		
		itemsForDifficulty = new String[difficultyLimit];
		for(int i = 0 ; i < difficultyLimit ; ++i)
		{
			itemsForDifficulty[i] = Integer.toString(i+1);
		}
		
		ArrayAdapter<String> arrayAdapterForDifficulty = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item , itemsForDifficulty);
		arrayAdapterForDifficulty.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerForDifficulty.setOnItemSelectedListener(this);
		spinnerForDifficulty.setAdapter(arrayAdapterForDifficulty);
		
		
		itemsForRow = new String[rowLimit];
		for(int i = 0 ; i < rowLimit ; ++i)
		{
			itemsForRow[i] = Integer.toString(i+1);
		}
		
		ArrayAdapter<String> arrayAdapterForRow = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item , itemsForRow);
		arrayAdapterForRow.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerForRow.setOnItemSelectedListener(this);
		spinnerForRow.setAdapter(arrayAdapterForRow);
		
		itemsForColumn = new String[columnLimit];
		for(int i = 0 ; i < columnLimit ; ++i)
		{
			itemsForColumn[i] = Integer.toString(i+1);
		}
		
		ArrayAdapter<String> arrayAdapterForColumn = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item , itemsForColumn);
		arrayAdapterForColumn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerForColumn.setOnItemSelectedListener(this);
		spinnerForColumn.setAdapter(arrayAdapterForColumn);
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	public void onClick(View v) {
		
		if(v.getId() == buttonSetting.getId())
		{
			
			if(radioGroupForFirstTurn.getCheckedRadioButtonId() == R.id.radio0)
			{
				firstTurn = "Human";
			}
			else if (radioGroupForFirstTurn.getCheckedRadioButtonId() == R.id.radio1) 
			{
				firstTurn = "Computer";
			}
			
			
			Toast.makeText(context, "Your Settings Are Applied!" + firstTurn + ":" +spinnerForDifficulty.getSelectedItem()
					+ ":" + spinnerForRow.getSelectedItem() + ":" + spinnerForColumn.getSelectedItem(), Toast.LENGTH_SHORT).show();
			
			
			connectFourApplication = (ConnectFourApplication)getApplication();
			connectFourApplication.row = Integer.parseInt(spinnerForRow.getSelectedItem().toString());
			connectFourApplication.column = Integer.parseInt(spinnerForColumn.getSelectedItem().toString());
			connectFourApplication.difficulty= Integer.parseInt(spinnerForDifficulty.getSelectedItem().toString());
			
			
			
			finish();
		}
		
	}


	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		
		
	}


	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
