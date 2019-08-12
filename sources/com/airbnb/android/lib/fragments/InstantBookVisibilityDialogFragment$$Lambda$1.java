package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

final /* synthetic */ class InstantBookVisibilityDialogFragment$$Lambda$1 implements OnItemClickListener {
    private final InstantBookVisibilityDialogFragment arg$1;

    private InstantBookVisibilityDialogFragment$$Lambda$1(InstantBookVisibilityDialogFragment instantBookVisibilityDialogFragment) {
        this.arg$1 = instantBookVisibilityDialogFragment;
    }

    public static OnItemClickListener lambdaFactory$(InstantBookVisibilityDialogFragment instantBookVisibilityDialogFragment) {
        return new InstantBookVisibilityDialogFragment$$Lambda$1(instantBookVisibilityDialogFragment);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        InstantBookVisibilityDialogFragment.lambda$getItemClickListener$0(this.arg$1, adapterView, view, i, j);
    }
}
