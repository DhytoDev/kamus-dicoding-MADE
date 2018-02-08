package com.izadalab.kamus.ui.dictionary;

import android.util.Log;

import com.dhytodev.mybasemvp.BasePresenter;
import com.izadalab.kamus.data.db.DatabaseManager;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static android.content.ContentValues.TAG;

/**
 * Created by izadalab on 08/02/18.
 */

public class DictionaryPresenterImpl extends BasePresenter<DictionaryView, DictionaryInteractor> implements DictionaryPresenter {

    public DictionaryPresenterImpl(DictionaryInteractor mvpInteractor, CompositeDisposable compositeDisposable) {
        super(mvpInteractor, compositeDisposable);
    }

    @Override
    public void searchDictionaryByWord(String query, boolean isEnglish) {
        getCompositeDisposable().add(getInteractor().searchDictionaryByWord(query, isEnglish)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dictionaries ->
                        getMvpView().displayData(dictionaries)
                ));
    }

    @Override
    public void fetchAllData(boolean isEnglish) {
        getCompositeDisposable().add(getInteractor().getAllData(isEnglish)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(dictionaries -> getMvpView().displayData(dictionaries)));
    }
}
