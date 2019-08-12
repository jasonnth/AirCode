package com.airbnb.android.registration;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.AccountSource;
import com.airbnb.android.registration.models.AccountLoginData;

final /* synthetic */ class EmailRegistrationFragment$$Lambda$3 implements OnClickListener {
    private final EmailRegistrationFragment arg$1;

    private EmailRegistrationFragment$$Lambda$3(EmailRegistrationFragment emailRegistrationFragment) {
        this.arg$1 = emailRegistrationFragment;
    }

    public static OnClickListener lambdaFactory$(EmailRegistrationFragment emailRegistrationFragment) {
        return new EmailRegistrationFragment$$Lambda$3(emailRegistrationFragment);
    }

    public void onClick(View view) {
        this.arg$1.registrationController.switchToLoginWithLoginData(AccountLoginData.builder(AccountSource.Email).email(this.arg$1.email).build());
    }
}
