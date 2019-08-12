package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.AccountVerification;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class AccountVerificationsResponse extends BaseResponse {
    @JsonProperty("verifications")
    public List<AccountVerification> accountActivationVerifications;

    public AccountVerification getAccountVerification(String type) {
        for (AccountVerification verification : this.accountActivationVerifications) {
            if (verification.getType().equals(type)) {
                return verification;
            }
        }
        return null;
    }
}
