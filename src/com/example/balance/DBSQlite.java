package com.example.balance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBSQlite extends SQLiteOpenHelper {

	public DBSQlite(Context context, String name, CursorFactory factory,
			int version) {
		super(context, "MySQLiteTable", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.d("MyLog", "SQLiteDatabase created");
		db.execSQL("CREATE table MySQLiteTable ("
		          + "id integer primary key autoincrement," 
		          + "date text,"
		          + "summ text,"
		          + "notify text,"
		          + "key text" + ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
