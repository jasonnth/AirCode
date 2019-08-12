package com.airbnb.android.lib.cancellation;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.cancellation.CancellationData;
import com.airbnb.android.core.enums.CancellationReason;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.fragments.DLSCancelReservationFragment;
import com.airbnb.android.core.intents.DLSCancelReservationActivityIntents;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.DLSReservationObjectFragment;
import com.airbnb.android.utils.BundleBuilder;
import icepick.State;

public class DLSCancelReservationActivity extends AirActivity {
    private static final int REQUEST_CODE_CANCEL_RESERVATION = 994;
    @State
    CancellationData cancellationData;
    @State
    String confirmationCode;
    @State
    Reservation reservation;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        if (savedInstanceState == null) {
            this.reservation = (Reservation) getIntent().getParcelableExtra("reservation");
            String cancellationPolicy = null;
            if (this.reservation != null) {
                this.confirmationCode = this.reservation.getConfirmationCode();
                cancellationPolicy = this.reservation.getCancellationPolicyKey();
            } else {
                this.confirmationCode = getIntent().getStringExtra("confirmation_code");
            }
            this.cancellationData = CancellationData.builder().confirmationCode(this.confirmationCode).policyKey(cancellationPolicy).isHost(false).isRetracting(getIntent().getBooleanExtra(DLSCancelReservationActivityIntents.ARG_IS_RETRACTING, false)).isPositiveRefund(false).build();
            showFragmentForStepWithAnimation(CancelReservationStep.Reason);
        }
    }

    public void showFragment(Fragment fragment, boolean addToBackStack) {
        showFragment(fragment, C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, addToBackStack);
    }

    private void showFragmentForStepWithAnimation(CancelReservationStep step) {
        CancellationAnalytics.trackPage(step.pageName, this.cancellationData);
        showFragment(Fragment.instantiate(this, step.fragmentClass.getCanonicalName(), ((BundleBuilder) new BundleBuilder().putParcelable(DLSCancelReservationBaseFragment.ARG_CANCELLATION_DATA, this.cancellationData)).toBundle()), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }

    public void onStepFinished(CancelReservationStep step, CancellationData data) {
        this.cancellationData = data;
        switch (step) {
            case Summary:
                tryCancelReservation();
                return;
            case Date:
                setResult(996);
                finish();
                return;
            case Asked:
                setResult(DLSReservationObjectFragment.RESPONSE_CODE_GO_MESSAGE);
                finish();
                return;
            case Reason:
                onReasonSelected();
                return;
            case Other:
            case Emergency:
                showFragmentForStepWithAnimation(CancelReservationStep.Summary);
                return;
            default:
                throw new IllegalStateException("Unsupported CancellationStep:" + step.toString());
        }
    }

    private void onReasonSelected() {
        CancellationReason cancellationReason = this.cancellationData.cancellationReason();
        switch (cancellationReason) {
            case Dates:
                showFragmentForStepWithAnimation(CancelReservationStep.Date);
                return;
            case Emergency:
                showFragmentForStepWithAnimation(CancelReservationStep.Emergency);
                return;
            case Asked:
                showFragmentForStepWithAnimation(CancelReservationStep.Asked);
                return;
            case Other:
                showFragmentForStepWithAnimation(CancelReservationStep.Other);
                return;
            case Unnecessary:
            case Accident:
            case Uncomfortable:
            case Dislike:
                showFragmentForStepWithAnimation(CancelReservationStep.Summary);
                return;
            default:
                throw new IllegalStateException("Unsupported cancallation reason: " + cancellationReason.toString());
        }
    }

    private void tryCancelReservation() {
        startActivityForResult(TransparentActionBarActivity.intentForFragment(this, DLSCancelReservationFragment.newInstance(this.reservation, this.cancellationData)), REQUEST_CODE_CANCEL_RESERVATION);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE_CANCEL_RESERVATION /*994*/:
                setResult(resultCode);
                finish();
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }
}
