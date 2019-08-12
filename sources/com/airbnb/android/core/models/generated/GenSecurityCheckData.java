package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.SecurityCheckPhoneNumber;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenSecurityCheckData implements Parcelable {
    @JsonProperty("phone_numbers")
    protected List<SecurityCheckPhoneNumber> mPhoneNumbers;

    protected GenSecurityCheckData(List<SecurityCheckPhoneNumber> phoneNumbers) {
        this();
        this.mPhoneNumbers = phoneNumbers;
    }

    protected GenSecurityCheckData() {
    }

    public List<SecurityCheckPhoneNumber> getPhoneNumbers() {
        return this.mPhoneNumbers;
    }

    @JsonProperty("phone_numbers")
    public void setPhoneNumbers(List<SecurityCheckPhoneNumber> value) {
        this.mPhoneNumbers = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mPhoneNumbers);
    }

    public void readFromParcel(Parcel source) {
        this.mPhoneNumbers = source.createTypedArrayList(SecurityCheckPhoneNumber.CREATOR);
    }
}
