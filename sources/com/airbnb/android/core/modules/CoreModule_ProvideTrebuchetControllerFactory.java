package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.controllers.TrebuchetController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideTrebuchetControllerFactory implements Factory<TrebuchetController> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideTrebuchetControllerFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final CoreModule module;

    public CoreModule_ProvideTrebuchetControllerFactory(CoreModule module2, Provider<Context> contextProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public TrebuchetController get() {
        return (TrebuchetController) Preconditions.checkNotNull(this.module.provideTrebuchetController((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<TrebuchetController> create(CoreModule module2, Provider<Context> contextProvider2) {
        return new CoreModule_ProvideTrebuchetControllerFactory(module2, contextProvider2);
    }

    public static TrebuchetController proxyProvideTrebuchetController(CoreModule instance, Context context) {
        return instance.provideTrebuchetController(context);
    }
}
