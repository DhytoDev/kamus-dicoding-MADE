package com.izadalab.kamus.ui.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.dhytodev.mybasemvp.BaseActivity;
import com.izadalab.kamus.R;
import com.izadalab.kamus.data.model.Dictionary;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by izadalab on 08/02/18.
 */

public class DictionaryDetailActivity extends BaseActivity {

    public static final String DICTIONARY_INTENT = "dictionary.intent";

    @BindView(R.id.tv_word)
    TextView tvWord;
    @BindView(R.id.tv_mean)
    TextView tvMean;
    @BindView(R.id.tv_translate)
    TextView tvTranslate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dictionary_item);
        setUnbinder(ButterKnife.bind(this));
        setUp();
    }

    @Override
    protected void setUp() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Dictionary dictionary = getIntent().getParcelableExtra(DICTIONARY_INTENT);
        showDetails(dictionary);
    }

    private void showDetails(Dictionary dictionary) {
        tvWord.setText(dictionary.getWord());
        tvMean.setText(dictionary.getMean());
        tvTranslate.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
