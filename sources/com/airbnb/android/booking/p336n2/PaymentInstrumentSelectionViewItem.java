package com.airbnb.android.booking.p336n2;

import android.content.Context;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionViewItemPresenter;

/* renamed from: com.airbnb.android.booking.n2.PaymentInstrumentSelectionViewItem */
public class PaymentInstrumentSelectionViewItem implements SelectionViewItemPresenter {
    private final OldPaymentInstrument paymentInstrument;

    public PaymentInstrumentSelectionViewItem(OldPaymentInstrument paymentInstrument2) {
        this.paymentInstrument = paymentInstrument2;
    }

    public OldPaymentInstrument getPaymentInstrument() {
        return this.paymentInstrument;
    }

    public boolean equals(Object o) {
        if (o != null && (o instanceof PaymentInstrumentSelectionViewItem)) {
            return ((PaymentInstrumentSelectionViewItem) o).paymentInstrument.equals(this.paymentInstrument);
        }
        return false;
    }

    public int hashCode() {
        return this.paymentInstrument.hashCode();
    }

    public String getDisplayText(Context context) {
        return this.paymentInstrument.getDisplayName(context);
    }
}
