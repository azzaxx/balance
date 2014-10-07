package com.example.balance;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ShowAll extends /*ListActivity*/ Activity implements android.view.View.OnClickListener {
	private final String tableName =  "SQLiteTable";
	private Button clear;
	private TextView txV;
	private DBSQlite myDb = new DBSQlite(this, tableName, null, 1);
	private String s = "";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_window_showall);
		
		clear = (Button)findViewById(R.id.clearButtonInShowAll);
		clear.setOnClickListener(this);
		txV = (TextView)findViewById(R.id.textViewInShowAll);
		txV.setText(s);
		txV.setTextColor(Color.parseColor("#bb33aa"));
		
		SQLiteDatabase db = myDb.getWritableDatabase();
		Cursor c = db.query(tableName, null, null, null, null, null, null);
		
		if (c.moveToFirst()) {
			int getDate = c.getColumnIndex("date");
			int getSumm = c.getColumnIndex("summ");
			int getnotify = c.getColumnIndex("notify");
			
			do {
		          s +=
		              c.getString(getDate) + "\n" +
		              "        Сумма изменения: " + c.getString(getSumm) + "\n" +
		              "        Заметка: " + c.getString(getnotify) + "\n" + "\n";
		          txV.setText(s);
		        } while (c.moveToNext());
		} else {
			s += "Записей пока не было...";
			txV.setText(s);
		}
		c.close();
		db.close();
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.clearButtonInShowAll) {
			SQLiteDatabase db = myDb.getWritableDatabase();
			long d = db.delete(tableName, null, null);
			db.close();
			startActivity(getIntent());
			finish();
		}
	}
}
