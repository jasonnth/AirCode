package com.airbnb.android.core.models;

import java.util.Comparator;

final /* synthetic */ class BedType$$Lambda$1 implements Comparator {
    private static final BedType$$Lambda$1 instance = new BedType$$Lambda$1();

    private BedType$$Lambda$1() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return ((BedType) obj).getType().compareTo(((BedType) obj2).getType());
    }
}
