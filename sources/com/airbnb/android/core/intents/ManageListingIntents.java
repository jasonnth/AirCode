package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.utils.SettingDeepLink;

public class ManageListingIntents {
    public static final String INTENT_EXTRA_LISTING_ID = "listing_id";
    public static final String INTENT_EXTRA_MANAGED_LISTING = "managed_listing";
    public static final String INTENT_EXTRA_SESSION_ID = "lys_session_id";
    public static final String INTENT_EXTRA_SETTING_DEEP_LINK = "setting_deep_link";
    public static final String INTENT_EXTRA_SETTING_DEEP_LINK_EARLY_RETURN = "setting_early_return";
    public static final String INTENT_EXTRA_SETTING_DEEP_LINK_SHOW_FULL_LOADING_ONLY = "setting_show_full_loading_only";
    private static final String TAG = "ManageListingIntents";

    public static class DeepLinks {
        public static Intent deepLinkIntentWithoutListingId(Context context) {
            return ManageListingIntents.intentForFallback(context);
        }

        public static Intent deepLinkIntentForExistingListingPricingSetting(Context context, Bundle extras) {
            long listingId = ManageListingIntents.getId(extras, "id");
            return ManageListingIntents.isValidId(listingId) ? ManageListingIntents.intentForExistingListingSetting(context, listingId, SettingDeepLink.Price) : ManageListingIntents.intentForFallback(context);
        }

        public static Intent deepLinkIntentForExistingListingHouseRulesSetting(Context context, Bundle extras) {
            return ManageListingIntents.intentForHouseRules(context, ManageListingIntents.getId(extras, "id"));
        }

        public static Intent deepLinkIntentForExistingListing(Context context, Bundle extras) {
            long listingId = ManageListingIntents.getId(extras, "id");
            return ManageListingIntents.isValidId(listingId) ? ManageListingIntents.intentForExistingListing(context, listingId) : ManageListingIntents.intentForFallback(context);
        }

        public static Intent deepLinkIntentForCheckInGuide(Context context, Bundle extras) {
            long listingId = ManageListingIntents.getId(extras, "id");
            if (!ManageListingIntents.isValidId(listingId)) {
                return ManageListingIntents.intentForFallback(context);
            }
            return ManageListingIntents.intentForExistingListingSetting(context, listingId, SettingDeepLink.CheckInInformation);
        }
    }

    private ManageListingIntents() {
    }

    public static Intent intentForHouseRules(Context context, long listingId) {
        return isValidId(listingId) ? intentForExistingListingSetting(context, listingId, SettingDeepLink.HouseRules) : intentForFallback(context);
    }

    public static Intent intentForExistingListing(Context context, long listingId) {
        return new Intent(context, Activities.dlsManageListing()).putExtra("listing_id", listingId);
    }

    public static Intent intentForExistingListingSettingWithFullscreenLoader(Context context, long listingId, SettingDeepLink setting, boolean settingDeepLinkEarlyReturn) {
        return intentForExistingListingSetting(context, listingId, setting, settingDeepLinkEarlyReturn).putExtra(INTENT_EXTRA_SETTING_DEEP_LINK_SHOW_FULL_LOADING_ONLY, true);
    }

    public static Intent intentForExistingListingSetting(Context context, long listingId, SettingDeepLink setting, boolean settingDeepLinkEarlyReturn) {
        return intentForExistingListing(context, listingId).putExtra(INTENT_EXTRA_SETTING_DEEP_LINK, setting).putExtra(INTENT_EXTRA_SETTING_DEEP_LINK_EARLY_RETURN, settingDeepLinkEarlyReturn);
    }

    public static Intent intentForExistingListingSetting(Context context, long listingId, SettingDeepLink setting) {
        return intentForExistingListingSetting(context, listingId, setting, false);
    }

    public static Intent intentForListingsPage(Context context) {
        return intentForFallback(context);
    }

    /* access modifiers changed from: private */
    public static Intent intentForFallback(Context context) {
        return HomeActivityIntents.intentForListings(context);
    }

    public static boolean isValidId(long id) {
        return id != -1;
    }

    public static long getId(Bundle extras, String key) {
        try {
            return Long.parseLong(extras.getString(key));
        } catch (NumberFormatException e) {
            C0715L.m1191e(TAG, "Failed to parse ID");
            return -1;
        }
    }
}
