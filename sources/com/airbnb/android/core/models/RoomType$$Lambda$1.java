package com.airbnb.android.core.models;

import com.google.common.base.Predicate;

final /* synthetic */ class RoomType$$Lambda$1 implements Predicate {
    private final String arg$1;

    private RoomType$$Lambda$1(String str) {
        this.arg$1 = str;
    }

    public static Predicate lambdaFactory$(String str) {
        return new RoomType$$Lambda$1(str);
    }

    public boolean apply(Object obj) {
        return ((C6120RoomType) obj).key.equals(this.arg$1);
    }
}
