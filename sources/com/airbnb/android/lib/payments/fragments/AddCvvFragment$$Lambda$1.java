package com.airbnb.android.lib.payments.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class AddCvvFragment$$Lambda$1 implements OnClickListener {
    private final AddCvvFragment arg$1;

    private AddCvvFragment$$Lambda$1(AddCvvFragment addCvvFragment) {
        this.arg$1 = addCvvFragment;
    }

    public static OnClickListener lambdaFactory$(AddCvvFragment addCvvFragment) {
        return new AddCvvFragment$$Lambda$1(addCvvFragment);
    }

    public void onClick(View view) {
        AddCvvFragment.lambda$onCreateView$0(this.arg$1, view);
    }
}
