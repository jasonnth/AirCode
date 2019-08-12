package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.core.enums.CardType;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.SheetInputText;
import com.airbnb.p027n2.components.SheetInputText.State;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;

public class SecurityCodeFragment extends CreditCardBaseFragment {
    @BindView
    JellyfishView jellyfishView;
    @BindView
    SheetMarquee marquee;
    @BindView
    AirButton nextButton;
    @BindView
    SheetInputText sheetInput;
    private final TextWatcher validationWatcher = new SimpleTextWatcher() {
        public void afterTextChanged(Editable s) {
            SecurityCodeFragment.this.validateInput(s.toString());
        }
    };

    public static SecurityCodeFragment newInstance() {
        return new SecurityCodeFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0704R.layout.fragment_security_code, container, false);
        bindViews(view);
        setMarqueeTitle();
        setUpSecurityCodeInput();
        return view;
    }

    public void onDestroyView() {
        dismissError();
        this.sheetInput.removeTextChangedListener(this.validationWatcher);
        super.onDestroyView();
    }

    private void setMarqueeTitle() {
        if (CardType.getCardTypeFromCCNumber(getPaymentDetails().cardNumber()) == CardType.Amex) {
            this.marquee.setTitle(C0704R.string.p4_security_code_title_front);
        } else {
            this.marquee.setTitle(C0704R.string.p4_security_code_title_back);
        }
    }

    private void setUpSecurityCodeInput() {
        this.sheetInput.requestFocus();
        this.sheetInput.post(SecurityCodeFragment$$Lambda$1.lambdaFactory$(this));
        this.sheetInput.addTextChangedListener(this.validationWatcher);
    }

    /* access modifiers changed from: private */
    public void validateInput(String securityCode) {
        CardType cardType = CardType.getCardTypeFromCCNumber(getPaymentDetails().cardNumber());
        if (CardType.isSecurityCodeFullLength(securityCode, cardType)) {
            dismissError();
            this.nextButton.setEnabled(true);
            this.sheetInput.setState(State.Valid);
        } else if (CardType.isSecurityCodeTooLong(securityCode, cardType)) {
            displayError(getString(C0704R.string.p4_security_code_too_long, Integer.valueOf(cardType.getCCVLength())));
            this.nextButton.setEnabled(false);
        } else {
            dismissError();
            this.nextButton.setEnabled(false);
            this.sheetInput.setState(State.Normal);
        }
    }

    /* access modifiers changed from: protected */
    public void displayError(String errorMessage) {
        super.displayError(errorMessage);
        this.sheetInput.setState(State.Error);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onClickNextButton() {
        getCreditCardActivity().updateSecurityCode(this.sheetInput.getText().toString());
        if (getCreditCardActivity().isQuickPay()) {
            this.quickPayJitneyLogger.paymentCcCvv();
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.AddPaymentSecurityCode;
    }
}
