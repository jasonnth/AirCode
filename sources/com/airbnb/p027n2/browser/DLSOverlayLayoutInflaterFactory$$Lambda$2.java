package com.airbnb.p027n2.browser;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View.OnLayoutChangeListener;

/* renamed from: com.airbnb.n2.browser.DLSOverlayLayoutInflaterFactory$$Lambda$2 */
final /* synthetic */ class DLSOverlayLayoutInflaterFactory$$Lambda$2 implements OnLayoutChangeListener {
    private final Drawable arg$1;

    private DLSOverlayLayoutInflaterFactory$$Lambda$2(Drawable drawable) {
        this.arg$1 = drawable;
    }

    public static OnLayoutChangeListener lambdaFactory$(Drawable drawable) {
        return new DLSOverlayLayoutInflaterFactory$$Lambda$2(drawable);
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.arg$1.setBounds(0, 0, i3 - i, i4 - i2);
    }
}
