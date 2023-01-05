package com.example.tdav;

import java.util.List;

import android.R.layout;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TuAdapter extends BaseAdapter {
	private Context context;
	private int layout;
	private List<Tu> TuList;

	public TuAdapter(Context context, int layout, List<Tu> tuList) {
		super();
		this.context = context;
		this.layout = layout;
		TuList = tuList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return TuList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	private class ViewHolder {
		TextView txtTen, txtMoTa;
		ImageView imgHinh;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder;

		if (arg1 == null) {
			holder = new ViewHolder();
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			arg1 = inflater.inflate(layout, null);
			holder.txtTen = (TextView) arg1.findViewById(R.id.textViewTuCustom);
			holder.txtMoTa = (TextView) arg1
					.findViewById(R.id.textViewMotaCustum);
			holder.imgHinh = (ImageView) arg1
					.findViewById(R.id.imageViewHinhCustom);
			arg1.setTag(holder);
		} else {
			holder = (ViewHolder) arg1.getTag();
		}

		Tu tu = TuList.get(arg0);

		holder.txtTen.setText(tu.getTen());
		holder.txtMoTa.setText(tu.getMota());
		// chuyen byte[] --> bitmap
		byte[] hinhAnh = tu.getHinh();
		Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0,
				hinhAnh.length);
		holder.imgHinh.setImageBitmap(bitmap);
		return arg1;
	}

}
