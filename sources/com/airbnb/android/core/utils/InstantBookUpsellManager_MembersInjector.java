package com.airbnb.android.core.utils;

import dagger.MembersInjector;
import javax.inject.Provider;

public final class InstantBookUpsellManager_MembersInjector implements MembersInjector<InstantBookUpsellManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!InstantBookUpsellManager_MembersInjector.class.desiredAssertionStatus());
    private final Provider<SharedPrefsHelper> sharedPrefsHelperProvider;

    public InstantBookUpsellManager_MembersInjector(Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        if ($assertionsDisabled || sharedPrefsHelperProvider2 != null) {
            this.sharedPrefsHelperProvider = sharedPrefsHelperProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<InstantBookUpsellManager> create(Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        return new InstantBookUpsellManager_MembersInjector(sharedPrefsHelperProvider2);
    }

    public void injectMembers(InstantBookUpsellManager instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.sharedPrefsHelper = (SharedPrefsHelper) this.sharedPrefsHelperProvider.get();
    }

    public static void injectSharedPrefsHelper(InstantBookUpsellManager instance, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        instance.sharedPrefsHelper = (SharedPrefsHelper) sharedPrefsHelperProvider2.get();
    }
}
