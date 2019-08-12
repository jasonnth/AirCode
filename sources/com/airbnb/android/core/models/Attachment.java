package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenAttachment;

public class Attachment extends GenAttachment {
    public static final Creator<Attachment> CREATOR = new Creator<Attachment>() {
        public Attachment[] newArray(int size) {
            return new Attachment[size];
        }

        public Attachment createFromParcel(Parcel source) {
            Attachment object = new Attachment();
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
        if (this.mId != ((GenAttachment) o).getId()) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (int) (this.mId ^ (this.mId >>> 32));
    }
}
