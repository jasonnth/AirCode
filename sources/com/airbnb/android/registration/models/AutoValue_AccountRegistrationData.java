package com.airbnb.android.registration.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.AccountSource;
import com.airbnb.android.core.models.AirPhone;

final class AutoValue_AccountRegistrationData extends C$AutoValue_AccountRegistrationData {
    public static final Creator<AutoValue_AccountRegistrationData> CREATOR = new Creator<AutoValue_AccountRegistrationData>() {
        public AutoValue_AccountRegistrationData createFromParcel(Parcel in) {
            AccountSource accountSource;
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            boolean z = true;
            String str8 = null;
            if (in.readInt() == 0) {
                accountSource = AccountSource.valueOf(in.readString());
            } else {
                accountSource = null;
            }
            if (in.readInt() == 0) {
                str = in.readString();
            } else {
                str = null;
            }
            if (in.readInt() != 1) {
                z = false;
            }
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
            AirPhone airPhone = (AirPhone) in.readParcelable(AirPhone.class.getClassLoader());
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
            return new AutoValue_AccountRegistrationData(accountSource, str, z, str2, str3, airPhone, str4, str5, str6, str7, str8);
        }

        public AutoValue_AccountRegistrationData[] newArray(int size) {
            return new AutoValue_AccountRegistrationData[size];
        }
    };

    AutoValue_AccountRegistrationData(AccountSource accountSource, String email, boolean promoOptIn, String phone, String phoneSMSCode, AirPhone airPhone, String password, String firstName, String lastName, String birthDateString, String authToken) {
        super(accountSource, email, promoOptIn, phone, phoneSMSCode, airPhone, password, firstName, lastName, birthDateString, authToken);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        if (accountSource() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(accountSource().name());
        }
        if (email() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(email());
        }
        if (promoOptIn()) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeInt(i);
        if (phone() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(phone());
        }
        if (phoneSMSCode() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(phoneSMSCode());
        }
        dest.writeParcelable(airPhone(), flags);
        if (password() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(password());
        }
        if (firstName() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(firstName());
        }
        if (lastName() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(lastName());
        }
        if (birthDateString() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(birthDateString());
        }
        if (authToken() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeString(authToken());
    }

    public int describeContents() {
        return 0;
    }
}
