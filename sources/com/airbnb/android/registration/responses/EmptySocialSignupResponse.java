package com.airbnb.android.registration.responses;

import com.airbnb.android.registration.responses.SocialSignupResponse.SocialSignup;

public class EmptySocialSignupResponse extends SocialSignupResponse {
    public EmptySocialSignupResponse() {
        this.socialSignup = new SocialSignup();
    }

    public boolean isEmailReadOnly() {
        return false;
    }
}
