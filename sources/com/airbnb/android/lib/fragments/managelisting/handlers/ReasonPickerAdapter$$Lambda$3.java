package com.airbnb.android.lib.fragments.managelisting.handlers;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ReasonPickerAdapter$$Lambda$3 implements OnClickListener {
    private final ReasonPickerAdapter arg$1;

    private ReasonPickerAdapter$$Lambda$3(ReasonPickerAdapter reasonPickerAdapter) {
        this.arg$1 = reasonPickerAdapter;
    }

    public static OnClickListener lambdaFactory$(ReasonPickerAdapter reasonPickerAdapter) {
        return new ReasonPickerAdapter$$Lambda$3(reasonPickerAdapter);
    }

    public void onClick(View view) {
        this.arg$1.callback.onKeepReservationClicked();
    }
}
