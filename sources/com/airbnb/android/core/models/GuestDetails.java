package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenGuestDetails;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GuestDetails extends GenGuestDetails {
    public static final Creator<GuestDetails> CREATOR = new Creator<GuestDetails>() {
        public GuestDetails[] newArray(int size) {
            return new GuestDetails[size];
        }

        public GuestDetails createFromParcel(Parcel source) {
            GuestDetails object = new GuestDetails();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final boolean DEFAULT_IS_BRINGING_PETS = false;
    public static final int DEFAULT_NUM_ADULTS = 1;
    public static final int DEFAULT_NUM_CHILDREN = 0;
    public static final int DEFAULT_NUM_INFANTS = 0;

    public static GuestDetails newInstance() {
        return new GuestDetails().adultsCount(defaultNumAdults());
    }

    public static int defaultNumAdults() {
        return 1;
    }

    public static int minNumAdults() {
        return 1;
    }

    public GuestDetails() {
        adultsCount(1);
        childrenCount(0);
        infantsCount(0);
        isBringingPets(false);
    }

    public int totalGuestCount() {
        return adultsCount() + childrenCount();
    }

    public GuestDetails isBringingPets(boolean hasPets) {
        setBringingPets(hasPets);
        return this;
    }

    public int adultsCount() {
        return getNumberOfAdults();
    }

    @JsonProperty("number_of_adults")
    public GuestDetails adultsCount(int adultsCount) {
        this.mIsValid = adultsCount > 0;
        this.mNumberOfAdults = adultsCount;
        return this;
    }

    public GuestDetails reservationDetails(ReservationDetails reservationDetails) {
        adultsCount(reservationDetails.numberOfAdults().intValue());
        childrenCount(reservationDetails.numberOfChildren().intValue());
        infantsCount(reservationDetails.numberOfInfants().intValue());
        isBringingPets(reservationDetails.isBringingPets().booleanValue());
        return this;
    }

    public int childrenCount() {
        return getNumberOfChildren();
    }

    public boolean hasChildren() {
        return getNumberOfChildren() > 0;
    }

    public GuestDetails childrenCount(int childrenCount) {
        setNumberOfChildren(childrenCount);
        return this;
    }

    public boolean hasInfants() {
        return getNumberOfInfants() > 0;
    }

    public int infantsCount() {
        return getNumberOfInfants();
    }

    public GuestDetails infantsCount(int infantsCount) {
        setNumberOfInfants(infantsCount);
        return this;
    }

    public void clear() {
        adultsCount(1);
        childrenCount(0);
        infantsCount(0);
        isBringingPets(false);
        this.mIsValid = true;
    }

    public boolean equals(Object o) {
        boolean z = true;
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GuestDetails that = (GuestDetails) o;
        if (this.mBringingPets != that.isBringingPets() || this.mNumberOfAdults != that.getNumberOfAdults() || this.mNumberOfChildren != that.getNumberOfChildren()) {
            return false;
        }
        if (this.mNumberOfInfants != that.getNumberOfInfants()) {
            z = false;
        }
        return z;
    }

    public int hashCode() {
        return ((((((this.mBringingPets ? 1 : 0) * 31) + this.mNumberOfAdults) * 31) + this.mNumberOfChildren) * 31) + this.mNumberOfInfants;
    }
}
