package com.airbnb.android.booking.fragments;

import com.airbnb.android.booking.p336n2.PaymentInstrumentSelectionViewItem;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionSheetOnItemClickedListener;

final /* synthetic */ class PaymentInstrumentsFragment$$Lambda$1 implements SelectionSheetOnItemClickedListener {
    private final PaymentInstrumentsFragment arg$1;

    private PaymentInstrumentsFragment$$Lambda$1(PaymentInstrumentsFragment paymentInstrumentsFragment) {
        this.arg$1 = paymentInstrumentsFragment;
    }

    public static SelectionSheetOnItemClickedListener lambdaFactory$(PaymentInstrumentsFragment paymentInstrumentsFragment) {
        return new PaymentInstrumentsFragment$$Lambda$1(paymentInstrumentsFragment);
    }

    public void onItemClicked(Object obj) {
        PaymentInstrumentsFragment.lambda$new$1(this.arg$1, (PaymentInstrumentSelectionViewItem) obj);
    }
}
