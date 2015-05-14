package com.example.balance;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.nhaarman.listviewanimations.appearance.simple.AlphaInAnimationAdapter;

public class Window1 extends ListActivity {
	protected static final String INTENT_EXTRA_MESSAGE = "Sagayda4niyAlexeyIntentMESSAGE";
	private final String tableName = "Sagayda4niyAlexeySQLiteTable";
	private DBSQlite myDb = new DBSQlite(this, tableName, null, 1);
	private long total = 0; // total  is summ of all
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
	private String[] category = { "Food ", "Road ", "Home ", "Vacation ",
			"Shop ", "Medecine ", "Car ", "Family ", "Pets ", "Gifts ",
			"Poker ", "Other" };
	private TextView[] textViews = { jun, feb, mar, apr, may, june, july, aug,
			sep, oct, nov, dec };
	private int[] tvId = { R.id.Junaury, R.id.February, R.id.March, R.id.April, 
			R.id.May, R.id.June, R.id.July, R.id.August,
			R.id.September, R.id.October, R.id.November, R.id.December };
	private String displayOptions = "category";
	private String keyWord = "BALANCE";
	private long monthCount[] = new long[12];
	private long categoryCount[] = new long[12];
	private Button profit;
	private Button lose;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.window1_v3);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		intent = new Intent(this, Window3.class);
		profit = (Button) findViewById(R.id.profit);
		lose = (Button) findViewById(R.id.lose);
		balance = (TextView) findViewById(R.id.textViewBalanceInShowAll);
		for (int i = 0; i < tvId.length; i++) {
			textViews[i] = (TextView) findViewById(tvId[i]);
		}
		
//		 SQLiteDatabase db = myDb.getWritableDatabase();
//		 db.delete("Sagayda4niyAlexeySQLiteTable", null, null);

		balance.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				keyWord = "BALANCE";
				onResume();
			}
		});
		for (int i = 0; i < 12; i++) {
			final int j = i;
			textViews[i].setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (displayOptions.equals("month")) {
						keyWord = month[j];
					} else {
						keyWord = category[j];
					}
					onResume();
				}
			});
		}
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
			categoryCount[i] = 0;
		}
		list = new ArrayList<MyList>();

		db = myDb.getWritableDatabase();
		c = db.query(tableName, null, null, null, null, null, " ID DESC ");

		OnItemLongClickListener itemLongListener = new OnItemLongClickListener() {

			@Override
			// deleting object
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
									onResume(); // refreshing
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
		// creating list
		if (c.moveToFirst()) {
			int getDate = c.getColumnIndex("date");
			int getSumm = c.getColumnIndex("summ");
			int getNotify = c.getColumnIndex("notify");
			int getMonth = c.getColumnIndex("month");
			int getCategory = c.getColumnIndex("category");
			getKey = c.getColumnIndex("key");

			do {
				String a = c.getString(getSumm);
				String b = a.substring(1);
				int d = Integer.parseInt(b);
				String month = c.getString(getMonth);
				String cat = c.getString(getCategory);

				if (displayOptions.equals("month")) {
					CountMonth(d, month, c.getString(getKey));
				} else {
					CountCategory(d, cat, c.getString(getKey));
				}

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
				} else if (haveSome(keyWord, cat)) {
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
		//animation list
		AlphaInAnimationAdapter animationAdapter = new AlphaInAnimationAdapter((BaseAdapter) adapter);
		animationAdapter.setAbsListView(getListView());
		setListAdapter(animationAdapter);
		
		getListView().setOnItemLongClickListener(itemLongListener);

		setText();
		
		super.onResume();
	}

	private boolean haveSome(String keyWord2, String cat) {
		if (cat.equals(keyWord2)) {
			return true;
		}
		for (int i = 0; i < 12; i++) {
			String s = cat;
			if (s.length() >= category[i].length()) {
				s = cat.substring(0, category[i].length());
			}
			if (s.equals(keyWord2)){
				return true;
			} else if (s.equals(category[i])) {
				cat = cat.substring(category[i].length());
			}
		}
		return false;
	}

	private void CountCategory(int d, String cat1, String key) {
		String s = cat1;
		for (int i = 0; i < category.length && s.length() > 0; i++) {
			if (s.length() >= category[i].length()) {
				s = cat1.substring(0, category[i].length());
			}
			if (s.equals(category[i])) {
				cat1 = cat1.substring(category[i].length());
				if (key.equals("+")) {
					categoryCount[i] += d;
				} else {
					categoryCount[i] -= d;
				}
			}
			s = cat1;
		}
	}

	private void CountMonth(int d, String month1, String key) {
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

	private void setText() {
		if (displayOptions.equals("month")) {
			for (int i = 0; i < 12; i++) {
				if (monthCount[i] != 0) {
					textViews[i].setText(month[i] + " " + monthCount[i]
							+ ".00$");
				} else {
					textViews[i].setText(month[i]);
				}
			}
		} else {
			for (int i = 0; i < 12; i++) {
				if (categoryCount[i] != 0) {
					textViews[i].setText(category[i] + " " + categoryCount[i]
							+ ".00$");
				} else {
					textViews[i].setText(category[i]);
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
		if (item.getItemId() == R.id.showMonth) {
			displayOptions = "month";
			onResume();
		} else if (item.getItemId() == R.id.showCategory) {
			displayOptions = "category";
			onResume();
		} else if (item.getItemId() == R.id.about) {
			AlertDialog.Builder builder = new AlertDialog.Builder(Window1.this);
			LayoutInflater inflater = Window1.this.getLayoutInflater();
			builder.setView(inflater.inflate(R.layout.two_button_lyaout, null))
					.setPositiveButton("Close",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int id) {
									dialog.cancel();
								}
							});
			AlertDialog alert = builder.create();
			alert.show();
		}
		return super.onOptionsItemSelected(item);
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
