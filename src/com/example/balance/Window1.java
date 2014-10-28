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
import android.widget.TextView;
import android.widget.AdapterView.OnItemLongClickListener;

public class Window1 extends ListActivity {
	protected static final String INTENT_EXTRA_MESSAGE = "Sagayda4niyAlexeyIntentMESSAGE";
	private final String tableName = "MySQLiteTable";
	private DBSQlite myDb = new DBSQlite(this, tableName, null, 1);
	private long total = 0; // total это сумма всего, выводится в конце экрана.
	private ListAdapter adapter;
	private ArrayList<MyList> list;
	private SQLiteDatabase db;
	private Cursor c;
	private int getKey;
	private TextView balance;
	private Button profit;
	private Button lose;
	private Intent intent; 
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.window1_v2);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		intent = new Intent(this, Window3.class);
		profit = (Button) findViewById(R.id.profit);
		lose = (Button) findViewById(R.id.lose);
		balance = (TextView) findViewById(R.id.textViewBalanceInShowAll);
		
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

					int getSumm = c.getColumnIndex("summ");
					int getDate = c.getColumnIndex("date");
					int getNotify = c.getColumnIndex("notify");
					int getK = c.getColumnIndex("key");

					final String s = c.getString(getSumm);
					final String d = c.getString(getDate);
					final String n = c.getString(getNotify);
					final String k = c.getString(getK);
					final String del = "date = '" + d + "' AND summ = '" + s
							+ "' AND notify = '" + n + "' AND key= '" + k + "'";
					// строка для удаления записи с базы данных

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
			getKey = c.getColumnIndex("key");

			do {
				String a = c.getString(getSumm);
				String b = a.substring(1);
				int d = Integer.parseInt(b);

				if (c.getString(getSumm).charAt(0) == (char) 43) {
					total += d;
				} else {
					total -= d;
				}

				list.add(new MyList(c.getString(getDate), "Сумма: "
						+ c.getString(getSumm) + ".00UAH", "Заметка: "
						+ c.getString(getNotify), c.getString(getKey)));
			} while (c.moveToNext());
		}

		if (c.getCount() == 0) {
			list.add(new MyList("Записей пока не было..", null, null, null));
			balance.setText("Баланс: 0.00UAH");
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
			balance.setText("Баланс: "+ totalTwo);
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
		super.onResume();
	}
}
