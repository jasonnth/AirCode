package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenEmptyStateMetadata;

public class EmptyStateMetadata extends GenEmptyStateMetadata {
    public static final Creator<EmptyStateMetadata> CREATOR = new Creator<EmptyStateMetadata>() {
        public EmptyStateMetadata[] newArray(int size) {
            return new EmptyStateMetadata[size];
        }

        public EmptyStateMetadata createFromParcel(Parcel source) {
            EmptyStateMetadata object = new EmptyStateMetadata();
            object.readFromParcel(source);
            return object;
        }
    };
}
