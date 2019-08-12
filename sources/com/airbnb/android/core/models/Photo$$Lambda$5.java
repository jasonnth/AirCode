package com.airbnb.android.core.models;

import java.util.Comparator;

final /* synthetic */ class Photo$$Lambda$5 implements Comparator {
    private static final Photo$$Lambda$5 instance = new Photo$$Lambda$5();

    private Photo$$Lambda$5() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return Integer.valueOf(((Photo) obj).getSortOrder()).compareTo(Integer.valueOf(((Photo) obj2).getSortOrder()));
    }
}
