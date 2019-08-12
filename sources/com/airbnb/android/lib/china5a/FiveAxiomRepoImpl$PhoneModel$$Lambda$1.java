package com.airbnb.android.lib.china5a;

import com.airbnb.android.lib.china5a.FiveAxiomRepoImpl.PhoneModel;
import p032rx.functions.Action1;

final /* synthetic */ class FiveAxiomRepoImpl$PhoneModel$$Lambda$1 implements Action1 {
    private final PhoneModel arg$1;

    private FiveAxiomRepoImpl$PhoneModel$$Lambda$1(PhoneModel phoneModel) {
        this.arg$1 = phoneModel;
    }

    public static Action1 lambdaFactory$(PhoneModel phoneModel) {
        return new FiveAxiomRepoImpl$PhoneModel$$Lambda$1(phoneModel);
    }

    public void call(Object obj) {
        PhoneModel.lambda$new$0(this.arg$1, obj);
    }
}
