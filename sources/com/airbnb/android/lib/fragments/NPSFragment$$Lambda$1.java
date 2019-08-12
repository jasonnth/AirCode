package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class NPSFragment$$Lambda$1 implements OnClickListener {
    private final NPSFragment arg$1;

    private NPSFragment$$Lambda$1(NPSFragment nPSFragment) {
        this.arg$1 = nPSFragment;
    }

    public static OnClickListener lambdaFactory$(NPSFragment nPSFragment) {
        return new NPSFragment$$Lambda$1(nPSFragment);
    }

    public void onClick(View view) {
        NPSFragment.lambda$onCreateView$0(this.arg$1, view);
    }
}
