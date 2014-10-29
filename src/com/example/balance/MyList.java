package com.example.balance;

import java.util.HashMap;

public class MyList extends HashMap<String, Object> {
	protected String date;
	protected String summ;
	protected String notify;
	protected String key;
	
	public MyList(String date1, String summ1, String notify1, String key1, 
			String category1, String month1, String year1) {
		super();
		super.put("date", date1);
		super.put("summ", summ1);
		super.put("notify", notify1);
		super.put("key", key1);
		super.put("category", category1);
		super.put("month", month1);
		super.put("year", year1);
	}

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
}
