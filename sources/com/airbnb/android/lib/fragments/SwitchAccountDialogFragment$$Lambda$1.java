package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

final /* synthetic */ class SwitchAccountDialogFragment$$Lambda$1 implements OnItemClickListener {
    private final SwitchAccountDialogFragment arg$1;

    private SwitchAccountDialogFragment$$Lambda$1(SwitchAccountDialogFragment switchAccountDialogFragment) {
        this.arg$1 = switchAccountDialogFragment;
    }

    public static OnItemClickListener lambdaFactory$(SwitchAccountDialogFragment switchAccountDialogFragment) {
        return new SwitchAccountDialogFragment$$Lambda$1(switchAccountDialogFragment);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        SwitchAccountDialogFragment.lambda$getItemClickListener$0(this.arg$1, adapterView, view, i, j);
    }
}
