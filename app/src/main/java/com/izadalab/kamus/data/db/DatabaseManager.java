package com.izadalab.kamus.data.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.izadalab.kamus.data.model.Dictionary;

import java.util.List;

import static com.izadalab.kamus.data.db.DatabaseContract.*;
import static com.izadalab.kamus.data.db.DatabaseContract.DictionaryColumns.*;

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

    public void insertTransaction(List<Dictionary> dictionaries, boolean isEnglish) {
        String DB_TABLE_NAME = isEnglish ? TABLE_ENG_INA : TABLE_INA_ENG;

        String sql = String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)",
                DB_TABLE_NAME,
                COLUMN_WORD,
                COLUMN_MEAN);

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

    public Cursor selectByWord(String query, boolean isEnglish) {
        String DB_TABLE_NAME = isEnglish ? TABLE_ENG_INA : TABLE_INA_ENG;
        return mDb.rawQuery("SELECT * FROM " + DB_TABLE_NAME +
                " WHERE " + COLUMN_WORD + " LIKE '%" + query.trim() + "%'", null);
    }

    public Cursor selectAllData(boolean isEnglish) {
        String DB_TABLE_NAME = isEnglish ? TABLE_ENG_INA : TABLE_INA_ENG;
        return mDb.rawQuery(String.format("SELECT * FROM %s ORDER BY %s ASC", DB_TABLE_NAME, COLUMN_WORD),
                null);
    }

}
