package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.C0716R;
import com.google.common.collect.FluentIterable;

/* renamed from: com.airbnb.android.core.models.RoomType */
public enum C6120RoomType implements Parcelable {
    EntireHome("Entire home/apt", C0716R.string.entire_place, C0716R.string.filter_room_type_entire_home_title),
    PrivateRoom("Private room", C0716R.string.private_room, C0716R.string.filter_room_type_private_room_title),
    SharedRoom("Shared room", C0716R.string.shared_room, C0716R.string.filter_room_type_shared_room_title);
    
    public static final Creator<C6120RoomType> CREATOR = null;
    public final int descriptionRes;
    public final String key;
    public final int titleRes;

    static {
        CREATOR = new Creator<C6120RoomType>() {
            public C6120RoomType createFromParcel(Parcel source) {
                return C6120RoomType.values()[source.readInt()];
            }

            public C6120RoomType[] newArray(int size) {
                return new C6120RoomType[size];
            }
        };
    }

    private C6120RoomType(String key2, int titleRes2, int descriptionRes2) {
        this.key = key2;
        this.titleRes = titleRes2;
        this.descriptionRes = descriptionRes2;
    }

    public static C6120RoomType fromKey(String key2) {
        return (C6120RoomType) FluentIterable.m1283of(values()).filter(RoomType$$Lambda$1.lambdaFactory$(key2)).first().orNull();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }
}
