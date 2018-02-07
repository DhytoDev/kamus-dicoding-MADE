package com.izadalab.kamus.ui.preload;

import com.dhytodev.mybasemvp.MvpView;

/**
 * Created by izadalab on 03/02/18.
 */

public interface MainView extends MvpView {
    void publishProgress(Integer progressValue);
    void onPreLoadCompleted();
}
