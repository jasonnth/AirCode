package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenFullRefundUpsellInfo implements Parcelable {
    @JsonProperty("body")
    protected String mBody;
    @JsonProperty("is_full_refund")
    protected boolean mIsFullRefund;
    @JsonProperty("title")
    protected String mTitle;

    protected GenFullRefundUpsellInfo(String body, String title, boolean isFullRefund) {
        this();
        this.mBody = body;
        this.mTitle = title;
        this.mIsFullRefund = isFullRefund;
    }

    protected GenFullRefundUpsellInfo() {
    }

    public String getBody() {
        return this.mBody;
    }

    @JsonProperty("body")
    public void setBody(String value) {
        this.mBody = value;
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public boolean isFullRefund() {
        return this.mIsFullRefund;
    }

    @JsonProperty("is_full_refund")
    public void setIsFullRefund(boolean value) {
        this.mIsFullRefund = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mBody);
        parcel.writeString(this.mTitle);
        parcel.writeBooleanArray(new boolean[]{this.mIsFullRefund});
    }

    public void readFromParcel(Parcel source) {
        this.mBody = source.readString();
        this.mTitle = source.readString();
        this.mIsFullRefund = source.createBooleanArray()[0];
    }
}
