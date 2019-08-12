package com.airbnb.android.core.models;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.utils.Trebuchet;
import com.facebook.internal.AnalyticsEvents;
import java.util.Arrays;
import java.util.HashMap;

public enum ReservationStatus implements Parcelable {
    Preapproved("preapproved"),
    New("new"),
    Accepted("accepted"),
    Inquiry("inquiry"),
    Pending("pending"),
    Denied("denied"),
    NotPossible("not_possible"),
    Cancelled(AnalyticsEvents.PARAMETER_SHARE_OUTCOME_CANCELLED),
    Timedout("timedout"),
    SpecialOffer("special_offer"),
    Checkpoint(Trebuchet.CHECKPOINT),
    WaitingForPayment("awaiting_payment"),
    Message("message"),
    Unknown("unknown");
    
    public static final Creator<ReservationStatus> CREATOR = null;
    private static final HashMap<String, ReservationStatus> mMap = null;
    public final String key;

    static {
        int i;
        ReservationStatus[] values;
        mMap = new HashMap<>(values().length);
        for (ReservationStatus status : values()) {
            mMap.put(status.key, status);
            mMap.put(status.name(), status);
        }
        CREATOR = new Creator<ReservationStatus>() {
            public ReservationStatus createFromParcel(Parcel source) {
                return ReservationStatus.values()[source.readInt()];
            }

            public ReservationStatus[] newArray(int size) {
                return new ReservationStatus[size];
            }
        };
    }

    private ReservationStatus(String key2) {
        this.key = key2;
    }

    @SuppressLint({"DefaultLocale"})
    public static ReservationStatus findStatus(String statusText) {
        ReservationStatus status = (ReservationStatus) mMap.get(statusText);
        return (status == null || status == New) ? Unknown : status;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }

    public boolean matchesAny(ReservationStatus... haystack) {
        return Arrays.asList(haystack).contains(this);
    }
}
