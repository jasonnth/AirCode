package com.airbnb.android.react;

import dagger.internal.Factory;
import dagger.internal.Preconditions;
import okhttp3.CookieJar;

public final class ReactModule_ProvideReactCookieJarContainerFactory implements Factory<CookieJar> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ReactModule_ProvideReactCookieJarContainerFactory.class.desiredAssertionStatus());
    private final ReactModule module;

    public ReactModule_ProvideReactCookieJarContainerFactory(ReactModule module2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            return;
        }
        throw new AssertionError();
    }

    public CookieJar get() {
        return (CookieJar) Preconditions.checkNotNull(this.module.provideReactCookieJarContainer(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<CookieJar> create(ReactModule module2) {
        return new ReactModule_ProvideReactCookieJarContainerFactory(module2);
    }
}
