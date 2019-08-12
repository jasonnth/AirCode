package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class AccountVerificationWelcomeFragment_ViewBinding implements Unbinder {
    private AccountVerificationWelcomeFragment target;

    public AccountVerificationWelcomeFragment_ViewBinding(AccountVerificationWelcomeFragment target2, View source) {
        this.target = target2;
        target2.mBeginButton = (Button) Utils.findRequiredViewAsType(source, C0880R.C0882id.btn_begin, "field 'mBeginButton'", Button.class);
    }

    public void unbind() {
        AccountVerificationWelcomeFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mBeginButton = null;
    }
}
