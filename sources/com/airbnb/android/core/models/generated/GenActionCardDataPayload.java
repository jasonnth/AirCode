package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.ActionCardMonthlyMarketDemand;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenActionCardDataPayload implements Parcelable {
    @JsonProperty("adoption_percentage")
    protected int mAdoptionPercentage;
    @JsonProperty("booking_change_percentage")
    protected int mBookingChangePercentage;
    @JsonProperty("date_range")
    protected List<AirDate> mDateRange;
    @JsonProperty("discount_percentage")
    protected int mDiscountPercentage;
    @JsonProperty("month")
    protected AirDate mMonth;
    @JsonProperty("monthly_market_demand")
    protected List<ActionCardMonthlyMarketDemand> mMonthlyMarketDemand;
    @JsonProperty("price")
    protected int mPrice;
    @JsonProperty("revenue")
    protected int mRevenue;
    @JsonProperty("search_change_percentage")
    protected int mSearchChangePercentage;

    protected GenActionCardDataPayload(AirDate month, List<ActionCardMonthlyMarketDemand> monthlyMarketDemand, List<AirDate> dateRange, int revenue, int discountPercentage, int bookingChangePercentage, int price, int adoptionPercentage, int searchChangePercentage) {
        this();
        this.mMonth = month;
        this.mMonthlyMarketDemand = monthlyMarketDemand;
        this.mDateRange = dateRange;
        this.mRevenue = revenue;
        this.mDiscountPercentage = discountPercentage;
        this.mBookingChangePercentage = bookingChangePercentage;
        this.mPrice = price;
        this.mAdoptionPercentage = adoptionPercentage;
        this.mSearchChangePercentage = searchChangePercentage;
    }

    protected GenActionCardDataPayload() {
    }

    public AirDate getMonth() {
        return this.mMonth;
    }

    @JsonProperty("month")
    public void setMonth(AirDate value) {
        this.mMonth = value;
    }

    public List<ActionCardMonthlyMarketDemand> getMonthlyMarketDemand() {
        return this.mMonthlyMarketDemand;
    }

    public List<AirDate> getDateRange() {
        return this.mDateRange;
    }

    @JsonProperty("date_range")
    public void setDateRange(List<AirDate> value) {
        this.mDateRange = value;
    }

    public int getRevenue() {
        return this.mRevenue;
    }

    @JsonProperty("revenue")
    public void setRevenue(int value) {
        this.mRevenue = value;
    }

    public int getDiscountPercentage() {
        return this.mDiscountPercentage;
    }

    @JsonProperty("discount_percentage")
    public void setDiscountPercentage(int value) {
        this.mDiscountPercentage = value;
    }

    public int getBookingChangePercentage() {
        return this.mBookingChangePercentage;
    }

    @JsonProperty("booking_change_percentage")
    public void setBookingChangePercentage(int value) {
        this.mBookingChangePercentage = value;
    }

    public int getPrice() {
        return this.mPrice;
    }

    @JsonProperty("price")
    public void setPrice(int value) {
        this.mPrice = value;
    }

    public int getAdoptionPercentage() {
        return this.mAdoptionPercentage;
    }

    @JsonProperty("adoption_percentage")
    public void setAdoptionPercentage(int value) {
        this.mAdoptionPercentage = value;
    }

    public int getSearchChangePercentage() {
        return this.mSearchChangePercentage;
    }

    @JsonProperty("search_change_percentage")
    public void setSearchChangePercentage(int value) {
        this.mSearchChangePercentage = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mMonth, 0);
        parcel.writeTypedList(this.mMonthlyMarketDemand);
        parcel.writeTypedList(this.mDateRange);
        parcel.writeInt(this.mRevenue);
        parcel.writeInt(this.mDiscountPercentage);
        parcel.writeInt(this.mBookingChangePercentage);
        parcel.writeInt(this.mPrice);
        parcel.writeInt(this.mAdoptionPercentage);
        parcel.writeInt(this.mSearchChangePercentage);
    }

    public void readFromParcel(Parcel source) {
        this.mMonth = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mMonthlyMarketDemand = source.createTypedArrayList(ActionCardMonthlyMarketDemand.CREATOR);
        this.mDateRange = source.createTypedArrayList(AirDate.CREATOR);
        this.mRevenue = source.readInt();
        this.mDiscountPercentage = source.readInt();
        this.mBookingChangePercentage = source.readInt();
        this.mPrice = source.readInt();
        this.mAdoptionPercentage = source.readInt();
        this.mSearchChangePercentage = source.readInt();
    }
}
