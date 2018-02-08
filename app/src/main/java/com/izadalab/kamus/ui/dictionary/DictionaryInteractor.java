package com.izadalab.kamus.ui.dictionary;

import com.dhytodev.mybasemvp.MvpInteractor;
import com.izadalab.kamus.data.model.Dictionary;
import com.izadalab.kamus.data.prefs.PreferencesHelper;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by izadalab on 08/02/18.
 */

public interface DictionaryInteractor extends MvpInteractor<PreferencesHelper> {
    Observable<List<Dictionary>> searchDictionaryByWord(String query, boolean isEnglish);
    Observable<List<Dictionary>> getAllData(boolean isEnglish);
}
