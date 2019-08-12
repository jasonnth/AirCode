package com.airbnb.android.contentframework.data;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.contentframework.data.StoryCreationImage.PhotoType;

final class AutoValue_StoryCreationImage extends C$AutoValue_StoryCreationImage {
    public static final Creator<AutoValue_StoryCreationImage> CREATOR = new Creator<AutoValue_StoryCreationImage>() {
        public AutoValue_StoryCreationImage createFromParcel(Parcel in) {
            return new AutoValue_StoryCreationImage((Uri) in.readParcelable(Uri.class.getClassLoader()), PhotoType.valueOf(in.readString()), in.readString(), in.readInt(), in.readInt());
        }

        public AutoValue_StoryCreationImage[] newArray(int size) {
            return new AutoValue_StoryCreationImage[size];
        }
    };

    AutoValue_StoryCreationImage(Uri uri, PhotoType photoType, String filePath, int width, int height) {
        super(uri, photoType, filePath, width, height);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(uri(), flags);
        dest.writeString(photoType().name());
        dest.writeString(filePath());
        dest.writeInt(width());
        dest.writeInt(height());
    }

    public int describeContents() {
        return 0;
    }
}
