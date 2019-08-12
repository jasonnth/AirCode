package com.airbnb.android.core.p008mt.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonCreator;

/* renamed from: com.airbnb.android.core.mt.models.RecommendationCardType */
public enum RecommendationCardType implements Parcelable {
    Small("small"),
    Medium("medium"),
    Large("large"),
    Unknown("");
    
    public static final Creator<RecommendationCardType> CREATOR = null;
    private final String key;

    static {
        CREATOR = new Creator<RecommendationCardType>() {
            public RecommendationCardType createFromParcel(Parcel source) {
                return RecommendationCardType.values()[source.readInt()];
            }

            public RecommendationCardType[] newArray(int size) {
                return new RecommendationCardType[size];
            }
        };
    }

    private RecommendationCardType(String key2) {
        this.key = key2;
    }

    @JsonCreator
    public static RecommendationCardType from(String givenKey) {
        RecommendationCardType[] values;
        for (RecommendationCardType type : values()) {
            if (type.key.equals(givenKey)) {
                return type;
            }
        }
        return Unknown;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }

    public String getKey() {
        return this.key;
    }
}
