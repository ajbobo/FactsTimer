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
	private static final int MAX_LEVEL = 2;
	private static final int OPERATOR_PLUS = 1;
	private static final int OPERATOR_MINUS = 2;
	private static final int OPERATOR_TIMES = 3;
	private static final int OPERATOR_DIVIDE = 4;
	
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
		case 1: makeLevel1(rand); break;
		case 2: makeLevel2(rand); break;
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
	
	private void makeLevel1(Random rand)
	{
		// Add two 1-digit numbers
		_operator = OPERATOR_PLUS;
		
		_operand1 = rand.nextInt(10);
		_operand2 = rand.nextInt(10);
		
		_result = _operand1 + _operand2;
	}
	
	private void makeLevel2(Random rand)
	{
		// Subtract two 1-digit numbers (no negative results)
		_operator = OPERATOR_MINUS;
		
		_operand1 = rand.nextInt(9) + 1; // 1-9
		_operand2 = rand.nextInt(_operand1 + 1); // Less than or equal to operator1
		
		_result = _operand1 - _operand2;
	}
	
	private String getOpString(int operator)
	{
		switch (operator)
		{
		case OPERATOR_PLUS: return "+";
		case OPERATOR_MINUS: return "-";
		case OPERATOR_TIMES: return "*";
		case OPERATOR_DIVIDE: return "/";
		}
		
		return "";
	}
	
	private String getNumString(int num)
	{
		// Always put space for 2 digits in the result
		if (num > 9)
			return Integer.toString(num);
		else if (num == NO_ANSWER)
			return "  ";
		
		return " " + num;
	}
	
	public void drawProblem(Canvas canvas, Rect viewRect, Paint paint)
	{
		int height = viewRect.height();
		int sectionheight = height / 3;
		paint.setTextSize(sectionheight);
		
		String numstr1 = getNumString(_operand1);
		String numstr2 = getNumString(_operand2);
		String opstr = getOpString(_operator);
		String answerstr = getNumString(_useranswer);
		
		// Come up with a better way to center the problem on the screen - FINISH ME
		int x = viewRect.left = (viewRect.width() / 2);
		int farleft = (int) (x - paint.measureText(opstr));
		int y = sectionheight - 5;
		canvas.drawText(numstr1, x, y, paint);
		y = sectionheight * 2 - 5;
		canvas.drawText(numstr2, x, y, paint);
		canvas.drawText(opstr, farleft, y, paint);
		y = sectionheight * 3 - 5;
		canvas.drawText(answerstr, x, y, paint);
		
		paint.setStrokeWidth(3);
		y = sectionheight * 2;
		canvas.drawLine(farleft, y, x + paint.measureText(numstr1), y, paint);
	}
	
	public boolean isCorrect()
	{
		if (_useranswer == _result)
			return true;
		return false;
	}
	
	public void addDigitToAnswer(int digit)
	{
		if (_useranswer == NO_ANSWER)
			_useranswer = digit;
		else
			_useranswer = (10 * _useranswer) + digit;
	}
	
	public void clearAnswer()
	{
		_useranswer = NO_ANSWER;
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
