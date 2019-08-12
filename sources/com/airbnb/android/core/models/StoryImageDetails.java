package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenStoryImageDetails;
import com.facebook.places.model.PlaceFields;

public class StoryImageDetails extends GenStoryImageDetails {
    public static final Creator<StoryImageDetails> CREATOR = new Creator<StoryImageDetails>() {
        public StoryImageDetails[] newArray(int size) {
            return new StoryImageDetails[size];
        }

        public StoryImageDetails createFromParcel(Parcel source) {
            StoryImageDetails object = new StoryImageDetails();
            object.readFromParcel(source);
            return object;
        }
    };

    public static StoryImageDetails fromLegacyCoverImageUrl(String url, double imageRatio) {
        StoryImageDetails details = new StoryImageDetails();
        details.setImageUrl(url);
        details.setImageIdentifier(PlaceFields.COVER);
        details.setImageRatio(imageRatio);
        return details;
    }
}
