package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.controller.BookingController;
import com.airbnb.android.booking.enums.BookingPerfEnum;
import com.airbnb.android.booking.fragments.BookingGuestsPickerFragment.BookingGuestsPickerFragmentBuilder;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.analytics.BookingAnalytics;
import com.airbnb.android.core.analytics.BookingJitneyLogger;
import com.airbnb.android.core.controllers.CalendarViewCallbacks;
import com.airbnb.android.core.enums.UrgencyMessageType;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.fragments.datepicker.DatesFragment;
import com.airbnb.android.core.intents.BookingActivityIntents;
import com.airbnb.android.core.models.FullRefundUpsellInfo;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.core.models.ReservationDetails.TripType;
import com.airbnb.android.core.presenters.GuestDetailsPresenter;
import com.airbnb.android.core.requests.booking.UpdateBusinessTravelDetailRequest;
import com.airbnb.android.core.requests.booking.UpdateDatesRequest;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.ParcelStrap;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.core.viewcomponents.models.BookingListingDetailsSummaryEpoxyModel_;
import com.airbnb.android.core.views.UrgencyView;
import com.airbnb.android.core.views.calendar.CalendarView.Style;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.erf.Experiments;
import com.airbnb.jitney.event.logging.P4FlowDatepickerSection.p169v1.C2463P4FlowDatepickerSection;
import com.airbnb.jitney.event.logging.P4FlowPage.p173v1.C2467P4FlowPage;
import com.airbnb.jitney.event.logging.P4FlowSummarySection.p175v1.C2469P4FlowSummarySection;
import com.airbnb.jitney.event.logging.ToggleMethod.p268v1.C2759ToggleMethod;
import com.airbnb.jitney.event.logging.UcMessageType.p276v1.C2774UcMessageType;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.BookingNavigationView;
import com.airbnb.p027n2.components.KickerMarquee;
import com.airbnb.p027n2.components.StandardRow;
import com.airbnb.p027n2.components.SwitchRow;
import com.airbnb.p027n2.components.UserDetailsActionRow;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import p032rx.Observer;

public class BookingReviewFragment extends BookingV2BaseFragment implements CalendarViewCallbacks {
    final RequestListener<ReservationResponse> businessTravelDetailsUpdateListener = new RequestListener<ReservationResponse>() {
        public void onResponse(ReservationResponse data) {
            BookingReviewFragment.this.onReservationUpdate(data);
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            BookingReviewFragment.this.navView.hideLoader();
            NetworkUtil.tryShowRetryableErrorWithSnackbar(BookingReviewFragment.this.getView(), BookingReviewFragment$1$$Lambda$1.lambdaFactory$(this));
        }
    };
    @BindView
    SwitchRow businessTripToggleRow;
    final RequestListener<ReservationResponse> dateUpdateListener = new RequestListener<ReservationResponse>() {
        public void onResponse(ReservationResponse data) {
            BookingReviewFragment.this.onReservationUpdate(data);
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            BookingReviewFragment.this.navView.hideLoader();
            NetworkUtil.tryShowRetryableErrorWithSnackbar(BookingReviewFragment.this.getView(), BookingReviewFragment$2$$Lambda$1.lambdaFactory$(this));
        }
    };
    @BindView
    StandardRow datesRow;
    @BindView
    StandardRow guestRow;
    private boolean isCancellationPolicyVisible;
    @BindView
    UserDetailsActionRow listingDetailsSummary;
    @BindView
    KickerMarquee marquee;
    @BindView
    BookingNavigationView navView;
    private boolean pendingBusinessTripUpdate;
    private boolean pendingDatesUpdate;
    @BindView
    StandardRow refundPolicyView;
    private boolean shouldShowBusinessToggle;
    private Snackbar snackbar;
    private final OnClickListener specialOfferListener = BookingReviewFragment$$Lambda$1.lambdaFactory$(this);
    @BindView
    AirToolbar toolbar;
    @BindView
    UrgencyView urgencyView;

    static /* synthetic */ void lambda$new$0(BookingReviewFragment bookingReviewFragment, View v) {
        bookingReviewFragment.startActivity(BookingActivityIntents.intentForRebooking(bookingReviewFragment.getActivity(), bookingReviewFragment.listing, bookingReviewFragment.reservationDetails.toBuilder().reservationId(null).specialOfferId(null).paymentInstrument(null).totalPrice(null).currency(null).agreedToHouseRules(Boolean.valueOf(false)).build()));
        bookingReviewFragment.getActivity().finish();
    }

    public static BookingReviewFragment getInstance() {
        return new BookingReviewFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.reservation == null) {
            onP4LoadStart();
            createReservation();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0704R.layout.fragment_booking_review, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        new BookingListingDetailsSummaryEpoxyModel_().listing(this.listing).businessReady(this.listing.isBusinessTravelReady()).bind(this.listingDetailsSummary);
        refreshDates();
        refreshGuests();
        setUpNavButton(this.navView, C0704R.string.next);
        setupKicker();
        this.shouldShowBusinessToggle = shouldShowBusinessToggle();
        if (this.shouldShowBusinessToggle) {
            setUpBusinessTripToggle();
        }
        this.guestRow.showDivider(this.shouldShowBusinessToggle);
        return view;
    }

    public void onPause() {
        super.onPause();
        if (this.reservation == null) {
            removeP4LoadTracker();
        }
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showCancellationPolicyView();
        showUrgencyView();
    }

    private boolean shouldShowBusinessToggle() {
        return (getController().getBookingActivityFacade().isVerifiedBusinessTraveler() && Experiments.allowBusinessBookings()) || Experiments.isVisibleToNonVU();
    }

    public void setupKicker() {
        this.marquee.setKicker((CharSequence) getController().getP4Steps());
    }

    private void setUpBusinessTripToggle() {
        ViewUtils.setVisibleIf((View) this.businessTripToggleRow, this.shouldShowBusinessToggle);
        BookingController controller = getController();
        boolean isVerifiedBusinessTraveler = controller.getBookingActivityFacade().isVerifiedBusinessTraveler();
        this.businessTripToggleRow.setChecked(this.reservationDetails.isBusinessTrip());
        this.businessTripToggleRow.setOnCheckedChangeListener(BookingReviewFragment$$Lambda$2.lambdaFactory$(this, isVerifiedBusinessTraveler, controller));
    }

    static /* synthetic */ void lambda$setUpBusinessTripToggle$1(BookingReviewFragment bookingReviewFragment, boolean isVerifiedBusinessTraveler, BookingController controller, SwitchRowInterface v, boolean isBusinessTravel) {
        bookingReviewFragment.onP4LoadEnd(BookingPerfEnum.BUSINESS_TRAVEL);
        TripType tripType = isVerifiedBusinessTraveler ? isBusinessTravel ? TripType.BusinessVerified : TripType.PersonalVerified : isBusinessTravel ? TripType.BusinessUnverified : TripType.PersonalUnverified;
        bookingReviewFragment.reservationDetails = bookingReviewFragment.reservationDetails.toBuilder().tripType(tripType).build();
        controller.setReservationDetails(bookingReviewFragment.reservationDetails);
        bookingReviewFragment.updateBusinessTravelDetails();
        bookingReviewFragment.bookingJitneyLogger.businessTravelToggle(controller.getReservationDetails(), controller.getReservation() == null ? false : controller.getReservation().isInstantBookable(), isBusinessTravel ? C2759ToggleMethod.Toggle : C2759ToggleMethod.Untoggle);
    }

    /* access modifiers changed from: private */
    public void updateBusinessTravelDetails() {
        if (this.reservation != null) {
            this.navView.showLoader();
            UpdateBusinessTravelDetailRequest.forReservationDetails(this.reservationDetails).withListener((Observer) this.businessTravelDetailsUpdateListener).execute(this.requestManager);
            return;
        }
        this.pendingBusinessTripUpdate = true;
    }

    private void refreshGuests() {
        this.guestRow.setActionText((CharSequence) GuestDetailsPresenter.formatGuestCountLabelWithoutPets(getContext(), this.reservationDetails.getGuestDetails()));
        this.guestRow.setSubtitleText((CharSequence) this.reservationDetails.isBringingPets().booleanValue() ? getString(C0704R.string.p4_bringing_pets) : "");
    }

    private void refreshDates() {
        String dateFormat = getContext().getString(C0716R.string.date_name_format);
        this.datesRow.setActionText((CharSequence) getActivity().getString(C0704R.string.p4_date_range, new Object[]{this.reservationDetails.checkIn().formatDate(dateFormat), this.reservationDetails.checkOut().formatDate(dateFormat)}));
    }

    private void showUrgencyView() {
        if (this.reservation != null && !TextUtils.isEmpty(this.reservation.getUrgencyCommitmentType()) && FeatureToggles.showP4UrgencyMessage()) {
            UrgencyMessageType urgencyMessageType = UrgencyMessageType.fromKey(this.reservation.getUrgencyCommitmentType());
            this.urgencyView.setUrgencyData(this.reservation.getUrgencyCommitmentHeadline(), this.reservation.getUrgencyCommitmentBody(), urgencyMessageType);
            this.urgencyView.setVisibility(0);
            this.urgencyView.showDivider(false);
            this.urgencyView.showWithoutAnimation();
            showDividerAboveUrgencyMessage();
            logUrgencyMessageType(getJitneyUrgencyMessageKey(urgencyMessageType));
        }
    }

    private void showCancellationPolicyView() {
        if (this.reservation != null && Trebuchet.launch(TrebuchetKeys.P4_CANCELLATION_POLICY_FEATURE_FLAG)) {
            PricingQuote pricingQuote = this.reservation.getPricingQuote();
            FullRefundUpsellInfo fullRefundInfo = pricingQuote != null ? pricingQuote.getP4FullRefundUpsellInfo() : null;
            if (fullRefundInfo != null && fullRefundInfo.isFullRefund() && FeatureToggles.showP4CancellationPolicy()) {
                this.isCancellationPolicyVisible = true;
                this.refundPolicyView.setTitle((CharSequence) fullRefundInfo.getTitle());
                this.refundPolicyView.setSubtitleText((CharSequence) fullRefundInfo.getBody());
                this.refundPolicyView.setSubtitleMaxLine(Integer.MAX_VALUE);
                this.refundPolicyView.setFullWidthSubtitle(true);
                this.refundPolicyView.setVisibility(0);
                showDividerAboveCancellationPolicy();
                logUrgencyMessageType(C2774UcMessageType.CancellationPolicy);
            }
        }
    }

    private void logUrgencyMessageType(C2774UcMessageType jitneyUrgencyType) {
        boolean isInstantBookable;
        if (this.bookingJitneyLogger != null) {
            BookingJitneyLogger bookingJitneyLogger = this.bookingJitneyLogger;
            ReservationDetails reservationDetails = this.reservationDetails;
            if (this.reservation == null) {
                isInstantBookable = false;
            } else {
                isInstantBookable = this.reservation.isInstantBookable();
            }
            bookingJitneyLogger.bookingSummaryUCImpression(reservationDetails, isInstantBookable, jitneyUrgencyType);
        }
    }

    private C2774UcMessageType getJitneyUrgencyMessageKey(UrgencyMessageType urgencyMessageType) {
        try {
            return C2774UcMessageType.valueOf(urgencyMessageType.name());
        } catch (Exception e) {
            return null;
        }
    }

    private void showDividerAboveCancellationPolicy() {
        if (this.shouldShowBusinessToggle) {
            this.businessTripToggleRow.showDivider(true);
        } else {
            this.guestRow.showDivider(true);
        }
    }

    private void showDividerAboveUrgencyMessage() {
        if (this.isCancellationPolicyVisible) {
            this.refundPolicyView.showDivider(true);
        } else {
            showDividerAboveCancellationPolicy();
        }
    }

    public void onDetach() {
        if (this.snackbar != null) {
            this.snackbar.dismiss();
        }
        super.onDetach();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickDateSelection() {
        boolean isInstantBookable;
        onP4LoadEnd(BookingPerfEnum.DATES);
        BookingJitneyLogger bookingJitneyLogger = this.bookingJitneyLogger;
        ReservationDetails reservationDetails = this.reservationDetails;
        if (this.reservation == null) {
            isInstantBookable = false;
        } else {
            isInstantBookable = this.reservation.isInstantBookable();
        }
        bookingJitneyLogger.bookingSummaryClick(reservationDetails, isInstantBookable, C2469P4FlowSummarySection.Dates);
        if (this.reservationDetails.specialOfferId() == null) {
            showModal(DatesFragment.forBooking(this.listing, this.reservationDetails.checkIn(), this.reservationDetails.checkOut(), Style.WHITE, NavigationTag.BookingDatepicker, getNavigationTrackingTag(), ParcelStrap.make(BookingAnalytics.getP4NavigationTrackingParams(true))), C0704R.C0706id.content_container, C0704R.C0706id.modal_container, true);
        } else {
            showSpecialOfferSnackbar();
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickGuestDetails() {
        boolean isInstantBookable;
        onP4LoadEnd(BookingPerfEnum.GUESTS);
        BookingJitneyLogger bookingJitneyLogger = this.bookingJitneyLogger;
        ReservationDetails reservationDetails = this.reservationDetails;
        if (this.reservation == null) {
            isInstantBookable = false;
        } else {
            isInstantBookable = this.reservation.isInstantBookable();
        }
        bookingJitneyLogger.bookingSummaryClick(reservationDetails, isInstantBookable, C2469P4FlowSummarySection.Guests);
        if (this.reservationDetails.specialOfferId() == null) {
            showModal(new BookingGuestsPickerFragmentBuilder(new GuestDetails().reservationDetails(this.reservationDetails), NavigationTag.BookingSummary.trackingName).setListing(this.listing).setShowBlockInstantBookWarning(true).build(), C0704R.C0706id.content_container, C0704R.C0706id.modal_container, true);
        } else {
            showSpecialOfferSnackbar();
        }
    }

    private void showSpecialOfferSnackbar() {
        this.snackbar = new SnackbarWrapper().view(getView()).body(getString(C0704R.string.p4_existing_special_offer)).duration(-2).action(getString(C0704R.string.p4_new_request), this.specialOfferListener).buildAndShow();
    }

    public void onCalendarDatesApplied(AirDate start, AirDate end) {
        ParcelStrap kv = ParcelStrap.make().mo9946kv("ds_checkin", start.getIsoDateString()).mo9946kv("ds_checkout", end.getIsoDateString());
        this.reservationDetails = this.reservationDetails.toBuilder().checkIn(start).checkOut(end).build();
        getController().setReservationDetails(this.reservationDetails);
        if (this.reservation != null) {
            updateDates();
        } else {
            this.pendingDatesUpdate = true;
        }
    }

    public void onStartDateClicked(AirDate start) {
        boolean isInstantBookable;
        BookingJitneyLogger bookingJitneyLogger = this.bookingJitneyLogger;
        String isoDateString = start.getIsoDateString();
        ReservationDetails reservationDetails = this.reservationDetails;
        if (this.reservation == null) {
            isInstantBookable = false;
        } else {
            isInstantBookable = this.reservation.isInstantBookable();
        }
        bookingJitneyLogger.datePickerSelectDates(isoDateString, reservationDetails, isInstantBookable, C2463P4FlowDatepickerSection.Checkin);
    }

    public void onEndDateClicked(AirDate end) {
        boolean isInstantBookable;
        BookingJitneyLogger bookingJitneyLogger = this.bookingJitneyLogger;
        String isoDateString = end.getIsoDateString();
        ReservationDetails reservationDetails = this.reservationDetails;
        if (this.reservation == null) {
            isInstantBookable = false;
        } else {
            isInstantBookable = this.reservation.isInstantBookable();
        }
        bookingJitneyLogger.datePickerSelectDates(isoDateString, reservationDetails, isInstantBookable, C2463P4FlowDatepickerSection.Checkout);
    }

    /* access modifiers changed from: private */
    public void updateDates() {
        new UpdateDatesRequest(this.reservationDetails).withListener((Observer) this.dateUpdateListener).execute(this.requestManager);
        this.navView.showLoader();
        getChildFragmentManager().popBackStack();
    }

    public void onUpdateError(NetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(getView(), e);
    }

    public void onUpdated() {
        refreshGuests();
        refreshDates();
        setupKicker();
    }

    public void onReservationCreated() {
        super.onReservationCreated();
        if (this.pendingDatesUpdate) {
            this.pendingDatesUpdate = false;
            updateDates();
        }
        if (this.pendingBusinessTripUpdate) {
            this.pendingBusinessTripUpdate = false;
            updateBusinessTravelDetails();
        }
        showCancellationPolicyView();
        showUrgencyView();
        setupKicker();
    }

    public Strap getNavigationTrackingParams() {
        return BookingAnalytics.getP4NavigationTrackingParams(true);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.BookingSummary;
    }

    public C2467P4FlowPage getP4FlowPage() {
        return C2467P4FlowPage.BookingSummary;
    }
}
