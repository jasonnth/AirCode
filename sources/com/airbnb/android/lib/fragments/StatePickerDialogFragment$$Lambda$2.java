package com.airbnb.android.lib.fragments;

final /* synthetic */ class StatePickerDialogFragment$$Lambda$2 implements Runnable {
    private final StatePickerDialogFragment arg$1;

    private StatePickerDialogFragment$$Lambda$2(StatePickerDialogFragment statePickerDialogFragment) {
        this.arg$1 = statePickerDialogFragment;
    }

    public static Runnable lambdaFactory$(StatePickerDialogFragment statePickerDialogFragment) {
        return new StatePickerDialogFragment$$Lambda$2(statePickerDialogFragment);
    }

    public void run() {
        StatePickerDialogFragment.lambda$onCreateView$1(this.arg$1);
    }
}
