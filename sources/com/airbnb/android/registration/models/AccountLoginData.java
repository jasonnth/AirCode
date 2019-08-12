package com.airbnb.android.registration.models;

import android.os.Parcelable;
import com.airbnb.android.core.models.AccountSource;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.core.utils.Check;

public abstract class AccountLoginData implements Parcelable {

    public static abstract class Builder {
        public abstract Builder accountSource(AccountSource accountSource);

        public abstract Builder airPhone(AirPhone airPhone);

        public abstract Builder authToken(String str);

        public abstract AccountLoginData build();

        public abstract Builder email(String str);

        public abstract Builder firstName(String str);

        public abstract Builder mowebAccessToken(String str);

        public abstract Builder mowebAuthId(String str);

        public abstract Builder password(String str);

        @Deprecated
        public abstract Builder phone(String str);

        public abstract Builder profilePicture(String str);
    }

    public abstract AccountSource accountSource();

    public abstract AirPhone airPhone();

    public abstract String authToken();

    public abstract String email();

    public abstract String firstName();

    public abstract String mowebAccessToken();

    public abstract String mowebAuthId();

    public abstract String password();

    @Deprecated
    public abstract String phone();

    public abstract String profilePicture();

    public static Builder builder(AccountSource source) {
        return new Builder().accountSource(source);
    }

    public static AccountLoginData fromRegistrationData(AccountRegistrationData data) {
        return builder(data.accountSource()).email(data.email()).phone(data.phone()).airPhone(data.airPhone()).password(data.password()).authToken(data.authToken()).firstName(data.firstName()).build();
    }

    public void throwIfInvalidForLogin() {
        switch (accountSource()) {
            case Email:
                Check.notEmpty(email(), "Missing email for email sign in");
                Check.notEmpty(password(), "Missing password for email sign in");
                return;
            case Phone:
                checkPhone();
                Check.notEmpty(password(), "Missing password for phone number sign in");
                return;
            case WeChat:
            case Weibo:
            case Facebook:
            case Google:
                Check.notEmpty(authToken(), "Missing authToken for social sign in");
                return;
            case MoWeb:
                Check.notEmpty(mowebAuthId(), "missing id");
                Check.notEmpty(mowebAccessToken(), "missing access token");
                return;
            default:
                return;
        }
    }

    private void checkPhone() {
        if (airPhone() != null) {
            Check.notEmpty(airPhone().formattedPhone(), "Missing phone number for phone number sign in");
        } else {
            Check.notEmpty(phone(), "Missing phone number for phone number sign in");
        }
    }

    public void throwIfInvalidForFetchingInfo() {
        Check.state(accountSource().isSocialNetwork(), "AccountSource is not social for fetching social info");
        Check.notEmpty(authToken(), "Missing authToken for fetching social info");
    }
}
