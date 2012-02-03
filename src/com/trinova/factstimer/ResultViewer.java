package com.trinova.factstimer;

import com.trinova.factstimer.R.id;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultViewer extends Activity
{
	private GameData _gamedata;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.resultsviewerlayout);
		
		Intent intent = getIntent();
		_gamedata = intent.getParcelableExtra("GameData");
		
		showResults();
	}
	
	private void showResults()
	{
		TextView control;

		// Show the accuracy results and grade
		int problemscorrect = _gamedata.getNumberCorrect();
		int totalproblems = _gamedata.getProblemCount();
		String accuracystr = problemscorrect + " / " + totalproblems;
		control = (TextView)findViewById(id.lblAccuracyScore);
		control.setText(accuracystr);
		
		String accuracygrade = _gamedata.getProblemGrade();
		control = (TextView)findViewById(id.lblAccuracyGrade);
		control.setText(accuracygrade);
		
		// Show the time results and grade
		long minutes = _gamedata.getTotalTime() / 60;
		long seconds = _gamedata.getTotalTime() % 60;
		String timestr = minutes + ":" + seconds;
		control = (TextView)findViewById(id.lblTimeScore);
		control.setText(timestr);
		
		String timegrade = _gamedata.getTimeGrade();
		control = (TextView)findViewById(id.lblTimeGrade);
		control.setText(timegrade);
	}
}
