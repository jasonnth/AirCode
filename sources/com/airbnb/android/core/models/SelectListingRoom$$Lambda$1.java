package com.airbnb.android.core.models;

import java.util.Comparator;

final /* synthetic */ class SelectListingRoom$$Lambda$1 implements Comparator {
    private static final SelectListingRoom$$Lambda$1 instance = new SelectListingRoom$$Lambda$1();

    private SelectListingRoom$$Lambda$1() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return SelectListingRoom.lambda$static$0((SelectListingRoom) obj, (SelectListingRoom) obj2);
    }
}
