package com.airbnb.android.core.requests.notifications;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.android.core.responses.NotificationPreferencesResponse;
import java.lang.reflect.Type;
import p032rx.Observer;

public class NotificationPreferencesRequest extends BaseRequestV2<NotificationPreferencesResponse> {
    public NotificationPreferencesRequest(BaseRequestListener<NotificationPreferencesResponse> listener) {
        withListener((Observer) listener);
    }

    public String getPath() {
        return "air_notification_settings";
    }

    public Type successResponseType() {
        return NotificationPreferencesResponse.class;
    }
}
