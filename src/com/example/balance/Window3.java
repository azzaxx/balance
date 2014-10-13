package com.example.balance;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class Window3 extends Activity implements OnClickListener {
	private Calendar cal = Calendar.getInstance(); 
	private TextView date;
	private EditText summ;
	private EditText notify;
	private Button ok;
	private Button cancel;
	private int DIALOG_DATE = 1;
	private int myYear = cal.get(Calendar.YEAR);
	private int myMonth = cal.get(Calendar.MONTH);
	private int myDay = cal.get(Calendar.DAY_OF_MONTH);
	private DBSQlite myDb;
	private final String tableName = "MySQLiteTable";
	private String key;
	private String[] month = {"Янв", "Фев", "Март", "Апр", "Май", "Июнь", "Июль", "Авг"
							, "Сент", "Окт", "Нояб", "Декаб"};
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_window3);
		
		date = (TextView)findViewById(R.id.editTextDate);
		summ = (EditText)findViewById(R.id.editTextSumm);
		notify = (EditText)findViewById(R.id.editTextNote);
		ok = (Button)findViewById(R.id.buttonOk);
		cancel = (Button)findViewById(R.id.buttonCancel);
		
		ok.setOnClickListener(this);
		cancel.setOnClickListener(this);
		date.setOnClickListener(this);
		summ.setOnClickListener(this);

		date.setText("Добавленно: " + myDay + "." + month[myMonth] + "." + myYear + "г.");

		myDb = new DBSQlite(this, tableName, null, 1);
		Intent intent = getIntent();
		key = intent.getStringExtra(Window2.INTENT_EXTRA_MESSAGE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		ContentValues cv = new ContentValues();
		SQLiteDatabase db = myDb.getWritableDatabase();
		String dateText = date.getText().toString();
		String summText = summ.getText().toString();
		String notifyText = notify.getText().toString();
		
		if (v.getId() == R.id.buttonOk) {
			if ( summText.length() != 0 ) {
				cv.put("date", dateText);
				cv.put("summ", key + summText);
				cv.put("notify", notifyText);
				cv.put("key", key);
				db.insert(tableName, null, cv);
			}
			finish();
		} else if (v.getId() == R.id.buttonCancel) {
			finish();
		} else if (v.getId() == R.id.editTextDate) {
			showDialog(DIALOG_DATE);
		} else if (v.getId() == R.id.editTextSumm) {
			
		}
	}
	
    @SuppressWarnings("deprecation")
	protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
        	DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, myYear, myMonth, myDay);
        	return tpd;
        }
       
        return super.onCreateDialog(id);
    }
      
    OnDateSetListener myCallBack = new OnDateSetListener() {

    	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//    		myYear = year;
//    		myMonth = monthOfYear;
//    		myDay = dayOfMonth;
    		date.setText("Добавленно: " + dayOfMonth + "." + month[monthOfYear] + "." + year + "г.");
    	}
      };
}
