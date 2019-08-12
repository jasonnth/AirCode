package com.airbnb.android.lib.activities.booking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.booking.controller.BookingController;
import com.airbnb.android.booking.controller.BookingController.BookingActivityFacade;
import com.airbnb.android.booking.fragments.BookingV2BaseFragment;
import com.airbnb.android.booking.utils.BookingUtil;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.analytics.BookingAnalytics;
import com.airbnb.android.core.analytics.BookingJitneyLogger;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.BookingActivityIntents;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.core.utils.NavigationUtils;
import com.airbnb.android.core.utils.ParcelStrap;
import com.airbnb.android.explore.controllers.ExplorePerformanceAnalytics;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.LibBindings;
import com.airbnb.android.lib.LibComponent.Builder;
import com.airbnb.android.lib.fragments.price_breakdown.PriceBreakdownFragment;
import com.airbnb.android.lib.identity.psb.KonaManageGuestProfilesFragment;
import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.P4FlowNavigationMethod.p172v1.C2466P4FlowNavigationMethod;
import com.airbnb.jitney.event.logging.P4FlowPage.p173v1.C2467P4FlowPage;
import com.airbnb.p027n2.components.LoadingView;
import com.bugsnag.android.Severity;
import java.util.ArrayList;

public class BookingV2Activity extends AirActivity implements BookingActivityFacade {
    BookingJitneyLogger bookingJitneyLogger;
    BusinessTravelAccountManager businessTravelAccountManager;
    private BookingController controller;
    IdentityJitneyLogger identityJitneyLogger;
    @BindView
    LoadingView loadingView;
    ExplorePerformanceAnalytics performanceAnalytics;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        ButterKnife.bind((Activity) this);
        ((Builder) ((LibBindings) AirbnbApplication.instance(this).componentProvider()).libComponentProvider().get()).build().inject(this);
        setOnHomePressedListener(BookingV2Activity$$Lambda$1.lambdaFactory$(this));
        this.controller = new BookingController(this, this, this.requestManager, savedInstanceState);
        if (savedInstanceState == null) {
            this.controller.setListing((Listing) getIntent().getParcelableExtra(BookingActivityIntents.EXTRA_LISTING));
            this.controller.setMobileSearchSessionId(getIntent().getStringExtra(BookingActivityIntents.EXTRA_SESSION_ID));
            this.controller.setReservationDetails(getReservationDetailsFromIntent());
            this.controller.setHostMessage(getIntent().getStringExtra(BookingActivityIntents.EXTRA_HOST_MESSAGE));
            this.controller.showCurrentStep();
        }
    }

    public void showFragment(Fragment fragment, FragmentTransitionType transitionType) {
        NavigationUtils.showFragment(getSupportFragmentManager(), this, fragment, C0880R.C0882id.content_container, transitionType, false);
    }

    public void finishOK() {
        setResult(-1);
        finish();
    }

    public void finishCancelled() {
        setResult(0);
        finish();
    }

    public AirbnbAccountManager getAccountManager() {
        return this.accountManager;
    }

    public RequestManager getRequestManager() {
        return this.requestManager;
    }

    public BookingController getController() {
        return this.controller;
    }

    public boolean isVerifiedBusinessTraveler() {
        return this.businessTravelAccountManager.isVerifiedBusinessTraveler();
    }

    public void showPriceDetails() {
        showModal(PriceBreakdownFragment.forReservationBookingV2(this.controller.getPrice(), this.controller.getListing(), this.controller.getReservationDetails().checkIn().getDaysUntil(this.controller.getReservationDetails().checkOut()), true, NavigationTag.PriceBreakdown, ParcelStrap.make(BookingAnalytics.getP4NavigationTrackingParams(true))), C0880R.C0882id.content_container, C0880R.C0882id.modal_container, true);
    }

    public void showGuestIdentifications(boolean isBackButtonPressed, String p4Steps) {
        ReservationDetails reservationDetails = this.controller.getReservationDetails();
        showFragment(KonaManageGuestProfilesFragment.forSelectedIdentifications(reservationDetails.identifications() != null ? new ArrayList<>(reservationDetails.identifications()) : new ArrayList<>(), this.controller.getReservation().getGuestCount(), true, p4Steps), BookingUtil.getTransitionType(isBackButtonPressed));
    }

    public void trackP4LoadStart(long startTime) {
        this.performanceAnalytics.trackP4LoadStart(startTime, true);
    }

    public void trackP4LoadEnd(Strap eventData) {
        this.performanceAnalytics.trackP4LoadEnd(eventData, true);
    }

    public void removeP4LoadTracker() {
        this.performanceAnalytics.removeP4LoadTracker();
    }

    public void showLoader() {
        this.loadingView.setVisibility(0);
    }

    public void hideLoader() {
        this.loadingView.setVisibility(8);
    }

    public BookingJitneyLogger getLogger() {
        return this.bookingJitneyLogger;
    }

    public IdentityJitneyLogger getIdentityJitneyLogger() {
        return this.identityJitneyLogger;
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.controller.onSaveInstanceState(outState);
    }

    private ReservationDetails getReservationDetailsFromIntent() {
        ReservationDetails reservationDetails = (ReservationDetails) getIntent().getParcelableExtra(BookingActivityIntents.EXTRA_RESERVATION_DETAILS);
        if (reservationDetails != null) {
            return reservationDetails;
        }
        return ReservationDetails.fromIntent(getIntent(), (Listing) getIntent().getParcelableExtra(BookingActivityIntents.EXTRA_LISTING), this.accountManager.getCurrentUser());
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.controller.onActivityResult(requestCode, resultCode, data);
    }

    public void onBackPressed() {
        if (getSupportFragmentManager().findFragmentById(C0880R.C0882id.modal_container) != null) {
            logClick(C2467P4FlowPage.PriceBreakdown, C2466P4FlowNavigationMethod.CloseButton);
            getSupportFragmentManager().popBackStackImmediate();
            return;
        }
        Fragment fragment = getSupportFragmentManager().findFragmentById(C0880R.C0882id.content_container);
        if ((fragment instanceof KonaManageGuestProfilesFragment) && ((KonaManageGuestProfilesFragment) fragment).onBackPressed()) {
            return;
        }
        if (!(fragment instanceof BookingV2BaseFragment) || !((BookingV2BaseFragment) fragment).onBackPressed()) {
            if (fragment instanceof BookingV2BaseFragment) {
                logClick(((BookingV2BaseFragment) fragment).getP4FlowPage(), C2466P4FlowNavigationMethod.BackButton);
            } else {
                BugsnagWrapper.notify((Throwable) new IllegalArgumentException("Booking step fragment " + fragment + " not logged properly."), Severity.WARNING);
            }
            this.controller.showPreviousStep();
        }
    }

    private void logClick(C2467P4FlowPage flowPage, C2466P4FlowNavigationMethod method) {
        boolean isInstantBookable;
        ReservationDetails reservationDetails = this.controller.getReservationDetails();
        Reservation reservation = this.controller.getReservation();
        BookingJitneyLogger bookingJitneyLogger2 = this.bookingJitneyLogger;
        if (reservation == null) {
            isInstantBookable = false;
        } else {
            isInstantBookable = reservation.isInstantBookable();
        }
        bookingJitneyLogger2.clickNavigation(reservationDetails, isInstantBookable, flowPage, method);
    }
}
