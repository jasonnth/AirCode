package com.airbnb.p027n2.primitives;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

/* renamed from: com.airbnb.n2.primitives.AirSwitch$$Lambda$2 */
final /* synthetic */ class AirSwitch$$Lambda$2 implements AnimatorUpdateListener {
    private final AirSwitch arg$1;

    private AirSwitch$$Lambda$2(AirSwitch airSwitch) {
        this.arg$1 = airSwitch;
    }

    public static AnimatorUpdateListener lambdaFactory$(AirSwitch airSwitch) {
        return new AirSwitch$$Lambda$2(airSwitch);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        AirSwitch.lambda$new$0(this.arg$1, valueAnimator);
    }
}
