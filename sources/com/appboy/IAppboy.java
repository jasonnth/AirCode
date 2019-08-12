package com.appboy;

import com.appboy.models.outgoing.AppboyProperties;
import java.math.BigDecimal;

public interface IAppboy {
    AppboyUser getCurrentUser();

    boolean logCustomEvent(String str);

    boolean logCustomEvent(String str, AppboyProperties appboyProperties);

    boolean logPurchase(String str, String str2, BigDecimal bigDecimal);

    boolean logPurchase(String str, String str2, BigDecimal bigDecimal, AppboyProperties appboyProperties);

    @Deprecated
    boolean logPushNotificationOpened(String str);

    boolean submitFeedback(String str, String str2, boolean z);
}
