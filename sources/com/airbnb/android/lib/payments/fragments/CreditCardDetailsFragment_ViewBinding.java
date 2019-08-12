package com.airbnb.android.lib.payments.fragments;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.PaymentInputLayout;
import com.airbnb.p027n2.primitives.AirButton;

public class CreditCardDetailsFragment_ViewBinding implements Unbinder {
    private CreditCardDetailsFragment target;
    private View view2131756035;

    public CreditCardDetailsFragment_ViewBinding(final CreditCardDetailsFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.documentMarquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.marquee, "field 'documentMarquee'", DocumentMarquee.class);
        target2.cardNumberInputLayout = (PaymentInputLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.credit_card_layout, "field 'cardNumberInputLayout'", PaymentInputLayout.class);
        target2.cardDateInputLayout = (PaymentInputLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.card_date_layout, "field 'cardDateInputLayout'", PaymentInputLayout.class);
        target2.cardCvvInputLayout = (PaymentInputLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.cvv_layout, "field 'cardCvvInputLayout'", PaymentInputLayout.class);
        target2.cardPostalCodeInputLayout = (PaymentInputLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.postal_code_layout, "field 'cardPostalCodeInputLayout'", PaymentInputLayout.class);
        target2.cardDetailsContainer = (LinearLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.card_date_cvv_zipcode_container, "field 'cardDetailsContainer'", LinearLayout.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.next_button, "field 'nextButton' and method 'onNextButtonClicked'");
        target2.nextButton = (AirButton) Utils.castView(view, C0880R.C0882id.next_button, "field 'nextButton'", AirButton.class);
        this.view2131756035 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onNextButtonClicked();
            }
        });
    }

    public void unbind() {
        CreditCardDetailsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.documentMarquee = null;
        target2.cardNumberInputLayout = null;
        target2.cardDateInputLayout = null;
        target2.cardCvvInputLayout = null;
        target2.cardPostalCodeInputLayout = null;
        target2.cardDetailsContainer = null;
        target2.nextButton = null;
        this.view2131756035.setOnClickListener(null);
        this.view2131756035 = null;
    }
}
