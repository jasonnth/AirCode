package com.airbnb.android.lib.china5a.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.PhoneNumberInputSheet;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.primitives.AirButton;

public class PhoneVerificationFragment_ViewBinding implements Unbinder {
    private PhoneVerificationFragment target;
    private View view2131755970;
    private View view2131755971;

    public PhoneVerificationFragment_ViewBinding(final PhoneVerificationFragment target2, View source) {
        this.target = target2;
        target2.phoneConfirmationSheetMarquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.phone_confirmation_sheet_marquee, "field 'phoneConfirmationSheetMarquee'", SheetMarquee.class);
        target2.phoneNumberInputSheet = (PhoneNumberInputSheet) Utils.findRequiredViewAsType(source, C0880R.C0882id.phone_number_input_view, "field 'phoneNumberInputSheet'", PhoneNumberInputSheet.class);
        target2.codeInputSheet = (SheetInputText) Utils.findRequiredViewAsType(source, C0880R.C0882id.code_sheetInput, "field 'codeInputSheet'", SheetInputText.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.send_code_btn, "field 'sendCodeBtn' and method 'onSendCodeClicked'");
        target2.sendCodeBtn = (AirButton) Utils.castView(view, C0880R.C0882id.send_code_btn, "field 'sendCodeBtn'", AirButton.class);
        this.view2131755970 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSendCodeClicked();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.next_btn, "field 'nextButton' and method 'onNextClicked'");
        target2.nextButton = (AirButton) Utils.castView(view2, C0880R.C0882id.next_btn, "field 'nextButton'", AirButton.class);
        this.view2131755971 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onNextClicked();
            }
        });
        target2.codeInputContainer = Utils.findRequiredView(source, C0880R.C0882id.code_sheetInput_container, "field 'codeInputContainer'");
    }

    public void unbind() {
        PhoneVerificationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.phoneConfirmationSheetMarquee = null;
        target2.phoneNumberInputSheet = null;
        target2.codeInputSheet = null;
        target2.sendCodeBtn = null;
        target2.nextButton = null;
        target2.codeInputContainer = null;
        this.view2131755970.setOnClickListener(null);
        this.view2131755970 = null;
        this.view2131755971.setOnClickListener(null);
        this.view2131755971 = null;
    }
}
