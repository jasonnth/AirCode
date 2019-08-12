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
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.primitives.AirButton;

public class CardNumberFragment extends CreditCardBaseFragment {
    @BindView
    SheetInputText cardNumberInput;
    private final TextWatcher cardNumberTextWatcher = new SimpleTextWatcher() {
        private boolean didFormat = false;

        public void afterTextChanged(Editable s) {
            String cardNumber = s.toString().replaceAll("\\s+", "");
            CardType cardType = CardType.getCardTypeFromCCNumber(cardNumber);
            if (cardType != CardType.Unknown && cardNumberExceedsMaxLength(cardNumber, cardType)) {
                formatCardNumber(cardNumber, cardType);
            } else if (this.didFormat) {
                this.didFormat = false;
            } else {
                CardNumberFragment.this.dismissError();
                if (cardType != CardType.Unknown) {
                    formatCardNumber(cardNumber, cardType);
                    if (CardType.isCardNumberFullLength(cardNumber, cardType)) {
                        validateCardNumber(cardNumber, cardType);
                        return;
                    }
                } else if (cardNumber.length() > 3) {
                    CardNumberFragment.this.displayError(CardNumberFragment.this.getString(C0704R.string.p4_error_unknown_card));
                    return;
                }
                CardNumberFragment.this.cardNumberInput.setState(State.Normal);
                CardNumberFragment.this.nextButton.setEnabled(false);
            }
        }

        private boolean cardNumberExceedsMaxLength(String cardNumber, CardType cardType) {
            return cardNumber.replace(" ", "").length() > cardType.getCardNumberMaxLength();
        }

        private void formatCardNumber(String cardNumber, CardType cardType) {
            this.didFormat = true;
            CardNumberFragment.this.cardNumberInput.setText(cardType.formatCC(cardNumber));
            CardNumberFragment.this.cardNumberInput.setSelection(CardNumberFragment.this.cardNumberInput.getText().length());
        }

        private void validateCardNumber(String cardNumber, CardType cardType) {
            if (CardType.isValidCCNumber(cardNumber, cardType)) {
                CardNumberFragment.this.cardNumberInput.setState(State.Valid);
                CardNumberFragment.this.nextButton.setEnabled(true);
                return;
            }
            CardNumberFragment.this.displayError(CardNumberFragment.this.getString(C0704R.string.p4_error_credit_card_invalid_number));
        }
    };
    @BindView
    JellyfishView jellyfishView;
    @BindView
    AirButton nextButton;

    public static CardNumberFragment newInstance() {
        return new CardNumberFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0704R.layout.fragment_card_number, container, false);
        bindViews(view);
        setUpCardNumberInput();
        return view;
    }

    public void onDestroyView() {
        dismissError();
        this.cardNumberInput.removeTextChangedListener(this.cardNumberTextWatcher);
        super.onDestroyView();
    }

    private void setUpCardNumberInput() {
        this.cardNumberInput.requestFocus();
        this.cardNumberInput.post(CardNumberFragment$$Lambda$1.lambdaFactory$(this));
        this.cardNumberInput.addTextChangedListener(this.cardNumberTextWatcher);
    }

    static /* synthetic */ void lambda$setUpCardNumberInput$0(CardNumberFragment cardNumberFragment) {
        if (cardNumberFragment.cardNumberInput != null) {
            cardNumberFragment.cardNumberInput.showSoftKeyboard();
        }
    }

    /* access modifiers changed from: protected */
    public void dismissError() {
        super.dismissError();
        if (this.cardNumberInput != null) {
            this.cardNumberInput.setState(State.Normal);
        }
    }

    /* access modifiers changed from: protected */
    public void displayError(String errorMessage) {
        super.displayError(errorMessage);
        this.cardNumberInput.setState(State.Error);
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onClickNextButton() {
        getCreditCardActivity().updateCardNumber(this.cardNumberInput.getText().toString());
        if (getCreditCardActivity().isQuickPay()) {
            this.quickPayJitneyLogger.paymentCcNumber();
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.AddPaymentCardNumber;
    }
}
