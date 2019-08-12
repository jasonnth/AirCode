package com.airbnb.android.checkin.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDateTime;
import com.airbnb.android.core.models.CheckInGuide;

final class AutoValue_CheckInGuideData extends C$AutoValue_CheckInGuideData {
    public static final Creator<AutoValue_CheckInGuideData> CREATOR = new Creator<AutoValue_CheckInGuideData>() {
        public AutoValue_CheckInGuideData createFromParcel(Parcel in) {
            return new AutoValue_CheckInGuideData(in.readLong(), (AirDateTime) in.readParcelable(AirDateTime.class.getClassLoader()), (CheckInGuide) in.readParcelable(CheckInGuide.class.getClassLoader()));
        }

        public AutoValue_CheckInGuideData[] newArray(int size) {
            return new AutoValue_CheckInGuideData[size];
        }
    };

    AutoValue_CheckInGuideData(long listing_id, AirDateTime updated_at, CheckInGuide check_in_guide) {
        super(listing_id, updated_at, check_in_guide);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(listing_id());
        dest.writeParcelable(updated_at(), flags);
        dest.writeParcelable(check_in_guide(), flags);
    }

    public int describeContents() {
        return 0;
    }
}
