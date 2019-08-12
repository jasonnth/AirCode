package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.DeviceInfo;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideDeviceIDFactory implements Factory<DeviceInfo> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideDeviceIDFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;

    public CoreModule_ProvideDeviceIDFactory(Provider<Context> contextProvider2) {
        if ($assertionsDisabled || contextProvider2 != null) {
            this.contextProvider = contextProvider2;
            return;
        }
        throw new AssertionError();
    }

    public DeviceInfo get() {
        return (DeviceInfo) Preconditions.checkNotNull(CoreModule.provideDeviceID((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<DeviceInfo> create(Provider<Context> contextProvider2) {
        return new CoreModule_ProvideDeviceIDFactory(contextProvider2);
    }

    public static DeviceInfo proxyProvideDeviceID(Context context) {
        return CoreModule.provideDeviceID(context);
    }
}
