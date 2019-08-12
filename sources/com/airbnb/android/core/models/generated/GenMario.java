package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenMario implements Parcelable {
    @JsonProperty("assigned_treatment")
    protected String mAssigned_treatment;
    @JsonProperty("description")
    protected String mDescription;
    @JsonProperty("experiment_name")
    protected String mExperiment_name;
    @JsonProperty("group_name")
    protected String mGroupName;
    @JsonProperty("parameter_name")
    protected String mParameterName;
    @JsonProperty("value")
    protected String mValue;
    @JsonProperty("version")
    protected String mVersion;

    protected GenMario(String groupName, String parameterName, String description, String version, String value, String experiment_name, String assigned_treatment) {
        this();
        this.mGroupName = groupName;
        this.mParameterName = parameterName;
        this.mDescription = description;
        this.mVersion = version;
        this.mValue = value;
        this.mExperiment_name = experiment_name;
        this.mAssigned_treatment = assigned_treatment;
    }

    protected GenMario() {
    }

    public String getGroupName() {
        return this.mGroupName;
    }

    @JsonProperty("group_name")
    public void setGroupName(String value) {
        this.mGroupName = value;
    }

    public String getParameterName() {
        return this.mParameterName;
    }

    @JsonProperty("parameter_name")
    public void setParameterName(String value) {
        this.mParameterName = value;
    }

    public String getDescription() {
        return this.mDescription;
    }

    @JsonProperty("description")
    public void setDescription(String value) {
        this.mDescription = value;
    }

    public String getVersion() {
        return this.mVersion;
    }

    @JsonProperty("version")
    public void setVersion(String value) {
        this.mVersion = value;
    }

    public String getValue() {
        return this.mValue;
    }

    @JsonProperty("value")
    public void setValue(String value) {
        this.mValue = value;
    }

    public String getExperiment_name() {
        return this.mExperiment_name;
    }

    @JsonProperty("experiment_name")
    public void setExperiment_name(String value) {
        this.mExperiment_name = value;
    }

    public String getAssigned_treatment() {
        return this.mAssigned_treatment;
    }

    @JsonProperty("assigned_treatment")
    public void setAssigned_treatment(String value) {
        this.mAssigned_treatment = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mGroupName);
        parcel.writeString(this.mParameterName);
        parcel.writeString(this.mDescription);
        parcel.writeString(this.mVersion);
        parcel.writeString(this.mValue);
        parcel.writeString(this.mExperiment_name);
        parcel.writeString(this.mAssigned_treatment);
    }

    public void readFromParcel(Parcel source) {
        this.mGroupName = source.readString();
        this.mParameterName = source.readString();
        this.mDescription = source.readString();
        this.mVersion = source.readString();
        this.mValue = source.readString();
        this.mExperiment_name = source.readString();
        this.mAssigned_treatment = source.readString();
    }
}
