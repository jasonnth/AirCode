package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.CheckInTimeOption;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenListingCheckInTimeOptions implements Parcelable {
    @JsonProperty("valid_check_in_time_end_options")
    protected List<CheckInTimeOption> mValidCheckInTimeEndOptions;
    @JsonProperty("valid_check_in_time_start_options")
    protected List<CheckInTimeOption> mValidCheckInTimeStartOptions;
    @JsonProperty("valid_check_out_time_options")
    protected List<CheckInTimeOption> mValidCheckOutTimeOptions;

    protected GenListingCheckInTimeOptions(List<CheckInTimeOption> validCheckOutTimeOptions, List<CheckInTimeOption> validCheckInTimeStartOptions, List<CheckInTimeOption> validCheckInTimeEndOptions) {
        this();
        this.mValidCheckOutTimeOptions = validCheckOutTimeOptions;
        this.mValidCheckInTimeStartOptions = validCheckInTimeStartOptions;
        this.mValidCheckInTimeEndOptions = validCheckInTimeEndOptions;
    }

    protected GenListingCheckInTimeOptions() {
    }

    public List<CheckInTimeOption> getValidCheckOutTimeOptions() {
        return this.mValidCheckOutTimeOptions;
    }

    @JsonProperty("valid_check_out_time_options")
    public void setValidCheckOutTimeOptions(List<CheckInTimeOption> value) {
        this.mValidCheckOutTimeOptions = value;
    }

    public List<CheckInTimeOption> getValidCheckInTimeStartOptions() {
        return this.mValidCheckInTimeStartOptions;
    }

    @JsonProperty("valid_check_in_time_start_options")
    public void setValidCheckInTimeStartOptions(List<CheckInTimeOption> value) {
        this.mValidCheckInTimeStartOptions = value;
    }

    public List<CheckInTimeOption> getValidCheckInTimeEndOptions() {
        return this.mValidCheckInTimeEndOptions;
    }

    @JsonProperty("valid_check_in_time_end_options")
    public void setValidCheckInTimeEndOptions(List<CheckInTimeOption> value) {
        this.mValidCheckInTimeEndOptions = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mValidCheckOutTimeOptions);
        parcel.writeTypedList(this.mValidCheckInTimeStartOptions);
        parcel.writeTypedList(this.mValidCheckInTimeEndOptions);
    }

    public void readFromParcel(Parcel source) {
        this.mValidCheckOutTimeOptions = source.createTypedArrayList(CheckInTimeOption.CREATOR);
        this.mValidCheckInTimeStartOptions = source.createTypedArrayList(CheckInTimeOption.CREATOR);
        this.mValidCheckInTimeEndOptions = source.createTypedArrayList(CheckInTimeOption.CREATOR);
    }
}
