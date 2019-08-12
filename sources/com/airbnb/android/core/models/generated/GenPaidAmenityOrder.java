package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.PaidAmenity;
import com.airbnb.android.core.paidamenities.enums.PaidAmenityOrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenPaidAmenityOrder implements Parcelable {
    @JsonProperty("bill_item_token")
    protected String mBillItemToken;
    @JsonProperty("bill_token")
    protected String mBillToken;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("number_of_units")
    protected int mNumberOfUnits;
    @JsonProperty("paid_amenity")
    protected PaidAmenity mPaidAmenity;
    @JsonProperty("status_text")
    protected PaidAmenityOrderStatus mStatus;

    protected GenPaidAmenityOrder(PaidAmenity paidAmenity, PaidAmenityOrderStatus status, String billToken, String billItemToken, int numberOfUnits, long id) {
        this();
        this.mPaidAmenity = paidAmenity;
        this.mStatus = status;
        this.mBillToken = billToken;
        this.mBillItemToken = billItemToken;
        this.mNumberOfUnits = numberOfUnits;
        this.mId = id;
    }

    protected GenPaidAmenityOrder() {
    }

    public PaidAmenity getPaidAmenity() {
        return this.mPaidAmenity;
    }

    @JsonProperty("paid_amenity")
    public void setPaidAmenity(PaidAmenity value) {
        this.mPaidAmenity = value;
    }

    public PaidAmenityOrderStatus getStatus() {
        return this.mStatus;
    }

    public String getBillToken() {
        return this.mBillToken;
    }

    @JsonProperty("bill_token")
    public void setBillToken(String value) {
        this.mBillToken = value;
    }

    public String getBillItemToken() {
        return this.mBillItemToken;
    }

    @JsonProperty("bill_item_token")
    public void setBillItemToken(String value) {
        this.mBillItemToken = value;
    }

    public int getNumberOfUnits() {
        return this.mNumberOfUnits;
    }

    @JsonProperty("number_of_units")
    public void setNumberOfUnits(int value) {
        this.mNumberOfUnits = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mPaidAmenity, 0);
        parcel.writeParcelable(this.mStatus, 0);
        parcel.writeString(this.mBillToken);
        parcel.writeString(this.mBillItemToken);
        parcel.writeInt(this.mNumberOfUnits);
        parcel.writeLong(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mPaidAmenity = (PaidAmenity) source.readParcelable(PaidAmenity.class.getClassLoader());
        this.mStatus = (PaidAmenityOrderStatus) source.readParcelable(PaidAmenityOrderStatus.class.getClassLoader());
        this.mBillToken = source.readString();
        this.mBillItemToken = source.readString();
        this.mNumberOfUnits = source.readInt();
        this.mId = source.readLong();
    }
}
