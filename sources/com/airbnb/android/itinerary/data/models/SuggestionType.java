package com.airbnb.android.itinerary.data.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.facebook.share.internal.ShareConstants;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum SuggestionType implements Parcelable {
    Experience("EXPERIENCE"),
    Place(ShareConstants.PLACE_ID),
    Immersion("IMMERSION"),
    Unknown("");
    
    public static final Creator<SuggestionType> CREATOR = null;
    private final String key;

    static {
        CREATOR = new Creator<SuggestionType>() {
            public SuggestionType createFromParcel(Parcel source) {
                return SuggestionType.values()[source.readInt()];
            }

            public SuggestionType[] newArray(int size) {
                return new SuggestionType[size];
            }
        };
    }

    private SuggestionType(String key2) {
        this.key = key2;
    }

    @JsonCreator
    public static SuggestionType from(String key2) {
        SuggestionType[] values = values();
        int length = values.length;
        for (int i = 0; i < length; i++) {
            SuggestionType type = values[i];
            if (type.getKey().equals(key2) || type.name().equals(key2)) {
                return type;
            }
        }
        return Unknown;
    }

    public String getKey() {
        return this.key;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }
}
