package com.airbnb.android.lib.host.stats;

public class HostMonthlyEarningsRequest extends HostEarningsRequest {
    public static HostMonthlyEarningsRequest forCurrentUser() {
        return new HostMonthlyEarningsRequest();
    }

    /* access modifiers changed from: protected */
    public String getPeriodString() {
        return "monthly";
    }
}
