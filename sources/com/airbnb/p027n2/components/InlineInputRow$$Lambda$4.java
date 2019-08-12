package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnFocusChangeListener;

/* renamed from: com.airbnb.n2.components.InlineInputRow$$Lambda$4 */
final /* synthetic */ class InlineInputRow$$Lambda$4 implements OnFocusChangeListener {
    private final InlineInputRow arg$1;

    private InlineInputRow$$Lambda$4(InlineInputRow inlineInputRow) {
        this.arg$1 = inlineInputRow;
    }

    public static OnFocusChangeListener lambdaFactory$(InlineInputRow inlineInputRow) {
        return new InlineInputRow$$Lambda$4(inlineInputRow);
    }

    public void onFocusChange(View view, boolean z) {
        InlineInputRow.lambda$init$0(this.arg$1, view, z);
    }
}
