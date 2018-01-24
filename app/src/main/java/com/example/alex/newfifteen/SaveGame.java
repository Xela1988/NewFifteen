package com.example.alex.newfifteen;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;

/**
 * Created by Alex on 02/11/2017.
 */
public class SaveGame extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "newFifteeSave.db";
    public static final String TABLE_NAME = "listFifteen_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "BUTTON";

    public SaveGame(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
String createTable = "CREATE_TABLE" + TABLE_NAME + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "+ "ITEM1 TEXT";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS" + TABLE_NAME);
    }
    public boolean salvaGame (String item1) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, item1);

        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor caricaGame(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);
        return data;
    }
}
