package com.airbnb.android.misnap;

import android.content.Context;
import com.airbnb.android.core.utils.PhotoCompressor;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class MisnapModule_ProvideTakeSelfieControllerFactory implements Factory<TakeSelfieController> {
    static final /* synthetic */ boolean $assertionsDisabled = (!MisnapModule_ProvideTakeSelfieControllerFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final MisnapModule module;
    private final Provider<PhotoCompressor> photoCompressorProvider;

    public MisnapModule_ProvideTakeSelfieControllerFactory(MisnapModule module2, Provider<Context> contextProvider2, Provider<PhotoCompressor> photoCompressorProvider2) {
        if ($assertionsDisabled || module2 != null) {
            this.module = module2;
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
        throw new AssertionError();
    }

    public TakeSelfieController get() {
        return (TakeSelfieController) Preconditions.checkNotNull(this.module.provideTakeSelfieController((Context) this.contextProvider.get(), (PhotoCompressor) this.photoCompressorProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<TakeSelfieController> create(MisnapModule module2, Provider<Context> contextProvider2, Provider<PhotoCompressor> photoCompressorProvider2) {
        return new MisnapModule_ProvideTakeSelfieControllerFactory(module2, contextProvider2, photoCompressorProvider2);
    }
}
