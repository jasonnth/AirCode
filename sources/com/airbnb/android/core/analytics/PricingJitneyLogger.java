package com.airbnb.android.core.analytics;

import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.models.CalendarDay;
import com.airbnb.android.core.models.CalendarDayPriceInfo;
import com.airbnb.android.core.models.DynamicPricingControl;
import com.airbnb.jitney.event.logging.DsNightAvailabilityStatus.p086v1.C1987DsNightAvailabilityStatus;
import com.airbnb.jitney.event.logging.LongTermPriceDiscountTypes.p139v1.C2376LongTermPriceDiscountTypes;
import com.airbnb.jitney.event.logging.PriceSuggestionContext.p205v1.C2569PriceSuggestionContext;
import com.airbnb.jitney.event.logging.PriceTipDaysType.p206v1.C2571PriceTipDaysType;
import com.airbnb.jitney.event.logging.Pricing.p207v1.PricingBasePriceChangeEvent;
import com.airbnb.jitney.event.logging.Pricing.p207v1.PricingBasePriceTipAdoptionEvent.Builder;
import com.airbnb.jitney.event.logging.Pricing.p207v1.PricingCalendarDailyPriceChangeEvent;
import com.airbnb.jitney.event.logging.Pricing.p207v1.PricingLongTermDiscountSettingChangeEvent;
import com.airbnb.jitney.event.logging.Pricing.p207v1.PricingLongTermDiscountSettingTipAdoptionEvent;
import com.airbnb.jitney.event.logging.Pricing.p207v1.PricingPriceTipExplicitAdoptionEvent;
import com.airbnb.jitney.event.logging.Pricing.p207v1.PricingPriceTipViewEvent;
import com.airbnb.jitney.event.logging.Pricing.p207v1.PricingSmartPricingEnabledSettingChangeEvent;
import com.airbnb.jitney.event.logging.Pricing.p207v1.PricingSmartPricingMaxPriceChangeEvent;
import com.airbnb.jitney.event.logging.Pricing.p207v1.PricingSmartPricingMaxPriceTipExplicitAdoptionEvent;
import com.airbnb.jitney.event.logging.Pricing.p207v1.PricingSmartPricingMinPriceChangeEvent;
import com.airbnb.jitney.event.logging.Pricing.p207v1.PricingSmartPricingMinPriceTipExplicitAdoptionEvent;
import com.airbnb.jitney.event.logging.PricingSettingsPageType.p208v1.C2585PricingSettingsPageType;
import com.airbnb.jitney.event.logging.PricingSettingsSectionType.p209v1.C2586PricingSettingsSectionType;
import com.airbnb.jitney.event.logging.SinglePriceChangeContext.p258v1.C2742SinglePriceChangeContext;
import com.airbnb.jitney.event.logging.SmartPricingSettingsContext.p259v1.C2744SmartPricingSettingsContext;
import com.airbnb.jitney.event.logging.SuggestedPriceBucketLevel.p261v1.C2747SuggestedPriceBucketLevel;
import java.util.ArrayList;
import java.util.List;

public class PricingJitneyLogger extends BaseLogger {
    private final long listingId;
    private final C2585PricingSettingsPageType page;
    private final C2586PricingSettingsSectionType section;

    public PricingJitneyLogger(LoggingContextFactory loggingContextFactory, C2585PricingSettingsPageType page2, C2586PricingSettingsSectionType section2, long listingId2) {
        super(loggingContextFactory);
        this.page = page2;
        this.section = section2;
        this.listingId = listingId2;
    }

    public void adoptBasePrice(String currency, long oldBasePrice, long suggestedPrice) {
        publish(new Builder(context(), this.page, this.section, Long.valueOf(this.listingId), currency, Long.valueOf(oldBasePrice), Long.valueOf(suggestedPrice)));
    }

    public void viewCalendarPriceTip(long listingId2, List<C2569PriceSuggestionContext> priceSuggestions, C2571PriceTipDaysType daysType) {
        publish(new PricingPriceTipViewEvent.Builder(context(), this.page, this.section, Long.valueOf(listingId2), priceSuggestions, daysType));
    }

    public void adoptCalendarPriceTip(long listingId2, List<C2569PriceSuggestionContext> priceSuggestions, C2571PriceTipDaysType daysType) {
        publish(new PricingPriceTipExplicitAdoptionEvent.Builder(context(), this.page, this.section, Long.valueOf(listingId2), priceSuggestions, daysType));
    }

    public static C2747SuggestedPriceBucketLevel getBucket(CalendarDayPriceInfo priceInfo) {
        int dailyPrice = priceInfo.getNativePrice();
        List<Integer> suggestedPriceLevels = priceInfo.getNativeSuggestedPriceLevels();
        if (suggestedPriceLevels == null) {
            BugsnagWrapper.notify(new Throwable("Suggested price levels list size is null " + priceInfo));
            return null;
        } else if (dailyPrice < ((Integer) suggestedPriceLevels.get(0)).intValue()) {
            return C2747SuggestedPriceBucketLevel.Low;
        } else {
            if (dailyPrice <= ((Integer) suggestedPriceLevels.get(1)).intValue()) {
                return C2747SuggestedPriceBucketLevel.Suggested;
            }
            if (dailyPrice < ((Integer) suggestedPriceLevels.get(2)).intValue()) {
                return C2747SuggestedPriceBucketLevel.Medium;
            }
            return C2747SuggestedPriceBucketLevel.High;
        }
    }

    private static C1987DsNightAvailabilityStatus getJitneyAvailability(CalendarDay day) {
        if (day.isAvailable()) {
            return C1987DsNightAvailabilityStatus.Available;
        }
        if (day.isReserved()) {
            return C1987DsNightAvailabilityStatus.Booked;
        }
        return C1987DsNightAvailabilityStatus.Blocked;
    }

    public static List<C2569PriceSuggestionContext> getPriceSuggestionContextList(List<CalendarDay> selectedDays) {
        List<C2569PriceSuggestionContext> suggestionContext = new ArrayList<>();
        for (CalendarDay day : selectedDays) {
            CalendarDayPriceInfo priceInfo = day.getPriceInfo();
            C2747SuggestedPriceBucketLevel bucketLevel = getBucket(day.getPriceInfo());
            if (bucketLevel != null) {
                suggestionContext.add(new C2569PriceSuggestionContext.Builder(day.getDate().getIsoDateString(), getJitneyAvailability(day), Long.valueOf((long) priceInfo.getNativePrice()), Long.valueOf((long) priceInfo.getRecommendedSmartPrice()), bucketLevel).build());
            }
        }
        return suggestionContext;
    }

    public void changeBasePrice(String currency, long oldBasePrice, long suggestedPrice, long basePrice) {
        publish(new PricingBasePriceChangeEvent.Builder(context(), this.page, this.section, Long.valueOf(this.listingId), currency, Long.valueOf(oldBasePrice), Long.valueOf(suggestedPrice), Long.valueOf(basePrice)));
    }

    public void enableSmartPricing(String currency, DynamicPricingControl smartPricingControls) {
        publish(new PricingSmartPricingEnabledSettingChangeEvent.Builder(context(), this.page, this.section, Long.valueOf(this.listingId), currency, Long.valueOf((long) smartPricingControls.getMinPrice()), Long.valueOf((long) smartPricingControls.getMaxPrice()), Boolean.valueOf(smartPricingControls.isEnabled()), Long.valueOf((long) smartPricingControls.getDesiredHostingFrequencyKey().intValue())));
    }

    public void changeSmartPricingMaxPrice(String currency, long oldMaxPrice, long suggestedMaxPrice, C2744SmartPricingSettingsContext smartPricingSettings) {
        publish(new PricingSmartPricingMaxPriceChangeEvent.Builder(context(), this.page, this.section, Long.valueOf(this.listingId), currency, Long.valueOf(oldMaxPrice), Long.valueOf(suggestedMaxPrice), smartPricingSettings));
    }

    public void changeSmartPricingMinPrice(String currency, long oldMinPrice, long suggestedMinPrice, C2744SmartPricingSettingsContext smartPricingSettings) {
        publish(new PricingSmartPricingMinPriceChangeEvent.Builder(context(), this.page, this.section, Long.valueOf(this.listingId), currency, Long.valueOf(oldMinPrice), Long.valueOf(suggestedMinPrice), smartPricingSettings));
    }

    public void adoptSmartPricingMinPriceTip(String currency, long oldMinPrice, long suggestedMinPrice, C2744SmartPricingSettingsContext smartPricingSettings) {
        publish(new PricingSmartPricingMinPriceTipExplicitAdoptionEvent.Builder(context(), this.page, this.section, Long.valueOf(this.listingId), currency, Long.valueOf(oldMinPrice), Long.valueOf(suggestedMinPrice), smartPricingSettings));
    }

    public void adoptSmartPricingMaxPriceTip(String currency, long oldMaxPrice, long suggestedMaxPrice, C2744SmartPricingSettingsContext smartPricingSettings) {
        publish(new PricingSmartPricingMaxPriceTipExplicitAdoptionEvent.Builder(context(), this.page, this.section, Long.valueOf(this.listingId), currency, Long.valueOf(oldMaxPrice), Long.valueOf(suggestedMaxPrice), smartPricingSettings));
    }

    public void adoptLongTermDiscountTip(double longTermPricingDiscountFactor, double suggestedLongTermPricingDiscountFactor, double oldLongTermPricingDiscountFactor, C2376LongTermPriceDiscountTypes longTermPriceDiscountLengthType) {
        publish(new PricingLongTermDiscountSettingTipAdoptionEvent.Builder(context(), this.page, this.section, Long.valueOf(this.listingId), Double.valueOf(longTermPricingDiscountFactor), Double.valueOf(suggestedLongTermPricingDiscountFactor), Double.valueOf(oldLongTermPricingDiscountFactor), longTermPriceDiscountLengthType));
    }

    public void changeLongTermDiscount(double longTermPricingDiscountFactor, double suggestedLongTermPricingDiscountFactor, double oldLongTermPricingDiscountFactor, C2376LongTermPriceDiscountTypes longTermPriceDiscountLengthType) {
        publish(new PricingLongTermDiscountSettingChangeEvent.Builder(context(), this.page, this.section, Long.valueOf(this.listingId), Double.valueOf(longTermPricingDiscountFactor), Double.valueOf(suggestedLongTermPricingDiscountFactor), Double.valueOf(oldLongTermPricingDiscountFactor), longTermPriceDiscountLengthType));
    }

    public void changeCalendarDailyPrice(String currency, List<C2742SinglePriceChangeContext> priceChangesContext, boolean isSmartPricingEnabledListing) {
        publish(new PricingCalendarDailyPriceChangeEvent.Builder(context(), this.page, this.section, Long.valueOf(this.listingId), currency, priceChangesContext, Boolean.valueOf(isSmartPricingEnabledListing)));
    }
}
