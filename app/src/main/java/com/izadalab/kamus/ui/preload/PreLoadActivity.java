package com.izadalab.kamus.ui.preload;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ProgressBar;

import com.dhytodev.mybasemvp.BaseActivity;
import com.izadalab.kamus.R;
import com.izadalab.kamus.data.prefs.AppPreferencesHelper;
import com.izadalab.kamus.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by izadalab on 08/02/18.
 */

public class PreLoadActivity extends BaseActivity implements MainView {

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private PreloadPresenter presenter ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preload);
        setUnbinder(ButterKnife.bind(this));
        setUp();
    }

    @Override
    protected void setUp() {
        PreLoadInteractor interactor = new PreLoadInteractorImpl(new AppPreferencesHelper(this), this);
        presenter = new PreloadPresenterImpl(interactor, new CompositeDisposable());
        presenter.onAttach(this);
        presenter.preloadDictionaries();
    }

    @Override
    public void publishProgress(Integer progressValue) {
        progressBar.setProgress(progressValue);
    }

    @Override
    public void onPreLoadCompleted() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
