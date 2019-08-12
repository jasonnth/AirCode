package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.AirbnbPreferences;
import com.airbnb.android.core.utils.PullStringsScheduler;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvidePullStringsSchedulerFactory implements Factory<PullStringsScheduler> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvidePullStringsSchedulerFactory.class.desiredAssertionStatus());
    private final Provider<AirbnbPreferences> airbnbPreferencesProvider;
    private final Provider<Context> contextProvider;

    public CoreModule_ProvidePullStringsSchedulerFactory(Provider<Context> contextProvider2, Provider<AirbnbPreferences> airbnbPreferencesProvider2) {
        if ($assertionsDisabled || contextProvider2 != null) {
            this.contextProvider = contextProvider2;
            if ($assertionsDisabled || airbnbPreferencesProvider2 != null) {
                this.airbnbPreferencesProvider = airbnbPreferencesProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public PullStringsScheduler get() {
        return (PullStringsScheduler) Preconditions.checkNotNull(CoreModule.providePullStringsScheduler((Context) this.contextProvider.get(), (AirbnbPreferences) this.airbnbPreferencesProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<PullStringsScheduler> create(Provider<Context> contextProvider2, Provider<AirbnbPreferences> airbnbPreferencesProvider2) {
        return new CoreModule_ProvidePullStringsSchedulerFactory(contextProvider2, airbnbPreferencesProvider2);
    }

    public static PullStringsScheduler proxyProvidePullStringsScheduler(Context context, AirbnbPreferences airbnbPreferences) {
        return CoreModule.providePullStringsScheduler(context, airbnbPreferences);
    }
}
