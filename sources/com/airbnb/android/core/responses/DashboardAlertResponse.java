package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.DashboardAlert;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DashboardAlertResponse extends BaseResponse {
    @JsonProperty("dashboard_alert")
    public DashboardAlert dashboardAlert;
}
