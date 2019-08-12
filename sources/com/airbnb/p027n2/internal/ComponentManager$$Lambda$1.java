package com.airbnb.p027n2.internal;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

/* renamed from: com.airbnb.n2.internal.ComponentManager$$Lambda$1 */
final /* synthetic */ class ComponentManager$$Lambda$1 implements OnGlobalLayoutListener {
    private final ComponentManager arg$1;

    private ComponentManager$$Lambda$1(ComponentManager componentManager) {
        this.arg$1 = componentManager;
    }

    public static OnGlobalLayoutListener lambdaFactory$(ComponentManager componentManager) {
        return new ComponentManager$$Lambda$1(componentManager);
    }

    public void onGlobalLayout() {
        this.arg$1.startTraversalDebounced();
    }
}
