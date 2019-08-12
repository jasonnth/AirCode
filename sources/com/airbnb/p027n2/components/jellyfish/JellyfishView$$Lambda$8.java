package com.airbnb.p027n2.components.jellyfish;

import android.view.View;

/* renamed from: com.airbnb.n2.components.jellyfish.JellyfishView$$Lambda$8 */
final /* synthetic */ class JellyfishView$$Lambda$8 implements UpdateListener {
    private final JellyfishView arg$1;
    private final View arg$2;

    private JellyfishView$$Lambda$8(JellyfishView jellyfishView, View view) {
        this.arg$1 = jellyfishView;
        this.arg$2 = view;
    }

    public static UpdateListener lambdaFactory$(JellyfishView jellyfishView, View view) {
        return new JellyfishView$$Lambda$8(jellyfishView, view);
    }

    public void onUpdate(float f) {
        this.arg$2.setAlpha(this.arg$1.fadeInAnimator.getAnimatedFraction() * f);
    }
}
