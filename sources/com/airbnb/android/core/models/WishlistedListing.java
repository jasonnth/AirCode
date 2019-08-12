package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.models.generated.GenWishlistedListing;
import com.airbnb.android.core.wishlists.WishListItem;
import com.airbnb.android.core.wishlists.WishListableType;

public class WishlistedListing extends GenWishlistedListing implements Mappable, WishListItem {
    public static final Creator<WishlistedListing> CREATOR = new Creator<WishlistedListing>() {
        public WishlistedListing[] newArray(int size) {
            return new WishlistedListing[size];
        }

        public WishlistedListing createFromParcel(Parcel source) {
            WishlistedListing object = new WishlistedListing();
            object.readFromParcel(source);
            return object;
        }
    };

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof WishlistedListing)) {
            return false;
        }
        if (this.mId != ((WishlistedListing) obj).mId) {
            return false;
        }
        return true;
    }

    public WishListableType getItemType() {
        return WishListableType.Home;
    }

    public long getItemId() {
        return getListing().getId();
    }

    public boolean isAvailable() {
        return this.mPricingQuote.isAvailableForWishList();
    }

    public boolean hasDates() {
        return this.mPricingQuote.hasDates();
    }

    public AirDate getCheckIn() {
        return this.mPricingQuote.getCheckIn();
    }

    public AirDate getCheckOut() {
        return this.mPricingQuote.getCheckOut();
    }

    public long getMappableId() {
        return this.mListing.getId();
    }

    public double getLatitude() {
        return this.mListing.getLatitude();
    }

    public double getLongitude() {
        return this.mListing.getLongitude();
    }
}
