package com.airbnb.android.checkin;

import com.airbnb.android.core.responses.CheckInGuideExamplesResponse;
import p032rx.functions.Action1;

final /* synthetic */ class ViewCheckinActivity$$Lambda$3 implements Action1 {
    private final ViewCheckinActivity arg$1;

    private ViewCheckinActivity$$Lambda$3(ViewCheckinActivity viewCheckinActivity) {
        this.arg$1 = viewCheckinActivity;
    }

    public static Action1 lambdaFactory$(ViewCheckinActivity viewCheckinActivity) {
        return new ViewCheckinActivity$$Lambda$3(viewCheckinActivity);
    }

    public void call(Object obj) {
        this.arg$1.handleGuideServerResponse(((CheckInGuideExamplesResponse) obj).example.guide);
    }
}
