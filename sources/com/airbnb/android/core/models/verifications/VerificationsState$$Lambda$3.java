package com.airbnb.android.core.models.verifications;

import com.airbnb.android.core.models.Verification;
import java.util.Comparator;

final /* synthetic */ class VerificationsState$$Lambda$3 implements Comparator {
    private static final VerificationsState$$Lambda$3 instance = new VerificationsState$$Lambda$3();

    private VerificationsState$$Lambda$3() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return VerificationsState.lambda$moveCompletedVerificationsToFront$2((Verification) obj, (Verification) obj2);
    }
}
