package com.airbnb.android.core.data;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class PartnerTask_MembersInjector<T> implements MembersInjector<PartnerTask<T>> {
    static final /* synthetic */ boolean $assertionsDisabled = (!PartnerTask_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AffiliateInfo> mAffiliateInfoProvider;

    public PartnerTask_MembersInjector(Provider<AffiliateInfo> mAffiliateInfoProvider2) {
        if ($assertionsDisabled || mAffiliateInfoProvider2 != null) {
            this.mAffiliateInfoProvider = mAffiliateInfoProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static <T> MembersInjector<PartnerTask<T>> create(Provider<AffiliateInfo> mAffiliateInfoProvider2) {
        return new PartnerTask_MembersInjector(mAffiliateInfoProvider2);
    }

    public void injectMembers(PartnerTask<T> instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mAffiliateInfo = (AffiliateInfo) this.mAffiliateInfoProvider.get();
    }

    public static <T> void injectMAffiliateInfo(PartnerTask<T> instance, Provider<AffiliateInfo> mAffiliateInfoProvider2) {
        instance.mAffiliateInfo = (AffiliateInfo) mAffiliateInfoProvider2.get();
    }
}
