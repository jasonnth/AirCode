package com.airbnb.android.registration.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.AccountSource;
import com.airbnb.android.core.models.AirPhone;

final class AutoValue_AccountLoginData extends C$AutoValue_AccountLoginData {
    public static final Creator<AutoValue_AccountLoginData> CREATOR = new Creator<AutoValue_AccountLoginData>() {
        public AutoValue_AccountLoginData createFromParcel(Parcel in) {
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            String str8 = null;
            AccountSource valueOf = AccountSource.valueOf(in.readString());
            if (in.readInt() == 0) {
                str = in.readString();
            } else {
                str = null;
            }
            AirPhone airPhone = (AirPhone) in.readParcelable(AirPhone.class.getClassLoader());
            if (in.readInt() == 0) {
                str2 = in.readString();
            } else {
                str2 = null;
            }
            if (in.readInt() == 0) {
                str3 = in.readString();
            } else {
                str3 = null;
            }
            if (in.readInt() == 0) {
                str4 = in.readString();
            } else {
                str4 = null;
            }
            if (in.readInt() == 0) {
                str5 = in.readString();
            } else {
                str5 = null;
            }
            if (in.readInt() == 0) {
                str6 = in.readString();
            } else {
                str6 = null;
            }
            if (in.readInt() == 0) {
                str7 = in.readString();
            } else {
                str7 = null;
            }
            if (in.readInt() == 0) {
                str8 = in.readString();
            }
            return new AutoValue_AccountLoginData(valueOf, str, airPhone, str2, str3, str4, str5, str6, str7, str8);
        }

        public AutoValue_AccountLoginData[] newArray(int size) {
            return new AutoValue_AccountLoginData[size];
        }
    };

    AutoValue_AccountLoginData(AccountSource accountSource, String phone, AirPhone airPhone, String email, String password, String authToken, String mowebAuthId, String mowebAccessToken, String firstName, String profilePicture) {
        super(accountSource, phone, airPhone, email, password, authToken, mowebAuthId, mowebAccessToken, firstName, profilePicture);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(accountSource().name());
        if (phone() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(phone());
        }
        dest.writeParcelable(airPhone(), flags);
        if (email() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(email());
        }
        if (password() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(password());
        }
        if (authToken() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(authToken());
        }
        if (mowebAuthId() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(mowebAuthId());
        }
        if (mowebAccessToken() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(mowebAccessToken());
        }
        if (firstName() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(firstName());
        }
        if (profilePicture() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeString(profilePicture());
    }

    public int describeContents() {
        return 0;
    }
}
