package com.airbnb.android.core.requests;

import android.util.Log;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.NotificationPreference;
import com.airbnb.android.core.responses.NotificationPreferencesResponse;
import java.lang.reflect.Type;
import org.json.JSONException;
import org.json.JSONObject;

public class UpdateNotificationPreferencesRequest extends BaseRequestV2<NotificationPreferencesResponse> {
    private final NotificationPreference preference;

    public UpdateNotificationPreferencesRequest(NotificationPreference preference2) {
        this.preference = preference2;
    }

    public String getPath() {
        return "air_notification_settings/" + this.preference.getId();
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public String getBody() {
        JSONObject json = new JSONObject();
        try {
            json.put("push_enabled", this.preference.isPushEnabled());
            json.put("sms_enabled", this.preference.isSmsEnabled());
        } catch (JSONException e) {
            Log.d(UpdateNotificationPreferencesRequest.class.getSimpleName(), "Error parsing JSON");
        }
        return json.toString();
    }

    public Type successResponseType() {
        return NotificationPreferencesResponse.class;
    }
}
