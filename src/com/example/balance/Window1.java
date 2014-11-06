package com.example.balance;

import java.util.ArrayList;
import java.util.zip.Inflater;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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

		getListView().setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount) {
				if (firstVisibleItem + visibleItemCount >= totalItemCount) {
					getListView().setTranscriptMode(
							AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
				} else {
					getListView().setTranscriptMode(
							AbsListView.TRANSCRIPT_MODE_NORMAL);
				}
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
		c = db.query(tableName, null, null, null, null, null, " ID DESC ");

		OnItemLongClickListener itemLongListener = new OnItemLongClickListener() {

			@Override
			// Удаление обьекта
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
		// Построение списка
		if (c.moveToFirst()) {
			int getDate = c.getColumnIndex("date");
			int getSumm = c.getColumnIndex("summ");
			int getNotify = c.getColumnIndex("notify");
			int getMonth = c.getColumnIndex("month");
			int getYear = c.getColumnIndex("year");
			int getCategory = c.getColumnIndex("category");
			getKey = c.getColumnIndex("key");

			do {
				String a = c.getString(getSumm);
				String b = a.substring(1);
				int d = Integer.parseInt(b);
				String month = c.getString(getMonth);

				Count(d, month, c.getString(getKey));

				if (keyWord.equals("BALANCE")) {
					list.add(new MyList(c.getString(getDate), "Sum: "
							+ c.getString(getSumm) + ".00$", "Note: "
							+ c.getString(getNotify) + "\nCategory: "
							+ c.getString(getCategory), c.getString(getKey),
							null, null, null));
					if (c.getString(getSumm).charAt(0) == (char) 43) {
						total += d;
					} else {
						total -= d;
					}
				} else if (keyWord.equals(month)) {
					list.add(new MyList(c.getString(getDate), "Sum: "
							+ c.getString(getSumm) + ".00$", "Note: "
							+ c.getString(getNotify) + "\nCategory: "
							+ c.getString(getCategory), c.getString(getKey),
							null, null, null));
					if (c.getString(getSumm).charAt(0) == (char) 43) {
						total += d;
					} else {
						total -= d;
					}
				}
			} while (c.moveToNext());
		}

		if (c.getCount() == 0) {
			list.add(new MyList("No note yet..", null, null, null, null, null,
					null));
			balance.setText("BALANCE: 0.00$");
			balance.setTextColor(Color.parseColor("#23AD41"));
		} else {
			String totalTwo;
			if (total == 0) {
				totalTwo = String.valueOf(total) + ".00$";
			} else if (total > 0) {
				totalTwo = "+" + String.valueOf(total) + ".00$";
			} else {
				totalTwo = String.valueOf(total) + ".00$";
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
			jun.setText("Junaury " + monthCount[0] + ".00$");
		} else {
			jun.setText("Junaury");
		}
		if (monthCount[1] != 0) {
			feb.setText("February " + monthCount[1] + ".00$");
		} else {
			feb.setText("February");
		}
		if (monthCount[2] != 0) {
			mar.setText("March " + monthCount[2] + ".00$");
		} else {
			mar.setText("March");
		}
		if (monthCount[3] != 0) {
			apr.setText("April " + monthCount[3] + ".00$");
		} else {
			apr.setText("April");
		}
		if (monthCount[4] != 0) {
			may.setText("May " + monthCount[4] + ".00$");
		} else {
			may.setText("May");
		}
		if (monthCount[5] != 0) {
			june.setText("June " + monthCount[5] + ".00$");
		} else {
			june.setText("June");
		}
		if (monthCount[6] != 0) {
			july.setText("July " + monthCount[6] + ".00$");
		} else {
			july.setText("July");
		}
		if (monthCount[7] != 0) {
			aug.setText("August " + monthCount[7] + ".00$");
		} else {
			aug.setText("August");
		}
		if (monthCount[8] != 0) {
			sep.setText("September " + monthCount[8] + ".00$");
		} else {
			sep.setText("September");
		}
		if (monthCount[9] != 0) {
			oct.setText("October " + monthCount[9] + ".00$");
		} else {
			oct.setText("October");
		}
		if (monthCount[10] != 0) {
			nov.setText("November " + monthCount[10] + ".00$");
		} else {
			nov.setText("November");
		}
		if (monthCount[11] != 0) {
			dec.setText("December " + monthCount[11] + ".00$");
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
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_v1, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		AlertDialog.Builder builder = new AlertDialog.Builder(Window1.this);
		LayoutInflater inflater = Window1.this.getLayoutInflater();
		View vw = inflater.inflate(R.layout.two_button_lyaout, null);
		RadioGroup rg = (RadioGroup) vw.findViewById(R.id.radioGroup);
		RadioButton showMonth = (RadioButton) vw.findViewById(R.id.showMonth);
		RadioButton showCategory = (RadioButton) vw
				.findViewById(R.id.showCategory);

		onRadioButtonClicked(rg);
		builder.setView(inflater.inflate(R.layout.two_button_lyaout, null))
				.setPositiveButton("Cancel",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						})
				.setNegativeButton("Confirm",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						});
		AlertDialog alert = builder.create();
		alert.show();
		return super.onOptionsItemSelected(item);
	}

	public void onRadioButtonClicked(View view) {
		Log.d("MyLog", "--------------show 11111 on");

		switch (view.getId()) {
		case R.id.radioGroup:
			Log.d("MyLog", "radioGroupradioGroupradioGroupradioGroup month on");
		break;
		case R.id.showMonth:
				Log.d("MyLog", "show month on");
			break;
		case R.id.showCategory:
				Log.d("MyLog", "show category on");
			break;
		}
	}

	@Override
	public void onBackPressed() {
		AlertDialog.Builder builder = new AlertDialog.Builder(Window1.this);
		LayoutInflater inflater = Window1.this.getLayoutInflater();
		builder.setView(inflater.inflate(R.layout.exit_dialog_lyaout, null))
				.setTitle("Exit Balance?")
				.setPositiveButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				})
				.setNegativeButton("Yes",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								finish();
							}
						});
		AlertDialog alert = builder.create();
		alert.show();
	}
}
