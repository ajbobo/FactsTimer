package com.trinova.factstimer;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

public class Problem implements Parcelable
{
	private static final int NO_ANSWER = -1;
	private static final int MIN_LEVEL = 1;
	private static final int MAX_LEVEL = 1;
	private static final int _operator_PLUS = 1;
	private static final int _operator_MINUS = 2;
	private static final int _operator_TIMES = 3;
	private static final int _operator_DIVIDE = 4;
	
	private int _operand1, _operand2, _result, _operator, _useranswer;
	
	public Problem(int level, Random rand)
	{
		_useranswer = NO_ANSWER;
		
		if (level < MIN_LEVEL)
			level = MIN_LEVEL;
		else if (level > MAX_LEVEL)
			level = MAX_LEVEL;
		
		switch(level)
		{
		case 1: MakeLevel1(rand); break;
		}
	}
	
	public Problem(Parcel parcel)
	{
		_operand1 = parcel.readInt();
		_operand2 = parcel.readInt();
		_result = parcel.readInt();
		_operator = parcel.readInt();
		_useranswer = parcel.readInt();
	}
	
	private void MakeLevel1(Random rand)
	{
		// Add two 1-digit numbers
		_operator = _operator_PLUS;
		
		_operand1 = rand.nextInt(10);
		_operand2 = rand.nextInt(10);
		
		_result = _operand1 + _operand2;
	}
	
	public boolean isCorrect()
	{
		if (_useranswer == _result)
			return true;
		return false;
	}
	
	private String getOpString()
	{
		switch (_operator)
		{
		case _operator_PLUS: return "+";
		case _operator_MINUS: return "-";
		case _operator_TIMES: return "*";
		case _operator_DIVIDE: return "/";
		}
		
		return "";
	}
	
	public void drawProblem(Canvas canvas, Rect viewRect, Paint paint)
	{
		String result = _operand1 + " " + getOpString() + " " + _operand2;
		if (_useranswer != NO_ANSWER)
			result += " = " + _useranswer;
		int x = viewRect.left + (viewRect.width() / 2);
		int y = viewRect.top + (viewRect.height() / 2);
		canvas.drawText(result, x, y, paint);
	}
	
	public void addDigitToAnswer(int digit)
	{
		if (_useranswer == NO_ANSWER)
			_useranswer = digit;
		else
			_useranswer = (10 * _useranswer) + digit;
	}

	public int describeContents() // Requred by Parcelable
	{
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) // Requred by Parcelable
	{
		dest.writeInt(_operand1);
		dest.writeInt(_operand2);
		dest.writeInt(_result);
		dest.writeInt(_operator);
		dest.writeInt(_useranswer);
	}
	
	public static final Parcelable.Creator<Problem> CREATOR = new Parcelable.Creator<Problem>() // Requred by Parcelable
	{
		public Problem createFromParcel(Parcel in)
		{
			return new Problem(in);
		}

		public Problem[] newArray(int size)
		{
			return new Problem[size];
		}
	};
}
