package com.airbnb.android.lib.tripassistant;

import com.airbnb.android.core.models.HelpThreadAmenity;
import com.google.common.base.Function;

final /* synthetic */ class UpdateHelpThreadRequest$$Lambda$1 implements Function {
    private static final UpdateHelpThreadRequest$$Lambda$1 instance = new UpdateHelpThreadRequest$$Lambda$1();

    private UpdateHelpThreadRequest$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return String.valueOf(((HelpThreadAmenity) obj).getId());
    }
}
