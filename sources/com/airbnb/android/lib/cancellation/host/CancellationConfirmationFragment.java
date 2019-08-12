package com.airbnb.android.lib.cancellation.host;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.SheetMarquee;

public class CancellationConfirmationFragment extends AirFragment {
    private static final String ARG_RESERVATION = "reservation";
    @BindView
    SheetMarquee marquee;
    Reservation reservation;

    public static CancellationConfirmationFragment newInstance(Reservation reservation2) {
        return (CancellationConfirmationFragment) ((FragmentBundleBuilder) FragmentBundler.make(new CancellationConfirmationFragment()).putParcelable("reservation", reservation2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = (ViewGroup) inflater.inflate(C0880R.layout.fragment_cancellation_confirmation, container, false);
        bindViews(view);
        this.reservation = (Reservation) getArguments().getParcelable("reservation");
        setupMarquee();
        return view;
    }

    private void setupMarquee() {
        this.marquee.setTitle(getString(C0880R.string.cancellation_confirmation_title, this.reservation.getGuest().getName(), this.reservation.getListing().getLocation()));
        this.marquee.setSubtitle(getString(C0880R.string.cancellation_confirmation_subtitle, this.mCurrencyHelper.formatNativeCurrency((double) this.reservation.getCancellationHostFeeNative(), true)));
    }

    @OnClick
    public void onClickOkay() {
        getActivity().finish();
    }
}
