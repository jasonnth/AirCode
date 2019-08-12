package com.airbnb.android.core.glide;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger.Builder;
import com.airbnb.android.core.utils.Trebuchet;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Random;

public class ImageLoadingAnalytics {
    private static final String CANCEL_WHILE_LOADING = "cancel_while_loading";
    private static final String IMAGE_LOADED = "image_loaded";
    private static final String IMAGE_LOAD_ERROR = "error_image_load";
    private static final Random RANDOM = new Random();
    private static final int SAMPLING_PERCENTAGE = 1;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Status {
    }

    public static void maybeLogImageLoaded(long loadTime, boolean fromMemoryCache) {
        if (!fromMemoryCache) {
            maybeLogEvent(IMAGE_LOADED, loadTime);
        }
    }

    public static void maybeLogImageLoadCleared(long loadTime) {
        maybeLogEvent(CANCEL_WHILE_LOADING, loadTime);
    }

    public static void maybeLogImageLoadError(long loadTime, Exception exception) {
        maybeLogEvent(IMAGE_LOAD_ERROR, loadTime, exception);
    }

    private static void maybeLogEvent(String status, long loadTime) {
        maybeLogEvent(status, loadTime, null);
    }

    private static void maybeLogEvent(String status, long loadTime, Exception e) {
        if (Trebuchet.launch("android_eng", "disable_image_load_logging", false)) {
            return;
        }
        if (e != null || RANDOM.nextInt(100) < 1) {
            Builder builder = AirbnbEventLogger.event().name("android_eng").mo8238kv("status", status).mo8237kv("loading_time_displayed", loadTime);
            if (e != null) {
                builder.mo8238kv("errorDescription", e.getMessage());
                builder.mo8238kv("errorLocalizedDescription", e.getLocalizedMessage());
            }
            builder.logToDatadog().track();
        }
    }
}
