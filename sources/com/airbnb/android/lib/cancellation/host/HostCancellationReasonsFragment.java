package com.airbnb.android.lib.cancellation.host;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.StandardRow;
import icepick.State;

public class HostCancellationReasonsFragment extends AirFragment {
    public static final String ARG_CHECK_IN = "check_in";
    public static final String ARG_CHECK_OUT = "check_out";
    @State
    AirDate checkInDate;
    @State
    AirDate checkOutDate;
    @BindView
    StandardRow datesUnavailableRow;
    private Listener listener;
    @BindView
    AirToolbar toolbar;

    public interface Listener {
        Strap getLoggingParams();

        void goToAlterReservationFlow();

        void goToDatesUnavailableFlow();

        void goToExtenuatingCircumstancesFlow();

        void goToGuestCancelFlow();

        void goToOtherFlow();

        void goToUncomfortableBehaviorFlow();

        void goToUndergoingMaintenanceFlow();
    }

    public static HostCancellationReasonsFragment newInstance(AirDate checkInDate2, AirDate checkOutDate2) {
        return (HostCancellationReasonsFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new HostCancellationReasonsFragment()).putParcelable("check_in", checkInDate2)).putParcelable("check_out", checkOutDate2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C0880R.layout.fragment_host_cancellation_reasons, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (savedInstanceState == null) {
            this.checkInDate = (AirDate) getArguments().getParcelable("check_in");
            this.checkOutDate = (AirDate) getArguments().getParcelable("check_out");
        }
        setupDatesUnavailableRow();
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

    private void setupDatesUnavailableRow() {
        this.datesUnavailableRow.setSubtitleText((CharSequence) getString(C0880R.string.cancellation_dates_unavailable_row_caption, this.checkInDate.getDateSpanString(getContext(), this.checkOutDate)));
    }

    @OnClick
    public void onClickDatesUnavailableRow() {
        this.listener.goToDatesUnavailableFlow();
    }

    @OnClick
    public void onClickAlterReservationsRow() {
        this.listener.goToAlterReservationFlow();
    }

    @OnClick
    public void onClickUndergoingMaintenanceRow() {
        this.listener.goToUndergoingMaintenanceFlow();
    }

    @OnClick
    public void onClickExtenuatingCircumstancesRow() {
        this.listener.goToExtenuatingCircumstancesFlow();
    }

    @OnClick
    public void onClickGuestCancelRow() {
        this.listener.goToGuestCancelFlow();
    }

    @OnClick
    public void onClickUncomfortableBehaviorRow() {
        this.listener.goToUncomfortableBehaviorFlow();
    }

    @OnClick
    public void onClickOtherRow() {
        this.listener.goToOtherFlow();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.HostCancellationReasonSelector;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mix(this.listener.getLoggingParams());
    }
}
