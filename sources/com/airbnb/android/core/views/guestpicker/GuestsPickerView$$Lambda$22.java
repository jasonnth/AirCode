package com.airbnb.android.core.views.guestpicker;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class GuestsPickerView$$Lambda$22 implements OnClickListener {
    private static final GuestsPickerView$$Lambda$22 instance = new GuestsPickerView$$Lambda$22();

    private GuestsPickerView$$Lambda$22() {
    }

    public static OnClickListener lambdaFactory$() {
        return instance;
    }

    public void onClick(View view) {
        GuestsPickerView.lambda$showInfantsAndChildrenWarningIfNeeded$7(view);
    }
}
