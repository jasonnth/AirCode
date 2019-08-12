package com.airbnb.android.itinerary.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.ActivityOptionsCompat;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentTransaction;
import android.support.p000v4.util.Pair;
import android.view.View;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.models.TimelineMetadata;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.core.utils.MapUtil;
import com.airbnb.android.itinerary.C5755R;
import com.airbnb.android.itinerary.animation.ItineraryAnimationUtil;
import com.airbnb.android.itinerary.data.models.Suggestion;
import com.airbnb.android.itinerary.data.models.TimelineTrip;
import com.airbnb.android.itinerary.data.models.TripEvent;
import com.airbnb.android.itinerary.data.models.TripEventSecondaryAction;
import com.airbnb.android.itinerary.fragments.FlightReservationObjectFragment;
import com.airbnb.android.itinerary.fragments.TimelineFragment;
import com.airbnb.android.itinerary.fragments.TripFragment;
import com.airbnb.android.itinerary.utils.ItineraryUtils;
import com.airbnb.android.itinerary.views.TripCardView;
import com.airbnb.android.utils.AndroidVersion;
import com.airbnb.jitney.event.logging.MtPdpReferrer.p157v1.C2443MtPdpReferrer;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import icepick.State;
import java.util.List;

public class ItineraryNavigationController {
    public static final String FRAGMENT_TIMELINE_TAG = "fragmentTimelineTag";
    public static final String FRAGMENT_TRIP_TAG = "fragmentTripTag";
    private static final String TAG = "ItineraryNavController";
    private final Activity activity;
    private Context context;
    @State
    public String currentFragmentTag;
    private FragmentManager fragmentManager;
    private ItineraryJitneyLogger jitneyLogger;
    private ItineraryPerformanceAnalytics performanceAnalytics;

    public ItineraryNavigationController(Activity activity2, Context context2, Bundle savedState, FragmentManager fragmentManager2, ItineraryPerformanceAnalytics performanceAnalytics2, ItineraryJitneyLogger jitneyLogger2) {
        this.activity = activity2;
        this.context = context2;
        this.fragmentManager = fragmentManager2;
        this.performanceAnalytics = performanceAnalytics2;
        this.jitneyLogger = jitneyLogger2;
        IcepickWrapper.restoreInstanceState(this, savedState);
    }

    public void onSaveInstanceState(Bundle outState) {
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void navigateToTimeline(boolean isFirstLoad) {
        this.currentFragmentTag = FRAGMENT_TIMELINE_TAG;
        this.fragmentManager.beginTransaction().replace(C5755R.C5757id.container_itinerary, TimelineFragment.instance(isFirstLoad), FRAGMENT_TIMELINE_TAG).commit();
    }

    public void navigateToTripSchedule(View view, TimelineTrip timelineTrip, boolean showNowIndicator) {
        Fragment timelineFragment = this.fragmentManager.findFragmentByTag(FRAGMENT_TIMELINE_TAG);
        Fragment tripFragment = TripFragment.instance(timelineTrip.confirmation_code(), timelineTrip.title(), showNowIndicator);
        if (AndroidVersion.isAtLeastLollipop() && (timelineFragment instanceof TimelineFragment) && (view instanceof TripCardView)) {
            ItineraryAnimationUtil.setupTransitionToTripFragment(timelineFragment, tripFragment, view);
        }
        this.currentFragmentTag = FRAGMENT_TRIP_TAG;
        FragmentTransaction transaction = this.fragmentManager.beginTransaction().replace(C5755R.C5757id.container_itinerary, tripFragment, FRAGMENT_TRIP_TAG).addToBackStack(null);
        if (!AndroidVersion.isAtLeastLollipop()) {
            transaction.setCustomAnimations(FragmentTransitionType.SlideInFromSide.enter, FragmentTransitionType.SlideInFromSide.exit, FragmentTransitionType.SlideInFromSide.popEnter, FragmentTransitionType.SlideInFromSide.popExit);
        }
        transaction.commit();
        this.performanceAnalytics.trackTripEventLoadStart();
        this.jitneyLogger.trackClickItineraryEvent(timelineTrip.confirmation_code());
    }

    public void navigateToCurrentFragment(boolean isFirstLoad) {
        Fragment fragment = this.fragmentManager.findFragmentByTag(this.currentFragmentTag);
        if (fragment == null) {
            navigateToTimeline(isFirstLoad);
            return;
        }
        if (fragment instanceof TimelineFragment) {
            fragment = TimelineFragment.instance(isFirstLoad);
        }
        this.fragmentManager.beginTransaction().replace(C5755R.C5757id.container_itinerary, fragment, this.currentFragmentTag).commit();
    }

    public String getCurrentFragmentTag() {
        return this.currentFragmentTag;
    }

    public void navigateToTripEventCard(TripEvent tripEvent, boolean isTimeline) {
        Intent intent = null;
        switch (tripEvent.card_type()) {
            case Checkin:
            case Checkout:
                intent = ReactNativeIntents.intentForItineraryCheckinCard(this.context, tripEvent.confirmation_code(), tripEvent.schedule_confirmation_code(), tripEvent.picture());
                break;
            case Experience:
                intent = ReactNativeIntents.intentForItineraryExperienceCard(this.context, tripEvent.mo10245id().longValue(), tripEvent.schedule_confirmation_code(), tripEvent.picture());
                break;
            case Place:
                intent = ReactNativeIntents.intentForItineraryPlaceCardModal(this.context, tripEvent.mo10245id().longValue(), tripEvent.schedule_confirmation_code(), tripEvent.picture());
                break;
            case Flight:
                if (FeatureToggles.shouldShowFlightReservations()) {
                    intent = TransparentActionBarActivity.intentForFragment(this.context, FlightReservationObjectFragment.newInstance(tripEvent.mo10245id(), tripEvent.schedule_confirmation_code(), tripEvent.picture()));
                    break;
                }
                break;
        }
        if (intent != null) {
            this.jitneyLogger.trackClickCardItem(isTimeline, tripEvent);
            this.context.startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this.activity, new Pair[0]).toBundle());
        }
    }

    public void navigateToTripEventSecondaryAction(TripEvent tripEvent, boolean isTimeline) {
        TripEventSecondaryAction secondaryAction = tripEvent.getMainSecondaryAction();
        if (secondaryAction != null || secondaryAction.type() == null) {
            switch (secondaryAction.type()) {
                case Map:
                    double[] latLng = ItineraryUtils.getLatLng(secondaryAction.destination());
                    if (latLng != null) {
                        this.context.startActivity(MapUtil.intentFor(this.context, latLng[0], latLng[1], secondaryAction.destinationOptions()));
                        this.jitneyLogger.trackClickActionButton(isTimeline, tripEvent, ItineraryJitneyLogger.CLICK_ACTION_BUTTON_TARGET_SECONDARY_ACTION_DIRECTIONS);
                        return;
                    }
                    return;
                case Deeplink:
                    DeepLinkUtils.startActivityForDeepLink(this.context, secondaryAction.destination());
                    this.jitneyLogger.trackClickToReviewEvent(isTimeline, tripEvent, new Long(0));
                    return;
                default:
                    return;
            }
        }
    }

    public void navigateToPendingScreen(List<TimelineTrip> pendingTimelineTrips, TimelineMetadata pendingMetadata) {
        List<Bundle> tripList = FluentIterable.from((Iterable<E>) pendingTimelineTrips).transform(ItineraryNavigationController$$Lambda$1.lambdaFactory$()).toList();
        this.context.startActivity(ReactNativeIntents.intentForItineraryPendingScreen(this.context, ItineraryUtils.getVerificationBundleForPendingScreen(pendingMetadata), Lists.newArrayList((Iterable<? extends E>) tripList), ItineraryUtils.getPendingTimeLeft(this.context, pendingMetadata)));
    }

    public void navigateToSuggestion(Suggestion suggestion, String confirmationCode) {
        Intent intent = null;
        switch (suggestion.type()) {
            case Experience:
                intent = ReactNativeIntents.intentForExperiencePDP(this.context, false, suggestion.mo10321id(), C2443MtPdpReferrer.Itinerary, confirmationCode);
                break;
            case Immersion:
                intent = ReactNativeIntents.intentForExperiencePDP(this.context, true, suggestion.mo10321id(), C2443MtPdpReferrer.Itinerary, confirmationCode);
                break;
            case Place:
                intent = ReactNativeIntents.intentForPlaceP3(this.context, Long.valueOf(suggestion.mo10321id()), suggestion.title(), null);
                break;
        }
        if (intent != null) {
            this.jitneyLogger.trackClickItineraryRecommendationsEvent(suggestion, confirmationCode);
            this.context.startActivity(intent);
        }
    }

    public void navigateToFlightLandingPage() {
    }

    public boolean handleOnBackPressed() {
        Fragment fragment = this.fragmentManager.findFragmentByTag(this.currentFragmentTag);
        if (fragment instanceof OnBackListener) {
            return ((OnBackListener) fragment).onBackPressed();
        }
        return false;
    }

    public void popBackStack() {
        this.fragmentManager.popBackStack();
    }
}
