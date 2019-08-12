package com.airbnb.android.registration.requests;

import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.cohosting.utils.CohostingConstants;
import com.airbnb.android.core.models.AccountSource;
import com.airbnb.android.itinerary.requests.TimelineRequest;
import com.airbnb.android.registration.models.AccountLoginData;
import com.airbnb.android.registration.models.AccountRegistrationData;
import com.airbnb.android.registration.responses.SocialSignupResponse;
import com.airbnb.android.utils.Strap;
import java.lang.reflect.Type;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateSocialSignupRequest extends BaseRequestV2<SocialSignupResponse> {
    private final Strap params;

    public static CreateSocialSignupRequest forFetchingSocialAccountInfo(AccountLoginData loginData) {
        loginData.throwIfInvalidForFetchingInfo();
        return new CreateSocialSignupRequest(getSignUpV2CommonParams(loginData.accountSource(), loginData.authToken()).mo11640kv("_only_validate", true));
    }

    public static CreateSocialSignupRequest forFetchingSocialAccountInfoWithExistingAccount(AccountLoginData loginData) {
        loginData.throwIfInvalidForFetchingInfo();
        return new CreateSocialSignupRequest(getSignUpV2CommonParams(loginData.accountSource(), loginData.authToken()).mo11640kv("_only_validate", true).mo11640kv("_return_user_obj_if_exists", true));
    }

    public static CreateSocialSignupRequest forCreatingSocialAccount(AccountRegistrationData data) {
        data.throwIfInvalidForAccountCreation();
        return new CreateSocialSignupRequest(getSignUpV2CommonParams(data.accountSource(), data.authToken()).mo11639kv("email", data.email()).mo11640kv("promo_opt_in", data.promoOptIn()).mo11639kv(CohostingConstants.FIRST_NAME_FIELD, data.firstName()).mo11639kv("last_name", data.lastName()).mo11639kv("birthdate", data.birthDateString()));
    }

    private static Strap getSignUpV2CommonParams(AccountSource source, String authToken) {
        Strap params2 = Strap.make().mo11639kv(TimelineRequest.ARG_FORMAT, "for_signup_v2").mo11639kv("type", source.getName());
        if (source == AccountSource.Facebook || source == AccountSource.Google) {
            params2.mo11639kv("access_token", authToken);
        } else {
            params2.mo11639kv("code", authToken);
        }
        return params2;
    }

    private CreateSocialSignupRequest(Strap params2) {
        this.params = params2;
    }

    public String getPath() {
        return "social_signup_operations";
    }

    public RequestMethod getMethod() {
        return RequestMethod.POST;
    }

    public String getBody() {
        try {
            JSONObject jsonObject = new JSONObject();
            for (String property : this.params.keySet()) {
                jsonObject.put(property, this.params.get(property));
            }
            return jsonObject.toString();
        } catch (JSONException e) {
            return "";
        }
    }

    public Type successResponseType() {
        return SocialSignupResponse.class;
    }
}
