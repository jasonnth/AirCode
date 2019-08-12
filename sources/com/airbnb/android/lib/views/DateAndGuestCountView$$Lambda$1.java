package com.airbnb.android.lib.views;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class DateAndGuestCountView$$Lambda$1 implements OnClickListener {
    private final DateAndGuestCountView arg$1;
    private final boolean arg$2;

    private DateAndGuestCountView$$Lambda$1(DateAndGuestCountView dateAndGuestCountView, boolean z) {
        this.arg$1 = dateAndGuestCountView;
        this.arg$2 = z;
    }

    public static OnClickListener lambdaFactory$(DateAndGuestCountView dateAndGuestCountView, boolean z) {
        return new DateAndGuestCountView$$Lambda$1(dateAndGuestCountView, z);
    }

    public void onClick(View view) {
        DateAndGuestCountView.lambda$init$0(this.arg$1, this.arg$2, view);
    }
}
