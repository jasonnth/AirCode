package com.airbnb.android.identity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;

public class AccountVerificationFinishFragment_ViewBinding implements Unbinder {
    private AccountVerificationFinishFragment target;
    private View view2131755598;

    public AccountVerificationFinishFragment_ViewBinding(final AccountVerificationFinishFragment target2, View source) {
        this.target = target2;
        target2.marquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C6533R.C6535id.account_verification_finish_marquee, "field 'marquee'", SheetMarquee.class);
        View view = Utils.findRequiredView(source, C6533R.C6535id.account_verification_finish_button, "field 'button' and method 'onFinishBookingClick'");
        target2.button = (AirButton) Utils.castView(view, C6533R.C6535id.account_verification_finish_button, "field 'button'", AirButton.class);
        this.view2131755598 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onFinishBookingClick();
            }
        });
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C6533R.C6535id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
    }

    public void unbind() {
        AccountVerificationFinishFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.marquee = null;
        target2.button = null;
        target2.jellyfishView = null;
        this.view2131755598.setOnClickListener(null);
        this.view2131755598 = null;
    }
}
