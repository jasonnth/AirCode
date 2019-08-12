package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.models.generated.GenRecommendationItem;
import com.airbnb.android.utils.Strap;
import java.util.ArrayList;
import java.util.List;

public class RecommendationItem extends GenRecommendationItem {
    public static final Creator<RecommendationItem> CREATOR = new Creator<RecommendationItem>() {
        public RecommendationItem[] newArray(int size) {
            return new RecommendationItem[size];
        }

        public RecommendationItem createFromParcel(Parcel source) {
            RecommendationItem object = new RecommendationItem();
            object.readFromParcel(source);
            return object;
        }
    };

    public static List<List<RecommendationItem>> collectRecommendationItemRowGroupings(List<RecommendationItem> items, List<Integer> displayLayout) {
        List<List<RecommendationItem>> toReturn = new ArrayList<>();
        if (checkForDisplayLayoutSanity(items, displayLayout) != null) {
            int startIndex = 0;
            for (Integer intValue : displayLayout) {
                int breakdown = intValue.intValue();
                toReturn.add(items.subList(startIndex, startIndex + breakdown));
                startIndex += breakdown;
            }
        }
        return toReturn;
    }

    private static List<Integer> checkForDisplayLayoutSanity(List<RecommendationItem> items, List<Integer> displayLayout) {
        int totalItemsInLayout = 0;
        for (Integer intValue : displayLayout) {
            totalItemsInLayout += intValue.intValue();
        }
        if (totalItemsInLayout <= items.size()) {
            return displayLayout;
        }
        AirbnbEventLogger.track("explore_magazine_incorrect_config", new Strap().mo11639kv("expected", "items.size").mo11637kv("found", totalItemsInLayout));
        return null;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RecommendationItem that = (RecommendationItem) o;
        if (this.mId == that.mId && this.mItemType == that.mItemType) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) (this.mId ^ (this.mId >>> 32));
    }
}
