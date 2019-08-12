package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenPassportInformation implements Parcelable {
    @JsonProperty("booker")
    protected boolean mBooker;
    @JsonProperty("given_names")
    protected String mGivenNames;
    @JsonProperty("id")
    protected int mId;
    @JsonProperty("censored_id_number")
    protected String mIdentificationNumber;
    @JsonProperty("localized_date_of_birth")
    protected String mLocalizedDateOfBirth;
    @JsonProperty("selected")
    protected boolean mSelected;
    @JsonProperty("surname")
    protected String mSurname;

    protected GenPassportInformation(String surname, String givenNames, String identificationNumber, String localizedDateOfBirth, boolean selected, boolean booker, int id) {
        this();
        this.mSurname = surname;
        this.mGivenNames = givenNames;
        this.mIdentificationNumber = identificationNumber;
        this.mLocalizedDateOfBirth = localizedDateOfBirth;
        this.mSelected = selected;
        this.mBooker = booker;
        this.mId = id;
    }

    protected GenPassportInformation() {
    }

    public String getSurname() {
        return this.mSurname;
    }

    @JsonProperty("surname")
    public void setSurname(String value) {
        this.mSurname = value;
    }

    public String getGivenNames() {
        return this.mGivenNames;
    }

    @JsonProperty("given_names")
    public void setGivenNames(String value) {
        this.mGivenNames = value;
    }

    public String getIdentificationNumber() {
        return this.mIdentificationNumber;
    }

    @JsonProperty("censored_id_number")
    public void setIdentificationNumber(String value) {
        this.mIdentificationNumber = value;
    }

    public String getLocalizedDateOfBirth() {
        return this.mLocalizedDateOfBirth;
    }

    @JsonProperty("localized_date_of_birth")
    public void setLocalizedDateOfBirth(String value) {
        this.mLocalizedDateOfBirth = value;
    }

    public boolean isSelected() {
        return this.mSelected;
    }

    public boolean isBooker() {
        return this.mBooker;
    }

    public int getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(int value) {
        this.mId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mSurname);
        parcel.writeString(this.mGivenNames);
        parcel.writeString(this.mIdentificationNumber);
        parcel.writeString(this.mLocalizedDateOfBirth);
        parcel.writeBooleanArray(new boolean[]{this.mSelected, this.mBooker});
        parcel.writeInt(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mSurname = source.readString();
        this.mGivenNames = source.readString();
        this.mIdentificationNumber = source.readString();
        this.mLocalizedDateOfBirth = source.readString();
        boolean[] bools = source.createBooleanArray();
        this.mSelected = bools[0];
        this.mBooker = bools[1];
        this.mId = source.readInt();
    }
}
