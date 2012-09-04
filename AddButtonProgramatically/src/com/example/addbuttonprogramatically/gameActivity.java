package com.example.addbuttonprogramatically;

import java.util.Random;

import android.os.Bundle;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.support.v4.app.NavUtils;
import android.text.Layout;

public class gameActivity extends Activity implements OnClickListener{

    private Game game;
    private int row=5,column=5,difficulty=5,firstTurn=1;
	@Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        
        game = new Game(row, column, difficulty, firstTurn);
        
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setBackgroundColor(Color.BLACK);
        linearLayout.setPadding(5, 5, 5, 5);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        int buttonNumber = 0;
        
        for(int i=0 ; i < row ; ++i)
        {
        	
        	LinearLayout innerLinearLayout = new LinearLayout(this);
        	innerLinearLayout.setOrientation(LinearLayout.HORIZONTAL);
        	//innerLinearLayout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
        	
        	for(int j = 0 ; j < column ; ++j)
        	{        		
        		Button button = new Button(this);
        		button.setOnClickListener(this);
        		button.setHeight(10);
        		button.setWidth(10);
        		button.setBackgroundResource(R.drawable.blank);
        		button.setText("" + buttonNumber);
        		button.setId(buttonNumber);
        		innerLinearLayout.addView(button);
        		++buttonNumber;
        		
        	}
        	
        	linearLayout.addView(innerLinearLayout);
        	
        }
        
        setContentView(linearLayout);        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    @Override
	public void onBackPressed() 
    {    	
		AlertDialog.Builder builderForAlertBox = new AlertDialog.Builder(this);
		builderForAlertBox.setCancelable(false).setMessage("Do You Wish To Save This Game ?").setPositiveButton("Yes", dialogClickListner).setNegativeButton("No", dialogClickListner).show();
	}

	public void onClick(View v) 
	{		
		Log.d("CLICKED ON BUTTON ID", String.valueOf(v.getId()));
		
		int id = v.getId();
		int rw = id/row;
		int col = id%row;
		if((col<0 || col>=column) || (game.flag[col]>=row))
        {
            Toast.makeText(getApplicationContext(), "Invalid Move", Toast.LENGTH_SHORT).show();
        }
		else
		{
			
			game.turn--;
			game.symbol = 4;
			rw = row-game.flag[col]-1;
			id = column*rw + col;
	        v = findViewById(id);
	        humanTurn(col,v);
	       
	        game.symbol = 9;
	        col = game.AI_Turn();
	        rw = row-game.flag[col]-1;
	        id = column*rw + col;
	        v = findViewById(id);
			AITurn(col,v);
		}
		
		
	}
	void humanTurn(int col,View v)
	{
		Log.d("In human", String.valueOf(v.getId())+" "+col);
		game.mat[game.flag[col]++][col]=game.symbol;
        v.setBackgroundResource(R.drawable.gridwithred);
        AlertDialog.Builder builderForAlertBox = new AlertDialog.Builder(this);
        if(game.Win(game.flag[col]-1,col))
        {
        	builderForAlertBox.setCancelable(false).setMessage("YOU WON\n"+"AI Says : I was going Eazy on you ;-)").
        	setPositiveButton("ok", gameFinishListener).show();
        }
        if(game.turn==0)
        {
        	builderForAlertBox.setCancelable(false).setMessage("Game Draw\n"+"AI Says : Feeling Lucky Punk!!??").
         	setPositiveButton("ok", gameFinishListener).show();
        }
	}
	void AITurn (int col,View v)
	{
		 
		 Log.d("In AI", String.valueOf(v.getId())+" "+col);
		 v.setBackgroundResource(R.drawable.blueball);
		 game.mat[game.flag[col]++][col]=game.symbol;
		 AlertDialog.Builder builderForAlertBox = new AlertDialog.Builder(this);
		 if(game.Win(game.flag[col]-1,col))
         {
         	builderForAlertBox.setCancelable(false).setMessage("AI WON\n"+"AI Says : Well I guess it's time for another Evoulution >:P ").
         	setPositiveButton("ok", gameFinishListener).show();
         }
		 if(game.turn==0)
         {
        	builderForAlertBox.setCancelable(false).setMessage("Game Draw\n"+"AI Says : Feeling Lucky Punk!!??").
          	setPositiveButton("ok", gameFinishListener).show();
         }
	}
	DialogInterface.OnClickListener gameFinishListener = new DialogInterface.OnClickListener() 
	{		
		public void onClick(DialogInterface dialog, int which) 
		{			
			finish();			
		}
	};
	
	DialogInterface.OnClickListener dialogClickListner = new DialogInterface.OnClickListener() 
	{		
		public void onClick(DialogInterface dialog, int which) {
			
			switch (which) {
			case DialogInterface.BUTTON_POSITIVE:
				
				Toast.makeText(getApplicationContext(), "Your Game Is Saved", Toast.LENGTH_SHORT).show();
				finish();
				break;
				
			case DialogInterface.BUTTON_NEGATIVE:
				Toast.makeText(getApplicationContext(), "Your Game Is Not Saved", Toast.LENGTH_SHORT).show();
				finish();
			default:
				break;
			}
			
		}
	};

    
}
