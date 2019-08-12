package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.DashboardAlert;
import com.airbnb.android.core.models.DashboardAlert.DashboardAlertType;
import com.airbnb.android.core.responses.DashboardAlertResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;
import retrofit2.Query;

public class UpdateAlertRequest extends BaseRequestV2<DashboardAlertResponse> {
    private final long alertId;
    private final boolean isActionNotification;
    private final Object requestBody;

    public static UpdateAlertRequest markAsViewed(DashboardAlert alert) {
        return new UpdateAlertRequest(alert, Strap.make().mo11640kv("viewed", true));
    }

    public UpdateAlertRequest(DashboardAlert alert, Object requestBody2) {
        this.alertId = alert.getId();
        this.isActionNotification = DashboardAlertType.ActionNotfication.equals(alert.getAlertTypeEnum());
        this.requestBody = requestBody2;
    }

    public Collection<Query> getQueryParams() {
        return QueryStrap.make().mix((Map<String, String>) Strap.make().mo11640kv("is_action_notification", this.isActionNotification));
    }

    public String getPath() {
        return "dashboard_alerts/" + this.alertId;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Object getBody() {
        return this.requestBody;
    }

    public Type successResponseType() {
        return DashboardAlertResponse.class;
    }
}
