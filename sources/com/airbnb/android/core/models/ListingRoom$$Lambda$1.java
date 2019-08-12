package com.airbnb.android.core.models;

import java.util.Comparator;

final /* synthetic */ class ListingRoom$$Lambda$1 implements Comparator {
    private static final ListingRoom$$Lambda$1 instance = new ListingRoom$$Lambda$1();

    private ListingRoom$$Lambda$1() {
    }

    public static Comparator lambdaFactory$() {
        return instance;
    }

    public int compare(Object obj, Object obj2) {
        return ListingRoom.lambda$static$0((ListingRoom) obj, (ListingRoom) obj2);
    }
}
