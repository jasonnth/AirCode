package com.airbnb.p027n2.browser;

import android.view.View;
import com.airbnb.p027n2.browser.DLSOverlayLayoutInflaterFactory.OnLongClickListener;
import com.airbnb.p027n2.components.DLSComponent;

/* renamed from: com.airbnb.n2.browser.DLSOverlayLayoutInflaterFactory$$Lambda$3 */
final /* synthetic */ class DLSOverlayLayoutInflaterFactory$$Lambda$3 implements OnLongClickListener {
    private static final DLSOverlayLayoutInflaterFactory$$Lambda$3 instance = new DLSOverlayLayoutInflaterFactory$$Lambda$3();

    private DLSOverlayLayoutInflaterFactory$$Lambda$3() {
    }

    public static OnLongClickListener lambdaFactory$() {
        return instance;
    }

    public boolean onLongClick(View view, DLSComponent dLSComponent) {
        return DLSOverlayLayoutInflaterFactory.lambda$static$0(view, dLSComponent);
    }
}
