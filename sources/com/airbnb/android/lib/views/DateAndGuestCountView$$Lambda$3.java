package com.airbnb.android.lib.views;

import com.airbnb.android.lib.views.BaseCounter.OnValueChangeListener;

final /* synthetic */ class DateAndGuestCountView$$Lambda$3 implements OnValueChangeListener {
    private final DateAndGuestCountView arg$1;

    private DateAndGuestCountView$$Lambda$3(DateAndGuestCountView dateAndGuestCountView) {
        this.arg$1 = dateAndGuestCountView;
    }

    public static OnValueChangeListener lambdaFactory$(DateAndGuestCountView dateAndGuestCountView) {
        return new DateAndGuestCountView$$Lambda$3(dateAndGuestCountView);
    }

    public void onValueChange(BaseCounter baseCounter, int i) {
        DateAndGuestCountView.lambda$init$3(this.arg$1, baseCounter, i);
    }
}
