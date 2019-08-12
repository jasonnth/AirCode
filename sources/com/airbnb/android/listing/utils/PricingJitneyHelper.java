package com.airbnb.android.listing.utils;

import com.airbnb.android.core.analytics.PricingJitneyLogger;
import com.airbnb.android.core.models.DynamicPricingControl;
import com.airbnb.android.core.models.Listing;
import com.airbnb.jitney.event.logging.LongTermPriceDiscountTypes.p139v1.C2376LongTermPriceDiscountTypes;

public class PricingJitneyHelper {
    private PricingJitneyHelper() {
    }

    public static void logDiscountsChange(PricingJitneyLogger pricingJitneyLogger, Listing oldListing, Listing newListing) {
        double oldWeeklyFactor = oldListing.getWeeklyPriceFactor().getFactorValue().doubleValue();
        double oldMonthlyFactor = oldListing.getMonthlyPriceFactor().getFactorValue().doubleValue();
        double newWeeklyFactor = newListing.getWeeklyPriceFactor().getFactorValue().doubleValue();
        double newMonthlyFactor = newListing.getMonthlyPriceFactor().getFactorValue().doubleValue();
        if (oldWeeklyFactor != newWeeklyFactor) {
            pricingJitneyLogger.changeLongTermDiscount(newWeeklyFactor, oldListing.getAutoWeeklyFactor(), oldWeeklyFactor, C2376LongTermPriceDiscountTypes.Weekly);
        }
        if (oldMonthlyFactor != newMonthlyFactor) {
            pricingJitneyLogger.changeLongTermDiscount(newMonthlyFactor, oldListing.getAutoMonthlyFactor(), oldMonthlyFactor, C2376LongTermPriceDiscountTypes.Monthly);
        }
    }

    public static void logSmartPricingChangesIfNeeded(PricingJitneyLogger pricingJitneyLogger, DynamicPricingControl smartPricingSettings, DynamicPricingControl oldSmartPricingSettings, Listing listing) {
        if (oldSmartPricingSettings.isEnabled() != smartPricingSettings.isEnabled()) {
            if (smartPricingSettings.isEnabled()) {
                pricingJitneyLogger.enableSmartPricing(listing.getListingCurrency(), smartPricingSettings);
            } else {
                pricingJitneyLogger.enableSmartPricing(listing.getListingCurrency(), oldSmartPricingSettings);
            }
        }
        if (smartPricingSettings.isEnabled() && oldSmartPricingSettings.getMaxPrice() != smartPricingSettings.getMaxPrice()) {
            pricingJitneyLogger.changeSmartPricingMaxPrice(listing.getListingCurrency(), (long) oldSmartPricingSettings.getMaxPrice(), (long) oldSmartPricingSettings.getSuggestedMaxPrice(), oldSmartPricingSettings.toSmartPricingSettingsContext());
        }
        if (smartPricingSettings.isEnabled() && oldSmartPricingSettings.getMinPrice() != smartPricingSettings.getMinPrice()) {
            pricingJitneyLogger.changeSmartPricingMinPrice(listing.getListingCurrency(), (long) oldSmartPricingSettings.getMinPrice(), (long) oldSmartPricingSettings.getSuggestedMinPrice(), oldSmartPricingSettings.toSmartPricingSettingsContext());
        }
    }

    public static void logBasePriceChangesIfNeeded(PricingJitneyLogger pricingJitneyLogger, Listing listing, int newPrice, String newCurrencyCode) {
        if (listing.getListingPrice() != newPrice) {
            pricingJitneyLogger.changeBasePrice(newCurrencyCode, (long) listing.getListingPrice(), (long) listing.getAutoPricingDaily(), (long) newPrice);
        }
    }
}
