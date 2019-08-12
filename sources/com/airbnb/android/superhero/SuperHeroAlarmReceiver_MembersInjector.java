package com.airbnb.android.superhero;

import com.airbnb.android.core.superhero.SuperHeroManager;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class SuperHeroAlarmReceiver_MembersInjector implements MembersInjector<SuperHeroAlarmReceiver> {
    static final /* synthetic */ boolean $assertionsDisabled = (!SuperHeroAlarmReceiver_MembersInjector.class.desiredAssertionStatus());
    private final Provider<SuperHeroManager> superHeroManagerProvider;

    public SuperHeroAlarmReceiver_MembersInjector(Provider<SuperHeroManager> superHeroManagerProvider2) {
        if ($assertionsDisabled || superHeroManagerProvider2 != null) {
            this.superHeroManagerProvider = superHeroManagerProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<SuperHeroAlarmReceiver> create(Provider<SuperHeroManager> superHeroManagerProvider2) {
        return new SuperHeroAlarmReceiver_MembersInjector(superHeroManagerProvider2);
    }

    public void injectMembers(SuperHeroAlarmReceiver instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.superHeroManager = (SuperHeroManager) this.superHeroManagerProvider.get();
    }

    public static void injectSuperHeroManager(SuperHeroAlarmReceiver instance, Provider<SuperHeroManager> superHeroManagerProvider2) {
        instance.superHeroManager = (SuperHeroManager) superHeroManagerProvider2.get();
    }
}
