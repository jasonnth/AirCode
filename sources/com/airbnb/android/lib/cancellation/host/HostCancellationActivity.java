package com.airbnb.android.lib.cancellation.host;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.ActivityOptionsCompat;
import android.support.p000v4.util.Pair;
import butterknife.ButterKnife;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.enums.ReservationCancellationReason;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.intents.ThreadFragmentIntents;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.Incentive;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.requests.ReservationRequest;
import com.airbnb.android.core.requests.ReservationRequest.Format;
import com.airbnb.android.core.responses.ReservationResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.cancellation.host.CancellationAlterReservationFragment.Listener;
import com.airbnb.android.utils.Strap;
import icepick.State;
import p032rx.Observer;

public class HostCancellationActivity extends AirActivity implements Listener, CancellationDatesUnavailableFragment.Listener, CancellationExtenuatingCircumstancesFragment.Listener, CancellationGuestCancelFragment.Listener, CancellationOtherFragment.Listener, CancellationPenaltiesFragment.Listener, CancellationUncomfortableBehaviorFragment.Listener, CancellationUndergoingMaintenanceFragment.Listener, HostCancellationReasonsFragment.Listener, PenaltyFreeCancellationTrialFragment.Listener {
    private static final String ARG_RESERVATION = "reservation";
    @State
    String confirmationCode;
    @State
    Reservation reservation;
    final RequestListener<ReservationResponse> reservationListener = new C0699RL().onResponse(HostCancellationActivity$$Lambda$1.lambdaFactory$(this)).onError(HostCancellationActivity$$Lambda$2.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$0(HostCancellationActivity hostCancellationActivity, ReservationResponse response) {
        hostCancellationActivity.reservation = response.reservation;
        hostCancellationActivity.showCancellationFragment();
    }

    public static Intent intentForReservation(Context context, Reservation reservation2) {
        Check.notNull(reservation2);
        return new Intent(context, HostCancellationActivity.class).putExtra("reservation", reservation2);
    }

    public void showCancellationFragment() {
        if (!this.reservation.getCheckinDate().isAfter(AirDate.today())) {
            navigateToFragment(LateCancellationFragment.newInstance());
        } else if (this.reservation.hasIncentive(Incentive.PENALTY_FREE_CANCELLATION_TRIAL)) {
            navigateToFragment(PenaltyFreeCancellationTrialFragment.newInstance(this.reservation));
        } else {
            navigateToFragment(CancellationPenaltiesFragment.newInstance(this.reservation, false));
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        ButterKnife.bind((Activity) this);
        if (savedInstanceState == null) {
            this.reservation = (Reservation) getIntent().getParcelableExtra("reservation");
            if (this.reservation == null) {
                this.confirmationCode = getIntent().getStringExtra("confirmation_code");
                ReservationRequest.forConfirmationCode(this.confirmationCode, Format.Host).withListener((Observer) this.reservationListener).skipCache().execute(this.requestManager);
                return;
            }
            showCancellationFragment();
        }
    }

    private void navigateToFragment(AirFragment f) {
        showFragment(f, C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }

    private void navigateToFinishFragment(ReservationCancellationReason reason, String message) {
        navigateToFragment(CancellationOverviewFragment.newInstance(this.reservation, reason, message));
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    public void goToDatesUnavailableFlow() {
        navigateToFragment(new CancellationDatesUnavailableFragment());
    }

    public void goToAlterReservationFlow() {
        navigateToFragment(new CancellationAlterReservationFragment());
    }

    public void goToUndergoingMaintenanceFlow() {
        navigateToFragment(new CancellationUndergoingMaintenanceFragment());
    }

    public void goToExtenuatingCircumstancesFlow() {
        navigateToFragment(new CancellationExtenuatingCircumstancesFragment());
    }

    public void goToGuestCancelFlow() {
        navigateToFragment(new CancellationGuestCancelFragment());
    }

    public void goToUncomfortableBehaviorFlow() {
        navigateToFragment(new CancellationUncomfortableBehaviorFragment());
    }

    public void goToOtherFlow() {
        navigateToFragment(new CancellationOtherFragment());
    }

    public void onContinueToReasons() {
        navigateToFragment(HostCancellationReasonsFragment.newInstance(this.reservation.getStartDate(), this.reservation.getEndDate()));
    }

    public void onKeepReservation() {
        finish();
    }

    public void onViewPenalties() {
        showModal(CancellationPenaltiesFragment.newModalInstance(this.reservation, this.reservation.hasIncentive(Incentive.PENALTY_FREE_CANCELLATION_TRIAL)), C0880R.C0882id.content_container, C0880R.C0882id.modal_container, true);
    }

    public void onConfirmDatesUnavailable() {
        navigateToFinishFragment(ReservationCancellationReason.Unavailable, null);
    }

    public void onConfirmExtenuatingCircumstances() {
        navigateToFinishFragment(ReservationCancellationReason.Emergency, null);
    }

    public void onConfirmUncomfortableBehavior(String message) {
        navigateToFinishFragment(ReservationCancellationReason.Concerned, message);
    }

    public void onConfirmGuestCancel() {
        startActivity(ThreadFragmentIntents.newIntent(this, (long) this.reservation.getThreadId(), InboxType.Host));
    }

    public void onConfirmAlterReservation() {
        startActivity(ReactNativeIntents.intentForAlterations(this, this.reservation, true), ActivityOptionsCompat.makeSceneTransitionAnimation(this, new Pair[0]).toBundle());
    }

    public void onConfirmOther(String message) {
        navigateToFinishFragment(ReservationCancellationReason.Other, message);
    }

    public void onConfirmUndergoingMaintenance() {
        navigateToFinishFragment(ReservationCancellationReason.UndergoingMaintenance, null);
    }

    public Strap getLoggingParams() {
        return Strap.make().mo11638kv("reservation_id", this.reservation.getId()).mo11638kv("listing_id", this.reservation.getListing().getId());
    }
}
