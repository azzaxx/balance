package com.example.balance;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListAdapter;
import android.widget.TextView;

public class ShowAll extends ListActivity {
	private final String tableName = "MySQLiteTable";
	private DBSQlite myDb = new DBSQlite(this, tableName, null, 1);
	private int total = 0; // total summ
	private ListAdapter adapter;
	private ArrayList<MyList> list;
	private SQLiteDatabase db;
	private Cursor c;
	private int getKey;
	private TextView balance;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.custom_list_layout);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		balance = (TextView) findViewById(R.id.textViewBalanceInShowAll);
	}

	@Override
	protected void onResume() {
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
					// delete all

					AlertDialog.Builder builder = new AlertDialog.Builder(
							ShowAll.this);
					LayoutInflater inflater = ShowAll.this.getLayoutInflater();
					builder.setView(inflater.inflate(
							R.layout.exit_dialog_lyaout, null));
					builder.setTitle("�� ������������� ������ ������� �������?");
					builder.setCancelable(false);
					builder.setNegativeButton("��",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									db.delete(tableName, del, null);
									total = 0;
									onResume(); // refresh list
								}
							});
					builder.setPositiveButton("���",
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

				list.add(new MyList(c.getString(getDate), "�����: "
						+ c.getString(getSumm) + ".00UAH", "�������: "
						+ c.getString(getNotify), c.getString(getKey), null, null, null));
			} while (c.moveToNext());
		}

		if (c.getCount() == 0) {
			list.add(new MyList("������� ���� �� ����..", null, null, null, null, null, null));
			balance.setText("������: 0.00UAH");
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
//			list.add(new MyList("������:", "�����: " + totalTwo, null, null));
			balance.setText("������: "+ totalTwo);
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
