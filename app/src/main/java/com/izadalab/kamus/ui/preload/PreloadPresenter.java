package com.izadalab.kamus.ui.preload;

import com.dhytodev.mybasemvp.MvpPresenter;

/**
 * Created by izadalab on 03/02/18.
 */

public interface PreloadPresenter extends MvpPresenter<MainView, PreLoadInteractor> {

    void preloadDictionaries();
}
