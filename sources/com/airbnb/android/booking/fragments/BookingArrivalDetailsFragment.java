package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.analytics.KonaBookingAnalytics;
import com.airbnb.android.booking.controller.BookingController;
import com.airbnb.android.booking.p336n2.ArrivalTimeSelectionView;
import com.airbnb.android.booking.p336n2.ArrivalTimeSelectionViewItem;
import com.airbnb.android.core.analytics.BookingAnalytics;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.ArrivalDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.requests.booking.UpdateArrivalDetailsRequest;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.jitney.event.logging.P4FlowPage.p173v1.C2467P4FlowPage;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.BookingNavigationView;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.State;
import p032rx.Observer;

public class BookingArrivalDetailsFragment extends BookingV2BaseFragment {
    private static final String ARG_ARRIVAL_DETAILS = "arg_arrival_time_options";
    private static final String ARG_HOST_CHECK_IN_MESSAGE = "arg_check_in_time_phrase";
    private static final String ARG_HOST_NAME = "arg_host_name";
    private static final String ARG_RESERVATION_CHECK_IN = "arg_reservation_check_in";
    @State
    ArrivalDetails arrivalDetails;
    final RequestListener<ReservationResponse> arrivalDetailsUpdateListener = new RequestListener<ReservationResponse>() {
        public void onResponse(ReservationResponse data) {
            BookingArrivalDetailsFragment.this.onReservationUpdate(data);
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            BookingArrivalDetailsFragment.this.navView.hideLoader();
            NetworkUtil.tryShowRetryableErrorWithSnackbar(BookingArrivalDetailsFragment.this.getView(), BookingArrivalDetailsFragment$1$$Lambda$1.lambdaFactory$(this));
        }
    };
    @State
    String checkInTimePhrase;
    @State
    String hostName;
    private String invalidTimeErrorMessage;
    @BindView
    BookingNavigationView navView;
    @State
    String selectedCheckIn;
    @BindView
    ArrivalTimeSelectionView selectionView;
    private Snackbar snackbar;
    @BindView
    AirToolbar toolbar;

    public static BookingArrivalDetailsFragment newInstance(Listing listing, Reservation reservation, String reservationCheckIn) {
        return (BookingArrivalDetailsFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new BookingArrivalDetailsFragment()).putString(ARG_RESERVATION_CHECK_IN, reservationCheckIn)).putParcelable(ARG_ARRIVAL_DETAILS, reservation.getArrivalDetails())).putString(ARG_HOST_CHECK_IN_MESSAGE, listing.getHostCheckInTimePhrase())).putString(ARG_HOST_NAME, reservation.getHost().getFirstName())).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.checkInTimePhrase = getArguments().getString(ARG_HOST_CHECK_IN_MESSAGE);
            this.hostName = getArguments().getString(ARG_HOST_NAME);
            this.selectedCheckIn = getArguments().getString(ARG_RESERVATION_CHECK_IN);
            this.arrivalDetails = (ArrivalDetails) getArguments().getParcelable(ARG_ARRIVAL_DETAILS);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0704R.layout.fragment_booking_arrival_details, container, false);
        bindViews(view);
        this.invalidTimeErrorMessage = getString(C0704R.string.p4_arrival_time_sheet_snackbar_msg, this.checkInTimePhrase, this.hostName);
        this.selectionView.setCheckInOptions(this.arrivalDetails.getValidCheckinTimeSelectionOptions());
        this.selectionView.setSelectedArrivalTime(this.selectedCheckIn);
        this.selectionView.setSelectionSheetOnItemClickedListener(BookingArrivalDetailsFragment$$Lambda$1.lambdaFactory$(this));
        if (!TextUtils.isEmpty(this.checkInTimePhrase)) {
            this.selectionView.setSubtitle(this.checkInTimePhrase);
        }
        this.selectionView.setKicker(getController().getP4Steps());
        setUpNavButton(this.navView, C0704R.string.next);
        this.navView.setButtonOnClickListener(BookingArrivalDetailsFragment$$Lambda$2.lambdaFactory$(this));
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(BookingArrivalDetailsFragment bookingArrivalDetailsFragment, ArrivalTimeSelectionViewItem item) {
        if (!item.isInstantBookable()) {
            bookingArrivalDetailsFragment.showSnackbar(bookingArrivalDetailsFragment.invalidTimeErrorMessage);
        } else {
            bookingArrivalDetailsFragment.hideSnackbar();
        }
    }

    static /* synthetic */ void lambda$onCreateView$1(BookingArrivalDetailsFragment bookingArrivalDetailsFragment, View v) {
        bookingArrivalDetailsFragment.logNavigationClick();
        bookingArrivalDetailsFragment.confirmArrivalTime();
    }

    public void onPause() {
        super.onPause();
        hideSnackbar();
    }

    /* access modifiers changed from: private */
    public void confirmArrivalTime() {
        this.navView.showLoader();
        if (TextUtils.isEmpty(this.selectionView.getSelectedArrivalTime())) {
            getController().showNextStep(BookingController.getBookingStepLoader(this.navView));
            return;
        }
        this.reservationDetails = this.reservationDetails.toBuilder().checkInHour(this.selectionView.getSelectedArrivalTime()).build();
        KonaBookingAnalytics.trackUpdate(getNavigationTrackingTag().trackingName, this.reservationDetails, this.mobileSearchSessionId);
        this.bookingJitneyLogger.checkinSelectTime(this.reservationDetails, this.reservation.isInstantBookable());
        new UpdateArrivalDetailsRequest(this.reservationDetails).withListener((Observer) this.arrivalDetailsUpdateListener).execute(this.requestManager);
    }

    public void onUpdateError(NetworkException e) {
        if (isResumed()) {
            String errorMessage = NetworkUtil.errorDetails(e);
            if (!TextUtils.isEmpty(errorMessage)) {
                showSnackbar(errorMessage);
            }
        }
    }

    public void onUpdated() {
        getController().showNextStep(BookingController.getBookingStepLoader(this.navView));
    }

    private boolean isSnackbarShown() {
        return this.snackbar != null && this.snackbar.isShownOrQueued();
    }

    private void showSnackbar(String message) {
        if (!isSnackbarShown()) {
            this.snackbar = new SnackbarWrapper().view(getView()).body(message).duration(0).buildAndShow();
        }
    }

    private void hideSnackbar() {
        if (isSnackbarShown()) {
            this.snackbar.dismiss();
            this.snackbar = null;
        }
    }

    public Strap getNavigationTrackingParams() {
        return BookingAnalytics.getP4NavigationTrackingParams(true);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.BookingCheckinTime;
    }

    public C2467P4FlowPage getP4FlowPage() {
        return C2467P4FlowPage.BookingCheckinTime;
    }
}
