package com.airbnb.android.lib.china5a;

import com.airbnb.android.lib.china5a.FiveAxiomRepoImpl.EmailModel;
import p032rx.functions.Action1;

final /* synthetic */ class FiveAxiomRepoImpl$EmailModel$$Lambda$4 implements Action1 {
    private final EmailModel arg$1;

    private FiveAxiomRepoImpl$EmailModel$$Lambda$4(EmailModel emailModel) {
        this.arg$1 = emailModel;
    }

    public static Action1 lambdaFactory$(EmailModel emailModel) {
        return new FiveAxiomRepoImpl$EmailModel$$Lambda$4(emailModel);
    }

    public void call(Object obj) {
        this.arg$1.queryVerificationResultDelay();
    }
}
