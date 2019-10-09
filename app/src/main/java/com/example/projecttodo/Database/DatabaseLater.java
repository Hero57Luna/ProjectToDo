package com.example.projecttodo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;



import java.util.ArrayList;

public class DatabaseLater extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "project";
    public static final String DATABASE_TABLE_LATER = "later";
    public static final String DATABASE_COLUMN_LATER_ID = "id";
    public static final String DATABASE_COLUMN_LATER_TASK = "laterTask";
    public static final String DATABASE_TABLE_IMPORTANT = "important";
    public static final String DATABASE_COLUMN_IMPORTANT_ID = "id";
    public static final String DATABASE_COLUMN_IMPORTANT_TASK = "importantTask";

    public static final String CREATE_TABLE_IMPORTANT = "CREATE TABLE "
            + DATABASE_TABLE_IMPORTANT + "("
            + DATABASE_COLUMN_IMPORTANT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DATABASE_COLUMN_IMPORTANT_TASK + " TEXT NOT NULL)";

    private static final String CREATE_TABLE_LATER = "CREATE TABLE "
            + DATABASE_TABLE_LATER + "("
            + DATABASE_COLUMN_LATER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + DATABASE_COLUMN_LATER_TASK + " TEXT NOT NULL)";

    public DatabaseLater(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_IMPORTANT);
        db.execSQL(CREATE_TABLE_LATER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String queryLater = "DROP TABLE IF EXISTS " + DATABASE_TABLE_LATER;
        String queryImportant = "DROP TABLE IF EXISTS " + DATABASE_TABLE_IMPORTANT;
        db.execSQL(queryLater);
        db.execSQL(queryImportant);
        onCreate(db);
    }

    public void insertDataLater(String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATABASE_COLUMN_LATER_TASK, data);
        db.insertWithOnConflict(DATABASE_TABLE_LATER, null, values, SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void insertDataImportant(String data) {
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(DATABASE_COLUMN_IMPORTANT_TASK, data);
        db.insertWithOnConflict(DATABASE_TABLE_IMPORTANT, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public void deleteDataLater(String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DATABASE_TABLE_LATER, DATABASE_COLUMN_LATER_TASK + " = ? ", new String[]{data});
        db.close();
    }

    public void deleteDataImportant(String data) {
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(DATABASE_TABLE_IMPORTANT, DATABASE_COLUMN_IMPORTANT_TASK + " = ? ", new String[]{data});
        db.close();
    }

    public ArrayList<String> getDataLater() {
        ArrayList<String> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE_LATER, new String[]{DATABASE_COLUMN_LATER_TASK}, null, null, null, null, null);
        while(cursor.moveToNext()) {
            int i = cursor.getColumnIndex(DATABASE_COLUMN_LATER_TASK);
            dataList.add(cursor.getString(i));
        }
        cursor.close();
        db.close();
        return dataList;
    }

    public ArrayList<String> getDataImportant() {
        ArrayList<String> dataList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DATABASE_TABLE_IMPORTANT, new String[]{DATABASE_COLUMN_IMPORTANT_TASK}, null, null, null, null, null);
        while(cursor.moveToNext()) {
            int i = cursor.getColumnIndex(DATABASE_COLUMN_IMPORTANT_TASK);
            dataList.add(cursor.getString(i));
        }
        cursor.close();
        db.close();
        return dataList;
    }

    public void updateData(String data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
//        values.put(DATABASE_COLUMN1, data);
//        db.update(DATABASE_TABLE, values, DATABASE_COLUMN1 + " = ?", new String[]{data});
    }
}
