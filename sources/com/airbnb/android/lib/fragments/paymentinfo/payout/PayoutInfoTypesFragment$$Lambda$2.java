package com.airbnb.android.lib.fragments.paymentinfo.payout;

import com.airbnb.android.booking.p336n2.SimpleSelectionViewItem;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionSheetOnItemClickedListener;

final /* synthetic */ class PayoutInfoTypesFragment$$Lambda$2 implements SelectionSheetOnItemClickedListener {
    private final PayoutInfoTypesFragment arg$1;

    private PayoutInfoTypesFragment$$Lambda$2(PayoutInfoTypesFragment payoutInfoTypesFragment) {
        this.arg$1 = payoutInfoTypesFragment;
    }

    public static SelectionSheetOnItemClickedListener lambdaFactory$(PayoutInfoTypesFragment payoutInfoTypesFragment) {
        return new PayoutInfoTypesFragment$$Lambda$2(payoutInfoTypesFragment);
    }

    public void onItemClicked(Object obj) {
        PayoutInfoTypesFragment.lambda$onCreateView$1(this.arg$1, (SimpleSelectionViewItem) obj);
    }
}
