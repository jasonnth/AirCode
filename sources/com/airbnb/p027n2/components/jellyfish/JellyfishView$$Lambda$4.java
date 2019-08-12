package com.airbnb.p027n2.components.jellyfish;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;

/* renamed from: com.airbnb.n2.components.jellyfish.JellyfishView$$Lambda$4 */
final /* synthetic */ class JellyfishView$$Lambda$4 implements AnimatorUpdateListener {
    private final JellyfishView arg$1;

    private JellyfishView$$Lambda$4(JellyfishView jellyfishView) {
        this.arg$1 = jellyfishView;
    }

    public static AnimatorUpdateListener lambdaFactory$(JellyfishView jellyfishView) {
        return new JellyfishView$$Lambda$4(jellyfishView);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.arg$1.onPaletteAnimationUpdate(valueAnimator.getAnimatedFraction());
    }
}
