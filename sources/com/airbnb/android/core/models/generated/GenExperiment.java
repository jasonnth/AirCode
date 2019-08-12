package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenExperiment implements Parcelable {
    @JsonProperty("assigned_treatment")
    protected String mAssignedTreatment;
    @JsonProperty("experiment_name")
    protected String mExperimentName;
    @JsonProperty("holdout_name")
    protected String mHoldoutName;
    @JsonProperty("subject")
    protected String mSubject;
    @JsonProperty("treatments")
    protected List<String> mTreatments;
    @JsonProperty("version")
    protected int mVersion;

    protected GenExperiment(List<String> treatments, String experimentName, String assignedTreatment, String subject, String holdoutName, int version) {
        this();
        this.mTreatments = treatments;
        this.mExperimentName = experimentName;
        this.mAssignedTreatment = assignedTreatment;
        this.mSubject = subject;
        this.mHoldoutName = holdoutName;
        this.mVersion = version;
    }

    protected GenExperiment() {
    }

    public List<String> getTreatments() {
        return this.mTreatments;
    }

    @JsonProperty("treatments")
    public void setTreatments(List<String> value) {
        this.mTreatments = value;
    }

    public String getExperimentName() {
        return this.mExperimentName;
    }

    @JsonProperty("experiment_name")
    public void setExperimentName(String value) {
        this.mExperimentName = value;
    }

    public String getAssignedTreatment() {
        return this.mAssignedTreatment;
    }

    @JsonProperty("assigned_treatment")
    public void setAssignedTreatment(String value) {
        this.mAssignedTreatment = value;
    }

    public String getSubject() {
        return this.mSubject;
    }

    @JsonProperty("subject")
    public void setSubject(String value) {
        this.mSubject = value;
    }

    public String getHoldoutName() {
        return this.mHoldoutName;
    }

    @JsonProperty("holdout_name")
    public void setHoldoutName(String value) {
        this.mHoldoutName = value;
    }

    public int getVersion() {
        return this.mVersion;
    }

    @JsonProperty("version")
    public void setVersion(int value) {
        this.mVersion = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeStringList(this.mTreatments);
        parcel.writeString(this.mExperimentName);
        parcel.writeString(this.mAssignedTreatment);
        parcel.writeString(this.mSubject);
        parcel.writeString(this.mHoldoutName);
        parcel.writeInt(this.mVersion);
    }

    public void readFromParcel(Parcel source) {
        this.mTreatments = source.createStringArrayList();
        this.mExperimentName = source.readString();
        this.mAssignedTreatment = source.readString();
        this.mSubject = source.readString();
        this.mHoldoutName = source.readString();
        this.mVersion = source.readInt();
    }
}
