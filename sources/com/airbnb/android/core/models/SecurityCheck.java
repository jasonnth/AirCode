package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSecurityCheck;

public class SecurityCheck extends GenSecurityCheck {
    public static final Creator<SecurityCheck> CREATOR = new Creator<SecurityCheck>() {
        public SecurityCheck[] newArray(int size) {
            return new SecurityCheck[size];
        }

        public SecurityCheck createFromParcel(Parcel source) {
            SecurityCheck object = new SecurityCheck();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final String ERROR_SECURITY_CHECK_REQUIRED = "security_check_required";
    public static final int REQUEST_CODE_CONTACT = 1001;
    public static final String STRATEGY_PHONE_VERIFICATION = "phone_verification";
}
