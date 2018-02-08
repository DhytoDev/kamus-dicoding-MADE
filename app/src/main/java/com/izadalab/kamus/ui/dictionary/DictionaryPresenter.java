package com.izadalab.kamus.ui.dictionary;

import com.dhytodev.mybasemvp.MvpPresenter;

/**
 * Created by izadalab on 08/02/18.
 */

public interface DictionaryPresenter extends MvpPresenter<DictionaryView, DictionaryInteractor> {
    void searchDictionaryByWord(String query, boolean isEnglish);
    void fetchAllData(boolean isEnglish);
}
