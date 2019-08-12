package com.airbnb.android.lib.china5a;

import com.airbnb.android.core.responses.AccountVerificationsResponse;
import com.airbnb.android.lib.china5a.FiveAxiomRepoImpl.EmailModel;
import p032rx.functions.Action1;

final /* synthetic */ class FiveAxiomRepoImpl$EmailModel$$Lambda$3 implements Action1 {
    private final EmailModel arg$1;

    private FiveAxiomRepoImpl$EmailModel$$Lambda$3(EmailModel emailModel) {
        this.arg$1 = emailModel;
    }

    public static Action1 lambdaFactory$(EmailModel emailModel) {
        return new FiveAxiomRepoImpl$EmailModel$$Lambda$3(emailModel);
    }

    public void call(Object obj) {
        EmailModel.lambda$new$4(this.arg$1, (AccountVerificationsResponse) obj);
    }
}
