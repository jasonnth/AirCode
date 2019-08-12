package com.airbnb.android.itinerary.data.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDateTime;
import java.util.List;

final class AutoValue_FreeTimeItem extends C$AutoValue_FreeTimeItem {
    public static final Creator<AutoValue_FreeTimeItem> CREATOR = new Creator<AutoValue_FreeTimeItem>() {
        public AutoValue_FreeTimeItem createFromParcel(Parcel in) {
            return new AutoValue_FreeTimeItem((AirDateTime) in.readParcelable(AirDateTime.class.getClassLoader()), (AirDateTime) in.readParcelable(AirDateTime.class.getClassLoader()), in.readString(), in.readArrayList(Suggestion.class.getClassLoader()));
        }

        public AutoValue_FreeTimeItem[] newArray(int size) {
            return new AutoValue_FreeTimeItem[size];
        }
    };

    AutoValue_FreeTimeItem(AirDateTime startsAt, AirDateTime endsAt, String confirmationCode, List<Suggestion> suggestions) {
        super(startsAt, endsAt, confirmationCode, suggestions);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(startsAt(), flags);
        dest.writeParcelable(endsAt(), flags);
        dest.writeString(confirmationCode());
        dest.writeList(suggestions());
    }

    public int describeContents() {
        return 0;
    }
}
