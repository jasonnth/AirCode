package com.airbnb.android.contentframework;

import android.content.Context;
import com.airbnb.airrequest.AirRequestInitializer;
import com.airbnb.android.contentframework.controller.StoryPublishController;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ContentFrameworkModule_ProvideStoryPublishControllerFactory implements Factory<StoryPublishController> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ContentFrameworkModule_ProvideStoryPublishControllerFactory.class.desiredAssertionStatus());
    private final Provider<AirRequestInitializer> airRequestInitializerProvider;
    private final Provider<Context> contextProvider;
    private final ContentFrameworkModule module;

    public ContentFrameworkModule_ProvideStoryPublishControllerFactory(ContentFrameworkModule module2, Provider<Context> contextProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            if ($assertionsDisabled || contextProvider2 != null) {
                this.contextProvider = contextProvider2;
                if ($assertionsDisabled || airRequestInitializerProvider2 != null) {
                    this.airRequestInitializerProvider = airRequestInitializerProvider2;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public StoryPublishController get() {
        return (StoryPublishController) Preconditions.checkNotNull(this.module.provideStoryPublishController((Context) this.contextProvider.get(), (AirRequestInitializer) this.airRequestInitializerProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<StoryPublishController> create(ContentFrameworkModule module2, Provider<Context> contextProvider2, Provider<AirRequestInitializer> airRequestInitializerProvider2) {
        return new ContentFrameworkModule_ProvideStoryPublishControllerFactory(module2, contextProvider2, airRequestInitializerProvider2);
    }

    public static StoryPublishController proxyProvideStoryPublishController(ContentFrameworkModule instance, Context context, AirRequestInitializer airRequestInitializer) {
        return instance.provideStoryPublishController(context, airRequestInitializer);
    }
}
