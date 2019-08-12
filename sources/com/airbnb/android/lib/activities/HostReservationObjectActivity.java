package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.enums.ROLaunchSource;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.HostReservationObjectIntents.ArgumentConstants;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.HostReservationObjectFragment;

public class HostReservationObjectActivity extends AirActivity implements ArgumentConstants {
    public static Intent intentForConfirmationCode(Context context, String code, ROLaunchSource source) {
        Check.notNull(code);
        return new Intent(context, HostReservationObjectActivity.class).putExtra("launch_source", source).putExtra("confirmation_code", code);
    }

    public static Intent intentForReservation(Context context, Reservation reservation, ROLaunchSource source) {
        Check.notNull(reservation);
        return new Intent(context, HostReservationObjectActivity.class).putExtra("reservation", reservation);
    }

    public static Intent intentForThread(Context context, long threadId, ROLaunchSource source) {
        return new Intent(context, HostReservationObjectActivity.class).putExtra("launch_source", source).putExtra("thread_id", Check.validId(threadId));
    }

    public static Intent intentForReservationId(Context context, long id, ROLaunchSource source) {
        Check.state(id != -1);
        return new Intent(context, HostReservationObjectActivity.class).putExtra("launch_source", source).putExtra("reservation_id", id);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        Fragment f;
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        if (savedInstanceState == null) {
            Reservation reservation = (Reservation) getIntent().getParcelableExtra("reservation");
            String confirmationCode = getIntent().getStringExtra("confirmation_code");
            long reservationId = getIntent().getLongExtra("reservation_id", -1);
            long threadId = getIntent().getLongExtra("thread_id", -1);
            ROLaunchSource source = (ROLaunchSource) getIntent().getSerializableExtra("launch_source");
            if (reservation != null) {
                f = HostReservationObjectFragment.newInstanceForReservation(reservation, source);
            } else if (confirmationCode != null) {
                f = HostReservationObjectFragment.newInstanceForConfirmationCode(confirmationCode, source);
            } else if (threadId > 0) {
                f = HostReservationObjectFragment.newInstanceForThread(threadId, source);
            } else if (reservationId > 0) {
                f = HostReservationObjectFragment.newInstanceForReservationId(reservationId, source);
            } else {
                throw new IllegalArgumentException("No valid parameter to load RO");
            }
            showFragment(f, C0880R.C0882id.content_container, FragmentTransitionType.FadeInAndOut, true);
        }
    }

    public void showFragment(AirFragment fragment) {
        showFragment(fragment, C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true, fragment.getTag());
    }

    public void showModalFragment(AirFragment fragment) {
        showModal(fragment, C0880R.C0882id.content_container, C0880R.C0882id.modal_container, true, fragment.getTag());
    }

    public void showFragmentInModal(AirFragment fragment) {
        showFragment(fragment, C0880R.C0882id.modal_container, FragmentTransitionType.SlideInFromSide, true, fragment.getTag());
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }
}
