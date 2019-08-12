package com.airbnb.android.identity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.AirToolbar;

public class AccountVerificationStartActivity_ViewBinding implements Unbinder {
    private AccountVerificationStartActivity target;

    public AccountVerificationStartActivity_ViewBinding(AccountVerificationStartActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public AccountVerificationStartActivity_ViewBinding(AccountVerificationStartActivity target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C6533R.C6535id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        AccountVerificationStartActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
    }
}
