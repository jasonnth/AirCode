package com.airbnb.android.lib.cancellation;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.core.requests.ReservationRequest;
import com.airbnb.android.core.requests.ReservationRequest.Format;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.cancellation.CancelReservationReasonAdapter.Listener;
import com.airbnb.android.lib.fragments.RetractRequestFragment;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.LoadingView;

public class DLSCancelReservationReasonFragment extends DLSCancelReservationBaseFragment {
    private final Listener cancelReservationReasonActionListner = DLSCancelReservationReasonFragment$$Lambda$1.lambdaFactory$(this);
    @BindView
    LoadingView loadingView;
    @BindView
    RecyclerView recyclerView;
    final RequestListener<ReservationResponse> reservationListener = new C0699RL().onResponse(DLSCancelReservationReasonFragment$$Lambda$2.lambdaFactory$(this)).onError(DLSCancelReservationReasonFragment$$Lambda$3.lambdaFactory$(this)).build();
    @BindView
    AirToolbar toolbar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_dls_cancel_reservation_reason, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (getAirActivity() instanceof TransparentActionBarActivity) {
            ((TransparentActionBarActivity) getAirActivity()).getAirToolbar().setVisibility(8);
        }
        if (this.cancelActivity.reservation != null) {
            setUpReasons();
        } else {
            this.loadingView.setVisibility(0);
            fetchReservation();
        }
        return view;
    }

    private void setUpReasons() {
        this.loadingView.setVisibility(8);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setAdapter(new CancelReservationReasonAdapter(this.cancelReservationReasonActionListner));
    }

    private void fetchReservation() {
        ReservationRequest.forConfirmationCode(this.cancellationData.confirmationCode(), Format.Guest).singleResponse().withListener(this.reservationListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void handleErrorResponse(NetworkException e) {
        NetworkUtil.toastNetworkError(getContext(), e);
        getActivity().finish();
    }

    /* access modifiers changed from: private */
    public void handleResponse(Reservation reservation) {
        this.cancelActivity.reservation = reservation;
        if (reservation.getStatus() != ReservationStatus.Accepted) {
            startActivity(RetractRequestFragment.intent(getActivity(), reservation));
            getActivity().finish();
        } else if (reservation.getStatus() != ReservationStatus.Accepted || !this.cancellationData.isRetracting()) {
            this.cancellationData = this.cancellationData.toBuilder().policyKey(reservation.getCancellationPolicyKey()).build();
            setUpReasons();
        } else {
            Toast.makeText(getContext(), C0880R.string.retract_accepted_reservation, 1000).show();
            getActivity().finish();
        }
    }
}
