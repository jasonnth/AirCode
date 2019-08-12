package com.airbnb.android.hostcalendar.fragments;

import com.airbnb.p027n2.components.bottom_sheet.BottomSheetItemClickListener;
import com.airbnb.p027n2.components.bottom_sheet.BottomSheetMenuItem;

final /* synthetic */ class SingleCalendarFragment$$Lambda$3 implements BottomSheetItemClickListener {
    private final SingleCalendarFragment arg$1;

    private SingleCalendarFragment$$Lambda$3(SingleCalendarFragment singleCalendarFragment) {
        this.arg$1 = singleCalendarFragment;
    }

    public static BottomSheetItemClickListener lambdaFactory$(SingleCalendarFragment singleCalendarFragment) {
        return new SingleCalendarFragment$$Lambda$3(singleCalendarFragment);
    }

    public void onBottomSheetItemClick(BottomSheetMenuItem bottomSheetMenuItem) {
        SingleCalendarFragment.lambda$new$3(this.arg$1, bottomSheetMenuItem);
    }
}
