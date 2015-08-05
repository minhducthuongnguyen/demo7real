package com.example.demo7real;

import java.util.ArrayList;

import android.content.Context;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class TimeAdapter extends ArrayAdapter<Time> {
	private ArrayList<Time> Tlist;
	private Context thecontext;
	Vibrator v1;
	public TimeAdapter(Context context, int textViewResourceId,ArrayList<Time> items){
		super(context, textViewResourceId, items);
		thecontext=context;
		Tlist=items;
	}
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
		v1 = (Vibrator) thecontext.getSystemService(Context.VIBRATOR_SERVICE);	
            View v = convertView;
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)thecontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.listitem, null);
            }
            Time thetime = Tlist.get(position);
            int H=thetime.getHour();
            String truehour= H+"";
            if (H<10)
            	truehour="0"+truehour;
            int M=thetime.getMin();
            String truemin= M+"";
            if (M<10)
            	truemin="0"+truemin;
            if (thetime != null) {
                    TextView tv1 = (TextView) v.findViewById(R.id.textView1);
                    
                    if (tv1 != null) {
                          tv1.setText(""+truehour+":"+truemin);                            }
                   
                    }
            final int a=position;
            Button bt=(Button) v.findViewById(R.id.deletebutt);
            bt.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
						
					v1.vibrate(100);
					Act1.tilist.remove(a);
					
				//DELETE IN DATABASE
					Act1.TDB.DeleteDB();
					for (int i=0;i<Act1.tilist.size();i++)
						Act1.TDB.addTime(Act1.tilist.get(i));
					TimeAdapter adapt = new TimeAdapter(thecontext , R.layout.listitem, Act1.tilist);
					Act1.lv1.setAdapter(adapt);
					//DELETE RINGTONE?
					//if (Act1.ringlist.isEmpty()==false){
						//Act1.ringlist.get(a).cancel();
						//Act1.ringlist.remove(a);}
					
				}
			});
            return v;
    }
	

		
		
			
		

}