package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

final /* synthetic */ class OptionSelectionDialog$$Lambda$1 implements OnItemClickListener {
    private final OptionSelectionDialog arg$1;

    private OptionSelectionDialog$$Lambda$1(OptionSelectionDialog optionSelectionDialog) {
        this.arg$1 = optionSelectionDialog;
    }

    public static OnItemClickListener lambdaFactory$(OptionSelectionDialog optionSelectionDialog) {
        return new OptionSelectionDialog$$Lambda$1(optionSelectionDialog);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        OptionSelectionDialog.lambda$new$0(this.arg$1, adapterView, view, i, j);
    }
}
