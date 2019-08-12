package com.airbnb.p027n2.browser;

import android.view.View;
import android.view.View.OnLongClickListener;
import com.airbnb.p027n2.components.DLSComponent;

/* renamed from: com.airbnb.n2.browser.DLSOverlayLayoutInflaterFactory$$Lambda$1 */
final /* synthetic */ class DLSOverlayLayoutInflaterFactory$$Lambda$1 implements OnLongClickListener {
    private final DLSComponent arg$1;

    private DLSOverlayLayoutInflaterFactory$$Lambda$1(DLSComponent dLSComponent) {
        this.arg$1 = dLSComponent;
    }

    public static OnLongClickListener lambdaFactory$(DLSComponent dLSComponent) {
        return new DLSOverlayLayoutInflaterFactory$$Lambda$1(dLSComponent);
    }

    public boolean onLongClick(View view) {
        return DLSOverlayLayoutInflaterFactory.longClickListener.onLongClick(view, this.arg$1);
    }
}
