package com.airbnb.android.guestrecovery.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import butterknife.ButterKnife;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.intents.GuestRecoveryActivityIntents;
import com.airbnb.android.core.models.ReservationStatus;
import com.airbnb.android.guestrecovery.C6392R;
import com.airbnb.android.guestrecovery.fragments.GuestRecoveryFragment;

public class GuestRecoveryActivity extends AirActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        GuestRecoveryFragment fragment;
        super.onCreate(savedInstanceState);
        setContentView(C6392R.layout.activity_simple_fragment);
        ButterKnife.bind((Activity) this);
        if (savedInstanceState == null) {
            String confirmationCode = getIntent().getStringExtra("confirmation_code");
            long reservationId = getIntent().getLongExtra("reservation_id", -1);
            ReservationStatus reservationStatus = (ReservationStatus) getIntent().getParcelableExtra(GuestRecoveryActivityIntents.EXTRA_RESERVATION_STATUS);
            if (confirmationCode != null) {
                fragment = GuestRecoveryFragment.intentForConfirmationCode(confirmationCode, reservationStatus);
            } else {
                fragment = GuestRecoveryFragment.intentForReservationId(reservationId, reservationStatus);
            }
            showFragment(fragment);
        }
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    public void showFragment(Fragment fragment) {
        showFragment(fragment, C6392R.C6394id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }
}
