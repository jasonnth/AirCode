package com.airbnb.p027n2.components;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.n2.components.FixedActionFooterWithText$$Lambda$4 */
final /* synthetic */ class FixedActionFooterWithText$$Lambda$4 implements OnClickListener {
    private final FixedActionFooterWithText arg$1;

    private FixedActionFooterWithText$$Lambda$4(FixedActionFooterWithText fixedActionFooterWithText) {
        this.arg$1 = fixedActionFooterWithText;
    }

    public static OnClickListener lambdaFactory$(FixedActionFooterWithText fixedActionFooterWithText) {
        return new FixedActionFooterWithText$$Lambda$4(fixedActionFooterWithText);
    }

    public void onClick(View view) {
        FixedActionFooterWithText.lambda$mockMissingText$3(this.arg$1, view);
    }
}
