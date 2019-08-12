package com.airbnb.android.core.requests;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.android.core.models.DashboardAlert;
import com.airbnb.android.core.models.DashboardAlert.DashboardAlertType;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.responses.DashboardAlertsResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import retrofit2.Query;

public class DashboardAlertsRequest extends BaseRequestV2<DashboardAlertsResponse> {
    private static final String SCOPE_GUEST = "mobile_guest";
    private static final String SCOPE_HOST = "host_home";
    private final String scope;
    private final List<DashboardAlertType> supportedAlertTypes;

    public static DashboardAlertsRequest forInboxType(InboxType inboxType, Context context) {
        switch (inboxType) {
            case Guest:
                return forGuest();
            case Host:
                return forHost(context);
            default:
                throw new IllegalArgumentException("Invalid inbox type for alerts: " + inboxType);
        }
    }

    public static DashboardAlertsRequest forHost(Context context) {
        return new DashboardAlertsRequest("host_home", DashboardAlert.getSupportedHostTypes(context));
    }

    public static DashboardAlertsRequest forGuest() {
        return new DashboardAlertsRequest(SCOPE_GUEST, DashboardAlert.getSupportedGuestTypes());
    }

    private DashboardAlertsRequest(String scope2, List<DashboardAlertType> alertTypes) {
        this.scope = scope2;
        this.supportedAlertTypes = alertTypes;
    }

    private boolean isForGuest() {
        return SCOPE_GUEST.equals(this.scope);
    }

    public String getPath() {
        return "dashboard_alerts";
    }

    public Collection<Query> getQueryParams() {
        QueryStrap strap = QueryStrap.make().mo7895kv("scope", this.scope).mo7895kv(TimelineRequest.ARG_FORMAT, isForGuest() ? "for_mobile_guest_v3" : "for_mobile_host_v3");
        if (isForGuest()) {
            strap.mo7895kv("alert_types", getSupportedTypesAsString());
        }
        return strap;
    }

    private String getSupportedTypesAsString() {
        List<String> alertsKeys = new ArrayList<>(this.supportedAlertTypes.size());
        for (DashboardAlertType type : this.supportedAlertTypes) {
            alertsKeys.add(type.alertTypeFormatted);
        }
        return TextUtils.join(",", alertsKeys);
    }

    public AirResponse<DashboardAlertsResponse> transformResponse(AirResponse<DashboardAlertsResponse> response) {
        DashboardAlertsResponse data = (DashboardAlertsResponse) response.body();
        if (data.dashboardAlerts != null) {
            Iterator<DashboardAlert> it = data.dashboardAlerts.iterator();
            while (it.hasNext()) {
                if (!this.supportedAlertTypes.contains(((DashboardAlert) it.next()).getAlertTypeEnum())) {
                    it.remove();
                }
            }
        }
        return response;
    }

    public Type successResponseType() {
        return DashboardAlertsResponse.class;
    }
}
