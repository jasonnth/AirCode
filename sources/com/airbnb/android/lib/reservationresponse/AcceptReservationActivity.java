package com.airbnb.android.lib.reservationresponse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.airbnb.android.core.activities.SolitAirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.SouthKoreanCancellationPolicyFragment;
import com.airbnb.android.lib.fragments.SouthKoreanCancellationPolicyFragment.Listener;
import icepick.State;

public class AcceptReservationActivity extends SolitAirActivity implements Listener {
    private static final String ARG_PROVIDER = "arg_provider";
    private static final String SK_CANCELLATION_TAG = SouthKoreanCancellationPolicyFragment.class.getSimpleName();
    private static final String TAG = AcceptReservationFragment.class.getSimpleName();
    @State
    TripInformationProvider provider;

    public static Intent getIntentForTripProvider(Context context, TripInformationProvider provider2) {
        return new Intent(context, AcceptReservationActivity.class).putExtra(ARG_PROVIDER, provider2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        ButterKnife.bind((Activity) this);
        if (savedInstanceState == null) {
            this.provider = (TripInformationProvider) getIntent().getParcelableExtra(ARG_PROVIDER);
            if (this.provider.isKoreanStrictBooking()) {
                showFragment(SouthKoreanCancellationPolicyFragment.newInstance(C0880R.string.accept_reservation_south_korean_cancellation_policy, C0880R.string.accept_south_korean_cancellation_policy_host_agreement, this.provider), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, false, SK_CANCELLATION_TAG);
                return;
            }
            showAcceptReservationFragment(false);
        }
    }

    public void onAcceptCancellationAgreement() {
        showAcceptReservationFragment(true);
    }

    public void onBackPressed() {
        AcceptReservationFragment fragment = (AcceptReservationFragment) getSupportFragmentManager().findFragmentByTag(TAG);
        if (fragment == null || !fragment.isRequestAccepted()) {
            super.onBackPressed();
            return;
        }
        setResult(-1);
        finish();
    }

    private void showAcceptReservationFragment(boolean hostAgreedSouthKoreanPreapproval) {
        showFragment(AcceptReservationFragment.newInstanceForTripProvider(this.provider, hostAgreedSouthKoreanPreapproval), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true, TAG);
    }
}
