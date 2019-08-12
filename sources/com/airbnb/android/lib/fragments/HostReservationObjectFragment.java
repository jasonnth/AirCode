package com.airbnb.android.lib.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.ActivityOptionsCompat;
import android.support.p000v4.util.LongSparseArray;
import android.support.p000v4.util.Pair;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.JitneyPublisher;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.HostReservationObjectJitneyLogger;
import com.airbnb.android.core.analytics.PerformanceLoggerTimeline;
import com.airbnb.android.core.analytics.PerformanceLoggerTimeline.Event;
import com.airbnb.android.core.analytics.TripsAnalytics;
import com.airbnb.android.core.calendar.CalendarDays;
import com.airbnb.android.core.calendar.CalendarStore;
import com.airbnb.android.core.calendar.CalendarStoreListener;
import com.airbnb.android.core.enums.ROLaunchSource;
import com.airbnb.android.core.enums.ReviewsMode;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationLoggingElement;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.intents.GuestRecoveryActivityIntents;
import com.airbnb.android.core.intents.HelpCenterIntents;
import com.airbnb.android.core.intents.HostCalendarIntents;
import com.airbnb.android.core.intents.PaidAmenityIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.intents.ThreadFragmentIntents;
import com.airbnb.android.core.intents.UserProfileIntents;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.messaging.MessagingRequestFactory;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Inquiry;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.NightCount;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.Review;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.InquiryRequest;
import com.airbnb.android.core.requests.ReservationRequest;
import com.airbnb.android.core.requests.ReservationRequest.Format;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.InquiryResponse;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.core.utils.InstantBookUpsellManager;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.ReservationStatusDisplay;
import com.airbnb.android.core.utils.ReservationUtils;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.GuestRatingsHelpViewPagerActivity;
import com.airbnb.android.lib.activities.HostReservationObjectActivity;
import com.airbnb.android.lib.activities.PreapprovalActivity;
import com.airbnb.android.lib.activities.ReservationCancellationActivity;
import com.airbnb.android.lib.activities.ReservationResponseActivity;
import com.airbnb.android.lib.activities.SpecialOfferActivity;
import com.airbnb.android.lib.adapters.HostReservationObjectAdapter;
import com.airbnb.android.lib.adapters.HostReservationObjectAdapter.Listener;
import com.airbnb.android.lib.fragments.price_breakdown.PriceBreakdownFragment;
import com.airbnb.android.lib.fragments.reviews.ReservationReviewsFragment;
import com.airbnb.android.lib.fragments.reviews.ReviewFragment;
import com.airbnb.android.lib.paidamenities.activities.HostPendingAmenityActivity;
import com.airbnb.android.lib.reservationresponse.AcceptReservationActivity;
import com.airbnb.android.lib.reviews.activities.WriteReviewActivity;
import com.airbnb.android.utils.CallHelper;
import com.airbnb.android.utils.EmailUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.jitney.event.logging.ReservationObject.p226v1.ReservationObjectEmailEvent.Builder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.components.onboarding_overlay.OnboardingOverlayViewController;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.State;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import p032rx.Observer;

public class HostReservationObjectFragment extends AirFragment {
    private static final String ARG_CONFIRMATION_CODE = "confirmation_code";
    private static final String ARG_LAUNCH_SOURCE = "launch_source";
    private static final String ARG_RESERVATION = "reservation";
    private static final String ARG_RESERVATION_ID = "reservation_id";
    private static final String ARG_THREAD_ID = "thread_id";
    private static final int CALENDAR_FETCH_END_DATE_OFFSET = 27;
    private static final int CALENDAR_FETCH_START_DATE_OFFSET = -3;
    private static final int GUEST_RATING_ONBOARDING_ON_SEEN_TIMES = 1;
    private static final int REQUEST_CODE_ALTERATION = 904;
    private static final int REQUEST_CODE_CANCELLATION = 905;
    private static final int REQUEST_CODE_DECLINE_INQUIRY = 903;
    private static final int REQUEST_CODE_DECLINE_REQUEST = 900;
    public static final int REQUEST_CODE_EXPORT_TO_CALENDAR = 12;
    private static final int REQUEST_CODE_PREAPPROVAL = 902;
    private static final int REQUEST_CODE_RESPOND_TO_REQUEST = 906;
    private static final int REQUEST_CODE_SPECIAL_OFFER = 901;
    public static final int RESULT_CODE_BLOCKED_DATES = 11;
    public static final int RESULT_CODE_PREAPPROVED = 10;
    /* access modifiers changed from: private */
    public HostReservationObjectAdapter adapter;
    public final NonResubscribableRequestListener<AirBatchResponse> batchListener = new C0699RL().onResponse(HostReservationObjectFragment$$Lambda$1.lambdaFactory$(this)).onError(HostReservationObjectFragment$$Lambda$2.lambdaFactory$(this)).buildWithoutResubscription();
    CalendarStore calendarStore;
    private final CalendarStoreListener calendarStoreListener = new CalendarStoreListener() {
        public void onResponse(LongSparseArray<CalendarDays> calendarDaysByListingId, LongSparseArray<NightCount> longSparseArray, AirDate startDate, AirDate endDate) {
            HostReservationObjectFragment.this.provider.setCalendarDays((CalendarDays) calendarDaysByListingId.valueAt(0));
            HostReservationObjectFragment.this.adapter.updateWithCalendarDays(HostReservationObjectFragment.this.provider);
        }

        public void onError(NetworkException e) {
            NetworkUtil.tryShowRetryableErrorWithSnackbar(HostReservationObjectFragment.this.getView(), e, HostReservationObjectFragment$2$$Lambda$1.lambdaFactory$(this));
        }
    };
    @State
    String confirmationCode;
    final RequestListener<InquiryResponse> inquiryListener = new C0699RL().onResponse(HostReservationObjectFragment$$Lambda$5.lambdaFactory$(this)).onError(HostReservationObjectFragment$$Lambda$6.lambdaFactory$(this)).build();
    InstantBookUpsellManager instantBookUpsellManager;
    HostReservationObjectJitneyLogger jitneyLogger;
    @BindView
    View loader;
    LoggingContextFactory loggingContextFactory;
    MessagingRequestFactory messagingRequestFactory;
    @State
    TripInformationProvider provider;
    @BindView
    RecyclerView recyclerView;
    private final Listener reservationActionListener = new Listener() {
        public void goToMessageThread() {
            HostReservationObjectFragment.this.jitneyLogger.reservationObjectMessage(HostReservationObjectFragment.this.provider);
            if (HostReservationObjectFragment.this.source == ROLaunchSource.MessageThread) {
                HostReservationObjectFragment.this.messagingRequestFactory.expireCacheForThread(HostReservationObjectFragment.this.provider.getThreadId(), InboxType.Host);
                HostReservationObjectFragment.this.getActivity().finish();
                return;
            }
            HostReservationObjectFragment.this.startActivity(ThreadFragmentIntents.newIntent(HostReservationObjectFragment.this.getActivity(), HostReservationObjectFragment.this.provider.getThreadId(), InboxType.Host, null, ROLaunchSource.HostRO));
        }

        public void goToGuestRatingsHelpFlow() {
            HostReservationObjectFragment.this.startActivity(GuestRatingsHelpViewPagerActivity.intentForDefault(HostReservationObjectFragment.this.getContext(), HostReservationObjectFragment.this.provider.getListing().getId()));
        }

        public void goToReview(Review review) {
            HostReservationObjectFragment.this.showFragment(ReviewFragment.forReview(review));
        }

        public void goToAcceptFlow() {
            HostReservationObjectFragment.this.jitneyLogger.reservationObjectAccept(HostReservationObjectFragment.this.provider);
            HostReservationObjectFragment.this.startActivityForResult(AcceptReservationActivity.getIntentForTripProvider(HostReservationObjectFragment.this.getActivity(), HostReservationObjectFragment.this.provider), HostReservationObjectFragment.REQUEST_CODE_RESPOND_TO_REQUEST);
        }

        public void goToAcceptOrDeclineFlow() {
            HostReservationObjectFragment.this.startActivityForResult(ReservationResponseActivity.newIntentForResponseWithNewAcceptBehavior(HostReservationObjectFragment.this.getContext(), HostReservationObjectFragment.this.provider.getReservation(), HostReservationObjectFragment.this.provider.getThreadId()), HostReservationObjectFragment.REQUEST_CODE_RESPOND_TO_REQUEST);
        }

        public void goToDeclineInquiryFlow() {
            HostReservationObjectFragment.this.jitneyLogger.reservationObjectDecline(HostReservationObjectFragment.this.provider);
            HostReservationObjectFragment.this.startActivityForResult(DeclineInquiryFragment.newIntent(HostReservationObjectFragment.this.getContext(), HostReservationObjectFragment.this.provider.getThreadId(), HostReservationObjectFragment.this.provider.getListing().getId(), HostReservationObjectFragment.this.provider.getGuestIfPossible().getName()), HostReservationObjectFragment.REQUEST_CODE_DECLINE_INQUIRY);
        }

        public void goToDeclineRequestFlow() {
            HostReservationObjectFragment.this.jitneyLogger.reservationObjectDecline(HostReservationObjectFragment.this.provider);
            HostReservationObjectFragment.this.startActivityForResult(ReservationResponseActivity.newIntentForDecline(HostReservationObjectFragment.this.getActivity(), HostReservationObjectFragment.this.provider.getReservation(), HostReservationObjectFragment.this.provider.getThreadId()), HostReservationObjectFragment.REQUEST_CODE_DECLINE_REQUEST);
        }

        public void goToPreapproveFlow() {
            if (HostReservationObjectFragment.this.provider.getCalendarDays() == null || ReservationUtils.isDateRangeUnblocked(HostReservationObjectFragment.this.provider.getStartDate(), HostReservationObjectFragment.this.provider.getEndDate(), HostReservationObjectFragment.this.provider.getCalendarDays())) {
                HostReservationObjectFragment.this.jitneyLogger.reservationObjectPreapprove(HostReservationObjectFragment.this.provider);
                HostReservationObjectFragment.this.startActivityForResult(PreapprovalActivity.intentForProvider(HostReservationObjectFragment.this.getContext(), HostReservationObjectFragment.this.provider), HostReservationObjectFragment.REQUEST_CODE_PREAPPROVAL);
                return;
            }
            HostReservationObjectFragment.this.showSnackbar(C0880R.string.ro_preapprove_error_blocked_dates);
        }

        public void goToGuestProfile() {
            HostReservationObjectFragment.this.startActivity(UserProfileIntents.intentForUserAndReservationStatus(HostReservationObjectFragment.this.getContext(), HostReservationObjectFragment.this.provider.getGuestIfPossible(), HostReservationObjectFragment.this.provider.getStatus()));
        }

        public void goToGuestReviewsModal(ReviewsMode reviewsMode) {
            HostReservationObjectFragment.this.showModalFragment(ReservationReviewsFragment.newInstanceForUser(HostReservationObjectFragment.this.provider.getGuestIfPossible(), reviewsMode));
        }

        public void goToGuestRatingsModal(User guest) {
            HostReservationObjectFragment.this.showModalFragment(GuestStarRatingsFragment.newInstance(guest));
        }

        public void goToSpecialOfferFlow() {
            HostReservationObjectFragment.this.startActivityForResult(SpecialOfferActivity.intentForProvider(HostReservationObjectFragment.this.getContext(), HostReservationObjectFragment.this.provider), HostReservationObjectFragment.REQUEST_CODE_SPECIAL_OFFER);
        }

        public void goToCallGuest() {
            CallHelper.dial(HostReservationObjectFragment.this.getContext(), HostReservationObjectFragment.this.provider.getGuestIfPossible().getPhone());
        }

        public void goToPayoutBreakdown(Price price, Listing listing) {
            HostReservationObjectFragment.this.showModalFragment(PriceBreakdownFragment.forHost(price, listing));
        }

        public void goToRemovePreapproval() {
            HostReservationObjectFragment.this.jitneyLogger.reservationObjectRemovePreapprove(HostReservationObjectFragment.this.provider);
            HostReservationObjectFragment.this.showFragment(RemovePreapprovalFragment.newInstanceForProvider(HostReservationObjectFragment.this.provider));
        }

        public void goToCancellationFlow() {
            HostReservationObjectFragment.this.startActivityForResult(ReservationCancellationActivity.intentForReservation(HostReservationObjectFragment.this.getContext(), HostReservationObjectFragment.this.provider.getReservation()), HostReservationObjectFragment.REQUEST_CODE_CANCELLATION);
        }

        public void goToResolutionCenter(String confirmationCode) {
            String uri = HostReservationObjectFragment.this.getContext().getString(C0880R.string.resolution_center_landing_page);
            if (FeatureToggles.showResolutionCenterDeeplink()) {
                uri = HostReservationObjectFragment.this.getContext().getString(C0880R.string.resolution_center_format, new Object[]{confirmationCode});
            }
            HostReservationObjectFragment.this.startActivity(WebViewIntentBuilder.newBuilder(HostReservationObjectFragment.this.getContext(), uri).title(C0880R.string.ro_resolution_center).authenticate().toIntent());
        }

        public void goToHelp() {
            HostReservationObjectFragment.this.startActivity(HelpCenterIntents.intentForHelpCenter(HostReservationObjectFragment.this.getActivity()));
        }

        public void goToCalendar() {
            Listing listing = HostReservationObjectFragment.this.provider.getListing();
            HostReservationObjectFragment.this.startActivity(HostCalendarIntents.intentForSingleListingCalendarWithDates(HostReservationObjectFragment.this.getContext(), listing.getId(), listing.getName(), HostReservationObjectFragment.this.provider.getStartDate(), HostReservationObjectFragment.this.provider.getEndDate()));
        }

        public void goToEmailGuest() {
            JitneyPublisher.publish(new Builder(HostReservationObjectFragment.this.loggingContextFactory.newInstance(), Long.valueOf(HostReservationObjectFragment.this.provider.getListing().getId()), Long.valueOf(HostReservationObjectFragment.this.provider.getReservation().getId())));
            EmailUtils.send(HostReservationObjectFragment.this.getContext(), HostReservationObjectFragment.this.provider.getGuestIfPossible().getEmailAddress(), null, null);
        }

        public void goToAlterationFlow() {
            HostReservationObjectFragment.startReservationAlterationFlow(HostReservationObjectFragment.this.getActivity(), HostReservationObjectFragment.this.provider.getReservation());
        }

        public void goToAddExtraServices(long listingId) {
            HostReservationObjectFragment.this.startActivity(PaidAmenityIntents.createAmenitiesWithListingIdIntent(HostReservationObjectFragment.this.getContext(), listingId));
        }

        public void goToViewExtraServiceOrder() {
            Reservation reservation = HostReservationObjectFragment.this.provider.getReservation();
            HostReservationObjectFragment.this.startActivity(HostPendingAmenityActivity.intent(HostReservationObjectFragment.this.getContext(), reservation.getConfirmationCode(), (long) reservation.getThreadId(), HostReservationObjectFragment.this.provider.getListing().getId()));
        }

        public void goToExportCalendar() {
            ZenDialog.builder().withTitle(HostReservationObjectFragment.this.getString(C0880R.string.dialog_export_to_calendar_title)).withBodyText(HostReservationObjectFragment.this.getString(C0880R.string.dialog_export_to_calendar_body)).withDualButton(C0880R.string.cancel, 0, C0880R.string.okay, 12).create().showAllowingStateLoss(HostReservationObjectFragment.this.getFragmentManager(), null);
        }
    };
    @State
    long reservationId = -1;
    final RequestListener<ReservationResponse> reservationRequestListener = new C0699RL().onResponse(HostReservationObjectFragment$$Lambda$3.lambdaFactory$(this)).onError(HostReservationObjectFragment$$Lambda$4.lambdaFactory$(this)).build();
    @BindView
    PrimaryButton reviewButton;
    @State
    ROLaunchSource source;
    @BindView
    AirToolbar toolbar;

    public static void startReservationAlterationFlow(Activity activity, Reservation reservation) {
        activity.startActivityForResult(ReactNativeIntents.intentForAlterations(activity, reservation, true), REQUEST_CODE_ALTERATION, ActivityOptionsCompat.makeSceneTransitionAnimation(activity, new Pair[0]).toBundle());
    }

    static /* synthetic */ void lambda$new$0(HostReservationObjectFragment hostReservationObjectFragment, AirBatchResponse response) {
        List<ReservationResponse> responses = response.filterResponses(ReservationResponse.class).toList();
        Reservation reservation = ((ReservationResponse) responses.get(0)).reservation;
        User guestWithReviewInformation = ((ReservationResponse) responses.get(1)).reservation.getGuest();
        reservation.getGuest().setReviewRatingsAsGuest(guestWithReviewInformation.getReviewRatingsAsGuest());
        reservation.getGuest().setReviewsCountAsGuest(guestWithReviewInformation.getReviewsCountAsGuest());
        hostReservationObjectFragment.handleLoad(TripInformationProvider.create(reservation));
    }

    static /* synthetic */ void lambda$new$4(HostReservationObjectFragment hostReservationObjectFragment, InquiryResponse data) {
        Inquiry inquiry = data.inquiry;
        if (inquiry.getInquiryReservation() != null) {
            hostReservationObjectFragment.confirmationCode = inquiry.getInquiryReservation().getConfirmationCode();
            hostReservationObjectFragment.loadData();
            return;
        }
        hostReservationObjectFragment.handleLoad(TripInformationProvider.create(inquiry));
    }

    /* access modifiers changed from: private */
    public void showModalFragment(AirFragment fragment) {
        ((HostReservationObjectActivity) getActivity()).showModalFragment(fragment);
    }

    /* access modifiers changed from: private */
    public void showFragment(AirFragment fragment) {
        ((HostReservationObjectActivity) getActivity()).showFragment(fragment);
    }

    /* access modifiers changed from: private */
    public void showSnackbar(int stringRes) {
        new SnackbarWrapper().view(getView()).body(stringRes).duration(0).buildAndShow();
    }

    /* access modifiers changed from: private */
    public void handleLoad(TripInformationProvider provider2) {
        this.provider = provider2;
        this.adapter.setModels(provider2);
        setLoading(false);
        PerformanceLoggerTimeline.logTimeToInteraction(Event.HOST_RESERVATION);
        logImpression(getContext(), provider2);
        ViewUtils.setVisibleIf((View) this.reviewButton, isPendingHostReview());
        if (provider2.getListing() != null && HostReservationObjectAdapter.shouldShowInlineCalendar(provider2.getStatus())) {
            loadCalendar();
        }
    }

    /* access modifiers changed from: private */
    public void loadCalendar() {
        this.calendarStoreListener.setEnabled(true);
        AirDate loadCalendarStartDate = getLoadCalendarStartDate(this.provider.getStartDate());
        AirDate loadCalendarEndDate = getLoadCalendarEndDate(loadCalendarStartDate, this.provider.getEndDate());
        if (loadCalendarStartDate.isSameDayOrBefore(loadCalendarEndDate)) {
            this.calendarStore.getDaysForListingIds(Collections.singleton(Long.valueOf(this.provider.getListing().getId())), loadCalendarStartDate, loadCalendarEndDate, this.calendarStoreListener);
        } else {
            BugsnagWrapper.throwOrNotify(new RuntimeException(buildErrorString("HostReservationObjectFragment.loadCalendar invalid date range", this.provider)));
        }
    }

    private AirDate getLoadCalendarStartDate(AirDate checkinDate) {
        AirDate date = checkinDate.plusDays(-3);
        AirDate date2 = date.plusDays(0 - date.getDaysFromDayOfWeek(AirDate.getDayOfWeekFromCalendar()));
        if (date2.isBefore(this.calendarStore.getMinDate())) {
            return this.calendarStore.getMinDate();
        }
        return date2;
    }

    private AirDate getLoadCalendarEndDate(AirDate loadCalendarStartDate, AirDate checkoutDate) {
        AirDate endDisplayedDate = loadCalendarStartDate.plusDays(27);
        if (!endDisplayedDate.isAfter(checkoutDate)) {
            endDisplayedDate = checkoutDate;
        }
        if (endDisplayedDate.isAfter(this.calendarStore.getMaxDate())) {
            return this.calendarStore.getMaxDate();
        }
        return endDisplayedDate;
    }

    /* access modifiers changed from: private */
    public void handleError(NetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(this.recyclerView, e);
    }

    public static HostReservationObjectFragment newInstanceForReservation(Reservation reservation, ROLaunchSource source2) {
        Check.notNull(reservation);
        return (HostReservationObjectFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new HostReservationObjectFragment()).putParcelable("reservation", reservation)).putSerializable("launch_source", source2)).build();
    }

    public static HostReservationObjectFragment newInstanceForConfirmationCode(String code, ROLaunchSource source2) {
        Check.notNull(code, "Confirmation code");
        return (HostReservationObjectFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new HostReservationObjectFragment()).putString("confirmation_code", code)).putSerializable("launch_source", source2)).build();
    }

    public static HostReservationObjectFragment newInstanceForThread(long threadId, ROLaunchSource source2) {
        return (HostReservationObjectFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new HostReservationObjectFragment()).putLong("thread_id", Check.validId(threadId))).putSerializable("launch_source", source2)).build();
    }

    public static HostReservationObjectFragment newInstanceForReservationId(long id, ROLaunchSource source2) {
        Check.state(id != -1);
        return (HostReservationObjectFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new HostReservationObjectFragment()).putLong("reservation_id", id)).putSerializable("launch_source", source2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PerformanceLoggerTimeline.start(Event.HOST_RESERVATION);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_host_reservation_object, container, false);
        bindViews(view);
        ((AirbnbGraph) AirbnbApplication.instance(getContext()).component()).inject(this);
        setToolbar(this.toolbar);
        this.adapter = new HostReservationObjectAdapter(getContext(), this.reservationActionListener, savedInstanceState);
        if (savedInstanceState == null) {
            this.confirmationCode = getArguments().getString("confirmation_code");
            this.reservationId = getArguments().getLong("reservation_id", -1);
            this.source = (ROLaunchSource) getArguments().getSerializable("launch_source");
            Reservation reservation = (Reservation) getArguments().getParcelable("reservation");
            if (reservation == null) {
                loadData();
            } else {
                handleLoad(TripInformationProvider.create(reservation));
            }
        } else if (this.provider != null) {
            handleLoad(this.provider);
        }
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false) {
            public void onLayoutCompleted(RecyclerView.State state) {
                super.onLayoutCompleted(state);
                HostReservationObjectFragment.this.showGuestRatingsOnboardingOverlay();
            }
        });
        getFragmentManager().addOnBackStackChangedListener(HostReservationObjectFragment$$Lambda$7.lambdaFactory$(this));
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$5(HostReservationObjectFragment hostReservationObjectFragment) {
        if (hostReservationObjectFragment.getFragmentManager().getBackStackEntryCount() < 1) {
            hostReservationObjectFragment.getActionBar().show();
            hostReservationObjectFragment.loadData();
        }
    }

    public void showGuestRatingsOnboardingOverlay() {
        View starRatingView = getActivity().findViewById(C0880R.C0882id.rating_stars);
        if (starRatingView != null && FeatureToggles.showGuestReviewRatings()) {
            OnboardingOverlayViewController.show(getActivity(), starRatingView, getContext().getString(C0880R.string.guest_ratings_onboarding_message), getContext().getString(C0880R.string.guest_ratings_onboarding_button), "host_ro_guest_star_ratings", 1);
        }
    }

    public void onResume() {
        super.onResume();
        if (this.provider != null) {
            this.adapter.setModels(this.provider);
        }
    }

    public void onPause() {
        super.onPause();
        this.calendarStoreListener.setEnabled(false);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != 16908332) {
            return super.onOptionsItemSelected(item);
        }
        getFragmentManager().popBackStack();
        return true;
    }

    private void loadData() {
        setLoading(true);
        if (this.provider != null) {
            this.messagingRequestFactory.expireCacheForThread(this.provider.getThreadId(), InboxType.Host);
        }
        if (!TextUtils.isEmpty(this.confirmationCode)) {
            if (FeatureToggles.showGuestReviewRatings()) {
                List<BaseRequestV2<?>> requests = new ArrayList<>();
                requests.add(ReservationRequest.forConfirmationCode(this.confirmationCode, Format.Host));
                requests.add(ReservationRequest.forConfirmationCode(this.confirmationCode, Format.ReviewRatings));
                new AirBatchRequest(requests, true, this.batchListener).execute(this.requestManager);
                return;
            }
            ReservationRequest.forConfirmationCode(this.confirmationCode, Format.Host).withListener((Observer) this.reservationRequestListener).skipCache().execute(this.requestManager);
        } else if (getArguments().containsKey("thread_id")) {
            long threadId = Check.validId(getArguments().getLong("thread_id", -1));
            TripsAnalytics.trackInquiryLoad(threadId);
            new InquiryRequest(threadId).withListener((Observer) this.inquiryListener).execute(this.requestManager);
        } else if (this.reservationId == -1) {
            BugsnagWrapper.notify((Throwable) new IllegalArgumentException("No confirmation code or thread given in Host Reservation Object"));
            ErrorUtils.showErrorUsingSnackbar((View) this.recyclerView, C0880R.string.error_ro_unable_to_load);
        } else if (FeatureToggles.showGuestReviewRatings()) {
            List<BaseRequestV2<?>> requests2 = new ArrayList<>();
            requests2.add(ReservationRequest.forReservationId(this.reservationId, Format.Host));
            requests2.add(ReservationRequest.forReservationId(this.reservationId, Format.ReviewRatings));
            new AirBatchRequest(requests2, true, this.batchListener).execute(this.requestManager);
        } else {
            ReservationRequest.forReservationId(this.reservationId, Format.Host).withListener((Observer) this.reservationRequestListener).skipCache().execute(this.requestManager);
        }
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onWriteReview() {
        startActivity(WriteReviewActivity.newIntent((Context) getActivity(), this.provider.getReservation().getHostReview().getId()));
    }

    private void setLoading(boolean loading) {
        boolean z;
        boolean z2 = true;
        PrimaryButton primaryButton = this.reviewButton;
        if (loading || !isPendingHostReview()) {
            z = false;
        } else {
            z = true;
        }
        ViewUtils.setVisibleIf((View) primaryButton, z);
        ViewUtils.setVisibleIf(this.loader, loading);
        RecyclerView recyclerView2 = this.recyclerView;
        if (loading) {
            z2 = false;
        }
        ViewUtils.setVisibleIf((View) recyclerView2, z2);
    }

    private void refreshCalendar() {
        if (this.provider.getStartDate().isSameDayOrBefore(this.provider.getEndDate())) {
            this.calendarStore.refreshDays(this.provider.getListing().getId(), this.provider.getStartDate(), this.provider.getEndDate());
        } else {
            BugsnagWrapper.throwOrNotify(new RuntimeException(buildErrorString("HostReservationObjectFragment.refreshCalendar unexpected date range", this.provider)));
        }
    }

    private String buildErrorString(String prefix, TripInformationProvider provider2) {
        return prefix + ": reservationId=" + (provider2.hasReservation() ? String.valueOf(provider2.getReservation().getId()) : "null") + ", listingId=" + provider2.getListing().getId() + ", startDate=" + provider2.getStartDate().getIsoDateString() + ", endDate=" + provider2.getEndDate().getIsoDateString();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    private boolean isPendingHostReview() {
        return this.provider.hasReservation() && this.provider.getReservation().isPendingHostReview();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        boolean refreshData = false;
        switch (requestCode) {
            case 12:
                if (resultCode == -1) {
                    if (this.provider.hasReservation()) {
                        ReservationUtils.launchIntentForExportToCalendar(getActivity(), this.provider.getReservation());
                        break;
                    } else {
                        throw new IllegalStateException("tried to export when no reservation was present");
                    }
                }
                break;
            case REQUEST_CODE_DECLINE_REQUEST /*900*/:
            case REQUEST_CODE_SPECIAL_OFFER /*901*/:
            case REQUEST_CODE_DECLINE_INQUIRY /*903*/:
            case REQUEST_CODE_ALTERATION /*904*/:
            case REQUEST_CODE_CANCELLATION /*905*/:
            case REQUEST_CODE_RESPOND_TO_REQUEST /*906*/:
                if (resultCode == -1) {
                    refreshData = true;
                    break;
                }
                break;
            case REQUEST_CODE_PREAPPROVAL /*902*/:
                if (resultCode == 10) {
                    showSnackbar(C0880R.string.ro_preapprove_sheet_success);
                } else if (resultCode == 11) {
                    showSnackbar(C0880R.string.ro_block_dates_sheet_success);
                }
                refreshData = true;
                break;
        }
        if (refreshData) {
            refreshCalendar();
            loadData();
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.Ignore;
    }

    private static void logImpression(final Context context, final TripInformationProvider provider2) {
        AirbnbApplication.instance(context).component().navigationAnalytics().trackImpression(new NavigationLoggingElement() {
            public NavigationTag getNavigationTrackingTag() {
                return NavigationTag.HostReservationObject;
            }

            public Strap getNavigationTrackingParams() {
                Strap strap = Strap.make().mo11638kv("message_thread_id", provider2.getThreadId()).mo11639kv(GuestRecoveryActivityIntents.EXTRA_RESERVATION_STATUS, ReservationStatusDisplay.forHost(provider2).getString(context));
                if (provider2.hasReservation()) {
                    strap.mo11638kv("reservation_id", provider2.getReservation().getId());
                }
                return strap;
            }
        });
    }
}
