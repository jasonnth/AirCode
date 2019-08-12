package com.airbnb.android.core.modules;

import com.airbnb.android.core.utils.ImageUtils;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

public final class ImageModule_ProvideImageUtilsFactory implements Factory<ImageUtils> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ImageModule_ProvideImageUtilsFactory.class.desiredAssertionStatus());
    private final ImageModule module;

    public ImageModule_ProvideImageUtilsFactory(ImageModule module2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
            return;
        }
        throw new AssertionError();
    }

    public ImageUtils get() {
        return (ImageUtils) Preconditions.checkNotNull(this.module.provideImageUtils(), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ImageUtils> create(ImageModule module2) {
        return new ImageModule_ProvideImageUtilsFactory(module2);
    }

    public static ImageUtils proxyProvideImageUtils(ImageModule instance) {
        return instance.provideImageUtils();
    }
}
