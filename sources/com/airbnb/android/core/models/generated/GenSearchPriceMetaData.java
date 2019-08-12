package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.businesstravel.models.BusinessTravelReadyData;
import com.airbnb.android.core.models.SearchPriceMetaData.PriceType;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSearchPriceMetaData implements Parcelable {
    @JsonProperty("business_travel_ready_data")
    protected BusinessTravelReadyData mBusinessTravelReadyData;
    @JsonProperty("native_currency")
    protected String mCurrencyType;
    @JsonProperty("price_range_max_native")
    protected int mMaxFilterPrice;
    @JsonProperty("price_range_min_native")
    protected int mMinFilterPrice;
    @JsonProperty("price_type")
    protected PriceType mPriceType;
    @JsonProperty("search_id")
    protected String mSearchId;
    @JsonProperty("mobile_session_id")
    protected String mSearchSessionId;

    protected GenSearchPriceMetaData(BusinessTravelReadyData businessTravelReadyData, PriceType priceType, String currencyType, String searchSessionId, String searchId, int minFilterPrice, int maxFilterPrice) {
        this();
        this.mBusinessTravelReadyData = businessTravelReadyData;
        this.mPriceType = priceType;
        this.mCurrencyType = currencyType;
        this.mSearchSessionId = searchSessionId;
        this.mSearchId = searchId;
        this.mMinFilterPrice = minFilterPrice;
        this.mMaxFilterPrice = maxFilterPrice;
    }

    protected GenSearchPriceMetaData() {
    }

    public BusinessTravelReadyData getBusinessTravelReadyData() {
        return this.mBusinessTravelReadyData;
    }

    @JsonProperty("business_travel_ready_data")
    public void setBusinessTravelReadyData(BusinessTravelReadyData value) {
        this.mBusinessTravelReadyData = value;
    }

    public PriceType getPriceType() {
        return this.mPriceType;
    }

    public String getCurrencyType() {
        return this.mCurrencyType;
    }

    @JsonProperty("native_currency")
    public void setCurrencyType(String value) {
        this.mCurrencyType = value;
    }

    public String getSearchSessionId() {
        return this.mSearchSessionId;
    }

    @JsonProperty("mobile_session_id")
    public void setSearchSessionId(String value) {
        this.mSearchSessionId = value;
    }

    public String getSearchId() {
        return this.mSearchId;
    }

    @JsonProperty("search_id")
    public void setSearchId(String value) {
        this.mSearchId = value;
    }

    public int getMinFilterPrice() {
        return this.mMinFilterPrice;
    }

    @JsonProperty("price_range_min_native")
    public void setMinFilterPrice(int value) {
        this.mMinFilterPrice = value;
    }

    public int getMaxFilterPrice() {
        return this.mMaxFilterPrice;
    }

    @JsonProperty("price_range_max_native")
    public void setMaxFilterPrice(int value) {
        this.mMaxFilterPrice = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mBusinessTravelReadyData, 0);
        parcel.writeSerializable(this.mPriceType);
        parcel.writeString(this.mCurrencyType);
        parcel.writeString(this.mSearchSessionId);
        parcel.writeString(this.mSearchId);
        parcel.writeInt(this.mMinFilterPrice);
        parcel.writeInt(this.mMaxFilterPrice);
    }

    public void readFromParcel(Parcel source) {
        this.mBusinessTravelReadyData = (BusinessTravelReadyData) source.readParcelable(BusinessTravelReadyData.class.getClassLoader());
        this.mPriceType = (PriceType) source.readSerializable();
        this.mCurrencyType = source.readString();
        this.mSearchSessionId = source.readString();
        this.mSearchId = source.readString();
        this.mMinFilterPrice = source.readInt();
        this.mMaxFilterPrice = source.readInt();
    }
}
