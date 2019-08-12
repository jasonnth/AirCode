package com.airbnb.android.identity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.KickerMarquee;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.primitives.AirButton;

public class AccountVerificationStart_ViewBinding implements Unbinder {
    private AccountVerificationStart target;
    private View view2131755318;
    private View view2131755319;

    public AccountVerificationStart_ViewBinding(AccountVerificationStart target2) {
        this(target2, target2);
    }

    public AccountVerificationStart_ViewBinding(final AccountVerificationStart target2, View source) {
        this.target = target2;
        target2.headerText = (KickerMarquee) Utils.findRequiredViewAsType(source, C6533R.C6535id.account_verifications_start_header, "field 'headerText'", KickerMarquee.class);
        View view = Utils.findRequiredView(source, C6533R.C6535id.account_verifications_start_btn, "field 'primaryButton' and method 'onContinueClick'");
        target2.primaryButton = (PrimaryButton) Utils.castView(view, C6533R.C6535id.account_verifications_start_btn, "field 'primaryButton'", PrimaryButton.class);
        this.view2131755318 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onContinueClick();
            }
        });
        View view2 = Utils.findRequiredView(source, C6533R.C6535id.account_verifications_start_btn_booking, "field 'nextButton' and method 'onNextClick'");
        target2.nextButton = (AirButton) Utils.castView(view2, C6533R.C6535id.account_verifications_start_btn_booking, "field 'nextButton'", AirButton.class);
        this.view2131755319 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onNextClick();
            }
        });
    }

    public void unbind() {
        AccountVerificationStart target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.headerText = null;
        target2.primaryButton = null;
        target2.nextButton = null;
        this.view2131755318.setOnClickListener(null);
        this.view2131755318 = null;
        this.view2131755319.setOnClickListener(null);
        this.view2131755319 = null;
    }
}
