package com.airbnb.android.itinerary.data.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDateTime;
import java.util.ArrayList;

final class AutoValue_TimelineTrip extends C$AutoValue_TimelineTrip {
    public static final Creator<AutoValue_TimelineTrip> CREATOR = new Creator<AutoValue_TimelineTrip>() {
        public AutoValue_TimelineTrip createFromParcel(Parcel in) {
            Boolean bool;
            boolean z;
            String readString = in.readString();
            AirDateTime airDateTime = (AirDateTime) in.readParcelable(AirDateTime.class.getClassLoader());
            AirDateTime airDateTime2 = (AirDateTime) in.readParcelable(AirDateTime.class.getClassLoader());
            AirDateTime airDateTime3 = (AirDateTime) in.readParcelable(AirDateTime.class.getClassLoader());
            String str = in.readInt() == 0 ? in.readString() : null;
            String str2 = in.readInt() == 0 ? in.readString() : null;
            String str3 = in.readInt() == 0 ? in.readString() : null;
            String str4 = in.readInt() == 0 ? in.readString() : null;
            ArrayList readArrayList = in.readArrayList(String.class.getClassLoader());
            String str5 = in.readInt() == 0 ? in.readString() : null;
            String str6 = in.readInt() == 0 ? in.readString() : null;
            ArrayList readArrayList2 = in.readArrayList(TripEvent.class.getClassLoader());
            if (in.readInt() == 0) {
                if (in.readInt() == 1) {
                    z = true;
                } else {
                    z = false;
                }
                bool = Boolean.valueOf(z);
            } else {
                bool = null;
            }
            return new AutoValue_TimelineTrip(readString, airDateTime, airDateTime2, airDateTime3, str, str2, str3, str4, readArrayList, str5, str6, readArrayList2, bool);
        }

        public AutoValue_TimelineTrip[] newArray(int size) {
            return new AutoValue_TimelineTrip[size];
        }
    };

    AutoValue_TimelineTrip(String confirmation_code, AirDateTime starts_at, AirDateTime ends_at, AirDateTime expires_at, String time_zone, String title, String bundle_title, String bundle_subtitle, ArrayList<String> bundle_photo_urls, String picture, String pending_type, ArrayList<TripEvent> trip_schedule_cards, Boolean should_bundle) {
        super(confirmation_code, starts_at, ends_at, expires_at, time_zone, title, bundle_title, bundle_subtitle, bundle_photo_urls, picture, pending_type, trip_schedule_cards, should_bundle);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i = 1;
        dest.writeString(confirmation_code());
        dest.writeParcelable(starts_at(), flags);
        dest.writeParcelable(ends_at(), flags);
        dest.writeParcelable(expires_at(), flags);
        if (time_zone() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(time_zone());
        }
        if (title() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(title());
        }
        if (bundle_title() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(bundle_title());
        }
        if (bundle_subtitle() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(bundle_subtitle());
        }
        dest.writeList(bundle_photo_urls());
        if (picture() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(picture());
        }
        if (pending_type() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(pending_type());
        }
        dest.writeList(trip_schedule_cards());
        if (should_bundle() == null) {
            dest.writeInt(1);
            return;
        }
        dest.writeInt(0);
        if (!should_bundle().booleanValue()) {
            i = 0;
        }
        dest.writeInt(i);
    }

    public int describeContents() {
        return 0;
    }
}
