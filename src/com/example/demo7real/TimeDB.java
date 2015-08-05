package com.example.demo7real;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TimeDB extends SQLiteOpenHelper{
	//DB version
	private static final int DB_VERSION=1;

	//DB Name
	private static final String DB_NAME="TIME_DB";
	
	//TABLE NAME
	private static final String TABLE_NAME="TABLE_1";
	
	//COLUMN NAME
	private static final String COLUMN_HOUR="COLUMN_HOUR";
	private static final String COLUMN_MIN="COLUMN_MIN";
	
	//CREATE TABLE
	private static final String CREATE_TABLE_TIME = "CREATE TABLE "
            + TABLE_NAME
            + "("
            + COLUMN_HOUR + " TEXT,"
            + COLUMN_MIN + " TEXT"
            + ")";

	
	
	public TimeDB(Context context, String name,SQLiteDatabase.CursorFactory factory, int version){
		super(context,name,factory,version);
	}
	
    public TimeDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
	
@Override
	public void onCreate(SQLiteDatabase db) {
	// TODO Auto-generated method stub
	db.execSQL(CREATE_TABLE_TIME);
}

@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	// TODO Auto-generated method stub
		
	}


	public void closeDatabase() {
	    SQLiteDatabase db = getReadableDatabase();
	    if (db != null && db.isOpen())
	        db.close();
	}
	public ArrayList<Time> getTimelist(){
		ArrayList<Time> timelist=new ArrayList<Time>();
		String selectQuery = "SELECT  * FROM " + TABLE_NAME ;
		SQLiteDatabase db=getReadableDatabase();
		Cursor c= db.rawQuery(selectQuery, null);
		//loop and add to list
		if(c.moveToFirst()){
			do{
				Time t=new Time(0,0);
				t.setHour(c.getInt(c.getColumnIndex(COLUMN_HOUR)));
				t.setMin(c.getInt(c.getColumnIndex(COLUMN_MIN)));
				timelist.add(t);}
			while(c.moveToNext());}
		closeDatabase();
		return timelist;
			}
	public void DeleteDB(){
		SQLiteDatabase db = getReadableDatabase();
		if(db!=null)
			db.delete(TABLE_NAME, "1",null);
	}
	
	
	public boolean addTime(Time atime ){
		SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        int H=atime.getHour();
        int M=atime.getMin();
        values.put(COLUMN_HOUR,H );
        values.put(COLUMN_MIN, M);
        if(db.insert(TABLE_NAME,null, values)!=0){
        	closeDatabase();
        	return true;
        }
        else
        {closeDatabase();
        return false;
        }
	}
	
}
	
	
	
	

