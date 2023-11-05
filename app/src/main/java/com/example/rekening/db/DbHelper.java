package com.example.rekening.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.rekening.model.Rekening;

import java.util.ArrayList;
public class DbHelper extends SQLiteOpenHelper {
    public static String DATABASE_NAME = "dbrekening";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_RKNG = "rekenings";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_REKENING = "rekening";
    private static final String CREATE_TABLE_REKENINGS = "CREATE TABLE " + TABLE_RKNG + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME + " TEXT, " + KEY_REKENING + " TEXT );";

    public DbHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_REKENINGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int
            newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE_RKNG + "'");
        onCreate(db);
    }

    public long addUserDetail(String rekening, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_REKENING, rekening);
        long insert = db.insert(TABLE_RKNG, null, values);
        return insert;
    }

    @SuppressLint("Range")
    public ArrayList<Rekening> getAllUsers() {
        ArrayList<Rekening> userModelArrayList = new
                ArrayList<Rekening>();
        String selectQuery = "SELECT * FROM " + TABLE_RKNG;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                Rekening std = new Rekening();
                std.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                std.setName(c.getString(c.getColumnIndex(KEY_NAME)));
                std.setRekening(c.getString(c.getColumnIndex(KEY_REKENING)));
                userModelArrayList.add(std);
            } while (c.moveToNext());
        }
        return userModelArrayList;
    }

    public void deleteUser(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RKNG, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public int updateUser(int id, String rekening, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_REKENING, rekening);
        return db.update(TABLE_RKNG, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }
}
