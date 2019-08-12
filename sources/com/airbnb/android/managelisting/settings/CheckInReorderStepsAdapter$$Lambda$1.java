package com.airbnb.android.managelisting.settings;

import com.airbnb.epoxy.EpoxyModel;
import com.google.common.base.Function;

final /* synthetic */ class CheckInReorderStepsAdapter$$Lambda$1 implements Function {
    private static final CheckInReorderStepsAdapter$$Lambda$1 instance = new CheckInReorderStepsAdapter$$Lambda$1();

    private CheckInReorderStepsAdapter$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return Long.valueOf(((EpoxyModel) obj).mo11715id());
    }
}
