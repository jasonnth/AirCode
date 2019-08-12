package com.airbnb.android.core.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.generated.GenRestaurantAvailability;
import com.airbnb.android.utils.ListUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import org.joda.time.DateTime;

public class RestaurantAvailability extends GenRestaurantAvailability {
    public static final Creator<RestaurantAvailability> CREATOR = new Creator<RestaurantAvailability>() {
        public RestaurantAvailability[] newArray(int size) {
            return new RestaurantAvailability[size];
        }

        public RestaurantAvailability createFromParcel(Parcel source) {
            RestaurantAvailability object = new RestaurantAvailability();
            object.readFromParcel(source);
            return object;
        }
    };

    @JsonProperty("starts_at_in_restaurant_timezone")
    public void setStartsAtInRestaurantTimezone(String value) {
        this.mStartsAtInRestaurantTimezone = new AirDateTime(DateTime.parse(value));
    }

    public String getTimeString(Context context) {
        return getStartsAtInRestaurantTimezone().getTimeString(context);
    }

    public boolean hasConfirmationMessages() {
        return !ListUtils.isEmpty((Collection<?>) getConfirmationMessages());
    }

    public int hashCode() {
        return (this.mId ^ (this.mId >>> 16)) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (this.mId != ((RestaurantAvailability) obj).getId()) {
            return false;
        }
        return true;
    }
}
