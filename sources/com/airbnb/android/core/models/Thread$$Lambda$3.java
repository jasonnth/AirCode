package com.airbnb.android.core.models;

import java.util.Comparator;

final /* synthetic */ class Thread$$Lambda$3 implements Comparator {
    private static final Thread$$Lambda$3 instance = new Thread$$Lambda$3();

    private Thread$$Lambda$3() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return ((Post) obj2).getCreatedAt().compareTo(((Post) obj).getCreatedAt());
    }
}
