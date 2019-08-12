package com.airbnb.android.lib.utils.webintent;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.interfaces.WebIntentMatcher;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class WebIntentMatcherModule_ProvideWebIntentMatcherFactory implements Factory<WebIntentMatcher> {
    static final /* synthetic */ boolean $assertionsDisabled = (!WebIntentMatcherModule_ProvideWebIntentMatcherFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbAccountManager> accountManagerProvider;
    private final WebIntentMatcherModule module;

    public WebIntentMatcherModule_ProvideWebIntentMatcherFactory(WebIntentMatcherModule module2, Provider<AirbnbAccountManager> accountManagerProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || accountManagerProvider2 != null) {
                this.accountManagerProvider = accountManagerProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public WebIntentMatcher get() {
        return (WebIntentMatcher) Preconditions.checkNotNull(this.module.provideWebIntentMatcher((AirbnbAccountManager) this.accountManagerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<WebIntentMatcher> create(WebIntentMatcherModule module2, Provider<AirbnbAccountManager> accountManagerProvider2) {
        return new WebIntentMatcherModule_ProvideWebIntentMatcherFactory(module2, accountManagerProvider2);
    }

    public static WebIntentMatcher proxyProvideWebIntentMatcher(WebIntentMatcherModule instance, AirbnbAccountManager accountManager) {
        return instance.provideWebIntentMatcher(accountManager);
    }
}
