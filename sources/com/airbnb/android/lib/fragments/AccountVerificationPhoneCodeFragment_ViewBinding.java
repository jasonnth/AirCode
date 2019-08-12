package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class AccountVerificationPhoneCodeFragment_ViewBinding implements Unbinder {
    private AccountVerificationPhoneCodeFragment target;

    public AccountVerificationPhoneCodeFragment_ViewBinding(AccountVerificationPhoneCodeFragment target2, View source) {
        this.target = target2;
        target2.mBackButton = (Button) Utils.findRequiredViewAsType(source, C0880R.C0882id.back_btn, "field 'mBackButton'", Button.class);
        target2.mSubmitCodeButton = (Button) Utils.findRequiredViewAsType(source, C0880R.C0882id.enter_code_btn, "field 'mSubmitCodeButton'", Button.class);
        target2.mPhoneCodeInput = (EditText) Utils.findRequiredViewAsType(source, C0880R.C0882id.phone_code_edit_text, "field 'mPhoneCodeInput'", EditText.class);
    }

    public void unbind() {
        AccountVerificationPhoneCodeFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mBackButton = null;
        target2.mSubmitCodeButton = null;
        target2.mPhoneCodeInput = null;
    }
}
