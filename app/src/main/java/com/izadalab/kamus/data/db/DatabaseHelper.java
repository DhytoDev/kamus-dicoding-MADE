package com.izadalab.kamus.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.izadalab.kamus.data.db.DatabaseContract.DictionaryColumns;

/**
 * Created by izadalab on 01/02/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "dictionary";
    private static final int DB_VERSION = 1;

    private static final String SQL_CREATE_TABLE_ENG_INA = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_ENG_INA,
            DictionaryColumns._ID,
            DictionaryColumns.COLUMN_WORD,
            DictionaryColumns.COLUMN_MEAN);

    private static final String SQL_CREATE_TABLE_INA_ENG = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s TEXT NOT NULL," +
                    " %s TEXT NOT NULL)",
            DatabaseContract.TABLE_INA_ENG,
            DictionaryColumns._ID,
            DictionaryColumns.COLUMN_WORD,
            DictionaryColumns.COLUMN_MEAN);


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_ENG_INA);
        db.execSQL(SQL_CREATE_TABLE_INA_ENG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_ENG_INA);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContract.TABLE_INA_ENG);
        onCreate(db);
    }
}
