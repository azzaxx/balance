package com.example.balance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Window1 extends ActionBarActivity implements OnClickListener {
	private Button editBalance;
	private Button showBalance;
	private Button exit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_window1);
		
		editBalance = (Button)findViewById(R.id.button1);
		showBalance = (Button)findViewById(R.id.button2);
		exit = (Button)findViewById(R.id.button3);
		
		editBalance.setOnClickListener(this);
		showBalance.setOnClickListener(this);
		exit.setOnClickListener(this);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.button1){
			Log.d("MyLog", "EditBalance Pressed");
			Intent intent = new Intent(this, Window2.class);
			startActivity(intent);
			
		} else if (v.getId() == R.id.button2) {
			Log.d("MyLog", "showBalance Pressed");
			
			Intent intent = new Intent(this, ShowAll.class);
			startActivity(intent);
		} else if (v.getId() == R.id.button3) {
			AlertDialog.Builder builder = new AlertDialog.Builder(Window1.this);
		    LayoutInflater inflater = Window1.this.getLayoutInflater();
		    builder.setView(inflater.inflate(R.layout.exit_dialog_lyaout, null))
		    		.setTitle("Вы действительно хотите выйти?")
		    		.setPositiveButton("Нет", new DialogInterface.OnClickListener() {
		               public void onClick(DialogInterface dialog, int id) {
		                   dialog.cancel();
		               }
		           })
		           .setNegativeButton("Да", new DialogInterface.OnClickListener() {
		               public void onClick(DialogInterface dialog, int id) {
		                   finish();
		               }
		           });      
		    AlertDialog alert = builder.create();
			alert.show();
		}
	}
	@Override
	public void onBackPressed() {
		AlertDialog.Builder builder = new AlertDialog.Builder(Window1.this);
	    LayoutInflater inflater = Window1.this.getLayoutInflater();
	    builder.setView(inflater.inflate(R.layout.exit_dialog_lyaout, null))
	    		.setTitle("Вы действительно хотите выйти?")
	    		.setPositiveButton("Нет", new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	                   dialog.cancel();
	               }
	           })
	           .setNegativeButton("Да", new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	                   finish();
	               }
	           });      
	    AlertDialog alert = builder.create();
		alert.show();
	}
}
