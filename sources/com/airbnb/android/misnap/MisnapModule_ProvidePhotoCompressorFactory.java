package com.airbnb.android.misnap;

import android.content.Context;
import com.airbnb.android.core.utils.PhotoCompressor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class MisnapModule_ProvidePhotoCompressorFactory implements Factory<PhotoCompressor> {
    static final /* synthetic */ boolean $assertionsDisabled = (!MisnapModule_ProvidePhotoCompressorFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final MisnapModule module;

    public MisnapModule_ProvidePhotoCompressorFactory(MisnapModule module2, Provider<Context> contextProvider2) {
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

    public PhotoCompressor get() {
        return (PhotoCompressor) Preconditions.checkNotNull(this.module.providePhotoCompressor((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<PhotoCompressor> create(MisnapModule module2, Provider<Context> contextProvider2) {
        return new MisnapModule_ProvidePhotoCompressorFactory(module2, contextProvider2);
    }
}
