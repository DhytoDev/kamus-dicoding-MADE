package com.izadalab.kamus.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by izadalab on 03/02/18.
 */

public class AppPreferencesHelper implements PreferencesHelper{

    private final SharedPreferences mPrefs ;
    private static final String KEY_FIRST_RUN = "first_run";
    private Context context ;

    public AppPreferencesHelper(Context context) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        this.context = context;
    }

    @Override
    public void setFirstRun(boolean isFirstRun) {
        mPrefs.edit().putBoolean(KEY_FIRST_RUN, isFirstRun).apply();
    }

    @Override
    public Boolean getFirstRun() {
        return mPrefs.getBoolean(KEY_FIRST_RUN, true);
    }
}
