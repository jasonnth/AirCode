package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.analytics.PushAnalytics;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.erf.p010db.ErfExperimentsModel;
import java.lang.reflect.Type;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class PushNotificationConversionRequest extends BaseRequestV2<Object> {
    public static final String PUSH_NOTIFICATION_ID_KEY = "push_notification_id";
    public static final String PUSH_TYPE = "push_type";
    public static final String SECRET_KEY = "secret";
    private final String pushNotificationId;
    private final String secret;

    public PushNotificationConversionRequest(String pushNotificationId2, String secret2) {
        this.pushNotificationId = pushNotificationId2;
        this.secret = secret2;
    }

    public String getPath() {
        return "air_notification_receipts";
    }

    public String getBody() {
        try {
            return new JSONObject().put("air_notification_type", "android_push").put(ErfExperimentsModel.TIMESTAMP, System.currentTimeMillis()).put("uuid", UUID.randomUUID().toString()).put("payload", new JSONObject().put(PUSH_NOTIFICATION_ID_KEY, this.pushNotificationId).put("secret", this.secret)).toString();
        } catch (JSONException e) {
            BugsnagWrapper.notify((Throwable) e);
            return "";
        }
    }

    public static void sendPushNotificationOpenedEvent(String pushId, String secret2, String pushType) {
        new PushNotificationConversionRequest(pushId, secret2).execute(NetworkUtil.singleFireExecutor());
        PushAnalytics.trackOperation(pushType, pushId, "push_opened");
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return Object.class;
    }
}
