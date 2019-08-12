package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.SecurityCheckData;
import com.airbnb.android.core.models.SecurityCheckRequirements;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenSecurityCheck implements Parcelable {
    @JsonProperty("data")
    protected SecurityCheckData mData;
    @JsonProperty("requirements")
    protected SecurityCheckRequirements mRequirements;
    @JsonProperty("satisfied")
    protected boolean mSatisfied;
    @JsonProperty("strategy")
    protected String mStrategy;

    protected GenSecurityCheck(SecurityCheckData data, SecurityCheckRequirements requirements, String strategy, boolean satisfied) {
        this();
        this.mData = data;
        this.mRequirements = requirements;
        this.mStrategy = strategy;
        this.mSatisfied = satisfied;
    }

    protected GenSecurityCheck() {
    }

    public SecurityCheckData getData() {
        return this.mData;
    }

    @JsonProperty("data")
    public void setData(SecurityCheckData value) {
        this.mData = value;
    }

    public SecurityCheckRequirements getRequirements() {
        return this.mRequirements;
    }

    @JsonProperty("requirements")
    public void setRequirements(SecurityCheckRequirements value) {
        this.mRequirements = value;
    }

    public String getStrategy() {
        return this.mStrategy;
    }

    @JsonProperty("strategy")
    public void setStrategy(String value) {
        this.mStrategy = value;
    }

    public boolean isSatisfied() {
        return this.mSatisfied;
    }

    @JsonProperty("satisfied")
    public void setSatisfied(boolean value) {
        this.mSatisfied = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mData, 0);
        parcel.writeParcelable(this.mRequirements, 0);
        parcel.writeString(this.mStrategy);
        parcel.writeBooleanArray(new boolean[]{this.mSatisfied});
    }

    public void readFromParcel(Parcel source) {
        this.mData = (SecurityCheckData) source.readParcelable(SecurityCheckData.class.getClassLoader());
        this.mRequirements = (SecurityCheckRequirements) source.readParcelable(SecurityCheckRequirements.class.getClassLoader());
        this.mStrategy = source.readString();
        this.mSatisfied = source.createBooleanArray()[0];
    }
}
