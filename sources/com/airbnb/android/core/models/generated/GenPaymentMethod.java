package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.businesstravel.models.BusinessEntityGroup;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenPaymentMethod implements Parcelable {
    @JsonProperty("business_entity_group")
    protected BusinessEntityGroup mBusinessEntityGroup;
    @JsonProperty("cc_type")
    protected String mCc_type;
    @JsonProperty("name")
    protected String mName;
    @JsonProperty("type")
    protected String mType;

    protected GenPaymentMethod(BusinessEntityGroup businessEntityGroup, String cc_type, String name, String type) {
        this();
        this.mBusinessEntityGroup = businessEntityGroup;
        this.mCc_type = cc_type;
        this.mName = name;
        this.mType = type;
    }

    protected GenPaymentMethod() {
    }

    public BusinessEntityGroup getBusinessEntityGroup() {
        return this.mBusinessEntityGroup;
    }

    @JsonProperty("business_entity_group")
    public void setBusinessEntityGroup(BusinessEntityGroup value) {
        this.mBusinessEntityGroup = value;
    }

    public String getCc_type() {
        return this.mCc_type;
    }

    @JsonProperty("cc_type")
    public void setCc_type(String value) {
        this.mCc_type = value;
    }

    public String getName() {
        return this.mName;
    }

    @JsonProperty("name")
    public void setName(String value) {
        this.mName = value;
    }

    public String getType() {
        return this.mType;
    }

    @JsonProperty("type")
    public void setType(String value) {
        this.mType = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mBusinessEntityGroup, 0);
        parcel.writeString(this.mCc_type);
        parcel.writeString(this.mName);
        parcel.writeString(this.mType);
    }

    public void readFromParcel(Parcel source) {
        this.mBusinessEntityGroup = (BusinessEntityGroup) source.readParcelable(BusinessEntityGroup.class.getClassLoader());
        this.mCc_type = source.readString();
        this.mName = source.readString();
        this.mType = source.readString();
    }
}
