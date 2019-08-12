package com.airbnb.android.core.activities;

import com.airbnb.p027n2.internal.ComponentVisitor.OnComponentDisplayListener;
import java.util.List;

final /* synthetic */ class AirActivity$$Lambda$1 implements OnComponentDisplayListener {
    private final AirActivity arg$1;

    private AirActivity$$Lambda$1(AirActivity airActivity) {
        this.arg$1 = airActivity;
    }

    public static OnComponentDisplayListener lambdaFactory$(AirActivity airActivity) {
        return new AirActivity$$Lambda$1(airActivity);
    }

    public void onComponentDisplay(List list) {
        this.arg$1.DLSlogger.componentsDisplayed(list);
    }
}
