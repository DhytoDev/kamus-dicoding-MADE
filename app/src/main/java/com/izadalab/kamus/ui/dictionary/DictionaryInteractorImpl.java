package com.izadalab.kamus.ui.dictionary;

import android.content.Context;
import android.database.Cursor;

import com.dhytodev.mybasemvp.BaseInteractor;
import com.izadalab.kamus.data.db.DatabaseManager;
import com.izadalab.kamus.data.model.Dictionary;
import com.izadalab.kamus.data.prefs.PreferencesHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by izadalab on 08/02/18.
 */

public class DictionaryInteractorImpl extends BaseInteractor<PreferencesHelper> implements DictionaryInteractor {

    private Context context;

    public DictionaryInteractorImpl(PreferencesHelper preferencesHelper, Context context) {
        super(preferencesHelper);
        this.context = context;
    }

    @Override
    public Observable<List<Dictionary>> searchDictionaryByWord(String query, boolean isEnglish) {
        return Observable.create(e -> {
            List<Dictionary> dictionaries = new ArrayList<>();

            final Cursor cursor = DatabaseManager.getInstance(context).searchByWord(query, isEnglish);

            if (cursor.moveToFirst()) {
                do {
                    dictionaries.add(new Dictionary(cursor));
                } while (cursor.moveToNext());
            }
            cursor.close();

            e.onNext(dictionaries);
        });
    }

    @Override
    public Observable<List<Dictionary>> getAllData(boolean isEnglish) {
        return Observable.create(e -> {
            List<Dictionary> dictionaries = new ArrayList<>();

            final Cursor cursor = DatabaseManager.getInstance(context).selectAllData(isEnglish);

            if (cursor.moveToFirst()) {
                do {
                    dictionaries.add(new Dictionary(cursor));
                } while (cursor.moveToNext());
            }

            cursor.close();

            e.onNext(dictionaries);
        });
    }
}
