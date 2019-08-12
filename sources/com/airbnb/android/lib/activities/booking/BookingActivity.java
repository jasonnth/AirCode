package com.airbnb.android.lib.activities.booking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View.OnClickListener;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.ErrorResponse;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.booking.activities.BookingActivityFacade;
import com.airbnb.android.booking.activities.CreditCardActivity;
import com.airbnb.android.booking.analytics.KonaBookingAnalytics;
import com.airbnb.android.booking.fragments.ArrivalDetailsFragment;
import com.airbnb.android.booking.fragments.BookingSummaryFragment;
import com.airbnb.android.booking.fragments.CouponCodeFragment;
import com.airbnb.android.booking.fragments.GuestsPickerInterstitialFragment;
import com.airbnb.android.booking.fragments.GuestsPickerInterstitialFragment.GuestsPickerInterstitialFragmentBuilder;
import com.airbnb.android.booking.fragments.PaymentInstrumentsFragment;
import com.airbnb.android.booking.fragments.PaymentManagerFragment;
import com.airbnb.android.booking.fragments.PaymentManagerFragment.PaymentManagerListener;
import com.airbnb.android.booking.fragments.TooltipFragment;
import com.airbnb.android.booking.fragments.androidpay.AndroidPayApiLegacy;
import com.airbnb.android.booking.utils.PaymentUtils;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.activities.ModalActivity;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.airbnb.android.core.analytics.PsbAnalytics;
import com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.controllers.CalendarViewCallbacks;
import com.airbnb.android.core.controllers.ContactHostControllerProvider;
import com.airbnb.android.core.controllers.ContactHostFragmentController;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.enums.TripPurpose;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.ContactHostFragment;
import com.airbnb.android.core.fragments.DLSHouseRulesFragment;
import com.airbnb.android.core.fragments.EditTextFragment.EditTextFragmentBuilder;
import com.airbnb.android.core.fragments.EditTextFragment.EditTextFragmentController;
import com.airbnb.android.core.fragments.EditTextFragment.EditTextFragmentListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.fragments.datepicker.DatesFragment;
import com.airbnb.android.core.fragments.guestpicker.GuestPickerFragment;
import com.airbnb.android.core.fragments.guestpicker.GuestPickerFragment.GuestPickerController;
import com.airbnb.android.core.fragments.guestpicker.GuestPickerFragment.GuestPickerControllerProvider;
import com.airbnb.android.core.fragments.guestpicker.GuestPickerFragment.GuestPickerFragmentBuilder;
import com.airbnb.android.core.identity.FetchIdentityController;
import com.airbnb.android.core.identity.FetchIdentityController.MultiVerificationFlowListener;
import com.airbnb.android.core.intents.AccountVerificationStartActivityIntents;
import com.airbnb.android.core.intents.BookingActivityIntents;
import com.airbnb.android.core.intents.PostBookingActivityIntents;
import com.airbnb.android.core.intents.VerifiedIdActivityIntents;
import com.airbnb.android.core.interfaces.GuestIdentity;
import com.airbnb.android.core.interfaces.UpdateRequestListener;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.models.ArrivalDetails;
import com.airbnb.android.core.models.FreezeDetails;
import com.airbnb.android.core.models.GovernmentIdResult;
import com.airbnb.android.core.models.GovernmentIdResult.Status;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationDetails;
import com.airbnb.android.core.models.ReservationDetails.Builder;
import com.airbnb.android.core.models.ReservationDetails.TripType;
import com.airbnb.android.core.models.payments.AlipayPaymentInstrument;
import com.airbnb.android.core.models.payments.AndroidPayInstrument;
import com.airbnb.android.core.models.payments.BraintreeCreditCard;
import com.airbnb.android.core.models.payments.BraintreePaymentInstrument;
import com.airbnb.android.core.models.payments.BusinessTravelPaymentInstruments;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.core.models.payments.OtherPaymentInstrument;
import com.airbnb.android.core.models.payments.PayPalInstrument;
import com.airbnb.android.core.p009p3.interfaces.ContactHostDataChangeListener;
import com.airbnb.android.core.requests.CreateInquiryRequest;
import com.airbnb.android.core.requests.ListingRequest;
import com.airbnb.android.core.requests.base.AirlockErrorHandler;
import com.airbnb.android.core.requests.booking.CreateReservationRequest;
import com.airbnb.android.core.requests.booking.UpdateArrivalDetailsRequest;
import com.airbnb.android.core.requests.booking.UpdateBusinessTravelDetailRequest;
import com.airbnb.android.core.requests.booking.UpdateDatesRequest;
import com.airbnb.android.core.requests.booking.UpdateGuestDetailsRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.CreateInquiryResponse;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.ParcelStrap;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.core.views.calendar.CalendarView.Style;
import com.airbnb.android.explore.controllers.ExplorePerformanceAnalytics;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.LibBindings;
import com.airbnb.android.lib.LibComponent;
import com.airbnb.android.lib.activities.BookingWebViewActivity;
import com.airbnb.android.lib.activities.PayWithAlipayActivity;
import com.airbnb.android.lib.businesstravel.BusinessTravelInterstitialFragment;
import com.airbnb.android.lib.businesstravel.TripNoteFragment;
import com.airbnb.android.lib.fragments.price_breakdown.PriceBreakdownFragment;
import com.airbnb.android.lib.identity.psb.KonaManageGuestProfilesFragment;
import com.airbnb.android.lib.utils.ContactHostHelper;
import com.airbnb.android.utils.Strap;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.google.android.gms.wallet.FullWallet;
import com.google.common.base.Optional;
import com.google.common.base.Stopwatch;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import p032rx.Observer;

public class BookingActivity extends AirActivity implements BookingActivityFacade, CalendarViewCallbacks, ContactHostControllerProvider, EditTextFragmentController, GuestPickerControllerProvider {
    private static final String AIREVENT_BOOKING_FAILED = "booking_failed_submission";
    private static final int AIREVENT_MISSING_FIELD = -1;
    private static final int BOOKING_ADULT_COUNT_MIN = 1;
    private static final String ERROR_TYPE_CURRENCY_MISMATCH = "settlement_currency_change";
    private static final String ERROR_TYPE_POSTAL_CODE_MISMATCH = "cc_zip_retry";
    private static final int REQUEST_CODE_HOUSE_RULES = 989;
    private static final int REQUEST_CODE_PAY_WITH_ALIPAY = 990;
    private static final int REQUEST_CODE_PAY_WITH_OTHER_PAYMENT = 991;
    private static final int REQUEST_CODE_VERIFY_IDENTITY = 992;
    public static final String TAG = BookingActivity.class.getSimpleName();
    AirlockErrorHandler airlockErrorHandler;
    private AndroidPayApiLegacy androidPayApi;
    BusinessTravelAccountManager businessTravelAccountManager;
    @State
    ArrayList<ContactHostDataChangeListener> changeListeners = new ArrayList<>();
    private final ContactHostFragmentController contactHostFragmentController = new ContactHostFragmentController() {
        public String getInquiryMessage() {
            return BookingActivity.this.inquiryMessage;
        }

        public AirDate getCheckInDate() {
            return BookingActivity.this.reservationDetails.checkIn();
        }

        public AirDate getCheckOutDate() {
            return BookingActivity.this.reservationDetails.checkOut();
        }

        public GuestDetails getGuestDetails() {
            return BookingActivity.this.buildGuestDetails();
        }

        public void onDatesUpdateRequested() {
            BookingActivity.this.showCalendar();
        }

        public void onGuestsUpdateRequested() {
            BookingActivity.this.showGuestDetails();
        }

        public void onComposeMessageRequested() {
            BookingActivity.this.showEditTextModalWithHostMarquee(BookingActivity.this.inquiryMessage);
        }

        public void onSubmitToHost() {
            new CreateInquiryRequest(BookingActivity.this.inquiryMessage, BookingActivity.this.reservation.getListing().getId(), BookingActivity.this.reservationDetails.checkIn(), BookingActivity.this.reservationDetails.checkOut(), BookingActivity.this.reservationDetails.getGuestDetails(), BookingActivity.this.reservation.getListing().getPrimaryHost().getId(), BookingActivity.this.inquiryRequestListener).execute(BookingActivity.this.requestManager);
        }

        public void registerListener(ContactHostDataChangeListener listener) {
            BookingActivity.this.changeListeners.add(listener);
        }

        public void unregisterListener(ContactHostDataChangeListener listener) {
            BookingActivity.this.changeListeners.remove(listener);
        }
    };
    final RequestListener<ReservationResponse> createReservationListener = new C0699RL().onResponse(BookingActivity$$Lambda$3.lambdaFactory$(this)).onError(BookingActivity$$Lambda$4.lambdaFactory$(this)).build();
    private final EditTextFragmentListener editTextFragmentListener = new EditTextFragmentListener() {
        public void onMessageSaved(String text) {
            if (BookingActivity.this.shouldSkipMessageHostFragment) {
                BookingActivity.this.doneWithMessageHost(text);
                BookingActivity.this.shouldSkipMessageHostFragment = false;
                return;
            }
            BookingActivity.this.inquiryMessage = text;
            BookingActivity.this.getSupportFragmentManager().popBackStack();
        }
    };
    /* access modifiers changed from: private */
    public FetchIdentityController fetchIdentityController;
    private final MultiVerificationFlowListener fetchIdentityListener = new MultiVerificationFlowListener() {
        public void onVerificationsFetchComplete(HashMap<VerificationFlow, ArrayList<AccountVerification>> incompleteVerifications) {
            ReservationDetails build;
            boolean z = true;
            BookingActivity.this.governmentIdResult = BookingActivity.this.fetchIdentityController.getGovernmentIdResult();
            boolean isPhoneRegistration = BookingActivity.this.accountManager.getCurrentUser().isPhoneNumberRegisteredUser();
            BookingActivity.this.phoneOrEmailVerification = (AccountVerification) FluentIterable.from((Iterable) incompleteVerifications.get(VerificationFlow.FinalizeBooking)).firstMatch(BookingActivity$1$$Lambda$1.lambdaFactory$(isPhoneRegistration)).orNull();
            if (!(BookingActivity.this.reservationDetails.confirmedEmailAddress() == null && BookingActivity.this.reservationDetails.confirmedPhoneNumber() == null)) {
                boolean requiresVerification = BookingActivity.this.phoneOrEmailVerification != null && !BookingActivity.this.phoneOrEmailVerification.isComplete();
                BookingActivity bookingActivity = BookingActivity.this;
                if (isPhoneRegistration) {
                    Builder builder = BookingActivity.this.reservationDetails.toBuilder();
                    if (requiresVerification) {
                        z = false;
                    }
                    build = builder.confirmedEmailAddress(Boolean.valueOf(z)).build();
                } else {
                    Builder builder2 = BookingActivity.this.reservationDetails.toBuilder();
                    if (requiresVerification) {
                        z = false;
                    }
                    build = builder2.confirmedPhoneNumber(Boolean.valueOf(z)).build();
                }
                bookingActivity.reservationDetails = build;
            }
            if (BookingActivity.this.getReservation() != null) {
                BookingActivity.this.onReservationUpdate();
            } else {
                BookingActivity.this.onVerificationUpdate();
            }
            BookingActivity.this.incompleteVerifications = (ArrayList) incompleteVerifications.get(BookingActivity.this.verificationFlow);
            BookingActivity.this.startVerificationFlowIfNeeded();
        }

        static /* synthetic */ boolean lambda$onVerificationsFetchComplete$0(boolean isPhoneRegistration, AccountVerification verification) {
            if (isPhoneRegistration) {
                return verification.getType().equals("email");
            }
            return verification.getType().equals("phone");
        }

        public void onVerificationsFetchError(NetworkException e) {
            NetworkUtil.toastGenericNetworkError(BookingActivity.this);
        }
    };
    @State
    String firstVerificationStep;
    @State
    GovernmentIdResult governmentIdResult;
    private final GuestPickerController guestPickerController = new GuestPickerController() {
        public void onGuestDetailsSaved(GuestDetails guestData, UpdateRequestListener listener) {
            listener.onUpdateStarted();
            KonaBookingAnalytics.trackUpdateGuestDetails(KonaBookingAnalytics.BOOKING_SUMMARY_PAGE, guestData, BookingActivity.this.reservationDetails, BookingActivity.this.mobileSearchSessionId);
            ReservationDetails newReservationDetails = BookingActivity.this.reservationDetails.toBuilder().guestDetails(guestData).build();
            KonaBookingAnalytics.trackUpdate(KonaBookingAnalytics.BOOKING_SUMMARY_PAGE, newReservationDetails, BookingActivity.this.mobileSearchSessionId);
            BookingActivity.this.updateReservationGuestDetails(newReservationDetails);
        }

        public NavigationTag getNavigationAnalyticsTag() {
            return NavigationTag.GuestsDetails;
        }
    };
    @State
    String hostMessage;
    IdentityJitneyLogger identityJitneyLogger;
    @State
    ArrayList<AccountVerification> incompleteVerifications;
    @State
    String inquiryMessage;
    final RequestListener<CreateInquiryResponse> inquiryRequestListener = new RequestListener<CreateInquiryResponse>() {
        public void onResponse(CreateInquiryResponse data) {
            BookingActivity.this.getSupportFragmentManager().popBackStack();
            BookingActivity.this.displayMessage(BookingActivity.this.getString(C0880R.string.ro_status_inquiry_for_listing, new Object[]{BookingActivity.this.getReservation().getListing().getName()}));
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            BookingActivity.this.getSupportFragmentManager().popBackStack();
            BookingActivity.this.displayError(BookingActivity.this.getString(C0880R.string.error), BookingActivity.this.getString(C0880R.string.error_send_inquiry), null, null);
        }
    };
    @State
    Listing listing;
    final RequestListener<ListingResponse> listingListener = new C0699RL().onResponse(BookingActivity$$Lambda$1.lambdaFactory$(this)).onError(BookingActivity$$Lambda$2.lambdaFactory$(this)).build();
    @State
    String mobileSearchSessionId;
    @State
    ArrayList<OldPaymentInstrument> paymentInstruments;
    /* access modifiers changed from: private */
    public PaymentManagerFragment paymentManagerFragment;
    private final PaymentManagerListener paymentManagerListener = new PaymentManagerListener() {
        public void onAndroidPayConfigured() {
            if (BookingActivity.this.paymentManagerFragment.isAndroidPayEnabled() && BookingActivity.this.paymentInstruments != null) {
                BookingActivity.this.addAndroidPayIfEligible(BookingActivity.this.paymentInstruments);
            }
        }

        public void onPaymentManagerError(int errorCode, Exception error) {
            Log.d(BookingActivity.TAG, "Payment Manager Error: " + error.getMessage());
        }

        public void onNonceCreated(OldPaymentInstrument instrument) {
            BookingActivity.this.reservationDetails = BookingActivity.this.reservationDetails.toBuilder().paymentInstrument(instrument).build();
            BookingActivity.this.getBookingSummaryFragment().submitPaymentRequestForReservation(BookingActivity.this.reservationDetails);
        }

        public void onNonceError() {
            if (BookingActivity.this.reservationDetails.paymentInstrument() instanceof AndroidPayInstrument) {
                BookingActivity.this.handleAndroidPayError();
            }
        }
    };
    ExplorePerformanceAnalytics performanceAnalytics;
    @State
    AccountVerification phoneOrEmailVerification;
    @State
    Reservation reservation;
    @State
    ReservationDetails reservationDetails;
    private Stopwatch setupTimeStopWatch;
    @State
    boolean shouldSkipMessageHostFragment;
    final RequestListener<ReservationResponse> updateReservationListener = new RequestListener<ReservationResponse>() {
        public void onResponse(ReservationResponse data) {
            GuestDetails newGuestDetails;
            BookingActivity.this.reservation = data.reservation;
            ArrivalDetails arrivalDetails = BookingActivity.this.reservation.getArrivalDetails();
            if (arrivalDetails.getNumberOfAdults() > 0) {
                newGuestDetails = BookingActivity.this.reservation.getGuestDetails();
            } else {
                newGuestDetails = new GuestDetails().adultsCount(BookingActivity.this.reservation.getGuestCount());
            }
            BookingActivity.this.reservation.setGuestDetails(newGuestDetails);
            BookingActivity.this.reservationDetails = BookingActivity.this.reservationDetails.toBuilder().checkIn(BookingActivity.this.reservation.getCheckIn()).totalPrice(Integer.valueOf(BookingActivity.this.reservation.getPricingQuote().getTotalPrice().getAmount().intValue())).currency(BookingActivity.this.reservation.getPricingQuote().getTotalPrice().getCurrency()).guestDetails(newGuestDetails).isBringingPets(Boolean.valueOf(arrivalDetails.isBringingPets())).build();
            BookingActivity.this.onReservationUpdate();
            UpdateRequestListener listenerFragment = BookingActivity.this.getUpdateListenerFargment();
            if (listenerFragment != null) {
                listenerFragment.onUpdated();
            }
            if ((listenerFragment instanceof GuestPickerFragment) || (listenerFragment instanceof ArrivalDetailsFragment)) {
                BookingActivity.this.getSupportFragmentManager().popBackStack();
            }
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            BookingActivity.this.onReservationUpdateError(NetworkUtil.errorDetails(e));
        }
    };
    @State
    VerificationFlow verificationFlow;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        this.setupTimeStopWatch = Stopwatch.createStarted();
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        ButterKnife.bind((Activity) this);
        ((LibComponent.Builder) ((LibBindings) AirbnbApplication.instance(this).componentProvider()).libComponentProvider().get()).build().inject(this);
        if (!BuildHelper.isDevelopmentBuild() || !DeepLinkUtils.isDeepLink(getIntent())) {
            if (savedInstanceState == null) {
                this.listing = (Listing) getIntent().getParcelableExtra(BookingActivityIntents.EXTRA_LISTING);
                this.mobileSearchSessionId = getIntent().getStringExtra("android.content.pm.extra.SESSION_ID");
                this.firstVerificationStep = getIntent().getStringExtra(BookingActivityIntents.EXTRA_FIRST_VERIFICATION_STEP);
                this.reservationDetails = getReservationDetailsFromIntent();
                this.hostMessage = getIntent().getStringExtra(BookingActivityIntents.EXTRA_HOST_MESSAGE);
                this.verificationFlow = TextUtils.isEmpty(this.firstVerificationStep) ? VerificationFlow.Booking : VerificationFlow.MobileHandOff;
                initFetchIdentityController(savedInstanceState);
                showNextStep();
            }
        } else if (savedInstanceState == null) {
            ListingRequest.forListingId(DeepLinkUtils.getParamAsId(getIntent(), "id")).withListener((Observer) this.listingListener).execute(this.requestManager);
        }
        if (this.fetchIdentityController == null) {
            initFetchIdentityController(savedInstanceState);
        }
        this.androidPayApi = initializePaymentManagerFragment();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.fetchIdentityController.onSaveInstanceState(outState);
    }

    private void initFetchIdentityController(Bundle savedInstanceState) {
        this.fetchIdentityController = new FetchIdentityController(this.requestManager, this.fetchIdentityListener, new VerificationFlow[]{this.verificationFlow, VerificationFlow.VerifiedID, VerificationFlow.FinalizeBooking}, savedInstanceState);
    }

    private ReservationDetails getReservationDetailsFromIntent() {
        ReservationDetails reservationDetails2 = (ReservationDetails) getIntent().getParcelableExtra(BookingActivityIntents.EXTRA_RESERVATION_DETAILS);
        return reservationDetails2 != null ? reservationDetails2 : ReservationDetails.fromIntent(getIntent(), this.listing, this.accountManager.getCurrentUser());
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            switch (requestCode) {
                case 602:
                    this.androidPayApi.onFullWalletLoaded((FullWallet) data.getParcelableExtra("com.google.android.gms.wallet.EXTRA_FULL_WALLET"));
                    return;
                case REQUEST_CODE_HOUSE_RULES /*989*/:
                    KonaBookingAnalytics.trackClick(ListingRequestConstants.JSON_HOUSE_RULES_KEY, "agree_rules", getReservationDetails().toBasicAnalyticsStrap(this.mobileSearchSessionId));
                    doneWithHouseRules();
                    return;
                case REQUEST_CODE_PAY_WITH_ALIPAY /*990*/:
                    this.reservation = (Reservation) data.getParcelableExtra(PayWithAlipayActivity.EXTRA_RESERVATION);
                    completeBooking(this.reservation);
                    return;
                case 991:
                    if (isVerifiedIdRequiredFromOtherPaymentWebView(data)) {
                        this.reservation.setCheckpointedStatus();
                    }
                    completeBooking(this.reservation);
                    return;
                case REQUEST_CODE_VERIFY_IDENTITY /*992*/:
                    this.reservationDetails = this.reservationDetails.toBuilder().requiresVerifications(Boolean.valueOf(false)).build();
                    if (replaceVerifiedIdWithIdentityAsBookingStep() || getBookingSummaryFragment().isInstantBookableIfGovIdProvided()) {
                        fetchIdentityVerifications();
                        return;
                    } else {
                        onReservationUpdate();
                        return;
                    }
                case ContactHostHelper.REQUEST_CODE_MESSAGE_TO_HOST /*993*/:
                    String message = ContactHostHelper.getMessageFromRNContactHostResponse(data);
                    if (message != null) {
                        doneWithMessageHost(message);
                        return;
                    }
                    return;
                case CreditCardActivity.REQUEST_CODE_POSTAL_RETRY /*11002*/:
                    handleUpdatedPostalCode(data.getStringExtra(CreditCardActivity.RESULT_EXTRA_POSTAL_CODE));
                    return;
                default:
                    super.onActivityResult(requestCode, resultCode, data);
                    return;
            }
        } else {
            switch (requestCode) {
                case REQUEST_CODE_PAY_WITH_ALIPAY /*990*/:
                    handleNewAlipayBookingError(data.getStringExtra(PayWithAlipayActivity.EXTRA_ERROR_MESSAGE));
                    return;
                default:
                    super.onActivityResult(requestCode, resultCode, data);
                    return;
            }
        }
    }

    static /* synthetic */ void lambda$new$0(BookingActivity bookingActivity, ListingResponse response) {
        bookingActivity.listing = response.listing;
        long guestId = bookingActivity.accountManager.getCurrentUser().getId();
        Check.state(BuildHelper.isDevelopmentBuild());
        bookingActivity.reservationDetails = ReservationDetails.builder().listingId(Long.valueOf(bookingActivity.listing.getId())).guestId(Long.valueOf(guestId)).numberOfAdults(Integer.valueOf(1)).numberOfChildren(Integer.valueOf(0)).numberOfInfants(Integer.valueOf(0)).isBringingPets(Boolean.valueOf(false)).checkIn(new AirDate("2016-06-03")).checkOut(new AirDate("2016-06-05")).tripPurpose(TripPurpose.Business).agreedToHouseRules(Boolean.valueOf(false)).build();
        bookingActivity.showNextStep();
    }

    private void showNextStep() {
        if (shouldShowGuestPickerInterstitial()) {
            showGuestPickerInterstitial();
        } else if (shouldShowBusinessTravelInterstitial()) {
            showBusinessTravelInterstitial();
        } else {
            transitionToBooking();
        }
    }

    private boolean shouldShowGuestPickerInterstitial() {
        return this.reservationDetails.getGuestDetails().totalGuestCount() == 0;
    }

    private void showGuestPickerInterstitial() {
        if (this.setupTimeStopWatch.isRunning()) {
            this.setupTimeStopWatch.stop();
        }
        showFragment(new GuestsPickerInterstitialFragmentBuilder(buildGuestDetails(), NavigationTag.P3.trackingName).setListing(this.listing).setShowBlockInstantBookWarning(true).build(), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true, GuestsPickerInterstitialFragment.TAG);
    }

    private boolean shouldShowBusinessTravelInterstitial() {
        return (this.businessTravelAccountManager.isVerifiedBusinessTraveler() && Experiments.allowBusinessBookings()) || Experiments.isVisibleToNonVU();
    }

    public void showBusinessTravelInterstitial() {
        if (this.setupTimeStopWatch.isRunning()) {
            this.setupTimeStopWatch.stop();
        }
        showFragment(BusinessTravelInterstitialFragment.newInstance(), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true, BusinessTravelInterstitialFragment.TAG);
    }

    public void showBusinessTravelTripNote() {
        showFragment(TripNoteFragment.newInstanceWithTripNote(this.reservationDetails.businessTripNote()), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true, TripNoteFragment.TAG);
    }

    public void showBooking() {
        showFragment(BookingSummaryFragment.newInstance(), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true, BookingSummaryFragment.TAG);
        onBookingSummaryLoadStart();
    }

    private void onBookingSummaryLoadStart() {
        long setupTimeDuration = this.setupTimeStopWatch.elapsed(TimeUnit.MILLISECONDS);
        if (setupTimeDuration > 0) {
            this.performanceAnalytics.trackP4LoadStart(System.currentTimeMillis() - setupTimeDuration, false);
            this.setupTimeStopWatch.reset();
        }
    }

    public void onBookingSummaryLoadEnd() {
        this.performanceAnalytics.trackP4LoadEnd(Strap.make().mo11638kv("reservation_id", this.reservation.getId()), false);
    }

    public void showGuestDetails() {
        showModal(new GuestPickerFragmentBuilder(buildGuestDetails(), NavigationTag.P4.trackingName).setListing(getReservation().getListing()).setMaxNumberOfGuests(getReservation().getListing().getPersonCapacity()).setMinNumberOfAdults(1).setShowBlockInstantBookWarning(this.reservation.isWillAutoAccept()).build());
    }

    public void showCalendar() {
        showModal(DatesFragment.forBooking(this.reservation.getListing(), this.reservation.getCheckIn(), this.reservation.getCheckOut(), Style.BABU, NavigationTag.FindDatepicker, NavigationTag.P4, ParcelStrap.make()));
    }

    public void showCouponCode() {
        showModal(CouponCodeFragment.newInstance(this.reservation.getCouponCode()));
    }

    public void showPriceBreakdown() {
        showModal(PriceBreakdownFragment.forReservationBooking(this.reservation, this.listing));
    }

    public void showGuestIdentifications() {
        showModal(KonaManageGuestProfilesFragment.forSelectedIdentifications(this.reservationDetails.identifications() != null ? new ArrayList<>(this.reservationDetails.identifications()) : new ArrayList<>(), getReservation().getGuestCount(), false, ""));
    }

    public void showTooltip(String title, String body) {
        showModal(TooltipFragment.newInstance(title, body));
    }

    public void showPaymentOptions() {
        showModal(PaymentInstrumentsFragment.newInstance(this.reservationDetails.isVerifiedBusinessTrip(), ChinaUtils.enableAlipayNonBindingFlow(this, true)));
    }

    public void showArrivalDetails() {
        showModal(ArrivalDetailsFragment.newInstance(this.listing, getReservation(), getReservationDetails().checkInHour()));
    }

    public void showMessageHost() {
        this.shouldSkipMessageHostFragment = true;
        showEditTextModalWithHostMarquee(this.reservationDetails.messageToHost());
    }

    public void showHouseRules() {
        startActivityForResult(ModalActivity.intentForFragment(this, DLSHouseRulesFragment.instanceForBooking(this.reservation)), REQUEST_CODE_HOUSE_RULES);
    }

    public void doneWithGuestIdentifications(List<GuestIdentity> guestIdentifications) {
        this.reservationDetails = this.reservationDetails.toBuilder().identifications(guestIdentifications).build();
        PsbAnalytics.trackManageIdentitiesDoneClick(guestIdentifications.size());
    }

    public void onCalendarDatesApplied(AirDate start, AirDate end) {
        KonaBookingAnalytics.trackClick("calendar_view", "apply_date", getReservationDetails().toBasicAnalyticsStrap(this.mobileSearchSessionId).mix(ParcelStrap.make().mo9946kv("ds_checkin", start.getIsoDateString()).mo9946kv("ds_checkout", end.getIsoDateString())));
        FindTweenAnalytics.trackSaveDates(NavigationTag.P4, start, end);
        this.reservationDetails = this.reservationDetails.toBuilder().checkIn(start).checkOut(end).build();
        KonaBookingAnalytics.trackUpdate(KonaBookingAnalytics.BOOKING_SUMMARY_PAGE, this.reservationDetails, this.mobileSearchSessionId);
        updateReservationDates();
        getSupportFragmentManager().popBackStackImmediate();
    }

    public void onStartDateClicked(AirDate start) {
    }

    public void onEndDateClicked(AirDate end) {
    }

    private void transitionToBooking() {
        showBooking();
        if (!hasReservation()) {
            createReservation();
        } else if (this.businessTravelAccountManager.isVerifiedBusinessTraveler() && shouldShowBusinessTravelInterstitial()) {
            updateReservationBusinessTravelDetails();
        }
        fetchIdentityVerifications();
    }

    public void doneWithGuestPicking(GuestDetails guestDetails) {
        this.reservationDetails = this.reservationDetails.toBuilder().guestDetails(guestDetails).build();
        showNextStep();
    }

    public void doneWithBusinessTravelIdentification(TripType tripType) {
        this.reservationDetails = this.reservationDetails.toBuilder().tripType(tripType).build();
        if (!this.businessTravelAccountManager.isVerifiedBusinessTraveler() && Experiments.isVisibleToNonVU()) {
            transitionToBooking();
        } else if (tripType == TripType.BusinessVerified) {
            showBusinessTravelTripNote();
        } else {
            this.reservationDetails = this.reservationDetails.toBuilder().businessTripNote(null).isBusinessTravelPaymentMethod(null).build();
            transitionToBooking();
        }
    }

    public void doneWithTripNote(String tripNote) {
        this.reservationDetails = this.reservationDetails.toBuilder().businessTripNote(tripNote).build();
        transitionToBooking();
    }

    public void doneWithPaymentSelection(OldPaymentInstrument instrument) {
        getSupportFragmentManager().popBackStackImmediate();
        this.reservationDetails = this.reservationDetails.toBuilder().paymentInstrument(instrument).isBusinessTravelPaymentMethod(Boolean.valueOf(instrument instanceof BusinessTravelPaymentInstruments)).build();
        onReservationUpdate();
    }

    public void doneWithCouponCode(Reservation reservation2) {
        getSupportFragmentManager().popBackStackImmediate();
        KonaBookingAnalytics.trackUpdate(KonaBookingAnalytics.BOOKING_SUMMARY_PAGE, this.reservationDetails, this.mobileSearchSessionId);
        this.reservation = reservation2;
        onReservationUpdate();
    }

    public void doneWithArrivalDetails(String checkInHour) {
        this.reservationDetails = this.reservationDetails.toBuilder().checkInHour(checkInHour).build();
        KonaBookingAnalytics.trackUpdate(KonaBookingAnalytics.BOOKING_SUMMARY_PAGE, this.reservationDetails, this.mobileSearchSessionId);
        updateReservationArrivalDetails();
    }

    public void doneWithMessageHost(String messageToHost) {
        this.reservationDetails = this.reservationDetails.toBuilder().messageToHost(messageToHost).build();
        if (!FeatureToggles.shouldShowFirstMessageSuggestions()) {
            getSupportFragmentManager().popBackStackImmediate();
        }
        KonaBookingAnalytics.trackUpdate(KonaBookingAnalytics.BOOKING_SUMMARY_PAGE, this.reservationDetails, this.mobileSearchSessionId);
    }

    public void doneWithNoResults() {
        getSupportFragmentManager().popBackStackImmediate();
    }

    public void doneWithHouseRules() {
        this.reservationDetails = this.reservationDetails.toBuilder().agreedToHouseRules(Boolean.valueOf(true)).build();
        KonaBookingAnalytics.trackUpdate(KonaBookingAnalytics.BOOKING_SUMMARY_PAGE, this.reservationDetails, this.mobileSearchSessionId);
        onReservationUpdate();
    }

    /* access modifiers changed from: private */
    public void onReservationUpdateError(String errorMessage) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(C0880R.C0882id.modal_container);
        if (fragment instanceof UpdateRequestListener) {
            ((UpdateRequestListener) fragment).onUpdateError(errorMessage);
        } else if (fragment == null) {
            Fragment fragment2 = getSupportFragmentManager().findFragmentById(C0880R.C0882id.content_container);
            if (fragment2 instanceof UpdateRequestListener) {
                ((UpdateRequestListener) fragment2).onUpdateError(errorMessage);
            }
        }
    }

    /* access modifiers changed from: private */
    public UpdateRequestListener getUpdateListenerFargment() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(C0880R.C0882id.modal_container);
        if (fragment instanceof UpdateRequestListener) {
            return (UpdateRequestListener) fragment;
        }
        return null;
    }

    public Reservation getReservation() {
        return this.reservation;
    }

    public Listing getListing() {
        return this.listing;
    }

    public ReservationDetails getReservationDetails() {
        return this.reservationDetails;
    }

    public List<OldPaymentInstrument> getPaymentInstruments() {
        return this.paymentInstruments;
    }

    public PaymentManagerFragment getPaymentManagerFragment() {
        return this.paymentManagerFragment;
    }

    public boolean hasReservation() {
        return this.reservation != null;
    }

    public List<AccountVerification> getIncompleteVerifications() {
        return this.incompleteVerifications;
    }

    private void createReservation() {
        new CreateReservationRequest(this.reservationDetails, ChinaUtils.enableAlipayNonBindingFlow(this, false)).withListener((Observer) this.createReservationListener).execute(this.requestManager);
    }

    public void fetchIdentityVerifications() {
        this.fetchIdentityController.startFetchingIdentityVerificationState(this.accountManager.getCurrentUserId());
    }

    static /* synthetic */ void lambda$new$2(BookingActivity bookingActivity, ReservationResponse data) {
        boolean isMessageHostRequired;
        boolean z = false;
        bookingActivity.reservation = data.reservation;
        bookingActivity.listing = bookingActivity.reservation.getListing();
        if (!Trebuchet.launch(TrebuchetKeys.P4_HIDE_MESSAGE_HOST_EXPERIMENT) || bookingActivity.reservation.isShouldShowFirstMessage()) {
            isMessageHostRequired = true;
        } else {
            isMessageHostRequired = false;
        }
        Builder currency = bookingActivity.reservationDetails.toBuilder().reservationId(Long.valueOf(bookingActivity.reservation.getId())).confirmationCode(bookingActivity.reservation.getConfirmationCode()).checkIn(bookingActivity.reservation.getCheckIn()).checkOut(bookingActivity.reservation.getCheckOut()).totalPrice(Integer.valueOf(bookingActivity.reservation.getPricingQuote().getTotalPrice().getAmount().intValue())).currency(bookingActivity.reservation.getPricingQuote().getTotalPrice().getCurrency());
        if (!bookingActivity.listing.hasHouseRules()) {
            z = true;
        }
        bookingActivity.reservationDetails = currency.agreedToHouseRules(Boolean.valueOf(z)).requiresIdentifications(Boolean.valueOf(bookingActivity.reservation.isGuestIdentificationsRequired())).isCheckInTimeRequired(Boolean.valueOf(bookingActivity.reservation.isCheckInTimeRequired())).messageToHost(bookingActivity.hostMessage).isMessageHostRequired(Boolean.valueOf(isMessageHostRequired)).build();
        KonaBookingAnalytics.trackImpression(bookingActivity.reservation, bookingActivity.reservationDetails, bookingActivity.mobileSearchSessionId, bookingActivity);
        bookingActivity.updatePaymentInstruments(data.paymentInstruments);
        bookingActivity.updateFXCopy(bookingActivity.reservation);
        bookingActivity.startVerificationFlowIfNeeded();
        ChinaUtils.enableAlipayNonBindingFlow(bookingActivity, true);
    }

    static /* synthetic */ void lambda$new$3(BookingActivity bookingActivity, AirRequestNetworkException e) {
        bookingActivity.getBookingSummaryFragment().onReservationError();
        ErrorResponse errorResponse = (ErrorResponse) e.errorResponse();
        String str = errorResponse == null ? bookingActivity.getString(C0880R.string.error_request) : errorResponse.isValidationError() ? errorResponse.errorDetails : errorResponse.errorMessage;
        bookingActivity.onReservationUpdateError(str);
    }

    public void startVerificationFlowIfNeeded() {
        boolean z;
        ReservationDetails build;
        if (getReservation() != null && getIncompleteVerifications() != null) {
            boolean usingIdentityFlow = shouldUseIdentityFlowForFrozenReservation();
            if (shouldReservationBeFrozen()) {
                AccountVerificationAnalytics.trackP4VerifiedID(usingIdentityFlow ? InterpolationAnimatedNode.EXTRAPOLATE_TYPE_IDENTITY : "verified_id", getReservation().getFreezeDetails(), getReservation().getListing().getId());
            }
            if (usingIdentityFlow) {
                boolean isPhoneRegistration = this.accountManager.getCurrentUser().isPhoneNumberRegisteredUser();
                if (this.phoneOrEmailVerification != null && !this.phoneOrEmailVerification.isComplete()) {
                    if (isPhoneRegistration) {
                        build = this.reservationDetails.toBuilder().confirmedEmailAddress(Boolean.valueOf(false)).build();
                    } else {
                        build = this.reservationDetails.toBuilder().confirmedPhoneNumber(Boolean.valueOf(false)).build();
                    }
                    this.reservationDetails = build;
                    onReservationUpdate();
                }
                this.incompleteVerifications = new ArrayList<>(FluentIterable.from((Iterable<E>) this.incompleteVerifications).filter(BookingActivity$$Lambda$5.lambdaFactory$()).toList());
            } else {
                this.incompleteVerifications = new ArrayList<>(FluentIterable.from((Iterable<E>) this.incompleteVerifications).filter(BookingActivity$$Lambda$6.lambdaFactory$()).toList());
            }
            Builder builder = this.reservationDetails.toBuilder();
            if (!this.incompleteVerifications.isEmpty()) {
                z = true;
            } else {
                z = false;
            }
            this.reservationDetails = builder.requiresVerifications(Boolean.valueOf(z)).usesIdentityFlow(Boolean.valueOf(usingIdentityFlow)).build();
            onReservationUpdate();
            if (this.firstVerificationStep != null) {
                this.firstVerificationStep = null;
            } else if (!this.incompleteVerifications.isEmpty() && !replaceVerifiedIdWithIdentityAsBookingStep()) {
                FreezeDetails freezeDetails = getReservation().getFreezeDetails();
                startActivityForResult(AccountVerificationStartActivityIntents.newIntentForIncompleteVerifications(this.identityJitneyLogger, this, AccountVerificationStartFragmentArguments.builder().verificationFlow(freezeDetails.requiredByHost() ? VerificationFlow.VerifiedID : VerificationFlow.Booking).incompleteVerifications(getIncompleteVerifications()).host(getReservation().getPrimaryHost()).verificationUser(this.fetchIdentityController.getVerificationUser()).listingId(getReservation().getListing().getId()).governmentIdResult(this.governmentIdResult).reservationFrozenReason(freezeDetails.getReason()).build()), REQUEST_CODE_VERIFY_IDENTITY);
            } else if (replaceVerifiedIdWithIdentityAsBookingStep()) {
                onVerificationUpdate();
            }
        }
    }

    static /* synthetic */ boolean lambda$startVerificationFlowIfNeeded$4(AccountVerification verification) {
        return !verification.isPhoneOrEmail();
    }

    static /* synthetic */ boolean lambda$startVerificationFlowIfNeeded$5(AccountVerification verification) {
        return !verification.isOnlyRequiredForIdentityFlow();
    }

    private boolean shouldReservationBeFrozen() {
        return (getReservation() == null || getReservation().getFreezeDetails() == null || !getReservation().getFreezeDetails().isShouldBeFrozen()) ? false : true;
    }

    public boolean shouldUseIdentityFlowForFrozenReservation() {
        return shouldReservationBeFrozen() && FeatureToggles.replaceVerifiedIdWithIdentity(this.fetchIdentityController.getVerificationUser());
    }

    public boolean replaceVerifiedIdWithIdentityAsBookingStep() {
        return shouldUseIdentityFlowForFrozenReservation() && FeatureToggles.replaceVerifiedIdWithIdentityOnP4();
    }

    private void updatePaymentInstruments(List<OldPaymentInstrument> instruments) {
        this.paymentInstruments = new ArrayList<>(instruments);
        if (!this.paymentInstruments.isEmpty()) {
            this.reservationDetails = this.reservationDetails.toBuilder().paymentInstrument((OldPaymentInstrument) this.paymentInstruments.get(0)).build();
        }
        addOtherPaymentIfEligible(this.paymentInstruments);
        addAndroidPayIfEligible(this.paymentInstruments);
        onReservationUpdate();
    }

    private void updateFXCopy(Reservation reservation2) {
        Optional<PaymentOption> defaultPaymentOption = FluentIterable.from((Iterable<E>) reservation2.getPaymentOptions()).first();
        if (defaultPaymentOption.isPresent() && ((PaymentOption) defaultPaymentOption.get()).shouldDisplayFXCopy()) {
            this.reservationDetails = this.reservationDetails.toBuilder().fxCopy(((PaymentOption) defaultPaymentOption.get()).getFXCopy(this, reservation2.getPricingQuote().getTotalPrice().formattedForDisplay())).build();
            onReservationUpdate();
        }
    }

    /* access modifiers changed from: private */
    public void updateReservationGuestDetails(ReservationDetails newReservationDetails) {
        new UpdateGuestDetailsRequest(newReservationDetails).withListener((Observer) this.updateReservationListener).execute(this.requestManager);
    }

    public void updateReservation() {
        new UpdateGuestDetailsRequest(this.reservationDetails).withListener((Observer) this.updateReservationListener).execute(this.requestManager);
    }

    private void updateReservationDates() {
        new UpdateDatesRequest(this.reservationDetails).withListener((Observer) this.updateReservationListener).execute(this.requestManager);
    }

    private void updateReservationArrivalDetails() {
        new UpdateArrivalDetailsRequest(this.reservationDetails).withListener((Observer) this.updateReservationListener).execute(this.requestManager);
    }

    private void updateReservationBusinessTravelDetails() {
        if (!this.reservationDetails.isVerifiedBusinessTrip() && (this.reservationDetails.paymentInstrument() instanceof BusinessTravelPaymentInstruments)) {
            this.reservationDetails = this.reservationDetails.toBuilder().paymentInstrument(null).build();
        }
        UpdateBusinessTravelDetailRequest.forReservationDetails(this.reservationDetails).withListener((Observer) this.updateReservationListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public BookingSummaryFragment getBookingSummaryFragment() {
        return (BookingSummaryFragment) getSupportFragmentManager().findFragmentByTag(BookingSummaryFragment.TAG);
    }

    /* access modifiers changed from: private */
    public void onReservationUpdate() {
        if (isBookingSummaryFragmentVisible()) {
            getBookingSummaryFragment().onReservationUpdate();
        }
    }

    /* access modifiers changed from: private */
    public void onVerificationUpdate() {
        if (isBookingSummaryFragmentVisible()) {
            getBookingSummaryFragment().onVerificationUpdate();
        }
    }

    private boolean isBookingSummaryFragmentVisible() {
        return getBookingSummaryFragment() == getSupportFragmentManager().findFragmentById(C0880R.C0882id.content_container);
    }

    private PaymentManagerFragment initializePaymentManagerFragment() {
        this.paymentManagerFragment = PaymentManagerFragment.newInstance(this, null);
        this.paymentManagerFragment.setPaymentManagerListener(this.paymentManagerListener);
        return this.paymentManagerFragment;
    }

    /* access modifiers changed from: private */
    public void handleAndroidPayError() {
        displayError(getString(C0880R.string.error), getString(C0880R.string.error_paying_with_android_pay), getString(C0880R.string.error_action_paying_with_android_pay), BookingActivity$$Lambda$7.lambdaFactory$(this));
    }

    private void addOtherPaymentIfEligible(List<OldPaymentInstrument> instruments) {
        for (OldPaymentInstrument instrument : instruments) {
            if (instrument instanceof OtherPaymentInstrument) {
                return;
            }
        }
        if (PaymentUtils.isOtherPaymentEligible()) {
            instruments.add(new OtherPaymentInstrument());
        }
    }

    /* access modifiers changed from: private */
    public void addAndroidPayIfEligible(List<OldPaymentInstrument> instruments) {
        for (OldPaymentInstrument instrument : instruments) {
            if (instrument instanceof AndroidPayInstrument) {
                return;
            }
        }
        if (this.paymentManagerFragment.isAndroidPayEnabled() && Experiments.isAndroidPayEnabled()) {
            instruments.add(new AndroidPayInstrument());
        }
    }

    private boolean isVerifiedIdRequiredFromOtherPaymentWebView(Intent data) {
        return data.getBooleanExtra("verified_id", false);
    }

    public void bookReservation() {
        if ((this.reservationDetails.paymentInstrument() instanceof AlipayPaymentInstrument) && !Experiments.isAlipayDirectEnabled()) {
            startActivityForResult(PayWithAlipayActivity.intentForReservation(this, this.reservation), REQUEST_CODE_PAY_WITH_ALIPAY);
        } else if (this.reservationDetails.paymentInstrument() instanceof OtherPaymentInstrument) {
            startActivityForResult(BookingWebViewActivity.forConfirmationCode(this, this.reservation.getConfirmationCode(), this.reservationDetails.messageToHost()), 991);
        } else if (this.reservationDetails.paymentInstrument() instanceof AndroidPayInstrument) {
            this.paymentManagerFragment.loadFullAndroidPayWallet(this.reservationDetails.totalPrice().intValue(), this.reservationDetails.currency());
        } else {
            getBookingSummaryFragment().submitPaymentRequestForReservation(this.reservationDetails);
        }
    }

    public void contactHost() {
        showFragment(ContactHostFragment.newInstance(this.listing), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }

    public void completeBooking(Reservation completedReservation) {
        this.reservation = completedReservation;
        if (!this.reservation.isCheckpointed() || shouldUseIdentityFlowForFrozenReservation()) {
            startActivity(PostBookingActivityIntents.intentForReservation(this, this.reservation.getReservation()));
            finish();
            return;
        }
        startActivity(VerifiedIdActivityIntents.intentForVerifiedId(this, null, this.reservation.getReservation()));
        finish();
    }

    public void handleBookingError(AirRequestNetworkException e) {
        String errorMessage = NetworkUtil.errorMessage(e);
        clearNonceIfCreditCard();
        if (!this.airlockErrorHandler.isAirlockError(e)) {
            if (isCurrencyMismatchError(e)) {
                handleCurrencyMismatch(errorMessage);
            } else if (isPostalCodeMismatchError(e)) {
                handlePostalCodeMismatch(errorMessage);
            } else if (isPaypalBookingError(e)) {
                handleNewPaypalBookingError(errorMessage);
            } else {
                if (TextUtils.isEmpty(errorMessage)) {
                    errorMessage = getString(C0880R.string.error_request);
                }
                displayError(getString(C0880R.string.p4_error_booking), errorMessage, null, null);
            }
        }
        trackBookingFailure(e);
    }

    public void handleAlipayError() {
        displayError(getString(C0880R.string.p4_error_booking), getString(C0880R.string.error_alipay), null, null);
    }

    private void trackBookingFailure(NetworkException e) {
        long j = -1;
        Strap eventData = Strap.make().mo11639kv("failure_reasons", NetworkUtil.errorDetails(e)).mo11639kv(ErrorResponse.ERROR_ID, NetworkUtil.errorId(e)).mo11639kv("error_code", NetworkUtil.errorCode(e) == null ? "null" : NetworkUtil.errorCode(e).toString()).mo11639kv("error_message", NetworkUtil.errorMessage(e));
        if (this.reservationDetails != null) {
            eventData.mo11639kv("payment_type", this.reservationDetails.paymentInstrument() == null ? "null" : this.reservationDetails.paymentInstrument().getName()).mo11639kv("confirmation_code", this.reservationDetails.confirmationCode());
        }
        if (this.reservation != null) {
            Strap kv = eventData.mo11638kv("reservation_id", this.reservation.getId()).mo11638kv("listing_id", this.reservation.getListing() == null ? -1 : this.reservation.getListing().getId()).mo11638kv("guest_id", this.reservation.getGuest() == null ? -1 : this.reservation.getGuest().getId());
            String str = "hosting_id";
            if (this.reservation.getHost() != null) {
                j = this.reservation.getHost().getId();
            }
            kv.mo11638kv(str, j).mo11640kv("is_instant_book", this.reservation.isInstantBooked()).mo11639kv("checkin_date", this.reservation.getStartDate() == null ? "null" : this.reservation.getStartDate().getIsoDateString()).mo11639kv("checkout_date", this.reservation.getEndDate() == null ? "null" : this.reservation.getEndDate().getIsoDateString()).mo11637kv("nights", this.reservation.getReservedNightsCount()).mo11637kv(FindTweenAnalytics.GUESTS, this.reservation.getGuestCount());
        }
        AirbnbEventLogger.track(AIREVENT_BOOKING_FAILED, eventData);
    }

    private void clearNonceIfCreditCard() {
        OldPaymentInstrument instrument = getReservationDetails().paymentInstrument();
        if (instrument instanceof BraintreeCreditCard) {
            ((BraintreePaymentInstrument) instrument).setNonce(null);
        }
    }

    private boolean isPostalCodeMismatchError(NetworkException e) {
        ErrorResponse errorResponse = (ErrorResponse) e.errorResponse();
        return errorResponse != null && ERROR_TYPE_POSTAL_CODE_MISMATCH.equals(errorResponse.errorDetails);
    }

    private void handlePostalCodeMismatch(String errorMessage) {
        displayError(getString(C0880R.string.error_title_postal_code_mismatch), errorMessage, getString(C0880R.string.error_action_postal_code_mismatch), BookingActivity$$Lambda$8.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public void updatePostalCode() {
        startActivityForResult(CreditCardActivity.intentForPostalCodeRetry(this, this.reservationDetails.toBasicAnalyticsStrap(this.mobileSearchSessionId)), CreditCardActivity.REQUEST_CODE_POSTAL_RETRY);
    }

    private void handleUpdatedPostalCode(String updatedPostalCode) {
        BraintreeCreditCard creditCard = (BraintreeCreditCard) this.reservationDetails.paymentInstrument();
        if (creditCard.hasValidId()) {
            creditCard.setUpdatedPostalCode(updatedPostalCode);
        } else {
            creditCard.setPostalCode(updatedPostalCode);
        }
        getBookingSummaryFragment().submitPaymentRequestForReservation(this.reservationDetails);
    }

    private boolean isCurrencyMismatchError(NetworkException e) {
        ErrorResponse errorResponse = (ErrorResponse) e.errorResponse();
        return errorResponse != null && ERROR_TYPE_CURRENCY_MISMATCH.equals(errorResponse.error);
    }

    private void handleCurrencyMismatch(String errorMessage) {
        displayError(getString(C0880R.string.p4_error_title_currency_update), errorMessage, getString(C0880R.string.p4_error_action_currency_update), BookingActivity$$Lambda$9.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public void agreeToCurrencyUpdate() {
        this.reservationDetails.paymentInstrument().setCurrencyMismatchApproved(true);
        bookReservation();
    }

    private boolean isPaypalBookingError(NetworkException e) {
        OldPaymentInstrument instrument = this.reservationDetails.paymentInstrument();
        return e.hasErrorResponse() && (instrument instanceof PayPalInstrument) && !instrument.hasValidId();
    }

    private void handleNewPaypalBookingError(String errorMessage) {
        this.paymentInstruments.remove(this.reservationDetails.paymentInstrument());
        this.reservationDetails = this.reservationDetails.toBuilder().paymentInstrument(null).build();
        displayError(getString(C0880R.string.p4_error_title_paypal), errorMessage, getString(C0880R.string.p4_add_payment), BookingActivity$$Lambda$10.lambdaFactory$(this));
    }

    private void handleNewAlipayBookingError(String errorMessage) {
        displayError(getString(C0880R.string.p4_error_title_alipay), errorMessage, getString(C0880R.string.p4_add_payment), BookingActivity$$Lambda$11.lambdaFactory$(this));
    }

    public void displayMessage(String body) {
        displaySnackbar(null, body, false, null, null);
    }

    /* access modifiers changed from: private */
    public void displayError(String title, String body, String action, OnClickListener actionListener) {
        displaySnackbar(title, body, true, action, actionListener);
    }

    private void displaySnackbar(String title, String body, boolean isError, String action, OnClickListener actionListener) {
        SnackbarWrapper snackbarWrapper = new SnackbarWrapper();
        snackbarWrapper.view(findViewById(16908290)).title(title, isError).body(body);
        if (action != null) {
            snackbarWrapper.action(action, actionListener);
        } else {
            snackbarWrapper.duration(0);
        }
        snackbarWrapper.buildAndShow();
    }

    private void showModal(Fragment fragment) {
        showModal(fragment, C0880R.C0882id.content_container, C0880R.C0882id.modal_container, true, fragment.getClass().getSimpleName());
    }

    /* access modifiers changed from: private */
    public GuestDetails buildGuestDetails() {
        return new GuestDetails().adultsCount(this.reservationDetails.numberOfAdults().intValue()).childrenCount(this.reservationDetails.numberOfChildren().intValue()).infantsCount(this.reservationDetails.numberOfInfants().intValue()).isBringingPets(this.reservationDetails.isBringingPets().booleanValue());
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    public String getMobileSearchSessionId() {
        return this.mobileSearchSessionId;
    }

    public EditTextFragmentListener getEditTextFragmentListener() {
        return this.editTextFragmentListener;
    }

    public GuestPickerController getGuestPickerController() {
        return this.guestPickerController;
    }

    public ContactHostFragmentController getContactHostFragmentController() {
        return this.contactHostFragmentController;
    }

    /* access modifiers changed from: private */
    public void showEditTextModalWithHostMarquee(String message) {
        if (FeatureToggles.shouldShowFirstMessageSuggestions()) {
            ContactHostHelper.launchRNContactHostForBooking(this, getReservationDetails(), getReservation(), message);
            return;
        }
        showModal(new EditTextFragmentBuilder().setText(message).setHeaderTitle(getString(C0880R.string.contact_host_prompt_title)).setHeaderSubtitle(getString(C0880R.string.contact_host_prompt_subtitle, new Object[]{this.listing.getPrimaryHost().getFirstName()})).showHeader(true).setUser(this.listing.getPrimaryHost()).setHint(getString(C0880R.string.p4_write_a_message_hint)).build());
    }

    public FetchIdentityController getIdentityController() {
        return this.fetchIdentityController;
    }

    public void setProvidedGovernmentId(boolean provided) {
        this.reservationDetails = this.reservationDetails.toBuilder().providedGovernmentId(Boolean.valueOf(provided)).build();
    }

    public void setGovernmentIdResult(GovernmentIdResult governmentIdResult2) {
        this.governmentIdResult = governmentIdResult2;
    }

    public GovernmentIdResult getGovernmentIdResult() {
        Status status = this.governmentIdResult == null ? null : this.governmentIdResult.getStatusFromString();
        List<AccountVerification> incompleteVerifications2 = this.fetchIdentityController.getIncompleteVerificationList(VerificationFlow.FinalizeBooking);
        if (incompleteVerifications2 == null) {
            return this.governmentIdResult;
        }
        AccountVerification offlineIdVerification = (AccountVerification) FluentIterable.from((Iterable<E>) incompleteVerifications2).firstMatch(BookingActivity$$Lambda$12.lambdaFactory$()).orNull();
        if (status != Status.Approved || offlineIdVerification == null) {
            return this.governmentIdResult;
        }
        return null;
    }
}
