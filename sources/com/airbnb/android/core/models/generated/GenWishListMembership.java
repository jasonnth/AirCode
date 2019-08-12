package com.airbnb.android.core.models.generated;

import android.os.Parcel;
import android.os.Parcelable;
import com.airbnb.android.core.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class GenWishListMembership implements Parcelable {
    @JsonProperty("user")
    protected User mUser;
    @JsonProperty("wishlist_id")
    protected long mWishlistId;

    protected GenWishListMembership(User user, long wishlistId) {
        this();
        this.mUser = user;
        this.mWishlistId = wishlistId;
    }

    protected GenWishListMembership() {
    }

    public User getUser() {
        return this.mUser;
    }

    @JsonProperty("user")
    public void setUser(User value) {
        this.mUser = value;
    }

    public long getWishlistId() {
        return this.mWishlistId;
    }

    @JsonProperty("wishlist_id")
    public void setWishlistId(long value) {
        this.mWishlistId = value;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeParcelable(this.mUser, 0);
        parcel.writeLong(this.mWishlistId);
    }

    public void readFromParcel(Parcel source) {
        this.mUser = (User) source.readParcelable(User.class.getClassLoader());
        this.mWishlistId = source.readLong();
    }
}
