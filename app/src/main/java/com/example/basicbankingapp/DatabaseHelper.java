package com.example.basicbankingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(6299062990,'Aman',10000.00,'aman@gmail.com','XXXXXXXXXXXX1234','CBIN0004567')");
        db.execSQL("insert into user_table values(9905799057,'Pratyush',10000.00,'pratyush@gmail.com','XXXXXXXXXXXX2341','CBIN0004567')");
        db.execSQL("insert into user_table values(8432184321,'Tushar',10000.00,'tushar@gmail.com','XXXXXXXXXXXX3412','CBIN0004567')");
        db.execSQL("insert into user_table values(7725677256,'Kiran',10000.00,'kiran@gmail.com','XXXXXXXXXXXX4123','CBIN0004567')");
        db.execSQL("insert into user_table values(8102381023,'Roshan',10000.00,'Roshan@gmail.com','XXXXXXXXXXXX2345','CBIN0004567')");
        db.execSQL("insert into user_table values(6287562875,'Ashwani',10000.00,'ashwani@gmail.com','XXXXXXXXXXXX3452','CBIN0004567')");
        db.execSQL("insert into user_table values(9431594315,'Abhishek',10000.00,'abhishek@gmail.com','XXXXXXXXXXXX4523','CBIN0004567')");
        db.execSQL("insert into user_table values(8254182541,'Shirish',10000.00,'shirish@gmail.com','XXXXXXXXXXXX5234','CBIN0004567')");
        db.execSQL("insert into user_table values(6457364573,'Praful',10000.00,'praful@gmail.com','XXXXXXXXXXXX3456','CBIN0004567')");
        db.execSQL("insert into user_table values(8151281512,'Nihal',10000.00,'nihal@gmail.com','XXXXXXXXXXXX4563','CBIN0004567')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}