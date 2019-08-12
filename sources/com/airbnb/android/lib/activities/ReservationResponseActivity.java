package com.airbnb.android.lib.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.MenuItem;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.DeclineReason;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.fragments.EditTextFragment.EditTextFragmentBuilder;
import com.airbnb.android.core.fragments.EditTextFragment.EditTextFragmentController;
import com.airbnb.android.core.fragments.EditTextFragment.EditTextFragmentListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.ManageListingIntents;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.core.requests.DeclineReservationRequest;
import com.airbnb.android.core.requests.ReservationRequest;
import com.airbnb.android.core.requests.ReservationRequest.Format;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.utils.InstantBookUpsellManager;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.SettingDeepLink;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationDeclineConfirmationFragment;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationDeclineDetailsFragment;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationDeclineLandingFragment;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationDeclineTipsFragment;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseBaseFragment.MessageType;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseBaseFragment.ReservationResponseNavigator;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseLandingFragment;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseLogger;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseLogger.ReservationResponseDataProvider;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationUpdateListener;
import com.airbnb.android.lib.fragments.reservationresponse.handlers.ReservationDeclineForDatesAdapter;
import com.airbnb.android.lib.fragments.reservationresponse.handlers.ReservationDeclineForGuestAdapter;
import com.airbnb.android.lib.fragments.reservationresponse.handlers.ReservationDeclineForListingAdapter;
import com.airbnb.android.lib.fragments.reservationresponse.handlers.ReservationDeclineForReservationDetailsAdapter;
import com.airbnb.android.lib.reservationresponse.AcceptReservationFragment;
import icepick.State;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class ReservationResponseActivity extends AirActivity implements EditTextFragmentController, ReservationResponseNavigator, ReservationResponseDataProvider {
    public static final String ARG_FLOW_MODE = "ro_flow_mode_enum";
    public static final String ARG_RESERVATION = "ro_reservation";
    public static final String ARG_THREAD_ID = "ro_thread_id";
    private static final int REJECTIONS_TIPS = 1001;
    @BindView
    ViewGroup contentContainer;
    @State
    DeclineReason declineReason;
    final RequestListener<ReservationResponse> declineReservationRequestListener = new C0699RL().onResponse(ReservationResponseActivity$$Lambda$1.lambdaFactory$(this)).onError(ReservationResponseActivity$$Lambda$2.lambdaFactory$(this)).build();
    private final EditTextFragmentListener editTextFragmentListener = ReservationResponseActivity$$Lambda$5.lambdaFactory$(this);
    InstantBookUpsellManager instantBookUpsellManager;
    @State
    boolean isUpdateRequestOut;
    ReservationResponseLogger logger;
    @State
    MessageType messageTypeToEdit;
    @State
    boolean requestIsDeclined;
    @State
    Reservation reservation;
    final RequestListener<ReservationResponse> reservationRequestListener = new C0699RL().onResponse(ReservationResponseActivity$$Lambda$3.lambdaFactory$(this)).onError(ReservationResponseActivity$$Lambda$4.lambdaFactory$(this)).build();
    @State
    Long threadId;
    private final List<ReservationUpdateListener> updateListeners = new ArrayList();

    private enum ReservationResponseFlowMode {
        DeclineFlow,
        AcceptDeclineFlow
    }

    static /* synthetic */ void lambda$new$0(ReservationResponseActivity reservationResponseActivity, ReservationResponse response) {
        reservationResponseActivity.logger.onReservationDeclined(reservationResponseActivity);
        reservationResponseActivity.reservation.setRejectionTips(response.reservation.getRejectionTips());
        reservationResponseActivity.requestIsDeclined = true;
        reservationResponseActivity.updateRegisteredListenersWithRequestResult(true);
        reservationResponseActivity.instantBookUpsellManager.onRequestDecliend(reservationResponseActivity.getReservation().getListing().getId());
        reservationResponseActivity.showFragment(new ReservationDeclineConfirmationFragment());
    }

    static /* synthetic */ void lambda$new$1(ReservationResponseActivity reservationResponseActivity, AirRequestNetworkException e) {
        reservationResponseActivity.updateRegisteredListenersWithRequestResult(false);
        NetworkUtil.tryShowErrorWithSnackbar(reservationResponseActivity.contentContainer, e);
    }

    static /* synthetic */ void lambda$new$2(ReservationResponseActivity reservationResponseActivity, ReservationResponse response) {
        reservationResponseActivity.reservation.setRejectionTips(response.reservation.getRejectionTips());
        reservationResponseActivity.updateRegisteredListenersWithRequestResult(true);
    }

    static /* synthetic */ void lambda$new$3(ReservationResponseActivity reservationResponseActivity, AirRequestNetworkException e) {
        reservationResponseActivity.updateRegisteredListenersWithRequestResult(false);
        NetworkUtil.tryShowErrorWithSnackbar(reservationResponseActivity.contentContainer, e);
    }

    private static Intent newIntentForResponse(Context context, Reservation reservation2, long threadId2) {
        return new Intent(context, ReservationResponseActivity.class).putExtra(ARG_RESERVATION, reservation2).putExtra(ARG_THREAD_ID, threadId2);
    }

    public static Intent newIntentForDecline(Context context, Reservation reservation2, long threadId2) {
        return newIntentForResponse(context, reservation2, threadId2).putExtra(ARG_FLOW_MODE, ReservationResponseFlowMode.DeclineFlow);
    }

    public static Intent newIntentForResponseWithNewAcceptBehavior(Context context, Reservation reservation2, long threadId2) {
        return newIntentForResponse(context, reservation2, threadId2).putExtra(ARG_FLOW_MODE, ReservationResponseFlowMode.AcceptDeclineFlow);
    }

    public ReservationResponseNavigator getNavigator() {
        return this;
    }

    public Reservation getReservation() {
        return this.reservation;
    }

    public Long getThreadId() {
        return this.threadId;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        ButterKnife.bind((Activity) this);
        ((AirbnbGraph) AirbnbApplication.instance(this).component()).inject(this);
        if (savedInstanceState == null) {
            this.reservation = (Reservation) getIntent().getParcelableExtra(ARG_RESERVATION);
            this.threadId = Long.valueOf(getIntent().getLongExtra(ARG_THREAD_ID, -1));
            if (((ReservationResponseFlowMode) getIntent().getSerializableExtra(ARG_FLOW_MODE)) == ReservationResponseFlowMode.DeclineFlow) {
                showDeclineReasonsPage();
            } else {
                showAcceptOrDeclinePage();
            }
        }
    }

    public void onAccept() {
        showFragment(AcceptReservationFragment.newInstanceForTripProvider(TripInformationProvider.create(this.reservation)));
    }

    public void onDoneWithAccept() {
        setResult(-1);
        finish();
    }

    public void onDecline() {
        showDeclineReasonsPage();
    }

    public void onDeclineReasonSelected(DeclineReason reason) {
        this.logger.onDeclineReasonSelected(this, reason);
        this.messageTypeToEdit = null;
        showFragment(ReservationDeclineDetailsFragment.newInstance(reason));
    }

    public EditTextFragmentListener getEditTextFragmentListener() {
        return this.editTextFragmentListener;
    }

    public void onRequestDeclined(DeclineReason declineReason2, String publicMessage, String privateMessage) {
        this.declineReason = declineReason2;
        this.logger.onDeclineSelected(this);
        updateRegisteredListenersWithRequestStart();
        new DeclineReservationRequest(getReservation().getId(), declineReason2).airbnbMessage(privateMessage).guestMessage(publicMessage).blockDates(declineReason2.requiresBlockingDates()).withListener((Observer) this.declineReservationRequestListener).execute(this.requestManager);
    }

    public void onDoneWithDecline() {
        setResult(-1);
        finish();
    }

    public void onBackPressed() {
        if (!this.requestIsDeclined || getReservationDeclineTipsFragment() != null) {
            super.onBackPressed();
        } else {
            onDoneWithDecline();
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != 16908332 || !this.requestIsDeclined) {
            return super.onOptionsItemSelected(item);
        }
        onDoneWithDecline();
        return true;
    }

    public void onShowTips() {
        this.logger.onShowTipsSelected(this);
        showFragment(ReservationDeclineTipsFragment.newInstance());
    }

    public void onTipSelected(SettingDeepLink settingDeepLink, String key) {
        this.logger.onBookingTipSelected(this, key);
        startActivityForResult(ManageListingIntents.intentForExistingListingSetting(this, this.reservation.getListing().getId(), settingDeepLink, true), 1001);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1001 && resultCode == -1) {
            updateTips();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void addUpdateListener(ReservationUpdateListener listener) {
        this.updateListeners.add(listener);
    }

    public void removeUpdateListener(ReservationUpdateListener listener) {
        this.updateListeners.remove(listener);
    }

    public void showEditTextFragment(MessageType messageType) {
        this.messageTypeToEdit = messageType;
        EditTextFragmentBuilder editTextFragmentBuilder = new EditTextFragmentBuilder().setText(getReservationDeclineDetailsFragment().getMessage(messageType)).saveMenuText(C0880R.string.save);
        if (this.messageTypeToEdit == MessageType.MessageToGuest) {
            editTextFragmentBuilder.setUser(getReservation().getGuest()).setHeaderSubtitle(getString(C0880R.string.ro_response_decline_edit_text_hint, new Object[]{getReservation().getGuest().getFirstName()})).setNavigationTrackingTag(NavigationTag.ReservationRejectionEditMessage).showHeader(true);
        } else {
            editTextFragmentBuilder.setHint(getString(C0880R.string.ro_response_decline_edit_text_hint_message_to_airbnb));
        }
        showFragment(editTextFragmentBuilder.build());
    }

    public boolean hasPendingRequest() {
        return this.isUpdateRequestOut;
    }

    public DeclineReason getDeclineReason() {
        return this.declineReason;
    }

    public AirEpoxyAdapter getDeclineDetailsAdapter(DeclineReason declineReason2) {
        switch (declineReason2) {
            case UnavailableDates:
                return new ReservationDeclineForDatesAdapter(this);
            case GuestIsNotAFit:
                return new ReservationDeclineForGuestAdapter(this);
            case ListingNotAFit:
                return new ReservationDeclineForListingAdapter(this);
            case ReservationDetailsNotAFit:
                return new ReservationDeclineForReservationDetailsAdapter(this);
            default:
                throw new IllegalArgumentException(declineReason2.toString());
        }
    }

    public String getDeclineMessage(MessageType messageType) {
        return getReservationDeclineDetailsFragment().getMessage(messageType);
    }

    private void showDeclineReasonsPage() {
        showFragment(ReservationDeclineLandingFragment.newInstance());
    }

    private void showAcceptOrDeclinePage() {
        showFragment(ReservationResponseLandingFragment.newInstance());
    }

    private void showFragment(Fragment fragment) {
        showFragment(fragment, C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true, fragment.getClass().getSimpleName());
    }

    private ReservationDeclineDetailsFragment getReservationDeclineDetailsFragment() {
        return (ReservationDeclineDetailsFragment) getSupportFragmentManager().findFragmentByTag(ReservationDeclineDetailsFragment.class.getSimpleName());
    }

    private ReservationDeclineTipsFragment getReservationDeclineTipsFragment() {
        return (ReservationDeclineTipsFragment) getSupportFragmentManager().findFragmentByTag(ReservationDeclineTipsFragment.class.getSimpleName());
    }

    private void updateTips() {
        updateRegisteredListenersWithRequestStart();
        ReservationRequest.forReservationId(getReservation().getId(), Format.HostRejection).withListener((Observer) this.reservationRequestListener).execute(this.requestManager);
    }

    private void updateRegisteredListenersWithRequestResult(boolean isSuccess) {
        this.isUpdateRequestOut = false;
        for (ReservationUpdateListener listener : this.updateListeners) {
            listener.onUpdateFinished(isSuccess);
        }
    }

    private void updateRegisteredListenersWithRequestStart() {
        this.isUpdateRequestOut = true;
        for (ReservationUpdateListener listener : this.updateListeners) {
            listener.onUpdateStarted();
        }
    }

    static /* synthetic */ void lambda$new$4(ReservationResponseActivity reservationResponseActivity, String message) {
        reservationResponseActivity.logger.onMessageSaveSelected(reservationResponseActivity, message, reservationResponseActivity.messageTypeToEdit == MessageType.MessageToAirbnb);
        reservationResponseActivity.getSupportFragmentManager().popBackStackImmediate();
        reservationResponseActivity.getReservationDeclineDetailsFragment().saveMessage(reservationResponseActivity.messageTypeToEdit, message);
        reservationResponseActivity.messageTypeToEdit = null;
    }
}
