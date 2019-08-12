package com.airbnb.android.checkin;

import com.airbnb.android.checkin.data.CheckInGuideData;
import p032rx.functions.Func1;

final /* synthetic */ class ViewCheckinActivity$$Lambda$7 implements Func1 {
    private static final ViewCheckinActivity$$Lambda$7 instance = new ViewCheckinActivity$$Lambda$7();

    private ViewCheckinActivity$$Lambda$7() {
    }

    public static Func1 lambdaFactory$() {
        return instance;
    }

    public Object call(Object obj) {
        return ViewCheckinActivity.lambda$getGuideFromDatabase$8((CheckInGuideData) obj);
    }
}
