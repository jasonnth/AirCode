package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.models.LocalizedCancellationPolicy;
import com.google.common.base.Function;

final /* synthetic */ class CancellationPolicyAdapter$$Lambda$1 implements Function {
    private static final CancellationPolicyAdapter$$Lambda$1 instance = new CancellationPolicyAdapter$$Lambda$1();

    private CancellationPolicyAdapter$$Lambda$1() {
    }

    public static Function lambdaFactory$() {
        return instance;
    }

    public Object apply(Object obj) {
        return ((LocalizedCancellationPolicy) obj).getPolicyId();
    }
}
