package com.airbnb.p027n2.browser;

import android.view.View;
import android.view.View.OnLayoutChangeListener;

/* renamed from: com.airbnb.n2.browser.DLSComponentListFragment$ViewHolder$$Lambda$2 */
final /* synthetic */ class DLSComponentListFragment$ViewHolder$$Lambda$2 implements OnLayoutChangeListener {
    private final ViewHolder arg$1;

    private DLSComponentListFragment$ViewHolder$$Lambda$2(ViewHolder viewHolder) {
        this.arg$1 = viewHolder;
    }

    public static OnLayoutChangeListener lambdaFactory$(ViewHolder viewHolder) {
        return new DLSComponentListFragment$ViewHolder$$Lambda$2(viewHolder);
    }

    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        ViewHolder.lambda$bind$2(this.arg$1, view, i, i2, i3, i4, i5, i6, i7, i8);
    }
}
