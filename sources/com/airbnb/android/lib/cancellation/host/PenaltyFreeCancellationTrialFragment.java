package com.airbnb.android.lib.cancellation.host;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.Incentive;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.StandardRow;
import icepick.State;

public class PenaltyFreeCancellationTrialFragment extends AirFragment {
    private static final String ARG_RESERVATION = "reservation";
    private static final int MAX_LINES_DESCRIPTION = 3;
    @BindView
    StandardRow description;
    private Listener listener;
    @State
    Reservation reservation;
    @BindView
    AirToolbar toolbar;

    public interface Listener {
        void onContinueToReasons();

        void onKeepReservation();

        void onViewPenalties();
    }

    public static PenaltyFreeCancellationTrialFragment newInstance(Reservation reservation2) {
        return (PenaltyFreeCancellationTrialFragment) ((FragmentBundleBuilder) FragmentBundler.make(new PenaltyFreeCancellationTrialFragment()).putParcelable("reservation", reservation2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C0880R.layout.fragment_penalty_free_cancellation_trial, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (savedInstanceState == null) {
            this.reservation = (Reservation) getArguments().getParcelable("reservation");
        }
        if (this.reservation.getIncentive(Incentive.PENALTY_FREE_CANCELLATION_TRIAL) != null) {
            this.description.setTitle((CharSequence) this.reservation.getIncentive(Incentive.PENALTY_FREE_CANCELLATION_TRIAL).getDescription());
            this.description.setTitleMaxLine(3);
        }
        return view;
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.listener = (Listener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement Listener interface");
        }
    }

    @OnClick
    public void onClickContinue() {
        this.listener.onContinueToReasons();
    }

    @OnClick
    public void onClickViewPenalties() {
        this.listener.onViewPenalties();
    }

    @OnClick
    public void onClickKeepReservation() {
        this.listener.onKeepReservation();
    }
}
