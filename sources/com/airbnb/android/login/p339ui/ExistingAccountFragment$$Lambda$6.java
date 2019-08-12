package com.airbnb.android.login.p339ui;

import android.view.View;
import android.view.View.OnClickListener;

/* renamed from: com.airbnb.android.login.ui.ExistingAccountFragment$$Lambda$6 */
final /* synthetic */ class ExistingAccountFragment$$Lambda$6 implements OnClickListener {
    private final ExistingAccountFragment arg$1;

    private ExistingAccountFragment$$Lambda$6(ExistingAccountFragment existingAccountFragment) {
        this.arg$1 = existingAccountFragment;
    }

    public static OnClickListener lambdaFactory$(ExistingAccountFragment existingAccountFragment) {
        return new ExistingAccountFragment$$Lambda$6(existingAccountFragment);
    }

    public void onClick(View view) {
        this.arg$1.editPassword.showPassword(true);
    }
}
