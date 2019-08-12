package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.models.generated.GenPaginationMetadata;

public class PaginationMetadata extends GenPaginationMetadata {
    public static final Creator<PaginationMetadata> CREATOR = new Creator<PaginationMetadata>() {
        public PaginationMetadata[] newArray(int size) {
            return new PaginationMetadata[size];
        }

        public PaginationMetadata createFromParcel(Parcel source) {
            PaginationMetadata object = new PaginationMetadata();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean hasSearchSessionId() {
        return !TextUtils.isEmpty(this.mSearchSessionId);
    }
}
