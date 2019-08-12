package com.airbnb.android.core.requests;

import com.airbnb.airrequest.AirResponse;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.RequestMethod;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.responses.DeleteManualVerificationResponse;
import java.lang.reflect.Type;

public class DeleteManualVerificationRequest extends BaseRequestV2<DeleteManualVerificationResponse> {
    private final AirbnbAccountManager accountManager;

    public DeleteManualVerificationRequest(AirbnbAccountManager accountManager2) {
        this.accountManager = accountManager2;
    }

    public String getPath() {
        return "identities/" + AirbnbAccountManager.currentUserId();
    }

    public RequestMethod getMethod() {
        return RequestMethod.DELETE;
    }

    public AirResponse<DeleteManualVerificationResponse> transformResponse(AirResponse<DeleteManualVerificationResponse> response) {
        User user = this.accountManager.getCurrentUser();
        user.setManuallyVerified(false);
        this.accountManager.setCurrentUser(user);
        return response;
    }

    public Type successResponseType() {
        return DeleteManualVerificationResponse.class;
    }
}
