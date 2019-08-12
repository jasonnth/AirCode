package com.airbnb.p027n2.components;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

/* renamed from: com.airbnb.n2.components.SmallSheetSwitchRowSwitch$$Lambda$2 */
final /* synthetic */ class SmallSheetSwitchRowSwitch$$Lambda$2 implements AnimatorUpdateListener {
    private final SmallSheetSwitchRowSwitch arg$1;

    private SmallSheetSwitchRowSwitch$$Lambda$2(SmallSheetSwitchRowSwitch smallSheetSwitchRowSwitch) {
        this.arg$1 = smallSheetSwitchRowSwitch;
    }

    public static AnimatorUpdateListener lambdaFactory$(SmallSheetSwitchRowSwitch smallSheetSwitchRowSwitch) {
        return new SmallSheetSwitchRowSwitch$$Lambda$2(smallSheetSwitchRowSwitch);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        SmallSheetSwitchRowSwitch.lambda$new$0(this.arg$1, valueAnimator);
    }
}
