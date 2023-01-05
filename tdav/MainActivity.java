package com.example.tdav;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText edtTraTu;
	Button btnTraTu;
	ListView lvTu;
	private static String url = "C:/Users/GIANG NGUYEN/Pictures";
	ArrayList<Tu> arrayTu;
	TuAdapter adapter;
	public Tra_Tu_ArrayAdapter arrayAdapterTu;
	public static Database database;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        edtTraTu = (EditText)findViewById(R.id.editTextTraTu);
        
        btnTraTu=(Button)findViewById(R.id.buttonTraTu);
        
        lvTu = (ListView)findViewById(R.id.listViewTu);
        arrayTu = new ArrayList<Tu>();
        
        adapter  = new TuAdapter(this, R.layout.dong_tu, arrayTu);
        lvTu.setAdapter(adapter);
        
       database = new Database(this, "TuDien.sqlite", null, 1);
        
        database.QueryData("CREATE TABLE IF NOT EXISTS Tu (Id INTEGER PRIMARY KEY AUTOINCREMENT, Ten VARCHAR(150), MoTa VARCHAR(250),HinhAnh BLOB)");
        
        //get data
        Cursor cursor = database.GetData("SELECT * FROM Tu");
        while(cursor.moveToNext()){
        	arrayTu.add(new Tu(
        			cursor.getInt(0),
        			cursor.getString(1),
        			cursor.getString(2),
        			cursor.getBlob(3)
        			) {
			});
        			
        			
        	
        	adapter.notifyDataSetChanged();

        			
        			
        }
        btnTraTu.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View view) {
				// TODO Auto-generated method stub
				if (view == btnTraTu) {
					new JSONParserAsyncTask().execute();
				}
			}
		});
	}
	
			
    

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	private class JSONParserAsyncTask extends
	AsyncTask<String, String, JSONObject> {
private ProgressDialog pDialog;

@Override
protected void onPreExecute() {
	super.onPreExecute();

	/*
	 * pDialog = new ProgressDialog(XemSach_GridView.this);
	 * pDialog.setMessage("Getting Data ...");
	 * pDialog.setIndeterminate(false); pDialog.setCancelable(true);
	 * pDialog.show();
	 */

}

@Override
protected JSONObject doInBackground(String... args) {
	JSONParser jParser = new JSONParser();
	List<NameValuePair> params = new ArrayList<NameValuePair>();
	params.add(new BasicNameValuePair("tag", "tratu"));
	params.add(new BasicNameValuePair("ten", edtTraTu.getText()
			+ ""));
	// Getting JSON from URL
	JSONObject json = jParser.getJSONFromUrl(url, params);
	return json;
}

@Override
protected void onPostExecute(JSONObject json) {
	// pDialog.dismiss();
	try {
		if (json.getString("success") != null) {
			// loginErrorMsg.setText("");
			// String res = json.getString(KEY_SUCCESS);
			// if(Integer.parseInt(res) == 1){
			if (json.getString("success").equals("1")) {
				int soLuong = json.getInt("soluong");

				JSONObject json_tu;
				Tu tu;
				arrayTu.clear();
				for (int i = 0; i < soLuong; i++) {

					json_tu = json.getJSONObject("tu" + i);

					arrayTu.add(new Tu(
							json_tu.getInt("id"),
							json_tu.getString("ten"),
							json_tu.getString("Mota"),
							((Cursor) json_tu).getBlob(3)
		        			) {
					});

				}
				arrayAdapterTu = new Tra_Tu_ArrayAdapter(
						MainActivity.this, arrayTu);
				lvTu.setAdapter(arrayAdapterTu);
				// arrayAdapterSach.notifyDataSetChanged();

				Toast.makeText(getApplicationContext(),
						"Đã tải xong danh mục sách", Toast.LENGTH_LONG)
						.show();
			}
		} else {

		}

	} catch (JSONException e) {
		e.printStackTrace();
	}

}
}

}
