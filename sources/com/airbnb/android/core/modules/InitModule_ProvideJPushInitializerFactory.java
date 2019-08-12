package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.JPushInitializer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class InitModule_ProvideJPushInitializerFactory implements Factory<JPushInitializer> {
    static final /* synthetic */ boolean $assertionsDisabled = (!InitModule_ProvideJPushInitializerFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final InitModule module;

    public InitModule_ProvideJPushInitializerFactory(InitModule module2, Provider<Context> contextProvider2) {
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

    public JPushInitializer get() {
        return (JPushInitializer) Preconditions.checkNotNull(this.module.provideJPushInitializer((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<JPushInitializer> create(InitModule module2, Provider<Context> contextProvider2) {
        return new InitModule_ProvideJPushInitializerFactory(module2, contextProvider2);
    }

    public static JPushInitializer proxyProvideJPushInitializer(InitModule instance, Context context) {
        return instance.provideJPushInitializer(context);
    }
}
