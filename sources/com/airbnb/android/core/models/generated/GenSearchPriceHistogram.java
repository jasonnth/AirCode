package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenSearchPriceHistogram implements Parcelable {
    @JsonProperty("average_price")
    protected int mAveragePrice;
    @JsonProperty("histogram")
    protected List<Integer> mHistogram;
    @JsonProperty("symbol_ranges")
    protected List<Integer> mSymbolRanges;

    protected GenSearchPriceHistogram(List<Integer> histogram, List<Integer> symbolRanges, int averagePrice) {
        this();
        this.mHistogram = histogram;
        this.mSymbolRanges = symbolRanges;
        this.mAveragePrice = averagePrice;
    }

    protected GenSearchPriceHistogram() {
    }

    public List<Integer> getHistogram() {
        return this.mHistogram;
    }

    @JsonProperty("histogram")
    public void setHistogram(List<Integer> value) {
        this.mHistogram = value;
    }

    public List<Integer> getSymbolRanges() {
        return this.mSymbolRanges;
    }

    @JsonProperty("symbol_ranges")
    public void setSymbolRanges(List<Integer> value) {
        this.mSymbolRanges = value;
    }

    public int getAveragePrice() {
        return this.mAveragePrice;
    }

    @JsonProperty("average_price")
    public void setAveragePrice(int value) {
        this.mAveragePrice = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(this.mHistogram);
        parcel.writeValue(this.mSymbolRanges);
        parcel.writeInt(this.mAveragePrice);
    }

    public void readFromParcel(Parcel source) {
        this.mHistogram = (List) source.readValue(null);
        this.mSymbolRanges = (List) source.readValue(null);
        this.mAveragePrice = source.readInt();
    }
}
