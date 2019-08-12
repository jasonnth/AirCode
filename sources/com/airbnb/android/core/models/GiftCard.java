package com.airbnb.android.core.models;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class GiftCard implements Parcelable {
    public static Creator<GiftCard> CREATOR = new Creator<GiftCard>() {
        public GiftCard createFromParcel(Parcel source) {
            return new GiftCard(source);
        }

        public GiftCard[] newArray(int size) {
            return new GiftCard[size];
        }
    };
    private final String code;
    private final String verificationToken;

    public static GiftCard fromUri(Uri uri) {
        return new GiftCard(uri.getQueryParameter("code"), uri.getQueryParameter("token"));
    }

    public GiftCard(String code2, String verificationToken2) {
        this.code = code2;
        this.verificationToken = verificationToken2;
    }

    public GiftCard(Parcel in) {
        this.code = in.readString();
        this.verificationToken = in.readString();
    }

    public boolean isValid() {
        return (this.code == null || this.verificationToken == null) ? false : true;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.code);
        dest.writeString(this.verificationToken);
    }

    public String code() {
        return this.code;
    }

    public String verificationToken() {
        return this.verificationToken;
    }
}
