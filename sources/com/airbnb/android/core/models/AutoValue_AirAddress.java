package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_AirAddress extends C$AutoValue_AirAddress {
    public static final Creator<AutoValue_AirAddress> CREATOR = new Creator<AutoValue_AirAddress>() {
        public AutoValue_AirAddress createFromParcel(Parcel in) {
            String str;
            String str2;
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            Double d;
            Double d2 = null;
            if (in.readInt() == 0) {
                str = in.readString();
            } else {
                str = null;
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
                d = Double.valueOf(in.readDouble());
            } else {
                d = null;
            }
            if (in.readInt() == 0) {
                d2 = Double.valueOf(in.readDouble());
            }
            return new AutoValue_AirAddress(str, str2, str3, str4, str5, str6, str7, d, d2);
        }

        public AutoValue_AirAddress[] newArray(int size) {
            return new AutoValue_AirAddress[size];
        }
    };

    AutoValue_AirAddress(String streetAddressOne, String streetAddressTwo, String city, String state, String postalCode, String country, String countryCode, Double latitude, Double longitude) {
        super(streetAddressOne, streetAddressTwo, city, state, postalCode, country, countryCode, latitude, longitude);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (streetAddressOne() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(streetAddressOne());
        }
        if (streetAddressTwo() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(streetAddressTwo());
        }
        if (city() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(city());
        }
        if (state() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(state());
        }
        if (postalCode() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(postalCode());
        }
        if (country() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(country());
        }
        if (countryCode() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(countryCode());
        }
        if (latitude() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeDouble(latitude().doubleValue());
        }
        if (longitude() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        dest.writeDouble(longitude().doubleValue());
    }

    public int describeContents() {
        return 0;
    }
}
