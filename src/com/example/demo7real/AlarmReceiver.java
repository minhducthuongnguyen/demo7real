package com.example.demo7real;





import java.util.Random;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
	Random random;
	int r;
public static boolean yeah;
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		boolean cool=false;
		int theID=intent.getIntExtra("key", 0);
		for (int i=0;i<Act1.tilist.size();i++){
			Time yo=Act1.tilist.get(i);
			int yoH=yo.getHour();
			int yoM=yo.getMin();
			String damn=yoH+yoM+"";
			int check=Integer.parseInt(damn);
			Log.e("DAMN", damn);
			Log.e("DAMN2",""+theID);
	        if (check==theID)
	        	cool=true;
	        else{}
		}
		
		if((cool==true)&&(yeah==false)){
			yeah=true;
		random=new Random();
		Log.e("WAKE1", "onReceive AlarmReceiver");
		r=random.nextInt(4);
		if (r==0){
			Intent intent1=new Intent(context, Actwake1.class);
			intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent1);}
		else if(r==1){
			Intent intent1=new Intent(context, Actwake2.class);
			intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent1);}
		else if(r==2){
			Intent intent1=new Intent(context, Actwake3.class);
			intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent1);}
			
		else if(r==3){
			Intent intent1=new Intent(context, Actwake4.class);
			intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent1);}
		}}
	}