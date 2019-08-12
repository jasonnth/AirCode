package com.airbnb.android.lib.views;

import android.view.View;
import android.view.View.OnFocusChangeListener;

final /* synthetic */ class NumberAndUnitsEditText$$Lambda$1 implements OnFocusChangeListener {
    private final NumberAndUnitsEditText arg$1;

    private NumberAndUnitsEditText$$Lambda$1(NumberAndUnitsEditText numberAndUnitsEditText) {
        this.arg$1 = numberAndUnitsEditText;
    }

    public static OnFocusChangeListener lambdaFactory$(NumberAndUnitsEditText numberAndUnitsEditText) {
        return new NumberAndUnitsEditText$$Lambda$1(numberAndUnitsEditText);
    }

    public void onFocusChange(View view, boolean z) {
        NumberAndUnitsEditText.lambda$setupViews$0(this.arg$1, view, z);
    }
}
