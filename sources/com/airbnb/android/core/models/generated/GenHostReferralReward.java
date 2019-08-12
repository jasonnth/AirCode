package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.CurrencyAmount;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenHostReferralReward implements Parcelable {
    @JsonProperty("localized_reward_for_referred_user")
    protected CurrencyAmount mReferralRewardReferredUser;
    @JsonProperty("localized_reward_for_referrer")
    protected CurrencyAmount mReferralRewardReferrer;
    @JsonProperty("reward_count_limit")
    protected int mRewardCountLimit;
    @JsonProperty("reward_id")
    protected long mRewardId;

    protected GenHostReferralReward(CurrencyAmount referralRewardReferrer, CurrencyAmount referralRewardReferredUser, int rewardCountLimit, long rewardId) {
        this();
        this.mReferralRewardReferrer = referralRewardReferrer;
        this.mReferralRewardReferredUser = referralRewardReferredUser;
        this.mRewardCountLimit = rewardCountLimit;
        this.mRewardId = rewardId;
    }

    protected GenHostReferralReward() {
    }

    public CurrencyAmount getReferralRewardReferrer() {
        return this.mReferralRewardReferrer;
    }

    @JsonProperty("localized_reward_for_referrer")
    public void setReferralRewardReferrer(CurrencyAmount value) {
        this.mReferralRewardReferrer = value;
    }

    public CurrencyAmount getReferralRewardReferredUser() {
        return this.mReferralRewardReferredUser;
    }

    @JsonProperty("localized_reward_for_referred_user")
    public void setReferralRewardReferredUser(CurrencyAmount value) {
        this.mReferralRewardReferredUser = value;
    }

    public int getRewardCountLimit() {
        return this.mRewardCountLimit;
    }

    @JsonProperty("reward_count_limit")
    public void setRewardCountLimit(int value) {
        this.mRewardCountLimit = value;
    }

    public long getRewardId() {
        return this.mRewardId;
    }

    @JsonProperty("reward_id")
    public void setRewardId(long value) {
        this.mRewardId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mReferralRewardReferrer, 0);
        parcel.writeParcelable(this.mReferralRewardReferredUser, 0);
        parcel.writeInt(this.mRewardCountLimit);
        parcel.writeLong(this.mRewardId);
    }

    public void readFromParcel(Parcel source) {
        this.mReferralRewardReferrer = (CurrencyAmount) source.readParcelable(CurrencyAmount.class.getClassLoader());
        this.mReferralRewardReferredUser = (CurrencyAmount) source.readParcelable(CurrencyAmount.class.getClassLoader());
        this.mRewardCountLimit = source.readInt();
        this.mRewardId = source.readLong();
    }
}
