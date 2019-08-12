package com.airbnb.p027n2.components.jellyfish;

import android.view.View;

/* renamed from: com.airbnb.n2.components.jellyfish.JellyfishView$$Lambda$6 */
final /* synthetic */ class JellyfishView$$Lambda$6 implements UpdateListener {
    private final View arg$1;

    private JellyfishView$$Lambda$6(View view) {
        this.arg$1 = view;
    }

    public static UpdateListener lambdaFactory$(View view) {
        return new JellyfishView$$Lambda$6(view);
    }

    public void onUpdate(float f) {
        this.arg$1.setScaleY(f);
    }
}
