package com.airbnb.android.core.p008mt.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonCreator;

/* renamed from: com.airbnb.android.core.mt.models.GuidebookItemType */
public enum GuidebookItemType implements Parcelable {
    Album(1),
    PlaceCollection(2),
    Detour(3),
    PlacesNearby(6),
    Meetups(7),
    Unknown(-1);
    
    public static final Creator<GuidebookItemType> CREATOR = null;

    /* renamed from: id */
    private final int f1082id;

    static {
        CREATOR = new Creator<GuidebookItemType>() {
            public GuidebookItemType createFromParcel(Parcel source) {
                return GuidebookItemType.values()[source.readInt()];
            }

            public GuidebookItemType[] newArray(int size) {
                return new GuidebookItemType[size];
            }
        };
    }

    private GuidebookItemType(int id) {
        this.f1082id = id;
    }

    @JsonCreator
    public static GuidebookItemType from(int givenId) {
        GuidebookItemType[] values;
        for (GuidebookItemType type : values()) {
            if (type.getId() == givenId) {
                return type;
            }
        }
        return Unknown;
    }

    public int getId() {
        return this.f1082id;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }
}
