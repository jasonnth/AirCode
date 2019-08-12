package com.airbnb.android.listing.utils;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import com.airbnb.android.core.enums.InstantBookingAllowedCategory;
import com.airbnb.android.core.erf.PricingFeatureToggles;
import com.airbnb.android.core.models.CalendarRule;
import com.airbnb.android.core.models.CheckInInformation;
import com.airbnb.android.core.models.DynamicPricingControl;
import com.airbnb.android.core.models.GuestControlType;
import com.airbnb.android.core.models.GuestControls;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.NestedListing;
import com.airbnb.android.core.models.SnoozeMode;
import com.airbnb.android.core.utils.DateHelper;
import com.airbnb.android.core.utils.PercentageUtils;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.listing.adapters.TripLengthSettingsHelper;
import com.airbnb.android.utils.CurrencyUtils;
import com.google.common.base.Joiner;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import dagger.Lazy;
import dagger.internal.DoubleCheck;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListingTextUtils {
    private static final Lazy<DecimalFormat> singleDecimalFormatter = DoubleCheck.lazy(ListingTextUtils$$Lambda$2.lambdaFactory$());

    static /* synthetic */ DecimalFormat lambda$static$0() {
        return new DecimalFormat("###,###,###.#");
    }

    public static String createCountWithMaxPlus(Context context, int count, int maxCountBeforePlus) {
        if (count != maxCountBeforePlus) {
            return Integer.toString(count);
        }
        return context.getString(C7213R.string.generic_count_or_greater, new Object[]{Integer.valueOf(count)});
    }

    public static String createCountWithMaxPlus(Context context, float count, float maxCountBeforePlus) {
        if (count != maxCountBeforePlus) {
            return formatWithMaxOneDecimal(count);
        }
        return context.getString(C7213R.string.generic_count_or_greater, new Object[]{formatWithMaxOneDecimal(count)});
    }

    public static String formatWithMaxOneDecimal(float number) {
        return ((DecimalFormat) singleDecimalFormatter.get()).format((double) number);
    }

    public static String createGuestsCount(Context context, int count) {
        if (count < 16) {
            return context.getResources().getQuantityString(C7213R.plurals.x_guests, count, new Object[]{Integer.valueOf(count)});
        }
        return context.getString(C7213R.string.x_plus_guests, new Object[]{Integer.valueOf(count), Integer.valueOf(count)});
    }

    public static int getActionTextForTextSetting(String text) {
        return TextUtils.isEmpty(text) ? C7213R.string.add : C7213R.string.edit;
    }

    public static int getSelfCheckinRowTitle(List<CheckInInformation> checkinTypes) {
        return ((CheckInInformation) FluentIterable.from((Iterable<E>) checkinTypes).firstMatch(ListingTextUtils$$Lambda$1.lambdaFactory$()).orNull()) != null ? C7213R.string.manage_listing_details_item_self_check_in_instructions : C7213R.string.manage_listing_details_item_add_self_check_in;
    }

    public static String createBathroomsCount(Context context, float count) {
        return context.getResources().getQuantityString(count == 8.0f ? C7213R.plurals.bathrooms_plus : C7213R.plurals.bathrooms, Math.round(count), new Object[]{formatWithMaxOneDecimal(count)});
    }

    public static String createCapacityCount(Context context, int count) {
        if (count < 16) {
            return context.getResources().getQuantityString(C7213R.plurals.x_guests, count, new Object[]{Integer.valueOf(count)});
        }
        return context.getString(C7213R.string.x_plus_guests, new Object[]{Integer.valueOf(count)});
    }

    public static String createBedCount(Context context, int count) {
        if (count < 16) {
            return context.getResources().getQuantityString(C7213R.plurals.beds, count, new Object[]{Integer.valueOf(count)});
        }
        return context.getString(C7213R.string.listing_beds_many_plus, new Object[]{Integer.valueOf(count)});
    }

    public static String createBedroomCount(Context context, int count) {
        if (count == 0) {
            return context.getString(C7213R.string.manage_listing_rooms_and_guests_bedroom_count_studio);
        }
        return context.getResources().getQuantityString(C7213R.plurals.bedrooms, count, new Object[]{Integer.valueOf(count)});
    }

    public static String createSelectedBedroomCount(Context context, int count) {
        if (count == 0) {
            return context.getString(C7213R.string.manage_listing_rooms_and_guests_bedroom_count_studio);
        }
        return Integer.toString(count);
    }

    public static String getGuestRequirementsReviewText(Context context, Listing listing) {
        ArrayList<String> requirementsDescription = new ArrayList<>();
        Resources res = context.getResources();
        requirementsDescription.add(res.getString(C7213R.string.lys_dls_base_requirements));
        InstantBookingAllowedCategory category = InstantBookingAllowedCategory.fromKey(listing.getInstantBookingAllowedCategory());
        if (category.isGovernmentIdNeeded()) {
            requirementsDescription.add(res.getString(C7213R.string.booking_requirements_government_id));
        }
        if (category.isHighRatingNeeded()) {
            requirementsDescription.add(res.getString(C7213R.string.lys_dls_additional_requirements_host_recommendation));
        }
        return Joiner.m1896on("\n").join((Iterable<?>) requirementsDescription);
    }

    public static String getFeesDescriptionText(Context context, Listing listing) {
        ArrayList<String> feeDescriptions = new ArrayList<>();
        String currency = getListingCurrency(listing);
        if (listing.getListingCleaningFeeNative() != null) {
            feeDescriptions.add(context.getResources().getString(C7213R.string.manage_listing_fees_cleaning, new Object[]{CurrencyUtils.formatCurrencyAmount((double) listing.getListingCleaningFeeNative().intValue(), currency)}));
        }
        if (listing.getListingWeekendPriceNative() > 0) {
            feeDescriptions.add(context.getResources().getString(C7213R.string.manage_listing_fees_weekend, new Object[]{CurrencyUtils.formatCurrencyAmount((double) listing.getListingWeekendPriceNative(), currency)}));
        }
        if (listing.getListingPriceForExtraPersonNative() > 0) {
            feeDescriptions.add(context.getResources().getString(C7213R.string.manage_listing_fees_extra_guests, new Object[]{context.getResources().getString(C7213R.string.manage_listing_fees_extra_guests_rules, new Object[]{CurrencyUtils.formatCurrencyAmount((double) listing.getListingPriceForExtraPersonNative(), currency), Integer.valueOf(listing.getGuestsIncluded())})}));
        }
        if (listing.getListingSecurityDepositNative() != null) {
            feeDescriptions.add(context.getResources().getString(C7213R.string.manage_listing_fees_security_deposit, new Object[]{CurrencyUtils.formatCurrencyAmount((double) listing.getListingSecurityDepositNative().intValue(), currency)}));
        }
        return Joiner.m1896on("・").join((Iterable<?>) feeDescriptions);
    }

    public static String getHouseRulesReviewText(Context context, Listing listing, GuestControls guestControls) {
        GuestControlType[] values;
        ArrayList<String> houseRulesDescriptions = new ArrayList<>();
        List<GuestControlType> disallowedRules = ImmutableList.m1284of();
        Resources res = context.getResources();
        if (guestControls != null) {
            disallowedRules = guestControls.getRules(false);
        }
        for (GuestControlType controlType : GuestControlType.values()) {
            if (disallowedRules.contains(controlType)) {
                houseRulesDescriptions.add(res.getString(controlType.getDisallowId()));
            } else {
                houseRulesDescriptions.add(res.getString(controlType.getLabelId()));
            }
        }
        String customHouseRules = listing.getHouseRules();
        if (!TextUtils.isEmpty(customHouseRules)) {
            houseRulesDescriptions.add(customHouseRules);
        }
        return Joiner.m1896on("\n").join((Iterable<?>) houseRulesDescriptions);
    }

    public static String getAvailabilityReviewText(Context context, Listing listing, CalendarRule calendarRule) {
        ArrayList<String> availabilityDescriptions = new ArrayList<>();
        String checkinCheckoutInfo = getCheckinCheckoutString(context, listing.getCheckInTime(), listing.getCheckOutTime());
        availabilityDescriptions.add(context.getString(C7213R.string.lys_dls_availability_advance_notice, new Object[]{calendarRule.getAdvanceNotice().getDays().getHowManyDays(context)}));
        availabilityDescriptions.add(context.getString(C7213R.string.lys_dls_availability_future_reservation, new Object[]{FutureReservationsDisplay.getShortValue(context, calendarRule.getMaxDaysNotice())}));
        if (!TextUtils.isEmpty(checkinCheckoutInfo)) {
            availabilityDescriptions.add(checkinCheckoutInfo);
        }
        availabilityDescriptions.add(getMinMaxNights(context, listing.getMinNights(), TripLengthSettingsHelper.getMaxNightsToShow(listing)));
        return Joiner.m1896on("\n").join((Iterable<?>) availabilityDescriptions);
    }

    private static String getCheckinCheckoutString(Context context, Integer checkin, Integer checkout) {
        if (checkin != null && checkout != null) {
            return context.getString(C7213R.string.lys_dls_availability_checkin_checkout, new Object[]{DateHelper.formatHourOfDay(context, checkin.intValue(), false), DateHelper.formatHourOfDay(context, checkout.intValue(), false)});
        } else if (checkin == null && checkout != null) {
            return context.getString(C7213R.string.lys_dls_availability_checkout, new Object[]{DateHelper.formatHourOfDay(context, checkout.intValue(), false)});
        } else if (checkin == null || checkout != null) {
            return "";
        } else {
            return context.getString(C7213R.string.lys_dls_availability_checkin, new Object[]{DateHelper.formatHourOfDay(context, checkin.intValue(), false)});
        }
    }

    private static String getMinMaxNights(Context context, int minNights, int maxNights) {
        if (maxNights == 0) {
            return context.getString(C7213R.string.lys_dls_availability_min_nights, new Object[]{Integer.valueOf(minNights)});
        }
        return context.getString(C7213R.string.lys_dls_availability_min_max_nights, new Object[]{Integer.valueOf(minNights), Integer.valueOf(maxNights)});
    }

    public static String getDLengthOfStayDiscountDescriptionText(Context context, Listing listing) {
        ArrayList<String> discountDescriptions = new ArrayList<>();
        if (listing.getWeeklyPriceFactor().hasDiscount()) {
            discountDescriptions.add(context.getResources().getString(C7213R.string.manage_listing_discounts_weekly_discount, new Object[]{PercentageUtils.localizePercentage(listing.getWeeklyPriceFactor().getDiscountPercentage())}));
        }
        if (listing.getMonthlyPriceFactor().hasDiscount()) {
            discountDescriptions.add(context.getResources().getString(C7213R.string.manage_listing_discounts_monthly_discount, new Object[]{PercentageUtils.localizePercentage(listing.getMonthlyPriceFactor().getDiscountPercentage())}));
        }
        return Joiner.m1896on("\n").join((Iterable<?>) discountDescriptions);
    }

    public static String getLastMinuteDiscountDescriptionText(Context context, Listing listing) {
        return "";
    }

    public static String getEarlyBirdDiscountDescriptionText(Context context, Listing listing) {
        return "";
    }

    public static String getAdditionalPricingReviewText(Context context, Listing listing) {
        ArrayList<String> additionalPricingDescriptions = new ArrayList<>();
        String listingCurrency = getListingCurrency(listing);
        Resources res = context.getResources();
        if (listing.getWeeklyPriceFactor() != null) {
            additionalPricingDescriptions.add(res.getString(C7213R.string.lys_dls_weekly_discount, new Object[]{PercentageUtils.localizePercentage(listing.getWeeklyPriceFactor().getDiscountPercentage())}));
        }
        if (listing.getMonthlyPriceFactor() != null) {
            additionalPricingDescriptions.add(res.getString(C7213R.string.lys_dls_monthly_discount, new Object[]{PercentageUtils.localizePercentage(listing.getMonthlyPriceFactor().getDiscountPercentage())}));
        }
        return Joiner.m1896on("\n").join((Iterable<?>) additionalPricingDescriptions);
    }

    public static String getSmartPricingSummaryText(Context context, Listing listing) {
        ArrayList<String> pricingDescription = new ArrayList<>();
        DynamicPricingControl pricingControl = listing.getDynamicPricingControls();
        String nativeCurrency = getListingCurrency(listing);
        boolean isSmartPricingAvailable = PricingFeatureToggles.showSmartPricing(listing);
        Resources res = context.getResources();
        if (!isSmartPricingAvailable || !listing.getDynamicPricingControls().isEnabled()) {
            if (isSmartPricingAvailable) {
                pricingDescription.add(res.getString(C7213R.string.manage_listings_smart_pricing_disabled));
            }
            pricingDescription.add(res.getString(C7213R.string.manage_listing_base_price, new Object[]{CurrencyUtils.formatCurrencyAmount((double) listing.getListingPriceNative(), nativeCurrency)}));
        } else {
            String smartPricingEnabledText = res.getString(C7213R.string.manage_listings_smart_pricing_enabled);
            String smartPricingMinMaxRangeText = res.getString(C7213R.string.manage_listing_smart_pricing_range, new Object[]{CurrencyUtils.formatCurrencyAmount((double) pricingControl.getMinPrice(), nativeCurrency), CurrencyUtils.formatCurrencyAmount((double) pricingControl.getMaxPrice(), nativeCurrency)});
            pricingDescription.add(smartPricingEnabledText);
            pricingDescription.add(smartPricingMinMaxRangeText);
        }
        return Joiner.m1896on("\n").join((Iterable<?>) pricingDescription);
    }

    public static String getSmartPricingReviewText(Context context, Listing listing) {
        ArrayList<String> smartPricingDescriptions = new ArrayList<>();
        DynamicPricingControl pricingSettings = listing.getDynamicPricingControls();
        Resources res = context.getResources();
        String currency = getListingCurrency(listing);
        int basePrice = listing.getListingPrice();
        if (pricingSettings == null || !pricingSettings.isEnabled()) {
            smartPricingDescriptions.add(res.getString(C7213R.string.manage_listing_price_per_night, new Object[]{CurrencyUtils.formatCurrencyAmount((double) basePrice, currency)}));
        } else {
            smartPricingDescriptions.add(res.getString(C7213R.string.lys_dls_listing_price_min_max, new Object[]{CurrencyUtils.formatCurrencyAmount((double) pricingSettings.getMinPrice(), currency), CurrencyUtils.formatCurrencyAmount((double) pricingSettings.getMaxPrice(), currency)}));
            smartPricingDescriptions.add(res.getString(C7213R.string.lys_dls_listing_base_price, new Object[]{CurrencyUtils.formatCurrencyAmount((double) basePrice, currency)}));
        }
        return Joiner.m1896on("\n").join((Iterable<?>) smartPricingDescriptions);
    }

    public static String getAvailabilityDescriptionText(Context context, CalendarRule calendarRule) {
        ArrayList<String> settings = new ArrayList<>();
        settings.add(getSingleSettingString(context, AdvanceNoticeDisplay.getDaysTitle(context), AdvanceNoticeDisplay.getDaysShortValue(context, calendarRule.getAdvanceNotice())));
        settings.add(getSingleSettingString(context, PreparationTimeDisplay.getTitle(context), PreparationTimeDisplay.getShortValue(context, calendarRule.getTurnoverDays())));
        settings.add(getSingleSettingString(context, FutureReservationsDisplay.getTitle(context), FutureReservationsDisplay.getShortValue(context, calendarRule.getMaxDaysNotice())));
        return Joiner.m1896on("\n").join((Iterable<?>) settings);
    }

    public static String getNestedListingSubtitle(Context context, NestedListing targetListing, HashMap<Long, NestedListing> nestedListingsById) {
        if (nestedListingsById != null && nestedListingsById.keySet().size() <= 1) {
            return context.getString(C7213R.string.manage_listing_booking_item_nested_listing_cannot_link);
        }
        if (targetListing == null) {
            return "";
        }
        int numLinkedCalendars = targetListing.getNumberOfChildren();
        if (targetListing.hasParent()) {
            numLinkedCalendars++;
        }
        if (numLinkedCalendars == 0) {
            return context.getString(C7213R.string.manage_listing_booking_item_nested_listing_num_linked_none);
        }
        return context.getResources().getQuantityString(C7213R.plurals.nested_listing_num_other_linked, numLinkedCalendars, new Object[]{Integer.valueOf(numLinkedCalendars)});
    }

    public static String getListingCurrency(Listing listing) {
        String currency = listing.getListingNativeCurrency();
        if (currency == null) {
            currency = listing.getListingCurrency();
        }
        if (currency == null) {
            return CurrencyUtils.getLocalDefaultCurrency().getCurrencyCode();
        }
        return currency;
    }

    private static String getSingleSettingString(Context context, String settingTitle, String settingValue) {
        return context.getString(C7213R.string.manage_listing_availability_settings_info_format, new Object[]{settingTitle, settingValue});
    }

    public static String getSnoozeSummaryString(Context context, SnoozeMode snoozeMode) {
        if (snoozeMode == null) {
            return "";
        }
        String dateRequestStr = context.getResources().getString(C7213R.string.mdy_format_full);
        return context.getResources().getString(C7213R.string.manage_listing_status_setting_snoozed_summary, new Object[]{snoozeMode.getStartDate().formatDate(dateRequestStr), snoozeMode.getEndDate().formatDate(dateRequestStr)});
    }

    public static int getCheckInGuideLabelRes(Listing listing) {
        switch (listing.getCheckInGuideStatusEnum()) {
            case NotCreated:
                return C7213R.string.manage_listing_details_item_check_in_instructions_label;
            default:
                return 0;
        }
    }

    public static int getTipRes() {
        return PricingFeatureToggles.showUseTipCopy() ? C7213R.string.use_tip : C7213R.string.price_tip;
    }

    public static CharSequence createPricingDisclaimer(Context context, boolean excludeSmartPricing) {
        SpannableParagraphBuilder builder = new SpannableParagraphBuilder(context);
        builder.appendWithoutTitle(C7213R.string.manage_listing_pricing_disclaimer_short);
        if (!excludeSmartPricing) {
            builder.append(C7213R.string.smart_pricing_title, C7213R.string.manage_listing_pricing_disclaimer_smart_pricing_info);
        }
        builder.append(C7213R.string.manage_listing_pricing_disclaimer_price_tips_heading, C7213R.string.manage_listing_pricing_disclaimer_price_tips_info);
        return builder.build();
    }
}
