package com.example.tdav;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Tra_Tu_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tra__tu_);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tra__tu_, menu);
		return true;
	}

}
