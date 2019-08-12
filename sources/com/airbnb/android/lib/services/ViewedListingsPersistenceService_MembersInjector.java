package com.airbnb.android.lib.services;

import com.airbnb.android.core.contentproviders.ViewedListingsDatabaseHelper;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ViewedListingsPersistenceService_MembersInjector implements MembersInjector<ViewedListingsPersistenceService> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ViewedListingsPersistenceService_MembersInjector.class.desiredAssertionStatus());
    private final Provider<ViewedListingsDatabaseHelper> dbHelperProvider;

    public ViewedListingsPersistenceService_MembersInjector(Provider<ViewedListingsDatabaseHelper> dbHelperProvider2) {
        if ($assertionsDisabled || dbHelperProvider2 != null) {
            this.dbHelperProvider = dbHelperProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<ViewedListingsPersistenceService> create(Provider<ViewedListingsDatabaseHelper> dbHelperProvider2) {
        return new ViewedListingsPersistenceService_MembersInjector(dbHelperProvider2);
    }

    public void injectMembers(ViewedListingsPersistenceService instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.dbHelper = (ViewedListingsDatabaseHelper) this.dbHelperProvider.get();
    }

    public static void injectDbHelper(ViewedListingsPersistenceService instance, Provider<ViewedListingsDatabaseHelper> dbHelperProvider2) {
        instance.dbHelper = (ViewedListingsDatabaseHelper) dbHelperProvider2.get();
    }
}
