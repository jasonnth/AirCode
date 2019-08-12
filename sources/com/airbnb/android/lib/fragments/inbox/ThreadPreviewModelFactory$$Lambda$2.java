package com.airbnb.android.lib.fragments.inbox;

import com.airbnb.android.core.models.User;
import com.google.common.base.Function;

final /* synthetic */ class ThreadPreviewModelFactory$$Lambda$2 implements Function {
    private static final ThreadPreviewModelFactory$$Lambda$2 instance = new ThreadPreviewModelFactory$$Lambda$2();

    private ThreadPreviewModelFactory$$Lambda$2() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((User) obj).getPictureUrl();
    }
}
