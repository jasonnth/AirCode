package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.primitives.AirButton;

public class BankTransferNameFragment_ViewBinding implements Unbinder {
    private BankTransferNameFragment target;
    private View view2131756035;

    public BankTransferNameFragment_ViewBinding(final BankTransferNameFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.nameInputText = (SheetInputText) Utils.findRequiredViewAsType(source, C0880R.C0882id.name_sheetInput, "field 'nameInputText'", SheetInputText.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.next_button, "field 'nextButton' and method 'onNextButtonClick'");
        target2.nextButton = (AirButton) Utils.castView(view, C0880R.C0882id.next_button, "field 'nextButton'", AirButton.class);
        this.view2131756035 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onNextButtonClick();
            }
        });
    }

    public void unbind() {
        BankTransferNameFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.nameInputText = null;
        target2.nextButton = null;
        this.view2131756035.setOnClickListener(null);
        this.view2131756035 = null;
    }
}
