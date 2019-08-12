package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenExplorePlaylist;

public class ExplorePlaylist extends GenExplorePlaylist {
    public static final Creator<ExplorePlaylist> CREATOR = new Creator<ExplorePlaylist>() {
        public ExplorePlaylist[] newArray(int size) {
            return new ExplorePlaylist[size];
        }

        public ExplorePlaylist createFromParcel(Parcel source) {
            ExplorePlaylist object = new ExplorePlaylist();
            object.readFromParcel(source);
            return object;
        }
    };
}
