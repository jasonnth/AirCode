package com.airbnb.android.core.enums;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.common.collect.FluentIterable;

public enum ReadyForSelectStatus implements Parcelable {
    Marketplace(1),
    ReadyForSelect(2),
    PostReadyForSelect(3),
    Select(4);
    
    public static final Creator<ReadyForSelectStatus> CREATOR = null;
    private final int serverKey;

    static {
        CREATOR = new Creator<ReadyForSelectStatus>() {
            public ReadyForSelectStatus createFromParcel(Parcel in) {
                return ReadyForSelectStatus.values()[in.readInt()];
            }

            public ReadyForSelectStatus[] newArray(int size) {
                return new ReadyForSelectStatus[size];
            }
        };
    }

    private ReadyForSelectStatus(int serverKey2) {
        this.serverKey = serverKey2;
    }

    public static ReadyForSelectStatus fromKeyWithDefault(int serverKey2, ReadyForSelectStatus defaultValue) {
        return (ReadyForSelectStatus) FluentIterable.from((E[]) values()).firstMatch(ReadyForSelectStatus$$Lambda$1.lambdaFactory$(serverKey2)).mo41059or(defaultValue);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
