package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.utils.DebugSettings;
import com.squareup.otto.Bus;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideDebugSettingsFactory implements Factory<DebugSettings> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideDebugSettingsFactory.class.desiredAssertionStatus());
    private final Provider<Bus> busProvider;
    private final Provider<Context> contextProvider;
    private final CoreModule module;

    public CoreModule_ProvideDebugSettingsFactory(CoreModule module2, Provider<Context> contextProvider2, Provider<Bus> busProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                if ($assertionsDisabled || busProvider2 != null) {
                    this.busProvider = busProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public DebugSettings get() {
        return (DebugSettings) Preconditions.checkNotNull(this.module.provideDebugSettings((Context) this.contextProvider.get(), (Bus) this.busProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<DebugSettings> create(CoreModule module2, Provider<Context> contextProvider2, Provider<Bus> busProvider2) {
        return new CoreModule_ProvideDebugSettingsFactory(module2, contextProvider2, busProvider2);
    }

    public static DebugSettings proxyProvideDebugSettings(CoreModule instance, Context context, Bus bus) {
        return instance.provideDebugSettings(context, bus);
    }
}
