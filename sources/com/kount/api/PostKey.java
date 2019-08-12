package com.kount.api;

import android.support.p000v4.app.NotificationCompat;

enum PostKey {
    LOCATION_LATITUDE("lat"),
    LOCATION_LONGITUDE("lon"),
    LOCATION_DATE("ltm"),
    SDK_VERSION("sv"),
    SDK_TYPE("st"),
    MOBILE_MODEL("mdl"),
    SOFT_ERRORS(NotificationCompat.CATEGORY_ERROR),
    MERCHANT_ID("m"),
    SESSION_ID("s"),
    OS_VERSION("os"),
    DEVICE_COOKIE("dc"),
    OLD_DEVICE_COOKIE("odc"),
    ELAPSED("elapsed"),
    TOTAL_MEMORY("dmm"),
    LANGUAGE("ln"),
    SCREEN_AVAILABLE("sa"),
    TIMEZONE_AUGUST("ta"),
    TIMEZONE_FEBRUARY("tf");
    
    private final String name;

    private PostKey(String name2) {
        this.name = name2;
    }

    public String toString() {
        return this.name;
    }
}
