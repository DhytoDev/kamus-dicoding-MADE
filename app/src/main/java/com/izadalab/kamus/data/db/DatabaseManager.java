package com.izadalab.kamus.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.izadalab.kamus.data.model.Dictionary;

import java.util.List;

import static com.izadalab.kamus.data.db.DatabaseContract.*;

/**
 * Created by izadalab on 05/02/18.
 */

public class DatabaseManager {
    private static DatabaseManager sInstance;
    private SQLiteDatabase mDb;
    private DatabaseHelper mDbHelper;

    public DatabaseManager(Context context) {
        mDbHelper = new DatabaseHelper(context);
        mDb = mDbHelper.getWritableDatabase();
    }

    public static synchronized DatabaseManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseManager(context.getApplicationContext());
        }

        return sInstance;
    }

    public void beginTransaction() {
        mDb.beginTransaction();
    }

    public void endTransaction() {
        mDb.endTransaction();
    }

    public void setTransactionSuccess() {
        mDb.setTransactionSuccessful();
    }

    public void close() {
        mDb.close();
    }

    public void insertTransaction(List<Dictionary> dictionaries, boolean isEnglish) {
        String DB_TABLE_NAME = isEnglish ? TABLE_ENG_INA : TABLE_INA_ENG;

        String sql = String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)",
                DB_TABLE_NAME,
                DictionaryColumns.COLUMN_WORD,
                DictionaryColumns.COLUMN_MEAN);

        mDb.beginTransaction();

        SQLiteStatement statement = mDb.compileStatement(sql);

        for (Dictionary dictionary : dictionaries) {
            statement.bindString(1, dictionary.getWord());
            statement.bindString(2, dictionary.getMean());
            statement.execute();
            statement.clearBindings();
        }

        mDb.setTransactionSuccessful();
        mDb.endTransaction();
    }

    public void insertEngInaDictionary(Dictionary dictionary) {
        String sql = String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)",
                TABLE_ENG_INA,
                DictionaryColumns.COLUMN_WORD,
                DictionaryColumns.COLUMN_MEAN);
        SQLiteStatement statement = mDb.compileStatement(sql);
        statement.bindString(1, dictionary.getWord());
        statement.bindString(2, dictionary.getMean());
        statement.execute();
        statement.clearBindings();
    }
}
