package com.airbnb.android.cohosting.adapters;

import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class ListingManagersPickerAdapter_MembersInjector implements MembersInjector<ListingManagersPickerAdapter> {
    static final /* synthetic */ boolean $assertionsDisabled = (!ListingManagersPickerAdapter_MembersInjector.class.desiredAssertionStatus());
    private final Provider<CohostingManagementJitneyLogger> cohostingManagementJitneyLoggerProvider;

    public ListingManagersPickerAdapter_MembersInjector(Provider<CohostingManagementJitneyLogger> cohostingManagementJitneyLoggerProvider2) {
        if ($assertionsDisabled || cohostingManagementJitneyLoggerProvider2 != null) {
            this.cohostingManagementJitneyLoggerProvider = cohostingManagementJitneyLoggerProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<ListingManagersPickerAdapter> create(Provider<CohostingManagementJitneyLogger> cohostingManagementJitneyLoggerProvider2) {
        return new ListingManagersPickerAdapter_MembersInjector(cohostingManagementJitneyLoggerProvider2);
    }

    public void injectMembers(ListingManagersPickerAdapter instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.cohostingManagementJitneyLogger = (CohostingManagementJitneyLogger) this.cohostingManagementJitneyLoggerProvider.get();
    }

    public static void injectCohostingManagementJitneyLogger(ListingManagersPickerAdapter instance, Provider<CohostingManagementJitneyLogger> cohostingManagementJitneyLoggerProvider2) {
        instance.cohostingManagementJitneyLogger = (CohostingManagementJitneyLogger) cohostingManagementJitneyLoggerProvider2.get();
    }
}
