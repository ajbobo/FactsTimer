package com.trinova.factstimer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;

public class GameSetup extends Activity
{
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.gamesettingslayout);
		Button btn = (Button)findViewById(R.id.btnStartGame);
		btn.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				StartGame();
			}
		});
	}
	
	public void StartGame()
	{
		// Get the problem level
		Spinner cboLevel = (Spinner)findViewById(R.id.cboSelectLevel);
		int level = cboLevel.getSelectedItemPosition() + 1;
		
		// Create a new GameData
		GameData gdata = new GameData(5, level);
		
		// Start the main game activity
		Intent intent = new Intent();
		intent.setClass(this, MainGame.class);
		intent.putExtra("GameData", gdata);
		startActivity(intent);
	}
}