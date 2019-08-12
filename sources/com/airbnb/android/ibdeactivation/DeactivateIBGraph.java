package com.airbnb.android.ibdeactivation;

import com.airbnb.android.core.BaseGraph;

public interface DeactivateIBGraph extends BaseGraph {
    void inject(DeactivateIBActivity deactivateIBActivity);

    void inject(DeactivateIBAnalytics deactivateIBAnalytics);

    void inject(DeactivateIBLandingFragment deactivateIBLandingFragment);

    void inject(DeactivateIBReasonsAdapter deactivateIBReasonsAdapter);

    void inject(ReviewAllRequestsFragment reviewAllRequestsFragment);
}
