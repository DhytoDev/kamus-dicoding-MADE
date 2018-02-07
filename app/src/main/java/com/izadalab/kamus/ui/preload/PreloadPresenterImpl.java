package com.izadalab.kamus.ui.preload;


import android.util.Log;

import com.dhytodev.mybasemvp.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by izadalab on 03/02/18.
 */

public class PreloadPresenterImpl extends BasePresenter<MainView, PreLoadInteractor> implements PreloadPresenter {

    public PreloadPresenterImpl(PreLoadInteractor mvpInteractor, CompositeDisposable compositeDisposable) {
        super(mvpInteractor, compositeDisposable);
    }

    @Override
    public void preloadDictionaries() {
        getCompositeDisposable().add(getInteractor().saveDictionaries()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        getMvpView().publishProgress(integer);
                    }

                    @Override
                    public void onError(Throwable e) {
                        getMvpView().onError(e.getLocalizedMessage());
                    }

                    @Override
                    public void onComplete() {
                        getMvpView().onPreLoadCompleted();
                    }
                }));

    }
}
