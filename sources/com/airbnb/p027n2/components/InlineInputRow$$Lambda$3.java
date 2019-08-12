package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.InlineInputRow$$Lambda$3 */
final /* synthetic */ class InlineInputRow$$Lambda$3 implements OnClickListener {
    private final InlineInputRow arg$1;

    private InlineInputRow$$Lambda$3(InlineInputRow inlineInputRow) {
        this.arg$1 = inlineInputRow;
    }

    public static OnClickListener lambdaFactory$(InlineInputRow inlineInputRow) {
        return new InlineInputRow$$Lambda$3(inlineInputRow);
    }

    public void onClick(View view) {
        this.arg$1.editText.setText("");
    }
}
