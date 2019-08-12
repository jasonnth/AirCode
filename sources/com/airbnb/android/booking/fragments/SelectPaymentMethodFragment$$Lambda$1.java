package com.airbnb.android.booking.fragments;

import com.airbnb.android.booking.activities.LegacyAddPaymentMethodActivity;
import com.airbnb.android.booking.p336n2.SimpleSelectionViewItem;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionSheetOnItemClickedListener;

final /* synthetic */ class SelectPaymentMethodFragment$$Lambda$1 implements SelectionSheetOnItemClickedListener {
    private final SelectPaymentMethodFragment arg$1;
    private final LegacyAddPaymentMethodActivity arg$2;

    private SelectPaymentMethodFragment$$Lambda$1(SelectPaymentMethodFragment selectPaymentMethodFragment, LegacyAddPaymentMethodActivity legacyAddPaymentMethodActivity) {
        this.arg$1 = selectPaymentMethodFragment;
        this.arg$2 = legacyAddPaymentMethodActivity;
    }

    public static SelectionSheetOnItemClickedListener lambdaFactory$(SelectPaymentMethodFragment selectPaymentMethodFragment, LegacyAddPaymentMethodActivity legacyAddPaymentMethodActivity) {
        return new SelectPaymentMethodFragment$$Lambda$1(selectPaymentMethodFragment, legacyAddPaymentMethodActivity);
    }

    public void onItemClicked(Object obj) {
        SelectPaymentMethodFragment.lambda$initializeSelectionSheetPresenter$0(this.arg$1, this.arg$2, (SimpleSelectionViewItem) obj);
    }
}
