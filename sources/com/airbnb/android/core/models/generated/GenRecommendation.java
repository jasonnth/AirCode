package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenRecommendation implements Parcelable {
    @JsonProperty("created_at")
    protected AirDateTime mCreatedAt;
    @JsonProperty("id")
    protected int mId;
    @JsonProperty("recommendation")
    protected String mRecommendation;
    @JsonProperty("recommender")
    protected User mRecommender;
    @JsonProperty("relationship_type_text")
    protected String mRelationshipType;

    protected GenRecommendation(AirDateTime createdAt, String recommendation, String relationshipType, User recommender, int id) {
        this();
        this.mCreatedAt = createdAt;
        this.mRecommendation = recommendation;
        this.mRelationshipType = relationshipType;
        this.mRecommender = recommender;
        this.mId = id;
    }

    protected GenRecommendation() {
    }

    public AirDateTime getCreatedAt() {
        return this.mCreatedAt;
    }

    @JsonProperty("created_at")
    public void setCreatedAt(AirDateTime value) {
        this.mCreatedAt = value;
    }

    public String getRecommendation() {
        return this.mRecommendation;
    }

    @JsonProperty("recommendation")
    public void setRecommendation(String value) {
        this.mRecommendation = value;
    }

    public String getRelationshipType() {
        return this.mRelationshipType;
    }

    @JsonProperty("relationship_type_text")
    public void setRelationshipType(String value) {
        this.mRelationshipType = value;
    }

    public User getRecommender() {
        return this.mRecommender;
    }

    public int getId() {
        return this.mId;
    }

    @JsonProperty("id")
    public void setId(int value) {
        this.mId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mCreatedAt, 0);
        parcel.writeString(this.mRecommendation);
        parcel.writeString(this.mRelationshipType);
        parcel.writeParcelable(this.mRecommender, 0);
        parcel.writeInt(this.mId);
    }

    public void readFromParcel(Parcel source) {
        this.mCreatedAt = (AirDateTime) source.readParcelable(AirDateTime.class.getClassLoader());
        this.mRecommendation = source.readString();
        this.mRelationshipType = source.readString();
        this.mRecommender = (User) source.readParcelable(User.class.getClassLoader());
        this.mId = source.readInt();
    }
}
