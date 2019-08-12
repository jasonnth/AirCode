package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.CurrencyAmount;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenHostEarnings implements Parcelable {
    @JsonProperty("cancellation_fees")
    protected List<CurrencyAmount> mCancellationFees;
    @JsonProperty("month")
    protected int mMonth;
    @JsonProperty("paid_out")
    protected List<CurrencyAmount> mPaidOut;
    @JsonProperty("paid_out_converted")
    protected CurrencyAmount mPaidOutCombined;
    @JsonProperty("pending")
    protected List<CurrencyAmount> mPending;
    @JsonProperty("pending_converted")
    protected CurrencyAmount mPendingCombined;
    @JsonProperty("total")
    protected List<CurrencyAmount> mTotal;
    @JsonProperty("year")
    protected int mYear;

    protected GenHostEarnings(CurrencyAmount paidOutCombined, CurrencyAmount pendingCombined, List<CurrencyAmount> paidOut, List<CurrencyAmount> pending, List<CurrencyAmount> cancellationFees, List<CurrencyAmount> total, int month, int year) {
        this();
        this.mPaidOutCombined = paidOutCombined;
        this.mPendingCombined = pendingCombined;
        this.mPaidOut = paidOut;
        this.mPending = pending;
        this.mCancellationFees = cancellationFees;
        this.mTotal = total;
        this.mMonth = month;
        this.mYear = year;
    }

    protected GenHostEarnings() {
    }

    public CurrencyAmount getPaidOutCombined() {
        return this.mPaidOutCombined;
    }

    @JsonProperty("paid_out_converted")
    public void setPaidOutCombined(CurrencyAmount value) {
        this.mPaidOutCombined = value;
    }

    public CurrencyAmount getPendingCombined() {
        return this.mPendingCombined;
    }

    @JsonProperty("pending_converted")
    public void setPendingCombined(CurrencyAmount value) {
        this.mPendingCombined = value;
    }

    public List<CurrencyAmount> getPaidOut() {
        return this.mPaidOut;
    }

    @JsonProperty("paid_out")
    public void setPaidOut(List<CurrencyAmount> value) {
        this.mPaidOut = value;
    }

    public List<CurrencyAmount> getPending() {
        return this.mPending;
    }

    @JsonProperty("pending")
    public void setPending(List<CurrencyAmount> value) {
        this.mPending = value;
    }

    public List<CurrencyAmount> getCancellationFees() {
        return this.mCancellationFees;
    }

    @JsonProperty("cancellation_fees")
    public void setCancellationFees(List<CurrencyAmount> value) {
        this.mCancellationFees = value;
    }

    public List<CurrencyAmount> getTotal() {
        return this.mTotal;
    }

    @JsonProperty("total")
    public void setTotal(List<CurrencyAmount> value) {
        this.mTotal = value;
    }

    public int getMonth() {
        return this.mMonth;
    }

    @JsonProperty("month")
    public void setMonth(int value) {
        this.mMonth = value;
    }

    public int getYear() {
        return this.mYear;
    }

    @JsonProperty("year")
    public void setYear(int value) {
        this.mYear = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mPaidOutCombined, 0);
        parcel.writeParcelable(this.mPendingCombined, 0);
        parcel.writeTypedList(this.mPaidOut);
        parcel.writeTypedList(this.mPending);
        parcel.writeTypedList(this.mCancellationFees);
        parcel.writeTypedList(this.mTotal);
        parcel.writeInt(this.mMonth);
        parcel.writeInt(this.mYear);
    }

    public void readFromParcel(Parcel source) {
        this.mPaidOutCombined = (CurrencyAmount) source.readParcelable(CurrencyAmount.class.getClassLoader());
        this.mPendingCombined = (CurrencyAmount) source.readParcelable(CurrencyAmount.class.getClassLoader());
        this.mPaidOut = source.createTypedArrayList(CurrencyAmount.CREATOR);
        this.mPending = source.createTypedArrayList(CurrencyAmount.CREATOR);
        this.mCancellationFees = source.createTypedArrayList(CurrencyAmount.CREATOR);
        this.mTotal = source.createTypedArrayList(CurrencyAmount.CREATOR);
        this.mMonth = source.readInt();
        this.mYear = source.readInt();
    }
}
