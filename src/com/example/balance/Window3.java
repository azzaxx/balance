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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Window3 extends Activity implements OnClickListener {
	private Calendar cal = Calendar.getInstance();
	private TextView date;
	private TextView summ;
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
	private String addUAH = "";
	private String[] month = { "Янв", "Фев", "Март", "Апр", "Май", "Июнь",
			"Июль", "Авг", "Сент", "Окт", "Нояб", "Декаб" };

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_window3);

		date = (TextView) findViewById(R.id.editTextDate);
		summ = (TextView) findViewById(R.id.editTextSumm);
		notify = (EditText) findViewById(R.id.editTextNote);
		ok = (Button) findViewById(R.id.buttonOk);
		cancel = (Button) findViewById(R.id.buttonCancel);
		ok.setOnClickListener(this);
		cancel.setOnClickListener(this);
		date.setOnClickListener(this);
		summ.setOnClickListener(this);

		date.setText("Добавленно: " + myDay + "." + month[myMonth] + "."
				+ myYear + "г.");
		summ.setText("Введите сумму");

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
		String summText = addUAH;
		String notifyText = notify.getText().toString();

		if (v.getId() == R.id.buttonOk) {
			if (summText.length() != 0 && addUAH != "0") {
				cv.put("date", dateText);
				cv.put("summ", key + summText);
				cv.put("notify", notifyText);
				cv.put("key", key);
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

			Button one = (Button) vw.findViewById(R.id.buttonOne);
			Button two = (Button) vw.findViewById(R.id.buttonTwo);
			Button three = (Button) vw.findViewById(R.id.buttonThree);
			Button four = (Button) vw.findViewById(R.id.buttonFour);
			Button five = (Button) vw.findViewById(R.id.buttonFive);
			Button six = (Button) vw.findViewById(R.id.buttonSix);
			Button seven = (Button) vw.findViewById(R.id.buttonSeven);
			Button eight = (Button) vw.findViewById(R.id.buttonEight);
			Button nine = (Button) vw.findViewById(R.id.buttonNine);
			Button zero = (Button) vw.findViewById(R.id.buttonZero);
			Button clear = (Button) vw.findViewById(R.id.buttonClear);
			Button bck = (Button) vw.findViewById(R.id.buttonBck);

			one.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					if ((String) text.getText() == "0") {
						text.setText("1");
					} else if (text.getText().length() < 9) {
						String te = (String) text.getText();
						text.setText(te + "1");
					}
				}
			});

			two.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					if ((String) text.getText() == "0") {
						text.setText("2");
					} else if (text.getText().length() < 9) {
						String te = (String) text.getText();
						text.setText(te + "2");
					}
				}
			});
			three.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					if ((String) text.getText() == "0") {
						text.setText("3");
					} else if (text.getText().length() < 9) {
						String te = (String) text.getText();
						text.setText(te + "3");
					}
				}
			});
			four.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					if ((String) text.getText() == "0") {
						text.setText("4");
					} else if (text.getText().length() < 9) {
						String te = (String) text.getText();
						text.setText(te + "4");
					}
				}
			});
			five.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					if ((String) text.getText() == "0") {
						text.setText("5");
					} else if (text.getText().length() < 9) {
						String te = (String) text.getText();
						text.setText(te + "5");
					}
				}
			});
			six.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					if ((String) text.getText() == "0") {
						text.setText("6");
					} else if (text.getText().length() < 9) {
						String te = (String) text.getText();
						text.setText(te + "6");
					}
				}
			});
			seven.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					if ((String) text.getText() == "0") {
						text.setText("7");
					} else if (text.getText().length() < 9) {
						String te = (String) text.getText();
						text.setText(te + "7");
					}
				}
			});
			eight.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					if ((String) text.getText() == "0") {
						text.setText("8");
					} else if (text.getText().length() < 9) {
						String te = (String) text.getText();
						text.setText(te + "8");
					}
				}
			});
			nine.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					if ((String) text.getText() == "0") {
						text.setText("9");
					} else if (text.getText().length() < 9) {
						String te = (String) text.getText();
						text.setText(te + "9");
					}
				}
			});
			zero.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					if ((String) text.getText() == "0") {
						text.setText("0");
					} else if (text.getText().length() < 9) {
						String te = (String) text.getText();
						text.setText(te + "0");
					}
				}
			});
			clear.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					text.setText("0");
				}
			});
			bck.setOnClickListener(new OnClickListener() {
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
			dialog.setNegativeButton("Добавить",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							String te = (String) text.getText();
							addUAH = te;
							summ.setText(te + ".00UAH");
							dialog.cancel();
						}
					});
			dialog.setPositiveButton("Отменить",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							dialog.cancel();
						}
					});
			dialog.show();
		}
	}

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
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			date.setText("Добавленно: " + dayOfMonth + "." + month[monthOfYear]
					+ "." + year + "г.");
		}
	};
}
