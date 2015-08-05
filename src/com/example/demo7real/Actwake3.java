package com.example.demo7real;

import java.util.Random;

import com.example.demo7real.Act1;
import com.example.demo7real.R;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Actwake3 extends Activity {

	Random random;
    int r;
	Vibrator v;
	Ringtone ringtone;
	
	TextView tv1;
	
	EditText thetype;Button thebutton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);  
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON); 
		setContentView(R.layout.activity_actwake3);

		random=new Random();
		r=random.nextInt(10000);
		
		tv1=(TextView)findViewById(R.id.textView1);
		//vibrate
		v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);		
		long[] pattern={1000,1000,1000};
		v.vibrate(pattern,0 );
		
		//ring
		Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
		if (alarmUri == null) {
			alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		        }
		Context context= this;
		ringtone = RingtoneManager.getRingtone(context, alarmUri);
		ringtone.play();
		//button stuff
        thetype=(EditText)findViewById(R.id.editText1);
		thebutton =(Button)findViewById(R.id.button1);
		thetype.addTextChangedListener(theWatcher);
		
	}

	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.actwake3, menu);
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
		thetype=(EditText)findViewById(R.id.editText1);
		String theString=thetype.getText().toString();
		AlarmReceiver.yeah=false;
		if(theString!=null){
			int res=Integer.parseInt(theString);
			if (res == r){
				v.cancel();
				
				ringtone.stop();

				tv1.setText("RIGHT!");
				Intent intent=new Intent(ClickedButton.getContext(), Act1.class);
				startActivityForResult(intent,0);}
			else if(res>r){
				tv1.setText("Too High");
				thetype.setText("");
			}
			else
				{tv1.setText("Too Low");
				thetype.setText("");
				}
			
				
	
	}
	}
}
