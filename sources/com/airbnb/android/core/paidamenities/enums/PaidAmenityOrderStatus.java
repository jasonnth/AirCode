package com.airbnb.android.core.paidamenities.enums;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.C0716R;
import com.facebook.internal.AnalyticsEvents;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.FluentIterable;

public enum PaidAmenityOrderStatus implements Parcelable {
    Pending("pending", C0716R.string.paid_amenities_order_status_pending, C0716R.color.c_beach),
    Accepted("accepted", C0716R.string.paid_amenities_order_status_accepted, C0716R.color.c_lima),
    Declined("declined", C0716R.string.paid_amenities_order_status_declined, C0716R.color.c_rausch_dark),
    Cancelled(AnalyticsEvents.PARAMETER_SHARE_OUTCOME_CANCELLED, C0716R.string.paid_amenities_order_status_canceled, C0716R.color.c_gray_3),
    Unknown("", -1, -1);
    
    public static final Creator<PaidAmenityOrderStatus> CREATOR = null;
    private final int colorRes;
    private final int displayStatusTextRes;
    private final String serverKey;

    static {
        CREATOR = new Creator<PaidAmenityOrderStatus>() {
            public PaidAmenityOrderStatus createFromParcel(Parcel source) {
                return PaidAmenityOrderStatus.values()[source.readInt()];
            }

            public PaidAmenityOrderStatus[] newArray(int size) {
                return new PaidAmenityOrderStatus[size];
            }
        };
    }

    private PaidAmenityOrderStatus(String serverKey2, int displayStatusTextRes2, int colorRes2) {
        this.serverKey = serverKey2;
        this.displayStatusTextRes = displayStatusTextRes2;
        this.colorRes = colorRes2;
    }

    @JsonValue
    public String getServerKey() {
        return this.serverKey;
    }

    @JsonCreator
    public static PaidAmenityOrderStatus findOrderStatusByStatusText(String statusText) {
        return (PaidAmenityOrderStatus) FluentIterable.m1283of(values()).firstMatch(PaidAmenityOrderStatus$$Lambda$1.lambdaFactory$(statusText)).mo41059or(Unknown);
    }

    public int getColorRes() {
        return this.colorRes;
    }

    public int getDisplayStatusTextRes() {
        return this.displayStatusTextRes;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }
}
