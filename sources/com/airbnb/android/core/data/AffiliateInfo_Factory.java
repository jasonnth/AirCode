package com.airbnb.android.core.data;

import com.airbnb.android.core.AirbnbPreferences;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class AffiliateInfo_Factory implements Factory<AffiliateInfo> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AffiliateInfo_Factory.class.desiredAssertionStatus());
    private final Provider<AirbnbPreferences> preferencesProvider;

    public AffiliateInfo_Factory(Provider<AirbnbPreferences> preferencesProvider2) {
        if ($assertionsDisabled || preferencesProvider2 != null) {
            this.preferencesProvider = preferencesProvider2;
            return;
        }
        throw new AssertionError();
    }

    public AffiliateInfo get() {
        return new AffiliateInfo((AirbnbPreferences) this.preferencesProvider.get());
    }

    public static Factory<AffiliateInfo> create(Provider<AirbnbPreferences> preferencesProvider2) {
        return new AffiliateInfo_Factory(preferencesProvider2);
    }
}
