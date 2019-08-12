package com.airbnb.android.login.p339ui;

import com.airbnb.android.login.responses.ForgotPasswordResponse;
import p032rx.functions.Action1;

/* renamed from: com.airbnb.android.login.ui.ExistingAccountFragment$$Lambda$1 */
final /* synthetic */ class ExistingAccountFragment$$Lambda$1 implements Action1 {
    private final ExistingAccountFragment arg$1;

    private ExistingAccountFragment$$Lambda$1(ExistingAccountFragment existingAccountFragment) {
        this.arg$1 = existingAccountFragment;
    }

    public static Action1 lambdaFactory$(ExistingAccountFragment existingAccountFragment) {
        return new ExistingAccountFragment$$Lambda$1(existingAccountFragment);
    }

    public void call(Object obj) {
        this.arg$1.onForgotPasswordResponse((ForgotPasswordResponse) obj);
    }
}
