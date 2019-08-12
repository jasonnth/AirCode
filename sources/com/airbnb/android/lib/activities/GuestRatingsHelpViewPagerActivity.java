package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.ManageListingIntents;
import com.airbnb.android.core.utils.SettingDeepLink;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.FullScreenImageMarqueeFragment;
import icepick.State;

public class GuestRatingsHelpViewPagerActivity extends ViewPagerActivity {
    private static final String ARG_LISTING_ID = "listing_id";
    private static final int NUM_PAGES = 2;
    @State
    long listingId;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.listingId = getIntent().getLongExtra("listing_id", -1);
        }
        this.navigationAnalytics.trackImpressionExplicitly(NavigationTag.HostReservationGuestReviewRatingsUpsell, null);
    }

    public static Intent intentForDefault(Context context, long listingId2) {
        return new Intent(context, GuestRatingsHelpViewPagerActivity.class).putExtra("listing_id", listingId2);
    }

    public int getNumPages() {
        return 2;
    }

    public FullScreenImageMarqueeFragment forPage(int page) {
        switch (page) {
            case 0:
                return FullScreenImageMarqueeFragment.newInstance(C0880R.string.guest_ratings_help_title_1, C0880R.string.guest_ratings_help_description_1, C0880R.C0881drawable.guest_ratings_how_to_1, C0880R.string.guest_ratings_image_text_1);
            case 1:
                return FullScreenImageMarqueeFragment.newInstance(C0880R.string.guest_ratings_help_title_2, C0880R.string.guest_ratings_help_description_2, C0880R.C0881drawable.guest_ratings_how_to_2, C0880R.string.guest_ratings_image_text_2);
            default:
                return null;
        }
    }

    /* access modifiers changed from: protected */
    public boolean useModalTransition() {
        return true;
    }

    public int getPrimaryButtonText() {
        return C0880R.string.guest_ratings_help_cta_primary;
    }

    public int getSecondaryButtonText() {
        return C0880R.string.guest_ratings_help_cta_secondary;
    }

    public void onPrimaryClicked() {
        if (this.listingId == -1) {
            startActivity(ManageListingIntents.intentForListingsPage(this));
        } else {
            startActivity(ManageListingIntents.intentForExistingListingSetting(this, this.listingId, SettingDeepLink.InstantBook));
        }
    }

    public void onSecondaryClicked() {
        finish();
    }
}
