package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.beta.models.guidebook.Place;
import com.airbnb.android.core.models.generated.GenWishListedPlace;
import com.airbnb.android.core.wishlists.WishListItem;
import com.airbnb.android.core.wishlists.WishListableType;

public class WishListedPlace extends GenWishListedPlace implements WishListItem {
    public static final Creator<WishListedPlace> CREATOR = new Creator<WishListedPlace>() {
        public WishListedPlace[] newArray(int size) {
            return new WishListedPlace[size];
        }

        public WishListedPlace createFromParcel(Parcel source) {
            WishListedPlace object = new WishListedPlace();
            object.readFromParcel(source);
            return object;
        }
    };

    public Place getPlace() {
        return this.mGuidebookPlace.getPrimaryPlace();
    }

    public WishListableType getItemType() {
        return WishListableType.Place;
    }

    public long getItemId() {
        return this.mPlaceId;
    }

    public boolean isAvailable() {
        return true;
    }

    public boolean hasGuidebookPlace() {
        return this.mGuidebookPlace != null;
    }
}
