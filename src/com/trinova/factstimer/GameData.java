package com.trinova.factstimer;
import java.util.Random;

import android.os.Parcel;
import android.os.Parcelable;



public class GameData implements Parcelable
{
	private Problem[] _problemlist;
	private int _problemcnt;
	private int _level;
	private int _curproblem;
	
	public GameData(int numproblems, int level)
	{
		_problemcnt = numproblems;
		_level = level;
		
		Random rand = new Random();
		_problemlist = new Problem[_problemcnt];
		for (int x = 0; x < _problemcnt; x++)
			_problemlist[x] = new Problem(_level, rand);
		
		_curproblem = 0;
	}
	
	public GameData(Parcel parcel)
	{
		_problemcnt = parcel.readInt();
		_level = parcel.readInt();
		_curproblem = parcel.readInt();
		_problemlist = new Problem[_problemcnt];
		parcel.readTypedArray(_problemlist, Problem.CREATOR);
	}
	
	public Problem getCurrentProblem() 
	{
		return _problemlist[_curproblem];
	}
	
	public int getProblemNumber()
	{
		return _curproblem + 1; // 1-based result
	}
	
	public int getProblemCount()
	{
		return _problemcnt;
	}
	
	public int getNumberCorrect()
	{
		int res = 0;
		for (int x = 0; x < _problemcnt; x++)
		{
			Problem problem = _problemlist[x];
			if (problem.isCorrect())
				res++;
		}
		
		return res;
	}
	
	public void nextProblem()
	{
		_curproblem++;
		if (_curproblem >= _problemcnt)
			_curproblem = 0; // wrap around for now
	}
	
	public int describeContents()
	{
		return 0;
	}

	public void writeToParcel(Parcel dest, int flags) // Requred by Parcelable
	{
		dest.writeInt(_problemcnt);
		dest.writeInt(_level);
		dest.writeInt(_curproblem);
		dest.writeTypedArray(_problemlist, 0);
	}
	
	public static final Parcelable.Creator<GameData> CREATOR = new Parcelable.Creator<GameData>() // Requred by Parcelable
	{
		public GameData createFromParcel(Parcel in)
		{
			return new GameData(in);
		}

		public GameData[] newArray(int size)
		{
			return new GameData[size];
		}
	};
}
