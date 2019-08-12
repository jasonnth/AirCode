package com.airbnb.android.lib.china5a.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.SheetMarquee;

public class VerificationCompleteFragment_ViewBinding implements Unbinder {
    private VerificationCompleteFragment target;
    private View view2131755891;

    public VerificationCompleteFragment_ViewBinding(final VerificationCompleteFragment target2, View source) {
        this.target = target2;
        target2.marquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.sheet_marquee, "field 'marquee'", SheetMarquee.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.confirm_button, "method 'onConfirmed'");
        this.view2131755891 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onConfirmed();
            }
        });
    }

    public void unbind() {
        VerificationCompleteFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.marquee = null;
        this.view2131755891.setOnClickListener(null);
        this.view2131755891 = null;
    }
}
