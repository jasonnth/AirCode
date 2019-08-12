package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenHostRatingBreakdown implements Parcelable {
    @JsonProperty("star_rating_accuracy")
    protected float mAccuracy;
    @JsonProperty("star_rating_checkin")
    protected float mCheckIn;
    @JsonProperty("star_rating_cleanliness")
    protected float mCleanliness;
    @JsonProperty("star_rating_communication")
    protected float mCommunication;
    @JsonProperty("star_rating_location")
    protected float mLocation;
    @JsonProperty("avg_market_five_star_overall_pct")
    protected Integer mMarketAverageFiveStarRatingPercentage;
    @JsonProperty("star_rating_overall")
    protected float mOverall;
    @JsonProperty("star_rating_value")
    protected float mValue;

    protected GenHostRatingBreakdown(Integer marketAverageFiveStarRatingPercentage, float overall, float accuracy, float communication, float cleanliness, float location, float checkIn, float value) {
        this();
        this.mMarketAverageFiveStarRatingPercentage = marketAverageFiveStarRatingPercentage;
        this.mOverall = overall;
        this.mAccuracy = accuracy;
        this.mCommunication = communication;
        this.mCleanliness = cleanliness;
        this.mLocation = location;
        this.mCheckIn = checkIn;
        this.mValue = value;
    }

    protected GenHostRatingBreakdown() {
    }

    public Integer getMarketAverageFiveStarRatingPercentage() {
        return this.mMarketAverageFiveStarRatingPercentage;
    }

    @JsonProperty("avg_market_five_star_overall_pct")
    public void setMarketAverageFiveStarRatingPercentage(Integer value) {
        this.mMarketAverageFiveStarRatingPercentage = value;
    }

    public float getOverall() {
        return this.mOverall;
    }

    @JsonProperty("star_rating_overall")
    public void setOverall(float value) {
        this.mOverall = value;
    }

    public float getAccuracy() {
        return this.mAccuracy;
    }

    @JsonProperty("star_rating_accuracy")
    public void setAccuracy(float value) {
        this.mAccuracy = value;
    }

    public float getCommunication() {
        return this.mCommunication;
    }

    @JsonProperty("star_rating_communication")
    public void setCommunication(float value) {
        this.mCommunication = value;
    }

    public float getCleanliness() {
        return this.mCleanliness;
    }

    @JsonProperty("star_rating_cleanliness")
    public void setCleanliness(float value) {
        this.mCleanliness = value;
    }

    public float getLocation() {
        return this.mLocation;
    }

    @JsonProperty("star_rating_location")
    public void setLocation(float value) {
        this.mLocation = value;
    }

    public float getCheckIn() {
        return this.mCheckIn;
    }

    @JsonProperty("star_rating_checkin")
    public void setCheckIn(float value) {
        this.mCheckIn = value;
    }

    public float getValue() {
        return this.mValue;
    }

    @JsonProperty("star_rating_value")
    public void setValue(float value) {
        this.mValue = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeValue(this.mMarketAverageFiveStarRatingPercentage);
        parcel.writeFloat(this.mOverall);
        parcel.writeFloat(this.mAccuracy);
        parcel.writeFloat(this.mCommunication);
        parcel.writeFloat(this.mCleanliness);
        parcel.writeFloat(this.mLocation);
        parcel.writeFloat(this.mCheckIn);
        parcel.writeFloat(this.mValue);
    }

    public void readFromParcel(Parcel source) {
        this.mMarketAverageFiveStarRatingPercentage = (Integer) source.readValue(Integer.class.getClassLoader());
        this.mOverall = source.readFloat();
        this.mAccuracy = source.readFloat();
        this.mCommunication = source.readFloat();
        this.mCleanliness = source.readFloat();
        this.mLocation = source.readFloat();
        this.mCheckIn = source.readFloat();
        this.mValue = source.readFloat();
    }
}
