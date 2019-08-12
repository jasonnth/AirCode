package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashMap;

public abstract class GenListingRegistrationOpenSubmission implements Parcelable {
    @JsonProperty("answers")
    protected HashMap mAnswers;
    @JsonProperty("id")
    protected long mId;
    @JsonProperty("listing_id")
    protected long mListingId;
    @JsonProperty("regulatory_body")
    protected String mRegulatoryBody;

    protected GenListingRegistrationOpenSubmission(HashMap answers, String regulatoryBody, long id, long listingId) {
        this();
        this.mAnswers = answers;
        this.mRegulatoryBody = regulatoryBody;
        this.mId = id;
        this.mListingId = listingId;
    }

    protected GenListingRegistrationOpenSubmission() {
    }

    public HashMap getAnswers() {
        return this.mAnswers;
    }

    @JsonProperty("answers")
    public void setAnswers(HashMap value) {
        this.mAnswers = value;
    }

    public String getRegulatoryBody() {
        return this.mRegulatoryBody;
    }

    @JsonProperty("regulatory_body")
    public void setRegulatoryBody(String value) {
        this.mRegulatoryBody = value;
    }

    public long getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(long value) {
        this.mId = value;
    }

    public long getListingId() {
        return this.mListingId;
    }

    @JsonProperty("listing_id")
    public void setListingId(long value) {
        this.mListingId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeSerializable(this.mAnswers);
        parcel.writeString(this.mRegulatoryBody);
        parcel.writeLong(this.mId);
        parcel.writeLong(this.mListingId);
    }

    public void readFromParcel(Parcel source) {
        this.mAnswers = (HashMap) source.readSerializable();
        this.mRegulatoryBody = source.readString();
        this.mId = source.readLong();
        this.mListingId = source.readLong();
    }
}
