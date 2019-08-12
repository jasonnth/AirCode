package com.facebook.accountkit;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.facebook.accountkit.internal.Utility;

public final class Account implements Parcelable {
    public static final Creator<Account> CREATOR = new Creator<Account>() {
        public Account createFromParcel(Parcel source) {
            return new Account(source);
        }

        public Account[] newArray(int size) {
            return new Account[size];
        }
    };
    private final String email;

    /* renamed from: id */
    private final String f3085id;
    private final PhoneNumber phoneNumber;

    public Account(String id, PhoneNumber phoneNumber2, String email2) {
        this.f3085id = id;
        this.phoneNumber = phoneNumber2;
        this.email = email2;
    }

    public String getEmail() {
        return this.email;
    }

    public String getId() {
        return this.f3085id;
    }

    public PhoneNumber getPhoneNumber() {
        return this.phoneNumber;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Account)) {
            return false;
        }
        Account o = (Account) other;
        if (!Utility.areObjectsEqual(this.email, o.email) || !Utility.areObjectsEqual(this.f3085id, o.f3085id) || !Utility.areObjectsEqual(this.phoneNumber, o.phoneNumber)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((Utility.getHashCode(this.email) + 527) * 31) + Utility.getHashCode(this.f3085id)) * 31) + Utility.getHashCode(this.phoneNumber);
    }

    private Account(Parcel source) {
        this.f3085id = source.readString();
        this.phoneNumber = (PhoneNumber) source.readParcelable(PhoneNumber.class.getClassLoader());
        this.email = source.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.f3085id);
        dest.writeParcelable(this.phoneNumber, flags);
        dest.writeString(this.email);
    }
}
