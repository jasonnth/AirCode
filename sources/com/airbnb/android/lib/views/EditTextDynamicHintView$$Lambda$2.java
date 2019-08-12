package com.airbnb.android.lib.views;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class EditTextDynamicHintView$$Lambda$2 implements OnClickListener {
    private final EditTextDynamicHintView arg$1;

    private EditTextDynamicHintView$$Lambda$2(EditTextDynamicHintView editTextDynamicHintView) {
        this.arg$1 = editTextDynamicHintView;
    }

    public static OnClickListener lambdaFactory$(EditTextDynamicHintView editTextDynamicHintView) {
        return new EditTextDynamicHintView$$Lambda$2(editTextDynamicHintView);
    }

    public void onClick(View view) {
        this.arg$1.scrollToPrevOrNextHint(false);
    }
}
