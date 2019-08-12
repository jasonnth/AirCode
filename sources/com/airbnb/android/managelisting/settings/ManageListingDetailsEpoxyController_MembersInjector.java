package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.utils.SharedPrefsHelper;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ManageListingDetailsEpoxyController_MembersInjector implements MembersInjector<ManageListingDetailsEpoxyController> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ManageListingDetailsEpoxyController_MembersInjector.class.desiredAssertionStatus());
    private final Provider<SharedPrefsHelper> sharedPrefsHelperProvider;

    public ManageListingDetailsEpoxyController_MembersInjector(Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        if ($assertionsDisabled || sharedPrefsHelperProvider2 != null) {
            this.sharedPrefsHelperProvider = sharedPrefsHelperProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<ManageListingDetailsEpoxyController> create(Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        return new ManageListingDetailsEpoxyController_MembersInjector(sharedPrefsHelperProvider2);
    }

    public void injectMembers(ManageListingDetailsEpoxyController instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.sharedPrefsHelper = (SharedPrefsHelper) this.sharedPrefsHelperProvider.get();
    }

    public static void injectSharedPrefsHelper(ManageListingDetailsEpoxyController instance, Provider<SharedPrefsHelper> sharedPrefsHelperProvider2) {
        instance.sharedPrefsHelper = (SharedPrefsHelper) sharedPrefsHelperProvider2.get();
    }
}
