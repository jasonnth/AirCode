package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.PricingRule;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public abstract class GenCalendarPricingSettings implements Parcelable {
    @JsonProperty("early_bird_rules")
    protected List<PricingRule> mEarlyBirdRules;
    @JsonProperty("last_minute_rules")
    protected List<PricingRule> mLastMinuteRules;
    @JsonProperty("length_of_stay_rules")
    protected List<PricingRule> mLengthOfStayRules;
    @JsonProperty("listing_id")
    protected long mListingId;

    protected GenCalendarPricingSettings(List<PricingRule> lengthOfStayRules, List<PricingRule> LastMinuteRules, List<PricingRule> EarlyBirdRules, long listingId) {
        this();
        this.mLengthOfStayRules = lengthOfStayRules;
        this.mLastMinuteRules = LastMinuteRules;
        this.mEarlyBirdRules = EarlyBirdRules;
        this.mListingId = listingId;
    }

    protected GenCalendarPricingSettings() {
    }

    public List<PricingRule> getLengthOfStayRules() {
        return this.mLengthOfStayRules;
    }

    @JsonProperty("length_of_stay_rules")
    public void setLengthOfStayRules(List<PricingRule> value) {
        this.mLengthOfStayRules = value;
    }

    public List<PricingRule> getLastMinuteRules() {
        return this.mLastMinuteRules;
    }

    @JsonProperty("last_minute_rules")
    public void setLastMinuteRules(List<PricingRule> value) {
        this.mLastMinuteRules = value;
    }

    public List<PricingRule> getEarlyBirdRules() {
        return this.mEarlyBirdRules;
    }

    @JsonProperty("early_bird_rules")
    public void setEarlyBirdRules(List<PricingRule> value) {
        this.mEarlyBirdRules = value;
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
        parcel.writeTypedList(this.mLengthOfStayRules);
        parcel.writeTypedList(this.mLastMinuteRules);
        parcel.writeTypedList(this.mEarlyBirdRules);
        parcel.writeLong(this.mListingId);
    }

    public void readFromParcel(Parcel source) {
        this.mLengthOfStayRules = source.createTypedArrayList(PricingRule.CREATOR);
        this.mLastMinuteRules = source.createTypedArrayList(PricingRule.CREATOR);
        this.mEarlyBirdRules = source.createTypedArrayList(PricingRule.CREATOR);
        this.mListingId = source.readLong();
    }
}
