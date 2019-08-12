package com.airbnb.android.core.presenters;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionViewItemPresenter;

public class CountryCodeItem implements Parcelable, SelectionViewItemPresenter, Comparable<CountryCodeItem> {
    public static final Creator CREATOR = new Creator() {
        public CountryCodeItem createFromParcel(Parcel in) {
            return new CountryCodeItem(in);
        }

        public CountryCodeItem[] newArray(int size) {
            return new CountryCodeItem[size];
        }
    };
    private final String callingCode;
    private final String countryCode;
    private final String displayCountryName;

    public CountryCodeItem(String callingCode2, String countryCode2, String displayCountryName2) {
        this.callingCode = callingCode2;
        this.countryCode = countryCode2;
        this.displayCountryName = displayCountryName2;
    }

    public CountryCodeItem(Parcel source) {
        this.callingCode = source.readString();
        this.countryCode = source.readString();
        this.displayCountryName = source.readString();
    }

    public String getCallingCode() {
        return this.callingCode;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public String getDisplayCountryName() {
        return this.displayCountryName;
    }

    public String getDisplayText(Context context) {
        return this.displayCountryName;
    }

    public int compareTo(CountryCodeItem another) {
        return this.displayCountryName.compareTo(another.displayCountryName);
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.callingCode);
        dest.writeString(this.countryCode);
        dest.writeString(this.displayCountryName);
    }
}
