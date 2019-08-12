package com.appboy;

import android.os.Build;

public final class Constants {
    public static final String APPBOY_LOG_TAG_PREFIX = String.format("%s v%s ", new Object[]{"Appboy", "1.18.0"});
    public static final Boolean IS_AMAZON = Boolean.valueOf(Build.MANUFACTURER.equals("Amazon"));
}
