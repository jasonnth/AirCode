package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSpokenLanguage;

public class SpokenLanguage extends GenSpokenLanguage {
    public static final Creator<SpokenLanguage> CREATOR = new Creator<SpokenLanguage>() {
        public SpokenLanguage[] newArray(int size) {
            return new SpokenLanguage[size];
        }

        public SpokenLanguage createFromParcel(Parcel source) {
            SpokenLanguage object = new SpokenLanguage();
            object.readFromParcel(source);
            return object;
        }
    };

    public void toggleSpoken() {
        this.mSpoken = !this.mSpoken;
    }
}
