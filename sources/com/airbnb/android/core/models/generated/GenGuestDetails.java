package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenGuestDetails implements Parcelable {
    @JsonProperty("bringing_pets")
    protected boolean mBringingPets;
    @JsonProperty("is_valid")
    protected boolean mIsValid;
    @JsonProperty("number_of_adults")
    protected int mNumberOfAdults;
    @JsonProperty("number_of_children")
    protected int mNumberOfChildren;
    @JsonProperty("number_of_infants")
    protected int mNumberOfInfants;

    protected GenGuestDetails(boolean bringingPets, boolean isValid, int numberOfAdults, int numberOfChildren, int numberOfInfants) {
        this();
        this.mBringingPets = bringingPets;
        this.mIsValid = isValid;
        this.mNumberOfAdults = numberOfAdults;
        this.mNumberOfChildren = numberOfChildren;
        this.mNumberOfInfants = numberOfInfants;
    }

    protected GenGuestDetails() {
    }

    public boolean isBringingPets() {
        return this.mBringingPets;
    }

    @JsonProperty("bringing_pets")
    public void setBringingPets(boolean value) {
        this.mBringingPets = value;
    }

    public boolean isValid() {
        return this.mIsValid;
    }

    @JsonProperty("is_valid")
    public void setIsValid(boolean value) {
        this.mIsValid = value;
    }

    public int getNumberOfAdults() {
        return this.mNumberOfAdults;
    }

    public int getNumberOfChildren() {
        return this.mNumberOfChildren;
    }

    @JsonProperty("number_of_children")
    public void setNumberOfChildren(int value) {
        this.mNumberOfChildren = value;
    }

    public int getNumberOfInfants() {
        return this.mNumberOfInfants;
    }

    @JsonProperty("number_of_infants")
    public void setNumberOfInfants(int value) {
        this.mNumberOfInfants = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeBooleanArray(new boolean[]{this.mBringingPets, this.mIsValid});
        parcel.writeInt(this.mNumberOfAdults);
        parcel.writeInt(this.mNumberOfChildren);
        parcel.writeInt(this.mNumberOfInfants);
    }

    public void readFromParcel(Parcel source) {
        boolean[] bools = source.createBooleanArray();
        this.mBringingPets = bools[0];
        this.mIsValid = bools[1];
        this.mNumberOfAdults = source.readInt();
        this.mNumberOfChildren = source.readInt();
        this.mNumberOfInfants = source.readInt();
    }
}
