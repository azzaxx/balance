package com.example.balance;

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
		} else if (v.getId() == R.id.button3) {
			Log.d("MyLog", "Exit Pressed");
			
			// Реализация всплывающего окна из нового лайаута
			LayoutInflater inflater = getLayoutInflater();
			View layout = inflater.inflate(R.layout.toast_layout,
			                               (ViewGroup) findViewById(R.id.toast_layout_root));

			TextView text = (TextView) layout.findViewById(R.id.text);
			text.setText("BYE BYE!!!!!!");
			Toast toast = new Toast(getApplicationContext());
			toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
			toast.setDuration(Toast.LENGTH_LONG);
			toast.setView(layout);
			toast.show();
		   
			//TODO Не плохо было бы реализовать AsynkTask где-то тут.
		    // Финиш - закрытие приложения
			finish();
		}
	}
}
