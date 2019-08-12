package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenLocation;

/* renamed from: com.airbnb.android.core.models.Location */
public class C6049Location extends GenLocation {
    public static final Creator<C6049Location> CREATOR = new Creator<C6049Location>() {
        public C6049Location[] newArray(int size) {
            return new C6049Location[size];
        }

        public C6049Location createFromParcel(Parcel source) {
            C6049Location object = new C6049Location();
            object.readFromParcel(source);
            return object;
        }
    };
}
