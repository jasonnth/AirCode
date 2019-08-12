package com.airbnb.android.core.views;

import com.airbnb.android.core.utils.MemoryUtils;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class AnimatedDrawableView_MembersInjector implements MembersInjector<AnimatedDrawableView> {
    static final /* synthetic */ boolean $assertionsDisabled = (!AnimatedDrawableView_MembersInjector.class.desiredAssertionStatus());
    private final Provider<MemoryUtils> memoryUtilsProvider;

    public AnimatedDrawableView_MembersInjector(Provider<MemoryUtils> memoryUtilsProvider2) {
        if ($assertionsDisabled || memoryUtilsProvider2 != null) {
            this.memoryUtilsProvider = memoryUtilsProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<AnimatedDrawableView> create(Provider<MemoryUtils> memoryUtilsProvider2) {
        return new AnimatedDrawableView_MembersInjector(memoryUtilsProvider2);
    }

    public void injectMembers(AnimatedDrawableView instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.memoryUtils = (MemoryUtils) this.memoryUtilsProvider.get();
    }

    public static void injectMemoryUtils(AnimatedDrawableView instance, Provider<MemoryUtils> memoryUtilsProvider2) {
        instance.memoryUtils = (MemoryUtils) memoryUtilsProvider2.get();
    }
}
