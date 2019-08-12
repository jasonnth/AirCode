package com.airbnb.android.core.data;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class DTKPartnerTask_MembersInjector implements MembersInjector<DTKPartnerTask> {
    static final /* synthetic */ boolean $assertionsDisabled = (!DTKPartnerTask_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AffiliateInfo> mAffiliateInfoProvider;

    public DTKPartnerTask_MembersInjector(Provider<AffiliateInfo> mAffiliateInfoProvider2) {
        if ($assertionsDisabled || mAffiliateInfoProvider2 != null) {
            this.mAffiliateInfoProvider = mAffiliateInfoProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<DTKPartnerTask> create(Provider<AffiliateInfo> mAffiliateInfoProvider2) {
        return new DTKPartnerTask_MembersInjector(mAffiliateInfoProvider2);
    }

    public void injectMembers(DTKPartnerTask instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mAffiliateInfo = (AffiliateInfo) this.mAffiliateInfoProvider.get();
    }
}
