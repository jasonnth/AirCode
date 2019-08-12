package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenStoryCreationPlaceTag;

public class StoryCreationPlaceTag extends GenStoryCreationPlaceTag {
    public static final Creator<StoryCreationPlaceTag> CREATOR = new Creator<StoryCreationPlaceTag>() {
        public StoryCreationPlaceTag[] newArray(int size) {
            return new StoryCreationPlaceTag[size];
        }

        public StoryCreationPlaceTag createFromParcel(Parcel source) {
            StoryCreationPlaceTag object = new StoryCreationPlaceTag();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return getGooglePlaceId().equals(((StoryCreationPlaceTag) o).getGooglePlaceId());
    }

    public int hashCode() {
        return getGooglePlaceId().hashCode();
    }
}
