package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenAccountVerification;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class AccountVerification extends GenAccountVerification {
    private static final String COMPLETE = "complete";
    public static final Creator<AccountVerification> CREATOR = new Creator<AccountVerification>() {
        public AccountVerification[] newArray(int size) {
            return new AccountVerification[size];
        }

        public AccountVerification createFromParcel(Parcel source) {
            AccountVerification object = new AccountVerification();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String PHOTO = "photo_with_face";
    public static final String SCANID = "government_id";
    public static final String SELFIE = "selfie";

    @Retention(RetentionPolicy.SOURCE)
    public @interface AccountVerificationType {
    }

    public boolean isComplete() {
        return COMPLETE.equals(this.mStatus);
    }

    public boolean isOnlyRequiredForIdentityFlow() {
        return SCANID.equals(this.mType) || SELFIE.equals(this.mType);
    }

    public boolean isPhoneOrEmail() {
        return "email".equals(this.mType) || "phone".equals(this.mType);
    }

    public String getType() {
        return this.mType;
    }

    public int hashCode() {
        return this.mType.hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AccountVerification)) {
            return false;
        }
        return this.mType.equals(((AccountVerification) obj).mType);
    }
}
