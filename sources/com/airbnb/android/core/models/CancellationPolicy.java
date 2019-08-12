package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.airbnb.android.core.models.generated.GenCancellationPolicy;

public class CancellationPolicy extends GenCancellationPolicy {
    public static final Creator<CancellationPolicy> CREATOR = new Creator<CancellationPolicy>() {
        public CancellationPolicy[] newArray(int size) {
            return new CancellationPolicy[size];
        }

        public CancellationPolicy createFromParcel(Parcel source) {
            CancellationPolicy object = new CancellationPolicy();
            object.readFromParcel(source);
            return object;
        }
    };

    public String getPolicyAsString() {
        return TextUtils.join("\n\n", getDetails());
    }
}
