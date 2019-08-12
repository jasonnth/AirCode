package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenListingWirelessInfo;

public class ListingWirelessInfo extends GenListingWirelessInfo {
    public static final Creator<ListingWirelessInfo> CREATOR = new Creator<ListingWirelessInfo>() {
        public ListingWirelessInfo[] newArray(int size) {
            return new ListingWirelessInfo[size];
        }

        public ListingWirelessInfo createFromParcel(Parcel source) {
            ListingWirelessInfo object = new ListingWirelessInfo();
            object.readFromParcel(source);
            return object;
        }
    };

    public enum WirelessTypes {
        UNKNOWN("unknown"),
        WPA("WPA"),
        WEP("WEP");
        
        public final String type;

        private WirelessTypes(String t) {
            this.type = t;
        }
    }

    public ListingWirelessInfo() {
        setWirelessType(WirelessTypes.UNKNOWN.type);
    }

    public ListingWirelessInfo(long listingId) {
        this();
        this.mListingId = listingId;
    }

    public boolean hasValidId() {
        return getId() != 0;
    }
}
