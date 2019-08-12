package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenSectionedListingDescription;

public class SectionedListingDescription extends GenSectionedListingDescription {
    public static final Creator<SectionedListingDescription> CREATOR = new Creator<SectionedListingDescription>() {
        public SectionedListingDescription[] newArray(int size) {
            return new SectionedListingDescription[size];
        }

        public SectionedListingDescription createFromParcel(Parcel source) {
            SectionedListingDescription object = new SectionedListingDescription();
            object.readFromParcel(source);
            return object;
        }
    };
}
