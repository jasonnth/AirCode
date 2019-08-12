package com.airbnb.android.core.p008mt.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

/* renamed from: com.airbnb.android.core.mt.models.ProductType */
public enum C6213ProductType implements Parcelable {
    IMMERSION(0),
    EXPERIENCE(1);
    
    public static final Creator<C6213ProductType> CREATOR = null;

    /* renamed from: id */
    public final int f8481id;

    static {
        CREATOR = new Creator<C6213ProductType>() {
            public C6213ProductType createFromParcel(Parcel source) {
                return C6213ProductType.values()[source.readInt()];
            }

            public C6213ProductType[] newArray(int size) {
                return new C6213ProductType[size];
            }
        };
    }

    private C6213ProductType(int id) {
        this.f8481id = id;
    }

    public static C6213ProductType from(int givenId) {
        if (IMMERSION.f8481id == givenId) {
            return IMMERSION;
        }
        if (EXPERIENCE.f8481id == givenId) {
            return EXPERIENCE;
        }
        return null;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }
}
