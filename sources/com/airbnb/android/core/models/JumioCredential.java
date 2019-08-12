package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenJumioCredential;

public class JumioCredential extends GenJumioCredential {
    public static final Creator<JumioCredential> CREATOR = new Creator<JumioCredential>() {
        public JumioCredential[] newArray(int size) {
            return new JumioCredential[size];
        }

        public JumioCredential createFromParcel(Parcel source) {
            JumioCredential object = new JumioCredential();
            object.readFromParcel(source);
            return object;
        }
    };

    protected JumioCredential() {
    }
}
