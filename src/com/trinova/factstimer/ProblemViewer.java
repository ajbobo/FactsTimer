package com.trinova.factstimer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class ProblemViewer extends View
{
	Paint paint;
	
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
	}
	
	public void onDraw(Canvas canvas)
	{
		paint.setColor(Color.WHITE);
		canvas.drawText("hello there", canvas.getWidth() / 2, canvas.getHeight() / 2, paint);
	}
	
	/*
	public void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		int widthmode = MeasureSpec.getMode(widthMeasureSpec);
		int width = MeasureSpec.getSize(widthMeasureSpec);
		int heightmode = MeasureSpec.getMode(heightMeasureSpec);
		int height = MeasureSpec.getSize(heightMeasureSpec);
		
		if (width > height)
			width = (int)(.75 * width);
		else
			height = (int)(.75 * height);

		setMeasuredDimension(width, height);
	}*/
}
