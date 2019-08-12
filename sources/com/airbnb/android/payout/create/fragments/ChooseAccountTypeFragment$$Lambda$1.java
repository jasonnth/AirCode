package com.airbnb.android.payout.create.fragments;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class ChooseAccountTypeFragment$$Lambda$1 implements OnClickListener {
    private final ChooseAccountTypeFragment arg$1;

    private ChooseAccountTypeFragment$$Lambda$1(ChooseAccountTypeFragment chooseAccountTypeFragment) {
        this.arg$1 = chooseAccountTypeFragment;
    }

    public static OnClickListener lambdaFactory$(ChooseAccountTypeFragment chooseAccountTypeFragment) {
        return new ChooseAccountTypeFragment$$Lambda$1(chooseAccountTypeFragment);
    }

    public void onClick(View view) {
        ChooseAccountTypeFragment.lambda$onActivityCreated$0(this.arg$1, view);
    }
}
