package com.dhytodev.mybasemvp;


/**
 * Created by izadalab on 9/6/17.
 */

public class BaseInteractor<T> implements MvpInteractor<T> {

    private T preferencesHelper;

    public BaseInteractor(T preferencesHelper) {
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public T getPreferencesHelper() {
        return preferencesHelper;
    }
}
