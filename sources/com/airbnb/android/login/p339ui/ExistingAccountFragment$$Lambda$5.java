package com.airbnb.android.login.p339ui;

import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

/* renamed from: com.airbnb.android.login.ui.ExistingAccountFragment$$Lambda$5 */
final /* synthetic */ class ExistingAccountFragment$$Lambda$5 implements OnEditorActionListener {
    private final ExistingAccountFragment arg$1;

    private ExistingAccountFragment$$Lambda$5(ExistingAccountFragment existingAccountFragment) {
        this.arg$1 = existingAccountFragment;
    }

    public static OnEditorActionListener lambdaFactory$(ExistingAccountFragment existingAccountFragment) {
        return new ExistingAccountFragment$$Lambda$5(existingAccountFragment);
    }

    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        return ExistingAccountFragment.lambda$setupViewForExistingEmailAccount$2(this.arg$1, textView, i, keyEvent);
    }
}
