package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenTimelineMetadata;

public class TimelineMetadata extends GenTimelineMetadata {
    public static final Creator<TimelineMetadata> CREATOR = new Creator<TimelineMetadata>() {
        public TimelineMetadata[] newArray(int size) {
            return new TimelineMetadata[size];
        }

        public TimelineMetadata createFromParcel(Parcel source) {
            TimelineMetadata object = new TimelineMetadata();
            object.readFromParcel(source);
            return object;
        }
    };
}
