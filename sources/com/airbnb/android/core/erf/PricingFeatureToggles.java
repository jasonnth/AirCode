package com.airbnb.android.core.erf;

import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.erf.Experiments;
import java.util.regex.Pattern;

public class PricingFeatureToggles {
    public static boolean isInPricingHoldout() {
        return !Experiments.showPricingFeatures();
    }

    public static boolean showSmartPricing(Listing listing) {
        return !isInPricingHoldout() && listing.isSmartPricingAvailable();
    }

    public static boolean showDLSInsights() {
        CoreApplication.instance().component().debugSettings();
        if (DebugSettings.DLS_INSIGHTS.isEnabled()) {
            return true;
        }
        if (isInPricingHoldout() || !Experiments.showDLSInsights()) {
            return false;
        }
        return true;
    }

    public static boolean matchesMLCalendarRegex(String text) {
        return Pattern.compile(".+/manage-listing/[0-9]+/calendar(\\?.+)?").matcher(text).matches();
    }

    public static boolean useDLDPricingScreens(String uri) {
        if (matchesMLCalendarRegex(uri) && !isInPricingHoldout() && Experiments.useDLDForPricing()) {
            return true;
        }
        return false;
    }

    public static boolean showInstallmentsRow() {
        return Experiments.showInstallmentsBelow() || Experiments.showInstallmentsAbove();
    }

    public static boolean showNewHostPromoLYS() {
        return Trebuchet.launch(TrebuchetKeys.SHOW_LYS_NEW_HOST_PROMO) && Experiments.showLYSNewHostDiscount();
    }

    public static boolean showNewInsightCards() {
        return Trebuchet.launch(TrebuchetKeys.SHOW_NEW_INSIGHTS) && !isInPricingHoldout() && Experiments.showNewInsights();
    }

    public static boolean showUseTipCopy() {
        return !isInPricingHoldout() && Experiments.showUseTipCopy();
    }

    public static boolean showMultiDayPriceTips() {
        return !isInPricingHoldout();
    }

    public static boolean showHostSmartPromo() {
        return Trebuchet.launch(TrebuchetKeys.SHOW_HOST_SMART_PROMO);
    }
}
