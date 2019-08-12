package com.airbnb.android.lib.utils.webintent;

import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.data.AffiliateInfo;
import com.squareup.otto.Bus;
import dagger.MembersInjector;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

public final class WebIntentDispatch_MembersInjector implements MembersInjector<WebIntentDispatch> {
    static final /* synthetic */ boolean $assertionsDisabled = (!WebIntentDispatch_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final Provider<AffiliateInfo> affiliateInfoProvider;
    private final Provider<Bus> busProvider;
    private final Provider<OkHttpClient> okHttpClientProvider;
    private final Provider<AirbnbPreferences> preferencesProvider;

    public WebIntentDispatch_MembersInjector(Provider<AffiliateInfo> affiliateInfoProvider2, Provider<AirbnbPreferences> preferencesProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<OkHttpClient> okHttpClientProvider2, Provider<Bus> busProvider2) {
        if ($assertionsDisabled || affiliateInfoProvider2 != null) {
            this.affiliateInfoProvider = affiliateInfoProvider2;
            if ($assertionsDisabled || preferencesProvider2 != null) {
                this.preferencesProvider = preferencesProvider2;
                if ($assertionsDisabled || accountManagerProvider2 != null) {
                    this.accountManagerProvider = accountManagerProvider2;
                    if ($assertionsDisabled || okHttpClientProvider2 != null) {
                        this.okHttpClientProvider = okHttpClientProvider2;
                        if ($assertionsDisabled || busProvider2 != null) {
                            this.busProvider = busProvider2;
                            return;
                        }
                        throw new AssertionError();
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<WebIntentDispatch> create(Provider<AffiliateInfo> affiliateInfoProvider2, Provider<AirbnbPreferences> preferencesProvider2, Provider<AirbnbAccountManager> accountManagerProvider2, Provider<OkHttpClient> okHttpClientProvider2, Provider<Bus> busProvider2) {
        return new WebIntentDispatch_MembersInjector(affiliateInfoProvider2, preferencesProvider2, accountManagerProvider2, okHttpClientProvider2, busProvider2);
    }

    public void injectMembers(WebIntentDispatch instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.affiliateInfo = (AffiliateInfo) this.affiliateInfoProvider.get();
        instance.preferences = (AirbnbPreferences) this.preferencesProvider.get();
        instance.accountManager = (AirbnbAccountManager) this.accountManagerProvider.get();
        instance.okHttpClient = (OkHttpClient) this.okHttpClientProvider.get();
        instance.bus = (Bus) this.busProvider.get();
    }

    public static void injectAffiliateInfo(WebIntentDispatch instance, Provider<AffiliateInfo> affiliateInfoProvider2) {
        instance.affiliateInfo = (AffiliateInfo) affiliateInfoProvider2.get();
    }

    public static void injectPreferences(WebIntentDispatch instance, Provider<AirbnbPreferences> preferencesProvider2) {
        instance.preferences = (AirbnbPreferences) preferencesProvider2.get();
    }

    public static void injectAccountManager(WebIntentDispatch instance, Provider<AirbnbAccountManager> accountManagerProvider2) {
        instance.accountManager = (AirbnbAccountManager) accountManagerProvider2.get();
    }

    public static void injectOkHttpClient(WebIntentDispatch instance, Provider<OkHttpClient> okHttpClientProvider2) {
        instance.okHttpClient = (OkHttpClient) okHttpClientProvider2.get();
    }

    public static void injectBus(WebIntentDispatch instance, Provider<Bus> busProvider2) {
        instance.bus = (Bus) busProvider2.get();
    }
}
