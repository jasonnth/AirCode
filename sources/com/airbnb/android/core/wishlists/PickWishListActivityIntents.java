package com.airbnb.android.core.wishlists;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;

public class PickWishListActivityIntents {
    public static final String KEY_WISHLISTABLE_DATA = "key_wishlistable_data";

    private PickWishListActivityIntents() {
    }

    public static Intent newIntent(Context context, WishListableData data) {
        return new Intent(context, Activities.pickWishList()).putExtra("key_wishlistable_data", data);
    }
}
