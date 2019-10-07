package com.example.projecttodo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.projecttodo.Fragments.LaterFragment;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "project";
    public static final String DATABASE_TABLE = "task";
    public static final String DATABASE_COLUMN = "id";
    public static final String DATABASE_COLUMN1 = "name";

    public DBHelper(@Nullable LaterFragment context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + DATABASE_TABLE + "("
                + DATABASE_COLUMN + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + DATABASE_COLUMN1 + "TEXT)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + DATABASE_TABLE;
        db.execSQL(query);
        onCreate(db);
    }

    public void insertData(String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATABASE_COLUMN1, data);
        db.insertWithOnConflict(DATABASE_TABLE, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void deleteData(String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE, DATABASE_COLUMN1 + " = ? ", new String[]{data});
        db.close();
    }

    public ArrayList<String> getData() {
        ArrayList<String> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE, new String[]{DATABASE_COLUMN1}, null, null, null, null, null);
        while(cursor.moveToNext()) {
            int i = cursor.getColumnIndex(DATABASE_COLUMN1);
            dataList.add(cursor.getString(i));
        }
        cursor.close();
        db.close();
        return dataList;
    }
}
