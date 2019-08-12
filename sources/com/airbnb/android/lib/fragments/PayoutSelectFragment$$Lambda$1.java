package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

final /* synthetic */ class PayoutSelectFragment$$Lambda$1 implements OnItemClickListener {
    private final PayoutSelectFragment arg$1;

    private PayoutSelectFragment$$Lambda$1(PayoutSelectFragment payoutSelectFragment) {
        this.arg$1 = payoutSelectFragment;
    }

    public static OnItemClickListener lambdaFactory$(PayoutSelectFragment payoutSelectFragment) {
        return new PayoutSelectFragment$$Lambda$1(payoutSelectFragment);
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        PayoutSelectFragment.lambda$onCreateView$0(this.arg$1, adapterView, view, i, j);
    }
}
