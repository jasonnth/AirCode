package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenExperimentMetadata implements Parcelable {
    @JsonProperty("experiment")
    protected String mExperiment;
    @JsonProperty("treatment")
    protected String mTreatment;

    protected GenExperimentMetadata(String experiment, String treatment) {
        this();
        this.mExperiment = experiment;
        this.mTreatment = treatment;
    }

    protected GenExperimentMetadata() {
    }

    public String getExperiment() {
        return this.mExperiment;
    }

    @JsonProperty("experiment")
    public void setExperiment(String value) {
        this.mExperiment = value;
    }

    public String getTreatment() {
        return this.mTreatment;
    }

    @JsonProperty("treatment")
    public void setTreatment(String value) {
        this.mTreatment = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mExperiment);
        parcel.writeString(this.mTreatment);
    }

    public void readFromParcel(Parcel source) {
        this.mExperiment = source.readString();
        this.mTreatment = source.readString();
    }
}
