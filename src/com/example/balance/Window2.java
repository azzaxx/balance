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
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_window2);
		
		back = (Button)findViewById(R.id.backInWindow2);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if ( v.getId() == R.id.backInWindow2) {
			Log.d("MyLog", "backinWindow2 pressed");
			Intent intent = new Intent(this, Window1.class);
			startActivity(intent);
		}
	}
}
