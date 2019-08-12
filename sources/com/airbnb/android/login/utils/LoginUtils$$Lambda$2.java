package com.airbnb.android.login.utils;

import android.content.Context;
import com.google.common.base.Function;

final /* synthetic */ class LoginUtils$$Lambda$2 implements Function {
    private final Context arg$1;

    private LoginUtils$$Lambda$2(Context context) {
        this.arg$1 = context;
    }

    public static Function lambdaFactory$(Context context) {
        return new LoginUtils$$Lambda$2(context);
    }

    public Object apply(Object obj) {
        return LoginUtils.lambda$getAgreementStringWithSections$1(this.arg$1, (TOSSection) obj);
    }
}
