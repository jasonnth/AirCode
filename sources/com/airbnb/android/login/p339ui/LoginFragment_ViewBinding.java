package com.airbnb.android.login.p339ui;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.PhoneNumberInputSheet;
import com.airbnb.android.login.C7331R;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.primitives.AirButton;

/* renamed from: com.airbnb.android.login.ui.LoginFragment_ViewBinding */
public class LoginFragment_ViewBinding implements Unbinder {
    private LoginFragment target;
    private View view2131755515;
    private View view2131755553;

    public LoginFragment_ViewBinding(final LoginFragment target2, View source) {
        this.target = target2;
        target2.editEmail = (SheetInputText) Utils.findRequiredViewAsType(source, C7331R.C7333id.sign_in_email, "field 'editEmail'", SheetInputText.class);
        target2.editPhone = (PhoneNumberInputSheet) Utils.findRequiredViewAsType(source, C7331R.C7333id.sign_in_phone, "field 'editPhone'", PhoneNumberInputSheet.class);
        target2.editPassword = (SheetInputText) Utils.findRequiredViewAsType(source, C7331R.C7333id.sign_in_password, "field 'editPassword'", SheetInputText.class);
        View view = Utils.findRequiredView(source, C7331R.C7333id.sign_in_button, "field 'loginButton' and method 'login'");
        target2.loginButton = (AirButton) Utils.castView(view, C7331R.C7333id.sign_in_button, "field 'loginButton'", AirButton.class);
        this.view2131755515 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.login();
            }
        });
        View view2 = Utils.findRequiredView(source, C7331R.C7333id.signin_mode_swap_button, "field 'swapModeButton' and method 'onSwapModeButtonClick'");
        target2.swapModeButton = (AirButton) Utils.castView(view2, C7331R.C7333id.signin_mode_swap_button, "field 'swapModeButton'", AirButton.class);
        this.view2131755553 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSwapModeButtonClick();
            }
        });
    }

    public void unbind() {
        LoginFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.editEmail = null;
        target2.editPhone = null;
        target2.editPassword = null;
        target2.loginButton = null;
        target2.swapModeButton = null;
        this.view2131755515.setOnClickListener(null);
        this.view2131755515 = null;
        this.view2131755553.setOnClickListener(null);
        this.view2131755553 = null;
    }
}
