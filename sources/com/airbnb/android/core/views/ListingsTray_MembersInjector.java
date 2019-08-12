package com.airbnb.android.core.views;

import com.airbnb.android.core.wishlists.WishListManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ListingsTray_MembersInjector implements MembersInjector<ListingsTray> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ListingsTray_MembersInjector.class.desiredAssertionStatus());
    private final Provider<WishListManager> wishListManagerProvider;

    public ListingsTray_MembersInjector(Provider<WishListManager> wishListManagerProvider2) {
        if ($assertionsDisabled || wishListManagerProvider2 != null) {
            this.wishListManagerProvider = wishListManagerProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<ListingsTray> create(Provider<WishListManager> wishListManagerProvider2) {
        return new ListingsTray_MembersInjector(wishListManagerProvider2);
    }

    public void injectMembers(ListingsTray instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.wishListManager = (WishListManager) this.wishListManagerProvider.get();
    }

    public static void injectWishListManager(ListingsTray instance, Provider<WishListManager> wishListManagerProvider2) {
        instance.wishListManager = (WishListManager) wishListManagerProvider2.get();
    }
}
