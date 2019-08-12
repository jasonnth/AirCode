package com.airbnb.android.lib.china5a.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.primitives.AirButton;

public class EmailVerificationFragment_ViewBinding implements Unbinder {
    private EmailVerificationFragment target;
    private View view2131755964;
    private View view2131755965;

    public EmailVerificationFragment_ViewBinding(final EmailVerificationFragment target2, View source) {
        this.target = target2;
        target2.titleMarquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.email_confirmation_sheet_marquee, "field 'titleMarquee'", SheetMarquee.class);
        target2.emailInputSheet = (SheetInputText) Utils.findRequiredViewAsType(source, C0880R.C0882id.email_address_sheet_input, "field 'emailInputSheet'", SheetInputText.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.send_btn, "field 'sendButton' and method 'onSendButtonClicked'");
        target2.sendButton = (AirButton) Utils.castView(view, C0880R.C0882id.send_btn, "field 'sendButton'", AirButton.class);
        this.view2131755964 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSendButtonClicked();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.resend_btn, "field 'resendButton' and method 'onResendButtonClicked'");
        target2.resendButton = (AirButton) Utils.castView(view2, C0880R.C0882id.resend_btn, "field 'resendButton'", AirButton.class);
        this.view2131755965 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onResendButtonClicked();
            }
        });
    }

    public void unbind() {
        EmailVerificationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.titleMarquee = null;
        target2.emailInputSheet = null;
        target2.sendButton = null;
        target2.resendButton = null;
        this.view2131755964.setOnClickListener(null);
        this.view2131755964 = null;
        this.view2131755965.setOnClickListener(null);
        this.view2131755965 = null;
    }
}
