package com.example.demo7real;

public class Time {
	protected int hour;
	protected int min;
	public Time(int theH,int theM){
		hour=theH;
		min=theM;
		}
	public int getHour(){
		return hour;
	}
	public int getMin()
	{
		return min;
	}
	
	public void setHour(int newH){
		hour=newH;
	}
	
	public void setMin(int newM){
		min=newM;
	}
	public boolean equals(Time other){
		if ((other.getHour()==hour)&&(other.getMin()==min))
			return true;
		else
			return false;
	}
}
