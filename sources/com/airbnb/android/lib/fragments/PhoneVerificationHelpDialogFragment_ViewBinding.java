package com.airbnb.android.lib.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class PhoneVerificationHelpDialogFragment_ViewBinding implements Unbinder {
    private PhoneVerificationHelpDialogFragment target;
    private View view2131755842;
    private View view2131755843;
    private View view2131755844;

    public PhoneVerificationHelpDialogFragment_ViewBinding(final PhoneVerificationHelpDialogFragment target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, C0880R.C0882id.btn_change_number, "method 'changePhoneNumber'");
        this.view2131755842 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.changePhoneNumber();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.btn_send_code, "method 'sendCode'");
        this.view2131755843 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.sendCode();
            }
        });
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.btn_call_instead, "method 'callInstead'");
        this.view2131755844 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.callInstead();
            }
        });
    }

    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        this.view2131755842.setOnClickListener(null);
        this.view2131755842 = null;
        this.view2131755843.setOnClickListener(null);
        this.view2131755843 = null;
        this.view2131755844.setOnClickListener(null);
        this.view2131755844 = null;
    }
}
