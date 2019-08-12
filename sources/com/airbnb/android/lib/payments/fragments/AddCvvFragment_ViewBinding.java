package com.airbnb.android.lib.payments.fragments;

import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.primitives.AirButton;

public class AddCvvFragment_ViewBinding implements Unbinder {
    private AddCvvFragment target;
    private View view2131756035;
    private View view2131756668;

    public AddCvvFragment_ViewBinding(final AddCvvFragment target2, View source) {
        this.target = target2;
        target2.rootView = (ViewGroup) Utils.findRequiredViewAsType(source, C0880R.C0882id.root_view, "field 'rootView'", ViewGroup.class);
        target2.marquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.security_code_marquee, "field 'marquee'", SheetMarquee.class);
        target2.sheetInput = (SheetInputText) Utils.findRequiredViewAsType(source, C0880R.C0882id.security_code_sheetInput, "field 'sheetInput'", SheetInputText.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.select_new_payment, "field 'selectNewPayment' and method 'onClickSelectNewPaymentButton'");
        target2.selectNewPayment = (AirButton) Utils.castView(view, C0880R.C0882id.select_new_payment, "field 'selectNewPayment'", AirButton.class);
        this.view2131756668 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickSelectNewPaymentButton();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.next_button, "field 'nextButton' and method 'onClickNextButton'");
        target2.nextButton = (AirButton) Utils.castView(view2, C0880R.C0882id.next_button, "field 'nextButton'", AirButton.class);
        this.view2131756035 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickNextButton();
            }
        });
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        AddCvvFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.rootView = null;
        target2.marquee = null;
        target2.sheetInput = null;
        target2.selectNewPayment = null;
        target2.nextButton = null;
        target2.toolbar = null;
        this.view2131756668.setOnClickListener(null);
        this.view2131756668 = null;
        this.view2131756035.setOnClickListener(null);
        this.view2131756035 = null;
    }
}
