package com.airbnb.android.booking.fragments;

import com.airbnb.android.booking.p336n2.ArrivalTimeSelectionViewItem;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionSheetOnItemClickedListener;

final /* synthetic */ class ArrivalDetailsFragment$$Lambda$1 implements SelectionSheetOnItemClickedListener {
    private final ArrivalDetailsFragment arg$1;

    private ArrivalDetailsFragment$$Lambda$1(ArrivalDetailsFragment arrivalDetailsFragment) {
        this.arg$1 = arrivalDetailsFragment;
    }

    public static SelectionSheetOnItemClickedListener lambdaFactory$(ArrivalDetailsFragment arrivalDetailsFragment) {
        return new ArrivalDetailsFragment$$Lambda$1(arrivalDetailsFragment);
    }

    public void onItemClicked(Object obj) {
        ArrivalDetailsFragment.lambda$onCreateView$0(this.arg$1, (ArrivalTimeSelectionViewItem) obj);
    }
}
