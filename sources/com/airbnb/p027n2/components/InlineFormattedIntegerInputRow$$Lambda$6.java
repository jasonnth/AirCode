package com.airbnb.p027n2.components;

import com.airbnb.p027n2.components.IntegerFormatInputView.Listener;

/* renamed from: com.airbnb.n2.components.InlineFormattedIntegerInputRow$$Lambda$6 */
final /* synthetic */ class InlineFormattedIntegerInputRow$$Lambda$6 implements Listener {
    private final InlineFormattedIntegerInputRow arg$1;

    private InlineFormattedIntegerInputRow$$Lambda$6(InlineFormattedIntegerInputRow inlineFormattedIntegerInputRow) {
        this.arg$1 = inlineFormattedIntegerInputRow;
    }

    public static Listener lambdaFactory$(InlineFormattedIntegerInputRow inlineFormattedIntegerInputRow) {
        return new InlineFormattedIntegerInputRow$$Lambda$6(inlineFormattedIntegerInputRow);
    }

    public void amountChanged(Integer num) {
        InlineFormattedIntegerInputRow.lambda$new$2(this.arg$1, num);
    }
}
