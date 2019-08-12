package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenStoryFeedTopTile;
import com.airbnb.android.utils.Strap;

public class StoryFeedTopTile extends GenStoryFeedTopTile {
    public static final Creator<StoryFeedTopTile> CREATOR = new Creator<StoryFeedTopTile>() {
        public StoryFeedTopTile[] newArray(int size) {
            return new StoryFeedTopTile[size];
        }

        public StoryFeedTopTile createFromParcel(Parcel source) {
            StoryFeedTopTile object = new StoryFeedTopTile();
            object.readFromParcel(source);
            return object;
        }
    };

    public Strap getLoggingParams() {
        return Strap.make().mo11639kv("main_text", getMainText()).mo11639kv("secondary_text", getSecondaryText()).mo11639kv("query_params", getQueryParams().toString());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof StoryFeedTopTile)) {
            return false;
        }
        return getQueryParams().equals(((StoryFeedTopTile) obj).getQueryParams());
    }

    public int hashCode() {
        return getQueryParams().hashCode();
    }
}
