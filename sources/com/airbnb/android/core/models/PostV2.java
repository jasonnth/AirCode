package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenPostV2;

public class PostV2 extends GenPostV2 {
    public static final Creator<PostV2> CREATOR = new Creator<PostV2>() {
        public PostV2[] newArray(int size) {
            return new PostV2[size];
        }

        public PostV2 createFromParcel(Parcel source) {
            PostV2 object = new PostV2();
            object.readFromParcel(source);
            return object;
        }
    };
}
