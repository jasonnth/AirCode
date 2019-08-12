package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenWishListedTrip;
import com.airbnb.android.core.wishlists.WishListItem;
import com.airbnb.android.core.wishlists.WishListableType;

public class WishListedTrip extends GenWishListedTrip implements WishListItem {
    public static final Creator<WishListedTrip> CREATOR = new Creator<WishListedTrip>() {
        public WishListedTrip[] newArray(int size) {
            return new WishListedTrip[size];
        }

        public WishListedTrip createFromParcel(Parcel source) {
            WishListedTrip object = new WishListedTrip();
            object.readFromParcel(source);
            return object;
        }
    };

    public WishListableType getItemType() {
        return WishListableType.Trip;
    }

    public long getItemId() {
        return getTrip().getId();
    }

    public boolean isAvailable() {
        return this.mPricingQuote.isAvailableForWishList();
    }
}
