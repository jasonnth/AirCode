package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.fasterxml.jackson.annotation.JsonCreator;

public enum ActionNotificationType implements Parcelable {
    AbandonP4("abandon_experience_checkout"),
    WishlistUrgency("wishlist_urgency"),
    NewMarketAnnouncement("announcement_new_market"),
    PHBThingsToDo("post_home_booking_things_to_do"),
    Unknown("");
    
    public static final Creator<ActionNotificationType> CREATOR = null;
    public final String productType;

    static {
        CREATOR = new Creator<ActionNotificationType>() {
            public ActionNotificationType createFromParcel(Parcel source) {
                return ActionNotificationType.values()[source.readInt()];
            }

            public ActionNotificationType[] newArray(int size) {
                return new ActionNotificationType[size];
            }
        };
    }

    private ActionNotificationType(String productType2) {
        this.productType = productType2;
    }

    @JsonCreator
    public static ActionNotificationType from(String givenProductType) {
        ActionNotificationType[] values;
        for (ActionNotificationType type : values()) {
            if (type.productType.equals(givenProductType)) {
                return type;
            }
        }
        return Unknown;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ordinal());
    }
}
