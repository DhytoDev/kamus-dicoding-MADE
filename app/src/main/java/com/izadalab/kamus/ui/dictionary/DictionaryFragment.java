package com.izadalab.kamus.ui.dictionary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.dhytodev.mybasemvp.BaseFragment;
import com.dhytodev.mybasemvp.BaseRvAdapter;
import com.dhytodev.mybasemvp.listener.RecyclerViewItemClickListener;
import com.izadalab.kamus.R;
import com.izadalab.kamus.data.model.Dictionary;
import com.izadalab.kamus.data.prefs.AppPreferencesHelper;
import com.izadalab.kamus.ui.detail.DictionaryDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

import static android.content.ContentValues.TAG;

/**
 * Created by izadalab on 08/02/18.
 */

public class DictionaryFragment extends BaseFragment implements DictionaryView, RecyclerViewItemClickListener {

    protected DictionaryPresenter presenter;
    @BindView(R.id.rv_dictionary)
    RecyclerView recyclerView;
    @BindView(R.id.et_search_bar)
    protected EditText searchBar;

    private List<Dictionary> dictionaries = new ArrayList<>();
    private BaseRvAdapter<Dictionary, DictionaryViewHolder> adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        DictionaryInteractor interactor = new DictionaryInteractorImpl(new AppPreferencesHelper(getContext()), getContext());
        presenter = new DictionaryPresenterImpl(interactor, new CompositeDisposable());
        presenter.onAttach(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_dictionary, container, false);
        setUnbinder(ButterKnife.bind(this, view));

        return view;
    }

    @Override
    protected void setUp(View view) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new BaseRvAdapter<Dictionary, DictionaryViewHolder>
                (R.layout.dictionary_item, DictionaryViewHolder.class, dictionaries, this) {
            @Override
            protected void bindView(DictionaryViewHolder holder, Dictionary dictionary, int position) {
                holder.bind(dictionary);
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void displayData(List<Dictionary> dictionaries) {
        this.dictionaries.clear();
        this.dictionaries.addAll(dictionaries);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), DictionaryDetailActivity.class);
        intent.putExtra(DictionaryDetailActivity.DICTIONARY_INTENT, dictionaries.get(position));
        startActivity(intent);
    }
}
