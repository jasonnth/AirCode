package com.airbnb.android.ibdeactivation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.activities.SolitAirActivity;
import com.airbnb.android.core.enums.DeactivateIBReason;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.fragments.EditTextFragment.EditTextFragmentBuilder;
import com.airbnb.android.core.fragments.EditTextFragment.EditTextFragmentController;
import com.airbnb.android.core.fragments.EditTextFragment.EditTextFragmentListener;
import com.airbnb.android.core.intents.ManageListingIntents;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.SettingDeepLink;
import com.airbnb.android.ibdeactivation.DeactivateIBLandingFragment.DeactivateIBNavigator;
import icepick.State;

public class DeactivateIBActivity extends SolitAirActivity implements EditTextFragmentController, DeactivateIBNavigator {
    private static final String ARG_LISTING = "listing";
    private final EditTextFragmentListener editTextFragmentListener = DeactivateIBActivity$$Lambda$1.lambdaFactory$(this);
    @State
    Listing listing;

    public static Intent newIntent(Context context, Listing listing2) {
        Intent intent = new Intent(context, DeactivateIBActivity.class);
        intent.putExtra("listing", listing2);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C6454R.layout.activity_simple_fragment);
        ButterKnife.bind((Activity) this);
        if (savedInstanceState == null) {
            this.listing = (Listing) getIntent().getParcelableExtra("listing");
            showFragment(DeactivateIBLandingFragment.newInstanceForListing(this.listing), C6454R.C6456id.content_container, FragmentTransitionType.SlideInFromSide, true);
        }
    }

    public DeactivateIBNavigator getNavigator() {
        return this;
    }

    private void startActivityForDeepLink(SettingDeepLink settingDeepLink) {
        startActivity(ManageListingIntents.intentForExistingListingSetting(this, this.listing.getId(), settingDeepLink, true));
    }

    public void showReviewAllRequestsFragment(String hostMessage) {
        showFragment(ReviewAllRequestsFragment.newInstance(this.listing, hostMessage), C6454R.C6456id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }

    public EditTextFragmentListener getEditTextFragmentListener() {
        return this.editTextFragmentListener;
    }

    public void showTellUsMoreFragment() {
        showFragment(new EditTextFragmentBuilder().setHeaderTitle(getString(C6454R.string.deactivate_ib_reason_unlisted_title)).setHeaderSubtitle(getString(C6454R.string.deactivate_ib_unlisted_header)).showHeader(true).setHint(getString(C6454R.string.deactivate_ib_unlisted_hint)).build(), C6454R.C6456id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }

    public void onDeactivateReasonSelected(DeactivateIBReason reason) {
        switch (reason) {
            case GuestControl:
            case CalendarUpdate:
            case BetterOffer:
            case UnwareIB:
                showFragment(DeactivateIBLandingFragment.newInstanceForReason(this.listing, reason), C6454R.C6456id.content_container, FragmentTransitionType.SlideInFromSide, true);
                return;
            case AirbnbRequirements:
                DeactivateIBAnalytics.trackSubReasonClick(reason);
                showFragment(DeactivateIBLandingFragment.newInstanceForReason(this.listing, reason), C6454R.C6456id.content_container, FragmentTransitionType.SlideInFromSide, true);
                return;
            case TripLength:
                DeactivateIBAnalytics.trackSubReasonClick(reason);
                startActivityForDeepLink(SettingDeepLink.TripLength);
                return;
            case PreparationTime:
                DeactivateIBAnalytics.trackSubReasonClick(reason);
                startActivityForDeepLink(SettingDeepLink.TurnoverDays);
                return;
            case DistantRequest:
                DeactivateIBAnalytics.trackSubReasonClick(reason);
                startActivityForDeepLink(SettingDeepLink.BookingWindow);
                return;
            case UnawareCalendarUpdated:
                DeactivateIBAnalytics.trackSubReasonClick(reason);
                startActivityForDeepLink(SettingDeepLink.CalendarSettings);
                return;
            case UnawareHouseRules:
            case HouseRules:
                DeactivateIBAnalytics.trackSubReasonClick(reason);
                startActivityForDeepLink(SettingDeepLink.HouseRules);
                return;
            case Unlisted:
                showTellUsMoreFragment();
                return;
            default:
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Invalid DeactivateIBReason: " + reason.toString()));
                return;
        }
    }
}
