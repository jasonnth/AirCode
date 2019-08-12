package com.airbnb.android.login;

import com.airbnb.android.core.explore.ChildScope;
import com.airbnb.android.login.p339ui.PhoneForgotPasswordConfirmSMSCodeFragment;
import com.airbnb.android.login.p339ui.PhoneForgotPasswordFragment;

@ChildScope
public interface LoginComponent {

    public interface Builder {
        LoginComponent build();
    }

    void inject(PhoneForgotPasswordConfirmSMSCodeFragment phoneForgotPasswordConfirmSMSCodeFragment);

    void inject(PhoneForgotPasswordFragment phoneForgotPasswordFragment);
}
