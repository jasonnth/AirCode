package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenIconWithTitles;

public class IconWithTitles extends GenIconWithTitles {
    public static final Creator<IconWithTitles> CREATOR = new Creator<IconWithTitles>() {
        public IconWithTitles[] newArray(int size) {
            return new IconWithTitles[size];
        }

        public IconWithTitles createFromParcel(Parcel source) {
            IconWithTitles object = new IconWithTitles();
            object.readFromParcel(source);
            return object;
        }
    };
}
