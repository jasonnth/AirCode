package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.ReferralMedium;
import com.airbnb.android.core.models.ReferralOffer;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenReferralStatus implements Parcelable {
    @JsonProperty("code")
    protected String mCode;
    @JsonProperty("link")
    protected String mLink;
    @JsonProperty("mediums")
    protected List<ReferralMedium> mReferralMediums;
    @JsonProperty("offer")
    protected ReferralOffer mReferralOffer;
    @JsonProperty("user_id")
    protected long mUserId;

    protected GenReferralStatus(List<ReferralMedium> referralMediums, ReferralOffer referralOffer, String code, String link, long userId) {
        this();
        this.mReferralMediums = referralMediums;
        this.mReferralOffer = referralOffer;
        this.mCode = code;
        this.mLink = link;
        this.mUserId = userId;
    }

    protected GenReferralStatus() {
    }

    public List<ReferralMedium> getReferralMediums() {
        return this.mReferralMediums;
    }

    @JsonProperty("mediums")
    public void setReferralMediums(List<ReferralMedium> value) {
        this.mReferralMediums = value;
    }

    public ReferralOffer getReferralOffer() {
        return this.mReferralOffer;
    }

    @JsonProperty("offer")
    public void setReferralOffer(ReferralOffer value) {
        this.mReferralOffer = value;
    }

    public String getCode() {
        return this.mCode;
    }

    @JsonProperty("code")
    public void setCode(String value) {
        this.mCode = value;
    }

    public String getLink() {
        return this.mLink;
    }

    @JsonProperty("link")
    public void setLink(String value) {
        this.mLink = value;
    }

    public long getUserId() {
        return this.mUserId;
    }

    @JsonProperty("user_id")
    public void setUserId(long value) {
        this.mUserId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeTypedList(this.mReferralMediums);
        parcel.writeParcelable(this.mReferralOffer, 0);
        parcel.writeString(this.mCode);
        parcel.writeString(this.mLink);
        parcel.writeLong(this.mUserId);
    }

    public void readFromParcel(Parcel source) {
        this.mReferralMediums = source.createTypedArrayList(ReferralMedium.CREATOR);
        this.mReferralOffer = (ReferralOffer) source.readParcelable(ReferralOffer.class.getClassLoader());
        this.mCode = source.readString();
        this.mLink = source.readString();
        this.mUserId = source.readLong();
    }
}
