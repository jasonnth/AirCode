package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenCurrency implements Parcelable {
    @JsonProperty("code")
    protected String mCode;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("symbol")
    protected String mSymbol;
    @JsonProperty("unicode_symbol")
    protected String mUnicodeSymbol;

    protected GenCurrency(String code, String name, String symbol, String unicodeSymbol) {
        this();
        this.mCode = code;
        this.mName = name;
        this.mSymbol = symbol;
        this.mUnicodeSymbol = unicodeSymbol;
    }

    protected GenCurrency() {
    }

    public String getCode() {
        return this.mCode;
    }

    @JsonProperty("code")
    public void setCode(String value) {
        this.mCode = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getSymbol() {
        return this.mSymbol;
    }

    @JsonProperty("symbol")
    public void setSymbol(String value) {
        this.mSymbol = value;
    }

    public String getUnicodeSymbol() {
        return this.mUnicodeSymbol;
    }

    @JsonProperty("unicode_symbol")
    public void setUnicodeSymbol(String value) {
        this.mUnicodeSymbol = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mCode);
        parcel.writeString(this.mName);
        parcel.writeString(this.mSymbol);
        parcel.writeString(this.mUnicodeSymbol);
    }

    public void readFromParcel(Parcel source) {
        this.mCode = source.readString();
        this.mName = source.readString();
        this.mSymbol = source.readString();
        this.mUnicodeSymbol = source.readString();
    }
}
