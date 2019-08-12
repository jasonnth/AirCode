package com.airbnb.android.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.models.RestaurantAvailability;
import java.util.List;

final class AutoValue_ResyState extends C$AutoValue_ResyState {
    public static final Creator<AutoValue_ResyState> CREATOR = new Creator<AutoValue_ResyState>() {
        public AutoValue_ResyState createFromParcel(Parcel in) {
            boolean z;
            boolean z2 = false;
            long readLong = in.readLong();
            if (in.readInt() == 1) {
                z = true;
            } else {
                z = false;
            }
            int readInt = in.readInt();
            AirDate airDate = (AirDate) in.readParcelable(AirDate.class.getClassLoader());
            if (in.readInt() == 1) {
                z2 = true;
            }
            return new AutoValue_ResyState(readLong, z, readInt, airDate, z2, in.readArrayList(RestaurantAvailability.class.getClassLoader()), (Photo) in.readParcelable(Photo.class.getClassLoader()), in.readInt() == 0 ? in.readString() : null, (RestaurantAvailability) in.readParcelable(RestaurantAvailability.class.getClassLoader()));
        }

        public AutoValue_ResyState[] newArray(int size) {
            return new AutoValue_ResyState[size];
        }
    };

    AutoValue_ResyState(long activityId, boolean showResy, int guests, AirDate date, boolean isLoading, List<RestaurantAvailability> timeSlots, Photo coverImage, String placeName, RestaurantAvailability selectedTime) {
        super(activityId, showResy, guests, date, isLoading, timeSlots, coverImage, placeName, selectedTime);
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i;
        dest.writeLong(activityId());
        dest.writeInt(showResy() ? 1 : 0);
        dest.writeInt(guests());
        dest.writeParcelable(date(), flags);
        if (isLoading()) {
            i = 1;
        } else {
            i = 0;
        }
        dest.writeInt(i);
        dest.writeList(timeSlots());
        dest.writeParcelable(coverImage(), flags);
        if (placeName() == null) {
            dest.writeInt(1);
        } else {
            dest.writeInt(0);
            dest.writeString(placeName());
        }
        dest.writeParcelable(selectedTime(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
