package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.CheckinTimeSelectionOptions;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenArrivalDetails implements Parcelable {
    @JsonProperty("additional_checkin_details_message")
    protected String mAdditionalCheckinDetailsMessage;
    @JsonProperty("checkin_time_selection_options")
    protected List<CheckinTimeSelectionOptions> mCheckinTimeSelectionOptions;
    @JsonProperty("guest_checkin_time_from")
    protected CheckinTimeSelectionOptions mGuestCheckinTimeFrom;
    @JsonProperty("guest_checkin_time_to")
    protected CheckinTimeSelectionOptions mGuestCheckinTimeTo;
    @JsonProperty("is_bringing_pets")
    protected boolean mIsBringingPets;
    @JsonProperty("number_of_adults")
    protected int mNumberOfAdults;
    @JsonProperty("number_of_children")
    protected int mNumberOfChildren;
    @JsonProperty("number_of_infants")
    protected int mNumberOfInfants;
    @JsonProperty("valid_checkin_time_selection_options")
    protected List<CheckinTimeSelectionOptions> mValidCheckinTimeSelectionOptions;

    protected GenArrivalDetails(CheckinTimeSelectionOptions guestCheckinTimeFrom, CheckinTimeSelectionOptions guestCheckinTimeTo, List<CheckinTimeSelectionOptions> checkinTimeSelectionOptions, List<CheckinTimeSelectionOptions> validCheckinTimeSelectionOptions, String additionalCheckinDetailsMessage, boolean isBringingPets, int numberOfAdults, int numberOfChildren, int numberOfInfants) {
        this();
        this.mGuestCheckinTimeFrom = guestCheckinTimeFrom;
        this.mGuestCheckinTimeTo = guestCheckinTimeTo;
        this.mCheckinTimeSelectionOptions = checkinTimeSelectionOptions;
        this.mValidCheckinTimeSelectionOptions = validCheckinTimeSelectionOptions;
        this.mAdditionalCheckinDetailsMessage = additionalCheckinDetailsMessage;
        this.mIsBringingPets = isBringingPets;
        this.mNumberOfAdults = numberOfAdults;
        this.mNumberOfChildren = numberOfChildren;
        this.mNumberOfInfants = numberOfInfants;
    }

    protected GenArrivalDetails() {
    }

    public CheckinTimeSelectionOptions getGuestCheckinTimeFrom() {
        return this.mGuestCheckinTimeFrom;
    }

    @JsonProperty("guest_checkin_time_from")
    public void setGuestCheckinTimeFrom(CheckinTimeSelectionOptions value) {
        this.mGuestCheckinTimeFrom = value;
    }

    public CheckinTimeSelectionOptions getGuestCheckinTimeTo() {
        return this.mGuestCheckinTimeTo;
    }

    @JsonProperty("guest_checkin_time_to")
    public void setGuestCheckinTimeTo(CheckinTimeSelectionOptions value) {
        this.mGuestCheckinTimeTo = value;
    }

    public List<CheckinTimeSelectionOptions> getCheckinTimeSelectionOptions() {
        return this.mCheckinTimeSelectionOptions;
    }

    @JsonProperty("checkin_time_selection_options")
    public void setCheckinTimeSelectionOptions(List<CheckinTimeSelectionOptions> value) {
        this.mCheckinTimeSelectionOptions = value;
    }

    public List<CheckinTimeSelectionOptions> getValidCheckinTimeSelectionOptions() {
        return this.mValidCheckinTimeSelectionOptions;
    }

    @JsonProperty("valid_checkin_time_selection_options")
    public void setValidCheckinTimeSelectionOptions(List<CheckinTimeSelectionOptions> value) {
        this.mValidCheckinTimeSelectionOptions = value;
    }

    public String getAdditionalCheckinDetailsMessage() {
        return this.mAdditionalCheckinDetailsMessage;
    }

    @JsonProperty("additional_checkin_details_message")
    public void setAdditionalCheckinDetailsMessage(String value) {
        this.mAdditionalCheckinDetailsMessage = value;
    }

    public boolean isBringingPets() {
        return this.mIsBringingPets;
    }

    @JsonProperty("is_bringing_pets")
    public void setIsBringingPets(boolean value) {
        this.mIsBringingPets = value;
    }

    public int getNumberOfAdults() {
        return this.mNumberOfAdults;
    }

    @JsonProperty("number_of_adults")
    public void setNumberOfAdults(int value) {
        this.mNumberOfAdults = value;
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
        parcel.writeParcelable(this.mGuestCheckinTimeFrom, 0);
        parcel.writeParcelable(this.mGuestCheckinTimeTo, 0);
        parcel.writeTypedList(this.mCheckinTimeSelectionOptions);
        parcel.writeTypedList(this.mValidCheckinTimeSelectionOptions);
        parcel.writeString(this.mAdditionalCheckinDetailsMessage);
        parcel.writeBooleanArray(new boolean[]{this.mIsBringingPets});
        parcel.writeInt(this.mNumberOfAdults);
        parcel.writeInt(this.mNumberOfChildren);
        parcel.writeInt(this.mNumberOfInfants);
    }

    public void readFromParcel(Parcel source) {
        this.mGuestCheckinTimeFrom = (CheckinTimeSelectionOptions) source.readParcelable(CheckinTimeSelectionOptions.class.getClassLoader());
        this.mGuestCheckinTimeTo = (CheckinTimeSelectionOptions) source.readParcelable(CheckinTimeSelectionOptions.class.getClassLoader());
        this.mCheckinTimeSelectionOptions = source.createTypedArrayList(CheckinTimeSelectionOptions.CREATOR);
        this.mValidCheckinTimeSelectionOptions = source.createTypedArrayList(CheckinTimeSelectionOptions.CREATOR);
        this.mAdditionalCheckinDetailsMessage = source.readString();
        this.mIsBringingPets = source.createBooleanArray()[0];
        this.mNumberOfAdults = source.readInt();
        this.mNumberOfChildren = source.readInt();
        this.mNumberOfInfants = source.readInt();
    }
}
