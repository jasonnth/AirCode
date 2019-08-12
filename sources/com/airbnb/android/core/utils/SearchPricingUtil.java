package com.airbnb.android.core.utils;

import com.airbnb.android.core.CoreApplication;

public final class SearchPricingUtil {
    private SearchPricingUtil() {
    }

    public static boolean isIncludingServiceFeeRequired() {
        return AppLaunchUtils.isUserInGermany();
    }

    public static boolean isTotalPricingRequired() {
        return AppLaunchUtils.isUserInAustralia();
    }

    public static boolean isTotalPricingEnabled() {
        if (isTotalPricingRequired()) {
            return true;
        }
        return new SharedPrefsHelper(CoreApplication.instance().component().airbnbPreferences()).isShowTotalPriceEnabled();
    }
}
