package com.airbnb.p027n2.primitives;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

/* renamed from: com.airbnb.n2.primitives.AirSwitch$$Lambda$3 */
final /* synthetic */ class AirSwitch$$Lambda$3 implements AnimatorUpdateListener {
    private final AirSwitch arg$1;

    private AirSwitch$$Lambda$3(AirSwitch airSwitch) {
        this.arg$1 = airSwitch;
    }

    public static AnimatorUpdateListener lambdaFactory$(AirSwitch airSwitch) {
        return new AirSwitch$$Lambda$3(airSwitch);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        AirSwitch.lambda$new$0(this.arg$1, valueAnimator);
    }
}
