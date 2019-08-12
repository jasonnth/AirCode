package com.airbnb.android.identity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;

public class AccountVerificationEmailConfirmationFragment_ViewBinding implements Unbinder {
    private AccountVerificationEmailConfirmationFragment target;
    private View view2131756111;
    private View view2131756112;

    public AccountVerificationEmailConfirmationFragment_ViewBinding(final AccountVerificationEmailConfirmationFragment target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C6533R.C6535id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        View view = Utils.findRequiredView(source, C6533R.C6535id.sheet_bottom_secondary_button, "field 'secondaryButton' and method 'onEmailResendClicked'");
        target2.secondaryButton = (AirButton) Utils.castView(view, C6533R.C6535id.sheet_bottom_secondary_button, "field 'secondaryButton'", AirButton.class);
        this.view2131756111 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onEmailResendClicked();
            }
        });
        View view2 = Utils.findRequiredView(source, C6533R.C6535id.sheet_bottom_secondary_button_white, "field 'secondaryButtonWhite' and method 'onEmailResendWhiteClicked'");
        target2.secondaryButtonWhite = (AirButton) Utils.castView(view2, C6533R.C6535id.sheet_bottom_secondary_button_white, "field 'secondaryButtonWhite'", AirButton.class);
        this.view2131756112 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onEmailResendWhiteClicked();
            }
        });
        target2.primaryButton = (AirButton) Utils.findRequiredViewAsType(source, C6533R.C6535id.sheet_bottom_primary_button, "field 'primaryButton'", AirButton.class);
        target2.nextButton = (AirButton) Utils.findRequiredViewAsType(source, C6533R.C6535id.sheet_bottom_next_button, "field 'nextButton'", AirButton.class);
        target2.sheetMarquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C6533R.C6535id.sheet_marquee, "field 'sheetMarquee'", SheetMarquee.class);
    }

    public void unbind() {
        AccountVerificationEmailConfirmationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.secondaryButton = null;
        target2.secondaryButtonWhite = null;
        target2.primaryButton = null;
        target2.nextButton = null;
        target2.sheetMarquee = null;
        this.view2131756111.setOnClickListener(null);
        this.view2131756111 = null;
        this.view2131756112.setOnClickListener(null);
        this.view2131756112 = null;
    }
}
