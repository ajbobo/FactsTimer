package com.trinova.factstimer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ProblemViewer extends View
{
	Paint paint;
	Problem problem;
	
	public ProblemViewer(Context context)
	{
		super(context);
		
		Initialize();
	}
	
	public ProblemViewer(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		
		Initialize();
	}
	
	public ProblemViewer(Context context, AttributeSet attrs, int defStyle)
	{
		super(context,attrs,defStyle);
		
		Initialize();
	}
	
	public void Initialize()
	{
		paint = new Paint();
		problem = null;
	}
	
	public void onDraw(Canvas canvas)
	{
		paint.setColor(Color.WHITE);
		canvas.drawText("hello there", 10, 10, paint);
		if (problem != null)
		{
			Rect viewRect = new Rect(0, 0, getWidth(), getHeight());
			problem.drawProblem(canvas, viewRect, paint);
		}
	}
	
	public void setProblem(Problem newproblem)
	{
		problem = newproblem;
	}
}
