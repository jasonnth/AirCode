package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.DashboardAlert;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class DashboardAlertsResponse extends BaseResponse {
    @JsonProperty("dashboard_alerts")
    public List<DashboardAlert> dashboardAlerts;

    static /* synthetic */ boolean lambda$containsUnseenAlerts$0(DashboardAlert a) {
        return !a.isViewed();
    }

    public boolean containsUnseenAlerts() {
        return FluentIterable.from((Iterable<E>) this.dashboardAlerts).anyMatch(DashboardAlertsResponse$$Lambda$1.lambdaFactory$());
    }
}
