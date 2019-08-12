package com.airbnb.android.lib.fragments.verifiedid;

import com.airbnb.android.core.utils.SharedPrefsHelper;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class OnlineIdChildFragment_MembersInjector implements MembersInjector<OnlineIdChildFragment> {
    static final /* synthetic */ boolean $assertionsDisabled = (!OnlineIdChildFragment_MembersInjector.class.desiredAssertionStatus());
    private final Provider<SharedPrefsHelper> mPrefsHelperProvider;

    public OnlineIdChildFragment_MembersInjector(Provider<SharedPrefsHelper> mPrefsHelperProvider2) {
        if ($assertionsDisabled || mPrefsHelperProvider2 != null) {
            this.mPrefsHelperProvider = mPrefsHelperProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<OnlineIdChildFragment> create(Provider<SharedPrefsHelper> mPrefsHelperProvider2) {
        return new OnlineIdChildFragment_MembersInjector(mPrefsHelperProvider2);
    }

    public void injectMembers(OnlineIdChildFragment instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mPrefsHelper = (SharedPrefsHelper) this.mPrefsHelperProvider.get();
    }

    public static void injectMPrefsHelper(OnlineIdChildFragment instance, Provider<SharedPrefsHelper> mPrefsHelperProvider2) {
        instance.mPrefsHelper = (SharedPrefsHelper) mPrefsHelperProvider2.get();
    }
}
