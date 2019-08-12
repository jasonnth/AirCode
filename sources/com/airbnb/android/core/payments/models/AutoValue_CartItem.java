package com.airbnb.android.core.payments.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.payments.models.clientparameters.QuickPayParameters;

final class AutoValue_CartItem extends C$AutoValue_CartItem {
    public static final Creator<AutoValue_CartItem> CREATOR = new Creator<AutoValue_CartItem>() {
        public AutoValue_CartItem createFromParcel(Parcel in) {
            String str;
            String str2;
            String str3 = null;
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
            }
            return new AutoValue_CartItem(str, str2, str3, (QuickPayParameters) in.readParcelable(QuickPayParameters.class.getClassLoader()));
        }

        public AutoValue_CartItem[] newArray(int size) {
            return new AutoValue_CartItem[size];
        }
    };

    AutoValue_CartItem(String thumbnailUrl, String title, String description, QuickPayParameters quickPayParameters) {
        super(thumbnailUrl, title, description, quickPayParameters);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (thumbnailUrl() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(thumbnailUrl());
        }
        if (title() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(title());
        }
        if (description() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(description());
        }
        dest.writeParcelable(quickPayParameters(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
