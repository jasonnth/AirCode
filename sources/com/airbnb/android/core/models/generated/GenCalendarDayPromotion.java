package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.CalendarDayPromotion.PromotionType;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenCalendarDayPromotion implements Parcelable {
    @JsonProperty("discount_percentage")
    protected int mDiscountPercentage;
    @JsonProperty("end_date")
    protected AirDate mEndDate;
    @JsonProperty("expired_at")
    protected AirDateTime mExpiredAt;
    @JsonProperty("promotion_id")
    protected long mPromotionId;
    @JsonProperty("promotion_type")
    protected PromotionType mPromotionType;
    @JsonProperty("start_date")
    protected AirDate mStartDate;

    protected GenCalendarDayPromotion(AirDate startDate, AirDate endDate, AirDateTime expiredAt, PromotionType promotionType, int discountPercentage, long promotionId) {
        this();
        this.mStartDate = startDate;
        this.mEndDate = endDate;
        this.mExpiredAt = expiredAt;
        this.mPromotionType = promotionType;
        this.mDiscountPercentage = discountPercentage;
        this.mPromotionId = promotionId;
    }

    protected GenCalendarDayPromotion() {
    }

    public AirDate getStartDate() {
        return this.mStartDate;
    }

    @JsonProperty("start_date")
    public void setStartDate(AirDate value) {
        this.mStartDate = value;
    }

    public AirDate getEndDate() {
        return this.mEndDate;
    }

    @JsonProperty("end_date")
    public void setEndDate(AirDate value) {
        this.mEndDate = value;
    }

    public AirDateTime getExpiredAt() {
        return this.mExpiredAt;
    }

    @JsonProperty("expired_at")
    public void setExpiredAt(AirDateTime value) {
        this.mExpiredAt = value;
    }

    public PromotionType getPromotionType() {
        return this.mPromotionType;
    }

    @JsonProperty("promotion_type")
    public void setPromotionType(PromotionType value) {
        this.mPromotionType = value;
    }

    public int getDiscountPercentage() {
        return this.mDiscountPercentage;
    }

    @JsonProperty("discount_percentage")
    public void setDiscountPercentage(int value) {
        this.mDiscountPercentage = value;
    }

    public long getPromotionId() {
        return this.mPromotionId;
    }

    @JsonProperty("promotion_id")
    public void setPromotionId(long value) {
        this.mPromotionId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mStartDate, 0);
        parcel.writeParcelable(this.mEndDate, 0);
        parcel.writeParcelable(this.mExpiredAt, 0);
        parcel.writeSerializable(this.mPromotionType);
        parcel.writeInt(this.mDiscountPercentage);
        parcel.writeLong(this.mPromotionId);
    }

    public void readFromParcel(Parcel source) {
        this.mStartDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mEndDate = (AirDate) source.readParcelable(AirDate.class.getClassLoader());
        this.mExpiredAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mPromotionType = (PromotionType) source.readSerializable();
        this.mDiscountPercentage = source.readInt();
        this.mPromotionId = source.readLong();
    }
}
