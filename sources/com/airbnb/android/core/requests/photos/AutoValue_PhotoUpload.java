package com.airbnb.android.core.requests.photos;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;

final class AutoValue_PhotoUpload extends C$AutoValue_PhotoUpload {
    public static final Creator<AutoValue_PhotoUpload> CREATOR = new Creator<AutoValue_PhotoUpload>() {
        public AutoValue_PhotoUpload createFromParcel(Parcel in) {
            boolean z = true;
            long readLong = in.readLong();
            long readLong2 = in.readLong();
            PhotoUploadTarget valueOf = PhotoUploadTarget.valueOf(in.readString());
            String readString = in.readString();
            if (in.readInt() != 1) {
                z = false;
            }
            return new AutoValue_PhotoUpload(readLong, readLong2, valueOf, readString, z, (Intent) in.readParcelable(Intent.class.getClassLoader()));
        }

        public AutoValue_PhotoUpload[] newArray(int size) {
            return new AutoValue_PhotoUpload[size];
        }
    };

    AutoValue_PhotoUpload(long galleryId, long uploadRequestId, PhotoUploadTarget uploadTarget, String path, boolean shouldDeleteFileOnComplete, Intent notificationIntent) {
        super(galleryId, uploadRequestId, uploadTarget, path, shouldDeleteFileOnComplete, notificationIntent);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(galleryId());
        dest.writeLong(uploadRequestId());
        dest.writeString(uploadTarget().name());
        dest.writeString(path());
        dest.writeInt(shouldDeleteFileOnComplete() ? 1 : 0);
        dest.writeParcelable(notificationIntent(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
