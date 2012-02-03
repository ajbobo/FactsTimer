package com.trinova.factstimer;
import android.app.Activity;
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
					case R.id.btnZero:	addDigit(0); break;
					case R.id.btnOne:		addDigit(1); break;
					case R.id.btnTwo:		addDigit(2); break;
					case R.id.btnThree:	addDigit(3); break;
					case R.id.btnFour:	addDigit(4); break;
					case R.id.btnFive:	addDigit(5); break;
					case R.id.btnSix:		addDigit(6); break;
					case R.id.btnSeven:	addDigit(7); break;
					case R.id.btnEight:	addDigit(8); break;
					case R.id.btnNine:	addDigit(9); break;
					}
				}
			});
		}
		
		Button button = (Button)findViewById(R.id.btnEnter);
		button.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				enterAnswer();
			}
		});
		
		button = (Button)findViewById(R.id.btnClear);
		button.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				clearAnswer();
			}
		});
		
		_gamedata.startTime();
		_viewer = (ProblemViewer)findViewById(R.id.problemviewer);
		_viewer.setGameData(_gamedata);
	}
	
	private void addDigit(int digit)
	{
		_gamedata.getCurrentProblem().addDigitToAnswer(digit);
		_viewer.invalidate();
	}
	
	private void clearAnswer()
	{
		_gamedata.getCurrentProblem().clearAnswer();
		_viewer.invalidate();
	}
	
	private void enterAnswer()
	{
		_gamedata.nextProblem();
		if (_gamedata.isGameOver())
			endGame();
		else
			_viewer.invalidate();
	}
	
	private void endGame()
	{
		Intent intent = new Intent();
		intent.setClass(this, ResultViewer.class);
		intent.putExtra("GameData", _gamedata);
		startActivity(intent);
		
		finish();
	}
}
