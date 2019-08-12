package com.airbnb.android.identity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;

public class AccountVerificationStartComplete_ViewBinding implements Unbinder {
    private AccountVerificationStartComplete target;
    private View view2131755318;
    private View view2131755323;

    public AccountVerificationStartComplete_ViewBinding(AccountVerificationStartComplete target2) {
        this(target2, target2);
    }

    public AccountVerificationStartComplete_ViewBinding(final AccountVerificationStartComplete target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C6533R.C6535id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        target2.headerText = (SheetMarquee) Utils.findRequiredViewAsType(source, C6533R.C6535id.account_verifications_start_header, "field 'headerText'", SheetMarquee.class);
        View view = Utils.findRequiredView(source, C6533R.C6535id.account_verifications_start_btn, "field 'continueButton' and method 'onContinueClick'");
        target2.continueButton = (AirButton) Utils.castView(view, C6533R.C6535id.account_verifications_start_btn, "field 'continueButton'", AirButton.class);
        this.view2131755318 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onContinueClick();
            }
        });
        View view2 = Utils.findRequiredView(source, C6533R.C6535id.account_verifications_next_btn, "field 'nextButton' and method 'onNextClick'");
        target2.nextButton = (AirButton) Utils.castView(view2, C6533R.C6535id.account_verifications_next_btn, "field 'nextButton'", AirButton.class);
        this.view2131755323 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onNextClick();
            }
        });
    }

    public void unbind() {
        AccountVerificationStartComplete target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.headerText = null;
        target2.continueButton = null;
        target2.nextButton = null;
        this.view2131755318.setOnClickListener(null);
        this.view2131755318 = null;
        this.view2131755323.setOnClickListener(null);
        this.view2131755323 = null;
    }
}
