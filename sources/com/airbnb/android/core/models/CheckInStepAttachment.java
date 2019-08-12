package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.contentframework.ContentFrameworkAnalytics;
import com.airbnb.android.core.models.generated.GenCheckInStepAttachment;

public class CheckInStepAttachment extends GenCheckInStepAttachment {
    public static final Creator<CheckInStepAttachment> CREATOR = new Creator<CheckInStepAttachment>() {
        public CheckInStepAttachment[] newArray(int size) {
            return new CheckInStepAttachment[size];
        }

        public CheckInStepAttachment createFromParcel(Parcel source) {
            CheckInStepAttachment object = new CheckInStepAttachment();
            object.readFromParcel(source);
            return object;
        }
    };

    public CheckInStepAttachment() {
    }

    public CheckInStepAttachment(String pictureUrl) {
        setType(ContentFrameworkAnalytics.IMAGE);
        setPictureUrl(pictureUrl);
    }
}
