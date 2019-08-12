package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.ReservationCancellationReasonInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

public abstract class GenReservationCancellationReasonInfo implements Parcelable {
    @JsonProperty("jitney_logging_key")
    protected int mJitneyLoggingKey;
    @JsonProperty("reason_id")
    protected String mReasonId;
    @JsonProperty("sub_reasons")
    protected ArrayList<ReservationCancellationReasonInfo> mSubReasons;
    @JsonProperty("sub_reasons_title")
    protected String mSubReasonsTitle;
    @JsonProperty("title")
    protected String mTitle;

    protected GenReservationCancellationReasonInfo(ArrayList<ReservationCancellationReasonInfo> subReasons, String reasonId, String subReasonsTitle, String title, int jitneyLoggingKey) {
        this();
        this.mSubReasons = subReasons;
        this.mReasonId = reasonId;
        this.mSubReasonsTitle = subReasonsTitle;
        this.mTitle = title;
        this.mJitneyLoggingKey = jitneyLoggingKey;
    }

    protected GenReservationCancellationReasonInfo() {
    }

    public ArrayList<ReservationCancellationReasonInfo> getSubReasons() {
        return this.mSubReasons;
    }

    @JsonProperty("sub_reasons")
    public void setSubReasons(ArrayList<ReservationCancellationReasonInfo> value) {
        this.mSubReasons = value;
    }

    public String getReasonId() {
        return this.mReasonId;
    }

    @JsonProperty("reason_id")
    public void setReasonId(String value) {
        this.mReasonId = value;
    }

    public String getSubReasonsTitle() {
        return this.mSubReasonsTitle;
    }

    @JsonProperty("sub_reasons_title")
    public void setSubReasonsTitle(String value) {
        this.mSubReasonsTitle = value;
    }

    public String getTitle() {
        return this.mTitle;
    }

    @JsonProperty("title")
    public void setTitle(String value) {
        this.mTitle = value;
    }

    public int getJitneyLoggingKey() {
        return this.mJitneyLoggingKey;
    }

    @JsonProperty("jitney_logging_key")
    public void setJitneyLoggingKey(int value) {
        this.mJitneyLoggingKey = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mSubReasons);
        parcel.writeString(this.mReasonId);
        parcel.writeString(this.mSubReasonsTitle);
        parcel.writeString(this.mTitle);
        parcel.writeInt(this.mJitneyLoggingKey);
    }

    public void readFromParcel(Parcel source) {
        this.mSubReasons = source.createTypedArrayList(ReservationCancellationReasonInfo.CREATOR);
        this.mReasonId = source.readString();
        this.mSubReasonsTitle = source.readString();
        this.mTitle = source.readString();
        this.mJitneyLoggingKey = source.readInt();
    }
}
