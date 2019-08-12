package com.airbnb.android.explore.adapters;

import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.instant_promo.InstantPromotionManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class BaseExploreAdapter_MembersInjector implements MembersInjector<BaseExploreAdapter> {
    static final /* synthetic */ boolean $assertionsDisabled = (!BaseExploreAdapter_MembersInjector.class.desiredAssertionStatus());
    private final Provider<BusinessTravelAccountManager> businessTravelAccountManagerProvider;
    private final Provider<InstantPromotionManager> instantPromotionManagerProvider;

    public BaseExploreAdapter_MembersInjector(Provider<BusinessTravelAccountManager> businessTravelAccountManagerProvider2, Provider<InstantPromotionManager> instantPromotionManagerProvider2) {
        if ($assertionsDisabled || businessTravelAccountManagerProvider2 != null) {
            this.businessTravelAccountManagerProvider = businessTravelAccountManagerProvider2;
            if ($assertionsDisabled || instantPromotionManagerProvider2 != null) {
                this.instantPromotionManagerProvider = instantPromotionManagerProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<BaseExploreAdapter> create(Provider<BusinessTravelAccountManager> businessTravelAccountManagerProvider2, Provider<InstantPromotionManager> instantPromotionManagerProvider2) {
        return new BaseExploreAdapter_MembersInjector(businessTravelAccountManagerProvider2, instantPromotionManagerProvider2);
    }

    public void injectMembers(BaseExploreAdapter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.businessTravelAccountManager = (BusinessTravelAccountManager) this.businessTravelAccountManagerProvider.get();
        instance.instantPromotionManager = (InstantPromotionManager) this.instantPromotionManagerProvider.get();
    }

    public static void injectBusinessTravelAccountManager(BaseExploreAdapter instance, Provider<BusinessTravelAccountManager> businessTravelAccountManagerProvider2) {
        instance.businessTravelAccountManager = (BusinessTravelAccountManager) businessTravelAccountManagerProvider2.get();
    }

    public static void injectInstantPromotionManager(BaseExploreAdapter instance, Provider<InstantPromotionManager> instantPromotionManagerProvider2) {
        instance.instantPromotionManager = (InstantPromotionManager) instantPromotionManagerProvider2.get();
    }
}
