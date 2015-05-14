package com.example.balance;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class Window3 extends Activity implements OnClickListener {
	private Calendar cal = Calendar.getInstance();
	private TextView date;
	private TextView summ;
	private EditText notify;
	private Button ok;
	private Button cancel;
	private CheckBox food, road, home, vacation, shop, medecine, car, family,
			pets, gifts, poker, other;
	private CheckBox[] chBox = {food, road, home, vacation, shop, medecine, car
			, family, pets, gifts, poker, other};
	private int chBoxid[] = {R.id.foodCheckBox, R.id.roadCheckBox, R.id.homeCheckBox,
			R.id.vacationCheckBox, R.id.shopCheckBox, R.id.medecineCheckBox, R.id.carCheckBox,
			R.id.familyCheckBox, R.id.petsCheckBox, R.id.giftsCheckBox, R.id.pokerCheckBox,
			R.id.otherCheckBox};
	private int DIALOG_DATE = 1;
	private int myYear = cal.get(Calendar.YEAR);
	private int myMonth = cal.get(Calendar.MONTH);
	private int myDay = cal.get(Calendar.DAY_OF_MONTH);
	private DBSQlite myDb;
	private final String tableName = "Sagayda4niyAlexeySQLiteTable";
	private String key;
	private String addUAH = "";
	private String[] month = { "Junaury", "February", "March", "April", "May",
			"June", "July", "August", "September", "October", "November",
			"December" };
	private String[] category = { "Food ", "Road ", "Home ", "Vacation ",
			"Shop ", "Medecine ", "Car ", "Family ", "Pets ", "Gifts ",
			"Poker ", "Other" };
	private ContentValues cv;
	private Button zero, one, two, three, four, 
			five, six , seven, eight, nine, 
			clear, bck;
	private Button[] buttons = {zero, one, two, three, four, five, six, seven, eight, nine, clear, bck};
	private int[] buttonsId = {R.id.buttonZero, R.id.buttonOne, R.id.buttonTwo, R.id.buttonThree, R.id.buttonFour,
			R.id.buttonFive, R.id.buttonSix, R.id.buttonSeven, R.id.buttonEight, R.id.buttonNine,
			R.id.buttonClear, R.id.buttonBck};
	private final String[] s = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.window3_v2);

		for (int i = 0; i < chBox.length; i++) {
			chBox[i] = (CheckBox) findViewById(chBoxid[i]);
		}
		
		date = (TextView) findViewById(R.id.editTextDate);
		summ = (TextView) findViewById(R.id.editTextSumm);
		notify = (EditText) findViewById(R.id.editTextNote);
		ok = (Button) findViewById(R.id.buttonOk);
		cancel = (Button) findViewById(R.id.buttonCancel);
		ok.setOnClickListener(this);
		cancel.setOnClickListener(this);
		date.setOnClickListener(this);
		summ.setOnClickListener(this);

		date.setText(myDay + " " + month[myMonth] + " " + myYear);
		summ.setText("Add sum");

		myDb = new DBSQlite(this, tableName, null, 1);
		Intent intent = getIntent();
		key = intent.getStringExtra(Window2.INTENT_EXTRA_MESSAGE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	}

	@Override
	public void onClick(View v) {
		cv = new ContentValues();
		SQLiteDatabase db = myDb.getWritableDatabase();
		String dateText = date.getText().toString();
		String summText = addUAH;
		String notifyText = notify.getText().toString();

		if (v.getId() == R.id.buttonOk) {
			if (summText.length() != 0 && addUAH != "0") {
				cv.put("date", dateText);
				cv.put("summ", key + summText);
				cv.put("notify", notifyText);
				cv.put("key", key);
				cv.put("month", month[myMonth]);
				cv.put("year", myYear);
				checkCheck();
				db.insert(tableName, null, cv);
			}
			db.close();
			finish();
		} else if (v.getId() == R.id.buttonCancel) {
			finish();
		} else if (v.getId() == R.id.editTextDate) {
			showDialog(DIALOG_DATE);
		} else if (v.getId() == R.id.editTextSumm) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(Window3.this);
			LayoutInflater inflater = LayoutInflater.from(Window3.this);
			View vw = inflater.inflate(R.layout.custom_dialog_lyaout, null);
			dialog.setView(vw);
			final TextView text = (TextView) vw.findViewById(R.id.username);
			text.setText("0");


			for (int i = 0; i < buttons.length; i++) {
				buttons[i] = (Button) vw.findViewById(buttonsId[i]);
			}

			for (int i = 0; i < 10; i++) {
				final int k = i;
				buttons[i].setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if ((String) text.getText() == "0") {
							text.setText(s[k]);
						} else if (text.getText().length() < 7) {
							String te = (String) text.getText();
							text.setText(te + s[k]);
						}
					}
				});
			}
			buttons[10].setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					text.setText("0");
				}
			});
			buttons[11].setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (text.getText().length() > 1) {
						String te = (String) text.getText();
						int len = text.getText().length();
						text.setText(te.substring(0, len - 1));
					} else if (text.getText().length() == 1) {
						text.setText("0");
					}
				}
			});
			dialog.setNegativeButton("Add",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int id) {
							String te = (String) text.getText();
							addUAH = te;
							summ.setText(te + ".00$");
							dialog.cancel();
						}
					});
			dialog.setPositiveButton("Cancel",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});
			dialog.show();
		}
	}

	private void checkCheck() {
		String categoryInsert = "";
		for (int i = 0; i < chBox.length; i++) {
			if(chBox[i].isChecked())
				categoryInsert += category[i];
		}
		cv.put("category", categoryInsert);
	}

	@Override
	@SuppressWarnings("deprecation")
	protected Dialog onCreateDialog(int id) {
		if (id == DIALOG_DATE) {
			DatePickerDialog tpd = new DatePickerDialog(this, myCallBack,
					myYear, myMonth, myDay);
			return tpd;
		}
		return super.onCreateDialog(id);
	}

	OnDateSetListener myCallBack = new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			date.setText(dayOfMonth + " " + month[monthOfYear] + " " + year);
			myMonth = monthOfYear;
			myYear = year;
		}
	};
}
