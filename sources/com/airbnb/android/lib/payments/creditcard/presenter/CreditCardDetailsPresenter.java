package com.airbnb.android.lib.payments.creditcard.presenter;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.LinearLayout;
import com.airbnb.android.core.enums.CardType;
import com.airbnb.android.lib.payments.utils.PaymentImageUtils;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.PaymentInputLayout;
import com.airbnb.p027n2.utils.SnackbarWrapper;

public class CreditCardDetailsPresenter {
    private static final float ALPHA_INVISIBLE = 0.0f;
    private static final float ALPHA_VISIBLE = 1.0f;
    private static final long ANIMATION_DURATION = 500;
    private final PaymentInputLayout cardCvvInputLayout;
    private final PaymentInputLayout cardDateInputLayout;
    private final PaymentInputLayout cardNumberInputLayout;
    private final PaymentInputLayout cardPostalCodeInputLayout;
    private Snackbar snackbar;

    public CreditCardDetailsPresenter(PaymentInputLayout cardNumberInputLayout2, PaymentInputLayout cardDateInputLayout2, PaymentInputLayout cardCvvInputLayout2, PaymentInputLayout cardPostalCodeInputLayout2) {
        this.cardNumberInputLayout = cardNumberInputLayout2;
        this.cardDateInputLayout = cardDateInputLayout2;
        this.cardCvvInputLayout = cardCvvInputLayout2;
        this.cardPostalCodeInputLayout = cardPostalCodeInputLayout2;
    }

    public void enableAllInputLayouts() {
        this.cardNumberInputLayout.setEnabled(true);
        this.cardDateInputLayout.setEnabled(true);
        this.cardCvvInputLayout.setEnabled(true);
        this.cardPostalCodeInputLayout.setEnabled(true);
    }

    public void disableAllInputLayouts() {
        this.cardNumberInputLayout.setEnabled(false);
        this.cardDateInputLayout.setEnabled(false);
        this.cardCvvInputLayout.setEnabled(false);
        this.cardPostalCodeInputLayout.setEnabled(false);
    }

    public void showCardDetailsRow(DocumentMarquee documentMarquee, LinearLayout cardDetailsContainer) {
        if (cardDetailsContainer.getAlpha() == ALPHA_INVISIBLE) {
            documentMarquee.animate().alpha(ALPHA_INVISIBLE).setDuration(ANIMATION_DURATION);
            int y = (-documentMarquee.getHeight()) / 2;
            this.cardNumberInputLayout.animate().translationYBy((float) y).setDuration(ANIMATION_DURATION);
            cardDetailsContainer.animate().alpha(1.0f).translationYBy((float) y).setDuration(ANIMATION_DURATION);
        }
    }

    public void showCardLogo(CardType cardType) {
        this.cardNumberInputLayout.showPaymentLogo(PaymentImageUtils.getCardImageRes(cardType));
    }

    public void showSnackbarError(View view, String title, String body) {
        this.snackbar = new SnackbarWrapper().view(view).title(title, true).body(body).duration(-2).buildAndShow();
    }

    public void hideSnackbarError() {
        if (this.snackbar != null) {
            this.snackbar.dismiss();
        }
    }
}
