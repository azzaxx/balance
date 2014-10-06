package com.example.balance;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
			
//			AlertDialog.Builder builder = new AlertDialog.Builder(Window1.this);
//		    LayoutInflater inflater = Window1.this.getLayoutInflater();
//		    builder.setView(inflater.inflate(R.layout.exit_dialog_lyaout, null))
//		    		.setTitle("Показать баланс")
//		    		.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//		               public void onClick(DialogInterface dialog, int id) {
//		                   dialog.cancel();
//		               }
//		           });      
//		    AlertDialog alert = builder.create();
//			alert.show();
		} else if (v.getId() == R.id.button3) {
			Log.d("MyLog", "Exit Pressed");
			
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
			
//			// Реализация всплывающего окна из нового лайаута
//			LayoutInflater inflater = getLayoutInflater();
//			View layout = inflater.inflate(R.layout.toast_layout,
//			                               (ViewGroup) findViewById(R.id.toast_layout_root));
//
//			TextView text = (TextView) layout.findViewById(R.id.text);
//			text.setText("BYE BYE!!!!!!");
//			Toast toast = new Toast(getApplicationContext());
//			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//			toast.setDuration(Toast.LENGTH_LONG);
//			toast.setView(layout);
//			toast.show();
		   
			//TODO Не плохо было бы реализовать AsynkTask где-то тут.
//			finish();
		}
	}
}
