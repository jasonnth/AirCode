package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.deserializers.WrappedDeserializer;
import com.airbnb.android.core.deserializers.WrappedObject;
import com.airbnb.android.core.models.generated.GenRecommendation;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Recommendation extends GenRecommendation {
    public static final Creator<Recommendation> CREATOR = new Creator<Recommendation>() {
        public Recommendation[] newArray(int size) {
            return new Recommendation[size];
        }

        public Recommendation createFromParcel(Parcel source) {
            Recommendation object = new Recommendation();
            object.readFromParcel(source);
            return object;
        }
    };

    @WrappedObject("user")
    @JsonProperty("left_by_user")
    @JsonDeserialize(using = WrappedDeserializer.class)
    public void setRecommender(User user) {
        this.mRecommender = user;
    }
}
