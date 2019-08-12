package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class ThreadUpdateWithPosts extends Thread {
    public static final Creator<ThreadUpdateWithPosts> CREATOR = new Creator<ThreadUpdateWithPosts>() {
        public ThreadUpdateWithPosts[] newArray(int size) {
            return new ThreadUpdateWithPosts[size];
        }

        public ThreadUpdateWithPosts createFromParcel(Parcel source) {
            ThreadUpdateWithPosts object = new ThreadUpdateWithPosts();
            object.readFromParcel(source);
            return object;
        }
    };

    public void writeToParcel(Parcel parcel, int flags) {
        super.writeToParcel(parcel, flags);
    }
}
