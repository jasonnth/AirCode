package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.core.responses.PhoneNumberVerificationResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;

public class PhoneNumberVerificationRequest extends BaseRequestV2<PhoneNumberVerificationResponse> {
    private final Strap requestBody;

    public static PhoneNumberVerificationRequest forPhoneNumberVerification(String formattedPhoneText) {
        return new PhoneNumberVerificationRequest(Strap.make().mo11639kv("phone_number", formattedPhoneText));
    }

    public static PhoneNumberVerificationRequest forPhoneNumberSMSCodeMatching(AirPhone airPhone) {
        return new PhoneNumberVerificationRequest(Strap.make().mo11639kv("phone_number", airPhone.formattedPhone()).mo11639kv("verification_code", airPhone.phoneSMSCode()));
    }

    private PhoneNumberVerificationRequest(Strap params) {
        this.requestBody = params;
    }

    public String getPath() {
        return "mobile_confirmations";
    }

    public Strap getBody() {
        return this.requestBody;
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return PhoneNumberVerificationResponse.class;
    }
}
