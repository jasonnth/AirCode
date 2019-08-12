package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenWishListedPlaceActivity;
import com.airbnb.android.core.wishlists.WishListItem;
import com.airbnb.android.core.wishlists.WishListableType;

public class WishListedPlaceActivity extends GenWishListedPlaceActivity implements WishListItem {
    public static final Creator<WishListedPlaceActivity> CREATOR = new Creator<WishListedPlaceActivity>() {
        public WishListedPlaceActivity[] newArray(int size) {
            return new WishListedPlaceActivity[size];
        }

        public WishListedPlaceActivity createFromParcel(Parcel source) {
            WishListedPlaceActivity object = new WishListedPlaceActivity();
            object.readFromParcel(source);
            return object;
        }
    };

    public WishListableType getItemType() {
        return WishListableType.PlaceActivity;
    }

    public long getItemId() {
        return this.mPlaceActivityId;
    }

    public boolean isAvailable() {
        return true;
    }
}
