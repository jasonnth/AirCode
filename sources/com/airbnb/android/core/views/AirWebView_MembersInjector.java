package com.airbnb.android.core.views;

import com.airbnb.android.core.AirbnbApi;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.interfaces.WebIntentMatcher;
import com.airbnb.android.core.persist.DomainStore;
import com.airbnb.android.core.utils.AirCookieManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AirWebView_MembersInjector implements MembersInjector<AirWebView> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AirWebView_MembersInjector.class.desiredAssertionStatus());
    private final Provider<AirCookieManager> cookieManagerProvider;
    private final Provider<AirbnbAccountManager> mAccountManagerProvider;
    private final Provider<AirbnbApi> mAirbnbApiProvider;
    private final Provider<DomainStore> mDomainStoreProvider;
    private final Provider<WebIntentMatcher> webIntentMatcherProvider;

    public AirWebView_MembersInjector(Provider<AirbnbApi> mAirbnbApiProvider2, Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<DomainStore> mDomainStoreProvider2, Provider<AirCookieManager> cookieManagerProvider2, Provider<WebIntentMatcher> webIntentMatcherProvider2) {
        if ($assertionsDisabled || mAirbnbApiProvider2 != null) {
            this.mAirbnbApiProvider = mAirbnbApiProvider2;
            if ($assertionsDisabled || mAccountManagerProvider2 != null) {
                this.mAccountManagerProvider = mAccountManagerProvider2;
                if ($assertionsDisabled || mDomainStoreProvider2 != null) {
                    this.mDomainStoreProvider = mDomainStoreProvider2;
                    if ($assertionsDisabled || cookieManagerProvider2 != null) {
                        this.cookieManagerProvider = cookieManagerProvider2;
                        if ($assertionsDisabled || webIntentMatcherProvider2 != null) {
                            this.webIntentMatcherProvider = webIntentMatcherProvider2;
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

    public static MembersInjector<AirWebView> create(Provider<AirbnbApi> mAirbnbApiProvider2, Provider<AirbnbAccountManager> mAccountManagerProvider2, Provider<DomainStore> mDomainStoreProvider2, Provider<AirCookieManager> cookieManagerProvider2, Provider<WebIntentMatcher> webIntentMatcherProvider2) {
        return new AirWebView_MembersInjector(mAirbnbApiProvider2, mAccountManagerProvider2, mDomainStoreProvider2, cookieManagerProvider2, webIntentMatcherProvider2);
    }

    public void injectMembers(AirWebView instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mAirbnbApi = (AirbnbApi) this.mAirbnbApiProvider.get();
        instance.mAccountManager = (AirbnbAccountManager) this.mAccountManagerProvider.get();
        instance.mDomainStore = (DomainStore) this.mDomainStoreProvider.get();
        instance.cookieManager = (AirCookieManager) this.cookieManagerProvider.get();
        instance.webIntentMatcher = (WebIntentMatcher) this.webIntentMatcherProvider.get();
    }

    public static void injectMAirbnbApi(AirWebView instance, Provider<AirbnbApi> mAirbnbApiProvider2) {
        instance.mAirbnbApi = (AirbnbApi) mAirbnbApiProvider2.get();
    }

    public static void injectMAccountManager(AirWebView instance, Provider<AirbnbAccountManager> mAccountManagerProvider2) {
        instance.mAccountManager = (AirbnbAccountManager) mAccountManagerProvider2.get();
    }

    public static void injectMDomainStore(AirWebView instance, Provider<DomainStore> mDomainStoreProvider2) {
        instance.mDomainStore = (DomainStore) mDomainStoreProvider2.get();
    }

    public static void injectCookieManager(AirWebView instance, Provider<AirCookieManager> cookieManagerProvider2) {
        instance.cookieManager = (AirCookieManager) cookieManagerProvider2.get();
    }

    public static void injectWebIntentMatcher(AirWebView instance, Provider<WebIntentMatcher> webIntentMatcherProvider2) {
        instance.webIntentMatcher = (WebIntentMatcher) webIntentMatcherProvider2.get();
    }
}
