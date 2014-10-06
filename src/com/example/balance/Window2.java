package com.example.balance;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Window2 extends ActionBarActivity implements OnClickListener {
	Button back;
	Button dohod;
	Button rashod;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_window2);
		
		back = (Button)findViewById(R.id.backInWindow2);
		back.setOnClickListener(this);
		dohod = (Button)findViewById(R.id.DohodButtonInWindow2);
		dohod.setOnClickListener(this);
		rashod = (Button)findViewById(R.id.RashodButtonInWindow2);
		rashod.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if ( v.getId() == R.id.backInWindow2) {
			Log.d("MyLog", "backinWindow2 pressed");
			finish();
		}
		if ( v.getId() == R.id.DohodButtonInWindow2) {
			Log.d("MyLog", "DohodButtonInWindow2 pressed");
			Intent intent = new Intent(this, Window3.class);
			startActivity(intent);
		}
		
		if ( v.getId() == R.id.RashodButtonInWindow2) {
			Log.d("MyLog", "RashodButtonInWindow2 pressed");
			Intent intent = new Intent(this, Window3.class);
			startActivity(intent);
		}
	}
}
