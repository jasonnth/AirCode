package com.airbnb.android.lib.payments.creditcard.brazil.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.PaymentInputLayout;
import com.airbnb.p027n2.primitives.AirButton;

public class BrazilCreditCardDetailsFragment_ViewBinding implements Unbinder {
    private BrazilCreditCardDetailsFragment target;
    private View view2131756035;
    private View view2131756117;

    public BrazilCreditCardDetailsFragment_ViewBinding(final BrazilCreditCardDetailsFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.next_button, "field 'nextButton' and method 'onNextButtonClicked'");
        target2.nextButton = (AirButton) Utils.castView(view, C0880R.C0882id.next_button, "field 'nextButton'", AirButton.class);
        this.view2131756035 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onNextButtonClicked();
            }
        });
        target2.documentMarquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.marquee, "field 'documentMarquee'", DocumentMarquee.class);
        target2.firstNameInput = (PaymentInputLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.first_name, "field 'firstNameInput'", PaymentInputLayout.class);
        target2.lastNameInput = (PaymentInputLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.last_name, "field 'lastNameInput'", PaymentInputLayout.class);
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.birthdate, "field 'birthdateInput' and method 'launchBirthdayPicker'");
        target2.birthdateInput = (PaymentInputLayout) Utils.castView(view2, C0880R.C0882id.birthdate, "field 'birthdateInput'", PaymentInputLayout.class);
        this.view2131756117 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.launchBirthdayPicker();
            }
        });
        target2.mobileNumberInput = (PaymentInputLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.mobile_number, "field 'mobileNumberInput'", PaymentInputLayout.class);
        target2.taxpayerIdInput = (PaymentInputLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.taxpayer_id, "field 'taxpayerIdInput'", PaymentInputLayout.class);
        target2.streetAddressInput = (PaymentInputLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.street_address, "field 'streetAddressInput'", PaymentInputLayout.class);
        target2.buildingNumberInput = (PaymentInputLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.building_number, "field 'buildingNumberInput'", PaymentInputLayout.class);
        target2.complementInput = (PaymentInputLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.complement, "field 'complementInput'", PaymentInputLayout.class);
        target2.cityInput = (PaymentInputLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.city, "field 'cityInput'", PaymentInputLayout.class);
        target2.stateInput = (PaymentInputLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.state, "field 'stateInput'", PaymentInputLayout.class);
    }

    public void unbind() {
        BrazilCreditCardDetailsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.nextButton = null;
        target2.documentMarquee = null;
        target2.firstNameInput = null;
        target2.lastNameInput = null;
        target2.birthdateInput = null;
        target2.mobileNumberInput = null;
        target2.taxpayerIdInput = null;
        target2.streetAddressInput = null;
        target2.buildingNumberInput = null;
        target2.complementInput = null;
        target2.cityInput = null;
        target2.stateInput = null;
        this.view2131756035.setOnClickListener(null);
        this.view2131756035 = null;
        this.view2131756117.setOnClickListener(null);
        this.view2131756117 = null;
    }
}
