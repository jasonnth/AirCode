package com.airbnb.android.lib.fragments.paymentinfo.payout;

import com.airbnb.android.booking.p336n2.SimpleSelectionViewItem;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionSheetOnItemClickedListener;

final /* synthetic */ class DirectDepositOptionFragment$$Lambda$1 implements SelectionSheetOnItemClickedListener {
    private final DirectDepositOptionFragment arg$1;

    private DirectDepositOptionFragment$$Lambda$1(DirectDepositOptionFragment directDepositOptionFragment) {
        this.arg$1 = directDepositOptionFragment;
    }

    public static SelectionSheetOnItemClickedListener lambdaFactory$(DirectDepositOptionFragment directDepositOptionFragment) {
        return new DirectDepositOptionFragment$$Lambda$1(directDepositOptionFragment);
    }

    public void onItemClicked(Object obj) {
        DirectDepositOptionFragment.lambda$onCreateView$0(this.arg$1, (SimpleSelectionViewItem) obj);
    }
}
