package com.izadalab.kamus.ui.dictionary;

import com.dhytodev.mybasemvp.MvpView;
import com.izadalab.kamus.data.model.Dictionary;

import java.util.List;

/**
 * Created by izadalab on 08/02/18.
 */

public interface DictionaryView extends MvpView {
    void displayData(List<Dictionary> dictionaries);
}
