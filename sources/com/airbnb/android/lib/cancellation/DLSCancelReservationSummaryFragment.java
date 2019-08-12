package com.airbnb.android.lib.cancellation;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.DLSCancellationPolicyFragment;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.models.CurrencyAmount;
import com.airbnb.android.core.models.Price.Type;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.requests.GetGuestCancellationRefundBreakdownRequest;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.cancellation.CancelReservationSummaryAdapter.Listener;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.PrimaryButton;
import icepick.State;
import java.math.BigDecimal;
import p032rx.Observer;

public class DLSCancelReservationSummaryFragment extends DLSCancelReservationBaseFragment {
    private static final int DIALOG_CONFIRM_CANCEL = 607;
    private final Listener actionListener = new Listener() {
        public void onClickPolicyLink() {
            CancellationAnalytics.trackElementClick("summary", DLSCancelReservationSummaryFragment.this.cancellationData, CancellationAnalytics.VALUE_ELEMENT_POLICY_LINK, null);
            DLSCancelReservationSummaryFragment.this.cancelActivity.showFragment(DLSCancellationPolicyFragment.newInstancePolicyOnly(DLSCancelReservationSummaryFragment.this.cancellationData.policyKey()), true);
        }

        public void onNonRefundableItemLink(Type priceType) {
            CancellationAnalytics.trackElementClick("summary", DLSCancelReservationSummaryFragment.this.cancellationData, CancellationAnalytics.VALUE_ELEMENT_NON_REFUNDABLE_ITEM, priceType.toString());
            DLSCancelReservationSummaryFragment.this.cancelActivity.showFragment(DLSCancellationPolicyFragment.newInstancePolicyOnly(DLSCancelReservationSummaryFragment.this.cancellationData.policyKey()), true);
        }
    };
    private CancelReservationSummaryAdapter adapter;
    final RequestListener<ReservationResponse> breakdownListener = new C0699RL().onResponse(DLSCancelReservationSummaryFragment$$Lambda$1.lambdaFactory$(this)).onError(DLSCancelReservationSummaryFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    PrimaryButton cancelButton;
    @BindView
    RecyclerView recyclerView;
    @State
    Reservation reservation;
    @BindView
    AirToolbar toolbar;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_dls_cancel_reservation_summary, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (savedInstanceState == null) {
            new GetGuestCancellationRefundBreakdownRequest(this.cancellationData.confirmationCode()).withListener((Observer) this.breakdownListener).execute(this.requestManager);
        }
        this.recyclerView.setHasFixedSize(true);
        this.adapter = new CancelReservationSummaryAdapter(getContext(), this.actionListener);
        this.recyclerView.setAdapter(this.adapter);
        if (this.reservation != null) {
            updateSummary();
        }
        return view;
    }

    @OnClick
    public void initiateCancellation() {
        ZenDialog.builder().withBodyText(getString(C0880R.string.cancel_reservation_confirmation_guest_question)).withDualButton(C0880R.string.f1211no, 0, C0880R.string.yes, (int) DIALOG_CONFIRM_CANCEL, (Fragment) this).create().show(getFragmentManager(), (String) null);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DIALOG_CONFIRM_CANCEL) {
            this.cancelActivity.onStepFinished(CancelReservationStep.Summary, this.cancellationData);
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void updateSummary() {
        boolean isPositiveRefund;
        CurrencyAmount refundTotal = this.reservation.getGuestCancellationRefundBreakdown().getRefundablePrice().getTotal();
        String refundAmount = refundTotal.getAmount().toString();
        if (refundTotal.getAmount().compareTo(BigDecimal.ZERO) > 0) {
            isPositiveRefund = true;
        } else {
            isPositiveRefund = false;
        }
        String paymentProvider = null;
        String paymentAccountPostfix = null;
        if (this.reservation.getLastVaultablePaymentOption() != null) {
            paymentProvider = this.reservation.getLastVaultablePaymentOption().getProviderName();
            paymentAccountPostfix = this.reservation.getLastVaultablePaymentOption().getCreditCardLastFour();
        }
        this.cancellationData = this.cancellationData.toBuilder().refundAmount(refundAmount).isPositiveRefund(isPositiveRefund).paymentProvider(paymentProvider).paymentAccountPostfix(paymentAccountPostfix).build();
        this.adapter.updateBreakdown(this.reservation);
        this.cancelButton.setVisibility(0);
    }

    static /* synthetic */ void lambda$new$0(DLSCancelReservationSummaryFragment dLSCancelReservationSummaryFragment, ReservationResponse data) {
        dLSCancelReservationSummaryFragment.reservation = data.reservation;
        dLSCancelReservationSummaryFragment.updateSummary();
    }

    static /* synthetic */ void lambda$new$1(DLSCancelReservationSummaryFragment dLSCancelReservationSummaryFragment, AirRequestNetworkException e) {
        Toast.makeText(dLSCancelReservationSummaryFragment.getActivity(), C0880R.string.cancel_breakdown_request_fail, 0).show();
        dLSCancelReservationSummaryFragment.getActivity().onBackPressed();
    }
}
