package com.airbnb.android.lib.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.primitives.AirButton;

public class PreapproveInquiryFragment_ViewBinding implements Unbinder {
    private PreapproveInquiryFragment target;
    private View view2131755720;
    private View view2131755988;

    public PreapproveInquiryFragment_ViewBinding(final PreapproveInquiryFragment target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, C0880R.C0882id.accept_button, "field 'preapproveButton' and method 'clickAccept'");
        target2.preapproveButton = (AirButton) Utils.castView(view, C0880R.C0882id.accept_button, "field 'preapproveButton'", AirButton.class);
        this.view2131755988 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickAccept();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.cancel_button, "field 'cancelButton' and method 'clickCancel'");
        target2.cancelButton = (AirButton) Utils.castView(view2, C0880R.C0882id.cancel_button, "field 'cancelButton'", AirButton.class);
        this.view2131755720 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.clickCancel();
            }
        });
        target2.marquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.accept_marquee, "field 'marquee'", SheetMarquee.class);
    }

    public void unbind() {
        PreapproveInquiryFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.preapproveButton = null;
        target2.cancelButton = null;
        target2.marquee = null;
        this.view2131755988.setOnClickListener(null);
        this.view2131755988 = null;
        this.view2131755720.setOnClickListener(null);
        this.view2131755720 = null;
    }
}
