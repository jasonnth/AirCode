package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.models.generated.GenWishListMembership;

public class WishListMembership extends GenWishListMembership {
    public static final Creator<WishListMembership> CREATOR = new Creator<WishListMembership>() {
        public WishListMembership[] newArray(int size) {
            return new WishListMembership[size];
        }

        public WishListMembership createFromParcel(Parcel source) {
            WishListMembership object = new WishListMembership();
            object.readFromParcel(source);
            return object;
        }
    };
}
