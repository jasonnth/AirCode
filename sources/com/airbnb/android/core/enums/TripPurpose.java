package com.airbnb.android.core.enums;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.C0716R;
import com.google.common.collect.FluentIterable;

public enum TripPurpose implements Parcelable {
    Visiting("visiting", C0716R.string.trip_purpose_visiting, C0716R.string.p4_trip_purpose_other),
    Exploring("exploring", C0716R.string.trip_purpose_exploring, C0716R.string.p4_trip_purpose_exploring_city),
    Business("business", C0716R.string.trip_purpose_business, C0716R.string.p4_trip_purpose_business),
    Rest("rest", C0716R.string.trip_purpose_rest, C0716R.string.p4_trip_purpose_rest_and_relaxation),
    Event("event", C0716R.string.trip_purpose_event, C0716R.string.p4_trip_purpose_attending_event),
    Other("other", C0716R.string.trip_purpose_other, C0716R.string.p4_trip_purpose_other);
    
    public static final Creator<TripPurpose> CREATOR = null;
    public final int hostMessageRes;
    public final String serverKey;
    public final int textRes;

    static {
        CREATOR = new Creator<TripPurpose>() {
            public TripPurpose createFromParcel(Parcel source) {
                return TripPurpose.values()[source.readInt()];
            }

            public TripPurpose[] newArray(int size) {
                return new TripPurpose[size];
            }
        };
    }

    private TripPurpose(String serverKey2, int textRes2, int hostMessageRes2) {
        this.serverKey = serverKey2;
        this.textRes = textRes2;
        this.hostMessageRes = hostMessageRes2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }

    public static TripPurpose fromKey(String key) {
        return (TripPurpose) FluentIterable.m1283of(values()).filter(TripPurpose$$Lambda$1.lambdaFactory$(key)).first().orNull();
    }
}
