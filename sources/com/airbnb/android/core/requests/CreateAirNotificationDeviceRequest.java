package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.responses.AirNotificationDeviceResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;

public class CreateAirNotificationDeviceRequest extends BaseRequestV2<AirNotificationDeviceResponse> {
    private final String deviceType;
    private final String token;

    public static CreateAirNotificationDeviceRequest newInstance(String deviceType2, String token2) {
        return new CreateAirNotificationDeviceRequest(deviceType2, token2);
    }

    private CreateAirNotificationDeviceRequest(String deviceType2, String token2) {
        this.deviceType = deviceType2;
        this.token = token2;
    }

    public String getPath() {
        return "air_notification_devices";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Object getBody() {
        return Strap.make().mo11639kv("device_type", this.deviceType).mo11639kv("token", this.token);
    }

    public Type successResponseType() {
        return AirNotificationDeviceResponse.class;
    }
}
