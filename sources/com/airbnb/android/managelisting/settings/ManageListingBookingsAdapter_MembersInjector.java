package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.utils.DebugSettings;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ManageListingBookingsAdapter_MembersInjector implements MembersInjector<ManageListingBookingsAdapter> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ManageListingBookingsAdapter_MembersInjector.class.desiredAssertionStatus());
    private final Provider<DebugSettings> debugSettingsProvider;

    public ManageListingBookingsAdapter_MembersInjector(Provider<DebugSettings> debugSettingsProvider2) {
        if ($assertionsDisabled || debugSettingsProvider2 != null) {
            this.debugSettingsProvider = debugSettingsProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<ManageListingBookingsAdapter> create(Provider<DebugSettings> debugSettingsProvider2) {
        return new ManageListingBookingsAdapter_MembersInjector(debugSettingsProvider2);
    }

    public void injectMembers(ManageListingBookingsAdapter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.debugSettings = (DebugSettings) this.debugSettingsProvider.get();
    }

    public static void injectDebugSettings(ManageListingBookingsAdapter instance, Provider<DebugSettings> debugSettingsProvider2) {
        instance.debugSettings = (DebugSettings) debugSettingsProvider2.get();
    }
}
