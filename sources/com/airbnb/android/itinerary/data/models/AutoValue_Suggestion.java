package com.airbnb.android.itinerary.data.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_Suggestion extends C$AutoValue_Suggestion {
    public static final Creator<AutoValue_Suggestion> CREATOR = new Creator<AutoValue_Suggestion>() {
        public AutoValue_Suggestion createFromParcel(Parcel in) {
            String str;
            String str2;
            long readLong = in.readLong();
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
            return new AutoValue_Suggestion(readLong, str, str2, (SuggestionType) in.readParcelable(SuggestionType.class.getClassLoader()));
        }

        public AutoValue_Suggestion[] newArray(int size) {
            return new AutoValue_Suggestion[size];
        }
    };

    AutoValue_Suggestion(long id, String title, String pictureUrl, SuggestionType type) {
        super(id, title, pictureUrl, type);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(mo10321id());
        if (title() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(title());
        }
        if (pictureUrl() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(pictureUrl());
        }
        dest.writeParcelable(type(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
