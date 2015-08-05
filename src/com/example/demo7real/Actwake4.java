package com.example.demo7real;

import java.util.Random;

import com.example.demo7real.Act1;
import com.example.demo7real.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Actwake4 extends Activity {

	Vibrator v;
	Ringtone ringtone;
	int[] colorlist= {0xFF1E90FF,0xFFCC0000,0xff2E8B57};
	String[] wordlist={"Green","Blue","Red"};
	TextView tv1;
	Button b1;//red
	Button b2;//green
	Button b3;//blue
	Button b4;
	Random random;
	int rint;
	int rstring;
	ProgressBar pb1;
	int value;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_actwake4);
		
		tv1=(TextView)findViewById(R.id.textView1);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		b3=(Button)findViewById(R.id.button3);
		b4=(Button)findViewById(R.id.button04);
		pb1=(ProgressBar)findViewById(R.id.progressBar1);
		value=0;
v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
	
		
		long[] pattern={1000,1000,1000};
		v.vibrate(pattern,0 );
		
		//ringtone
		Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
		if (alarmUri == null) {
			alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		        }
		Context context= this;
		ringtone = RingtoneManager.getRingtone(context, alarmUri);
		ringtone.play();
		randomize();
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actwake4, menu);
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
	public void randomize(){
		random=new Random();
		rint=random.nextInt(3);
		rstring=random.nextInt(3);
		tv1.setText(wordlist[rstring]);
		tv1.setTextColor(colorlist[rint]);}
	
	
	int color1,color2,color3;
	public void play(View ClickedButton){
		
		
		
		Drawable background1 = b1.getBackground();
        if (background1 instanceof ColorDrawable){
        	color1 = ((ColorDrawable) background1).getColor();
        	}
        
        Drawable background2 = b2.getBackground();
        if (background2 instanceof ColorDrawable){
        	color2 = ((ColorDrawable) background2).getColor();
        	}
        
        Drawable background3 = b3.getBackground();
        if (background3 instanceof ColorDrawable){
        	color3 = ((ColorDrawable) background3).getColor();
        	}
        
        
        if (((b1.isPressed())&&(wordlist[rstring].equals("Red")))||
        	((b2.isPressed())&&(wordlist[rstring].equals("Green"))||
        	((b3.isPressed())&&(wordlist[rstring].equals("Blue")))))
        	{value=value+5;
        	pb1.setProgress(value);
        	if (value<100)
        		randomize();
        	else
        		{b4.setClickable(true);
        		b4.setTextColor(Color.WHITE);
        		tv1.setText("DONE!");
        		tv1.setTextColor(Color.GRAY);
        		b1.setClickable(false);
        		b2.setClickable(false);
        		b3.setClickable(false);}
        		}
        else
        	randomize();
        	}
	
        
        	
        public void Stop(View ClickedButton){
        	v.cancel();
        	AlarmReceiver.yeah=false;
			ringtone.stop();
			
			Intent intent=new Intent(ClickedButton.getContext(), Act1.class);
			startActivityForResult(intent,0);}
        
    	@Override
    	public void onBackPressed() {
    		// TODO Auto-generated method stub
    		
    	}
        }
	
	



