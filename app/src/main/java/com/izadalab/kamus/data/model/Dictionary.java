package com.izadalab.kamus.data.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.izadalab.kamus.data.db.DatabaseContract;

import static com.izadalab.kamus.data.db.DatabaseContract.DictionaryColumns.*;

/**
 * Created by izadalab on 05/02/18.
 */

public class Dictionary implements Parcelable {
    private int id ;
    private String word ;
    private String mean ;

    public Dictionary(String word, String mean) {
        this.word = word;
        this.mean = mean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public Dictionary(Cursor cursor) {
        this.id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
        this.word = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_WORD));
        this.mean = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MEAN));
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.word);
        dest.writeString(this.mean);
    }

    protected Dictionary(Parcel in) {
        this.id = in.readInt();
        this.word = in.readString();
        this.mean = in.readString();
    }

    public static final Parcelable.Creator<Dictionary> CREATOR = new Parcelable.Creator<Dictionary>() {
        @Override
        public Dictionary createFromParcel(Parcel source) {
            return new Dictionary(source);
        }

        @Override
        public Dictionary[] newArray(int size) {
            return new Dictionary[size];
        }
    };
}
