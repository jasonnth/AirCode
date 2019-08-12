package com.airbnb.android.lib.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.p000v4.app.ActivityOptionsCompat;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.util.Pair;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.activities.AutoAirActivity;
import com.airbnb.android.core.analytics.MagicalWifiAnalytics;
import com.airbnb.android.core.analytics.TripsAnalytics;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.DLSCancelReservationFragment;
import com.airbnb.android.core.fragments.DLSCancellationPolicyFragment;
import com.airbnb.android.core.fragments.DLSHouseRulesFragment;
import com.airbnb.android.core.fragments.LocalAttractionsFragment;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.intents.DLSCancelReservationActivityIntents;
import com.airbnb.android.core.intents.HelpCenterIntents;
import com.airbnb.android.core.intents.P3ActivityIntents;
import com.airbnb.android.core.intents.PaidAmenityIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.intents.ThreadFragmentIntents;
import com.airbnb.android.core.intents.UserProfileIntents;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.models.C5990Guidebook;
import com.airbnb.android.core.models.HelpThread;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.core.requests.InquiryRequest;
import com.airbnb.android.core.requests.LocalAttractionsRequest;
import com.airbnb.android.core.requests.ReservationRequest;
import com.airbnb.android.core.requests.ReservationRequest.Format;
import com.airbnb.android.core.responses.InquiryResponse;
import com.airbnb.android.core.responses.LocalAttractionsResponse;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.NetworkUtil.hostWifiNetworkAddedCallback;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.DLSReservationObjectActivity;
import com.airbnb.android.lib.activities.InviteGuestsActivity;
import com.airbnb.android.lib.activities.PayForPendingReservationActivity;
import com.airbnb.android.lib.adapters.ReservationObjectAdapter;
import com.airbnb.android.lib.adapters.ReservationObjectAdapter.Listener;
import com.airbnb.android.lib.analytics.ROAnalytics;
import com.airbnb.android.lib.fragments.price_breakdown.PriceBreakdownFragment;
import com.airbnb.android.lib.paidamenities.activities.GuestPendingAmenityActivity;
import com.airbnb.android.lib.share.ShareYourTripToWechatFragment;
import com.airbnb.android.lib.tripassistant.GetHelpThreadForReservationRequest;
import com.airbnb.android.lib.tripassistant.HelpThreadActivity;
import com.airbnb.android.lib.tripassistant.HelpThreadsResponse;
import com.airbnb.android.utils.CallHelper;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.State;
import p032rx.Observer;

public class DLSReservationObjectFragment extends AirFragment implements hostWifiNetworkAddedCallback {
    private static final String ARG_CONFIRMATION_CODE = "confirmation_code";
    private static final String ARG_RESERVATION_ID = "reservation_id";
    private static final String ARG_THREAD_ID = "thread_id";
    private static final int REQUEST_CANCEL_PENDING = 993;
    private static final int REQUEST_CODE_ALTERATION = 996;
    private static final int REQUEST_CODE_CANCEL_RESERVATION = 994;
    private static final int REQUEST_CODE_SUBMIT_PAYMENT = 992;
    private static final int REQUEST_CONNECT_WIFI = 997;
    public static final int RESPONSE_CODE_GO_ALTERATION = 996;
    public static final int RESPONSE_CODE_GO_MESSAGE = 991;
    private ReservationObjectAdapter adapter;
    DebugSettings debugSettings;
    @State
    Boolean hasLocalAttractions;
    @State
    HelpThread helpThread;
    final RequestListener<HelpThreadsResponse> helpThreadRequestListener = new C0699RL().onResponse(DLSReservationObjectFragment$$Lambda$5.lambdaFactory$(this)).build();
    @State
    TripInformationProvider informationProvider;
    final RequestListener<InquiryResponse> inquiryListener = new C0699RL().onResponse(DLSReservationObjectFragment$$Lambda$3.lambdaFactory$(this)).onError(DLSReservationObjectFragment$$Lambda$4.lambdaFactory$(this)).build();
    @State
    boolean isLoading;
    @State
    boolean isWifiBroadcastReceiverRegistered = false;
    final RequestListener<LocalAttractionsResponse> localAttractionsRequestListener = new C0699RL().onResponse(DLSReservationObjectFragment$$Lambda$6.lambdaFactory$(this)).build();
    @BindView
    RecyclerView recyclerView;
    private final Listener reservationActionListener = new Listener() {
        public void goToUpdatePayment(Reservation reservation) {
            DLSReservationObjectFragment.this.startActivityForResult(PayForPendingReservationActivity.intentForReservation(DLSReservationObjectFragment.this.getActivity(), reservation), DLSReservationObjectFragment.REQUEST_CODE_SUBMIT_PAYMENT);
        }

        public void goToAlterations(Reservation reservation, boolean hasPendingAlteration) {
            if (reservation.isMobileNativeAlterationDisabled()) {
                DLSReservationObjectFragment.this.displayError(C0880R.string.cannot_change_reservation);
                return;
            }
            ROAnalytics.trackROItineraryClickAlterReservation(reservation.getId(), reservation, (long) reservation.getThreadId(), hasPendingAlteration);
            DLSReservationObjectFragment.this.startActivityForResult(ReactNativeIntents.intentForAlterations(DLSReservationObjectFragment.this.getActivity(), reservation, false), 996, ActivityOptionsCompat.makeSceneTransitionAnimation(DLSReservationObjectFragment.this.getActivity(), new Pair[0]).toBundle());
        }

        public void goToHost(User host) {
            DLSReservationObjectFragment.this.startActivity(UserProfileIntents.intentForUser(DLSReservationObjectFragment.this.getContext(), host));
        }

        public void goToListing(Listing listing) {
            DLSReservationObjectFragment.this.startActivity(P3ActivityIntents.withListing(DLSReservationObjectFragment.this.getContext(), listing, "reservation_object"));
        }

        public void goToDirections(Reservation reservation) {
            Listing listing = reservation.getListing();
            TripsAnalytics.trackDirectionsView(reservation, reservation.getListing().getDirections());
            if (TextUtils.isEmpty(listing.getDirections())) {
                Intent intent = DLSDirectionsFragment.getMapIntent(DLSReservationObjectFragment.this.getActivity(), listing);
                if (intent.resolveActivity(DLSReservationObjectFragment.this.getActivity().getPackageManager()) != null) {
                    DLSReservationObjectFragment.this.startActivity(intent);
                } else {
                    DLSReservationObjectFragment.this.displayError(C0880R.string.no_maps);
                }
            } else {
                DLSReservationObjectFragment.this.getReservationObjectActivity().showModal(DLSDirectionsFragment.newInstance(listing));
            }
        }

        public void goToHouseManual(String houseManual) {
            DLSReservationObjectFragment.this.getReservationObjectActivity().showModal(KonaHouseManualFragment.newInstance(houseManual));
        }

        public void goToGuidebook(C5990Guidebook guidebook) {
            DLSReservationObjectFragment.this.startActivity(WebViewIntentBuilder.newBuilder(DLSReservationObjectFragment.this.getActivity(), guidebook.getGuidebookUrl(DLSReservationObjectFragment.this.getActivity())).title(guidebook.getTitle()).toIntent());
        }

        public void goToLocalAttractions(Listing listing) {
            DLSReservationObjectFragment.this.startActivity(AutoAirActivity.intentForFragment(DLSReservationObjectFragment.this.getActivity(), LocalAttractionsFragment.class, LocalAttractionsFragment.bundleWithListing(listing)));
        }

        public void goToCancellationPolicy(String policyKey, Reservation reservation) {
            TripsAnalytics.trackCancellationPolicyView(reservation, policyKey);
            DLSReservationObjectFragment.this.getReservationObjectActivity().showModal(DLSCancellationPolicyFragment.newInstancePolicyOnly(policyKey));
        }

        public void goToCancel(Reservation reservation) {
            TripsAnalytics.trackCancelView(reservation);
            if (!reservation.isAccepted()) {
                ZenDialog.builder().withBodyText(C0880R.string.cancel_reservation_request_question).withDualButton(C0880R.string.f1211no, 0, C0880R.string.yes, 993, (Fragment) DLSReservationObjectFragment.this).create().show(DLSReservationObjectFragment.this.getFragmentManager(), (String) null);
            } else if (reservation.isMobileNativeAlterationDisabled()) {
                DLSReservationObjectFragment.this.displayError(C0880R.string.cannot_cancel_reservation);
            } else {
                DLSReservationObjectFragment.this.startActivityForResult(DLSCancelReservationActivityIntents.intent((Context) DLSReservationObjectFragment.this.getAirActivity(), reservation), DLSReservationObjectFragment.REQUEST_CODE_CANCEL_RESERVATION);
            }
        }

        public void goToRetract(Reservation reservation) {
            DLSReservationObjectFragment.this.startActivityForResult(RetractRequestFragment.intent(DLSReservationObjectFragment.this.getActivity(), reservation), DLSReservationObjectFragment.REQUEST_CODE_CANCEL_RESERVATION);
        }

        public void goToHelpCenter() {
            DLSReservationObjectFragment.this.startActivity(HelpCenterIntents.intentForHelpCenterWithTripAssistant(DLSReservationObjectFragment.this.getContext()));
        }

        public void goToShareTrip(Reservation reservation) {
            DLSReservationObjectFragment.this.startActivity(ShareYourTripToWechatFragment.newIntentFromRO(DLSReservationObjectFragment.this.getContext(), reservation));
        }

        public void goToInviteGuests(Reservation reservation) {
            DLSReservationObjectFragment.this.startActivity(InviteGuestsActivity.newIntent(DLSReservationObjectFragment.this.getContext(), reservation.getConfirmationCode()));
        }

        public void goToPriceBreakdown(Reservation reservation) {
            TripsAnalytics.trackPaymentBreakdownView(reservation);
            DLSReservationObjectFragment.this.getReservationObjectActivity().showModal(PriceBreakdownFragment.forGuestReservation(reservation));
        }

        public void goToMessageThread() {
            DLSReservationObjectFragment.this.startActivity(ThreadFragmentIntents.newIntent(DLSReservationObjectFragment.this.getActivity(), DLSReservationObjectFragment.this.informationProvider.getThreadId(), InboxType.Guest));
        }

        public void goToCallHost() {
            String phoneNumber = DLSReservationObjectFragment.this.informationProvider.getPrimaryHost().getPhone();
            if (TextUtils.isEmpty(phoneNumber)) {
                DLSReservationObjectFragment.this.displayError(C0880R.string.phone_number_not_found);
            } else {
                CallHelper.dial(DLSReservationObjectFragment.this.getContext(), phoneNumber);
            }
        }

        public void goToRules(Listing listing, Reservation reservation) {
            AirDate checkout = null;
            TripsAnalytics.trackHouseRulesView(reservation, listing);
            AirDate checkin = reservation == null ? null : reservation.getCheckinDate();
            if (reservation != null) {
                checkout = reservation.getCheckoutDate();
            }
            DLSReservationObjectFragment.this.getReservationObjectActivity().showModal(DLSHouseRulesFragment.newInstance(listing, checkin, checkout));
        }

        public void goToWifi(Reservation reservation, Listing listing) {
            ROAnalytics.trackROItineraryClickWifi(reservation.getId(), reservation, (long) reservation.getThreadId());
            WifiZenDialogFragment.newInstance(listing.getWirelessInfo(), 997).show(DLSReservationObjectFragment.this.getFragmentManager(), "");
        }

        public void goToHelpThread(HelpThread helpThread) {
            DLSReservationObjectFragment.this.startActivity(HelpThreadActivity.intentForId(DLSReservationObjectFragment.this.getContext(), helpThread.getId()));
        }

        public void goToExtraServices(Reservation reservation) {
            if (reservation.hasPaidAmenityOrders()) {
                DLSReservationObjectFragment.this.startActivity(GuestPendingAmenityActivity.intent(DLSReservationObjectFragment.this.getContext(), reservation.getConfirmationCode(), reservation.getListing().getId(), (long) reservation.getThreadId()));
            } else {
                DLSReservationObjectFragment.this.startActivity(PaidAmenityIntents.purchaseAmenities(DLSReservationObjectFragment.this.getContext(), reservation.getConfirmationCode(), reservation.getListing().getId(), (long) reservation.getThreadId()));
            }
        }
    };
    final RequestListener<ReservationResponse> reservationListener = new C0699RL().onResponse(DLSReservationObjectFragment$$Lambda$1.lambdaFactory$(this)).onError(DLSReservationObjectFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    AirToolbar toolbar;
    private final BroadcastReceiver wifiConnectedBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (((NetworkInfo) intent.getParcelableExtra("networkInfo")).getState() == NetworkInfo.State.CONNECTED) {
                new SnackbarWrapper().view(DLSReservationObjectFragment.this.getView()).body(DLSReservationObjectFragment.this.getString(C0880R.string.wifi_connected_to_wifi)).buildAndShow();
                context.unregisterReceiver(this);
            }
        }
    };

    public static DLSReservationObjectFragment newInstance(String confirmationCode) {
        Check.notEmpty(confirmationCode, "Confirmation code cannot be empty");
        return (DLSReservationObjectFragment) ((FragmentBundleBuilder) FragmentBundler.make(new DLSReservationObjectFragment()).putString("confirmation_code", confirmationCode)).build();
    }

    public static DLSReservationObjectFragment newInstanceForReservationId(long reservationId) {
        Check.argument(reservationId != -1, "Reservation ID cannot be -1");
        return (DLSReservationObjectFragment) ((FragmentBundleBuilder) FragmentBundler.make(new DLSReservationObjectFragment()).putLong("reservation_id", reservationId)).build();
    }

    public static DLSReservationObjectFragment newInstanceForThread(long threadId) {
        return (DLSReservationObjectFragment) ((FragmentBundleBuilder) FragmentBundler.make(new DLSReservationObjectFragment()).putLong("thread_id", Check.validId(threadId))).build();
    }

    public void onHostWifiNetworkAdded() {
        MagicalWifiAnalytics.trackNetworkAdded(this.informationProvider.getReservation());
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new ReservationObjectAdapter(getContext(), this.reservationActionListener, savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_dls_reservation_object, container, false);
        bindViews(view);
        ((AirbnbGraph) AirbnbApplication.instance(getContext()).component()).inject(this);
        setToolbar(this.toolbar);
        updateAdapterData();
        if (savedInstanceState == null) {
            fetchReservation(true);
        }
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    private void updateAdapterData() {
        this.adapter.refreshReservation(this.informationProvider, this.isLoading, this.hasLocalAttractions, this.helpThread);
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    private void fetchReservation(boolean allowCache) {
        ReservationRequest reservationRequest;
        String confirmationCode = getArguments().getString("confirmation_code");
        long reservationId = getArguments().getLong("reservation_id", -1);
        boolean hasConfirmationCode = !TextUtils.isEmpty(confirmationCode);
        if (hasConfirmationCode || reservationId != -1) {
            if (this.informationProvider == null || !allowCache) {
                this.isLoading = true;
            }
            if (hasConfirmationCode) {
                TripsAnalytics.trackReservationLoad(confirmationCode);
            } else {
                TripsAnalytics.trackReservationLoad(reservationId);
            }
            if (hasConfirmationCode) {
                reservationRequest = ReservationRequest.forConfirmationCode(confirmationCode, Format.Guest);
            } else {
                reservationRequest = ReservationRequest.forReservationId(reservationId, Format.Guest);
            }
            reservationRequest.doubleResponse(allowCache).withListener(this.reservationListener).execute(this.requestManager);
        } else {
            long threadId = Check.validId(getArguments().getLong("thread_id", -1));
            TripsAnalytics.trackInquiryLoad(threadId);
            new InquiryRequest(threadId).withListener((Observer) this.inquiryListener).execute(this.requestManager);
        }
        updateAdapterData();
    }

    /* access modifiers changed from: private */
    public void handleResponse(TripInformationProvider informationProvider2) {
        this.informationProvider = informationProvider2;
        requestLocalAttractionsIfNeeded();
        requestHelpThreadIfNeeded();
        this.isLoading = false;
        updateAdapterData();
        if (informationProvider2.hasReservation()) {
            NetworkUtil.setupWifiAlarmIfNecessary(informationProvider2.getReservation(), getActivity());
        }
    }

    /* access modifiers changed from: private */
    public void handleErrorResponse(NetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(getView(), e);
        this.isLoading = false;
        updateAdapterData();
    }

    private void requestHelpThreadIfNeeded() {
        if (FeatureToggles.isTripAssistantEnabled() && this.helpThread == null && this.informationProvider != null && this.informationProvider.hasReservation() && !this.informationProvider.getReservation().isSharedItinerary() && !this.requestManager.hasRequest((BaseRequestListener<T>) this.helpThreadRequestListener, GetHelpThreadForReservationRequest.class)) {
            new GetHelpThreadForReservationRequest(this.informationProvider.getReservation().getConfirmationCode()).doubleResponse().withListener(this.helpThreadRequestListener).execute(this.requestManager);
        }
    }

    static /* synthetic */ void lambda$new$2(DLSReservationObjectFragment dLSReservationObjectFragment, HelpThreadsResponse response) {
        boolean z;
        boolean z2 = true;
        HelpThread oldValue = dLSReservationObjectFragment.helpThread;
        dLSReservationObjectFragment.helpThread = null;
        long maxId = 0;
        for (int i = 0; i < response.helpThreads.size(); i++) {
            if (maxId == 0 || maxId < ((HelpThread) response.helpThreads.get(i)).getId()) {
                dLSReservationObjectFragment.helpThread = (HelpThread) response.helpThreads.get(i);
                maxId = dLSReservationObjectFragment.helpThread.getId();
            }
        }
        if (oldValue == null) {
            z = true;
        } else {
            z = false;
        }
        if (dLSReservationObjectFragment.helpThread != null) {
            z2 = false;
        }
        if (z != z2) {
            dLSReservationObjectFragment.updateAdapterData();
        }
    }

    private void requestLocalAttractionsIfNeeded() {
        if (this.hasLocalAttractions == null && !this.requestManager.hasRequest((BaseRequestListener<T>) this.localAttractionsRequestListener, LocalAttractionsRequest.class)) {
            if (this.informationProvider.getListing() == null) {
                String confirmationCode = getArguments().getString("confirmation_code");
                long reservationId = getArguments().getLong("reservation_id", -1);
                BugsnagWrapper.notify((Throwable) new IllegalStateException("Listing is null, but should never be null. Confirmation Code: " + confirmationCode + " Reservation ID: " + reservationId + " Thread ID :" + getArguments().getLong("thread_id", -1)));
                return;
            }
            new LocalAttractionsRequest(this.informationProvider.getListing().getId(), this.localAttractionsRequestListener).doubleResponse().execute(this.requestManager);
        }
    }

    static /* synthetic */ void lambda$new$3(DLSReservationObjectFragment dLSReservationObjectFragment, LocalAttractionsResponse response) {
        boolean hasAttractionsInResponse = !response.localAttractions.isEmpty();
        if (dLSReservationObjectFragment.hasLocalAttractions == null || dLSReservationObjectFragment.hasLocalAttractions.booleanValue() != hasAttractionsInResponse) {
            dLSReservationObjectFragment.hasLocalAttractions = Boolean.valueOf(hasAttractionsInResponse);
            dLSReservationObjectFragment.updateAdapterData();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_SUBMIT_PAYMENT /*992*/:
                fetchReservation(false);
                return;
            case 993:
                if (resultCode == -1) {
                    startActivityForResult(DLSCancelReservationFragment.intent(getActivity(), this.informationProvider.getReservation()), REQUEST_CODE_CANCEL_RESERVATION);
                    return;
                }
                return;
            case REQUEST_CODE_CANCEL_RESERVATION /*994*/:
                onCancelReservationResult(resultCode);
                return;
            case 996:
                fetchReservation(false);
                return;
            case 997:
                connectToWifi();
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }

    private void onCancelReservationResult(int resultCode) {
        switch (resultCode) {
            case -1:
                fetchReservation(false);
                return;
            case 0:
                return;
            case RESPONSE_CODE_GO_MESSAGE /*991*/:
                this.reservationActionListener.goToMessageThread();
                return;
            case 996:
                Reservation reservation = this.informationProvider.getReservation();
                this.reservationActionListener.goToAlterations(reservation, reservation.hasPendingAlterations());
                return;
            default:
                throw new IllegalStateException("Unsupported onCancelReservationResult result code :" + resultCode);
        }
    }

    private void connectToWifi() {
        this.isWifiBroadcastReceiverRegistered |= NetworkUtil.connectToWifiNetwork(this.informationProvider.getListing().getWirelessInfo(), getActivity(), this, this.isWifiBroadcastReceiverRegistered ? this.wifiConnectedBroadcastReceiver : null);
    }

    /* access modifiers changed from: private */
    public DLSReservationObjectActivity getReservationObjectActivity() {
        return (DLSReservationObjectActivity) getActivity();
    }

    /* access modifiers changed from: private */
    public void displayError(int errorString) {
        new SnackbarWrapper().view(getView()).title(getString(C0880R.string.error), true).body(getString(errorString)).buildAndShow();
    }
}
