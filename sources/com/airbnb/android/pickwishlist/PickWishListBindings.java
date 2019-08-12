package com.airbnb.android.pickwishlist;

import com.airbnb.android.core.apprater.GraphBindings;
import com.airbnb.android.pickwishlist.PickWishListComponent.Builder;
import javax.inject.Provider;

public interface PickWishListBindings extends GraphBindings {
    Provider<Builder> pickWishListComponentProvider();
}
