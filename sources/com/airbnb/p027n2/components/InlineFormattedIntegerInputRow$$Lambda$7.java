package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnFocusChangeListener;

/* renamed from: com.airbnb.n2.components.InlineFormattedIntegerInputRow$$Lambda$7 */
final /* synthetic */ class InlineFormattedIntegerInputRow$$Lambda$7 implements OnFocusChangeListener {
    private final InlineFormattedIntegerInputRow arg$1;

    private InlineFormattedIntegerInputRow$$Lambda$7(InlineFormattedIntegerInputRow inlineFormattedIntegerInputRow) {
        this.arg$1 = inlineFormattedIntegerInputRow;
    }

    public static OnFocusChangeListener lambdaFactory$(InlineFormattedIntegerInputRow inlineFormattedIntegerInputRow) {
        return new InlineFormattedIntegerInputRow$$Lambda$7(inlineFormattedIntegerInputRow);
    }

    public void onFocusChange(View view, boolean z) {
        InlineFormattedIntegerInputRow.lambda$init$0(this.arg$1, view, z);
    }
}
