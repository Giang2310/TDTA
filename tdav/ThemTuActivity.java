package com.example.tdav;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.http.util.ByteArrayBuffer;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class ThemTuActivity extends Activity {

	Button btnAdd,btnCancel;
	EditText edtTen,edtMota;
	ImageButton ibtnCamera,ibtnFolder;
	ImageView imgHinh;
	
	//int REQUEST_CODE_CAMERA = 123;
	int REQUEST_CODE_FOLDER = 456;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_them_tu);
		
		AnhXa();
		/*ibtnCamera.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_CODE_CAMERA);

			}
		});*/
		
		btnAdd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// Chuyển data image view thành byte[]
				BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh.getDrawable();
				Bitmap bitmap = bitmapDrawable.getBitmap();
				ByteArrayOutputStream byteArray = new ByteArrayOutputStream(); 	
				bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
				byte[] hinhAnh = byteArray.toByteArray();
				
				MainActivity.database.INSERT_TU(
						edtTen.getText().toString().trim(),
						edtMota.getText().toString().trim(),
						hinhAnh
						
						);
				Toast.makeText(ThemTuActivity.this,"Đã thêm",Toast.LENGTH_SHORT).show();
				startActivity(new Intent(ThemTuActivity.this,MainActivity.class));
			}
		});
		ibtnFolder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_PICK);
				intent.setType("image/*");
                startActivityForResult(intent,REQUEST_CODE_FOLDER);

			}
		});
	}
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null){
			Uri uri = data.getData();
			try {
				InputStream inputStream = getContentResolver().openInputStream(uri);
				Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
				imgHinh.setImageBitmap(bitmap);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}


	private void AnhXa() {
		// TODO Auto-generated method stub
		btnAdd = (Button)findViewById(R.id.buttonThemTu);
		btnCancel = (Button)findViewById(R.id.buttonHuy);
		edtTen = (EditText)findViewById(R.id.editTextTenTu);
		edtMota = (EditText)findViewById(R.id.editTextMoTa);
		imgHinh = (ImageView)findViewById(R.id.imageViewLoadImage);
		ibtnCamera = (ImageButton)findViewById(R.id.imageButtonCamera);
		ibtnFolder = (ImageButton)findViewById(R.id.imageButtonUpFile);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.them_tu, menu);
		return true;
	}

}
