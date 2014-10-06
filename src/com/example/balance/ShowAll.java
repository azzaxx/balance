package com.example.balance;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class ShowAll extends Activity {
	TextView txV;
	private DBSQlite myDb;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_window_showall);
		
		txV = (TextView)findViewById(R.id.textViewInShowAll);
//		
//		SQLiteDatabase db = myDb.getWritableDatabase();
//		Cursor c = db.query("SQLiteTable", null, null, null, null, null, null);
//		
//		if (c.moveToFirst()) {
//			int getid = c.getColumnIndex("id");
//			int getDate = c.getColumnIndex("date");
//			int getSumm = c.getColumnIndex("summ");
//			int getnotify = c.getColumnIndex("notify");
//		// Тут ошибка. Каждый раз получается создаю новую строку и заменяю старую. Нужно добавлять ее.	
//			do {
//		          String s =
//		              "ID = " + c.getInt(getid) + 
//		              ", Date = " + c.getString(getDate) + 
//		              ", Summ = " + c.getString(getSumm) +
//		              ", Notify = " + c.getString(getnotify);
//		          txV.setText(s);
//		        } while (c.moveToNext());
//		} else {
//		        String s = "Записей пока не было";
//		        txV.setText(s);
//		}
//		
//		c.close();
	}
}
