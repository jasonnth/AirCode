package com.airbnb.android.p011p3;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.support.p000v4.app.ActivityOptionsCompat;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.view.ViewCompat;
import android.transition.Slide;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.apprater.AppRaterController;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.ScreenshotManager;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.activities.AutoAirActivity;
import com.airbnb.android.core.analytics.BookItAnalytics;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.airbnb.android.core.analytics.PerformanceLogger;
import com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments;
import com.airbnb.android.core.arguments.Arguments;
import com.airbnb.android.core.arguments.P3Arguments;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.controllers.CalendarViewCallbacks;
import com.airbnb.android.core.controllers.ContactHostControllerProvider;
import com.airbnb.android.core.controllers.ContactHostFragmentController;
import com.airbnb.android.core.controllers.GoogleAppIndexingController;
import com.airbnb.android.core.enums.CancellationPolicyLabel;
import com.airbnb.android.core.enums.ContactHostAction;
import com.airbnb.android.core.enums.FetchPricingInteractionType;
import com.airbnb.android.core.enums.FlagContent;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.events.P3ImpressionDurationEvent;
import com.airbnb.android.core.fragments.ContactHostFragment;
import com.airbnb.android.core.fragments.DLSCancellationPolicyFragment;
import com.airbnb.android.core.fragments.DLSHouseRulesFragment;
import com.airbnb.android.core.fragments.EditTextFragment.EditTextFragmentBuilder;
import com.airbnb.android.core.fragments.EditTextFragment.EditTextFragmentController;
import com.airbnb.android.core.fragments.EditTextFragment.EditTextFragmentListener;
import com.airbnb.android.core.fragments.LocalAttractionsFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.fragments.datepicker.DatesFragment;
import com.airbnb.android.core.fragments.guestpicker.GuestPickerFragment.GuestPickerController;
import com.airbnb.android.core.fragments.guestpicker.GuestPickerFragment.GuestPickerControllerProvider;
import com.airbnb.android.core.fragments.guestpicker.GuestPickerFragment.GuestPickerFragmentBuilder;
import com.airbnb.android.core.identity.FetchIdentityController;
import com.airbnb.android.core.identity.FetchIdentityController.MultiVerificationFlowListener;
import com.airbnb.android.core.intents.AccountVerificationStartActivityIntents;
import com.airbnb.android.core.intents.P3ActivityIntents;
import com.airbnb.android.core.intents.PlacesIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.intents.ReferralsIntents;
import com.airbnb.android.core.intents.SearchActivityIntents;
import com.airbnb.android.core.intents.UserProfileIntents;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.interfaces.UpdateRequestListener;
import com.airbnb.android.core.localpushnotifications.LocalPushNotificationManager;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.MessageThreadV2;
import com.airbnb.android.core.models.Photo;
import com.airbnb.android.core.models.PostV2;
import com.airbnb.android.core.models.PricingQuote;
import com.airbnb.android.core.models.ReferralStatusForMobile;
import com.airbnb.android.core.models.SimilarListing;
import com.airbnb.android.core.models.UrgencyMessageData;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.UserFlag;
import com.airbnb.android.core.p009p3.P3State;
import com.airbnb.android.core.p009p3.interfaces.ContactHostDataChangeListener;
import com.airbnb.android.core.p009p3.interfaces.OnP3DataChangedListener;
import com.airbnb.android.core.requests.CreateInquiryRequest;
import com.airbnb.android.core.requests.GetUserFlagRequest;
import com.airbnb.android.core.requests.ListingDescriptionTranslateRequest;
import com.airbnb.android.core.requests.LocalAttractionsRequest;
import com.airbnb.android.core.requests.P3ListingRequest;
import com.airbnb.android.core.requests.PricingQuoteRequest;
import com.airbnb.android.core.requests.SimilarListingsRequest;
import com.airbnb.android.core.responses.CreateInquiryResponse;
import com.airbnb.android.core.responses.ListingDescriptionTranslateResponse;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.responses.LocalAttractionsResponse;
import com.airbnb.android.core.responses.PricingQuoteResponse;
import com.airbnb.android.core.responses.SimilarListingsResponse;
import com.airbnb.android.core.responses.UserFlagResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.lib.fragments.verifiedid.OfficialIdPhotoSelectionFragment;
import com.airbnb.android.p011p3.AccountVerificationContactHostFragment.ProvideIdClickListener;
import com.airbnb.android.p011p3.P3Component.Builder;
import com.airbnb.android.p011p3.models.Floor;
import com.airbnb.android.p011p3.models.HomeLayout;
import com.airbnb.android.p011p3.models.Room;
import com.airbnb.android.p011p3.models.RoomDetail;
import com.airbnb.android.p011p3.models.RoomPhoto;
import com.airbnb.android.p011p3.requests.ListingDetailsRequest;
import com.airbnb.android.p011p3.requests.ListingDetailsResponse;
import com.airbnb.android.utils.AndroidVersion;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.erf.Experiments;
import com.airbnb.jitney.event.logging.NativeMeasurementType.p159v1.C2445NativeMeasurementType;
import com.airbnb.p027n2.primitives.imaging.AirImageView;
import com.airbnb.p027n2.primitives.imaging.ImageSize;
import com.airbnb.p027n2.transitions.AutoSharedElementCallback;
import com.airbnb.p027n2.transitions.AutoSharedElementCallback.AutoSharedElementCallbackDelegate;
import com.airbnb.p027n2.transitions.TransitionName;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.facebook.internal.AnalyticsEvents;
import com.google.common.base.Stopwatch;
import icepick.State;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import p032rx.Observer;

/* renamed from: com.airbnb.android.p3.P3Activity */
public class P3Activity extends AirActivity implements CalendarViewCallbacks, ContactHostControllerProvider, EditTextFragmentController, GuestPickerControllerProvider, MultiVerificationFlowListener, ProvideIdClickListener, ListingDetailsController {
    private static final String CONTACT_HOST_FRAG_TAG = "fragment_contact_host";
    private static final String DATES_FRAG_TAG = "fragment_dates";
    private static final String GUESTS_FRAG_TAG = "fragment_guests";
    private static final String MESSAGE_FRAG_TAG = "fragment_message";
    private static final String P3_FRAGMENT_TAG = "fragment_p3";
    private static final String P3_PRICING_QUOTE_REQUEST_INTENT = "p3_book_it";
    private static final String PROVIDE_ID_FRAG_TAG = "fragment_provide_id";
    private static final int RC_CODE_VERIFICATIONS = 9001;
    private static final int RC_CONTACT_HOST_PROVIDE_ID = 763;
    private static final int RC_CONTACT_HOST_VERIFICATION = 762;
    private static final int RC_FLAG_LISTING = 766;
    private static final int RC_PROPOGATE_GUEST_CANCEL = 764;
    private static final int RC_PROPOGATE_GUEST_CONFIRM = 765;
    private static final String REVIEW_SEARCH_TAG = "fragment_review_search";
    private static final String TIME_TO_INERACTIVE = "p3_tti";
    AppRaterController appRaterController;
    BusinessTravelAccountManager businessTravelAccountManager;
    private final Messenger carouselPositionMessenger = new Messenger(new CarouselPositionHandler(this));
    /* access modifiers changed from: private */
    public final Set<OnP3DataChangedListener> changeListeners = new HashSet();
    /* access modifiers changed from: private */
    public final Set<ContactHostDataChangeListener> contactHostChangeListeners = new HashSet();
    private final ContactHostFragmentController contactHostFragmentController = new ContactHostFragmentController() {
        public String getInquiryMessage() {
            return P3Activity.this.getState().inquiryMessage();
        }

        public AirDate getCheckInDate() {
            return P3Activity.this.getState().checkInDate();
        }

        public AirDate getCheckOutDate() {
            return P3Activity.this.getState().checkOutDate();
        }

        public GuestDetails getGuestDetails() {
            return P3Activity.this.getState().guestDetails();
        }

        public void onDatesUpdateRequested() {
            P3Activity.this.onItemClick(6);
        }

        public void onGuestsUpdateRequested() {
            P3Activity.this.onItemClick(7);
        }

        public void onComposeMessageRequested() {
            P3Activity.this.onItemClick(8);
        }

        public void onSubmitToHost() {
            P3Activity.this.onItemClick(9);
        }

        public void registerListener(ContactHostDataChangeListener listener) {
            P3Activity.this.contactHostChangeListeners.add(listener);
        }

        public void unregisterListener(ContactHostDataChangeListener listener) {
            P3Activity.this.contactHostChangeListeners.remove(listener);
        }
    };
    @BindView
    FrameLayout contentContainer;
    @State
    boolean dismissDatePickerOnResume;
    private FetchIdentityController fetchIdentityController;
    GoogleAppIndexingController googleAppIndexingController;
    private final GuestPickerController guestPickerController = new GuestPickerController() {
        public void onGuestDetailsSaved(GuestDetails guestData, UpdateRequestListener listener) {
            P3Activity.this.state = P3Activity.this.state.toBuilder().guestDetails(guestData).build();
            FindTweenAnalytics.trackSaveGuests(NavigationTag.P3, guestData);
            P3Activity.this.p3Analytics.trackContactHostAddGuestsClick(guestData);
            P3Activity.this.fetchPricingQuote(FetchPricingInteractionType.GuestChanged);
            P3Activity.this.fetchSimilarListings();
            P3Activity.this.removeFragmentAndPopBackstack(P3Activity.GUESTS_FRAG_TAG);
            P3Activity.this.notifyContactHostStateChanged(ContactHostAction.GUESTS);
        }

        public NavigationTag getNavigationAnalyticsTag() {
            return NavigationTag.FindGuestSheet;
        }
    };
    @State
    boolean hasLoadedNonCriticalData;
    private boolean hasLoggedTimeToInteractive;
    private final EditTextFragmentListener hostMessageFragmentController = new EditTextFragmentListener() {
        public void onMessageSaved(String text) {
            P3Activity.this.state = P3Activity.this.state.toBuilder().inquiryMessage(text).build();
            P3Activity.this.p3Analytics.trackContactHostAddMessageClick();
            P3Activity.this.removeFragmentAndPopBackstack(P3Activity.MESSAGE_FRAG_TAG);
            P3Activity.this.notifyContactHostStateChanged(ContactHostAction.MESSAGE);
        }
    };
    IdentityJitneyLogger identityJitneyLogger;
    private Stopwatch impressionStopWatch;
    @State
    public HashMap<VerificationFlow, ArrayList<AccountVerification>> incompleteVerifications;
    @State
    P3State initialState;
    final RequestListener<CreateInquiryResponse> inquiryRequestListener = new C0699RL().onResponse(P3Activity$$Lambda$9.lambdaFactory$(this)).onError(P3Activity$$Lambda$10.lambdaFactory$(this)).build();
    @State
    boolean launchP4OnDatesPickerResult;
    final RequestListener<ListingDetailsResponse> listingDetailsRequestListener = new C0699RL().onResponse(P3Activity$$Lambda$1.lambdaFactory$()).onError(P3Activity$$Lambda$2.lambdaFactory$()).build();
    final RequestListener<ListingDescriptionTranslateResponse> listingDetailsTranslateResponse = new C0699RL().onResponse(P3Activity$$Lambda$12.lambdaFactory$(this)).onError(P3Activity$$Lambda$13.lambdaFactory$(this)).build();
    private final DataLoadedStatus listingLoaded = new DataLoadedStatus();
    final RequestListener<ListingResponse> listingResponseRequestListener = new C0699RL().onResponse(P3Activity$$Lambda$4.lambdaFactory$(this)).onError(P3Activity$$Lambda$5.lambdaFactory$(this)).build();
    @BindView
    LoaderFrame loaderFrame;
    final RequestListener<LocalAttractionsResponse> localAttractionsRequestListener = new C0699RL().onResponse(P3Activity$$Lambda$11.lambdaFactory$(this)).build();
    LocalPushNotificationManager localPushNotificationManager;
    LoggingContextFactory loggingContextFactory;
    @State
    Integer numLocalAttractions;
    /* access modifiers changed from: private */
    public P3Analytics p3Analytics;
    PerformanceLogger performanceLogger;
    private final DataLoadedStatus pricingQuoteLoaded = new DataLoadedStatus();
    final RequestListener<PricingQuoteResponse> pricingQuotesRequestListener = new C0699RL().onResponse(P3Activity$$Lambda$7.lambdaFactory$(this)).onError(P3Activity$$Lambda$8.lambdaFactory$(this)).build();
    private ReviewsController reviewsController;
    @BindView
    ViewGroup rootView;
    private ScreenshotManager screenshotManager;
    final RequestListener<SimilarListingsResponse> similarListingsListener = new C0699RL().onResponse(P3Activity$$Lambda$3.lambdaFactory$(this)).build();
    @State
    public P3State state;
    final RequestListener<UserFlagResponse> userFlagResponseRequestListener = new C0699RL().onResponse(P3Activity$$Lambda$6.lambdaFactory$(this)).build();
    @State
    VerificationFlow verificationFlow;

    /* renamed from: com.airbnb.android.p3.P3Activity$CarouselPositionHandler */
    private static final class CarouselPositionHandler extends Handler {
        private final WeakReference<P3Activity> activity;

        CarouselPositionHandler(P3Activity activity2) {
            this.activity = new WeakReference<>(activity2);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    P3Activity activity2 = (P3Activity) this.activity.get();
                    if (activity2 != null) {
                        for (OnP3DataChangedListener listener : activity2.changeListeners) {
                            listener.onPicturePositionChanged(msg.arg1);
                        }
                        return;
                    }
                    return;
                default:
                    throw new IllegalArgumentException("Unknown message " + msg.what);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        long startTimeMs = System.currentTimeMillis();
        super.onCreate(savedInstanceState);
        this.impressionStopWatch = Stopwatch.createUnstarted();
        ((Builder) ((P3Bindings) CoreApplication.instance(this).componentProvider()).p3ComponentProvider().get()).build().inject(this);
        setContentView(C7532R.layout.activity_p3);
        ButterKnife.bind((Activity) this);
        if (savedInstanceState == null) {
            P3Arguments arguments = null;
            Intent intent = getIntent();
            if (intent.getExtras() != null) {
                arguments = (P3Arguments) Arguments.fromIntent(P3Arguments.class, intent);
            }
            if (arguments == null) {
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("Failed to parse activity arguments. Probably a malformed deep link URL. Ignoring and closing the activity."));
                finish();
                return;
            } else if (!arguments.hasValidId()) {
                P3ErrorDialog.newInstance().show(getSupportFragmentManager(), (String) null);
                return;
            } else {
                this.state = P3State.fromArguments(arguments);
                this.initialState = this.state;
                transitionTo(P3Fragment.newInstance(), false, false, P3_FRAGMENT_TAG);
                setupTransitionIfNeeded();
                this.appRaterController.incrementSignificantEvent();
            }
        }
        this.performanceLogger.markStart(TIME_TO_INERACTIVE, null, Long.valueOf(startTimeMs));
        this.p3Analytics = new P3Analytics(this, this.loggingContextFactory, savedInstanceState);
        this.reviewsController = new ReviewsController(this, this.requestManager, savedInstanceState);
        this.screenshotManager = new ScreenshotManager(this);
        this.fetchIdentityController = new FetchIdentityController(this.requestManager, (MultiVerificationFlowListener) this, new VerificationFlow[]{VerificationFlow.ContactHost, VerificationFlow.MobileHandOff}, savedInstanceState);
        if (savedInstanceState == null) {
            loadCriticalData();
        }
    }

    @SuppressLint({"NewApi"})
    private void setupTransitionIfNeeded() {
        if (AndroidVersion.isAtLeastLollipop()) {
            setupTransition();
        }
    }

    @TargetApi(21)
    private void setupTransition() {
        setEnterSharedElementCallback(new AutoSharedElementCallback(this, TransitionName.create("listing", this.state.listingId(), AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO), TransitionName.create("listing", this.state.listingId(), "wishListHeart")).setDelegate(new AutoSharedElementCallbackDelegate() {
            public void onPostMapSharedElements(List<String> list, Map<String, View> sharedElements) {
                if (!P3Activity.this.doesPhotoSharedElementExist(sharedElements)) {
                    sharedElements.clear();
                    P3Activity.this.getWindow().setReturnTransition(new Slide(5));
                }
            }
        }));
        setExitSharedElementCallback(new AutoSharedElementCallback(this));
        getWindow().setEnterTransition(new P3Transition().addTarget(this.rootView));
        getWindow().setReturnTransition(new P3Transition().addTarget(this.rootView));
    }

    private void loadCriticalData() {
        P3ListingRequest.forP3(this.state).withListener((Observer) this.listingResponseRequestListener).doubleResponse().execute(this.requestManager);
        new ListingDetailsRequest(this.state.listingId()).withListener((Observer) this.listingDetailsRequestListener).doubleResponse().execute(this.requestManager);
        fetchPricingQuote(FetchPricingInteractionType.Pageload);
    }

    private void loadNonCriticalDataIfNeeded() {
        Check.notNull(this.state.listing());
        if (!this.hasLoadedNonCriticalData) {
            this.hasLoadedNonCriticalData = true;
            fetchSimilarListings();
            this.reviewsController.fetchFirstPageIfNeeded();
            this.businessTravelAccountManager.fetchBusinessTravelEmployeeInfo();
        }
    }

    static /* synthetic */ void lambda$new$1(AirRequestNetworkException e) {
    }

    /* access modifiers changed from: private */
    public boolean doesPhotoSharedElementExist(Map<String, View> sharedElements) {
        TransitionName photoTn = TransitionName.create("listing", this.state.listingId(), AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO);
        for (String mappedName : sharedElements.keySet()) {
            if (sharedElements.get(mappedName) != null && photoTn.partialEquals(TransitionName.parse(mappedName))) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (!this.impressionStopWatch.isRunning()) {
            this.impressionStopWatch.start();
        }
        if (this.dismissDatePickerOnResume) {
            removeFragmentAndPopBackstack(DATES_FRAG_TAG);
            this.dismissDatePickerOnResume = false;
        }
        this.screenshotManager.registerForListing(this.state.listing());
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.impressionStopWatch.isRunning()) {
            this.impressionStopWatch.stop();
        }
        this.screenshotManager.unregister();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 9001) {
            for (OnP3DataChangedListener listener : this.changeListeners) {
                listener.onIdentityForBookingCompleted();
            }
        } else if (resultCode == -1) {
            switch (requestCode) {
                case RC_CONTACT_HOST_VERIFICATION /*762*/:
                    this.p3Analytics.trackContactHostImpression();
                    launchContactHostFragment();
                    return;
                case RC_PROPOGATE_GUEST_CANCEL /*764*/:
                    supportFinishAfterTransition();
                    return;
                case 765:
                    Intent intent = new Intent();
                    intent.putExtra(P3ActivityIntents.CHECK_IN_DATE_KEY, getState().checkInDate());
                    intent.putExtra(P3ActivityIntents.CHECK_OUT_DATE_KEY, getState().checkOutDate());
                    setResult(-1, intent);
                    supportFinishAfterTransition();
                    return;
                case 766:
                    fetchReportedListingStatus();
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        Listing listing = this.state.listing();
        if (listing != null) {
            listing.trimPhotos(200);
        }
        super.onSaveInstanceState(outState);
        this.fetchIdentityController.onSaveInstanceState(outState);
        this.p3Analytics.onSaveInstanceState(outState);
        this.reviewsController.onSaveInstanceState(outState);
    }

    public void onActivityReenter(int resultCode, Intent data) {
        scheduleStartPostponedTransition();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.state != null && isFinishing()) {
            this.bus.post(new P3ImpressionDurationEvent(this.state.listingId(), this.impressionStopWatch.elapsed(TimeUnit.MILLISECONDS)));
        }
        super.onDestroy();
    }

    private void fetchReportedListingStatus() {
        setIsFetchingReportedListingStatus(true);
        new GetUserFlagRequest(FlagContent.Listing, this.state.listingId(), this.accountManager.getCurrentUserId()).withListener((Observer) this.userFlagResponseRequestListener).doubleResponse().execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void fetchPricingQuote(FetchPricingInteractionType interactionType) {
        new PricingQuoteRequest(this.state.listingId(), this.state.checkInDate(), this.state.checkOutDate(), this.state.guestDetails(), interactionType, this.p3Analytics.getImpressionId(), P3_PRICING_QUOTE_REQUEST_INTENT).withListener((Observer) this.pricingQuotesRequestListener).doubleResponse().execute(this.requestManager);
    }

    private void setIsFetchingReportedListingStatus(boolean isFetchingReportedListingStatus) {
        this.state = this.state.toBuilder().isFetchingReportedListingStatus(isFetchingReportedListingStatus).build();
    }

    /* access modifiers changed from: private */
    public void fetchSimilarListings() {
        SimilarListingsRequest.forP3(this.state.checkInDate(), this.state.checkOutDate(), this.state.guestDetails(), this.state.listingId()).withListener((Observer) this.similarListingsListener).doubleResponse().execute(this.requestManager);
        setSimilarListings(Collections.emptyList());
    }

    private void setSimilarListings(List<SimilarListing> listings) {
        if (!this.state.similarListings().equals(listings)) {
            this.state = this.state.toBuilder().similarListings(listings).build();
            notifyStateChange();
        }
    }

    static /* synthetic */ void lambda$new$2(P3Activity p3Activity, SimilarListingsResponse response) {
        for (SimilarListing similarListing : response.similarListings) {
            similarListing.getListing().trimForHomeCard();
        }
        p3Activity.setSimilarListings(response.similarListings);
    }

    private void transitionTo(Fragment newFragment) {
        transitionTo(newFragment, null);
    }

    private void transitionTo(Fragment newFragment, String tag) {
        transitionTo(newFragment, true, true, tag);
    }

    private void transitionTo(Fragment newFragment, boolean addToBackStack, boolean animate, String tag) {
        if (animate) {
            showModal(newFragment, C7532R.C7534id.content_container, C7532R.C7534id.modal_container, addToBackStack, tag);
            return;
        }
        showFragment(newFragment, C7532R.C7534id.content_container, FragmentTransitionType.SlideInFromSide, addToBackStack, tag);
    }

    public EditTextFragmentListener getEditTextFragmentListener() {
        return this.hostMessageFragmentController;
    }

    public GuestPickerController getGuestPickerController() {
        return this.guestPickerController;
    }

    public ContactHostFragmentController getContactHostFragmentController() {
        return this.contactHostFragmentController;
    }

    public void fetchIdentity(VerificationFlow verificationFlow2) {
        this.verificationFlow = verificationFlow2;
        this.loaderFrame.startAnimation();
        this.fetchIdentityController.startFetchingIdentityVerificationState(this.accountManager.getCurrentUserId());
    }

    public void launchIdentityForBooking() {
        fetchIdentity(VerificationFlow.MobileHandOff);
    }

    public void launchP3ReviewSearch() {
        transitionTo(P3ReviewSearchFragment.forListing(this.state.listingId()), REVIEW_SEARCH_TAG);
    }

    public void onItemClick(int action) {
        boolean z = true;
        if (isActivityResumed()) {
            switch (action) {
                case 0:
                    this.p3Analytics.trackCancellationPolicyClick();
                    transitionTo(DLSCancellationPolicyFragment.newInstancePolicyOnly(this.state.cancellationPolicyLabel()));
                    return;
                case 1:
                    this.p3Analytics.trackHouseRulesClick();
                    transitionTo(DLSHouseRulesFragment.newInstance(this.state.listing(), this.state.checkInDate(), this.state.checkOutDate()));
                    return;
                case 2:
                    this.p3Analytics.trackAdditionalPricesClick();
                    transitionTo(P3AdditionalPriceFragment.newInstance());
                    return;
                case 3:
                    onContactHostClicked(P3Analytics.CONTACT_HOST_TARGET_ORIGIN);
                    return;
                case 4:
                    this.p3Analytics.trackAvailabilityCalendarClick();
                    showDatesPicker(false);
                    return;
                case 5:
                    this.p3Analytics.trackGuidebookClick();
                    if (this.state.listing().getHostGuidebook() != null) {
                        startActivity(ReactNativeIntents.intentForGuidebookHomeHostMap(this, this.state.listingId()));
                        return;
                    } else {
                        startActivity(AutoAirActivity.intentForFragment(this, LocalAttractionsFragment.class, LocalAttractionsFragment.bundleWithListing(this.state.listing())));
                        return;
                    }
                case 6:
                    showDatesPicker(false);
                    return;
                case 7:
                    transitionTo(new GuestPickerFragmentBuilder(getState().guestDetails(), NavigationTag.P3.trackingName).setListing(this.state.listing()).setMaxNumberOfGuests(this.state.listing().getPersonCapacity()).build(), GUESTS_FRAG_TAG);
                    return;
                case 8:
                    User host = getState().listing().getPrimaryHost();
                    transitionTo(new EditTextFragmentBuilder().setText(getState().inquiryMessage()).setHeaderTitle(getString(C7532R.string.contact_host_prompt_title)).setHeaderSubtitle(getString(C7532R.string.contact_host_prompt_subtitle, new Object[]{host.getFirstName()})).showHeader(true).setUser(host).setHint(getString(C7532R.string.p4_write_a_message_hint)).build(), MESSAGE_FRAG_TAG);
                    return;
                case 9:
                    BookItAnalytics.trackInquirySendButtonClick(makeInquiryAnalyticsParams().mo11639kv("mobile_search_session_id", this.state.searchSessionId()));
                    contactHost();
                    return;
                case 10:
                    this.p3Analytics.trackReviewsClick();
                    transitionTo(P3ReviewFragment.newInstance());
                    return;
                case 11:
                    if (this.state.listing().hasPrimaryHost()) {
                        startActivity(UserProfileIntents.intentForUserId(this, this.state.listing().getPrimaryHost().getId()));
                        this.p3Analytics.trackHostProfileClick();
                        return;
                    }
                    return;
                case 12:
                    showDatesPicker(false);
                    return;
                case 13:
                    this.p3Analytics.trackAmenitiesClick();
                    transitionTo(P3AmenitiesFragment.newInstance());
                    return;
                case 14:
                    this.p3Analytics.trackListingDescriptionClick();
                    transitionTo(P3SummaryAndSpaceDescriptionFragment.newInstance());
                    return;
                case 15:
                    this.p3Analytics.trackMapClick();
                    transitionTo(P3MapFragment.newInstance());
                    return;
                case 16:
                    this.p3Analytics.trackToggleListingDescriptionTranslationClick(this.state.showTranslatedSections(), this.state.listingId());
                    if (!this.state.hasFetchedTranslations()) {
                        requestListingDetailsTranslation();
                    } else {
                        P3State.Builder builder = this.state.toBuilder();
                        if (this.state.showTranslatedSections()) {
                            z = false;
                        }
                        this.state = builder.showTranslatedSections(z).build();
                    }
                    notifyStateChange();
                    return;
                case 17:
                    this.p3Analytics.trackBusinessDetailsClick();
                    transitionTo(P3BusinessDetailsFragment.instanceForHostId(this.state.listing().getPrimaryHost().getId()));
                    return;
                case 18:
                    WebViewIntentBuilder.startMobileWebActivity(this, getString(C7532R.string.superhost_help_url));
                    this.p3Analytics.trackSuperhostHelpClick();
                    return;
                case 19:
                    onContactHostClicked(P3Analytics.CONTACT_HOST_TARGET_BOOK_BUTTON);
                    return;
                case 20:
                    showDatesPicker(true);
                    return;
                default:
                    throw new IllegalStateException("Unrecognized P3 Action: " + action);
            }
        }
    }

    private void showDatesPicker(boolean launchP4OnDatesPickerResult2) {
        this.launchP4OnDatesPickerResult = launchP4OnDatesPickerResult2;
        transitionTo(DatesFragment.forListing(this.state.listing(), this.state.checkInDate(), this.state.checkOutDate(), getAnalyticsTag()), DATES_FRAG_TAG);
    }

    private void onContactHostClicked(String target) {
        this.p3Analytics.trackContactHostClick(target);
        fetchIdentity(VerificationFlow.ContactHost);
    }

    private void contactHost() {
        new CreateInquiryRequest(this.state.inquiryMessage(), this.state.listing().getId(), this.state.checkInDate(), this.state.checkOutDate(), this.state.guestDetails(), this.state.listing().getPrimaryHost().getId(), this.inquiryRequestListener).execute(this.requestManager);
    }

    public void onCalendarDatesApplied(AirDate start, AirDate end) {
        this.state = this.state.toBuilder().checkInDate(start).checkOutDate(end).build();
        fetchPricingQuote(FetchPricingInteractionType.DateChanged);
        fetchSimilarListings();
        if (this.launchP4OnDatesPickerResult) {
            startActivity(P3BookingIntents.intentToBooking(this, this));
            this.p3Analytics.trackBookItAddDatesClick(this.state.checkInDate(), this.state.checkOutDate());
            this.dismissDatePickerOnResume = true;
        } else {
            removeFragmentAndPopBackstack(DATES_FRAG_TAG);
            if (getSupportFragmentManager().findFragmentById(C7532R.C7534id.content_container) instanceof ContactHostFragment) {
                this.p3Analytics.trackContactHostAddDatesClick(this.state.checkInDate(), this.state.checkOutDate());
            }
        }
        notifyStateChange();
        notifyContactHostStateChanged(ContactHostAction.DATES);
        FindTweenAnalytics.trackSaveDates(getAnalyticsTag(), start, end);
    }

    public void onStartDateClicked(AirDate start) {
    }

    public void onEndDateClicked(AirDate end) {
    }

    public void showZenDialog(ZenDialog dialog) {
        dialog.showAllowingStateLoss(getSupportFragmentManager(), null);
    }

    public void notifyStateChange() {
        for (OnP3DataChangedListener listener : this.changeListeners) {
            listener.onStateChanged();
        }
    }

    public void showErrorSnackbar(int errorMessage) {
        showSnackbar(new SnackbarWrapper().view(getViewToUseForSnackbar()).title(C7532R.string.error, true).body(errorMessage).duration(0));
    }

    public void showNetworkExceptionSnackbar(NetworkException exception) {
        NetworkUtil.tryShowErrorWithSnackbar(getViewToUseForSnackbar(), exception);
    }

    public void showSnackbar(SnackbarWrapper snackbar) {
        snackbar.view(getViewToUseForSnackbar()).buildAndShow();
    }

    private View getViewToUseForSnackbar() {
        Fragment currentFragment = getCurrentFragment();
        if (currentFragment instanceof P3BaseFragment) {
            return ((P3BaseFragment) currentFragment).getViewToUseForSnackbar();
        }
        return this.contentContainer;
    }

    private Fragment getCurrentFragment() {
        Fragment f = getSupportFragmentManager().findFragmentById(C7532R.C7534id.modal_container);
        if (isFragmentActive(f)) {
            return f;
        }
        Fragment f2 = getSupportFragmentManager().findFragmentById(C7532R.C7534id.content_container);
        if (isFragmentActive(f2)) {
            return f2;
        }
        return null;
    }

    private boolean isFragmentActive(Fragment f) {
        return f != null && !f.isRemoving() && f.isResumed();
    }

    public void onHomeMarqueeImageClicked(ImageView transitionPhoto, int position) {
        this.p3Analytics.trackPhotoCarouselClick();
        Intent intent = P3PicturesActivity.intent(this, this.state, position, this.carouselPositionMessenger);
        if (AndroidVersion.isAtLeastLollipopMR1()) {
            startActivity(intent, ActivityOptionsCompat.makeSceneTransitionAnimation(this, transitionPhoto, ViewCompat.getTransitionName(transitionPhoto)).toBundle());
        } else {
            startActivity(intent);
        }
    }

    public void onReferralCreditClicked(ReferralStatusForMobile referralStatus) {
        transitionTo(ReferralCreditDialogFragment.newInstance(referralStatus));
    }

    public void launchP3ForSimilarListing(View view, Listing listing, PricingQuote pricingQuote) {
        startActivity(P3Arguments.builder().listing(listing).pricingQuote(pricingQuote).checkInDate(this.state.checkInDate()).checkOutDate(this.state.checkOutDate()).guestDetails(this.state.guestDetails()).tripPurpose(this.state.tripPurpose()).from(P3Arguments.FROM_SIMILAR_LISTINGS).toIntent(this), AutoSharedElementCallback.getActivityOptionsBundle(this, view));
    }

    public void launchInstantBookOnlyP2() {
        startActivity(SearchActivityIntents.intentForPostInquiryInstantBookUpsell(this, this.state.listing(), this.state.guestDetails(), this.state.checkInDate(), this.state.checkOutDate()));
    }

    public void launchReportListingFlow() {
        Long valueOf;
        UserFlag flag = this.state.listing().getUserFlag();
        long listingId = this.state.listingId();
        if (flag == null) {
            valueOf = null;
        } else {
            valueOf = Long.valueOf(flag.getId());
        }
        startActivityForResult(ReactNativeIntents.intentForFlagContent(this, listingId, valueOf, FlagContent.Listing), 766);
    }

    public void registerP3DataChangedListener(OnP3DataChangedListener listener) {
        this.changeListeners.add(listener);
    }

    public void unregisterP3DataChangedListener(OnP3DataChangedListener listener) {
        this.changeListeners.remove(listener);
    }

    public UrgencyMessageData getUrgencyMessage() {
        PricingQuote pricingQuote = getState().pricingQuote();
        if (pricingQuote == null) {
            return null;
        }
        UrgencyMessageData urgencyMessageData = pricingQuote.getUrgencyMessageData();
        if (urgencyMessageData == null) {
            return null;
        }
        switch (urgencyMessageData.getType()) {
            case RareFind:
            case CompetingViewsP3:
            case BookingProbability:
                return urgencyMessageData;
            case SmartPromotion:
                Experiments.checkSmartPromotionExperimentGroupForCN();
                if (!Experiments.showSmartPromotionUrgencyForChina()) {
                    urgencyMessageData = null;
                }
                return urgencyMessageData;
            case LastBooked:
            case RecentViews:
            case LongTermPricingDiscount:
            case LongTermStayFriendly:
            case GoodPrice:
            case GoodValue:
                return urgencyMessageData;
            default:
                return null;
        }
    }

    public P3Analytics getAnalytics() {
        return this.p3Analytics;
    }

    public ReviewsController getReviewsController() {
        return this.reviewsController;
    }

    /* access modifiers changed from: private */
    public void removeFragmentAndPopBackstack(String tag) {
        Fragment frag = getSupportFragmentManager().findFragmentByTag(tag);
        if (frag != null) {
            getSupportFragmentManager().beginTransaction().remove(frag).commit();
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStackImmediate();
            }
        }
    }

    public P3State getState() {
        return this.state;
    }

    static /* synthetic */ void lambda$new$3(P3Activity p3Activity, ListingResponse response) {
        response.listing.trimPhotos();
        boolean hasTranslatedDescription = response.listing.hasTranslatedDescription();
        p3Activity.state = p3Activity.state.toBuilder().listing(response.listing).sectionedListingDescription(response.listing.getSectionedDescription()).translatedSectionedListingDescription(hasTranslatedDescription ? response.listing.getTranslatedSectionedDescription() : null).cancellationPolicyLabel(p3Activity.getCancellationPolicyKey(response.listing, p3Activity.state.pricingQuote())).isCurrentUserListingHost(response.listing.canUserHost(p3Activity.accountManager.getCurrentUser())).showTranslatedSections(hasTranslatedDescription).build();
        p3Activity.listingLoaded.fromCache(response.metadata.isCached());
        p3Activity.onListingLoaded();
    }

    static /* synthetic */ void lambda$new$4(P3Activity p3Activity, AirRequestNetworkException e) {
        p3Activity.showErrorSnackbar(e.statusCode() == 404 ? C7532R.string.listing_no_longer_available : C7532R.string.kona_p3_failed_to_load_listing);
    }

    private void onListingLoaded() {
        for (OnP3DataChangedListener listener : this.changeListeners) {
            listener.onListingLoaded();
        }
        loadNonCriticalDataIfNeeded();
        Listing listing = this.state.listing();
        requestLocalAttractionsIfNeeded();
        prefetchThumbnailForSharing(listing.getPhoto());
        this.googleAppIndexingController.setAppUri(Uri.parse(MiscUtils.getAppUriForAppIndexing("rooms/" + listing.getId()))).setTitle(MiscUtils.getAppIndexingTitleForListing(listing));
        this.p3Analytics.trackImpressionIfNeeded(this);
        if (!this.hasLoggedTimeToInteractive) {
            logTimeToInteractive();
        }
    }

    public void prefetchThumbnailForSharing(Photo photo) {
        String thumbnailURL = photo.getUrlForSize(ImageSize.LandscapeSmall);
        if (thumbnailURL != null) {
            AirImageView.getImageBackground(this, thumbnailURL);
        }
    }

    static /* synthetic */ void lambda$new$5(P3Activity p3Activity, UserFlagResponse response) {
        p3Activity.state.listing().setUserFlag(response.flag);
        p3Activity.setIsFetchingReportedListingStatus(false);
        for (OnP3DataChangedListener listener : p3Activity.changeListeners) {
            listener.onListingLoaded();
        }
    }

    static /* synthetic */ void lambda$new$6(P3Activity p3Activity, PricingQuoteResponse response) {
        p3Activity.state = p3Activity.state.toBuilder().pricingQuote(response.pricingQuote).cancellationPolicyLabel(p3Activity.getCancellationPolicyKey(p3Activity.state.listing(), response.pricingQuote)).build();
        for (OnP3DataChangedListener listener : p3Activity.changeListeners) {
            listener.onPricingQuoteLoaded();
        }
        p3Activity.notifyContactHostPriceQuoteLoaded(response.pricingQuote);
        p3Activity.pricingQuoteLoaded.fromCache(response.metadata.isCached());
        if (!p3Activity.hasLoggedTimeToInteractive) {
            p3Activity.logTimeToInteractive();
        }
    }

    static /* synthetic */ void lambda$new$8(P3Activity p3Activity, CreateInquiryResponse data) {
        boolean hasPosts;
        boolean showPostIBInquiryIBUpsell;
        p3Activity.removeFragmentAndPopBackstack(CONTACT_HOST_FRAG_TAG);
        MessageThreadV2 thread = data.getThread();
        if (thread == null || ListUtils.isEmpty((Collection<?>) thread.getPosts())) {
            hasPosts = false;
        } else {
            hasPosts = true;
        }
        if (hasPosts) {
            BookItAnalytics.trackInquirySent(thread.getId(), ((PostV2) thread.getPosts().get(0)).getId(), p3Activity.makeInquiryAnalyticsParams());
        }
        p3Activity.state = p3Activity.state.toBuilder().inquiryMessage("").build();
        p3Activity.sharedPrefsHelper.setHostContactForReferral();
        boolean shouldShowReferral = p3Activity.sharedPrefsHelper.shouldShowPostHostContactReferral();
        if (!InstantBookUpsellUtils.isReservationInstantBookable(p3Activity.state) || !Experiments.shouldShowInquiryIbUpsell()) {
            showPostIBInquiryIBUpsell = false;
        } else {
            showPostIBInquiryIBUpsell = true;
        }
        if (shouldShowReferral) {
            p3Activity.startActivity(ReferralsIntents.newIntent(p3Activity, ReferralsIntents.ENTRY_POINT_POST_CONTACT));
        } else if (showPostIBInquiryIBUpsell) {
            p3Activity.transitionTo(PostInquiryIBUpsellFragment.newInstance(false), true, false, null);
        } else {
            p3Activity.showSnackbar(new SnackbarWrapper().body(p3Activity.getString(C7532R.string.ro_status_inquiry_for_listing, new Object[]{p3Activity.state.listing().getName()})).duration(0));
            p3Activity.removeFragmentAndPopBackstack(CONTACT_HOST_FRAG_TAG);
        }
    }

    static /* synthetic */ void lambda$new$9(P3Activity p3Activity, AirRequestNetworkException e) {
        p3Activity.showErrorSnackbar(C7532R.string.error_send_inquiry);
        BookItAnalytics.trackInquiryFailed(p3Activity.makeInquiryAnalyticsParams());
    }

    public boolean hasGuidebook() {
        if (this.numLocalAttractions != null && this.numLocalAttractions.intValue() > 0) {
            return true;
        }
        Listing listing = this.state.listing();
        if (listing == null || listing.getHostGuidebook() == null) {
            return false;
        }
        return true;
    }

    private void logTimeToInteractive() {
        if (this.listingLoaded.isLoaded() && this.pricingQuoteLoaded.isLoaded()) {
            this.performanceLogger.markCompleted(TIME_TO_INERACTIVE, Strap.make().mo11640kv("is_cached", this.listingLoaded.fromCache() && this.pricingQuoteLoaded.fromCache()).mo11639kv(PlacesIntents.INTENT_EXTRA_SEARCH_ID, this.state.searchSessionId()).mo11638kv("id_listing", this.state.listingId()), C2445NativeMeasurementType.TTI, "p3");
            this.hasLoggedTimeToInteractive = true;
        }
    }

    private String getCancellationPolicyKey(Listing listing, PricingQuote pricingQuote) {
        if (pricingQuote != null) {
            CancellationPolicyLabel pricingQuoteLabel = pricingQuote.getCancellationPolicyLabel();
            if (pricingQuoteLabel != null) {
                return pricingQuoteLabel.getServerKey();
            }
        }
        if (listing != null) {
            CancellationPolicyLabel listingLabel = CancellationPolicyLabel.fromServerKey(listing.getCancellationPolicyKey());
            if (listingLabel != null) {
                return listingLabel.getServerKey();
            }
        }
        return null;
    }

    private void requestLocalAttractionsIfNeeded() {
        Listing listing = this.state.listing();
        if (listing != null && listing.getHostGuidebook() == null && this.numLocalAttractions == null && !this.requestManager.hasRequest((BaseRequestListener<T>) this.localAttractionsRequestListener, LocalAttractionsRequest.class)) {
            new LocalAttractionsRequest(listing.getId()).withListener((Observer) this.localAttractionsRequestListener).doubleResponse().execute(this.requestManager);
        }
    }

    static /* synthetic */ void lambda$new$10(P3Activity p3Activity, LocalAttractionsResponse response) {
        p3Activity.numLocalAttractions = Integer.valueOf(response.localAttractions.size());
        if (p3Activity.numLocalAttractions.intValue() != 0) {
            p3Activity.notifyStateChange();
        }
    }

    private void requestListingDetailsTranslation() {
        new ListingDescriptionTranslateRequest(this.state.listing().getId()).withListener((Observer) this.listingDetailsTranslateResponse).doubleResponse().execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$11(P3Activity p3Activity, ListingDescriptionTranslateResponse response) {
        p3Activity.state = p3Activity.state.toBuilder().translatedSectionedListingDescription(response.getTranslateSectionedListingDescription()).showTranslatedSections(true).build();
        p3Activity.notifyStateChange();
    }

    private Strap makeInquiryAnalyticsParams() {
        GuestDetails guestDetails = this.state.guestDetails();
        return BookItAnalytics.makeInquiryAnalyticsParams(this.state.listingId(), this.state.checkInDate(), this.state.checkOutDate(), guestDetails == null ? null : Integer.valueOf(guestDetails.adultsCount()), this.state.inquiryMessage());
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    public void onBackPressed() {
        if (!handleLeavingActivityIfNeeded()) {
            super.onBackPressed();
        }
    }

    public void navigateUp() {
        if (!handleLeavingActivityIfNeeded()) {
            super.navigateUp();
        }
    }

    private boolean handleLeavingActivityIfNeeded() {
        String formattedDateRange;
        if (cameFromDeeplink()) {
            finish();
            return true;
        }
        if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
            this.p3Analytics.trackLeaveP3();
            if (cameFromSearch() && datesHaveChanged()) {
                if (getState().checkOutDate() == null) {
                    formattedDateRange = getState().checkInDate().getDateString(this);
                } else {
                    formattedDateRange = getState().checkInDate().getDateSpanString((Context) this, getState().checkOutDate());
                }
                ZenDialog.builder().withBodyText(getString(C7532R.string.use_dates_for_search_title, new Object[]{formattedDateRange})).withDualButton(C7532R.string.f10586no, RC_PROPOGATE_GUEST_CANCEL, C7532R.string.yes, 765).create().show(getSupportFragmentManager(), (String) null);
                return true;
            }
        }
        return false;
    }

    private void launchContactHostFragment() {
        if (Experiments.shouldShowInquiryIbUpsell()) {
            transitionTo(ContactHostV2Fragment.newInstance(this.state.listing()), CONTACT_HOST_FRAG_TAG);
            notifyContactHostPriceQuoteLoaded(this.state.pricingQuote());
            return;
        }
        transitionTo(ContactHostFragment.newInstance(this.state.listing()), CONTACT_HOST_FRAG_TAG);
    }

    private boolean cameFromSearch() {
        String from = getState().from();
        return P3Arguments.FROM_P2.equals(from) || P3Arguments.FROM_MAP.equals(from) || P3Arguments.FROM_EXPLORE.equals(from);
    }

    private boolean cameFromDeeplink() {
        return "deep_link".equals(getState().from());
    }

    private boolean datesHaveChanged() {
        return dateChanged(this.initialState.checkInDate(), getState().checkInDate()) || dateChanged(this.initialState.checkOutDate(), getState().checkOutDate());
    }

    private static boolean dateChanged(AirDate initialDate, AirDate newDate) {
        return !(initialDate == null || newDate == null || initialDate.equals(newDate)) || (initialDate == null && newDate != null);
    }

    public void onVerificationsFetchComplete(HashMap<VerificationFlow, ArrayList<AccountVerification>> incompleteVerifications2) {
        this.loaderFrame.finishImmediate();
        this.incompleteVerifications = incompleteVerifications2;
        if (this.verificationFlow == VerificationFlow.ContactHost) {
            onVerificationsFetchCompleteForContactHost();
        } else {
            onVerificationsFetchCompleteForBooking();
        }
    }

    private void onVerificationsFetchCompleteForContactHost() {
        if (((ArrayList) this.incompleteVerifications.get(VerificationFlow.ContactHost)).isEmpty()) {
            this.p3Analytics.trackContactHostImpression();
            launchContactHostFragment();
            return;
        }
        startActivityForResult(AccountVerificationStartActivityIntents.newIntentForContactHostVerifications(this, (List) this.incompleteVerifications.get(VerificationFlow.ContactHost)), RC_CONTACT_HOST_VERIFICATION);
    }

    private void onVerificationsFetchCompleteForBooking() {
        startActivityForResult(AccountVerificationStartActivityIntents.newIntentForIncompleteVerifications(this.identityJitneyLogger, this, AccountVerificationStartFragmentArguments.builder().verificationUser(this.fetchIdentityController.getVerificationUser()).verificationFlow(VerificationFlow.MobileHandOff).incompleteVerifications((List) this.incompleteVerifications.get(VerificationFlow.MobileHandOff)).firstVerificationStep(this.state.firstVerificationStep()).build()), 9001);
    }

    public void onVerificationsFetchError(NetworkException e) {
        this.loaderFrame.finishImmediate();
        showNetworkExceptionSnackbar(e);
    }

    public void onProvideIdClick() {
        removeFragmentAndPopBackstack(PROVIDE_ID_FRAG_TAG);
        List<AccountVerification> incompleteVerificationsForBooking = (List) this.incompleteVerifications.get(VerificationFlow.Booking);
        incompleteVerificationsForBooking.removeAll((Collection) this.incompleteVerifications.get(VerificationFlow.ContactHost));
        startActivityForResult(AccountVerificationStartActivityIntents.newIntentForIncompleteVerifications(this, AccountVerificationStartFragmentArguments.builder().verificationFlow(VerificationFlow.NonBooking).incompleteVerifications(incompleteVerificationsForBooking).host(this.state.listing().getPrimaryHost()).verificationUser(this.fetchIdentityController.getVerificationUser()).listingId(this.state.listingId()).build()), RC_CONTACT_HOST_PROVIDE_ID);
    }

    private NavigationTag getAnalyticsTag() {
        return NavigationTag.P3;
    }

    /* access modifiers changed from: private */
    public void notifyContactHostStateChanged(ContactHostAction action) {
        for (ContactHostDataChangeListener listener : this.contactHostChangeListeners) {
            listener.onActionCompleted(action);
        }
    }

    /* access modifiers changed from: private */
    public void notifyContactHostPriceQuoteLoaded(PricingQuote pricingQuote) {
        for (ContactHostDataChangeListener listener : this.contactHostChangeListeners) {
            listener.onPricingQuoteLoaded(pricingQuote);
        }
    }

    public HomeLayout getMockHomeLayout() {
        String[] roomNames = {"Entry", "Livingroom", "Kitchen", "Bedroom", "Bathroom", "Office", "Bedroom", "Bathroom", "Balcony", "Bedroom", "Bathroom"};
        List<Room> roomItems = new ArrayList<>();
        for (int i = 0; i < roomNames.length; i++) {
            List<RoomDetail> roomDetailList = new ArrayList<>();
            roomDetailList.add(RoomDetail.builder().caption("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed et euismod leo, at tincidunt lectus. Donec quis est velit.").photo(RoomPhoto.builder().mo11106id((long) (i + 1000)).dominantSaturatedColor("").previewEncodedPng("").largeUrl("http://a0.muscache.com/airbnb/trips/posters/trip_poster_39_sm.jpg").build()).build());
            roomItems.add(Room.builder().mo11092id((long) (i + 200)).name(roomNames[i]).highlights(generateHighlightList()).photos(generateIndividualRoomPhotoList(i + OfficialIdPhotoSelectionFragment.MAX_IMAGE_SIZE)).details(roomDetailList).build());
        }
        List<Floor> floors = new ArrayList<>();
        floors.add(Floor.builder().mo11018id(100).name("First floor").rooms(roomItems.subList(0, 4)).build());
        floors.add(Floor.builder().mo11018id(101).name("Second floor").rooms(roomItems.subList(5, 8)).build());
        floors.add(Floor.builder().mo11018id(102).name("Third floor").rooms(roomItems.subList(9, 10)).build());
        return HomeLayout.builder().floors(floors).build();
    }

    private static List<RoomPhoto> generateIndividualRoomPhotoList(int id) {
        String url;
        Random rand = new Random();
        List<RoomPhoto> list = new ArrayList<>();
        for (int i = 0; i < rand.nextInt(6) + 1; i++) {
            switch (rand.nextInt(3) % 3) {
                case 0:
                    url = "https://a0.muscache.com/im/pictures/17938054/357a2ec4_original.jpg?aki_policy=large";
                    break;
                case 1:
                    url = "https://a0.muscache.com/im/pictures/812e040f-6f6f-4cae-ad67-66c050b57c1f.jpg?aki_policy=large";
                    break;
                case 2:
                    url = "https://a0.muscache.com/im/pictures/65441227/bb1eb2dd_original.jpg?aki_policy=large";
                    break;
                default:
                    url = "https://a0.muscache.com/im/pictures/40527001/725cf38d_original.jpg?aki_policy=x_large";
                    break;
            }
            list.add(RoomPhoto.builder().mo11106id((long) (rand.nextInt() * id)).dominantSaturatedColor("").previewEncodedPng("").largeUrl(url).build());
        }
        return list;
    }

    private static List<String> generateHighlightList() {
        String[] roomDetailCaptionStrings = {"Private", "Day bed", "Full kitchen", "King bed", "Jacuzzi", "Quiet", "Queen bed", "Great view", "Full bed"};
        Random rand = new Random();
        List<String> list = new ArrayList<>();
        for (int i = 0; i < rand.nextInt(roomDetailCaptionStrings.length) + 1; i++) {
            list.add(roomDetailCaptionStrings[rand.nextInt(roomDetailCaptionStrings.length)]);
        }
        return list;
    }
}
