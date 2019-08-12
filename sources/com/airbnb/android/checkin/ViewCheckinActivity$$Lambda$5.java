package com.airbnb.android.checkin;

import com.airbnb.android.core.models.CheckInStep;
import com.airbnb.android.utils.ListUtils.Condition;

final /* synthetic */ class ViewCheckinActivity$$Lambda$5 implements Condition {
    private final String arg$1;

    private ViewCheckinActivity$$Lambda$5(String str) {
        this.arg$1 = str;
    }

    public static Condition lambdaFactory$(String str) {
        return new ViewCheckinActivity$$Lambda$5(str);
    }

    public boolean check(Object obj) {
        return this.arg$1.equals(((CheckInStep) obj).getPictureUrl());
    }
}
