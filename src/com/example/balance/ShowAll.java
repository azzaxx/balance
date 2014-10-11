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
				list.add(new MyList(c.getString(getDate), c.getString(getSumm), c.getString(getNotify)
						, c.getString(getKey)));
		    } while (c.moveToNext());
		} else {
			list.add(new MyList("Записей пока не было..", null, null, null));
		}
		
		String[] from = {"date", "summ", "notify" };
		int[] to =  { R.id.dateCustomList, R.id.summCustomList, R.id.notifyCustomList };
		ListAdapter adapter = new CustomAdapter(this, list, R.layout.custom_list_layout, from, to);
		
		setListAdapter(adapter);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}
}
