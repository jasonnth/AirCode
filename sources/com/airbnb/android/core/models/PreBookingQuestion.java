package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenPreBookingQuestion;

public class PreBookingQuestion extends GenPreBookingQuestion {
    public static final Creator<PreBookingQuestion> CREATOR = new Creator<PreBookingQuestion>() {
        public PreBookingQuestion[] newArray(int size) {
            return new PreBookingQuestion[size];
        }

        public PreBookingQuestion createFromParcel(Parcel source) {
            PreBookingQuestion object = new PreBookingQuestion();
            object.readFromParcel(source);
            return object;
        }
    };
}
