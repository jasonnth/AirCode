package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.DLSReservationObjectFragment;

@Deprecated
public class DLSReservationObjectActivity extends AirActivity {
    private static final String ARG_CONFIRMATION_CODE = "confirmation_code";
    private static final String ARG_RESERVATION_ID = "reservation_id";
    private static final String ARG_THREAD_ID = "thread_id";

    public static Intent intentForThread(Context context, long threadId) {
        return new Intent(context, DLSReservationObjectActivity.class).putExtra("thread_id", Check.validId(threadId));
    }

    public static Intent intentForReservationId(Context context, long reservationId) {
        return new Intent(context, DLSReservationObjectActivity.class).putExtra("reservation_id", reservationId);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        if (savedInstanceState == null) {
            String confirmationCode = getIntent().getStringExtra("confirmation_code");
            long threadId = getIntent().getLongExtra("thread_id", -1);
            long reservationId = getIntent().getLongExtra("reservation_id", -1);
            if (confirmationCode != null) {
                showFragment(DLSReservationObjectFragment.newInstance(confirmationCode));
            } else if (reservationId != -1) {
                showFragment(DLSReservationObjectFragment.newInstanceForReservationId(reservationId));
            } else {
                showFragment(DLSReservationObjectFragment.newInstanceForThread(threadId));
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    public void showFragment(Fragment fragment) {
        showFragment(fragment, C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true, fragment.getTag());
    }

    public void showModal(Fragment fragment) {
        showModal(fragment, C0880R.C0882id.content_container, C0880R.C0882id.modal_container, true, fragment.getTag());
    }
}
