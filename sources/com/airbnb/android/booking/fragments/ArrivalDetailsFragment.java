package com.airbnb.android.booking.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.p336n2.ArrivalTimeSelectionView;
import com.airbnb.android.booking.p336n2.ArrivalTimeSelectionViewItem;
import com.airbnb.android.core.models.ArrivalDetails;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.State;

public class ArrivalDetailsFragment extends BookingBaseFragment {
    private static final String ARG_ARRIVAL_DETAILS = "arg_arrival_time_options";
    private static final String ARG_HOST_CHECK_IN_MESSAGE = "arg_check_in_time_phrase";
    private static final String ARG_HOST_NAME = "arg_host_name";
    private static final String ARG_RESERVATION_CHECK_IN = "arg_reservation_check_in";
    @BindView
    AirButton applyButton;
    @State
    ArrivalDetails arrivalDetails;
    @State
    String checkInTimePhrase;
    @State
    String hostName;
    private String invalidTimeErrorMessage;
    @State
    String selectedCheckIn;
    @BindView
    ArrivalTimeSelectionView selectionView;
    @BindView
    AirToolbar toolbar;

    public static ArrivalDetailsFragment newInstance(Listing listing, Reservation reservation, String reservationCheckIn) {
        return (ArrivalDetailsFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new ArrivalDetailsFragment()).putString(ARG_RESERVATION_CHECK_IN, reservationCheckIn)).putParcelable(ARG_ARRIVAL_DETAILS, reservation.getArrivalDetails())).putString(ARG_HOST_CHECK_IN_MESSAGE, listing.getHostCheckInTimePhrase())).putString(ARG_HOST_NAME, reservation.getHost().getFirstName())).build();
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
        View view = inflater.inflate(C0704R.layout.fragment_arrival_details, container, false);
        bindViews(view);
        this.invalidTimeErrorMessage = getString(C0704R.string.p4_arrival_time_sheet_snackbar_msg, this.checkInTimePhrase, this.hostName);
        this.selectionView.setCheckInOptions(this.arrivalDetails.getValidCheckinTimeSelectionOptions());
        this.selectionView.setSelectedArrivalTime(this.selectedCheckIn);
        this.selectionView.setSelectionSheetOnItemClickedListener(ArrivalDetailsFragment$$Lambda$1.lambdaFactory$(this));
        if (!TextUtils.isEmpty(this.checkInTimePhrase)) {
            this.selectionView.setSubtitle(this.checkInTimePhrase);
        }
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$0(ArrivalDetailsFragment arrivalDetailsFragment, ArrivalTimeSelectionViewItem item) {
        if (!item.isInstantBookable()) {
            arrivalDetailsFragment.showSnackbar(arrivalDetailsFragment.invalidTimeErrorMessage);
        } else {
            arrivalDetailsFragment.hideSnackbar();
        }
    }

    public void onPause() {
        super.onPause();
        hideSnackbar();
    }

    @OnClick
    public void confirmArrivalTime() {
        if (TextUtils.isEmpty(this.selectionView.getSelectedArrivalTime())) {
            getBookingActivity().doneWithNoResults();
            return;
        }
        this.applyButton.setState(AirButton.State.Loading);
        getBookingActivity().doneWithArrivalDetails(this.selectionView.getSelectedArrivalTime());
    }

    public void onReservationUpdate() {
        super.onReservationUpdate();
        if (isResumed()) {
            this.applyButton.setState(AirButton.State.Success);
        }
    }

    public void onUpdateError(String errorMessage) {
        super.onUpdateError(errorMessage);
        if (isResumed()) {
            this.applyButton.setState(AirButton.State.Normal);
            if (!TextUtils.isEmpty(errorMessage)) {
                showSnackbar(errorMessage);
            }
        }
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
}
