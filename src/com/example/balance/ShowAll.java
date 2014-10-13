package com.example.balance;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;

public class ShowAll extends ListActivity {
	private final String tableName =  "MySQLiteTable";
	private DBSQlite myDb = new DBSQlite(this, tableName, null, 1);
	int total = 0;

	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		ArrayList<MyList> list = new ArrayList<MyList>();
		
		SQLiteDatabase db = myDb.getWritableDatabase();
		Cursor c = db.query(tableName, null, null, null, null, null, null);
		int getKey;
		
		if (c.moveToFirst()) {
			int getDate = c.getColumnIndex("date");
			int getSumm = c.getColumnIndex("summ");
			int getNotify = c.getColumnIndex("notify");
			getKey = c.getColumnIndex("key");
			
			
			do {
				String a = c.getString(getSumm);
				String b = a.substring(1);
				int d = Integer.parseInt(b);
				Log.d("MyLog", "New string is: " + b + " A string is: " + a + " D is: " + d);
				
				if (c.getString(getSumm).charAt(0) == (char)43) {
					total += d;
				} else {
					total -= d;
				}
				
				list.add(new MyList(c.getString(getDate), c.getString(getSumm)  + ".00UAH", c.getString(getNotify)
						, c.getString(getKey)));
		    } while (c.moveToNext());
		} 
		if ( c.getCount() == 0 ) {
			list.add(new MyList("Записей пока не было..", null, null, null));
		} else {
			String totalTwo;
			if ( total == 0 ) {
				totalTwo = String.valueOf(total) + ".00UAH";
			} else if ( total > 0 ) {
				totalTwo = "+" + String.valueOf(total) + ".00UAH";
			} else {
				totalTwo = "-" + String.valueOf(total) + ".00UAH";
			}
			list.add(new MyList("Итого:", totalTwo, null, null));
			Log.d("MyLog", "Total is: " + total);
		}
		
		String[] from = {"date", "summ", "notify" };
		int[] to =  { R.id.dateCustomList, R.id.summCustomList, R.id.notifyCustomList };
		ListAdapter adapter = new CustomAdapter(this, list, R.layout.custom_list_layout, from, to);
		
		setListAdapter(adapter);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
}
