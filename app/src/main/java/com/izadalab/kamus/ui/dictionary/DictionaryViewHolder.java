package com.izadalab.kamus.ui.dictionary;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.izadalab.kamus.R;
import com.izadalab.kamus.data.model.Dictionary;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by izadalab on 08/02/18.
 */

public class DictionaryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_word)
    TextView tvWord ;
    @BindView((R.id.tv_mean))
    TextView tvMean ;

    public DictionaryViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Dictionary dictionary) {
        tvWord.setText(dictionary.getWord());
        tvMean.setText(dictionary.getMean());
        tvMean.setMaxLines(2);
    }
}
