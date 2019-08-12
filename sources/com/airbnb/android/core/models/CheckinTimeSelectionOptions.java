package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenCheckinTimeSelectionOptions;

public class CheckinTimeSelectionOptions extends GenCheckinTimeSelectionOptions {
    public static final Creator<CheckinTimeSelectionOptions> CREATOR = new Creator<CheckinTimeSelectionOptions>() {
        public CheckinTimeSelectionOptions[] newArray(int size) {
            return new CheckinTimeSelectionOptions[size];
        }

        public CheckinTimeSelectionOptions createFromParcel(Parcel source) {
            CheckinTimeSelectionOptions object = new CheckinTimeSelectionOptions();
            object.readFromParcel(source);
            return object;
        }
    };
    public static final String NOT_SELECTED_FORMATTED_HOUR = "NOT_SELECTED";
}
