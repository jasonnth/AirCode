package com.airbnb.android.lib.views;

import android.animation.ObjectAnimator;

final /* synthetic */ class MapScale$$Lambda$2 implements Runnable {
    private final MapScale arg$1;

    private MapScale$$Lambda$2(MapScale mapScale) {
        this.arg$1 = mapScale;
    }

    public static Runnable lambdaFactory$(MapScale mapScale) {
        return new MapScale$$Lambda$2(mapScale);
    }

    public void run() {
        ObjectAnimator.ofFloat(this.arg$1, "alpha", new float[]{0.0f}).setDuration(600).start();
    }
}