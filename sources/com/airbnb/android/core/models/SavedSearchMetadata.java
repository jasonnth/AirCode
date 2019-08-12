package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSavedSearchMetadata;

public class SavedSearchMetadata extends GenSavedSearchMetadata {
    public static final Creator<SavedSearchMetadata> CREATOR = new Creator<SavedSearchMetadata>() {
        public SavedSearchMetadata[] newArray(int size) {
            return new SavedSearchMetadata[size];
        }

        public SavedSearchMetadata createFromParcel(Parcel source) {
            SavedSearchMetadata object = new SavedSearchMetadata();
            object.readFromParcel(source);
            return object;
        }
    };
}
