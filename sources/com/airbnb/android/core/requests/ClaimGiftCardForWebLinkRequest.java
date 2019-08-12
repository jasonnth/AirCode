package com.airbnb.android.core.requests;

import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.responses.ClaimGiftCardResponse;
import org.json.JSONException;
import org.json.JSONObject;

public class ClaimGiftCardForWebLinkRequest extends ClaimGiftCardRequest {
    private static final String PARAM_TOKEN = "token";
    private static final String PARAM_UPDATE_TYPE = "update_type";
    private static final String TYPE_CLAIM_WITH_TOKEN = "claim_with_token";
    private final String verificationToken;

    public ClaimGiftCardForWebLinkRequest(String code, String verificationToken2, BaseRequestListener<ClaimGiftCardResponse> listener) {
        super(code, listener);
        this.verificationToken = verificationToken2;
    }

    public String getBody() {
        try {
            return new JSONObject().put(PARAM_UPDATE_TYPE, TYPE_CLAIM_WITH_TOKEN).put("token", this.verificationToken).toString();
        } catch (JSONException e) {
            BugsnagWrapper.notify((Throwable) e);
            return "";
        }
    }
}
