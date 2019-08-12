package com.airbnb.android.itinerary.data.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.intents.ShareActivityIntents;
import com.airbnb.android.core.requests.UpdateReviewRequest;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.mparticle.commerce.Product;

public enum TripEventCardType implements Parcelable {
    Checkin(UpdateReviewRequest.KEY_CHECKIN),
    Checkout(Product.CHECKOUT),
    Place("place"),
    Experience(ShareActivityIntents.ENTRY_POINT_EXPERIENCE),
    Freetime("free_time"),
    Flight("flight"),
    Unknown("");
    
    public static final Creator<TripEventCardType> CREATOR = null;
    private final String key;

    static {
        CREATOR = new Creator<TripEventCardType>() {
            public TripEventCardType createFromParcel(Parcel source) {
                return TripEventCardType.values()[source.readInt()];
            }

            public TripEventCardType[] newArray(int size) {
                return new TripEventCardType[size];
            }
        };
    }

    private TripEventCardType(String key2) {
        this.key = key2;
    }

    @JsonCreator
    public static TripEventCardType from(String key2) {
        TripEventCardType[] values = values();
        int length = values.length;
        for (int i = 0; i < length; i++) {
            TripEventCardType type = values[i];
            if (type.getKey().equals(key2) || type.name().equals(key2)) {
                return type;
            }
        }
        return Unknown;
    }

    public String getKey() {
        return this.key;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }
}
