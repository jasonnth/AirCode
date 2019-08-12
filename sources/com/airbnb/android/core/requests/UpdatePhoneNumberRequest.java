package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.FormUrlRequest;
import com.airbnb.airrequest.QueryStrap;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.utils.MiscUtils;
import java.lang.reflect.Type;

public class UpdatePhoneNumberRequest extends FormUrlRequest<Object> {
    private static final String PROPS_PHONE_CODE = "phone_number_verification_code";
    private static final String PROPS_PHONE_NUMBER = "phone_number";
    private static final String PROPS_PHONE_NUMBER_VERIFICATION_SECRET = "phone_number_verification_secret";
    private static final String PROPS_PHONE_VERIFICATION_METHOD = "phone_number_verification_method";
    private static final String PROPS_PHONE_VERIFICATION_METHOD_FB = "fb";
    private boolean isFBAutoPhoneNumberVerification = false;
    private final String phoneNumber;
    private String verificationCode;

    @Deprecated
    public static UpdatePhoneNumberRequest addPhoneNumber(String phoneNumber2, BaseRequestListener<Object> listener) {
        return new UpdatePhoneNumberRequest(phoneNumber2, null, listener);
    }

    @Deprecated
    public static UpdatePhoneNumberRequest verifyPhoneNumber(String verificationCode2, BaseRequestListener<Object> listener) {
        return new UpdatePhoneNumberRequest(null, verificationCode2, listener);
    }

    @Deprecated
    private UpdatePhoneNumberRequest(String phoneNumber2, String verificationCode2, BaseRequestListener<Object> listener) {
        withListener(listener);
        this.phoneNumber = phoneNumber2;
        this.verificationCode = verificationCode2;
    }

    public static UpdatePhoneNumberRequest requestConfirmationCode(String phoneNumber2) {
        return new UpdatePhoneNumberRequest(phoneNumber2, null);
    }

    public static UpdatePhoneNumberRequest verifyPhoneNumber(String verificationCode2) {
        return new UpdatePhoneNumberRequest(null, verificationCode2);
    }

    public static UpdatePhoneNumberRequest autoVerifyPhoneNumberWithFBAccountKit(String phoneNumber2) {
        return new UpdatePhoneNumberRequest(phoneNumber2);
    }

    private UpdatePhoneNumberRequest(String phoneNumber2, String verificationCode2) {
        this.phoneNumber = phoneNumber2;
        this.verificationCode = verificationCode2;
    }

    private UpdatePhoneNumberRequest(String phoneNumber2) {
        this.phoneNumber = phoneNumber2;
        this.isFBAutoPhoneNumberVerification = true;
    }

    public String getPath() {
        return "account/update";
    }

    public QueryStrap getFields() {
        QueryStrap body = QueryStrap.make();
        if (this.isFBAutoPhoneNumberVerification) {
            return body.mo7895kv(PROPS_PHONE_NUMBER, this.phoneNumber).mo7895kv(PROPS_PHONE_VERIFICATION_METHOD, PROPS_PHONE_VERIFICATION_METHOD_FB).mo7895kv(PROPS_PHONE_NUMBER_VERIFICATION_SECRET, getSecret());
        }
        if (this.verificationCode != null) {
            return body.mo7895kv(PROPS_PHONE_CODE, this.verificationCode);
        }
        return body.mo7895kv(PROPS_PHONE_NUMBER, this.phoneNumber);
    }

    private String getSecret() {
        return MiscUtils.sha1Hash(String.valueOf(CoreApplication.instance().component().accountManager().getCurrentUser().getId()));
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public Type successResponseType() {
        return Object.class;
    }
}
