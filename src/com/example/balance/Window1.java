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
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

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
	private String[] month = { "Junaury", "February", "March", "April", "May",
			"June", "July", "August", "September", "October", "November",
			"December" };
	private long monthCount[] = new long[12];
	private Button profit;
	private Button lose;
	private Intent intent;
	private String keyWord = "BALANCE";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// setContentView(R.layout.window1_v2);
		setContentView(R.layout.window1_v3);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		intent = new Intent(this, Window3.class);
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
				keyWord = "BALANCE";
				onResume();
			}
		});
		jun.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				keyWord = month[0];
				onResume();
			}
		});
		feb.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				keyWord = month[1];
				onResume();
			}
		});
		mar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				keyWord = month[2];
				onResume();
			}
		});
		apr.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				keyWord = month[3];
				onResume();
			}
		});
		may.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				keyWord = month[4];
				onResume();
			}
		});
		june.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				keyWord = month[5];
				onResume();
			}
		});
		july.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				keyWord = month[6];
				onResume();
			}
		});
		aug.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				keyWord = month[7];
				onResume();
			}
		});
		sep.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				keyWord = month[8];
				onResume();
			}
		});
		oct.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				keyWord = month[9];
				onResume();
			}
		});
		nov.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				keyWord = month[10];
				onResume();
			}
		});
		dec.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				keyWord = month[11];
				onResume();
			}
		});
		profit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String message = "+";
				keyWord = "BALANCE";
				intent.putExtra(INTENT_EXTRA_MESSAGE, message);
				startActivity(intent);
			}
		});
		lose.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String message = "-";
				keyWord = "BALANCE";
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
				if (c.getCount() != position && keyWord == "BALANCE") {
					c.moveToFirst();
					c.move(position);

					int getId = c.getColumnIndex("id");
					final String i = c.getString(getId);
					// final String del = "id = " + i;

					int getSumm = c.getColumnIndex("summ");
					int getDate = c.getColumnIndex("date");
					int getNotify = c.getColumnIndex("notify");
					int getK = c.getColumnIndex("key");
					final String s = c.getString(getSumm);
					final String d = c.getString(getDate);
					final String n = c.getString(getNotify);
					final String k = c.getString(getK);

					final String del = "date = '" + d + "' AND summ = '" + s
							+ "' AND notify = '" + n + "' AND key= '" + k + "'"
							+ " AND id = " + i;
					AlertDialog.Builder builder = new AlertDialog.Builder(
							Window1.this);
					LayoutInflater inflater = Window1.this.getLayoutInflater();
					builder.setView(inflater.inflate(
							R.layout.exit_dialog_lyaout, null));
					builder.setTitle("Delete note?");
					builder.setCancelable(false);
					builder.setNegativeButton("Yes",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									db.delete(tableName, del, null);
									onResume(); // Вызывается тут для обновления
												// списка.
								}
							});
					builder.setPositiveButton("No",
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

				if (keyWord.equals("BALANCE")) {
					list.add(new MyList(c.getString(getDate), "Sum: "
							+ c.getString(getSumm) + ".00UAH", "Note: "
							+ c.getString(getNotify) + c.getString(getMonth)
							+ c.getString(getYear), c.getString(getKey), null,
							null, null));
					if (c.getString(getSumm).charAt(0) == (char) 43) {
						total += d;
					} else {
						total -= d;
					}
				} else if (keyWord.equals(month)) {
					list.add(new MyList(c.getString(getDate), "Sum: "
							+ c.getString(getSumm) + ".00UAH", "Note: "
							+ c.getString(getNotify) + c.getString(getMonth)
							+ c.getString(getYear), c.getString(getKey), null,
							null, null));
					if (c.getString(getSumm).charAt(0) == (char) 43) {
						total += d;
					} else {
						total -= d;
					}
				}
			} while (c.moveToNext());
		}

		if (c.getCount() == 0) {
			list.add(new MyList("No note yet..", null, null, null,
					null, null, null));
			balance.setText("BALANCE: 0.00UAH");
			balance.setTextColor(Color.parseColor("#23AD41"));
		} else {
			String totalTwo;
			if (total == 0) {
				totalTwo = String.valueOf(total) + ".00UAH";
			} else if (total > 0) {
				totalTwo = "+" + String.valueOf(total) + ".00UAH";
			} else {
				totalTwo = String.valueOf(total) + ".00UAH";
			}
			balance.setText(keyWord + ": " + totalTwo);
			if (total > 0) {
				balance.setTextColor(Color.parseColor("#23AD41"));
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

		setText();

		super.onResume();
	}

	private void setText() {
		if (monthCount[0] != 0) {
			jun.setText("Junaury " + monthCount[0] + ".00UAH");
		} else {
			jun.setText("Junaury");
		}
		if (monthCount[1] != 0) {
			feb.setText("February " + monthCount[1] + ".00UAH");
		} else {
			feb.setText("February");
		}
		if (monthCount[2] != 0) {
			mar.setText("March " + monthCount[2] + ".00UAH");
		} else {
			mar.setText("March");
		}
		if (monthCount[3] != 0) {
			apr.setText("April " + monthCount[3] + ".00UAH");
		} else {
			apr.setText("April");
		}
		if (monthCount[4] != 0) {
			may.setText("May " + monthCount[4] + ".00UAH");
		} else {
			may.setText("May");
		}
		if (monthCount[5] != 0) {
			june.setText("June " + monthCount[5] + ".00UAH");
		} else {
			june.setText("June");
		}
		if (monthCount[6] != 0) {
			july.setText("July " + monthCount[6] + ".00UAH");
		} else {
			july.setText("July");
		}
		if (monthCount[7] != 0) {
			aug.setText("August " + monthCount[7] + ".00UAH");
		} else {
			aug.setText("August");
		}
		if (monthCount[8] != 0) {
			sep.setText("September " + monthCount[8] + ".00UAH");
		} else {
			sep.setText("September");
		}
		if (monthCount[9] != 0) {
			oct.setText("October " + monthCount[9] + ".00UAH");
		} else {
			oct.setText("October");
		}
		if (monthCount[10] != 0) {
			nov.setText("November " + monthCount[10] + ".00UAH");
		} else {
			nov.setText("November");
		}
		if (monthCount[11] != 0) {
			dec.setText("December " + monthCount[11] + ".00UAH");
		} else {
			dec.setText("December");
		}

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
				.setTitle("Exit Balance?")
				.setPositiveButton("No",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						})
				.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						finish();
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}
}
