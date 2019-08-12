package com.airbnb.android.itinerary.data.models;

import android.os.Parcelable;
import com.airbnb.android.airdate.AirDateTime;
import java.util.List;

public abstract class FreeTimeItem implements Parcelable, BaseItineraryItem {

    public static abstract class Builder {
        public abstract FreeTimeItem build();

        public abstract Builder confirmationCode(String str);

        public abstract Builder endsAt(AirDateTime airDateTime);

        public abstract Builder startsAt(AirDateTime airDateTime);

        public abstract Builder suggestions(List<Suggestion> list);
    }

    public abstract String confirmationCode();

    public abstract AirDateTime endsAt();

    public abstract AirDateTime startsAt();

    public abstract List<Suggestion> suggestions();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new Builder();
    }

    public AirDateTime getStartsAt() {
        return startsAt();
    }

    public AirDateTime getEndsAt() {
        return endsAt();
    }

    public String getId() {
        return confirmationCode();
    }
}
