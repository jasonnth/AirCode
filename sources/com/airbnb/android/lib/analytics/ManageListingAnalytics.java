package com.airbnb.android.lib.analytics;

import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.enums.InstantBookVisibility;
import com.airbnb.android.core.models.DynamicPricingControl.DesiredHostingFrequency;
import com.airbnb.android.utils.Strap;

public class ManageListingAnalytics extends BaseAnalytics {
    public static final String ACTION = "action";
    public static final String DBP_HOSTING_FREQUENCY = "hosting_frequency";
    public static final String DBP_MAX_PRICE = "max_price";
    public static final String DBP_MIN_PRICE = "min_price";
    public static final String DEMAND_BASED_PRICING = "demand_based_pricing";
    public static final String EDIT_LISTING = "edit_listing";
    public static final String ENABLED = "enabled";
    public static final String FLOW = "flow";
    public static final String IB_ADVANCE_NOTICE_CHANGE = "instant_book_lead_time_change";
    public static final String IB_ADVANCE_NOTICE_FOCUS = "instant_book_lead_time_focus";
    public static final String IB_ADVANCE_NOTICE_SAVE = "instant_book_lead_time_save";
    public static final String IB_CHECKBOX_CHANGE = "instant_book_checkbox_change";
    public static final String IB_CHECKBOX_SAVE = "instant_book_checkbox_save";
    public static final String IB_FIRST_MESSAGE_FOCUS = "instant_book_first_message_focus";
    public static final String IB_FTUE_IMMERSIVE = "ftue_immersive";
    public static final String IB_FTUE_PRESS_TRY_IB = "click_try_instant_book";
    public static final String IB_FTUE_PRESS_UP = "click_up";
    public static final String IB_HOUSE_RULES_FOCUS = "instant_book_house_rules_focus";
    public static final String IB_VISIBILITY_CHANGE = "instant_book_visibility__change";
    public static final String IB_VISIBILITY_FOCUS = "instant_book_visibility_focus";
    public static final String IB_VISIBILITY_SAVE = "instant_book_visibility_save";
    public static final String IMPRESSIONS = "impressions";
    public static final String INSTANT_BOOK = "instant_book";
    public static final String INSTANT_BOOK_FTUE = "instant_book_ftue";
    public static final String LISTING_ID = "listing_id";
    public static final String LT_ACCEPT_CHANGES = "tap_accept_proposed_price_factors";
    public static final String LT_DO_LATER = "tap_do_this_later";
    public static final String LT_EDIT_PRICES = "tap_set_new_discounts";
    public static final String LT_MONTHLY_FACTOR = "monthly_price_factor";
    public static final String LT_MONTHLY_SET = "change_monthly_discount";
    public static final String LT_PERCENT_BASED = "percent_based_long_term_pricing";
    public static final String LT_PRICE_FACTOR = "price_factor";
    public static final String LT_TOGGLED_MONTHLY = "monthly_discount_toggled";
    public static final String LT_TOGGLED_WEEKLY = "weekly_discount_toggled";
    public static final String LT_WEEKLY_FACTOR = "weekly_price_factor";
    public static final String LT_WEEKLY_SET = "change_weekly_discount";
    public static final String MANAGE_LISTING = "manage_listing_v2";
    public static final String POST_LIST = "ml_post_list";
    public static final String PRE_LIST = "ml_pre_list";
    public static final String SOFT_SUSPENSION = "host_standards_friendly_suspension";
    public static final String VIEW = "view";

    public static void trackHostStandardsImpressions() {
        AirbnbEventLogger.track(SOFT_SUSPENSION, new Strap().mo11639kv("page", "listings_dialog").mo11639kv(BaseAnalytics.OPERATION, "impressions"));
    }

    public static void trackInstantBookFtueImpressions() {
        AirbnbEventLogger.track(MANAGE_LISTING, new Strap().mo11639kv("c1", MANAGE_LISTING).mo11639kv("c2", "instant_book_ftue").mo11639kv("c3", "impressions").mo11639kv("c5", IB_FTUE_IMMERSIVE));
    }

    public static void trackInstantBookFtuePressTry() {
        AirbnbEventLogger.track(MANAGE_LISTING, new Strap().mo11639kv("c1", MANAGE_LISTING).mo11639kv("c2", "instant_book_ftue").mo11639kv("c3", IB_FTUE_PRESS_TRY_IB).mo11639kv("c5", IB_FTUE_IMMERSIVE));
    }

    public static void trackInstantBookFtuePressUp() {
        AirbnbEventLogger.track(MANAGE_LISTING, new Strap().mo11639kv("c1", MANAGE_LISTING).mo11639kv("c2", "instant_book_ftue").mo11639kv("c3", IB_FTUE_PRESS_UP).mo11639kv("c5", IB_FTUE_IMMERSIVE));
    }

    public static void trackInstantBookVisibilityFocus() {
        AirbnbEventLogger.track(MANAGE_LISTING, new Strap().mo11639kv("c1", MANAGE_LISTING).mo11639kv("c2", INSTANT_BOOK).mo11639kv("c3", INSTANT_BOOK).mo11639kv("c4", IB_VISIBILITY_FOCUS));
    }

    public static void trackInstantBookVisibilityChanged(InstantBookVisibility visibility) {
        AirbnbEventLogger.track(MANAGE_LISTING, new Strap().mo11639kv("c1", MANAGE_LISTING).mo11639kv("c2", INSTANT_BOOK).mo11639kv("c3", INSTANT_BOOK).mo11639kv("c4", IB_VISIBILITY_CHANGE).mo11639kv("c5", visibility.serverDescKey));
    }

    public static void trackInstantBookVisibilitySaved(InstantBookVisibility visibility) {
        AirbnbEventLogger.track(MANAGE_LISTING, new Strap().mo11639kv("c1", MANAGE_LISTING).mo11639kv("c2", INSTANT_BOOK).mo11639kv("c3", INSTANT_BOOK).mo11639kv("c4", IB_VISIBILITY_SAVE).mo11639kv("c5", visibility.serverDescKey));
    }

    public static void trackInstantBookAdvanceNoticeFocus() {
        AirbnbEventLogger.track(MANAGE_LISTING, new Strap().mo11639kv("c1", MANAGE_LISTING).mo11639kv("c2", INSTANT_BOOK).mo11639kv("c3", INSTANT_BOOK).mo11639kv("c4", IB_ADVANCE_NOTICE_FOCUS));
    }

    public static void trackInstantBookAdvanceNoticeChanged(int leadTimeHours) {
        AirbnbEventLogger.track(MANAGE_LISTING, new Strap().mo11639kv("c1", MANAGE_LISTING).mo11639kv("c2", INSTANT_BOOK).mo11639kv("c3", INSTANT_BOOK).mo11639kv("c4", IB_ADVANCE_NOTICE_CHANGE).mo11637kv("c5", leadTimeHours));
    }

    public static void trackInstantBookAdvanceNoticeSaved(int leadTimeHours) {
        AirbnbEventLogger.track(MANAGE_LISTING, new Strap().mo11639kv("c1", MANAGE_LISTING).mo11639kv("c2", INSTANT_BOOK).mo11639kv("c3", INSTANT_BOOK).mo11639kv("c4", IB_ADVANCE_NOTICE_SAVE).mo11637kv("c5", leadTimeHours));
    }

    public static void trackInstantBookFirstMessageFocus() {
        AirbnbEventLogger.track(MANAGE_LISTING, new Strap().mo11639kv("page", MANAGE_LISTING).mo11639kv(BaseAnalytics.SECTION, INSTANT_BOOK).mo11639kv("action", IB_FIRST_MESSAGE_FOCUS));
    }

    public static void trackInstantBookHouseRulesFocus() {
        AirbnbEventLogger.track(MANAGE_LISTING, new Strap().mo11639kv("page", MANAGE_LISTING).mo11639kv(BaseAnalytics.SECTION, INSTANT_BOOK).mo11639kv("action", IB_HOUSE_RULES_FOCUS));
    }

    public static void trackLongTermImpression(long listingId, boolean hasBeenListed) {
        AirbnbEventLogger.track(MANAGE_LISTING, longTermStrapBuilder("impressions", listingId, hasBeenListed));
    }

    public static void trackLongTermToggled(String toggledAction, boolean isOn, long listingId, boolean hasBeenListed) {
        AirbnbEventLogger.track(MANAGE_LISTING, longTermStrapBuilder(toggledAction, listingId, hasBeenListed, isOn));
    }

    public static void trackLongTermDiscountSet(String discountAction, double priceFactor, long listingId, boolean hasBeenListed) {
        AirbnbEventLogger.track(MANAGE_LISTING, longTermStrapBuilder(discountAction, listingId, hasBeenListed, priceFactor));
    }

    public static void trackLongTermDoLaterClicked(long listingId) {
        AirbnbEventLogger.track(MANAGE_LISTING, longTermStrapBuilder(LT_DO_LATER, listingId));
    }

    public static void trackLongTermEditPrices(long listingId) {
        AirbnbEventLogger.track(MANAGE_LISTING, longTermStrapBuilder(LT_EDIT_PRICES, listingId));
    }

    public static void trackLongTermAcceptChanges(long listingId, double weeklyFactor, double monthlyFactor) {
        AirbnbEventLogger.track(MANAGE_LISTING, longTermStrapBuilder(LT_ACCEPT_CHANGES, listingId).mo11635kv("weekly_price_factor", weeklyFactor).mo11635kv("monthly_price_factor", monthlyFactor));
    }

    private static Strap longTermStrapBuilder(String action, long listingId) {
        return Strap.make().mo11639kv("view", LT_PERCENT_BASED).mo11639kv("action", action).mo11638kv("listing_id", listingId);
    }

    private static Strap longTermStrapBuilder(String action, long listingId, boolean hasBeenListed) {
        return longTermStrapBuilder(action, listingId).mo11639kv(FLOW, hasBeenListed ? POST_LIST : PRE_LIST);
    }

    private static Strap longTermStrapBuilder(String action, long listingId, boolean hasBeenListed, double priceFactor) {
        return longTermStrapBuilder(action, listingId, hasBeenListed).mo11635kv(LT_PRICE_FACTOR, priceFactor);
    }

    private static Strap longTermStrapBuilder(String action, long listingId, boolean hasBeenListed, boolean isOn) {
        return longTermStrapBuilder(action, listingId, hasBeenListed).mo11640kv("enabled", isOn);
    }

    public static void trackChangeDemandBasedMinPrice(long listingId, int minPrice) {
        AirbnbEventLogger.track(MANAGE_LISTING, Strap.make().mo11639kv("page", "edit_listing").mo11639kv(BaseAnalytics.SECTION, DEMAND_BASED_PRICING).mo11639kv(BaseAnalytics.OPERATION, BaseAnalytics.UPDATE).mo11639kv(BaseAnalytics.TARGET, DBP_MIN_PRICE).mo11638kv("listing_id", listingId).mo11637kv(DBP_MIN_PRICE, minPrice));
    }

    public static void trackChangeDemandBasedMaxPrice(long listingId, int maxPrice) {
        AirbnbEventLogger.track(MANAGE_LISTING, Strap.make().mo11639kv("page", "edit_listing").mo11639kv(BaseAnalytics.SECTION, DEMAND_BASED_PRICING).mo11639kv(BaseAnalytics.OPERATION, BaseAnalytics.UPDATE).mo11639kv(BaseAnalytics.TARGET, DBP_MAX_PRICE).mo11638kv("listing_id", listingId).mo11637kv(DBP_MAX_PRICE, maxPrice));
    }

    public static void trackToggleDemandBasedEnabled(long listingId, boolean enabled) {
        AirbnbEventLogger.track(MANAGE_LISTING, Strap.make().mo11639kv("page", "edit_listing").mo11639kv(BaseAnalytics.SECTION, DEMAND_BASED_PRICING).mo11639kv(BaseAnalytics.OPERATION, BaseAnalytics.UPDATE).mo11639kv(BaseAnalytics.TARGET, "enable_demand_based_pricing").mo11638kv("listing_id", listingId).mo11640kv("is_enabled", enabled));
    }

    public static void trackChangeDemandBasedHostingFrequency(long listingId, DesiredHostingFrequency frequency) {
        AirbnbEventLogger.track(MANAGE_LISTING, Strap.make().mo11639kv("page", "edit_listing").mo11639kv(BaseAnalytics.SECTION, DEMAND_BASED_PRICING).mo11639kv(BaseAnalytics.OPERATION, BaseAnalytics.UPDATE).mo11639kv(BaseAnalytics.TARGET, DBP_HOSTING_FREQUENCY).mo11638kv("listing_id", listingId).mo11637kv(DBP_HOSTING_FREQUENCY, frequency.getServerKey()));
    }
}
