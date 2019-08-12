package com.airbnb.android.login.p339ui;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.login.C7331R;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.primitives.AirButton;

/* renamed from: com.airbnb.android.login.ui.EmailResetPasswordFragment_ViewBinding */
public class EmailResetPasswordFragment_ViewBinding implements Unbinder {
    private EmailResetPasswordFragment target;
    private View view2131755509;

    public EmailResetPasswordFragment_ViewBinding(final EmailResetPasswordFragment target2, View source) {
        this.target = target2;
        target2.password = (SheetInputText) Utils.findRequiredViewAsType(source, C7331R.C7333id.email_reset_password, "field 'password'", SheetInputText.class);
        target2.passwordRetype = (SheetInputText) Utils.findRequiredViewAsType(source, C7331R.C7333id.email_reset_password_retype, "field 'passwordRetype'", SheetInputText.class);
        View view = Utils.findRequiredView(source, C7331R.C7333id.email_reset_password_next, "field 'createPasswordAndLoginButton' and method 'resetPasswordAndLoginAttempt'");
        target2.createPasswordAndLoginButton = (AirButton) Utils.castView(view, C7331R.C7333id.email_reset_password_next, "field 'createPasswordAndLoginButton'", AirButton.class);
        this.view2131755509 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.resetPasswordAndLoginAttempt();
            }
        });
    }

    public void unbind() {
        EmailResetPasswordFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.password = null;
        target2.passwordRetype = null;
        target2.createPasswordAndLoginButton = null;
        this.view2131755509.setOnClickListener(null);
        this.view2131755509 = null;
    }
}
