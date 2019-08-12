package com.airbnb.android.core.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.analytics.TripsAnalytics;
import com.airbnb.android.core.cancellation.CancellationData;
import com.airbnb.android.core.events.ReservationUpdatedEvent;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.requests.CancelReservationRequest;
import com.airbnb.android.core.responses.CancelReservationResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.HeroMarquee;
import com.airbnb.p027n2.components.LoadingView;
import icepick.State;
import p032rx.Observer;

public class DLSCancelReservationFragment extends AirFragment {
    private static final String ARG_CANCELLATION_DATA = "cancellation_data";
    private static final String ARG_RESERVATION = "reservation";
    final RequestListener<CancelReservationResponse> cancelListener = new C0699RL().onResponse(DLSCancelReservationFragment$$Lambda$1.lambdaFactory$(this)).onError(DLSCancelReservationFragment$$Lambda$2.lambdaFactory$(this)).build();
    @State
    CancellationData cancellationData;
    @BindView
    HeroMarquee heroMarquee;
    boolean isReservationAccepted;
    @BindView
    LoadingView loadingView;
    @State
    Reservation reservation;
    @BindView
    AirToolbar toolbar;

    public static DLSCancelReservationFragment newInstance(Reservation reservation2) {
        return newInstance(reservation2, CancellationData.builder().confirmationCode(reservation2.getConfirmationCode()).isHost(false).isRetracting(false).isPositiveRefund(false).build());
    }

    public static Intent intent(Activity activity, Reservation reservation2) {
        return TransparentActionBarActivity.intentForFragment(activity, newInstance(reservation2));
    }

    public static DLSCancelReservationFragment newInstance(Reservation reservation2, CancellationData cancellationData2) {
        return (DLSCancelReservationFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new DLSCancelReservationFragment()).putParcelable("reservation", reservation2)).putParcelable(ARG_CANCELLATION_DATA, cancellationData2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0716R.layout.fragment_dls_cancel_reservation, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (getAirActivity() instanceof TransparentActionBarActivity) {
            ((TransparentActionBarActivity) getAirActivity()).getAirToolbar().setVisibility(8);
        }
        this.reservation = (Reservation) getArguments().getParcelable("reservation");
        this.cancellationData = (CancellationData) getArguments().getParcelable(ARG_CANCELLATION_DATA);
        Check.notNull(this.reservation);
        this.isReservationAccepted = this.reservation.isAccepted();
        TripsAnalytics.trackCancelConfirm(this.reservation);
        cancelReservation();
        return view;
    }

    public void cancelReservation() {
        if (!MiscUtils.isUserAMonkey()) {
            new CancelReservationRequest(this.cancellationData).withListener((Observer) this.cancelListener).execute(this.requestManager);
        }
    }

    static /* synthetic */ void lambda$new$1(DLSCancelReservationFragment dLSCancelReservationFragment, CancelReservationResponse response) {
        dLSCancelReservationFragment.mBus.post(new ReservationUpdatedEvent());
        dLSCancelReservationFragment.stopLoading();
        dLSCancelReservationFragment.heroMarquee.setFirstButtonClickListener(DLSCancelReservationFragment$$Lambda$3.lambdaFactory$(dLSCancelReservationFragment));
        if (dLSCancelReservationFragment.isReservationAccepted) {
            dLSCancelReservationFragment.heroMarquee.setCaption((CharSequence) dLSCancelReservationFragment.getCancellationFeeDetails(dLSCancelReservationFragment.mCurrencyHelper.formatNativeCurrency((double) response.reservation.getCancellationGuestFeeNative(), true), dLSCancelReservationFragment.mCurrencyHelper.formatNativeCurrency((double) response.reservation.getCancellationRefundNative(), true)));
            return;
        }
        dLSCancelReservationFragment.heroMarquee.setTitle(C0716R.string.request_canceled);
    }

    static /* synthetic */ void lambda$new$2(DLSCancelReservationFragment dLSCancelReservationFragment, AirRequestNetworkException error) {
        String message;
        String networkMessage = NetworkUtil.errorMessage(error);
        if (TextUtils.isEmpty(networkMessage)) {
            message = dLSCancelReservationFragment.getString(C0716R.string.error_cancel_reservation);
        } else {
            message = networkMessage;
        }
        ZenDialog.createSingleButtonDialog(C0716R.string.error, message, C0716R.string.okay).show(dLSCancelReservationFragment.getFragmentManager(), (String) null);
    }

    private String getCancellationFeeDetails(String guestFee, String refund) {
        if (!this.cancellationData.isPositiveRefund()) {
            return "";
        }
        if (!TextUtils.isEmpty(this.cancellationData.paymentProvider()) && !TextUtils.isEmpty(this.cancellationData.paymentAccountPostfix())) {
            return getString(C0716R.string.f1072xba643b8d, refund, this.cancellationData.paymentProvider(), this.cancellationData.paymentAccountPostfix());
        } else if (!TextUtils.isEmpty(this.cancellationData.paymentProvider())) {
            return getString(C0716R.string.reservation_cancelled_description_guest_with_provider, refund, this.cancellationData.paymentProvider());
        } else if (!TextUtils.isEmpty(this.cancellationData.paymentAccountPostfix())) {
            return getString(C0716R.string.reservation_cancelled_description_guest_with_postfix, refund, this.cancellationData.paymentAccountPostfix());
        } else {
            return getString(C0716R.string.f1073x97b4cee5, refund);
        }
    }

    /* access modifiers changed from: private */
    public void setResultOkAndFinish() {
        getActivity().setResult(-1);
        getActivity().finish();
    }

    private void stopLoading() {
        this.loadingView.setVisibility(8);
        this.heroMarquee.setVisibility(0);
        this.toolbar.scrollWith(C0716R.C0718id.scroll_view);
    }
}
