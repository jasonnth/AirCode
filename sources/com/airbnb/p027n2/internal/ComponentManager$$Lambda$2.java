package com.airbnb.p027n2.internal;

import android.view.ViewTreeObserver.OnScrollChangedListener;

/* renamed from: com.airbnb.n2.internal.ComponentManager$$Lambda$2 */
final /* synthetic */ class ComponentManager$$Lambda$2 implements OnScrollChangedListener {
    private final ComponentManager arg$1;

    private ComponentManager$$Lambda$2(ComponentManager componentManager) {
        this.arg$1 = componentManager;
    }

    public static OnScrollChangedListener lambdaFactory$(ComponentManager componentManager) {
        return new ComponentManager$$Lambda$2(componentManager);
    }

    public void onScrollChanged() {
        this.arg$1.startTraversalDebounced();
    }
}
