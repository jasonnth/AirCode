package com.airbnb.android.core.localpushnotifications;

import com.airbnb.android.core.utils.DebugSettings;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class LocalPushNotificationManager_MembersInjector implements MembersInjector<LocalPushNotificationManager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!LocalPushNotificationManager_MembersInjector.class.desiredAssertionStatus());
    private final Provider<DebugSettings> debugSettingsProvider;

    public LocalPushNotificationManager_MembersInjector(Provider<DebugSettings> debugSettingsProvider2) {
        if ($assertionsDisabled || debugSettingsProvider2 != null) {
            this.debugSettingsProvider = debugSettingsProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<LocalPushNotificationManager> create(Provider<DebugSettings> debugSettingsProvider2) {
        return new LocalPushNotificationManager_MembersInjector(debugSettingsProvider2);
    }

    public void injectMembers(LocalPushNotificationManager instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.debugSettings = (DebugSettings) this.debugSettingsProvider.get();
    }

    public static void injectDebugSettings(LocalPushNotificationManager instance, Provider<DebugSettings> debugSettingsProvider2) {
        instance.debugSettings = (DebugSettings) debugSettingsProvider2.get();
    }
}
