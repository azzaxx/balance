package com.example.balance;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DBSQlite extends SQLiteOpenHelper {

	public DBSQlite(Context context, String name, CursorFactory factory,
			int version) {
		super(context, "Sagayda4niyAlexeySQLiteTable", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE table Sagayda4niyAlexeySQLiteTable ("
		          + "id integer primary key autoincrement unique," 
		          + "date text,"
		          + "summ text,"
		          + "notify text,"
		          + "month text,"
		          + "category text,"
		          + "year text,"
		          + "key text" + ");");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
