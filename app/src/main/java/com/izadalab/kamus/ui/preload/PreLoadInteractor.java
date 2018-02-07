package com.izadalab.kamus.ui.preload;

import com.dhytodev.mybasemvp.MvpInteractor;
import com.izadalab.kamus.data.model.Dictionary;
import com.izadalab.kamus.data.prefs.PreferencesHelper;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;


/**
 * Created by izadalab on 03/02/18.
 */

public interface PreLoadInteractor extends MvpInteractor<PreferencesHelper> {

//    List<Dictionary> preLoadRawEngIna();
//    List<Dictionary> preLoadRawInaEng();

//    Observable<List<Dictionary>> preLoadRawEngInaDic();
//    Observable<List<Dictionary>> preLoadRawInaEngDic();
//    Completable saveEngInaDictionary();
    Observable<Integer> saveDictionaries();

}
