package com.airbnb.android.core.models;

import java.util.Comparator;

final /* synthetic */ class CheckInInformation$$Lambda$2 implements Comparator {
    private static final CheckInInformation$$Lambda$2 instance = new CheckInInformation$$Lambda$2();

    private CheckInInformation$$Lambda$2() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return CheckInInformation.lambda$static$0((CheckInInformation) obj, (CheckInInformation) obj2);
    }
}
