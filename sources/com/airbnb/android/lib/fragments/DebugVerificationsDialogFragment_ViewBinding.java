package com.airbnb.android.lib.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class DebugVerificationsDialogFragment_ViewBinding implements Unbinder {
    private DebugVerificationsDialogFragment target;
    private View view2131755830;
    private View view2131755831;
    private View view2131755832;

    public DebugVerificationsDialogFragment_ViewBinding(final DebugVerificationsDialogFragment target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, C0880R.C0882id.btn_verifications_onboarding, "method 'startDefault'");
        this.view2131755830 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.startDefault();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.btn_verifications_contact_host, "method 'startContactHost'");
        this.view2131755832 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.startContactHost();
            }
        });
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.btn_verifications_booking, "method 'startBooking'");
        this.view2131755831 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.startBooking();
            }
        });
    }

    public void unbind() {
        if (this.target == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        this.view2131755830.setOnClickListener(null);
        this.view2131755830 = null;
        this.view2131755832.setOnClickListener(null);
        this.view2131755832 = null;
        this.view2131755831.setOnClickListener(null);
        this.view2131755831 = null;
    }
}
