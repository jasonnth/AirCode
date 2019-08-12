package com.airbnb.android.lib.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.activities.TransparentActionBarActivity;
import com.airbnb.android.core.fragments.AirDialogFragment;
import com.airbnb.android.core.fragments.DLSCancelReservationFragment;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.components.StandardRow;
import icepick.State;

public class RetractRequestFragment extends AirDialogFragment {
    private static final String ARG_RESERVATION = "reservation";
    private static final int REQUEST_CODE_CANCEL_RESERVATION = 994;
    @BindView
    StandardRow dateRow;
    @BindView
    SimpleTextRow listingRow;
    @State
    Reservation reservation;
    @BindView
    AirToolbar toolbar;

    public static Intent intent(Activity activity, Reservation reservation2) {
        return TransparentActionBarActivity.intentForFragment(activity, (RetractRequestFragment) ((FragmentBundleBuilder) FragmentBundler.make(new RetractRequestFragment()).putParcelable("reservation", reservation2)).build());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_retract_request, container, false);
        ButterKnife.bind(this, view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        if (getAirActivity() instanceof TransparentActionBarActivity) {
            ((TransparentActionBarActivity) getAirActivity()).getAirToolbar().setVisibility(8);
        }
        if (savedInstanceState == null) {
            this.reservation = (Reservation) getArguments().getParcelable("reservation");
        }
        this.listingRow.setText((CharSequence) this.reservation.getListing().getName());
        this.dateRow.setPlaceholderText((CharSequence) this.reservation.getStartDate().getDateSpanString(getContext(), this.reservation.getEndDate()));
        return view;
    }

    @OnClick
    public void cancelRequest() {
        startActivityForResult(DLSCancelReservationFragment.intent(getActivity(), this.reservation), REQUEST_CODE_CANCEL_RESERVATION);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CANCEL_RESERVATION) {
            getActivity().setResult(resultCode);
            getActivity().finish();
        }
    }
}
