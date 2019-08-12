package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.InlineInputRow$$Lambda$5 */
final /* synthetic */ class InlineInputRow$$Lambda$5 implements OnClickListener {
    private final InlineInputRow arg$1;

    private InlineInputRow$$Lambda$5(InlineInputRow inlineInputRow) {
        this.arg$1 = inlineInputRow;
    }

    public static OnClickListener lambdaFactory$(InlineInputRow inlineInputRow) {
        return new InlineInputRow$$Lambda$5(inlineInputRow);
    }

    public void onClick(View view) {
        InlineInputRow.lambda$setTipValue$1(this.arg$1, view);
    }
}
