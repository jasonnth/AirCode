package com.airbnb.android.lib.businesstravel.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.lib.businesstravel.models.BusinessTravelWelcomeData.Link;
import java.util.List;

final class AutoValue_BusinessTravelWelcomeData_LegalDisclaimer extends C$AutoValue_BusinessTravelWelcomeData_LegalDisclaimer {
    public static final Creator<AutoValue_BusinessTravelWelcomeData_LegalDisclaimer> CREATOR = new Creator<AutoValue_BusinessTravelWelcomeData_LegalDisclaimer>() {
        public AutoValue_BusinessTravelWelcomeData_LegalDisclaimer createFromParcel(Parcel in) {
            return new AutoValue_BusinessTravelWelcomeData_LegalDisclaimer(in.readString(), in.readArrayList(Link.class.getClassLoader()));
        }

        public AutoValue_BusinessTravelWelcomeData_LegalDisclaimer[] newArray(int size) {
            return new AutoValue_BusinessTravelWelcomeData_LegalDisclaimer[size];
        }
    };

    AutoValue_BusinessTravelWelcomeData_LegalDisclaimer(String fullText, List<Link> links) {
        super(fullText, links);
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fullText());
        dest.writeList(links());
    }

    public int describeContents() {
        return 0;
    }
}
