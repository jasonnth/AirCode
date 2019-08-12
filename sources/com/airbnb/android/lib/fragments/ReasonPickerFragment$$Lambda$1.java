package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ReasonPickerFragment$$Lambda$1 implements OnClickListener {
    private final ReasonPickerFragment arg$1;

    private ReasonPickerFragment$$Lambda$1(ReasonPickerFragment reasonPickerFragment) {
        this.arg$1 = reasonPickerFragment;
    }

    public static OnClickListener lambdaFactory$(ReasonPickerFragment reasonPickerFragment) {
        return new ReasonPickerFragment$$Lambda$1(reasonPickerFragment);
    }

    public void onClick(View view) {
        this.arg$1.getActivity().onBackPressed();
    }
}
