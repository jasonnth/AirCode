package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.identity.ChooseProfilePhotoController;
import com.airbnb.android.core.utils.PhotoCompressor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideChooseProfilePhotoControllerFactory implements Factory<ChooseProfilePhotoController> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideChooseProfilePhotoControllerFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final Provider<PhotoCompressor> photoCompressorProvider;

    public CoreModule_ProvideChooseProfilePhotoControllerFactory(Provider<Context> contextProvider2, Provider<PhotoCompressor> photoCompressorProvider2) {
        if ($assertionsDisabled || contextProvider2 != null) {
            this.contextProvider = contextProvider2;
            if ($assertionsDisabled || photoCompressorProvider2 != null) {
                this.photoCompressorProvider = photoCompressorProvider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public ChooseProfilePhotoController get() {
        return (ChooseProfilePhotoController) Preconditions.checkNotNull(CoreModule.provideChooseProfilePhotoController((Context) this.contextProvider.get(), (PhotoCompressor) this.photoCompressorProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ChooseProfilePhotoController> create(Provider<Context> contextProvider2, Provider<PhotoCompressor> photoCompressorProvider2) {
        return new CoreModule_ProvideChooseProfilePhotoControllerFactory(contextProvider2, photoCompressorProvider2);
    }

    public static ChooseProfilePhotoController proxyProvideChooseProfilePhotoController(Context context, PhotoCompressor photoCompressor) {
        return CoreModule.provideChooseProfilePhotoController(context, photoCompressor);
    }
}
