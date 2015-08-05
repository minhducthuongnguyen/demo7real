package com.example.demo7real;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class Actwake2 extends Activity {
	Vibrator v;
	Ringtone ringtone;
	Random random;
	int r;
	
	Button[] buttlist;
	Button b1;
	Button b2;
	Button b3;
	Button b4;
	Button b5;
	Button b6;
	Button b7;
	Button b8;
	Button b9;
	Button b10;
	int point;
	TextView tv1;
	TextView tv2;
	
	boolean game;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);  
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON); 
		setContentView(R.layout.activity_actwake2);
		game=true;
		//textview 
		tv1=(TextView)findViewById(R.id.textView1);
		tv2=(TextView)findViewById(R.id.textView2);
		point=0;
		
		//vibrate
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
				
				
		//button
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		b3=(Button)findViewById(R.id.button3);
		b4=(Button)findViewById(R.id.button4);
		b5=(Button)findViewById(R.id.button5);
		b6=(Button)findViewById(R.id.button6);
		b7=(Button)findViewById(R.id.button7);
		b8=(Button)findViewById(R.id.button8);
		b9=(Button)findViewById(R.id.button9);
		b10=(Button)findViewById(R.id.button10);
		buttlist=new Button[]{b1,b2,b3,b4,b5,b6,b7,b8,b9};
		
		//randomize
		randomize();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actwake2, menu);
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
	public void Stop(View ClickedButton){
		AlarmReceiver.yeah=false;
		v.cancel();
		
		ringtone.stop();
				Intent intent=new Intent(ClickedButton.getContext(), Act1.class);
				startActivityForResult(intent,0);}
		
		
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
	}
	
	public void Check(View ClickedButton){
		if (point+1<20){
			String s1=b1.getText().toString();
			String s2=b2.getText().toString();
			String s3=b3.getText().toString();
			String s4=b4.getText().toString();
			String s5=b5.getText().toString();
			String s6=b6.getText().toString();
			String s7=b7.getText().toString();
			String s8=b8.getText().toString();
			String s9=b9.getText().toString();
			String aim=new String("x");
			randomize();
			if (((b1.isPressed())&&(s1.equals(aim)))||
				((b2.isPressed())&&(s2.equals(aim)))||
				((b3.isPressed())&&(s3.equals(aim)))||
				((b4.isPressed())&&(s4.equals(aim)))||
				((b5.isPressed())&&(s5.equals(aim)))||
				((b6.isPressed())&&(s6.equals(aim)))||
				((b7.isPressed())&&(s7.equals(aim)))||
				((b8.isPressed())&&(s8.equals(aim)))||
				((b9.isPressed())&&(s9.equals(aim)))){
				point=point+1;
				tv1.setText(""+point);
				}
			else
				point=point-1;
				tv1.setText(""+point);
				
	}
		else
			
		{
		tv1.setText("DONE!");
		tv1.setTextColor(Color.GRAY);
		tv2.setText("");
		b1.setText("o");
		b2.setText("o");
		b3.setText("o");
		b4.setText("o");
		b5.setText("o");
		b6.setText("o");
		b7.setText("o");
		b8.setText("o");
		b9.setText("o");
		b1.setTextColor(Color.GRAY);
		b2.setTextColor(Color.GRAY);
		b3.setTextColor(Color.GRAY);
		b4.setTextColor(Color.GRAY);
		b5.setTextColor(Color.GRAY);
		b6.setTextColor(Color.GRAY);
		b7.setTextColor(Color.GRAY);
		b8.setTextColor(Color.GRAY);
		b9.setTextColor(Color.GRAY);
		
		b10.setTextColor(Color.WHITE);
		b10.setClickable(true);}
		
			
}
		public void randomize(){
			b1.setText("o");
			b2.setText("o");
			b3.setText("o");
			b4.setText("o");
			b5.setText("o");
			b6.setText("o");
			b7.setText("o");
			b8.setText("o");
			b9.setText("o");
			
			random= new Random();
			r=random.nextInt(9);
			buttlist[r].setText("x");	}}
