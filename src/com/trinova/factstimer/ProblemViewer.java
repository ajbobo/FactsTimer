package com.trinova.factstimer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class ProblemViewer extends View
{
	Paint _paint;
	GameData _gamedata;
	
	public ProblemViewer(Context context)
	{
		super(context);
		
		initialize();
	}
	
	public ProblemViewer(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		initialize();
	}
	
	public ProblemViewer(Context context, AttributeSet attrs, int defStyle)
	{
		super(context,attrs,defStyle);
		
		initialize();
	}
	
	public void initialize()
	{
		_paint = new Paint();
	}
	
	public void onDraw(Canvas canvas)
	{
		_paint.setColor(Color.WHITE);
		if (_gamedata != null)
		{
			canvas.drawText(_gamedata.getProblemNumber() + "/" + _gamedata.getProblemCount(), 10, 10, _paint);
			Rect viewRect = new Rect(0, 0, getWidth(), getHeight());
			_gamedata.getCurrentProblem().drawProblem(canvas, viewRect, _paint);
		}
	}
	
	public void setGameData(GameData gamedata)
	{
		_gamedata = gamedata;
	}
}
