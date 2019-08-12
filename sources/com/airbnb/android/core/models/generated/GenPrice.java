package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.CurrencyAmount;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.models.Price.Type;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenPrice implements Parcelable {
    @JsonProperty("localized_explanation")
    protected String mLocalizedExplanation;
    @JsonProperty("localized_title")
    protected String mLocalizedTitle;
    @JsonProperty("localized_url_text")
    protected String mLocalizedUrlText;
    @JsonProperty("price_items")
    protected List<Price> mPriceItems;
    @JsonProperty("total")
    protected CurrencyAmount mTotal;
    @JsonProperty("type")
    protected Type mType;
    @JsonProperty("url")
    protected String mUrl;

    protected GenPrice(CurrencyAmount total, List<Price> priceItems, String localizedExplanation, String localizedTitle, String localizedUrlText, String url, Type type) {
        this();
        this.mTotal = total;
        this.mPriceItems = priceItems;
        this.mLocalizedExplanation = localizedExplanation;
        this.mLocalizedTitle = localizedTitle;
        this.mLocalizedUrlText = localizedUrlText;
        this.mUrl = url;
        this.mType = type;
    }

    protected GenPrice() {
    }

    public CurrencyAmount getTotal() {
        return this.mTotal;
    }

    @JsonProperty("total")
    public void setTotal(CurrencyAmount value) {
        this.mTotal = value;
    }

    public List<Price> getPriceItems() {
        return this.mPriceItems;
    }

    @JsonProperty("price_items")
    public void setPriceItems(List<Price> value) {
        this.mPriceItems = value;
    }

    public String getLocalizedExplanation() {
        return this.mLocalizedExplanation;
    }

    @JsonProperty("localized_explanation")
    public void setLocalizedExplanation(String value) {
        this.mLocalizedExplanation = value;
    }

    public String getLocalizedTitle() {
        return this.mLocalizedTitle;
    }

    @JsonProperty("localized_title")
    public void setLocalizedTitle(String value) {
        this.mLocalizedTitle = value;
    }

    public String getLocalizedUrlText() {
        return this.mLocalizedUrlText;
    }

    @JsonProperty("localized_url_text")
    public void setLocalizedUrlText(String value) {
        this.mLocalizedUrlText = value;
    }

    public String getUrl() {
        return this.mUrl;
    }

    @JsonProperty("url")
    public void setUrl(String value) {
        this.mUrl = value;
    }

    public Type getType() {
        return this.mType;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mTotal, 0);
        parcel.writeTypedList(this.mPriceItems);
        parcel.writeString(this.mLocalizedExplanation);
        parcel.writeString(this.mLocalizedTitle);
        parcel.writeString(this.mLocalizedUrlText);
        parcel.writeString(this.mUrl);
        parcel.writeSerializable(this.mType);
    }

    public void readFromParcel(Parcel source) {
        this.mTotal = (CurrencyAmount) source.readParcelable(CurrencyAmount.class.getClassLoader());
        this.mPriceItems = source.createTypedArrayList(Price.CREATOR);
        this.mLocalizedExplanation = source.readString();
        this.mLocalizedTitle = source.readString();
        this.mLocalizedUrlText = source.readString();
        this.mUrl = source.readString();
        this.mType = (Type) source.readSerializable();
    }
}
