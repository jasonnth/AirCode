package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.text.SpannableStringBuilder;
import android.view.View;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.analytics.KonaBookingAnalytics;
import com.airbnb.android.booking.controller.BookingController;
import com.airbnb.android.booking.controller.BookingController.BookingActivityFacade;
import com.airbnb.android.booking.enums.BookingPerfEnum;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.analytics.BookingJitneyLogger;
import com.airbnb.android.core.enums.FetchPricingInteractionType;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.datepicker.DatesFragment;
import com.airbnb.android.core.models.ArrivalDetails;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.models.PricingQuote.RateType;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.core.models.ReservationDetails.Builder;
import com.airbnb.android.core.models.ReservationDetails.TripType;
import com.airbnb.android.core.requests.PricingQuoteRequest;
import com.airbnb.android.core.requests.booking.CreateReservationRequest;
import com.airbnb.android.core.requests.booking.UpdateGuestDetailsRequest;
import com.airbnb.android.core.responses.PricingQuoteResponse;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.utils.Strap;
import com.airbnb.erf.Experiments;
import com.airbnb.jitney.event.logging.P4FlowNavigationMethod.p172v1.C2466P4FlowNavigationMethod;
import com.airbnb.jitney.event.logging.P4FlowPage.p173v1.C2467P4FlowPage;
import com.airbnb.p027n2.components.BookingNavigationView;
import com.airbnb.p027n2.primitives.fonts.CustomFontSpan;
import com.airbnb.p027n2.primitives.fonts.Font;
import com.bugsnag.android.Severity;
import com.google.common.base.Stopwatch;
import icepick.State;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import p032rx.Observer;

public abstract class BookingV2BaseFragment extends AirFragment {
    private static final String INTERACTION_KEY = "interaction";
    private static final String RESERVATION_ID_KEY = "reservation_id";
    protected BookingJitneyLogger bookingJitneyLogger;
    final RequestListener<ReservationResponse> createReservationListener = new C0699RL().onResponse(BookingV2BaseFragment$$Lambda$1.lambdaFactory$(this)).onError(BookingV2BaseFragment$$Lambda$2.lambdaFactory$(this)).build();
    @State
    boolean defaultBusinessTravelOn;
    final RequestListener<ReservationResponse> guestDetailsUpdateListener = new RequestListener<ReservationResponse>() {
        public void onResponse(ReservationResponse data) {
            BookingV2BaseFragment.this.onReservationUpdate(data);
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            BookingV2BaseFragment.this.navView.hideLoader();
            NetworkUtil.tryShowRetryableErrorWithSnackbar(BookingV2BaseFragment.this.getView(), BookingV2BaseFragment$1$$Lambda$1.lambdaFactory$(this));
        }
    };
    @State
    String hostMessage;
    @State
    Listing listing;
    @State
    String mobileSearchSessionId;
    /* access modifiers changed from: private */
    public BookingNavigationView navView;
    @State
    boolean pendingGuestDetailsUpdate;
    @State
    Price price;
    final RequestListener<PricingQuoteResponse> pricingQuotesRequestListener = new C0699RL().onResponse(BookingV2BaseFragment$$Lambda$3.lambdaFactory$(this)).onComplete(BookingV2BaseFragment$$Lambda$4.lambdaFactory$(this)).build();
    @State
    Reservation reservation;
    @State
    ReservationDetails reservationDetails;
    private Stopwatch setupTimeStopWatch;

    public abstract C2467P4FlowPage getP4FlowPage();

    public abstract void onUpdateError(NetworkException networkException);

    public abstract void onUpdated();

    static /* synthetic */ void lambda$new$0(BookingV2BaseFragment bookingV2BaseFragment, ReservationResponse data) {
        boolean isMessageHostRequired;
        boolean z = true;
        BookingController controller = bookingV2BaseFragment.getController();
        bookingV2BaseFragment.reservation = data.reservation;
        bookingV2BaseFragment.price = bookingV2BaseFragment.reservation.getPricingQuote().getPrice();
        Listing listing2 = bookingV2BaseFragment.reservation.getListing();
        if (!Trebuchet.launch(TrebuchetKeys.P4_HIDE_MESSAGE_HOST_EXPERIMENT) || bookingV2BaseFragment.reservation.isShouldShowFirstMessage()) {
            isMessageHostRequired = true;
        } else {
            isMessageHostRequired = false;
        }
        Builder reservation2 = bookingV2BaseFragment.reservationDetails.toBuilder().reservation(bookingV2BaseFragment.reservation);
        if (listing2.hasHouseRules()) {
            z = false;
        }
        bookingV2BaseFragment.reservationDetails = reservation2.agreedToHouseRules(Boolean.valueOf(z)).isMessageHostRequired(Boolean.valueOf(isMessageHostRequired)).messageToHost(bookingV2BaseFragment.hostMessage).build();
        controller.setReservationDetails(bookingV2BaseFragment.reservationDetails);
        controller.setReservation(bookingV2BaseFragment.reservation);
        controller.setPrice(bookingV2BaseFragment.price);
        bookingV2BaseFragment.navView.hideLoader();
        controller.getTotalBookingSteps(false);
        bookingV2BaseFragment.onReservationCreated();
        bookingV2BaseFragment.refreshNavText();
        bookingV2BaseFragment.onP4LoadEnd(BookingPerfEnum.P4_RESERVATION);
    }

    static /* synthetic */ void lambda$new$2(BookingV2BaseFragment bookingV2BaseFragment, AirRequestNetworkException e) {
        bookingV2BaseFragment.onP4LoadEnd(BookingPerfEnum.P4_RESERVATION_ERROR);
        NetworkUtil.tryShowRetryableErrorWithSnackbar(bookingV2BaseFragment.getView(), (NetworkException) e, BookingV2BaseFragment$$Lambda$7.lambdaFactory$(bookingV2BaseFragment));
    }

    /* access modifiers changed from: protected */
    public void onReservationUpdate(ReservationResponse data) {
        GuestDetails newGuestDetails;
        this.navView.hideLoader();
        this.reservation = data.reservation;
        ArrivalDetails arrivalDetails = this.reservation.getArrivalDetails();
        if (arrivalDetails.getNumberOfAdults() > 0) {
            newGuestDetails = this.reservation.getGuestDetails();
        } else {
            newGuestDetails = new GuestDetails().adultsCount(this.reservation.getGuestCount());
        }
        this.price = this.reservation.getPricingQuote().getPrice();
        this.reservation.setGuestDetails(newGuestDetails);
        this.reservationDetails = this.reservationDetails.toBuilder().checkIn(this.reservation.getCheckIn()).totalPrice(Integer.valueOf(this.reservation.getPricingQuote().getTotalPrice().getAmount().intValue())).currency(this.reservation.getPricingQuote().getTotalPrice().getCurrency()).guestDetails(newGuestDetails).isBringingPets(Boolean.valueOf(arrivalDetails.isBringingPets())).build();
        BookingController controller = getController();
        controller.setReservation(this.reservation);
        controller.setReservationDetails(this.reservationDetails);
        controller.setPrice(this.price);
        controller.getTotalBookingSteps(false);
        refreshNavText();
        onUpdated();
    }

    private boolean defaultToBusinessTravelOn() {
        return getController().getBookingActivityFacade().isVerifiedBusinessTraveler() && Experiments.allowBusinessBookings();
    }

    static /* synthetic */ void lambda$new$3(BookingV2BaseFragment bookingV2BaseFragment, PricingQuoteResponse response) {
        bookingV2BaseFragment.price = response.pricingQuote.getPrice();
        bookingV2BaseFragment.refreshNavText();
        bookingV2BaseFragment.getController().setPrice(bookingV2BaseFragment.price);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
        this.setupTimeStopWatch = Stopwatch.createStarted();
        if (savedInstanceState == null) {
            BookingController controller = getController();
            this.listing = controller.getListing();
            this.reservation = controller.getReservation();
            this.reservationDetails = controller.getReservationDetails();
            this.price = controller.getPrice();
            this.mobileSearchSessionId = controller.getMobileSearchSessionId();
            this.hostMessage = controller.getHostMessage();
            this.defaultBusinessTravelOn = defaultToBusinessTravelOn();
            this.bookingJitneyLogger = controller.getBookingActivityFacade().getLogger();
        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (this.bookingJitneyLogger == null) {
            this.bookingJitneyLogger = getController().getBookingActivityFacade().getLogger();
        }
        if (this.reservation == null) {
            getController().fetchIdentity();
        }
    }

    /* access modifiers changed from: protected */
    public void createReservation() {
        if (this.reservationDetails.getGuestDetails().totalGuestCount() == 0) {
            this.reservationDetails = this.reservationDetails.toBuilder().guestDetails(new GuestDetails().adultsCount(1)).build();
        }
        if (this.defaultBusinessTravelOn && this.reservationDetails.getGuestDetails().totalGuestCount() == 1) {
            this.reservationDetails = this.reservationDetails.toBuilder().tripType(TripType.BusinessVerified).build();
        }
        new CreateReservationRequest(this.reservationDetails).withListener((Observer) this.createReservationListener).execute(this.requestManager);
    }

    /* access modifiers changed from: protected */
    public BookingController getController() {
        return ((BookingActivityFacade) getActivity()).getController();
    }

    public void setUpNavButton(BookingNavigationView navView2, int buttonTextRes) {
        this.navView = navView2;
        navView2.setButtonText(buttonTextRes);
        navView2.setSeePricingDetailsText(C0704R.string.p4_see_details);
        if (this.price == null) {
            navView2.showLoader();
        } else {
            refreshNavText();
        }
        navView2.setPriceDetailsOnClickListener(BookingV2BaseFragment$$Lambda$5.lambdaFactory$(this));
        navView2.setButtonOnClickListener(BookingV2BaseFragment$$Lambda$6.lambdaFactory$(this, navView2));
    }

    static /* synthetic */ void lambda$setUpNavButton$6(BookingV2BaseFragment bookingV2BaseFragment, BookingNavigationView navView2, View v) {
        if (!navView2.isLoading()) {
            bookingV2BaseFragment.logNavigationClick();
            bookingV2BaseFragment.getController().showNextStep(BookingController.getBookingStepLoader(navView2));
        }
    }

    /* access modifiers changed from: protected */
    public void logNavigationClick() {
        boolean isInstantBookable;
        BookingJitneyLogger bookingJitneyLogger2 = this.bookingJitneyLogger;
        ReservationDetails reservationDetails2 = this.reservationDetails;
        if (this.reservation == null) {
            isInstantBookable = false;
        } else {
            isInstantBookable = this.reservation.isInstantBookable();
        }
        bookingJitneyLogger2.clickNavigation(reservationDetails2, isInstantBookable, getP4FlowPage(), getP4FlowNavigationMethod());
    }

    /* access modifiers changed from: protected */
    public C2466P4FlowNavigationMethod getP4FlowNavigationMethod() {
        return C2466P4FlowNavigationMethod.NextButton;
    }

    private void refreshNavText() {
        String priceFormattedForDisplay;
        String priceDetailsText;
        if (this.navView != null) {
            Integer stayDuration = Integer.valueOf(this.reservationDetails.checkIn().getDaysUntil(this.reservationDetails.checkOut()));
            boolean isInInstallmentsExperiment = isInInstallmentsExperiment();
            if (isInInstallmentsExperiment) {
                priceFormattedForDisplay = this.reservation.getPricingQuote().getRate().formattedForDisplay();
            } else {
                priceFormattedForDisplay = this.price.getTotal().formattedForDisplay();
            }
            if (isInInstallmentsExperiment) {
                priceDetailsText = getString(C0704R.string.book_button_price_per_month, priceFormattedForDisplay);
            } else {
                priceDetailsText = getResources().getQuantityString(C0704R.plurals.x_nights_for_price, stayDuration.intValue(), new Object[]{priceFormattedForDisplay, stayDuration});
            }
            int priceStartIndex = priceDetailsText.indexOf(priceFormattedForDisplay);
            SpannableStringBuilder builder = new SpannableStringBuilder().append(priceDetailsText);
            builder.setSpan(new CustomFontSpan(getContext(), Font.CircularBold), priceStartIndex, priceFormattedForDisplay.length() + priceStartIndex, 0);
            this.navView.setPricingDetailsText(builder);
        }
    }

    private boolean isInInstallmentsExperiment() {
        return this.reservation != null && this.reservation.getPricingQuote().getRateType() == RateType.Monthly && (Experiments.showInstallmentsAbove() || Experiments.showInstallmentsBelow());
    }

    public void clickSeePriceDetails() {
        boolean isInstantBookable;
        BookingJitneyLogger bookingJitneyLogger2 = this.bookingJitneyLogger;
        ReservationDetails reservationDetails2 = this.reservationDetails;
        if (this.reservation == null) {
            isInstantBookable = false;
        } else {
            isInstantBookable = this.reservation.isInstantBookable();
        }
        bookingJitneyLogger2.clickNavigation(reservationDetails2, isInstantBookable, getP4FlowPage(), C2466P4FlowNavigationMethod.SeePriceDetailsButton);
        getController().getBookingActivityFacade().showPriceDetails();
    }

    private C2467P4FlowPage getP4FlowPageForModal(Fragment modalFragment) {
        if (modalFragment instanceof DatesFragment) {
            return C2467P4FlowPage.BookingDatepicker;
        }
        if (modalFragment instanceof BookingGuestsPickerFragment) {
            return C2467P4FlowPage.GuestSheet;
        }
        BugsnagWrapper.notify((Throwable) new IllegalArgumentException("Booking step modal " + modalFragment + " not logged properly."), Severity.WARNING);
        return null;
    }

    public boolean onBackPressed() {
        boolean z = false;
        Fragment modalFragment = getChildFragmentManager().findFragmentById(C0704R.C0706id.modal_container);
        if (modalFragment == null) {
            return false;
        }
        BookingJitneyLogger bookingJitneyLogger2 = this.bookingJitneyLogger;
        ReservationDetails reservationDetails2 = this.reservationDetails;
        if (this.reservation != null) {
            z = this.reservation.isInstantBookable();
        }
        bookingJitneyLogger2.clickNavigation(reservationDetails2, z, getP4FlowPageForModal(modalFragment), C2466P4FlowNavigationMethod.BackButton);
        getChildFragmentManager().popBackStack();
        return true;
    }

    public void onReservationCreated() {
        if (this.pendingGuestDetailsUpdate) {
            this.pendingGuestDetailsUpdate = false;
            updateGuestDetails();
        }
    }

    public void onGuestDetailsSaved(GuestDetails guestData) {
        KonaBookingAnalytics.trackUpdateGuestDetails(getNavigationTrackingTag().trackingName, guestData, this.reservationDetails, this.mobileSearchSessionId);
        this.reservationDetails = this.reservationDetails.toBuilder().guestDetails(guestData).build();
        getController().setReservationDetails(this.reservationDetails);
        if (this.reservation != null) {
            updateGuestDetails();
        } else if (getParentFragment() == null) {
            fetchPricingQuote();
        } else {
            this.pendingGuestDetailsUpdate = true;
        }
    }

    private void fetchPricingQuote() {
        new PricingQuoteRequest(this.listing.getId(), this.reservationDetails.checkIn(), this.reservationDetails.checkOut(), this.reservationDetails.getGuestDetails(), FetchPricingInteractionType.GuestChanged, "p4_" + UUID.randomUUID().toString(), "p4_guest_picker").withListener((Observer) this.pricingQuotesRequestListener).doubleResponse().execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void updateGuestDetails() {
        KonaBookingAnalytics.trackUpdate(getNavigationTrackingTag().trackingName, this.reservationDetails, this.mobileSearchSessionId);
        new UpdateGuestDetailsRequest(this.reservationDetails).withListener((Observer) this.guestDetailsUpdateListener).execute(this.requestManager);
        this.navView.showLoader();
        getChildFragmentManager().popBackStack();
    }

    /* access modifiers changed from: protected */
    public BookingV2BaseFragment getParentBookingV2BaseFragment() {
        Check.state(getParentFragment() instanceof BookingV2BaseFragment);
        return (BookingV2BaseFragment) getParentFragment();
    }

    /* access modifiers changed from: protected */
    public void onP4LoadStart() {
        long setupTimeDuration = this.setupTimeStopWatch.elapsed(TimeUnit.MILLISECONDS);
        if (getController() != null) {
            getController().getBookingActivityFacade().trackP4LoadStart(System.currentTimeMillis() - setupTimeDuration);
            this.setupTimeStopWatch.reset();
        }
    }

    /* access modifiers changed from: protected */
    public void onP4LoadEnd(BookingPerfEnum interaction) {
        if (this.setupTimeStopWatch != null) {
            getController().getBookingActivityFacade().trackP4LoadEnd(Strap.make().mo11638kv("reservation_id", this.reservation != null ? this.reservation.getId() : -1).mo11639kv("interaction", interaction.toString()));
        }
    }

    /* access modifiers changed from: protected */
    public void removeP4LoadTracker() {
        if (getController() != null) {
            getController().getBookingActivityFacade().removeP4LoadTracker();
        }
    }
}
