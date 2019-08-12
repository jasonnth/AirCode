package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenAttachmentImage;

public class AttachmentImage extends GenAttachmentImage {
    public static final Creator<AttachmentImage> CREATOR = new Creator<AttachmentImage>() {
        public AttachmentImage[] newArray(int size) {
            return new AttachmentImage[size];
        }

        public AttachmentImage createFromParcel(Parcel source) {
            AttachmentImage object = new AttachmentImage();
            object.readFromParcel(source);
            return object;
        }
    };
}
