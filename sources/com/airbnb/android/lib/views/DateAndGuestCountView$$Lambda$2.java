package com.airbnb.android.lib.views;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class DateAndGuestCountView$$Lambda$2 implements OnClickListener {
    private final DateAndGuestCountView arg$1;

    private DateAndGuestCountView$$Lambda$2(DateAndGuestCountView dateAndGuestCountView) {
        this.arg$1 = dateAndGuestCountView;
    }

    public static OnClickListener lambdaFactory$(DateAndGuestCountView dateAndGuestCountView) {
        return new DateAndGuestCountView$$Lambda$2(dateAndGuestCountView);
    }

    public void onClick(View view) {
        this.arg$1.mViewer.showDialogIfClickWithSpecialOffer();
    }
}
