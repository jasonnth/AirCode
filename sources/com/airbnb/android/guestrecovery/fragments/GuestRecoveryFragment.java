package com.airbnb.android.guestrecovery.fragments;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.intents.GuestRecoveryActivityIntents;
import com.airbnb.android.core.intents.SearchActivityIntents;
import com.airbnb.android.core.models.ExploreTab.Tab;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.core.models.SearchParams;
import com.airbnb.android.core.models.SimilarListing;
import com.airbnb.android.core.requests.ListingsReplacementRequest;
import com.airbnb.android.core.requests.ReservationRequest;
import com.airbnb.android.core.requests.ReservationRequest.Format;
import com.airbnb.android.core.responses.ListingsReplacementResponse;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.guestrecovery.C6392R;
import com.airbnb.android.guestrecovery.GuestRecoveryBindings;
import com.airbnb.android.guestrecovery.GuestRecoveryComponent.Builder;
import com.airbnb.android.guestrecovery.adapter.GuestRecoveryEpoxyController;
import com.airbnb.android.guestrecovery.logging.GuestRecoveryLogger;
import com.airbnb.android.guestrecovery.utils.GuestRecoveryUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.RecyclerViewUtils;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import icepick.State;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class GuestRecoveryFragment extends AirFragment {
    private static final int SIMILAR_LISTINGS_COUNT = 4;
    AirbnbAccountManager accountManager;
    @State
    String confirmationCode;
    @BindView
    CoordinatorLayout coordinatorLayout;
    private GuestRecoveryEpoxyController guestRecoveryEpoxyController;
    @State
    boolean hasSetReservation;
    @State
    boolean hasSetSimilarListings;
    final RequestListener<ListingsReplacementResponse> listingReplacementListener = new C0699RL().onResponse(GuestRecoveryFragment$$Lambda$3.lambdaFactory$(this)).onError(GuestRecoveryFragment$$Lambda$4.lambdaFactory$(this)).build();
    GuestRecoveryLogger logger;
    @BindView
    AirRecyclerView recyclerView;
    @State
    Reservation reservation;
    @State
    long reservationId;
    final RequestListener<ReservationResponse> reservationListener = new C0699RL().onResponse(GuestRecoveryFragment$$Lambda$1.lambdaFactory$(this)).onError(GuestRecoveryFragment$$Lambda$2.lambdaFactory$(this)).build();
    @State
    ReservationStatus reservationStatus;
    @State
    ArrayList<SimilarListing> similarListings;
    @BindView
    AirToolbar toolbar;

    public static GuestRecoveryFragment intentForConfirmationCode(String confirmationCode2, ReservationStatus reservationStatus2) {
        return (GuestRecoveryFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new GuestRecoveryFragment()).putString("confirmation_code", confirmationCode2)).putParcelable(GuestRecoveryActivityIntents.EXTRA_RESERVATION_STATUS, reservationStatus2)).build();
    }

    public static GuestRecoveryFragment intentForReservationId(long reservationId2, ReservationStatus reservationStatus2) {
        return (GuestRecoveryFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new GuestRecoveryFragment()).putLong("reservation_id", reservationId2)).putParcelable(GuestRecoveryActivityIntents.EXTRA_RESERVATION_STATUS, reservationStatus2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((GuestRecoveryBindings) CoreApplication.instance(getContext()).componentProvider()).guestRecoveryComponentProvider().get()).build().inject(this);
        this.confirmationCode = getArguments().getString("confirmation_code");
        this.reservationId = getArguments().getLong("reservation_id", -1);
        this.reservationStatus = (ReservationStatus) getArguments().getParcelable(GuestRecoveryActivityIntents.EXTRA_RESERVATION_STATUS);
        if (this.reservation == null) {
            fetchReservation();
        }
        this.guestRecoveryEpoxyController = new GuestRecoveryEpoxyController(getContext(), this.reservation, this.similarListings, this.reservationStatus, this.hasSetSimilarListings, this.hasSetReservation, this.logger, this.accountManager);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6392R.layout.fragment_guest_recovery, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        RecyclerViewUtils.setTouchSlopForNestedScrolling(this.recyclerView);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setEpoxyController(this.guestRecoveryEpoxyController);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.guestRecoveryEpoxyController.requestModelBuild();
    }

    /* access modifiers changed from: private */
    public void fetchReservation() {
        ReservationRequest reservationRequest;
        boolean hasConfirmationCode = !TextUtils.isEmpty(this.confirmationCode);
        if (hasConfirmationCode || this.reservationId != -1) {
            if (hasConfirmationCode) {
                reservationRequest = ReservationRequest.forConfirmationCode(this.confirmationCode, Format.Guest);
            } else {
                reservationRequest = ReservationRequest.forReservationId(this.reservationId, Format.Guest);
            }
            reservationRequest.withListener((Observer) this.reservationListener).execute(this.requestManager);
            return;
        }
        BugsnagWrapper.throwOrNotify(new RuntimeException("Guest Recovery: Confirmation Code is null or Reservation ID is Invalid"));
    }

    private void fetchSimilarListings() {
        ListingsReplacementRequest.forGuestRecovery(this.reservation.getCheckinDate(), this.reservation.getCheckoutDate(), getGuestCount(), this.reservation.getListing().getId(), 4).withListener((Observer) this.listingReplacementListener).doubleResponse().execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void handleReservationSuccess(Reservation reservation2) {
        this.reservation = reservation2;
        this.hasSetReservation = true;
        this.guestRecoveryEpoxyController.setReservation(reservation2);
        this.guestRecoveryEpoxyController.requestModelBuild();
        fetchSimilarListings();
    }

    /* access modifiers changed from: private */
    public void handleSimilarListingSuccess(List<SimilarListing> similarListings2) {
        this.similarListings = new ArrayList<>(similarListings2);
        this.hasSetSimilarListings = true;
        this.guestRecoveryEpoxyController.setSimilarListings(this.similarListings);
        this.guestRecoveryEpoxyController.requestModelBuild();
    }

    /* access modifiers changed from: private */
    public void handleError(NetworkException e) {
        this.guestRecoveryEpoxyController.handleError();
        this.guestRecoveryEpoxyController.requestModelBuild();
        if (this.reservation == null) {
            NetworkUtil.tryShowRetryableErrorWithSnackbar(getView(), e, GuestRecoveryFragment$$Lambda$5.lambdaFactory$(this));
        }
        BugsnagWrapper.throwOrNotify(e);
    }

    private int getGuestCount() {
        GuestDetails guestDetails = this.reservation.getGuestDetails();
        if (guestDetails == null || guestDetails.totalGuestCount() == 0) {
            return this.reservation.getGuestCount();
        }
        return guestDetails.totalGuestCount();
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void browseMoreHomes() {
        SearchParams params = new SearchParams();
        params.setTabId(Tab.HOME.getTabId());
        if (this.reservation != null) {
            params.setGuestDetails(this.reservation.getGuestDetails());
            params.setCheckin(this.reservation.getCheckinDate());
            params.setCheckout(this.reservation.getCheckoutDate());
            params.setLocation(this.reservation.getListing().getLocation());
        }
        this.logger.browseMoreClick(GuestRecoveryUtils.getUserId(this.accountManager), GuestRecoveryUtils.getListingId(this.reservation), GuestRecoveryUtils.getReservationCode(this.reservation));
        startActivity(SearchActivityIntents.intentForGuestRecovery(getContext(), params));
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.RejectionRecovery;
    }
}
