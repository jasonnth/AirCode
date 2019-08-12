package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.InlineInputRow$$Lambda$1 */
final /* synthetic */ class InlineInputRow$$Lambda$1 implements OnClickListener {
    private final InlineInputRow arg$1;

    private InlineInputRow$$Lambda$1(InlineInputRow inlineInputRow) {
        this.arg$1 = inlineInputRow;
    }

    public static OnClickListener lambdaFactory$(InlineInputRow inlineInputRow) {
        return new InlineInputRow$$Lambda$1(inlineInputRow);
    }

    public void onClick(View view) {
        this.arg$1.editText.setText("");
    }
}
