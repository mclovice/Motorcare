package com.example.motorcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static String DATABASE = "consult.db";
    public static String TABLE ="mytable";
    public static String MESSAGE_BODY ="body";
    public static String ADDRESS ="address";
    public static String PERIOD = "period";
    public  static String ID = "id";
    String myquery;
    public DatabaseHelper(Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creating a database query
        myquery = "CREATE TABLE "+ TABLE + "("+ ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "+ MESSAGE_BODY + " TEXT, "+
                ADDRESS + " TEXT, " + PERIOD + " TEXT );";

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE + " ; ");

    }
public void insertData(String message, String address,String period){
        System.out.print("Hello "+ myquery);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put(ID,id);
        contentValues.put(MESSAGE_BODY,message);
        contentValues.put(ADDRESS,address);
        contentValues.put(PERIOD,period);
        db.insert(TABLE,null,contentValues);
}
public List<ConsultHelper> getdata(){
        List<ConsultHelper> data = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE + " ;",null);
        StringBuffer stringBuffer = new StringBuffer();
        ConsultHelper dataModel = null;
        while (cursor.moveToNext()){
            dataModel = new ConsultHelper();
            String body = cursor.getString(cursor.getColumnIndexOrThrow("body"));
            String address = cursor.getString(cursor.getColumnIndexOrThrow("address"));
            String period = cursor.getString(cursor.getColumnIndexOrThrow("period"));
            dataModel.setMessageBody(body);
            dataModel.setAddress(address);
            dataModel.setPeriod(period);
            //Adding a new entry to  a list
            data.add(dataModel);
        }
        //For debugging
        for(ConsultHelper cm : data){
            Log.i("Hello", "" + cm.getMessageBody());
        }
        return data;
}

}
