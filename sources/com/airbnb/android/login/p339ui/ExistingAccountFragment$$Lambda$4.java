package com.airbnb.android.login.p339ui;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.android.login.ui.ExistingAccountFragment$$Lambda$4 */
final /* synthetic */ class ExistingAccountFragment$$Lambda$4 implements OnClickListener {
    private final ExistingAccountFragment arg$1;

    private ExistingAccountFragment$$Lambda$4(ExistingAccountFragment existingAccountFragment) {
        this.arg$1 = existingAccountFragment;
    }

    public static OnClickListener lambdaFactory$(ExistingAccountFragment existingAccountFragment) {
        return new ExistingAccountFragment$$Lambda$4(existingAccountFragment);
    }

    public void onClick(View view) {
        ExistingAccountFragment.lambda$setupViewForExistingSocialAccount$1(this.arg$1, view);
    }
}
