package com.airbnb.android.lib;

import com.airbnb.android.core.LogAirInitializer;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AirbnbApplication_MembersInjector implements MembersInjector<AirbnbApplication> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AirbnbApplication_MembersInjector.class.desiredAssertionStatus());
    private final Provider<LogAirInitializer> logAirInitializerProvider;

    public AirbnbApplication_MembersInjector(Provider<LogAirInitializer> logAirInitializerProvider2) {
        if ($assertionsDisabled || logAirInitializerProvider2 != null) {
            this.logAirInitializerProvider = logAirInitializerProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<AirbnbApplication> create(Provider<LogAirInitializer> logAirInitializerProvider2) {
        return new AirbnbApplication_MembersInjector(logAirInitializerProvider2);
    }

    public void injectMembers(AirbnbApplication instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.logAirInitializer = (LogAirInitializer) this.logAirInitializerProvider.get();
    }

    public static void injectLogAirInitializer(AirbnbApplication instance, Provider<LogAirInitializer> logAirInitializerProvider2) {
        instance.logAirInitializer = (LogAirInitializer) logAirInitializerProvider2.get();
    }
}
