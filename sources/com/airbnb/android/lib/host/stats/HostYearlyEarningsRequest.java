package com.airbnb.android.lib.host.stats;

public class HostYearlyEarningsRequest extends HostEarningsRequest {
    public static HostYearlyEarningsRequest forCurrentUser() {
        return new HostYearlyEarningsRequest();
    }

    /* access modifiers changed from: protected */
    public String getPeriodString() {
        return "yearly";
    }
}
