package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.primitives.AirButton;

public class BankTransferInfoFragment_ViewBinding implements Unbinder {
    private BankTransferInfoFragment target;
    private View view2131756071;

    public BankTransferInfoFragment_ViewBinding(final BankTransferInfoFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.submit_button, "field 'submitButton' and method 'onSubmitButtonClick'");
        target2.submitButton = (AirButton) Utils.castView(view, C0880R.C0882id.submit_button, "field 'submitButton'", AirButton.class);
        this.view2131756071 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSubmitButtonClick();
            }
        });
        target2.swiftCodeSheetInputText = (SheetInputText) Utils.findRequiredViewAsType(source, C0880R.C0882id.swift_code_sheetInput, "field 'swiftCodeSheetInputText'", SheetInputText.class);
        target2.ibanSheetInputText = (SheetInputText) Utils.findRequiredViewAsType(source, C0880R.C0882id.iban_sheetInput, "field 'ibanSheetInputText'", SheetInputText.class);
    }

    public void unbind() {
        BankTransferInfoFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.submitButton = null;
        target2.swiftCodeSheetInputText = null;
        target2.ibanSheetInputText = null;
        this.view2131756071.setOnClickListener(null);
        this.view2131756071 = null;
    }
}
