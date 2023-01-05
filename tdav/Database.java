package com.example.tdav;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper{

	

	public Database(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
	}
	
	public void QueryData(String sql){
		SQLiteDatabase database = getWritableDatabase();
		database.execSQL(sql);
	}
	
	public void INSERT_TU(String ten, String mota,byte[] hinh){
		SQLiteDatabase database = getWritableDatabase();
		String sql = "INSERT INTO Tu VALUES(null,?,?,?)";
		SQLiteStatement statement = database.compileStatement(sql);
		statement.clearBindings();
		
		statement.bindString(1,ten);
		statement.bindString(2,mota);
		statement.bindBlob(3,hinh);
		
		statement.executeInsert();
	}
	public Cursor GetData(String sql){
		SQLiteDatabase database = getReadableDatabase();
		return database.rawQuery(sql, null);
	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

}
