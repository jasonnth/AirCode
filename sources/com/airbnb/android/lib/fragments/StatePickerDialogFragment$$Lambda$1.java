package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

final /* synthetic */ class StatePickerDialogFragment$$Lambda$1 implements OnItemClickListener {
    private final StatePickerDialogFragment arg$1;

    private StatePickerDialogFragment$$Lambda$1(StatePickerDialogFragment statePickerDialogFragment) {
        this.arg$1 = statePickerDialogFragment;
    }

    public static OnItemClickListener lambdaFactory$(StatePickerDialogFragment statePickerDialogFragment) {
        return new StatePickerDialogFragment$$Lambda$1(statePickerDialogFragment);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        StatePickerDialogFragment.lambda$onCreateView$0(this.arg$1, adapterView, view, i, j);
    }
}
