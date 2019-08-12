package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

final /* synthetic */ class RadioButtonListZenDialogFragment$$Lambda$1 implements OnItemClickListener {
    private final RadioButtonListZenDialogFragment arg$1;

    private RadioButtonListZenDialogFragment$$Lambda$1(RadioButtonListZenDialogFragment radioButtonListZenDialogFragment) {
        this.arg$1 = radioButtonListZenDialogFragment;
    }

    public static OnItemClickListener lambdaFactory$(RadioButtonListZenDialogFragment radioButtonListZenDialogFragment) {
        return new RadioButtonListZenDialogFragment$$Lambda$1(radioButtonListZenDialogFragment);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        RadioButtonListZenDialogFragment.lambda$getItemClickListener$0(this.arg$1, adapterView, view, i, j);
    }
}
