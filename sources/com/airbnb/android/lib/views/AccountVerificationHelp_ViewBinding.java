package com.airbnb.android.lib.views;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class AccountVerificationHelp_ViewBinding implements Unbinder {
    private AccountVerificationHelp target;

    public AccountVerificationHelp_ViewBinding(AccountVerificationHelp target2) {
        this(target2, target2);
    }

    public AccountVerificationHelp_ViewBinding(AccountVerificationHelp target2, View source) {
        this.target = target2;
        target2.mContactTextView = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txt_contact, "field 'mContactTextView'", TextView.class);
    }

    public void unbind() {
        AccountVerificationHelp target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mContactTextView = null;
    }
}
