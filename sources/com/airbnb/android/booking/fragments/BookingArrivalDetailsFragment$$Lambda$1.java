package com.airbnb.android.booking.fragments;

import com.airbnb.android.booking.p336n2.ArrivalTimeSelectionViewItem;
import com.airbnb.p027n2.collections.BaseSelectionView.SelectionSheetOnItemClickedListener;

final /* synthetic */ class BookingArrivalDetailsFragment$$Lambda$1 implements SelectionSheetOnItemClickedListener {
    private final BookingArrivalDetailsFragment arg$1;

    private BookingArrivalDetailsFragment$$Lambda$1(BookingArrivalDetailsFragment bookingArrivalDetailsFragment) {
        this.arg$1 = bookingArrivalDetailsFragment;
    }

    public static SelectionSheetOnItemClickedListener lambdaFactory$(BookingArrivalDetailsFragment bookingArrivalDetailsFragment) {
        return new BookingArrivalDetailsFragment$$Lambda$1(bookingArrivalDetailsFragment);
    }

    public void onItemClicked(Object obj) {
        BookingArrivalDetailsFragment.lambda$onCreateView$0(this.arg$1, (ArrivalTimeSelectionViewItem) obj);
    }
}
