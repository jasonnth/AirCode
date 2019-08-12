package com.airbnb.android.lib.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ProPhotographyFragment$$Lambda$1 implements OnClickListener {
    private final ProPhotographyFragment arg$1;

    private ProPhotographyFragment$$Lambda$1(ProPhotographyFragment proPhotographyFragment) {
        this.arg$1 = proPhotographyFragment;
    }

    public static OnClickListener lambdaFactory$(ProPhotographyFragment proPhotographyFragment) {
        return new ProPhotographyFragment$$Lambda$1(proPhotographyFragment);
    }

    public void onClick(View view) {
        ProPhotographyFragment.lambda$onCreateView$0(this.arg$1, view);
    }
}
