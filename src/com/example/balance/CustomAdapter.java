package com.example.balance;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class CustomAdapter extends SimpleAdapter {
	String keyValue;
	public CustomAdapter(Context context, List<? extends Map<String, ?>> data,
			int resource, String[] from, int[] to) {
		super(context, data, resource, from, to);
	}


	public void setViewText(TextView v, String text) {
		if (v.getId() == R.id.summCustomList && text != "" ) {
			if ( text.charAt(0) == (char)43 ) {
				v.setTextColor(Color.GREEN);
			} else {
				v.setTextColor(Color.RED);
			}
		}
		
		super.setViewText(v, text);
	}
}
