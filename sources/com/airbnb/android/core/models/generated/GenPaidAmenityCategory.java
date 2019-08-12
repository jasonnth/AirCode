package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.paidamenities.enums.PaidAmenityServerType;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenPaidAmenityCategory implements Parcelable {
    @JsonProperty("amenity_type")
    protected PaidAmenityServerType mAmenityServerType;
    @JsonProperty("disclosure_text")
    protected String mDisclosureText;
    @JsonProperty("free_only")
    protected boolean mFreeOnly;

    protected GenPaidAmenityCategory(PaidAmenityServerType amenityServerType, String disclosureText, boolean freeOnly) {
        this();
        this.mAmenityServerType = amenityServerType;
        this.mDisclosureText = disclosureText;
        this.mFreeOnly = freeOnly;
    }

    protected GenPaidAmenityCategory() {
    }

    public PaidAmenityServerType getAmenityServerType() {
        return this.mAmenityServerType;
    }

    public String getDisclosureText() {
        return this.mDisclosureText;
    }

    @JsonProperty("disclosure_text")
    public void setDisclosureText(String value) {
        this.mDisclosureText = value;
    }

    public boolean isFreeOnly() {
        return this.mFreeOnly;
    }

    @JsonProperty("free_only")
    public void setFreeOnly(boolean value) {
        this.mFreeOnly = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mAmenityServerType, 0);
        parcel.writeString(this.mDisclosureText);
        parcel.writeBooleanArray(new boolean[]{this.mFreeOnly});
    }

    public void readFromParcel(Parcel source) {
        this.mAmenityServerType = (PaidAmenityServerType) source.readParcelable(PaidAmenityServerType.class.getClassLoader());
        this.mDisclosureText = source.readString();
        this.mFreeOnly = source.createBooleanArray()[0];
    }
}
