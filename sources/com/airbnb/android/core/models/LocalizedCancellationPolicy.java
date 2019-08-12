package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenLocalizedCancellationPolicy;

public class LocalizedCancellationPolicy extends GenLocalizedCancellationPolicy {
    public static final Creator<LocalizedCancellationPolicy> CREATOR = new Creator<LocalizedCancellationPolicy>() {
        public LocalizedCancellationPolicy[] newArray(int size) {
            return new LocalizedCancellationPolicy[size];
        }

        public LocalizedCancellationPolicy createFromParcel(Parcel source) {
            LocalizedCancellationPolicy object = new LocalizedCancellationPolicy();
            object.readFromParcel(source);
            return object;
        }
    };
}
