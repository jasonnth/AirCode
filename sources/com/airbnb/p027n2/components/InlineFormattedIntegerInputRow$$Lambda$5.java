package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.InlineFormattedIntegerInputRow$$Lambda$5 */
final /* synthetic */ class InlineFormattedIntegerInputRow$$Lambda$5 implements OnClickListener {
    private final InlineFormattedIntegerInputRow arg$1;

    private InlineFormattedIntegerInputRow$$Lambda$5(InlineFormattedIntegerInputRow inlineFormattedIntegerInputRow) {
        this.arg$1 = inlineFormattedIntegerInputRow;
    }

    public static OnClickListener lambdaFactory$(InlineFormattedIntegerInputRow inlineFormattedIntegerInputRow) {
        return new InlineFormattedIntegerInputRow$$Lambda$5(inlineFormattedIntegerInputRow);
    }

    public void onClick(View view) {
        InlineFormattedIntegerInputRow.lambda$new$1(this.arg$1, view);
    }
}
