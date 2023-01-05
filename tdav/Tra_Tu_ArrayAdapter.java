package com.example.tdav;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Tra_Tu_ArrayAdapter extends ArrayAdapter<Tu>{

	
	Activity context;
	ArrayList<Tu> arr;
	private Tu tu;
	private TextView tvTenTu;
	private TextView tvMota;
	private ImageView ivHinh;
	private Iamge_loader imageLoader;
	
	public Tra_Tu_ArrayAdapter(Activity context , ArrayList<Tu> arr) {
		super(context, R.layout.dong_tu, arr);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.arr=arr;
		imageLoader = new Iamge_loader(this.context);
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater=this.context.getLayoutInflater();
		 View row=inflater.inflate(R.layout.dong_tu, null);
		 
		 
		 tu = arr.get(position);
		 if(tu !=null){
			
			 tvTenTu = (TextView) row.findViewById(R.id.textViewTuCustom);
			 tvMota = (TextView) row.findViewById(R.id.textViewMotaCustum);
			 ivHinh = (ImageView) row.findViewById(R.id.imageViewHinhCustom);
			 
			
			 tvTenTu.setText(tu.getTen());
			 tvMota.setText(tu.getMota()+"");
			 imageLoader.DisplayImage("C:/Users/GIANG NGUYEN/Pictures"+tu.getHinh(), ivHinh);
		
			 
		 }
		 return row;
	}
}
	
	

