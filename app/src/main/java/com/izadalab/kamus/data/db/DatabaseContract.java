package com.izadalab.kamus.data.db;

import android.provider.BaseColumns;

/**
 * Created by izadalab on 01/02/18.
 */

public class DatabaseContract {
    public static final String TABLE_ENG_INA = "table_eng_ina";
    public static final String TABLE_INA_ENG = "table_ina_eng";

    public static final class DictionaryColumns implements BaseColumns {
        public static final String COLUMN_WORD = "word";
        public static final String COLUMN_MEAN = "mean";
    }
}
