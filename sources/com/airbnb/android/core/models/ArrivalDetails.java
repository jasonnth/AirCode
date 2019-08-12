package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenArrivalDetails;
import com.google.common.collect.FluentIterable;

public class ArrivalDetails extends GenArrivalDetails {
    public static final Creator<ArrivalDetails> CREATOR = new Creator<ArrivalDetails>() {
        public ArrivalDetails[] newArray(int size) {
            return new ArrivalDetails[size];
        }

        public ArrivalDetails createFromParcel(Parcel source) {
            ArrivalDetails object = new ArrivalDetails();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean isCheckInOptionValid(CheckinTimeSelectionOptions checkInOption) {
        if (checkInOption == null || getValidCheckinTimeSelectionOptions() == null || ((CheckinTimeSelectionOptions) FluentIterable.from((Iterable<E>) getValidCheckinTimeSelectionOptions()).firstMatch(ArrivalDetails$$Lambda$1.lambdaFactory$(checkInOption)).orNull()) == null) {
            return false;
        }
        return true;
    }
}
