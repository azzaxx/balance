package com.example.balance;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.Button;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

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
		} else if (v.getId() == R.id.button2) {
			Log.d("MyLog", "showBalance Pressed");
		} else if (v.getId() == R.id.button3) {
			Log.d("MyLog", "Exit Pressed");
		}
	}
}
