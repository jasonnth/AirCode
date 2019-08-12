package com.airbnb.android.registration.models;

import android.os.Parcelable;
import android.text.TextUtils;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.models.AccountSource;
import com.airbnb.android.core.models.AirPhone;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.registration.responses.SocialSignupResponse;

public abstract class AccountRegistrationData implements Parcelable {

    public static abstract class Builder {
        public abstract Builder accountSource(AccountSource accountSource);

        public abstract Builder airPhone(AirPhone airPhone);

        public abstract Builder authToken(String str);

        public abstract Builder birthDateString(String str);

        public abstract AccountRegistrationData build();

        public abstract Builder email(String str);

        public abstract Builder firstName(String str);

        public abstract Builder lastName(String str);

        public abstract Builder password(String str);

        @Deprecated
        public abstract Builder phone(String str);

        @Deprecated
        public abstract Builder phoneSMSCode(String str);

        public abstract Builder promoOptIn(boolean z);
    }

    public abstract AccountSource accountSource();

    public abstract AirPhone airPhone();

    public abstract String authToken();

    public abstract String birthDateString();

    public abstract String email();

    public abstract String firstName();

    public abstract String lastName();

    public abstract String password();

    @Deprecated
    public abstract String phone();

    @Deprecated
    public abstract String phoneSMSCode();

    public abstract boolean promoOptIn();

    public abstract Builder toBuilder();

    public static Builder builder() {
        return new Builder().promoOptIn(true);
    }

    public static AccountRegistrationData updateAccountDataForStep(AccountRegistrationStep step, AccountRegistrationData oldData, AccountRegistrationData newData) {
        Builder builder = oldData.toBuilder();
        switch (step) {
            case AccountIdentifier:
                builder.email(newData.email());
                builder.airPhone(newData.airPhone());
                builder.accountSource(newData.accountSource());
                builder.promoOptIn(newData.promoOptIn());
                break;
            case Password:
                builder.password(newData.password());
                break;
            case Names:
                builder.firstName(newData.firstName());
                builder.lastName(newData.lastName());
                break;
            case Birthday:
                builder.birthDateString(newData.birthDateString());
                break;
            default:
                throw new IllegalArgumentException("Unexpected AccountRegistrationStep: " + step.name());
        }
        return builder.build();
    }

    public boolean isSocialSignUp() {
        return accountSource() != null && accountSource().isSocialNetwork();
    }

    public boolean isSocialSignupDataEmpty() {
        if (accountSource() == null || authToken() == null) {
            return true;
        }
        if (!TextUtils.isEmpty(email()) || !TextUtils.isEmpty(firstName()) || !TextUtils.isEmpty(lastName()) || !TextUtils.isEmpty(birthDateString())) {
            return false;
        }
        return true;
    }

    public void throwIfInvalidForAccountCreation() {
        Check.notEmpty(firstName(), "Missing first name for sign up");
        Check.notEmpty(lastName(), "Missing last name for sign up");
        Check.notEmpty(birthDateString(), "Missing birthdate for sign up");
        switch (accountSource()) {
            case Email:
                Check.notEmpty(email(), "Missing email for email sign up");
                Check.notEmpty(password(), "Missing password for email sign up");
                return;
            case Phone:
                checkPhone();
                Check.notEmpty(password(), "Missing password for phone number sign up");
                return;
            case WeChat:
            case Weibo:
            case Facebook:
            case Google:
                Check.notEmpty(authToken(), "Missing authToken for social sign up");
                return;
            default:
                return;
        }
    }

    private void checkPhone() {
        if (airPhone() != null) {
            Check.notEmpty(airPhone().formattedPhone(), "Missing phone number for phone number sign up");
            Check.notEmpty(airPhone().phoneSMSCode(), "Missing sms code for phone number sign up");
            return;
        }
        Check.notEmpty(phone(), "Missing phone number for phone number sign up");
        Check.notEmpty(phoneSMSCode(), "Missing sms code for phone number sign up");
    }

    public static AccountRegistrationData forConfirmSocialSignup(AccountSource source, String authToken, SocialSignupResponse response) {
        User user = new User();
        if (response.hasAccountData()) {
            user = response.socialSignup.account.getUser();
        }
        return builder().accountSource(source).authToken(authToken).email(user.getEmailAddress()).firstName(user.getFirstName()).lastName(user.getLastName()).birthDateString(user.hasBirthdate() ? user.getBirthdate().getIsoDateString() : null).build();
    }

    public String getRegistrationServiceForAnalytics() {
        return accountSource() == null ? RegistrationAnalytics.DIRECT : accountSource().getServiceNameForAnalytics();
    }
}
