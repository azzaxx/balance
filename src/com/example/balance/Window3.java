package com.example.balance;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class Window3 extends Activity implements OnClickListener {
	private TextView date;
	private TextView summ;
	private EditText notify;
	private Button ok;
	private Button cancel;
	private int DIALOG_DATE = 1;
	private int myYear = 2014;
	private int myMonth = 10;
	private int myDay = 04;
	private DBSQlite myDb;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_window3);
		
		date = (TextView)findViewById(R.id.editTextDate);
		summ = (TextView)findViewById(R.id.editTextSumm);
		notify = (EditText)findViewById(R.id.editTextNote);
		ok = (Button)findViewById(R.id.buttonOk);
		cancel = (Button)findViewById(R.id.buttonCancel);
		
		ok.setOnClickListener(this);
		cancel.setOnClickListener(this);
		date.setOnClickListener(this);
		summ.setOnClickListener(this);
		myDb = new DBSQlite(this, "SQLiteTable", null, 1);
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
			Log.d("MyLog", "----Insert in SQLiteTable ----");
			cv.put("date", dateText);
			cv.put("summ", summText);
			cv.put("notify", notifyText);
			long rowID = db.insert("SQLiteTable", null, cv);
			Log.d("MyLog", "--- Rows in SQLiteTable: --- " + rowID);
			
			finish();
		} else if (v.getId() == R.id.buttonCancel) {
			finish();
		} else if (v.getId() == R.id.editTextDate) {
			showDialog(DIALOG_DATE);
		} else if (v.getId() == R.id.editTextSumm) {
//			Cursor c = db.query("SQLiteTable", null, null, null, null, null, null);
//			
//			if (c.moveToFirst()) {
//				int getid = c.getColumnIndex("id");
//				int getDate = c.getColumnIndex("date");
//				int getSumm = c.getColumnIndex("summ");
//				int getnotify = c.getColumnIndex("notify");
//				
//				do {
//			          Log.d("MyLog",
//			              "ID = " + c.getInt(getid) + 
//			              ", Date = " + c.getString(getDate) + 
//			              ", Summ = " + c.getString(getSumm) +
//			              ", Notify = " + c.getString(getnotify));
//			        } while (c.moveToNext());
//			} else {
//			        Log.d("MyLog", "0 rows");
//			}
//			
//			c.close();
			      
			AlertDialog.Builder builder = new AlertDialog.Builder(Window3.this);
		    LayoutInflater inflater = Window3.this.getLayoutInflater();
		    builder.setView(inflater.inflate(R.layout.custom_dialog_lyaout, null))
		           .setNegativeButton("OK", new DialogInterface.OnClickListener() {
		               @Override
		               public void onClick(DialogInterface dialog, int id) {
		            	   dialog.cancel();
		               }
		           })
		           .setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
		               public void onClick(DialogInterface dialog, int id) {
		                   dialog.cancel();
		               }
		           });      
		    AlertDialog alert = builder.create();
			alert.show();
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
    		myYear = year;
    		myMonth = monthOfYear +1;
    		myDay = dayOfMonth;
    		date.setText("Добавленно: " + myDay + "." + myMonth + "." + myYear);
    	}
      };
}
