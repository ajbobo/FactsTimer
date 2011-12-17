package com.trinova.factstimer;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainGame extends Activity
{
	Integer numberbuttons[] = {
		R.id.btnZero, R.id.btnOne, R.id.btnTwo, R.id.btnThree, R.id.btnFour, R.id.btnFive,
		R.id.btnSix, R.id.btnSeven, R.id.btnEight, R.id.btnNine
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maingamelayout);
		
		for (int x = 0; x <= 9; x++)
		{
			Button button = (Button)findViewById(numberbuttons[x]);
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
	}
	
	private void AddDigit(int digit)
	{
		Toast.makeText(this, "You pressed " + digit, Toast.LENGTH_SHORT).show();
	}
	
	private void EnterAnswer()
	{
		Toast.makeText(this, "Enter", Toast.LENGTH_SHORT).show();
	}
}
