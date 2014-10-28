package com.example.balance;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Window2 extends Activity implements OnClickListener {
	private Button back;
	private Button dohod;
	private Button rashod;
	private Button clear;
	private final String tableName =  "MySQLiteTable";
	private DBSQlite myDb = new DBSQlite(this, tableName, null, 1);
	public final static String INTENT_EXTRA_MESSAGE = "Sagayda4niyAlexeyIntentMESSAGE";
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_window2);
		
		back = (Button)findViewById(R.id.backInWindow2);
		back.setOnClickListener(this);
		dohod = (Button)findViewById(R.id.DohodButtonInWindow2);
		dohod.setOnClickListener(this);
		rashod = (Button)findViewById(R.id.RashodButtonInWindow2);
		rashod.setOnClickListener(this);
		clear = (Button)findViewById(R.id.clearButtonInWindow2);
		clear.setOnClickListener(this);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.backInWindow2) {
			finish();
		}
		if (v.getId() == R.id.DohodButtonInWindow2) {
			Intent intent = new Intent(this, Window3.class);
			String message = "+";
			intent.putExtra(INTENT_EXTRA_MESSAGE, message);
			startActivity(intent);
		}
		if (v.getId() == R.id.RashodButtonInWindow2) {
			Intent intent = new Intent(this, Window3.class);
			String message = "-";
			intent.putExtra(INTENT_EXTRA_MESSAGE, message);
			startActivity(intent);
		}
		if (v.getId() == R.id.clearButtonInWindow2) {
			AlertDialog.Builder builder = new AlertDialog.Builder(Window2.this);
		    LayoutInflater inflater = Window2.this.getLayoutInflater();
		    builder.setView(inflater.inflate(R.layout.exit_dialog_lyaout, null))
		    		.setTitle("Вы действительно хотите очистить базу?")
		    		.setPositiveButton("Нет", new DialogInterface.OnClickListener() {
		               public void onClick(DialogInterface dialog, int id) {
		                   dialog.cancel();
		               }
		           })
		           .setNegativeButton("Да", new DialogInterface.OnClickListener() {
		               public void onClick(DialogInterface dialog, int id) {
		            	   SQLiteDatabase db = myDb.getWritableDatabase();
		            	   db.delete(tableName, null, null);
		            	   db.close();
		            	   dialog.cancel();
		               }
		           });      
		    AlertDialog alert = builder.create();
			alert.show();
		}
	}
}
