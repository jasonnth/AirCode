package com.airbnb.android.contentframework.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.StoryCreationPlaceTag;
import java.util.List;

final class AutoValue_StoryPublishArguments extends C$AutoValue_StoryPublishArguments {
    public static final Creator<AutoValue_StoryPublishArguments> CREATOR = new Creator<AutoValue_StoryPublishArguments>() {
        public AutoValue_StoryPublishArguments createFromParcel(Parcel in) {
            return new AutoValue_StoryPublishArguments(in.readString(), in.readString(), in.readArrayList(StoryCreationImage.class.getClassLoader()), (StoryCreationListingAppendix) in.readParcelable(StoryCreationListingAppendix.class.getClassLoader()), (StoryCreationPlaceTag) in.readParcelable(StoryCreationPlaceTag.class.getClassLoader()));
        }

        public AutoValue_StoryPublishArguments[] newArray(int size) {
            return new AutoValue_StoryPublishArguments[size];
        }
    };

    AutoValue_StoryPublishArguments(String title, String body, List<StoryCreationImage> imageList, StoryCreationListingAppendix appendix, StoryCreationPlaceTag placeTag) {
        super(title, body, imageList, appendix, placeTag);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title());
        dest.writeString(body());
        dest.writeList(imageList());
        dest.writeParcelable(appendix(), flags);
        dest.writeParcelable(placeTag(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
