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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Actwake1 extends Activity {

	 Random r;
	    int random;
		Vibrator v;
		Ringtone ringtone;
		EditText thetype;
		boolean wake;
		Button thebutton;
		TextView thetext;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);  
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
			setContentView(R.layout.activity_actwake1);
			
			Log.e("WAKE1", "onreceive WAKE1 ");
			
			thetext=(TextView) findViewById(R.id.textView1);
			//random number
			randomize();
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
			Log.e("WAKE1", "RINGRINGRING ");
			
			//button stuff
	        thetype=(EditText)findViewById(R.id.editText1);
			thebutton =(Button)findViewById(R.id.button1);
			thetype.addTextChangedListener(theWatcher);
		}
		public void randomize(){
			r = new Random();
			random = r.nextInt(10000000 - 1000000) + 1000000;
			thetext.setText(""+random);
		}
		@Override
		public boolean onCreateOptionsMenu(Menu menu) {
			// Inflate the menu; this adds items to the action bar if it is present.
			getMenuInflater().inflate(R.menu.actwake1, menu);
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
		
		private final TextWatcher theWatcher = new TextWatcher() {
	        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

	        }

	        public void onTextChanged(CharSequence s, int start, int before, int count) {
	            thebutton.setClickable(true);
	            thebutton.setTextColor(Color.WHITE);
	        }

	        public void afterTextChanged(Editable s) {
	            if (s.length() == 0) {
	               thebutton.setClickable(false);
	               thebutton.setTextColor(Color.GRAY);
	            } else{
	                thebutton.setClickable(true);
	                thebutton.setTextColor(Color.WHITE);
	            }
	        }
	    };

	
	    
		public void Stop(View ClickedButton){
			
			AlarmReceiver.yeah=false;
			thetype=(EditText)findViewById(R.id.editText1);
			String theString=thetype.getText().toString();
			
			
			if(theString!=null){
			long res=Long.valueOf(theString);
			if (res == random){
				v.cancel();
				
				ringtone.stop();
				
				Intent intent=new Intent(ClickedButton.getContext(), Act1.class);
				startActivityForResult(intent,0);}
			else{
				Context context= ClickedButton.getContext();
				thetype.setText("");
		    	CharSequence text = "Wrong number!";
		    	randomize();
				int duration = Toast.LENGTH_SHORT;
				Toast toast = Toast.makeText(context, text, duration);
				toast.setGravity(0, 0, 0);
				toast.show(); 
		
			}}}}
