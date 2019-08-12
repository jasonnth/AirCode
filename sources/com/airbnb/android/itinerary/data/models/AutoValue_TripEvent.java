package com.airbnb.android.itinerary.data.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDateTime;
import java.util.ArrayList;

final class AutoValue_TripEvent extends C$AutoValue_TripEvent {
    public static final Creator<AutoValue_TripEvent> CREATOR = new Creator<AutoValue_TripEvent>() {
        public AutoValue_TripEvent createFromParcel(Parcel in) {
            return new AutoValue_TripEvent(in.readString(), in.readInt() == 0 ? in.readString() : null, in.readInt() == 0 ? Long.valueOf(in.readLong()) : null, (TripEventCardType) in.readParcelable(TripEventCardType.class.getClassLoader()), in.readInt() == 0 ? in.readString() : null, in.readInt() == 0 ? in.readString() : null, in.readInt() == 0 ? in.readString() : null, (AirDateTime) in.readParcelable(AirDateTime.class.getClassLoader()), (AirDateTime) in.readParcelable(AirDateTime.class.getClassLoader()), in.readInt() == 0 ? in.readString() : null, in.readInt() == 0 ? in.readString() : null, in.readInt() == 0 ? in.readString() : null, in.readInt() == 0 ? in.readString() : null, in.readArrayList(TripEventSecondaryAction.class.getClassLoader()));
        }

        public AutoValue_TripEvent[] newArray(int size) {
            return new AutoValue_TripEvent[size];
        }
    };

    AutoValue_TripEvent(String primary_key, String schedule_confirmation_code, Long id, TripEventCardType card_type, String category, String confirmation_code, String picture, AirDateTime starts_at, AirDateTime ends_at, String time_zone, String header, String card_title, String card_subtitle, ArrayList<TripEventSecondaryAction> secondary_actions) {
        super(primary_key, schedule_confirmation_code, id, card_type, category, confirmation_code, picture, starts_at, ends_at, time_zone, header, card_title, card_subtitle, secondary_actions);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(primary_key());
        if (schedule_confirmation_code() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(schedule_confirmation_code());
        }
        if (mo10245id() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeLong(mo10245id().longValue());
        }
        dest.writeParcelable(card_type(), flags);
        if (category() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(category());
        }
        if (confirmation_code() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(confirmation_code());
        }
        if (picture() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(picture());
        }
        dest.writeParcelable(starts_at(), flags);
        dest.writeParcelable(ends_at(), flags);
        if (time_zone() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(time_zone());
        }
        if (header() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(header());
        }
        if (card_title() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(card_title());
        }
        if (card_subtitle() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(card_subtitle());
        }
        dest.writeList(secondary_actions());
    }

    public int describeContents() {
        return 0;
    }
}
