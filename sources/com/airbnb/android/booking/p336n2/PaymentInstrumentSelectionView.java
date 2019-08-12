package com.airbnb.android.booking.p336n2;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.p027n2.collections.BaseSelectionView;
import com.google.common.collect.FluentIterable;
import java.util.List;

/* renamed from: com.airbnb.android.booking.n2.PaymentInstrumentSelectionView */
public class PaymentInstrumentSelectionView extends BaseSelectionView<PaymentInstrumentSelectionViewItem> {
    public PaymentInstrumentSelectionView(Context context) {
        super(context);
    }

    public PaymentInstrumentSelectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PaymentInstrumentSelectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setExistingPaymentInstruments(List<OldPaymentInstrument> paymentInstruments) {
        setItems(FluentIterable.from((Iterable<E>) paymentInstruments).transform(PaymentInstrumentSelectionView$$Lambda$1.lambdaFactory$()).toList());
    }

    public void setSelectedPaymentInstrument(OldPaymentInstrument paymentInstrument) {
        setSelectedItem(new PaymentInstrumentSelectionViewItem(paymentInstrument));
    }

    public OldPaymentInstrument getSelectedPaymentInstrument() {
        PaymentInstrumentSelectionViewItem selectionSheetItem = (PaymentInstrumentSelectionViewItem) getSelectedItem();
        if (selectionSheetItem == null) {
            return null;
        }
        return selectionSheetItem.getPaymentInstrument();
    }
}
