package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum ExplorePromotionStyle implements Parcelable {
    BabuBackground("babu"),
    InverseBackground("inverse"),
    Unknown("");
    
    public static final Creator<ExplorePromotionStyle> CREATOR = null;
    public final String style;

    static {
        CREATOR = new Creator<ExplorePromotionStyle>() {
            public ExplorePromotionStyle createFromParcel(Parcel source) {
                return ExplorePromotionStyle.values()[source.readInt()];
            }

            public ExplorePromotionStyle[] newArray(int size) {
                return new ExplorePromotionStyle[size];
            }
        };
    }

    private ExplorePromotionStyle(String style2) {
        this.style = style2;
    }

    @JsonCreator
    public static ExplorePromotionStyle from(String givenStyle) {
        ExplorePromotionStyle[] values;
        for (ExplorePromotionStyle type : values()) {
            if (type.style.equals(givenStyle)) {
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
}
