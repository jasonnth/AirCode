package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.AirNotificationDeviceResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;

public class UpdateAirNotificationDeviceRequest extends BaseRequestV2<AirNotificationDeviceResponse> {

    /* renamed from: id */
    private final long f8492id;
    private final Object requestBody;

    public static UpdateAirNotificationDeviceRequest forDisableDevice(long airNotificationDeviceId) {
        return new UpdateAirNotificationDeviceRequest(airNotificationDeviceId, Strap.make().mo11639kv("enabled", InternalLogger.EVENT_PARAM_EXTRAS_FALSE));
    }

    private UpdateAirNotificationDeviceRequest(long id, Object requestBody2) {
        this.f8492id = id;
        this.requestBody = requestBody2;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public String getPath() {
        return "air_notification_devices/" + this.f8492id;
    }

    public Object getBody() {
        return this.requestBody;
    }

    public Type successResponseType() {
        return AirNotificationDeviceResponse.class;
    }
}
