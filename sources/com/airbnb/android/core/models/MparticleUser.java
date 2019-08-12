package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenMparticleUser;

public class MparticleUser extends GenMparticleUser {
    public static final Creator<MparticleUser> CREATOR = new Creator<MparticleUser>() {
        public MparticleUser[] newArray(int size) {
            return new MparticleUser[size];
        }

        public MparticleUser createFromParcel(Parcel source) {
            MparticleUser object = new MparticleUser();
            object.readFromParcel(source);
            return object;
        }
    };
}
