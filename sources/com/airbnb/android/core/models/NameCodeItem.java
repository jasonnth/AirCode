package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenNameCodeItem;

public class NameCodeItem extends GenNameCodeItem {
    public static final Creator<NameCodeItem> CREATOR = new Creator<NameCodeItem>() {
        public NameCodeItem[] newArray(int size) {
            return new NameCodeItem[size];
        }

        public NameCodeItem createFromParcel(Parcel source) {
            NameCodeItem object = new NameCodeItem();
            object.readFromParcel(source);
            return object;
        }
    };

    public void setCode(String code) {
        this.mCode = code;
    }

    public void setName(String name) {
        this.mName = name;
    }
}
