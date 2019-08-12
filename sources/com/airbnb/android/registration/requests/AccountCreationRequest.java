package com.airbnb.android.registration.requests;

import android.text.TextUtils;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.cohosting.utils.CohostingConstants;
import com.airbnb.android.core.responses.AccountResponse;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.registration.models.AccountRegistrationData;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;

public class AccountCreationRequest extends BaseRequestV2<AccountResponse> {
    private static final String EMAIL_PARAM = "email";
    private static final String PHONE_PARAM = "phone";
    private static final String TYPE_PARAM = "type";
    private final String extraPath;
    private final Strap requestBody;
    private final RequestMethod requestMethod;

    private AccountCreationRequest(Strap params) {
        this(params, "", RequestMethod.POST);
    }

    private AccountCreationRequest(Strap params, String extraPath2, RequestMethod requestMethod2) {
        this.requestBody = params;
        this.extraPath = extraPath2;
        this.requestMethod = requestMethod2;
    }

    public String getPath() {
        return "accounts/" + this.extraPath;
    }

    public Strap getBody() {
        return this.requestBody;
    }

    public RequestMethod getMethod() {
        return this.requestMethod;
    }

    public static AccountCreationRequest forValidatingEmail(String email) {
        return new AccountCreationRequest(Strap.make().mo11639kv("type", "email").mo11639kv("email", email).mo11639kv(TimelineRequest.ARG_FORMAT, "for_validate_only").mo11640kv("_validate_only", true));
    }

    public static AccountCreationRequest forValidatingPhone(String formattedPhone) {
        return new AccountCreationRequest(Strap.make().mo11639kv("type", "phone").mo11639kv("phone", formattedPhone).mo11639kv(TimelineRequest.ARG_FORMAT, "for_validate_only").mo11640kv("_validate_only", true));
    }

    public static AccountCreationRequest forAccountRegistration(AccountRegistrationData data) {
        Strap params;
        data.throwIfInvalidForAccountCreation();
        Strap params2 = Strap.make().mo11639kv("type", data.accountSource().getName());
        switch (data.accountSource()) {
            case Email:
                params = params2.mo11639kv("email", data.email()).mix(getNonSocialCommonParams(data));
                break;
            case Phone:
                String formattedPhone = data.phone();
                if (TextUtils.isEmpty(formattedPhone)) {
                    formattedPhone = data.airPhone().formattedPhone();
                }
                String smsCode = data.phoneSMSCode();
                if (TextUtils.isEmpty(smsCode)) {
                    smsCode = data.airPhone().phoneSMSCode();
                }
                params = params2.mo11639kv("phone", formattedPhone).mo11639kv("verification_code", smsCode).mix(getNonSocialCommonParams(data));
                break;
            default:
                throw new IllegalArgumentException(String.format("AccountSource %s not yet supported!", new Object[]{data.accountSource().name()}));
        }
        return new AccountCreationRequest(params);
    }

    public static AccountCreationRequest forEditPhoneNumber(String oldNumber, String newNumber, String SMSCode, long userId) {
        return new AccountCreationRequest(Strap.make().mo11639kv("phone_number", oldNumber).mo11639kv("new_phone_number", newNumber).mo11639kv("verification_code", SMSCode), "" + userId, RequestMethod.PUT);
    }

    private static Strap getNonSocialCommonParams(AccountRegistrationData data) {
        return Strap.make().mo11639kv("password", data.password()).mo11639kv(CohostingConstants.FIRST_NAME_FIELD, data.firstName()).mo11639kv("last_name", data.lastName()).mo11640kv("promo_opt_in", data.promoOptIn()).mo11639kv("birthdate", data.birthDateString());
    }

    public Type successResponseType() {
        return AccountResponse.class;
    }
}
