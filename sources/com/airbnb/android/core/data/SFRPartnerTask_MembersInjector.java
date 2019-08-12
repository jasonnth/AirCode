package com.airbnb.android.core.data;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class SFRPartnerTask_MembersInjector implements MembersInjector<SFRPartnerTask> {
    static final /* synthetic */ boolean $assertionsDisabled = (!SFRPartnerTask_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AffiliateInfo> mAffiliateInfoProvider;

    public SFRPartnerTask_MembersInjector(Provider<AffiliateInfo> mAffiliateInfoProvider2) {
        if ($assertionsDisabled || mAffiliateInfoProvider2 != null) {
            this.mAffiliateInfoProvider = mAffiliateInfoProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<SFRPartnerTask> create(Provider<AffiliateInfo> mAffiliateInfoProvider2) {
        return new SFRPartnerTask_MembersInjector(mAffiliateInfoProvider2);
    }

    public void injectMembers(SFRPartnerTask instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mAffiliateInfo = (AffiliateInfo) this.mAffiliateInfoProvider.get();
    }
}
