package com.example.balance;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListAdapter;
import android.widget.TextView;

public class ProfLossWindowActivity extends ListActivity {
	private TextView balance;
	private final String tableName = "Sagayda4niyAlexeySQLiteTable";
	private DBSQlite myDb = new DBSQlite(this, tableName, null, 1);
	public final static String INTENT_EXTRA_MESSAGE = "Sagayda4niyAlexeyIntentMESSAGE";
	private long total = 0; // total summ of all, displays at end
	private ListAdapter adapter;
	private ArrayList<MyList> list;
	private SQLiteDatabase db;
	private Cursor c;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.custom_list_layout);
		
		balance = (TextView)findViewById(R.id.textViewBalanceInShowAll);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		list = new ArrayList<MyList>();

		db = myDb.getWritableDatabase();
		c = db.query(tableName, null, null, null, null, null, null);
		
		if (c.moveToFirst()) {
			int getDate = c.getColumnIndex("date");
			int getSumm = c.getColumnIndex("summ");
			int getNotify = c.getColumnIndex("notify");
			int getMonth = c.getColumnIndex("month");
			int getKey = c.getColumnIndex("key");

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
						+ c.getString(getNotify) + c.getString(getMonth), c
						.getString(getKey), null, null, null));
			} while (c.moveToNext());
		}

		if (c.getCount() == 0) {
			list.add(new MyList("Записей пока не было..", null, null, null,
					null, null, null));
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
			balance.setText("Баланс: " + totalTwo);
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
	}
}
