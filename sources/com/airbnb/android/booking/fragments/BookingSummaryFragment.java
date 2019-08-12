package com.airbnb.android.booking.fragments;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.booking.BookingGraph;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.analytics.KonaBookingAnalytics;
import com.airbnb.android.booking.analytics.KonaBookingAnalytics.BookingSummaryRow;
import com.airbnb.android.booking.fragments.redirectpay.RedirectPayAnalytics;
import com.airbnb.android.booking.fragments.redirectpay.RedirectPayResultHandler;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.airbnb.android.core.analytics.PsbAnalytics;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.identity.IdentityVerificationUtil;
import com.airbnb.android.core.intents.BookingActivityIntents;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.models.CancellationRefundBanner;
import com.airbnb.android.core.models.CheckinTimeSelectionOptions;
import com.airbnb.android.core.models.CurrencyAmount;
import com.airbnb.android.core.models.GovernmentIdResult;
import com.airbnb.android.core.models.GovernmentIdResult.Status;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.core.presenters.GuestDetailsPresenter;
import com.airbnb.android.core.requests.CancelReservationRequest;
import com.airbnb.android.core.requests.GetGovernmentIdResultsRequest;
import com.airbnb.android.core.requests.booking.BookingRequest;
import com.airbnb.android.core.requests.booking.responses.BookingResponse;
import com.airbnb.android.core.responses.CancelReservationResponse;
import com.airbnb.android.core.responses.GovernmentIdResultsResponse;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.core.viewcomponents.models.ListingDetailsSummaryEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.PriceSummaryEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.RangeDisplayEpoxyModel_;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.MicroRow;
import com.airbnb.p027n2.components.PriceSummary;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.components.RangeDisplay;
import com.airbnb.p027n2.components.StandardRow;
import com.airbnb.p027n2.components.TextRow;
import com.airbnb.p027n2.components.UserDetailsActionRow;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.google.common.collect.FluentIterable;
import icepick.State;
import p032rx.Observer;

public class BookingSummaryFragment extends BookingBaseFragment {
    private static final int GOVERNMENT_ID_RESULT_CHECK_INTERVAL = 5000;
    private static final int REQUEST_CODE_VERIFICATIONS = 9001;
    public static final String TAG = BookingSummaryFragment.class.getSimpleName();
    @BindView
    StandardRow arrivalDetailsStandardRow;
    final RequestListener<BookingResponse> bookingRequestListener = new C0699RL().onResponse(BookingSummaryFragment$$Lambda$6.lambdaFactory$(this)).onError(BookingSummaryFragment$$Lambda$7.lambdaFactory$(this)).build();
    BusinessTravelAccountManager businessTravelAccountManager;
    final RequestListener<CancelReservationResponse> cancelReservationListener = new C0699RL().onResponse(BookingSummaryFragment$$Lambda$8.lambdaFactory$(this)).onError(BookingSummaryFragment$$Lambda$9.lambdaFactory$(this)).build();
    @BindView
    PrimaryButton confirmAndPayPrimaryButton;
    @BindView
    LinearLayout contentContainer;
    @BindView
    StandardRow couponStandardRow;
    @BindView
    RangeDisplay dateRangeDisplay;
    @BindView
    StandardRow emailAddressStandardRow;
    final RequestListener<GovernmentIdResultsResponse> fetchGovernmentIdResultsListenerForPending = new C0699RL().onResponse(BookingSummaryFragment$$Lambda$3.lambdaFactory$(this)).onError(BookingSummaryFragment$$Lambda$4.lambdaFactory$(this)).build();
    final RequestListener<GovernmentIdResultsResponse> fetchGovernmentIdResultsListenerForSubmit = new C0699RL().onResponse(BookingSummaryFragment$$Lambda$1.lambdaFactory$(this)).onError(BookingSummaryFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    StandardRow fullyRefundableRow;
    @BindView
    TextRow fxTextRow;
    @BindView
    StandardRow governmentIdStandardRow;
    @BindView
    StandardRow guestIdentificationsRow;
    @BindView
    StandardRow guestsStandardRow;
    private final Handler handler = new Handler();
    @BindView
    StandardRow hostMessageStandardRow;
    @BindView
    StandardRow houseRulesStandardRow;
    IdentityJitneyLogger identityJitneyLogger;
    @State
    boolean isGovIdSnackbarVisible;
    @State
    boolean isInstantBookableIfGovIdProvided;
    @State
    boolean isPaymentRequestQueued;
    @BindView
    UserDetailsActionRow listingDetailsSummary;
    @BindView
    MicroRow listingNameMicroRow;
    @BindView
    View loadingOverlay;
    @State
    boolean loggedDeniedGovId;
    @BindView
    DocumentMarquee marquee;
    @BindView
    StandardRow nightsStandardRow;
    @BindView
    StandardRow paymentStandardRow;
    @BindView
    StandardRow phoneNumberStandardRow;
    @BindView
    PriceSummary priceSummary;
    private RedirectPayResultHandler redirectPayResultHandler;
    @State
    int scrollPosition = 0;
    @BindView
    VerboseScrollView scrollView;
    private final OnClickListener specialOfferListener = BookingSummaryFragment$$Lambda$5.lambdaFactory$(this);
    @BindView
    LinearLayout stepsToBookContainer;
    @BindView
    CoordinatorLayout termsLayout;
    @BindView
    TextRow termsTextRow;
    @BindView
    AirToolbar toolbar;

    public static BookingSummaryFragment newInstance() {
        return new BookingSummaryFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0704R.layout.fragment_booking, container, false);
        bindViews(view);
        ((BookingGraph) CoreApplication.instance(getActivity()).component()).inject(this);
        getAirActivity().setSupportActionBar(this.toolbar);
        updateNumberLabels();
        setUpListingInformation();
        return view;
    }

    public void onResume() {
        super.onResume();
        if (!hasReservation() || getReservation().getPricingQuote() == null) {
            if (hasReservation() && getReservation().getPricingQuote() == null) {
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Pricing quote can't be null. Reservation id: " + getReservation().getId()));
            }
            showLoader();
        } else {
            hideLoader();
            refreshBookingSummary();
        }
        setScrollPosition();
        if (this.isPaymentRequestQueued) {
            this.isPaymentRequestQueued = false;
            submitPaymentRequestForReservation(getBookingActivity().getReservationDetails());
        }
        if (this.requestManager.hasRequest((BaseRequestListener<T>) this.bookingRequestListener, BookingRequest.class)) {
            this.confirmAndPayPrimaryButton.setLoading();
            disableRowInteractions();
        }
        if (this.isGovIdSnackbarVisible) {
            setGovIdSnackbarVisible();
        }
        if (this.redirectPayResultHandler != null && !this.redirectPayResultHandler.isFinished()) {
            this.redirectPayResultHandler.startPolling();
        }
    }

    public void onPause() {
        super.onPause();
        this.handler.removeCallbacksAndMessages(null);
        this.scrollPosition = this.scrollView.getScrollY();
        if (this.redirectPayResultHandler != null && !this.redirectPayResultHandler.isFinished()) {
            this.redirectPayResultHandler.stopPolling();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != -1) {
            super.onActivityResult(requestCode, resultCode, data);
        } else if (requestCode == 9001) {
            getBookingActivity().fetchIdentityVerifications();
        }
    }

    private void showLoader() {
        this.loadingOverlay.setVisibility(0);
        this.contentContainer.setVisibility(8);
    }

    private void hideLoader() {
        this.loadingOverlay.setVisibility(8);
        this.contentContainer.setVisibility(0);
    }

    private void setScrollPosition() {
        if (getReservationDetails().completedRequiredDetails()) {
            this.scrollView.post(BookingSummaryFragment$$Lambda$10.lambdaFactory$(this));
        } else {
            this.scrollView.scrollTo(0, this.scrollPosition);
        }
    }

    private void setUpListingInformation() {
        Integer stayDuration = Integer.valueOf(getReservationDetails().checkIn().getDaysUntil(getReservationDetails().checkOut()));
        this.marquee.setTitle((CharSequence) getResources().getQuantityString(C0704R.plurals.x_nights_in_city, stayDuration.intValue(), new Object[]{stayDuration, TextUtils.isEmpty(getListing().getLocalizedCity()) ? getListing().getCountry() : getListing().getLocalizedCity()}));
        this.marquee.setCaption((CharSequence) getString(C0704R.string.bullet_with_space_parameterized, getBedroomCountString(), getBathroomCountString()));
        this.listingNameMicroRow.setText((CharSequence) getListing().getName());
        Listing listing = getListing();
        new ListingDetailsSummaryEpoxyModel_().listing(listing).businessReady(listing.isBusinessTravelReady()).bind(this.listingDetailsSummary);
    }

    private String getBedroomCountString() {
        int bedroomCount = getListing().getBedrooms();
        if (bedroomCount == 0) {
            return getResources().getString(C0704R.string.listing_rooms_studio);
        }
        return getResources().getQuantityString(C0704R.plurals.bedrooms, bedroomCount, new Object[]{Integer.valueOf(bedroomCount)});
    }

    private String getBathroomCountString() {
        float bathroomCount = getListing().getBathrooms();
        int roundedBathroomCount = Math.round(bathroomCount);
        if (((float) roundedBathroomCount) == bathroomCount) {
            return getResources().getQuantityString(C0704R.plurals.bathrooms, Math.round(bathroomCount), new Object[]{String.valueOf(roundedBathroomCount)});
        }
        return getResources().getQuantityString(C0704R.plurals.bathrooms, Math.round(bathroomCount), new Object[]{String.valueOf(bathroomCount)});
    }

    public void onReservationUpdate() {
        super.onReservationUpdate();
        if (isResumed()) {
            hideLoader();
            refreshBookingSummary();
        }
    }

    public void onReservationError() {
        if (isResumed()) {
            this.loadingOverlay.setVisibility(8);
            this.contentContainer.setVisibility(8);
        }
    }

    public void onVerificationUpdate() {
        refreshPhoneNumberRow();
        refreshEmailRow();
        refreshGovernmentIdRow();
    }

    private void refreshBookingSummary() {
        refreshGuests();
        refreshDates();
        refreshPriceSummary();
        refreshFullyRefundRow();
        refreshFXTextRow();
        refreshPaymentRow();
        refreshCouponRow();
        refreshHostMessageRow();
        refreshArrivalTimeRow();
        refreshHouseRulesRow();
        refreshTermsRow();
        refreshGuestIdentificationsRow();
        refreshPhoneNumberRow();
        refreshEmailRow();
        refreshGovernmentIdRow();
        updateNumberLabels();
        refreshPayButton();
        getBookingActivity().onBookingSummaryLoadEnd();
    }

    private void refreshGuests() {
        this.guestsStandardRow.setActionText((CharSequence) GuestDetailsPresenter.formatGuestCountLabelWithoutPets(getContext(), getReservation().getGuestDetails()));
        this.guestsStandardRow.setSubtitleText((CharSequence) getReservationDetails().isBringingPets().booleanValue() ? getString(C0704R.string.p4_bringing_pets) : "");
    }

    private void refreshDates() {
        new RangeDisplayEpoxyModel_().startDate(getReservation().getStartDate()).endDate(getReservation().getEndDate()).bind(this.dateRangeDisplay);
        this.nightsStandardRow.setActionText((CharSequence) String.valueOf(getReservation().getStayDuration()));
    }

    private boolean updatingDates() {
        return !getReservationDetails().checkIn().equals(getReservation().getCheckIn()) || !getReservationDetails().checkOut().equals(getReservation().getCheckOut());
    }

    private void refreshPriceSummary() {
        if (updatingDates()) {
            this.priceSummary.setLoading();
        } else {
            new PriceSummaryEpoxyModel_().currencyAmount(getReservation().getPricingQuote().getPrice().getTotal()).bind(this.priceSummary);
        }
    }

    private void refreshFullyRefundRow() {
        PricingQuote pricingQuote = getReservation().getPricingQuote();
        CancellationRefundBanner cancellationRefundBanner = pricingQuote != null ? pricingQuote.getP4CancellationRefundBanner() : null;
        if (cancellationRefundBanner == null || !cancellationRefundBanner.isShowBanner()) {
            this.fullyRefundableRow.setVisibility(8);
            return;
        }
        this.fullyRefundableRow.setTitle((CharSequence) cancellationRefundBanner.getPlainTitle());
        this.fullyRefundableRow.setSubtitleText((CharSequence) cancellationRefundBanner.getPlainBody());
        this.fullyRefundableRow.setSubtitleMaxLine(Integer.MAX_VALUE);
        this.fullyRefundableRow.setFullWidthSubtitle(true);
        this.fullyRefundableRow.setVisibility(0);
    }

    private void refreshFXTextRow() {
        String fxCopy = getReservationDetails().fxCopy();
        ViewUtils.setGoneIf(this.fxTextRow, TextUtils.isEmpty(fxCopy));
        this.fxTextRow.setText((CharSequence) fxCopy);
    }

    private void refreshPaymentRow() {
        OldPaymentInstrument instrument = getReservationDetails().paymentInstrument();
        this.paymentStandardRow.setActionText((CharSequence) instrument != null ? instrument.getDisplayName(getActivity()) : getString(C0704R.string.p4_add));
    }

    private void refreshCouponRow() {
        String couponCode = getReservation().getCouponCode();
        if (TextUtils.isEmpty(couponCode)) {
            this.couponStandardRow.setSubtitleText((CharSequence) "");
            this.couponStandardRow.setActionText(C0704R.string.p4_add);
            return;
        }
        CurrencyAmount couponAmount = getReservation().getPricingQuote().getCouponCurrencyAmount();
        String couponAmountDisplay = couponAmount == null ? getReservation().getPricingQuote().getPrice().getTotal().formattedForDisplay() : couponAmount.formattedForDisplay();
        this.couponStandardRow.setSubtitleText((CharSequence) couponCode);
        this.couponStandardRow.setActionText((CharSequence) couponAmountDisplay);
    }

    private void refreshHouseRulesRow() {
        if (getListing().hasHouseRules()) {
            this.houseRulesStandardRow.setVisibility(0);
            this.houseRulesStandardRow.setActionText(getReservationDetails().agreedToHouseRules().booleanValue() ? C0704R.string.p4_agreed : C0704R.string.p4_agree);
        }
    }

    private void refreshHostMessageRow() {
        if (getReservationDetails().isMessageHostRequired().booleanValue()) {
            this.hostMessageStandardRow.setVisibility(0);
            this.hostMessageStandardRow.setActionText(TextUtils.isEmpty(getReservationDetails().messageToHost()) ? C0704R.string.p4_add : C0704R.string.p4_done);
        }
    }

    private void refreshArrivalTimeRow() {
        if (getReservationDetails().isCheckInTimeRequired().booleanValue()) {
            this.arrivalDetailsStandardRow.setVisibility(0);
            if (TextUtils.isEmpty(getReservationDetails().checkInHour())) {
                this.arrivalDetailsStandardRow.setActionText(C0704R.string.p4_add);
                return;
            }
            CheckinTimeSelectionOptions checkInTime = (CheckinTimeSelectionOptions) FluentIterable.from((Iterable<E>) getReservation().getArrivalDetails().getValidCheckinTimeSelectionOptions()).firstMatch(BookingSummaryFragment$$Lambda$11.lambdaFactory$(this)).orNull();
            this.arrivalDetailsStandardRow.setActionText((CharSequence) checkInTime != null ? checkInTime.getLocalizedGuestCheckinWindow() : getString(C0704R.string.p4_done));
        }
    }

    public boolean isInstantBookableIfGovIdProvided() {
        boolean z = true;
        if (this.isInstantBookableIfGovIdProvided) {
            return true;
        }
        if (!Trebuchet.launch(TrebuchetKeys.IDENTITY_FOR_INSTANT_BOOK) || getBookingActivity().shouldUseIdentityFlowForFrozenReservation() || getReservation() == null || !getReservation().isGovernmentIdRequiredForInstantBook()) {
            z = false;
        }
        this.isInstantBookableIfGovIdProvided = z;
        return this.isInstantBookableIfGovIdProvided;
    }

    private void refreshGovernmentIdRow() {
        String localizedDenialReason;
        if (getBookingActivity().replaceVerifiedIdWithIdentityAsBookingStep() || isInstantBookableIfGovIdProvided()) {
            this.governmentIdStandardRow.setVisibility(0);
            GovernmentIdResult governmentIdResult = getBookingActivity().getGovernmentIdResult();
            Status status = governmentIdResult != null ? governmentIdResult.getStatusFromString() : Status.Unmapped;
            this.governmentIdStandardRow.setSubtitleText((CharSequence) "");
            if (getBookingActivity().replaceVerifiedIdWithIdentityAsBookingStep()) {
                Boolean required = getReservationDetails().requiresVerifications();
                if (required == null || !required.booleanValue()) {
                    getBookingActivity().setProvidedGovernmentId(true);
                    this.governmentIdStandardRow.setActionText(C0704R.string.account_verification_complete);
                } else {
                    getBookingActivity().setProvidedGovernmentId(false);
                    this.governmentIdStandardRow.setActionText(C0704R.string.confirm);
                }
                if (status != Status.Denied) {
                    return;
                }
            } else {
                getBookingActivity().setProvidedGovernmentId(status == Status.Approved || status == Status.Denied || status == Status.Awaiting);
            }
            refreshTermsRow();
            switch (status) {
                case Approved:
                    this.governmentIdStandardRow.setActionText(C0704R.string.account_verification_complete);
                    if (this.snackbar != null && this.snackbar.isShown()) {
                        this.snackbar.dismiss();
                        this.isGovIdSnackbarVisible = false;
                        this.confirmAndPayPrimaryButton.setNormal();
                        return;
                    }
                    return;
                case Awaiting:
                    this.governmentIdStandardRow.setActionText(C0704R.string.pending);
                    this.handler.postDelayed(BookingSummaryFragment$$Lambda$12.lambdaFactory$(this), 5000);
                    return;
                case Denied:
                    this.governmentIdStandardRow.setActionText(C0704R.string.verified_id_offline_try_again);
                    StandardRow standardRow = this.governmentIdStandardRow;
                    if (isInstantBookableIfGovIdProvided()) {
                        localizedDenialReason = getContext().getString(C0704R.string.account_verifications_instant_book_error, new Object[]{governmentIdResult.getLocalizedDenialReason()});
                    } else {
                        localizedDenialReason = governmentIdResult.getLocalizedDenialReason();
                    }
                    standardRow.setSubtitleText((CharSequence) localizedDenialReason);
                    this.governmentIdStandardRow.setFullWidthSubtitle(true);
                    if (this.snackbar != null && this.snackbar.isShown()) {
                        this.snackbar.dismiss();
                        this.isGovIdSnackbarVisible = false;
                        this.confirmAndPayPrimaryButton.setNormal();
                    }
                    if (!this.loggedDeniedGovId) {
                        AccountVerificationAnalytics.trackP4VerificationInstantBookDenied(governmentIdResult);
                        this.loggedDeniedGovId = true;
                        return;
                    }
                    return;
                default:
                    this.governmentIdStandardRow.setActionText(C0704R.string.confirm);
                    return;
            }
        } else {
            this.governmentIdStandardRow.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void checkGovernmentIdResult() {
        new GetGovernmentIdResultsRequest(getReservation().getGuest().getId()).withListener((Observer) this.fetchGovernmentIdResultsListenerForPending).execute(this.requestManager);
    }

    private void refreshEmailRow() {
        if (this.emailAddressStandardRow != null) {
            Boolean confirmed = getReservationDetails().confirmedEmailAddress();
            ViewUtils.setVisibleIf((View) this.emailAddressStandardRow, confirmed != null);
            this.emailAddressStandardRow.setActionText((confirmed == null || !confirmed.booleanValue()) ? C0704R.string.confirm : C0704R.string.change);
        }
    }

    private void refreshPhoneNumberRow() {
        if (this.phoneNumberStandardRow != null) {
            Boolean confirmed = getReservationDetails().confirmedPhoneNumber();
            ViewUtils.setVisibleIf((View) this.phoneNumberStandardRow, confirmed != null);
            this.phoneNumberStandardRow.setActionText((confirmed == null || !confirmed.booleanValue()) ? C0704R.string.confirm : C0704R.string.change);
        }
    }

    @SuppressLint({"StringFormatInvalid"})
    private void refreshTermsRow() {
        ViewUtils.setVisibleIf((View) this.termsLayout, getReservationDetails().completedRequiredDetails());
        this.termsTextRow.setText((CharSequence) getString(C0704R.string.p4_terms_and_conditions, getBookCtaString()));
    }

    private void refreshPayButton() {
        boolean z;
        String buttonText;
        PrimaryButton primaryButton = this.confirmAndPayPrimaryButton;
        if (!updatingDates()) {
            z = true;
        } else {
            z = false;
        }
        primaryButton.setEnabled(z);
        if (getReservationDetails().completedRequiredDetails()) {
            CurrencyAmount totalPrice = getReservation().getPricingQuote().getPrice().getTotal();
            buttonText = totalPrice.formattedForDisplay() + getString(C0704R.string.bullet_with_space) + getBookCtaString();
            blockForPendingGovId();
        } else {
            int stepsRemaining = getReservationDetails().requiredDetailsRemaining();
            buttonText = getResources().getQuantityString(C0704R.plurals.x_steps_left, stepsRemaining, new Object[]{Integer.valueOf(stepsRemaining)});
        }
        this.confirmAndPayPrimaryButton.setText((CharSequence) buttonText);
    }

    private String getBookCtaString() {
        int ctaId;
        if (getReservation().isWillAutoAccept()) {
            ctaId = C0704R.string.instant_confirm_booking;
        } else if (Experiments.useRequestApprovalCopy()) {
            ctaId = C0704R.string.request_approval_rtb_cta;
        } else if (Experiments.useRequestReservationCopy()) {
            ctaId = C0704R.string.request_reservation_rtb_cta;
        } else {
            ctaId = C0704R.string.request_to_book_rtb_cta;
        }
        return getString(ctaId);
    }

    private void updateNumberLabels() {
        int visiblePreviousSteps = 0;
        for (int i = 0; i < this.stepsToBookContainer.getChildCount(); i++) {
            StandardRow row = (StandardRow) this.stepsToBookContainer.getChildAt(i);
            if (row.getVisibility() == 0) {
                row.setTitle((CharSequence) getString(row.getTitleResourceId(), Integer.valueOf(visiblePreviousSteps + 1)));
                visiblePreviousSteps++;
            }
        }
    }

    private void refreshGuestIdentificationsRow() {
        boolean identificationsRequired = getReservation().isGuestIdentificationsRequired();
        ViewUtils.setVisibleIf((View) this.guestIdentificationsRow, identificationsRequired);
        if (identificationsRequired) {
            int identificationsAdded = getReservationDetails().identifications() == null ? 0 : getReservationDetails().identifications().size();
            PsbAnalytics.trackP4Impression(getReservation(), identificationsAdded);
            if (identificationsAdded == 0) {
                this.guestIdentificationsRow.setActionText(C0704R.string.p4_add);
            } else {
                this.guestIdentificationsRow.setActionText((CharSequence) getResources().getQuantityString(C0704R.plurals.x_guest_profiles, identificationsAdded, new Object[]{Integer.valueOf(identificationsAdded)}));
            }
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickGuestDetails() {
        KonaBookingAnalytics.trackClick(KonaBookingAnalytics.BOOKING_SUMMARY_PAGE, BookingSummaryRow.GuestDetailsRow.toString(), getReservationDetails().toBasicAnalyticsStrap(getMobileSearchSessionId()));
        if (getReservationDetails().specialOfferId() == null) {
            getBookingActivity().showGuestDetails();
        } else {
            showSpecialOfferSnackbar();
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickDateSelection() {
        KonaBookingAnalytics.trackClick(KonaBookingAnalytics.BOOKING_SUMMARY_PAGE, BookingSummaryRow.DateRangeRow.toString(), getReservationDetails().toBasicAnalyticsStrap(getMobileSearchSessionId()));
        changeDates();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickNightsRow() {
        KonaBookingAnalytics.trackClick(KonaBookingAnalytics.BOOKING_SUMMARY_PAGE, BookingSummaryRow.NightsRow.toString(), getReservationDetails().toBasicAnalyticsStrap(getMobileSearchSessionId()));
        changeDates();
    }

    private void changeDates() {
        if (getReservationDetails().specialOfferId() == null) {
            getBookingActivity().showCalendar();
        } else {
            showSpecialOfferSnackbar();
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickPriceBreakdown() {
        KonaBookingAnalytics.trackClick(KonaBookingAnalytics.BOOKING_SUMMARY_PAGE, BookingSummaryRow.PriceRow.toString(), getReservationDetails().toBasicAnalyticsStrap(getMobileSearchSessionId()));
        getBookingActivity().showPriceBreakdown();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickGuestIdentifications() {
        KonaBookingAnalytics.trackClick(KonaBookingAnalytics.BOOKING_SUMMARY_PAGE, "guest_identifications_row", getReservationDetails().toBasicAnalyticsStrap(getMobileSearchSessionId()));
        getBookingActivity().showGuestIdentifications();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickCouponCode() {
        KonaBookingAnalytics.trackClick(KonaBookingAnalytics.BOOKING_SUMMARY_PAGE, BookingSummaryRow.CouponCodeRow.toString(), getReservationDetails().toBasicAnalyticsStrap(getMobileSearchSessionId()));
        getBookingActivity().showCouponCode();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickPaymentOptions() {
        KonaBookingAnalytics.trackClick(KonaBookingAnalytics.BOOKING_SUMMARY_PAGE, BookingSummaryRow.PaymentOptionsRow.toString(), getReservationDetails().toBasicAnalyticsStrap(getMobileSearchSessionId()));
        getBookingActivity().showPaymentOptions();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickArrivalDetails() {
        KonaBookingAnalytics.trackClick(KonaBookingAnalytics.BOOKING_SUMMARY_PAGE, BookingSummaryRow.ArrivalDetailsRow.toString(), getReservationDetails().toBasicAnalyticsStrap(getMobileSearchSessionId()));
        getBookingActivity().showArrivalDetails();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickHouseRules() {
        KonaBookingAnalytics.trackClick(KonaBookingAnalytics.BOOKING_SUMMARY_PAGE, BookingSummaryRow.HouseRulesRow.toString(), getReservationDetails().toBasicAnalyticsStrap(getMobileSearchSessionId()));
        getBookingActivity().showHouseRules();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickTripPurpose() {
        KonaBookingAnalytics.trackClick(KonaBookingAnalytics.BOOKING_SUMMARY_PAGE, BookingSummaryRow.TripPurposeRow.toString(), getReservationDetails().toBasicAnalyticsStrap(getMobileSearchSessionId()));
        getBookingActivity().showMessageHost();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickSubmitButton() {
        if (getReservationDetails().completedRequiredDetails() || !showRequiredStepRemainingToast()) {
            Boolean requiresVerifications = getReservationDetails().requiresVerifications();
            if (requiresVerifications == null) {
                displayToast(getString(C0704R.string.security_check_network_error_dialog), getString(C0704R.string.verified_id_offline_try_again), BookingSummaryFragment$$Lambda$13.lambdaFactory$(this));
                return;
            }
            GovernmentIdResult governmentIdResult = getBookingActivity().getGovernmentIdResult();
            if (isInstantBookableIfGovIdProvided() && governmentIdResult == null) {
                displayToast(getString(C0704R.string.p4_required_government_id_confirmation), getString(C0704R.string.confirm), BookingSummaryFragment$$Lambda$14.lambdaFactory$(this));
            } else if (requiresVerifications.booleanValue()) {
                showVerificationSnackbar();
            } else if (getBookingActivity().shouldUseIdentityFlowForFrozenReservation()) {
                new GetGovernmentIdResultsRequest(getReservation().getGuest().getId()).withListener((Observer) this.fetchGovernmentIdResultsListenerForSubmit).execute(this.requestManager);
            } else if (!blockForPendingGovId()) {
                logRtbWithDeniedGovId();
                submitRequest();
            }
        }
    }

    private void logRtbWithDeniedGovId() {
        GovernmentIdResult governmentIdResult = getBookingActivity().getGovernmentIdResult();
        if (isInstantBookableIfGovIdProvided() && governmentIdResult != null && governmentIdResult.getStatusFromString() == Status.Denied) {
            AccountVerificationAnalytics.trackP4VerificationInstantBookDeniedRTB(governmentIdResult);
        }
    }

    private boolean blockForPendingGovId() {
        if (!isInstantBookableIfGovIdProvided()) {
            return false;
        }
        GovernmentIdResult governmentIdResult = getBookingActivity().getGovernmentIdResult();
        if ((governmentIdResult == null ? null : governmentIdResult.getStatusFromString()) != Status.Awaiting) {
            return false;
        }
        if (!this.isGovIdSnackbarVisible) {
            AccountVerificationAnalytics.trackP4VerificationInstantBookPending(governmentIdResult);
            setGovIdSnackbarVisible();
        }
        return true;
    }

    private void setGovIdSnackbarVisible() {
        this.snackbar = new SnackbarWrapper().view(this.termsLayout).title(C0704R.string.account_verification_pending_warning, true).body(C0704R.string.p4_required_government_id_pending).duration(-2).buildAndShow();
        this.isGovIdSnackbarVisible = true;
        this.confirmAndPayPrimaryButton.setLoading();
    }

    static /* synthetic */ void lambda$new$5(BookingSummaryFragment bookingSummaryFragment, GovernmentIdResultsResponse response) {
        GovernmentIdResult latestResult = response.getLatestResult();
        Status status = latestResult == null ? null : latestResult.getStatusFromString();
        if (status == null || status != Status.Denied) {
            bookingSummaryFragment.submitRequest();
            return;
        }
        AccountVerificationAnalytics.trackP4VerificationBlock(latestResult);
        bookingSummaryFragment.snackbar = new SnackbarWrapper().view(bookingSummaryFragment.getView()).title(latestResult.getLocalizedDenialReasonTitle(), true).body(latestResult.getLocalizedDenialReason()).duration(-2).action(C0704R.string.account_verification_upload_id_again, BookingSummaryFragment$$Lambda$24.lambdaFactory$(bookingSummaryFragment)).buildAndShow();
    }

    static /* synthetic */ void lambda$new$7(BookingSummaryFragment bookingSummaryFragment, GovernmentIdResultsResponse response) {
        GovernmentIdResult governmentIdResult = response.getLatestResult();
        bookingSummaryFragment.getBookingActivity().setGovernmentIdResult(governmentIdResult);
        if (governmentIdResult == null || governmentIdResult.getStatusFromString() != Status.Approved) {
            bookingSummaryFragment.refreshGovernmentIdRow();
            return;
        }
        bookingSummaryFragment.getBookingActivity().updateReservation();
        if (bookingSummaryFragment.getBookingActivity().replaceVerifiedIdWithIdentityAsBookingStep()) {
            bookingSummaryFragment.getBookingActivity().fetchIdentityVerifications();
        }
    }

    /* access modifiers changed from: private */
    public void submitRequest() {
        KonaBookingAnalytics.trackClick(KonaBookingAnalytics.BOOKING_SUMMARY_PAGE, KonaBookingAnalytics.CONFIRM_AND_PAY_BUTTON, getReservationDetails().toFullAnalyticsStrap(getMobileSearchSessionId()));
        getBookingActivity().bookReservation();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickPhoneNumber() {
        KonaBookingAnalytics.trackClick(KonaBookingAnalytics.BOOKING_SUMMARY_PAGE, BookingSummaryRow.PhoneNumberRow.toString(), getReservationDetails().toBasicAnalyticsStrap(getMobileSearchSessionId()));
        IdentityVerificationUtil.verify(this, VerificationFlow.FinalizeBooking, getListing().getPrimaryHost(), getBookingActivity().getIdentityController().getVerificationUser(), getListing().getId(), 9001, Boolean.valueOf(false), this.identityJitneyLogger, "phone");
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickGovernmentId() {
        GovernmentIdResult governmentIdResult = getBookingActivity().getGovernmentIdResult();
        Status status = governmentIdResult == null ? null : governmentIdResult.getStatusFromString();
        Boolean required = getReservationDetails().requiresVerifications();
        if (getBookingActivity().replaceVerifiedIdWithIdentityAsBookingStep()) {
            if (required == null || !required.booleanValue()) {
                return;
            }
        } else if (status == Status.Awaiting || status == Status.Approved) {
            return;
        }
        clickGovernmentId(BookingSummaryRow.GovernmentIdRow.toString());
    }

    /* access modifiers changed from: private */
    public void clickGovernmentId(String section) {
        KonaBookingAnalytics.trackClick(KonaBookingAnalytics.BOOKING_SUMMARY_PAGE, section, getReservationDetails().toBasicAnalyticsStrap(getMobileSearchSessionId()));
        boolean shouldUseIdentityFlowForFrozenReservation = getBookingActivity().shouldUseIdentityFlowForFrozenReservation();
        if (shouldUseIdentityFlowForFrozenReservation || isInstantBookableIfGovIdProvided()) {
            IdentityVerificationUtil.verify(this, VerificationFlow.FinalizeBooking, getListing().getPrimaryHost(), getBookingActivity().getIdentityController().getVerificationUser(), getListing().getId(), 9001, Boolean.valueOf(getBookingActivity().replaceVerifiedIdWithIdentityAsBookingStep()), this.identityJitneyLogger, shouldUseIdentityFlowForFrozenReservation ? new String[0] : new String[]{AccountVerification.SCANID});
        } else {
            getBookingActivity().fetchIdentityVerifications();
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void clickEmailAddress() {
        KonaBookingAnalytics.trackClick(KonaBookingAnalytics.BOOKING_SUMMARY_PAGE, BookingSummaryRow.EmailAddressRow.toString(), getReservationDetails().toBasicAnalyticsStrap(getMobileSearchSessionId()));
        IdentityVerificationUtil.verify(this, VerificationFlow.FinalizeBooking, getListing().getPrimaryHost(), getBookingActivity().getIdentityController().getVerificationUser(), getListing().getId(), 9001, Boolean.valueOf(false), this.identityJitneyLogger, "email");
    }

    private void showSpecialOfferSnackbar() {
        this.snackbar = new SnackbarWrapper().view(getView()).body(getString(C0704R.string.p4_existing_special_offer)).duration(-2).action(getString(C0704R.string.p4_new_request), this.specialOfferListener).buildAndShow();
    }

    static /* synthetic */ void lambda$new$9(BookingSummaryFragment bookingSummaryFragment, View v) {
        bookingSummaryFragment.startActivity(BookingActivityIntents.intentForRebooking(bookingSummaryFragment.getActivity(), bookingSummaryFragment.getListing(), bookingSummaryFragment.createNewReservationDetails()));
        bookingSummaryFragment.getActivity().finish();
    }

    private ReservationDetails createNewReservationDetails() {
        return getReservationDetails().toBuilder().reservationId(null).specialOfferId(null).paymentInstrument(null).totalPrice(null).currency(null).agreedToHouseRules(Boolean.valueOf(false)).build();
    }

    public void onUpdateError(String errorMessage) {
        super.onUpdateError(errorMessage);
        this.snackbar = new SnackbarWrapper().view(getView()).body(errorMessage).buildAndShow();
    }

    private boolean showRequiredStepRemainingToast() {
        ReservationDetails reservationDetails = getReservationDetails();
        if (reservationDetails.paymentInstrument() == null) {
            displayToast(getString(C0704R.string.p4_required_payment), getString(C0704R.string.p4_required_payment_action), BookingSummaryFragment$$Lambda$15.lambdaFactory$(this));
            return true;
        } else if (TextUtils.isEmpty(reservationDetails.messageToHost()) && reservationDetails.isMessageHostRequired().booleanValue()) {
            displayToast(getString(C0704R.string.p4_required_host_message), getString(C0704R.string.p4_required_host_message_action), BookingSummaryFragment$$Lambda$16.lambdaFactory$(this));
            return true;
        } else if (!reservationDetails.agreedToHouseRules().booleanValue()) {
            displayToast(getString(C0704R.string.p4_required_agree_house_rule), getString(C0704R.string.p4_required_agree_house_rules_action), BookingSummaryFragment$$Lambda$17.lambdaFactory$(this));
            return true;
        } else if (!reservationDetails.completedGuestIdentifications()) {
            displayToast(getString(C0704R.string.p4_required_guest_identifications), getString(C0704R.string.p4_required_guest_identifications_action), BookingSummaryFragment$$Lambda$18.lambdaFactory$(this));
            return true;
        } else if (reservationDetails.confirmedEmailAddress() != null && !reservationDetails.confirmedEmailAddress().booleanValue()) {
            displayToast(getString(C0704R.string.p4_required_email_confirmation), getString(C0704R.string.confirm), BookingSummaryFragment$$Lambda$19.lambdaFactory$(this));
            return true;
        } else if (reservationDetails.confirmedPhoneNumber() == null || reservationDetails.confirmedPhoneNumber().booleanValue()) {
            return false;
        } else {
            displayToast(getString(C0704R.string.p4_required_phone_number_confirmation), getString(C0704R.string.confirm), BookingSummaryFragment$$Lambda$20.lambdaFactory$(this));
            return true;
        }
    }

    private void displayToast(String body, String action, OnClickListener clickListener) {
        this.snackbar = new SnackbarWrapper().view(getView()).body(body).action(action, clickListener).duration(-2).buildAndShow();
    }

    private void showVerificationSnackbar() {
        Boolean usesIdentityFlow = getReservationDetails().usesIdentityFlow();
        boolean showProvideId = usesIdentityFlow != null && usesIdentityFlow.booleanValue();
        this.snackbar = new SnackbarWrapper().view(getView()).body(getString(showProvideId ? C0704R.string.account_verification_p4_provide_id_desc : C0704R.string.account_verification_p4_confirm_details_desc)).duration(-2).action(showProvideId ? C0704R.string.account_verification_p4_provide_id : C0704R.string.account_verification_p4_confirm_details, BookingSummaryFragment$$Lambda$21.lambdaFactory$(this, showProvideId)).buildAndShow();
    }

    static /* synthetic */ void lambda$showVerificationSnackbar$16(BookingSummaryFragment bookingSummaryFragment, boolean showProvideId, View v) {
        bookingSummaryFragment.clickGovernmentId(showProvideId ? "confirm_gov_id_nonbooking_step_snackbar" : "confirm_details_snackbar");
    }

    public void submitPaymentRequestForReservation(ReservationDetails reservationDetails) {
        if (isResumed()) {
            this.confirmAndPayPrimaryButton.setLoading();
            disableRowInteractions();
            new BookingRequest(reservationDetails, getMobileSearchSessionId()).withListener((Observer) this.bookingRequestListener).execute(this.requestManager);
            return;
        }
        this.isPaymentRequestQueued = true;
    }

    private void markReservationAsCancelled() {
        new CancelReservationRequest(getBookingActivity().getReservation().getConfirmationCode()).withListener((Observer) this.cancelReservationListener).execute(this.requestManager);
    }

    private void handleBookingResponse(BookingResponse response) {
        onBookingResult(response.reservation);
    }

    private void handleAlipayRedirectBookingResponse(BookingResponse response) {
        String deeplinkUrl = response.bookingRequestOperation.getRedirectSettings().url();
        if (TextUtils.isEmpty(deeplinkUrl)) {
            onAlipayRedirectError();
        }
        Intent alipayIntent = new Intent("android.intent.action.VIEW");
        alipayIntent.setData(Uri.parse(deeplinkUrl));
        try {
            startActivity(alipayIntent);
            this.redirectPayResultHandler = new RedirectPayResultHandler(this.requestManager, "alipay", getBookingActivity().getReservation().getId(), BookingSummaryFragment$$Lambda$22.lambdaFactory$(this), BookingSummaryFragment$$Lambda$23.lambdaFactory$(this));
            RedirectPayAnalytics.trackRedirectSuccess("alipay");
        } catch (ActivityNotFoundException e) {
            onAlipayRedirectError();
            RedirectPayAnalytics.trackRedirectFail("alipay");
        }
    }

    /* access modifiers changed from: private */
    public void onBookingResult(Reservation reservation) {
        this.confirmAndPayPrimaryButton.setNormal();
        enableRowInteractions();
        ReservationDetails reservationDetails = getReservationDetails();
        this.businessTravelAccountManager.logBTSurveyResults(reservationDetails.tripType(), reservationDetails.listingId().longValue(), reservationDetails.confirmationCode());
        getBookingActivity().completeBooking(reservation);
    }

    /* access modifiers changed from: private */
    public void onBookingError(AirRequestNetworkException e) {
        this.confirmAndPayPrimaryButton.setNormal();
        enableRowInteractions();
        getBookingActivity().handleBookingError(e);
    }

    /* access modifiers changed from: private */
    public void onAlipayRedirectError() {
        markReservationAsCancelled();
    }

    /* access modifiers changed from: private */
    public void onAlipayCancelResult() {
        this.confirmAndPayPrimaryButton.setNormal();
        enableRowInteractions();
        getBookingActivity().handleAlipayError();
    }

    static /* synthetic */ void lambda$new$17(BookingSummaryFragment bookingSummaryFragment, BookingResponse response) {
        if (response.bookingRequestOperation.getRedirectSettings() != null) {
            bookingSummaryFragment.handleAlipayRedirectBookingResponse(response);
        } else {
            bookingSummaryFragment.handleBookingResponse(response);
        }
    }

    private void disableRowInteractions() {
        for (int i = 0; i < this.contentContainer.getChildCount(); i++) {
            this.contentContainer.getChildAt(i).setEnabled(false);
        }
    }

    private void enableRowInteractions() {
        for (int i = 0; i < this.contentContainer.getChildCount(); i++) {
            this.contentContainer.getChildAt(i).setEnabled(true);
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.Booking;
    }
}
