package com.studio.roy.pololuandroid.Class.ListeTour;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Royal on 16/12/2016.
 */

public class PhotoDatabase  extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "Photo";
    public static final String CONTACTS_TABLE_NAME = "Tour";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_IdTour = "IdTour";
    public static final String CONTACTS_COLUMN_PhotoName = "PhotoName";
    public static final String CONTACTS_COLUMN_PhotoData = "PhotoData";


    public PhotoDatabase(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table "+CONTACTS_TABLE_NAME+" ("+CONTACTS_COLUMN_ID+" integer primary key, "+CONTACTS_COLUMN_IdTour+" text,"+CONTACTS_COLUMN_PhotoName+" text,"+CONTACTS_COLUMN_PhotoData+" text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertPhoto(String Id, String PhotoName, byte[]  PhotoData) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACTS_COLUMN_IdTour, Id);
        contentValues.put(CONTACTS_COLUMN_PhotoName, PhotoName);
        contentValues.put(CONTACTS_COLUMN_PhotoData, PhotoData);
        db.insert(CONTACTS_TABLE_NAME, null, contentValues);
        return true;
    }

    public ArrayList<Photo> getAllPhoto() {
        ArrayList<Photo> photos = new ArrayList<Photo>();


        SQLiteDatabase sql=this.getReadableDatabase();
        String query =  "select * from "+CONTACTS_TABLE_NAME;

        Cursor c = sql.rawQuery(query, null);
        int i = 0;
        while(c.moveToNext()) {
            Photo photo = new Photo();

            photo.setIdPhoto(c.getInt(1));
            photo.setNamePhoto(c.getString(2));
            photo.setDataPhoto(c.getBlob(3));

            photos.add(photo);
            i++;
        }
        c.close();

        return photos;
    }

    public ArrayList<Photo> getAllPhotoiD(String id) {

        ArrayList<Photo> photos = new ArrayList<Photo>();


        SQLiteDatabase sql=this.getReadableDatabase();
        String query =  "select * from "+CONTACTS_TABLE_NAME+" WHERE "+CONTACTS_COLUMN_IdTour + " = " +id;

        Cursor c = sql.rawQuery(query, null);
        int i = 0;
        while(c.moveToNext()) {
            Photo photo = new Photo();

            photo.setIdPhoto(c.getInt(1));
            photo.setNamePhoto(c.getString(2));
            photo.setDataPhoto(c.getBlob(3));

            photos.add(photo);
            i++;
        }
        c.close();

        return photos;
    }

    public  void deleteTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+CONTACTS_TABLE_NAME+"");
    }
}
