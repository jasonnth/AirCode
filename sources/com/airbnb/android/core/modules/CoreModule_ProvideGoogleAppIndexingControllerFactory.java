package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.controllers.GoogleAppIndexingController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideGoogleAppIndexingControllerFactory implements Factory<GoogleAppIndexingController> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideGoogleAppIndexingControllerFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;

    public CoreModule_ProvideGoogleAppIndexingControllerFactory(Provider<Context> contextProvider2) {
        if ($assertionsDisabled || contextProvider2 != null) {
            this.contextProvider = contextProvider2;
            return;
        }
        throw new AssertionError();
    }

    public GoogleAppIndexingController get() {
        return (GoogleAppIndexingController) Preconditions.checkNotNull(CoreModule.provideGoogleAppIndexingController((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<GoogleAppIndexingController> create(Provider<Context> contextProvider2) {
        return new CoreModule_ProvideGoogleAppIndexingControllerFactory(contextProvider2);
    }

    public static GoogleAppIndexingController proxyProvideGoogleAppIndexingController(Context context) {
        return CoreModule.provideGoogleAppIndexingController(context);
    }
}
