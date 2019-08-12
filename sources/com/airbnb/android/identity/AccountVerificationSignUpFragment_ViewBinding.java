package com.airbnb.android.identity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;

public class AccountVerificationSignUpFragment_ViewBinding implements Unbinder {
    private AccountVerificationSignUpFragment target;
    private View view2131755525;
    private View view2131755618;

    public AccountVerificationSignUpFragment_ViewBinding(final AccountVerificationSignUpFragment target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, C6533R.C6535id.provide_id_button, "method 'onProvideIdClick'");
        this.view2131755618 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onProvideIdClick();
            }
        });
        View view2 = Utils.findRequiredView(source, C6533R.C6535id.cancel_button, "method 'onCancel'");
        this.view2131755525 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onCancel();
            }
        });
    }

    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        this.view2131755618.setOnClickListener(null);
        this.view2131755618 = null;
        this.view2131755525.setOnClickListener(null);
        this.view2131755525 = null;
    }
}
