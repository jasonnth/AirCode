package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BraintreeClientTokenResponse extends BaseResponse {
    @JsonProperty("braintree_client_token")
    public BraintreeClientToken braintreeClientToken;

    public static final class BraintreeClientToken {
        @JsonProperty("token")
        public String token;
    }

    public String getToken() {
        if (this.braintreeClientToken == null) {
            return null;
        }
        return this.braintreeClientToken.token;
    }
}
