package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSecurityCheckRequirements implements Parcelable {
    @JsonProperty("add_payout")
    protected boolean mAddPayout;
    @JsonProperty("manage_listing")
    protected boolean mManageListing;

    protected GenSecurityCheckRequirements(boolean manageListing, boolean addPayout) {
        this();
        this.mManageListing = manageListing;
        this.mAddPayout = addPayout;
    }

    protected GenSecurityCheckRequirements() {
    }

    public boolean isManageListing() {
        return this.mManageListing;
    }

    @JsonProperty("manage_listing")
    public void setManageListing(boolean value) {
        this.mManageListing = value;
    }

    public boolean isAddPayout() {
        return this.mAddPayout;
    }

    @JsonProperty("add_payout")
    public void setAddPayout(boolean value) {
        this.mAddPayout = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeBooleanArray(new boolean[]{this.mManageListing, this.mAddPayout});
    }

    public void readFromParcel(Parcel source) {
        boolean[] bools = source.createBooleanArray();
        this.mManageListing = bools[0];
        this.mAddPayout = bools[1];
    }
}
