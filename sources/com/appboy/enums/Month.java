package com.appboy.enums;

import com.appboy.support.AppboyLogger;

public enum Month {
    JANUARY(0),
    FEBRUARY(1),
    MARCH(2),
    APRIL(3),
    MAY(4),
    JUNE(5),
    JULY(6),
    AUGUST(7),
    SEPTEMBER(8),
    OCTOBER(9),
    NOVEMBER(10),
    DECEMBER(11);
    

    /* renamed from: a */
    private static final String f2768a = null;

    /* renamed from: b */
    private final int f2770b;

    static {
        f2768a = AppboyLogger.getAppboyLogTag(Month.class);
    }

    private Month(int value) {
        this.f2770b = value;
    }

    public int getValue() {
        return this.f2770b;
    }
}
