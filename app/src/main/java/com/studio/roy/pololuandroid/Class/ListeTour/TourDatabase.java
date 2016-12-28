package com.studio.roy.pololuandroid.Class.ListeTour;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Royal on 11/12/2016.
 */

public class TourDatabase  extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Tour";
    public static final String CONTACTS_TABLE_NAME = "Tour";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NOMTOUR = "NomTour";
    public static final String CONTACTS_COLUMN_DateTour = "DateTour";
    public static final String CONTACTS_COLUMN_Alert = "EtatTour";


    public TourDatabase(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table "+CONTACTS_TABLE_NAME+" ("+CONTACTS_COLUMN_ID+" integer primary key, "+CONTACTS_COLUMN_NOMTOUR+" text,"+CONTACTS_COLUMN_DateTour+" text,"+CONTACTS_COLUMN_Alert+" text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertTour(String NomTour, String DateTour,String EtatTour) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACTS_COLUMN_NOMTOUR, NomTour);
        contentValues.put(CONTACTS_COLUMN_DateTour, DateTour);
        contentValues.put(CONTACTS_COLUMN_Alert, EtatTour);
        db.insert(CONTACTS_TABLE_NAME, null, contentValues);
        return true;
    }

    public  int getidTour(String name) {
      int id = 0;


        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+CONTACTS_TABLE_NAME + " where "+CONTACTS_COLUMN_NOMTOUR + "= "+name + "", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            id = res.getInt(res.getColumnIndex(CONTACTS_COLUMN_ID));
            res.moveToNext();

        }
        return id;
    }

    public  ArrayList<Tour> getAllTour() {

        ArrayList<Tour> arraylist = new ArrayList<Tour>();
        SQLiteDatabase sql=this.getReadableDatabase();

        String query = "SELECT * FROM "+CONTACTS_TABLE_NAME+ "";

        Cursor c = sql.rawQuery(query, null);
        int i = 0;
        while(c.moveToNext()) {
            Tour tour = new Tour();

            tour.setId(c.getString(0));
            tour.setNameTour(c.getString(1));
            tour.setDateTour(c.getString(2));
            tour.setEtatTour(c.getString(3));

            arraylist.add(tour);
            i++;
        }
        c.close();
        return  arraylist;
    }

    public  void deleteTable(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+CONTACTS_TABLE_NAME+"");
    }
}
