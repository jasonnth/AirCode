package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenMeetup;

public class Meetup extends GenMeetup {
    public static final Creator<Meetup> CREATOR = new Creator<Meetup>() {
        public Meetup[] newArray(int size) {
            return new Meetup[size];
        }

        public Meetup createFromParcel(Parcel source) {
            Meetup object = new Meetup();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean hasReservation() {
        return getReservationId() != 0;
    }
}
