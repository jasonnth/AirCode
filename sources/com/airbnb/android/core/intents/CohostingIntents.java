package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.controllers.LottieNuxController;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.enums.LottieNuxViewPagerArguments;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.core.utils.CohostingDeepLink;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;

public class CohostingIntents {
    public static final String INTENT_EXTRA_DEEP_LINK = "deep_link";
    public static final String INTENT_EXTRA_FEE_TYPE = "fee_type";
    public static final String INTENT_EXTRA_INVITATION = "invitation";
    public static final String INTENT_EXTRA_INVITATIONS = "invitations";
    public static final String INTENT_EXTRA_INVITATION_CODE = "invite_code";
    public static final String INTENT_EXTRA_LISTING = "listing";
    public static final String INTENT_EXTRA_LISTING_ID = "listing_id";
    public static final String INTENT_EXTRA_LISTING_MANAGER = "listing_manager";
    public static final String INTENT_EXTRA_LISTING_MANAGERS = "listing_managers";
    public static final String INTENT_EXTRA_LISTING_MANAGER_USER_ID = "listing_manager_user_id";
    public static final String INTENT_EXTRA_REASON_TYPE = "reason_type";
    public static final String INTENT_EXTRA_SOURCE_FLOW_TO_INVITE_PAGE = "source_flow_to_invite_page";
    private static final String TAG = "CohostingIntents";
    private static final List<Float> animationTimes = Arrays.asList(new Float[]{Float.valueOf(0.14f), Float.valueOf(0.55f), Float.valueOf(1.0f), Float.valueOf(1.0f)});
    private static final List<SimpleEntry<Integer, Integer>> nux_page_content = Arrays.asList(new SimpleEntry[]{new SimpleEntry(Integer.valueOf(C0716R.string.cohosting_cohost_dashboard_nux_first_page_title), Integer.valueOf(C0716R.string.cohosting_cohost_dashboard_nux_first_page_subtitle)), new SimpleEntry(Integer.valueOf(C0716R.string.cohosting_cohost_dashboard_nux_second_page_title), Integer.valueOf(C0716R.string.cohosting_cohost_dashboard_nux_second_page_subtitle)), new SimpleEntry(Integer.valueOf(C0716R.string.cohosting_cohost_dashboard_nux_third_page_title), Integer.valueOf(C0716R.string.cohosting_cohost_dashboard_nux_third_page_subtitle))});

    public enum CohostReasonType {
        RemoveCohost,
        RemoveSelf
    }

    public static Intent intentForCohostManagementWithListing(Context context, Listing listing) {
        return new Intent(context, Activities.cohostManagement()).putExtra("listing", listing);
    }

    public static Intent intentForCohostManagementWithListingId(Context context, long listingId) {
        return new Intent(context, Activities.cohostManagement()).putExtra("listing_id", listingId);
    }

    public static Intent intentForListingManagerDeepLink(Context context, long listingId, long listingManagerUserId, CohostingDeepLink deepLink) {
        return new Intent(context, Activities.cohostManagement()).putExtra("listing_id", listingId).putExtra("deep_link", deepLink).putExtra(INTENT_EXTRA_LISTING_MANAGER_USER_ID, listingManagerUserId);
    }

    public static Intent intentForCohostUpsell(Context context, Listing listing, ListingManager manager) {
        return new Intent(context, Activities.cohostUpsell()).putExtra("listing", listing).putExtra(INTENT_EXTRA_LISTING_MANAGER, manager);
    }

    public static Intent intentForAcceptCohostInvitation(Context context, String invitationCode) {
        return new Intent(context, Activities.acceptCohostInvitation()).putExtra("invite_code", invitationCode);
    }

    public static Intent intentForRemoveCohostReasons(Context context, ListingManager manager, Listing listing) {
        return new Intent(context, Activities.cohostReasons()).putExtra(INTENT_EXTRA_LISTING_MANAGER, manager).putExtra("listing", listing).putExtra(INTENT_EXTRA_REASON_TYPE, CohostReasonType.RemoveCohost);
    }

    public static Intent intentForRemoveSelfReasons(Context context, ListingManager manager, Listing listing) {
        return new Intent(context, Activities.cohostReasons()).putExtra(INTENT_EXTRA_LISTING_MANAGER, manager).putExtra("listing", listing).putExtra(INTENT_EXTRA_REASON_TYPE, CohostReasonType.RemoveSelf);
    }

    public static Intent deepLinkIntentForListingManagers(Context context, Bundle extras) {
        long listingId = getId(extras, "id");
        return isValidId(listingId) ? intentForCohostManagementWithListingId(context, listingId) : intentForFallback(context);
    }

    public static Intent deepLinkIntentForAcceptCohostInvitation(Context context, Bundle extras) {
        return intentForAcceptCohostInvitation(context, extras.getString("code"));
    }

    public static Intent intentForCohostDashboardNux(Context context) {
        return LottieNuxController.intentForLottieNux(context, LottieNuxViewPagerArguments.builder().pagesContent(nux_page_content).animationTimes(animationTimes).coverPageTitleRes(Integer.valueOf(C0716R.string.cohosting_cohost_dashboard_nux_cover_page_title)).coverPageButtonTextRes(Integer.valueOf(C0716R.string.cohosting_cohost_dashboard_nux_cover_page_button)).coverPageImageRes(Integer.valueOf(C0716R.C0717drawable.n2_need_assets_from_design)).coverPageDescriptionRes(Integer.valueOf(C0716R.string.cohosting_cohost_dashboard_nux_cover_page_subtitle)).animationFilename("check_in_nux.json").build());
    }

    private static long getId(Bundle extras, String key) {
        try {
            return Long.parseLong(extras.getString(key));
        } catch (NumberFormatException e) {
            C0715L.m1191e(TAG, "Failed to parse ID");
            return -1;
        }
    }

    private static boolean isValidId(long id) {
        return id != -1;
    }

    private static Intent intentForFallback(Context context) {
        return HomeActivityIntents.intentForListings(context);
    }
}
