package com.airbnb.android.registration.responses;

import android.text.TextUtils;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.android.core.models.Account;
import com.airbnb.android.core.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SocialSignupResponse extends BaseResponse {
    private static final String KEY_EMAIL = "email";
    @JsonProperty("social_signup_operation")
    public SocialSignup socialSignup;

    public static class SocialSignup {
        @JsonProperty("account")
        public Account account;
        @JsonProperty("readonly_fields")
        public String[] readOnlyFields;
        @JsonProperty("user")
        public SocialSignupUser socialSignupUser;
    }

    public static class SocialSignupUser {
        @JsonProperty("birthdate")
        public String birthDate;
        @JsonProperty("email")
        public String email;
        @JsonProperty("first_name")
        public String firstName;
        @JsonProperty("last_name")
        public String lastName;
    }

    public boolean isEmailReadOnly() {
        String[] readOnlyFields = this.socialSignup.readOnlyFields;
        if (readOnlyFields == null) {
            return false;
        }
        for (String field : readOnlyFields) {
            if (TextUtils.equals(field, "email")) {
                return true;
            }
        }
        return false;
    }

    public boolean hasAccountData() {
        if (this.socialSignup.account == null || this.socialSignup.account.getUser() == null) {
            return false;
        }
        User user = this.socialSignup.account.getUser();
        if (user.hasEmailAddress() || !TextUtils.isEmpty(user.getFirstName()) || !TextUtils.isEmpty(user.getLastName()) || user.getBirthdate() != null) {
            return true;
        }
        return false;
    }
}
