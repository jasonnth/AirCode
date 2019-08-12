package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.RequestMethod;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Type;

public class SendCheckedInNotificationRequest extends BaseRequestV2<BaseResponse> {
    private final String confirmationCode;

    private class CheckInNotificationBody {
        @JsonProperty("status")
        int checkInStatus;

        private CheckInNotificationBody() {
            this.checkInStatus = 1;
        }
    }

    private SendCheckedInNotificationRequest(String confirmationCode2) {
        this.confirmationCode = confirmationCode2;
    }

    public static SendCheckedInNotificationRequest forConfirmationCode(String confirmationCode2) {
        return new SendCheckedInNotificationRequest(confirmationCode2);
    }

    public String getPath() {
        return "check_in_guide_notifications/" + this.confirmationCode;
    }

    public RequestMethod getMethod() {
        return RequestMethod.PUT;
    }

    public Type successResponseType() {
        return BaseResponse.class;
    }

    public Object getBody() {
        return new CheckInNotificationBody();
    }
}
