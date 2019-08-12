package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

final /* synthetic */ class InstantBookAdvanceNoticeDialogFragment$$Lambda$1 implements OnItemClickListener {
    private final InstantBookAdvanceNoticeDialogFragment arg$1;

    private InstantBookAdvanceNoticeDialogFragment$$Lambda$1(InstantBookAdvanceNoticeDialogFragment instantBookAdvanceNoticeDialogFragment) {
        this.arg$1 = instantBookAdvanceNoticeDialogFragment;
    }

    public static OnItemClickListener lambdaFactory$(InstantBookAdvanceNoticeDialogFragment instantBookAdvanceNoticeDialogFragment) {
        return new InstantBookAdvanceNoticeDialogFragment$$Lambda$1(instantBookAdvanceNoticeDialogFragment);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        InstantBookAdvanceNoticeDialogFragment.lambda$getItemClickListener$0(this.arg$1, adapterView, view, i, j);
    }
}
