package com.airbnb.android.core.utils;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.facebook.internal.NativeProtocol;

public class StaticMapUtil {
    public static void onImageException(Exception e) {
        AirbnbEventLogger.event().name("android_eng").mo8238kv("type", "static_map_error").mo8238kv(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION, e == null ? "Glide returned a null exception" : e.getMessage()).track();
    }
}
