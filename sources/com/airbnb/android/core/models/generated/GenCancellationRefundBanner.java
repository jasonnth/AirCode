package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenCancellationRefundBanner implements Parcelable {
    @JsonProperty("plain_body")
    protected String mPlainBody;
    @JsonProperty("plain_title")
    protected String mPlainTitle;
    @JsonProperty("show_banner")
    protected boolean mShowBanner;

    protected GenCancellationRefundBanner(String plainBody, String plainTitle, boolean showBanner) {
        this();
        this.mPlainBody = plainBody;
        this.mPlainTitle = plainTitle;
        this.mShowBanner = showBanner;
    }

    protected GenCancellationRefundBanner() {
    }

    public String getPlainBody() {
        return this.mPlainBody;
    }

    @JsonProperty("plain_body")
    public void setPlainBody(String value) {
        this.mPlainBody = value;
    }

    public String getPlainTitle() {
        return this.mPlainTitle;
    }

    @JsonProperty("plain_title")
    public void setPlainTitle(String value) {
        this.mPlainTitle = value;
    }

    public boolean isShowBanner() {
        return this.mShowBanner;
    }

    @JsonProperty("show_banner")
    public void setShowBanner(boolean value) {
        this.mShowBanner = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mPlainBody);
        parcel.writeString(this.mPlainTitle);
        parcel.writeBooleanArray(new boolean[]{this.mShowBanner});
    }

    public void readFromParcel(Parcel source) {
        this.mPlainBody = source.readString();
        this.mPlainTitle = source.readString();
        this.mShowBanner = source.createBooleanArray()[0];
    }
}
