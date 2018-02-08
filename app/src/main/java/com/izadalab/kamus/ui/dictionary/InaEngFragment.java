package com.izadalab.kamus.ui.dictionary;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.izadalab.kamus.R;

import butterknife.OnClick;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class InaEngFragment extends DictionaryFragment {


    public InaEngFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String query = savedInstanceState != null ? savedInstanceState.getString("query") : null;

        presenter.fetchAllData(false);

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                presenter.searchDictionaryByWord(charSequence.toString(),false);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @OnClick(R.id.btn_clear)
    void onClearBtnClicked() {
        searchBar.setText("");
        presenter.fetchAllData(true);
    }
}
