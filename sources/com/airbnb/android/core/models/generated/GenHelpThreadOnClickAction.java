package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.HelpThreadAmenity;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenHelpThreadOnClickAction implements Parcelable {
    @JsonProperty("amenities")
    protected List<HelpThreadAmenity> mAmenities;
    @JsonProperty("critical_amenities")
    protected List<HelpThreadAmenity> mCriticalAmenities;
    @JsonProperty("show")
    protected String mTypeKey;

    protected GenHelpThreadOnClickAction(List<HelpThreadAmenity> amenities, List<HelpThreadAmenity> criticalAmenities, String typeKey) {
        this();
        this.mAmenities = amenities;
        this.mCriticalAmenities = criticalAmenities;
        this.mTypeKey = typeKey;
    }

    protected GenHelpThreadOnClickAction() {
    }

    public List<HelpThreadAmenity> getAmenities() {
        return this.mAmenities;
    }

    @JsonProperty("amenities")
    public void setAmenities(List<HelpThreadAmenity> value) {
        this.mAmenities = value;
    }

    public List<HelpThreadAmenity> getCriticalAmenities() {
        return this.mCriticalAmenities;
    }

    @JsonProperty("critical_amenities")
    public void setCriticalAmenities(List<HelpThreadAmenity> value) {
        this.mCriticalAmenities = value;
    }

    public String getTypeKey() {
        return this.mTypeKey;
    }

    @JsonProperty("show")
    public void setTypeKey(String value) {
        this.mTypeKey = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mAmenities);
        parcel.writeTypedList(this.mCriticalAmenities);
        parcel.writeString(this.mTypeKey);
    }

    public void readFromParcel(Parcel source) {
        this.mAmenities = source.createTypedArrayList(HelpThreadAmenity.CREATOR);
        this.mCriticalAmenities = source.createTypedArrayList(HelpThreadAmenity.CREATOR);
        this.mTypeKey = source.readString();
    }
}
