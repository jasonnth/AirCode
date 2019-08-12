package com.airbnb.android.checkin.data;

import android.content.Context;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CheckInModule_ProvideCheckInDataTableOpenHelperFactory implements Factory<CheckInDataTableOpenHelper> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CheckInModule_ProvideCheckInDataTableOpenHelperFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final CheckInModule module;

    public CheckInModule_ProvideCheckInDataTableOpenHelperFactory(CheckInModule module2, Provider<Context> contextProvider2) {
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

    public CheckInDataTableOpenHelper get() {
        return (CheckInDataTableOpenHelper) Preconditions.checkNotNull(this.module.provideCheckInDataTableOpenHelper((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<CheckInDataTableOpenHelper> create(CheckInModule module2, Provider<Context> contextProvider2) {
        return new CheckInModule_ProvideCheckInDataTableOpenHelperFactory(module2, contextProvider2);
    }
}
