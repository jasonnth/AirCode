package com.airbnb.android.core.models;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.models.generated.GenStoryProductLinkDetails;
import com.airbnb.android.core.wishlists.WishListManager;
import com.airbnb.android.core.wishlists.WishListableType;
import com.google.common.collect.FluentIterable;

public class StoryProductLinkDetails extends GenStoryProductLinkDetails {
    public static final Creator<StoryProductLinkDetails> CREATOR = new Creator<StoryProductLinkDetails>() {
        public StoryProductLinkDetails[] newArray(int size) {
            return new StoryProductLinkDetails[size];
        }

        public StoryProductLinkDetails createFromParcel(Parcel source) {
            StoryProductLinkDetails object = new StoryProductLinkDetails();
            object.readFromParcel(source);
            return object;
        }
    };

    public enum Type {
        Listing("Hosting"),
        Place("Place");
        
        private final String serverKey;

        private Type(String serverKey2) {
            this.serverKey = serverKey2;
        }

        /* access modifiers changed from: private */
        public static Type forServerKey(String serverKey2) {
            return (Type) FluentIterable.m1283of(values()).firstMatch(StoryProductLinkDetails$Type$$Lambda$1.lambdaFactory$(serverKey2)).orNull();
        }
    }

    public Type getObjectType() {
        return Type.forServerKey(getObjectTypeString());
    }

    public WishListableType getWishListableType() {
        switch (getObjectType()) {
            case Listing:
                return WishListableType.Home;
            case Place:
                return WishListableType.Place;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("unknown WishListableType for object type: " + getObjectTypeString()));
                return null;
        }
    }

    public boolean isWishlisted(WishListManager wishListManager) {
        return wishListManager.isItemWishListed(getWishListableType(), getObjectId());
    }
}
