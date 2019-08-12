package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnFocusChangeListener;

/* renamed from: com.airbnb.n2.components.InlineInputWithContactPickerRow$$Lambda$1 */
final /* synthetic */ class InlineInputWithContactPickerRow$$Lambda$1 implements OnFocusChangeListener {
    private final InlineInputWithContactPickerRow arg$1;

    private InlineInputWithContactPickerRow$$Lambda$1(InlineInputWithContactPickerRow inlineInputWithContactPickerRow) {
        this.arg$1 = inlineInputWithContactPickerRow;
    }

    public static OnFocusChangeListener lambdaFactory$(InlineInputWithContactPickerRow inlineInputWithContactPickerRow) {
        return new InlineInputWithContactPickerRow$$Lambda$1(inlineInputWithContactPickerRow);
    }

    public void onFocusChange(View view, boolean z) {
        InlineInputWithContactPickerRow.lambda$init$0(this.arg$1, view, z);
    }
}
