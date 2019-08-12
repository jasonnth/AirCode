package com.airbnb.android.itinerary.data.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_TripEventSecondaryAction extends C$AutoValue_TripEventSecondaryAction {
    public static final Creator<AutoValue_TripEventSecondaryAction> CREATOR = new Creator<AutoValue_TripEventSecondaryAction>() {
        public AutoValue_TripEventSecondaryAction createFromParcel(Parcel in) {
            String str;
            String str2;
            String str3;
            String str4 = null;
            if (in.readInt() == 0) {
                str = in.readString();
            } else {
                str = null;
            }
            SecondaryActionButtonType secondaryActionButtonType = (SecondaryActionButtonType) in.readParcelable(SecondaryActionButtonType.class.getClassLoader());
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
            }
            return new AutoValue_TripEventSecondaryAction(str, secondaryActionButtonType, str2, str3, str4, (SecondaryActionType) in.readParcelable(SecondaryActionType.class.getClassLoader()));
        }

        public AutoValue_TripEventSecondaryAction[] newArray(int size) {
            return new AutoValue_TripEventSecondaryAction[size];
        }
    };

    AutoValue_TripEventSecondaryAction(String buttonText, SecondaryActionButtonType buttonType, String destination, String destinationOptions, String title, SecondaryActionType type) {
        super(buttonText, buttonType, destination, destinationOptions, title, type);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (buttonText() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(buttonText());
        }
        dest.writeParcelable(buttonType(), flags);
        if (destination() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(destination());
        }
        if (destinationOptions() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(destinationOptions());
        }
        if (title() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(title());
        }
        dest.writeParcelable(type(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
