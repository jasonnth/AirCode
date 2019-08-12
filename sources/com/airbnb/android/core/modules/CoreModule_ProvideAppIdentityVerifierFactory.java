package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.PostApplicationCreatedInitializer;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideAppIdentityVerifierFactory implements Factory<PostApplicationCreatedInitializer> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideAppIdentityVerifierFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;

    public CoreModule_ProvideAppIdentityVerifierFactory(Provider<Context> contextProvider2) {
        if ($assertionsDisabled || contextProvider2 != null) {
            this.contextProvider = contextProvider2;
            return;
        }
        throw new AssertionError();
    }

    public PostApplicationCreatedInitializer get() {
        return (PostApplicationCreatedInitializer) Preconditions.checkNotNull(CoreModule.provideAppIdentityVerifier((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<PostApplicationCreatedInitializer> create(Provider<Context> contextProvider2) {
        return new CoreModule_ProvideAppIdentityVerifierFactory(contextProvider2);
    }

    public static PostApplicationCreatedInitializer proxyProvideAppIdentityVerifier(Context context) {
        return CoreModule.provideAppIdentityVerifier(context);
    }
}
