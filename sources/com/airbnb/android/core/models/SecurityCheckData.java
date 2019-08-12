package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSecurityCheckData;

public class SecurityCheckData extends GenSecurityCheckData {
    public static final Creator<SecurityCheckData> CREATOR = new Creator<SecurityCheckData>() {
        public SecurityCheckData[] newArray(int size) {
            return new SecurityCheckData[size];
        }

        public SecurityCheckData createFromParcel(Parcel source) {
            SecurityCheckData object = new SecurityCheckData();
            object.readFromParcel(source);
            return object;
        }
    };
}
