package com.airbnb.android.lib.views;

final /* synthetic */ class DateAndGuestCountView$$Lambda$4 implements Runnable {
    private final DateAndGuestCountView arg$1;
    private final int arg$2;

    private DateAndGuestCountView$$Lambda$4(DateAndGuestCountView dateAndGuestCountView, int i) {
        this.arg$1 = dateAndGuestCountView;
        this.arg$2 = i;
    }

    public static Runnable lambdaFactory$(DateAndGuestCountView dateAndGuestCountView, int i) {
        return new DateAndGuestCountView$$Lambda$4(dateAndGuestCountView, i);
    }

    public void run() {
        this.arg$1.doGuestCountOnValueChanged(this.arg$2);
    }
}
