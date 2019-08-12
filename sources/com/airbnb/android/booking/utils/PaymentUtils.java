package com.airbnb.android.booking.utils;

import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.utils.Trebuchet;

public class PaymentUtils {
    private static final int MIN_LENGTH_ALIPAY_PHONE_INPUT = 4;
    private static final String ONLY_DIGITS_REGEX = "[0-9]+";
    private static final int VERICATION_RETRY_WINDOW_MILLIS = 60000;

    public static boolean isOtherPaymentEligible() {
        if (Trebuchet.launch(Trebuchet.KEY_OTHER_PAYMENTS_WEB_VIEW, Trebuchet.KEY_ANDROID_ENABLED, false) || (Trebuchet.launch(Trebuchet.KEY_OTHER_PAYMENTS_WEB_VIEW_INDIA, Trebuchet.KEY_ANDROID_ENABLED, false) && AppLaunchUtils.isIndiaRegion())) {
            return true;
        }
        return false;
    }

    public static boolean isLikelyAlipayPhone(String input) {
        return input.matches(ONLY_DIGITS_REGEX) && input.length() > 4;
    }

    public static boolean isAlipayVerificationRetryAllowed(long originalTimestamp) {
        return System.currentTimeMillis() - originalTimestamp > 60000;
    }
}
