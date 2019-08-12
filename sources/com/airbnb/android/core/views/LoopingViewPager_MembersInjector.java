package com.airbnb.android.core.views;

import com.airbnb.android.core.utils.MemoryUtils;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class LoopingViewPager_MembersInjector implements MembersInjector<LoopingViewPager> {
    static final /* synthetic */ boolean $assertionsDisabled = (!LoopingViewPager_MembersInjector.class.desiredAssertionStatus());
    private final Provider<MemoryUtils> mMemoryUtilsProvider;

    public LoopingViewPager_MembersInjector(Provider<MemoryUtils> mMemoryUtilsProvider2) {
        if ($assertionsDisabled || mMemoryUtilsProvider2 != null) {
            this.mMemoryUtilsProvider = mMemoryUtilsProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<LoopingViewPager> create(Provider<MemoryUtils> mMemoryUtilsProvider2) {
        return new LoopingViewPager_MembersInjector(mMemoryUtilsProvider2);
    }

    public void injectMembers(LoopingViewPager instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.mMemoryUtils = (MemoryUtils) this.mMemoryUtilsProvider.get();
    }

    public static void injectMMemoryUtils(LoopingViewPager instance, Provider<MemoryUtils> mMemoryUtilsProvider2) {
        instance.mMemoryUtils = (MemoryUtils) mMemoryUtilsProvider2.get();
    }
}
