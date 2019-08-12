package com.airbnb.android.core.utils;

import com.airbnb.android.core.models.NameCodeItem;
import java.util.Comparator;

final /* synthetic */ class LocationUtil$$Lambda$1 implements Comparator {
    private static final LocationUtil$$Lambda$1 instance = new LocationUtil$$Lambda$1();

    private LocationUtil$$Lambda$1() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return ((NameCodeItem) obj).getName().compareTo(((NameCodeItem) obj2).getName());
    }
}
