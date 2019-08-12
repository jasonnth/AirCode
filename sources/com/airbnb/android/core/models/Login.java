package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenLogin;

public class Login extends GenLogin {
    public static final Creator<Login> CREATOR = new Creator<Login>() {
        public Login[] newArray(int size) {
            return new Login[size];
        }

        public Login createFromParcel(Parcel source) {
            Login object = new Login();
            object.readFromParcel(source);
            return object;
        }
    };
}
