package com.airbnb.android.internal.bugreporter;

import com.google.common.base.Function;

final /* synthetic */ class InternalBugReportAdapter$$Lambda$9 implements Function {
    private static final InternalBugReportAdapter$$Lambda$9 instance = new InternalBugReportAdapter$$Lambda$9();

    private InternalBugReportAdapter$$Lambda$9() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return InternalBugReportAdapter.createPhotoModel((String) obj);
    }
}
