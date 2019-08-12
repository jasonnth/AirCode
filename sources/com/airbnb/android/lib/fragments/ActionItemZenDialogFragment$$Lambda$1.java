package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

final /* synthetic */ class ActionItemZenDialogFragment$$Lambda$1 implements OnItemClickListener {
    private final ActionItemZenDialogFragment arg$1;

    private ActionItemZenDialogFragment$$Lambda$1(ActionItemZenDialogFragment actionItemZenDialogFragment) {
        this.arg$1 = actionItemZenDialogFragment;
    }

    public static OnItemClickListener lambdaFactory$(ActionItemZenDialogFragment actionItemZenDialogFragment) {
        return new ActionItemZenDialogFragment$$Lambda$1(actionItemZenDialogFragment);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        ActionItemZenDialogFragment.lambda$getItemClickListener$0(this.arg$1, adapterView, view, i, j);
    }
}
