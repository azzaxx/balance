package com.example.balance;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Window3 extends Activity implements OnClickListener {
	private TextView date;
	private TextView summ;
	private TextView notify;
	private Button ok;
	private Button cancel;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_window3);
		
		date = (TextView)findViewById(R.id.editTextDate);
		summ = (TextView)findViewById(R.id.editTextSumm);
		notify = (TextView)findViewById(R.id.editTextNote);
		ok = (Button)findViewById(R.id.buttonOk);
		cancel = (Button)findViewById(R.id.buttonCancel);
		
		ok.setOnClickListener(this);
		cancel.setOnClickListener(this);
		date.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.buttonOk) {
			Log.d("MyLog", "Button OK Clicked");
			Intent intent = new Intent(this, Window2.class);
			startActivity(intent);
		} else if (v.getId() == R.id.buttonCancel) {
			Intent intent = new Intent(this, Window2.class);
			startActivity(intent);
			Log.d("MyLog", "Button Cancel Clicked");
		} else if (v.getId() == R.id.editTextDate) {
			AlertDialog.Builder builder = new AlertDialog.Builder(Window3.this);
			builder.setTitle("Важное сообщение!")
					.setMessage("Выбирите дату")
					.setCancelable(true)
					.setNegativeButton("Cancel",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									date.setText("Cancel pressed!!");
									dialog.cancel();
								}
							})
					.setNeutralButton("Ok", 
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									date.setText("Ok pressed!!");
									dialog.cancel();
								}
					});

			AlertDialog alert = builder.create();
			alert.show();
		}
	}
}
