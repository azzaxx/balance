package com.example.balance;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;

public class Window1 extends ListActivity {
	protected static final String INTENT_EXTRA_MESSAGE = "Sagayda4niyAlexeyIntentMESSAGE";
	private final String tableName = "Sagayda4niyAlexeySQLiteTable";
	private DBSQlite myDb = new DBSQlite(this, tableName, null, 1);
	private long total = 0; // total это сумма всего, выводится в конце экрана.
	private ListAdapter adapter;
	private ArrayList<MyList> list;
	private SQLiteDatabase db;
	private Cursor c;
	private int getKey;
	private TextView balance, jun, feb, mar, apr, may, june, july, aug, sep,
			oct, nov, dec;
	private String[] month = { "Января", "Февраля", "Марта", "Апреля", "Мая",
			"Июня", "Июля", "Августа", "Сентября", "Октября", "Ноября",
			"Декабря" };
	private long monthCount[] = new long[12];
	private Button profit;
	private Button lose;
	private Intent intent, intent2;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// setContentView(R.layout.window1_v2);
		setContentView(R.layout.window1_v3);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		intent = new Intent(this, Window3.class);
		intent2 = new Intent(this, Window2.class);
		profit = (Button) findViewById(R.id.profit);
		lose = (Button) findViewById(R.id.lose);
		balance = (TextView) findViewById(R.id.textViewBalanceInShowAll);
		jun = (TextView) findViewById(R.id.Junaury);
		feb = (TextView) findViewById(R.id.February);
		mar = (TextView) findViewById(R.id.March);
		apr = (TextView) findViewById(R.id.April);
		may = (TextView) findViewById(R.id.May);
		june = (TextView) findViewById(R.id.June);
		july = (TextView) findViewById(R.id.July);
		aug = (TextView) findViewById(R.id.August);
		sep = (TextView) findViewById(R.id.September);
		oct = (TextView) findViewById(R.id.October);
		nov = (TextView) findViewById(R.id.November);
		dec = (TextView) findViewById(R.id.December);

		// SQLiteDatabase db = myDb.getWritableDatabase();
		// db.delete("Sagayda4niyAlexeySQLiteTable", null, null);
		balance.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(intent2);

			}
		});
		profit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String message = "+";
				intent.putExtra(INTENT_EXTRA_MESSAGE, message);
				startActivity(intent);
			}
		});
		lose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String message = "-";
				intent.putExtra(INTENT_EXTRA_MESSAGE, message);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onResume() {
		total = 0;
		for (int i = 0; i < month.length; i++) {
			monthCount[i] = 0;
		}
		list = new ArrayList<MyList>();

		db = myDb.getWritableDatabase();
		c = db.query(tableName, null, null, null, null, null, null);

		OnItemLongClickListener itemLongListener = new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View v,
					int position, long id) {
				if (c.getCount() != position) {
					c.moveToFirst();
					c.move(position);

					int getId = c.getColumnIndex("id");
					final String i = c.getString(getId);
					final String del = "id = " + i;
					AlertDialog.Builder builder = new AlertDialog.Builder(
							Window1.this);
					LayoutInflater inflater = Window1.this.getLayoutInflater();
					builder.setView(inflater.inflate(
							R.layout.exit_dialog_lyaout, null));
					builder.setTitle("Вы действительно хотите удалить заметку?");
					builder.setCancelable(false);
					builder.setNegativeButton("Да",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									db.delete(tableName, del, null);
									onResume(); // Вызывается тут для обновления
												// списка.
								}
							});
					builder.setPositiveButton("Нет",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									dialog.cancel();
								}
							});
					builder.show();
				}
				return true;
			}
		};

		if (c.moveToFirst()) {
			int getDate = c.getColumnIndex("date");
			int getSumm = c.getColumnIndex("summ");
			int getNotify = c.getColumnIndex("notify");
			int getMonth = c.getColumnIndex("month");
			int getYear = c.getColumnIndex("year");
			getKey = c.getColumnIndex("key");

			do {
				String a = c.getString(getSumm);
				String b = a.substring(1);
				int d = Integer.parseInt(b);
				String month = c.getString(getMonth);

				Count(d, month, c.getString(getKey));
				Log.d("MyLog", "summ is: ------ " + d + " Month is: -----"
						+ month);
				if (c.getString(getSumm).charAt(0) == (char) 43) {
					total += d;
				} else {
					total -= d;
				}

				list.add(new MyList(c.getString(getDate), "Сумма: "
						+ c.getString(getSumm) + ".00UAH", "Заметка: "
						+ c.getString(getNotify) + c.getString(getMonth)
						+ c.getString(getYear), c.getString(getKey), null,
						null, null));
			} while (c.moveToNext());
		}

		if (c.getCount() == 0) {
			list.add(new MyList("Записей пока не было..", null, null, null,
					null, null, null));
			balance.setText("BALANCE: 0.00UAH");
			balance.setTextColor(Color.RED);
		} else {
			String totalTwo;
			if (total == 0) {
				totalTwo = String.valueOf(total) + ".00UAH";
			} else if (total > 0) {
				totalTwo = "+" + String.valueOf(total) + ".00UAH";
			} else {
				totalTwo = String.valueOf(total) + ".00UAH";
			}
			balance.setText("BALANCE: " + totalTwo);
			if (total > 0) {
				balance.setTextColor(Color.GREEN);
			} else {
				balance.setTextColor(Color.RED);
			}
		}

		String[] from = { "date", "summ", "notify" };
		int[] to = { R.id.dateCustomList, R.id.summCustomList,
				R.id.notifyCustomList };
		adapter = new CustomAdapter(this, list, R.layout.custom_list_veiw,
				from, to);

		setListAdapter(adapter);
		getListView().setOnItemLongClickListener(itemLongListener);

		if (monthCount[0] != 0)
			jun.setText("Junaury " + monthCount[0] + ".00UAH");
		if (monthCount[1] != 0)
			feb.setText("February " + monthCount[1] + ".00UAH");
		if (monthCount[2] != 0)
			mar.setText("March " + monthCount[2] + ".00UAH");
		if (monthCount[3] != 0)
			apr.setText("April " + monthCount[3] + ".00UAH");
		if (monthCount[4] != 0)
			may.setText("May " + monthCount[4] + ".00UAH");
		if (monthCount[5] != 0)
			june.setText("June " + monthCount[5] + ".00UAH");
		if (monthCount[6] != 0)
			july.setText("July " + monthCount[6] + ".00UAH");
		if (monthCount[7] != 0)
			aug.setText("August " + monthCount[7] + ".00UAH");
		if (monthCount[8] != 0)
			sep.setText("September " + monthCount[8] + ".00UAH");
		if (monthCount[9] != 0)
			oct.setText("October " + monthCount[9] + ".00UAH");
		if (monthCount[10] != 0)
			nov.setText("March " + monthCount[10] + ".00UAH");
		if (monthCount[11] != 0)
			dec.setText("November " + monthCount[11] + ".00UAH");

		super.onResume();
	}

	private void Count(int d, String month1, String key) {
		for (int i = 0; i < month.length; i++) {
			if (month[i].equals(month1)) {
				if (key.equals("+")) {
					monthCount[i] += d;
				} else {
					monthCount[i] -= d;
				}
			}
		}
	}

	@Override
	public void onBackPressed() {
		AlertDialog.Builder builder = new AlertDialog.Builder(Window1.this);
		LayoutInflater inflater = Window1.this.getLayoutInflater();
		builder.setView(inflater.inflate(R.layout.exit_dialog_lyaout, null))
				.setTitle("Вы действительно хотите выйти?")
				.setPositiveButton("Нет",
						new DialogInterface.OnClickListener() {
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
	}
}
