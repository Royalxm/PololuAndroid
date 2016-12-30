package com.studio.roy.pololuandroid.Class.Users;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Royal on 30/11/2016.
 */

public class DatabaseUser extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Login";
    public static final String CONTACTS_TABLE_NAME = "Login";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_PASSWORD = "password";

    public DatabaseUser(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table "+CONTACTS_TABLE_NAME+" ("+CONTACTS_COLUMN_ID+" integer primary key, "+CONTACTS_COLUMN_EMAIL+" text,"+CONTACTS_COLUMN_PASSWORD+" text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertUser(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        db.insert("Login", null, contentValues);
        return true;
    }

    public  void deletetable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM Login");

    }

    public boolean updateUser (Integer id, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        db.update("Login", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }



    public User getAllUser() {
        User user = new User();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+CONTACTS_TABLE_NAME, null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            user.setEmail(res.getString(res.getColumnIndex(CONTACTS_COLUMN_EMAIL)));
            user.setPassword(res.getString(res.getColumnIndex(CONTACTS_COLUMN_PASSWORD)));
            res.moveToNext();
        }
        return user;
    }

    public int getUsersCount() {
        String countQuery = "SELECT  * FROM " + CONTACTS_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

}
