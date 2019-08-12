package com.airbnb.android.lib.tripassistant;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.Attachment;

final class AutoValue_HelpThreadPhoto extends C$AutoValue_HelpThreadPhoto {
    public static final Creator<AutoValue_HelpThreadPhoto> CREATOR = new Creator<AutoValue_HelpThreadPhoto>() {
        public AutoValue_HelpThreadPhoto createFromParcel(Parcel in) {
            return new AutoValue_HelpThreadPhoto(in.readInt() == 0 ? in.readString() : null, (Attachment) in.readParcelable(Attachment.class.getClassLoader()));
        }

        public AutoValue_HelpThreadPhoto[] newArray(int size) {
            return new AutoValue_HelpThreadPhoto[size];
        }
    };

    AutoValue_HelpThreadPhoto(String localPath, Attachment attachment) {
        super(localPath, attachment);
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (localPath() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(localPath());
        }
        dest.writeParcelable(attachment(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
