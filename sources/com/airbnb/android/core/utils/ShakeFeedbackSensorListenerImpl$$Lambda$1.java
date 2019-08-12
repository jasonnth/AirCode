package com.airbnb.android.core.utils;

import android.support.p000v4.app.FragmentActivity;
import com.airbnb.android.utils.ShakeEventListener.OnShakeListener;

final /* synthetic */ class ShakeFeedbackSensorListenerImpl$$Lambda$1 implements OnShakeListener {
    private final ShakeFeedbackSensorListenerImpl arg$1;
    private final FragmentActivity arg$2;

    private ShakeFeedbackSensorListenerImpl$$Lambda$1(ShakeFeedbackSensorListenerImpl shakeFeedbackSensorListenerImpl, FragmentActivity fragmentActivity) {
        this.arg$1 = shakeFeedbackSensorListenerImpl;
        this.arg$2 = fragmentActivity;
    }

    public static OnShakeListener lambdaFactory$(ShakeFeedbackSensorListenerImpl shakeFeedbackSensorListenerImpl, FragmentActivity fragmentActivity) {
        return new ShakeFeedbackSensorListenerImpl$$Lambda$1(shakeFeedbackSensorListenerImpl, fragmentActivity);
    }

    public void onShake() {
        this.arg$1.onShake(this.arg$2);
    }
}
