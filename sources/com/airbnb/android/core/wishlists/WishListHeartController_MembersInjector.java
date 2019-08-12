package com.airbnb.android.core.wishlists;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class WishListHeartController_MembersInjector implements MembersInjector<WishListHeartController> {
    static final /* synthetic */ boolean $assertionsDisabled = (!WishListHeartController_MembersInjector.class.desiredAssertionStatus());
    private final Provider<WishListManager> managerProvider;
    private final Provider<WishListLogger> wlLoggerProvider;

    public WishListHeartController_MembersInjector(Provider<WishListLogger> wlLoggerProvider2, Provider<WishListManager> managerProvider2) {
        if ($assertionsDisabled || wlLoggerProvider2 != null) {
            this.wlLoggerProvider = wlLoggerProvider2;
            if ($assertionsDisabled || managerProvider2 != null) {
                this.managerProvider = managerProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<WishListHeartController> create(Provider<WishListLogger> wlLoggerProvider2, Provider<WishListManager> managerProvider2) {
        return new WishListHeartController_MembersInjector(wlLoggerProvider2, managerProvider2);
    }

    public void injectMembers(WishListHeartController instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.wlLogger = (WishListLogger) this.wlLoggerProvider.get();
        instance.manager = (WishListManager) this.managerProvider.get();
    }

    public static void injectWlLogger(WishListHeartController instance, Provider<WishListLogger> wlLoggerProvider2) {
        instance.wlLogger = (WishListLogger) wlLoggerProvider2.get();
    }

    public static void injectManager(WishListHeartController instance, Provider<WishListManager> managerProvider2) {
        instance.manager = (WishListManager) managerProvider2.get();
    }
}
