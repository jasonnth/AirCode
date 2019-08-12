package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.primitives.AirButton;

public class PayoutAddressFragment_ViewBinding implements Unbinder {
    private PayoutAddressFragment target;
    private View view2131756035;

    public PayoutAddressFragment_ViewBinding(final PayoutAddressFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.sheet = (FrameLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.sheet, "field 'sheet'", FrameLayout.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.next_button, "field 'nextButton' and method 'onNextButtonClick'");
        target2.nextButton = (AirButton) Utils.castView(view, C0880R.C0882id.next_button, "field 'nextButton'", AirButton.class);
        this.view2131756035 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onNextButtonClick();
            }
        });
        target2.addressOneInput = (SheetInputText) Utils.findRequiredViewAsType(source, C0880R.C0882id.address_street_one_input, "field 'addressOneInput'", SheetInputText.class);
        target2.addressTwoInput = (SheetInputText) Utils.findRequiredViewAsType(source, C0880R.C0882id.address_street_two_input, "field 'addressTwoInput'", SheetInputText.class);
        target2.cityInput = (SheetInputText) Utils.findRequiredViewAsType(source, C0880R.C0882id.address_city_input, "field 'cityInput'", SheetInputText.class);
        target2.stateInput = (SheetInputText) Utils.findRequiredViewAsType(source, C0880R.C0882id.address_state_input, "field 'stateInput'", SheetInputText.class);
        target2.postalCodeInput = (SheetInputText) Utils.findRequiredViewAsType(source, C0880R.C0882id.address_postal_code_input, "field 'postalCodeInput'", SheetInputText.class);
        target2.countryInput = (SheetInputText) Utils.findRequiredViewAsType(source, C0880R.C0882id.address_country_input, "field 'countryInput'", SheetInputText.class);
    }

    public void unbind() {
        PayoutAddressFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.sheet = null;
        target2.nextButton = null;
        target2.addressOneInput = null;
        target2.addressTwoInput = null;
        target2.cityInput = null;
        target2.stateInput = null;
        target2.postalCodeInput = null;
        target2.countryInput = null;
        this.view2131756035.setOnClickListener(null);
        this.view2131756035 = null;
    }
}
