package com.example.demo7real;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

public class Act1 extends Activity {
	public static AlarmManager alarmManager;
    PendingIntent pendingIntent;
    public static ArrayList<PendingIntent> ringlist;
	SeekBar SeekBar1;
	TextView h;
	public static ListView lv1 ;
	SeekBar SeekBar2;
	TextView m;
	public static TimeDB TDB;
	
	public static ArrayList<Time> tilist;
	Vibrator v1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_act1);
		
		alarmManager=(AlarmManager) getSystemService(ALARM_SERVICE);
		v1 = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);		
   
		//ringlist=new ArrayList<PendingIntent>(300);
		TDB=new TimeDB(this);
		
		tilist = TDB.getTimelist();//new ArrayList<Time>();
		Collections.sort(tilist, new TimeComp());
		populate();

	}

	public void populate() {
		
		//Set the values

		

		TimeAdapter adapt = new TimeAdapter(this, R.layout.listitem, tilist);
		lv1 = (ListView) findViewById(R.id.listView1);
		lv1.setAdapter(adapt);
		
		
						
			
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.act1, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	int theH;
	int theM;
	public void secret(View ClickedButton){
		v1.vibrate(100);
		Intent intent=new Intent(ClickedButton.getContext(), Secret.class);
		startActivityForResult(intent,0);
	}
	public void addalarm(View ClickedButton) {
		v1.vibrate(100);

		final Dialog dialog = new Dialog(ClickedButton.getContext(),
				android.R.style.Theme_Holo_Light_NoActionBar_Fullscreen);
		dialog.setContentView(R.layout.adddialog);
		dialog.show();

		// SET HOUR IN DIALOG
		SeekBar1 = (SeekBar) dialog.findViewById(R.id.seekBar1);
		h = (TextView) dialog.findViewById(R.id.textView1);
		theH=0;
		SeekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar SeekBar1, int progress1,
					boolean fromUser) {
				// TODO Auto-generated method stub
				if (progress1 <= 9)
					h.setText("0" + String.valueOf(progress1));
				else
					h.setText(String.valueOf(progress1));
				theH = progress1;
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}
		});

		// SET MIN IN DIALOG

		SeekBar2 = (SeekBar) dialog.findViewById(R.id.seekBar2);
		theM=0;
		m = (TextView) dialog.findViewById(R.id.textView5);

		SeekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			@Override
			public void onProgressChanged(SeekBar SeekBar2, int progress2,
					boolean fromUser) {
				// TODO Auto-generated method stub
				if (progress2 <= 9)
					m.setText("0" + String.valueOf(progress2));
				else
					m.setText(String.valueOf(progress2));
				theM = progress2;
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}
		});

		// HANDLE DIALOG BUTTON "X"
		Button bnope = (Button) dialog.findViewById(R.id.button2);
		bnope.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				v1.vibrate(100);
				dialog.cancel();
			}
		});

		// HANDLE DIALOG BUTTON "O"
		Button byep = (Button) dialog.findViewById(R.id.button1);
		byep.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				v1.vibrate(100);
				boolean yeah;
				yeah = true;

				dialog.cancel();
				Time a = new Time(theH, theM);

				// ONLY ADD NEW ONE
				if (tilist.isEmpty())

					{tilist.add(a);
					TDB.addTime(a);
					Calendar curcal=Calendar.getInstance();
					
					Calendar calendar = Calendar.getInstance();
			        calendar.set(Calendar.HOUR_OF_DAY, a.getHour());
			        calendar.set(Calendar.MINUTE, a.getMin());
			        calendar.set(Calendar.SECOND, 0);
			        if (calendar.after(curcal)){}
			        // do nothing
			        else{
			        	int day = curcal.get(Calendar.DAY_OF_YEAR) + 1;
			        	calendar.set(Calendar.DAY_OF_YEAR, day);
			        }
			        String wtf=theH+theM+"";
			        int _id = Integer.parseInt(wtf);
			        Intent theintent=new Intent(Act1.this,AlarmReceiver.class);
			        theintent.putExtra("key", _id);
			        Log.e("CLGT1",wtf);
			        Log.e("CLGT",""+_id);
			        pendingIntent = PendingIntent.getBroadcast(Act1.this, _id, theintent, android.app.PendingIntent.FLAG_ONE_SHOT);
			        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
			        Log.e("WAKE1", "TIME IS: "+calendar.getTimeInMillis() +  " h"+a.getHour() + " m" + a.getMin());
					}
				
				else {
					for (int i = 0; i < tilist.size(); i++) {
						if (tilist.get(i).equals(a))
							yeah = false;
					}
					if (yeah == true)
						{tilist.add(a);
						TDB.addTime(a);
						
						Calendar curcal=Calendar.getInstance();
						
						Calendar calendar = Calendar.getInstance();
				        calendar.set(Calendar.HOUR_OF_DAY, a.getHour());
				        calendar.set(Calendar.MINUTE, a.getMin());
				        calendar.set(Calendar.SECOND, 0);
				        
				        if (calendar.after(curcal)){}
				        // do nothing
				        else{
				        	int day = curcal.get(Calendar.DAY_OF_YEAR) + 1;
				        	calendar.set(Calendar.DAY_OF_YEAR, day);
				        }
				        String wtf=theH+theM+"";
				        int _id = Integer.parseInt(wtf);
				        Log.e("CLGT1",wtf);
				        Log.e("CLGT",""+_id);
				        Intent theintent=new Intent(Act1.this,AlarmReceiver.class);
				        theintent.putExtra("key", _id);
				        pendingIntent = PendingIntent.getBroadcast(Act1.this, _id, theintent, android.app.PendingIntent.FLAG_UPDATE_CURRENT);
				        alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
				        Log.e("WAKE1", "TIME IS: "+calendar.getTimeInMillis() +  " h"+a.getHour() + " m" + a.getMin());}
						
				}
				Collections.sort(tilist, new TimeComp());
				populate();
		        }

			
		});

		
	}
	
	class TimeComp implements Comparator<Time> {
		@Override
		public int compare(Time t1, Time t2) {
			if (t1.getHour() < t2.getHour())
				return -1;
			else if (t1.getHour() > t2.getHour())
				return 1;
			else {
				if (t1.getMin() == t2.getMin())
					return 0;
				else if (t1.getMin() < t2.getMin())
					return -1;
				else
					return 1;
			}

		}
	}
}
