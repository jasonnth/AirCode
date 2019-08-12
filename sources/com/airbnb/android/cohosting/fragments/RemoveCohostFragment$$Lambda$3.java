package com.airbnb.android.cohosting.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class RemoveCohostFragment$$Lambda$3 implements OnClickListener {
    private final RemoveCohostFragment arg$1;

    private RemoveCohostFragment$$Lambda$3(RemoveCohostFragment removeCohostFragment) {
        this.arg$1 = removeCohostFragment;
    }

    public static OnClickListener lambdaFactory$(RemoveCohostFragment removeCohostFragment) {
        return new RemoveCohostFragment$$Lambda$3(removeCohostFragment);
    }

    public void onClick(View view) {
        RemoveCohostFragment.lambda$onCreateView$2(this.arg$1, view);
    }
}
