package com.airbnb.android.cohosting.epoxycontrollers;

import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class CohostUpsellEpoxyController_MembersInjector implements MembersInjector<CohostUpsellEpoxyController> {
    static final /* synthetic */ boolean $assertionsDisabled = (!CohostUpsellEpoxyController_MembersInjector.class.desiredAssertionStatus());
    private final Provider<CohostingManagementJitneyLogger> cohostingManagementJitneyLoggerProvider;

    public CohostUpsellEpoxyController_MembersInjector(Provider<CohostingManagementJitneyLogger> cohostingManagementJitneyLoggerProvider2) {
        if ($assertionsDisabled || cohostingManagementJitneyLoggerProvider2 != null) {
            this.cohostingManagementJitneyLoggerProvider = cohostingManagementJitneyLoggerProvider2;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<CohostUpsellEpoxyController> create(Provider<CohostingManagementJitneyLogger> cohostingManagementJitneyLoggerProvider2) {
        return new CohostUpsellEpoxyController_MembersInjector(cohostingManagementJitneyLoggerProvider2);
    }

    public void injectMembers(CohostUpsellEpoxyController instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.cohostingManagementJitneyLogger = (CohostingManagementJitneyLogger) this.cohostingManagementJitneyLoggerProvider.get();
    }

    public static void injectCohostingManagementJitneyLogger(CohostUpsellEpoxyController instance, Provider<CohostingManagementJitneyLogger> cohostingManagementJitneyLoggerProvider2) {
        instance.cohostingManagementJitneyLogger = (CohostingManagementJitneyLogger) cohostingManagementJitneyLoggerProvider2.get();
    }
}
