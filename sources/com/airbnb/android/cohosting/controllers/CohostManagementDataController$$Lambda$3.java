package com.airbnb.android.cohosting.controllers;

import com.airbnb.android.core.models.ListingManager;
import com.google.common.base.Predicate;

final /* synthetic */ class CohostManagementDataController$$Lambda$3 implements Predicate {
    private final CohostManagementDataController arg$1;

    private CohostManagementDataController$$Lambda$3(CohostManagementDataController cohostManagementDataController) {
        this.arg$1 = cohostManagementDataController;
    }

    public static Predicate lambdaFactory$(CohostManagementDataController cohostManagementDataController) {
        return new CohostManagementDataController$$Lambda$3(cohostManagementDataController);
    }

    public boolean apply(Object obj) {
        return CohostManagementDataController.lambda$setCurrentUserValues$1(this.arg$1, (ListingManager) obj);
    }
}
