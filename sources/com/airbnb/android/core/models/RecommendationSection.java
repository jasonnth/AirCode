package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenRecommendationSection;

public class RecommendationSection extends GenRecommendationSection {
    public static final Creator<RecommendationSection> CREATOR = new Creator<RecommendationSection>() {
        public RecommendationSection[] newArray(int size) {
            return new RecommendationSection[size];
        }

        public RecommendationSection createFromParcel(Parcel source) {
            RecommendationSection object = new RecommendationSection();
            object.readFromParcel(source);
            return object;
        }
    };
}
