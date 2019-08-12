package com.airbnb.android.login.responses;

import com.airbnb.airrequest.BaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ForgotPasswordResponse extends BaseResponse {
    @JsonProperty("forgot_password")
    ForgotPassword forgotPassword;

    static class ForgotPassword {
        @JsonProperty("id")
        public String emailId;
        @JsonProperty("message")
        public String message;
        @JsonProperty("success")
        public boolean success;

        ForgotPassword() {
        }
    }

    public boolean isSuccess() {
        return this.forgotPassword.success;
    }

    public String getMessage() {
        return this.forgotPassword.message;
    }

    public String getId() {
        return this.forgotPassword.emailId;
    }
}
