package com.airbnb.android.core.modules;

import android.content.Context;
import com.airbnb.android.core.contentproviders.ViewedListingsDatabaseHelper;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

public final class CoreModule_ProvideViewedListingsDatabaseHelperFactory implements Factory<ViewedListingsDatabaseHelper> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CoreModule_ProvideViewedListingsDatabaseHelperFactory.class.desiredAssertionStatus());
    private final Provider<Context> contextProvider;

    public CoreModule_ProvideViewedListingsDatabaseHelperFactory(Provider<Context> contextProvider2) {
        if ($assertionsDisabled || contextProvider2 != null) {
            this.contextProvider = contextProvider2;
            return;
        }
        throw new AssertionError();
    }

    public ViewedListingsDatabaseHelper get() {
        return (ViewedListingsDatabaseHelper) Preconditions.checkNotNull(CoreModule.provideViewedListingsDatabaseHelper((Context) this.contextProvider.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<ViewedListingsDatabaseHelper> create(Provider<Context> contextProvider2) {
        return new CoreModule_ProvideViewedListingsDatabaseHelperFactory(contextProvider2);
    }

    public static ViewedListingsDatabaseHelper proxyProvideViewedListingsDatabaseHelper(Context context) {
        return CoreModule.provideViewedListingsDatabaseHelper(context);
    }
}
