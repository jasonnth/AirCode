package com.airbnb.android.login.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.login.responses.ForgotPasswordResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;

public class ForgotPasswordRequest extends BaseRequestV2<ForgotPasswordResponse> {
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private static final String RESOURCE_ID_RESET_PASSWORD = "reset_password";
    private final String extraPath;
    private final Strap headers;
    private final Strap params;
    private final RequestMethod requestMethod;

    public enum PhoneForgotPasswordStep {
        RequestSMS,
        VerifySMS
    }

    public static ForgotPasswordRequest forEmail(String email) {
        return new ForgotPasswordRequest(new Strap().mo11639kv("email", email), "", RequestMethod.POST, null);
    }

    @Deprecated
    private static ForgotPasswordRequest forPhone(String phone) {
        return new ForgotPasswordRequest(new Strap().mo11639kv("phone", phone), "", RequestMethod.POST, null);
    }

    @Deprecated
    private static ForgotPasswordRequest forPhoneWithCode(String phone, String verificationCode) {
        return new ForgotPasswordRequest(new Strap().mo11639kv("phone", phone).mo11639kv("verification_code", verificationCode), "", RequestMethod.POST, null);
    }

    public static ForgotPasswordRequest forPhoneResetPassword(AirPhone airPhone, String newPassword, String retypePassword) {
        return new ForgotPasswordRequest(new Strap().mo11639kv("verification_code", airPhone.phoneSMSCode()).mo11639kv("new_password", newPassword).mo11639kv("retype_password", retypePassword), airPhone.formattedPhone(), RequestMethod.PUT, null);
    }

    public static ForgotPasswordRequest forEmailResetPasswordVeirfySecret(String email_secret) {
        return new ForgotPasswordRequest(null, RESOURCE_ID_RESET_PASSWORD, RequestMethod.GET, new Strap().mo11639kv("Email-Secret", email_secret));
    }

    public static ForgotPasswordRequest forEmailResetPassword(String email, String email_secret, String newPassword, String retypePassword) {
        return new ForgotPasswordRequest(new Strap().mo11639kv("new_password", newPassword).mo11639kv("retype_password", retypePassword).mo11639kv("email_secret", email_secret).mo11639kv("email", email).mo11639kv("type", "email"), RESOURCE_ID_RESET_PASSWORD, RequestMethod.PUT, null);
    }

    public static ForgotPasswordRequest forPhoneForgotPassword(PhoneForgotPasswordStep step, AirPhone airPhone) {
        switch (step) {
            case RequestSMS:
                return forPhone(airPhone.formattedPhone());
            case VerifySMS:
                return forPhoneWithCode(airPhone.formattedPhone(), airPhone.phoneSMSCode());
            default:
                throw new IllegalStateException("A valid PhoneForgotPasswordStep is required");
        }
    }

    private ForgotPasswordRequest(Strap params2, String extraPath2, RequestMethod requestMethod2, Strap headers2) {
        this.params = params2;
        this.extraPath = extraPath2;
        this.requestMethod = requestMethod2;
        this.headers = headers2;
    }

    public Strap getBody() {
        return this.params;
    }

    public String getPath() {
        return "forgot_passwords/" + this.extraPath;
    }

    public RequestMethod getMethod() {
        return this.requestMethod;
    }

    public Type successResponseType() {
        return ForgotPasswordResponse.class;
    }

    public Strap getHeaders() {
        return Strap.make().mix(this.headers);
    }
}
