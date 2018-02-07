package com.izadalab.kamus.ui.preload;

import android.content.Context;

import com.dhytodev.mybasemvp.BaseInteractor;
import com.izadalab.kamus.R;
import com.izadalab.kamus.data.db.DatabaseManager;
import com.izadalab.kamus.data.model.Dictionary;
import com.izadalab.kamus.data.prefs.PreferencesHelper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by izadalab on 03/02/18.
 */

public class PreLoadInteractorImpl extends BaseInteractor<PreferencesHelper> implements PreLoadInteractor {

    private Context context;
    private double progress ;
    private double maxProgress = 100;

    public PreLoadInteractorImpl(PreferencesHelper preferencesHelper, Context context) {
        super(preferencesHelper);
        this.context = context;
    }

    private ArrayList<Dictionary> preLoadRaw(int rawResource) {
        ArrayList<Dictionary> dictionaries = new ArrayList<>();
        String line = null;
        BufferedReader reader;

        try {
            InputStream rawDict = context.getResources().openRawResource(rawResource);

            reader = new BufferedReader(new InputStreamReader(rawDict));

            do {
                line = reader.readLine();
                String[] splitStr = line.split("\t");

                Dictionary dictionary;
                dictionary = new Dictionary(splitStr[0], splitStr[1]);
                dictionaries.add(dictionary);
            } while (line != null);
        } catch (Exception err) {
            err.printStackTrace();
        }
        return dictionaries;
    }

    @Override
    public Observable<Integer> saveDictionaries() {
        return Observable.create(e -> {
            Boolean firstRun = getPreferencesHelper().getFirstRun();

            if (firstRun) {
                ArrayList<Dictionary> engInaDictionaries = preLoadRaw(R.raw.english_indonesia);
                ArrayList<Dictionary> inaEngDictionaries = preLoadRaw(R.raw.indonesia_english);

                e.onNext((int) progress);

                double progressMaxInsert = 100.0;
                double progressDiff = (progressMaxInsert - progress) / (engInaDictionaries.size()
                        + inaEngDictionaries.size() * 1000);

                DatabaseManager.getInstance(context).insertTransaction(engInaDictionaries, true);
                progress += progressDiff;
                e.onNext((int) progress);

                DatabaseManager.getInstance(context).insertTransaction(inaEngDictionaries, false);
                progress += progressDiff;
                e.onNext((int) progress);

                DatabaseManager.getInstance(context).close();
                getPreferencesHelper().setFirstRun(false);

                e.onNext((int) maxProgress);
                e.onComplete();
            } else {
                try {
                    synchronized (this) {
                        this.wait(1000);
                        e.onNext(50);

                        this.wait(300);
                        e.onNext((int) maxProgress);
                        e.onComplete();
                    }
                } catch (Exception err) {
                    e.onError(err);
                }
            }
        });
    }
}
