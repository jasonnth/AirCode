package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.PaidAmenityOrder;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenReservationSummary implements Parcelable {
    @JsonProperty("confirmation_code")
    protected String mConfirmationCode;
    @JsonProperty("is_canceled_by_host")
    protected boolean mIsCanceledByHost;
    @JsonProperty("using_identity_flow")
    protected boolean mIsGuestPendingIdentityVerification;
    @JsonProperty("paid_amenity_orders")
    protected List<PaidAmenityOrder> mPaidAmenityOrders;
    @JsonProperty("pending_expires_at")
    protected AirDateTime mPendingExpiresAt;

    protected GenReservationSummary(AirDateTime pendingExpiresAt, List<PaidAmenityOrder> paidAmenityOrders, String confirmationCode, boolean isGuestPendingIdentityVerification, boolean isCanceledByHost) {
        this();
        this.mPendingExpiresAt = pendingExpiresAt;
        this.mPaidAmenityOrders = paidAmenityOrders;
        this.mConfirmationCode = confirmationCode;
        this.mIsGuestPendingIdentityVerification = isGuestPendingIdentityVerification;
        this.mIsCanceledByHost = isCanceledByHost;
    }

    protected GenReservationSummary() {
    }

    public AirDateTime getPendingExpiresAt() {
        return this.mPendingExpiresAt;
    }

    @JsonProperty("pending_expires_at")
    public void setPendingExpiresAt(AirDateTime value) {
        this.mPendingExpiresAt = value;
    }

    public List<PaidAmenityOrder> getPaidAmenityOrders() {
        return this.mPaidAmenityOrders;
    }

    @JsonProperty("paid_amenity_orders")
    public void setPaidAmenityOrders(List<PaidAmenityOrder> value) {
        this.mPaidAmenityOrders = value;
    }

    public String getConfirmationCode() {
        return this.mConfirmationCode;
    }

    @JsonProperty("confirmation_code")
    public void setConfirmationCode(String value) {
        this.mConfirmationCode = value;
    }

    public boolean isGuestPendingIdentityVerification() {
        return this.mIsGuestPendingIdentityVerification;
    }

    @JsonProperty("using_identity_flow")
    public void setIsGuestPendingIdentityVerification(boolean value) {
        this.mIsGuestPendingIdentityVerification = value;
    }

    public boolean isCanceledByHost() {
        return this.mIsCanceledByHost;
    }

    @JsonProperty("is_canceled_by_host")
    public void setIsCanceledByHost(boolean value) {
        this.mIsCanceledByHost = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mPendingExpiresAt, 0);
        parcel.writeTypedList(this.mPaidAmenityOrders);
        parcel.writeString(this.mConfirmationCode);
        parcel.writeBooleanArray(new boolean[]{this.mIsGuestPendingIdentityVerification, this.mIsCanceledByHost});
    }

    public void readFromParcel(Parcel source) {
        this.mPendingExpiresAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mPaidAmenityOrders = source.createTypedArrayList(PaidAmenityOrder.CREATOR);
        this.mConfirmationCode = source.readString();
        boolean[] bools = source.createBooleanArray();
        this.mIsGuestPendingIdentityVerification = bools[0];
        this.mIsCanceledByHost = bools[1];
    }
}
