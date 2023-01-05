package com.example.tdav;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Dieu_Khien_Activity extends Activity implements OnClickListener{
	Button btnGoiThem,btnGoiTra,btnGoiTuYT;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dieu__khien_);
		
		btnGoiThem = (Button)findViewById(R.id.buttonGoiThem);
		btnGoiTra = (Button)findViewById(R.id.buttonGoiTra);
		btnGoiTuYT = (Button)findViewById(R.id.buttonGoiTuYT);
		
		btnGoiThem.setOnClickListener(this);
		btnGoiTra.setOnClickListener(this);
		btnGoiTuYT.setOnClickListener(this);
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dieu__khien_, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if(arg0 == btnGoiThem){
			startActivity(new Intent(Dieu_Khien_Activity.this, ThemTuActivity.class));
		}
		else if(arg0 == btnGoiTra){
			startActivity(new Intent(Dieu_Khien_Activity.this, MainActivity.class));
		}
	}

}
