package com.trinova.factstimer;
import java.util.Random;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.Time;



public class GameData implements Parcelable
{
	private Problem[] _problemlist;
	private int _problemcnt;
	private int _level;
	private int _curproblem;
	private boolean _gameover;
	private Time _starttime, _endtime;
	
	public GameData(int numproblems, int level)
	{
		_problemcnt = numproblems;
		_level = level;
		
		Random rand = new Random();
		_problemlist = new Problem[_problemcnt];
		for (int x = 0; x < _problemcnt; x++)
			_problemlist[x] = new Problem(_level, rand);
		
		_curproblem = 0;
		_gameover = false;
	}
	
	public GameData(Parcel parcel)
	{
		_problemcnt = parcel.readInt();
		_level = parcel.readInt();
		_curproblem = parcel.readInt();
		_problemlist = new Problem[_problemcnt];
		if (parcel.readInt() == 1)
		{
			_starttime = new Time();
			_starttime.set(parcel.readLong());
		}
		if (parcel.readInt() == 1)
		{
			_endtime = new Time();
			_endtime.set(parcel.readLong());
		}
		
		parcel.readTypedArray(_problemlist, Problem.CREATOR);
	}
	
	public void startTime()
	{
		if (_starttime != null)
			return;
		
		_starttime = new Time();
		_starttime.setToNow();
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
	
	public boolean isGameOver()
	{
		return _gameover;
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
	
	public String getProblemGrade()
	{
		double percent = (double)getNumberCorrect() / (double)_problemcnt;
		
		if (percent >= .9)
			return "A";
		else if (percent >= .8)
			return "B";
		else if (percent >= .7)
			return "C";
		else if (percent >= .6)
			return "D";
		
		return "F";
	}
	
	public long getTotalTime()
	{
		long diff = _endtime.toMillis(false) - _starttime.toMillis(false); // The time in milliseconds
		
		long seconds = diff / 1000;
		
		return seconds;
	}
	
	public String getTimeGrade()
	{
		double timeperproblem = getTotalTime() / _problemcnt; 

		if (timeperproblem <= 4)
			return "A";
		else if (timeperproblem <= 6)
			return "B";
		else if (timeperproblem <= 12)
			return "C";
		else if (timeperproblem <= 20)
			return "D";
		
		return "F";
	}
	
	public void nextProblem()
	{
		_curproblem++;
		if (_curproblem >= _problemcnt)
		{
			_curproblem = 0;
			_gameover = true;
			_endtime = new Time();
			_endtime.setToNow();
		}
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
		dest.writeInt((_starttime != null ? 1 : 0));
		if (_starttime != null)
			dest.writeLong(_starttime.toMillis(false));
		dest.writeInt((_endtime != null ? 1 : 0));
		if (_endtime != null)
			dest.writeLong(_endtime.toMillis(false));
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
