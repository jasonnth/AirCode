package com.airbnb.android.core.responses;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountVerificationSelfiePostResponse extends BaseResponse {
    @JsonProperty("user_selfie")
    protected UserSelfie userSelfie;

    static class UserSelfie {
        @JsonProperty("image_url")
        String url;

        UserSelfie() {
        }
    }
}
