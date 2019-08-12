package com.airbnb.android.core.enums;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.Referree;
import com.google.common.collect.FluentIterable;

public enum ListingStatus implements Parcelable {
    Listed(Referree.STATUS_LISTED),
    Unlisted("unlisted"),
    InProgress("in progress");
    
    public static final Creator<ListingStatus> CREATOR = null;
    private final String serverKey;

    static {
        CREATOR = new Creator<ListingStatus>() {
            public ListingStatus createFromParcel(Parcel in) {
                return ListingStatus.values()[in.readInt()];
            }

            public ListingStatus[] newArray(int size) {
                return new ListingStatus[size];
            }
        };
    }

    private ListingStatus(String serverKey2) {
        this.serverKey = serverKey2;
    }

    public static ListingStatus fromKey(String serverKey2) {
        return (ListingStatus) FluentIterable.from((E[]) values()).filter(ListingStatus$$Lambda$1.lambdaFactory$(serverKey2)).first().orNull();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
