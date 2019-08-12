package com.airbnb.android.lib;

import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.data.AffiliateInfo;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ReferralBroadcastReceiver_MembersInjector implements MembersInjector<ReferralBroadcastReceiver> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ReferralBroadcastReceiver_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AffiliateInfo> mAffiliateInfoProvider;
    private final Provider<AirbnbPreferences> mPreferencesProvider;

    public ReferralBroadcastReceiver_MembersInjector(Provider<AirbnbPreferences> mPreferencesProvider2, Provider<AffiliateInfo> mAffiliateInfoProvider2) {
        if ($assertionsDisabled || mPreferencesProvider2 != null) {
            this.mPreferencesProvider = mPreferencesProvider2;
            if ($assertionsDisabled || mAffiliateInfoProvider2 != null) {
                this.mAffiliateInfoProvider = mAffiliateInfoProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<ReferralBroadcastReceiver> create(Provider<AirbnbPreferences> mPreferencesProvider2, Provider<AffiliateInfo> mAffiliateInfoProvider2) {
        return new ReferralBroadcastReceiver_MembersInjector(mPreferencesProvider2, mAffiliateInfoProvider2);
    }

    public void injectMembers(ReferralBroadcastReceiver instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mPreferences = (AirbnbPreferences) this.mPreferencesProvider.get();
        instance.mAffiliateInfo = (AffiliateInfo) this.mAffiliateInfoProvider.get();
    }

    public static void injectMPreferences(ReferralBroadcastReceiver instance, Provider<AirbnbPreferences> mPreferencesProvider2) {
        instance.mPreferences = (AirbnbPreferences) mPreferencesProvider2.get();
    }

    public static void injectMAffiliateInfo(ReferralBroadcastReceiver instance, Provider<AffiliateInfo> mAffiliateInfoProvider2) {
        instance.mAffiliateInfo = (AffiliateInfo) mAffiliateInfoProvider2.get();
    }
}
