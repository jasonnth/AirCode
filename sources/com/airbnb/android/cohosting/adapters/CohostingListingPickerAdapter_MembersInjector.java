package com.airbnb.android.cohosting.adapters;

import com.airbnb.android.core.utils.DebugSettings;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class CohostingListingPickerAdapter_MembersInjector implements MembersInjector<CohostingListingPickerAdapter> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CohostingListingPickerAdapter_MembersInjector.class.desiredAssertionStatus());
    private final Provider<DebugSettings> debugSettingsProvider;

    public CohostingListingPickerAdapter_MembersInjector(Provider<DebugSettings> debugSettingsProvider2) {
        if ($assertionsDisabled || debugSettingsProvider2 != null) {
            this.debugSettingsProvider = debugSettingsProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<CohostingListingPickerAdapter> create(Provider<DebugSettings> debugSettingsProvider2) {
        return new CohostingListingPickerAdapter_MembersInjector(debugSettingsProvider2);
    }

    public void injectMembers(CohostingListingPickerAdapter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.debugSettings = (DebugSettings) this.debugSettingsProvider.get();
    }

    public static void injectDebugSettings(CohostingListingPickerAdapter instance, Provider<DebugSettings> debugSettingsProvider2) {
        instance.debugSettings = (DebugSettings) debugSettingsProvider2.get();
    }
}
