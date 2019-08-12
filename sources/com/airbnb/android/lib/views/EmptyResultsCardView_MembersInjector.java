package com.airbnb.android.lib.views;

import com.airbnb.android.core.utils.MemoryUtils;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class EmptyResultsCardView_MembersInjector implements MembersInjector<EmptyResultsCardView> {
    static final /* synthetic */ boolean $assertionsDisabled = (!EmptyResultsCardView_MembersInjector.class.desiredAssertionStatus());
    private final Provider<MemoryUtils> memoryUtilsProvider;

    public EmptyResultsCardView_MembersInjector(Provider<MemoryUtils> memoryUtilsProvider2) {
        if ($assertionsDisabled || memoryUtilsProvider2 != null) {
            this.memoryUtilsProvider = memoryUtilsProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<EmptyResultsCardView> create(Provider<MemoryUtils> memoryUtilsProvider2) {
        return new EmptyResultsCardView_MembersInjector(memoryUtilsProvider2);
    }

    public void injectMembers(EmptyResultsCardView instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.memoryUtils = (MemoryUtils) this.memoryUtilsProvider.get();
    }

    public static void injectMemoryUtils(EmptyResultsCardView instance, Provider<MemoryUtils> memoryUtilsProvider2) {
        instance.memoryUtils = (MemoryUtils) memoryUtilsProvider2.get();
    }
}
