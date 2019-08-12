package com.airbnb.android.lib.businesstravel;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.primitives.AirButton;

public class VerifyWorkEmailFragment_ViewBinding implements Unbinder {
    private VerifyWorkEmailFragment target;

    public VerifyWorkEmailFragment_ViewBinding(VerifyWorkEmailFragment target2, View source) {
        this.target = target2;
        target2.documentMarquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.document_marquee, "field 'documentMarquee'", DocumentMarquee.class);
        target2.gotItButton = (AirButton) Utils.findRequiredViewAsType(source, C0880R.C0882id.got_it_button, "field 'gotItButton'", AirButton.class);
        target2.resendEmailButton = (AirButton) Utils.findRequiredViewAsType(source, C0880R.C0882id.resend_email_button, "field 'resendEmailButton'", AirButton.class);
    }

    public void unbind() {
        VerifyWorkEmailFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.documentMarquee = null;
        target2.gotItButton = null;
        target2.resendEmailButton = null;
    }
}
