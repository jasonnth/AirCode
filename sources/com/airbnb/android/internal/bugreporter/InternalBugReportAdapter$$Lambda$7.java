package com.airbnb.android.internal.bugreporter;

import com.google.common.base.Function;
import java.io.File;

final /* synthetic */ class InternalBugReportAdapter$$Lambda$7 implements Function {
    private static final InternalBugReportAdapter$$Lambda$7 instance = new InternalBugReportAdapter$$Lambda$7();

    private InternalBugReportAdapter$$Lambda$7() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return new File((String) obj).getName();
    }
}
