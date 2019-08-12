package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenReviewKeyword implements Parcelable {
    @JsonProperty("hits")
    protected String mHits;
    @JsonProperty("term")
    protected String mTerm;

    protected GenReviewKeyword(String term, String hits) {
        this();
        this.mTerm = term;
        this.mHits = hits;
    }

    protected GenReviewKeyword() {
    }

    public String getTerm() {
        return this.mTerm;
    }

    @JsonProperty("term")
    public void setTerm(String value) {
        this.mTerm = value;
    }

    public String getHits() {
        return this.mHits;
    }

    @JsonProperty("hits")
    public void setHits(String value) {
        this.mHits = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mTerm);
        parcel.writeString(this.mHits);
    }

    public void readFromParcel(Parcel source) {
        this.mTerm = source.readString();
        this.mHits = source.readString();
    }
}
