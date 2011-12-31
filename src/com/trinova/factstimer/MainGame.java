package com.trinova.factstimer;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainGame extends Activity
{
	private Integer _numberbuttons[] = {
		R.id.btnZero, R.id.btnOne, R.id.btnTwo, R.id.btnThree, R.id.btnFour, R.id.btnFive,
		R.id.btnSix, R.id.btnSeven, R.id.btnEight, R.id.btnNine
	};
	ProblemViewer _viewer;
	
	private GameData _gamedata;
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maingamelayout);
		
		Intent intent = getIntent();
		_gamedata = intent.getParcelableExtra("GameData");

		int temp = 0;
		for (int x = temp; x <= 9; x++)
		{
			Button button = (Button)findViewById(_numberbuttons[x]);
			button.setOnClickListener(new OnClickListener()
			{
				public void onClick(View v)
				{
					int id = v.getId();
					switch(id)
					{
					case R.id.btnZero:	AddDigit(0); break;
					case R.id.btnOne:		AddDigit(1); break;
					case R.id.btnTwo:		AddDigit(2); break;
					case R.id.btnThree:	AddDigit(3); break;
					case R.id.btnFour:	AddDigit(4); break;
					case R.id.btnFive:	AddDigit(5); break;
					case R.id.btnSix:		AddDigit(6); break;
					case R.id.btnSeven:	AddDigit(7); break;
					case R.id.btnEight:	AddDigit(8); break;
					case R.id.btnNine:	AddDigit(9); break;
					}
				}
			});
		}
		
		Button button = (Button)findViewById(R.id.btnEnter);
		button.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				EnterAnswer();
			}
		});
		
		_gamedata.startTime();
		_viewer = (ProblemViewer)findViewById(R.id.problemviewer);
		_viewer.setGameData(_gamedata);
	}
	
	private void AddDigit(int digit)
	{
		_gamedata.getCurrentProblem().addDigitToAnswer(digit);
		_viewer.invalidate();
	}
	
	private void EnterAnswer()
	{

		_gamedata.nextProblem();
		if (_gamedata.isGameOver())
			EndGame();
		_viewer.invalidate();
	}
	
	private void EndGame()
	{
		long minutes = _gamedata.getTotalTime() / 60;
		long seconds = _gamedata.getTotalTime() % 60;
		// Put together the score message
		String message = "";
		message += "Accuracy:\r\n";
		message += "\t" + _gamedata.getNumberCorrect() + " out of " + _gamedata.getProblemCount() + "\r\n";
		message += "\t\tGrade:  " + _gamedata.getProblemGrade() + "\r\n";
		message += "\r\n";
		message += "Time:\r\n";
		message += "\t" + minutes + " minute" + (minutes != 1 ? "s" : "") + "\r\n";
		message += "\t" + seconds + " second" + (seconds != 1 ? "s" : "") + "\r\n";
		message += "\t\tGrade: " + _gamedata.getTimeGrade() + "\r\n";
		//message += "You took " + _gamedata.getTotalTime() + " seconds\r\n";
		//message += "\t\tGrade:  " + _gamedata.getTimeGrade() + "\r\n";
		

		// Display the score
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage(message).setCancelable(false).setNeutralButton("OK", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog, int id)
			{
				MainGame.this.finish();
			}
		}).setTitle("Final Score");
		AlertDialog alert = builder.create();
		alert.show();
	}
}
