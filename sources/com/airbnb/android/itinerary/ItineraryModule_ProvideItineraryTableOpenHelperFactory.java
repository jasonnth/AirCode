package com.airbnb.android.itinerary;

import android.content.Context;
import com.airbnb.android.itinerary.data.ItineraryTableOpenHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class ItineraryModule_ProvideItineraryTableOpenHelperFactory implements Factory<ItineraryTableOpenHelper> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ItineraryModule_ProvideItineraryTableOpenHelperFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;
    private final ItineraryModule module;

    public ItineraryModule_ProvideItineraryTableOpenHelperFactory(ItineraryModule module2, Provider<Context> contextProvider2) {
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

    public ItineraryTableOpenHelper get() {
        return (ItineraryTableOpenHelper) Preconditions.checkNotNull(this.module.provideItineraryTableOpenHelper((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ItineraryTableOpenHelper> create(ItineraryModule module2, Provider<Context> contextProvider2) {
        return new ItineraryModule_ProvideItineraryTableOpenHelperFactory(module2, contextProvider2);
    }
}
