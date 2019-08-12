package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.wishlists.WishListableData;

public class PickWishListActivityIntents {
    public static final String KEY_WISHLISTABLE_DATA = "key_wishlistable_data";

    public static Intent forData(Context context, WishListableData data) {
        return new Intent(context, Activities.pickWishList()).putExtra("key_wishlistable_data", data);
    }
}
