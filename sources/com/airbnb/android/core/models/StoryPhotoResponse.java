package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenStoryPhotoResponse;

public class StoryPhotoResponse extends GenStoryPhotoResponse {
    public static final Creator<StoryPhotoResponse> CREATOR = new Creator<StoryPhotoResponse>() {
        public StoryPhotoResponse[] newArray(int size) {
            return new StoryPhotoResponse[size];
        }

        public StoryPhotoResponse createFromParcel(Parcel source) {
            StoryPhotoResponse object = new StoryPhotoResponse();
            object.readFromParcel(source);
            return object;
        }
    };
}
