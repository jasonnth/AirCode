package com.airbnb.android.lib.reservationresponse;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.ManageListingIntents;
import com.airbnb.android.core.models.tripprovider.TripInformationProvider;
import com.airbnb.android.core.utils.InstantBookUpsellManager;
import com.airbnb.android.core.utils.SettingDeepLink;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.reservationresponse.ReservationResponseBaseFragment.ReservationResponseNavigator;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.SimpleTextRow;
import icepick.State;

public class AcceptReservationConfirmationFragment extends AirFragment {
    private static final String ARG_PROVIDER = "trip_provider";
    InstantBookUpsellManager instantBookUpsellManager;
    private ReservationResponseNavigator onAcceptListener;
    @State
    TripInformationProvider provider;
    @BindView
    SimpleTextRow upsellRow;

    public static AcceptReservationConfirmationFragment newInstance(TripInformationProvider provider2) {
        return (AcceptReservationConfirmationFragment) ((FragmentBundleBuilder) FragmentBundler.make(new AcceptReservationConfirmationFragment()).putParcelable("trip_provider", provider2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.provider = (TripInformationProvider) getArguments().getParcelable("trip_provider");
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ReservationResponseNavigator) {
            this.onAcceptListener = (ReservationResponseNavigator) context;
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C0880R.layout.fragment_accept_reservation_confirmation, container, false);
        bindViews(view);
        ((AirbnbGraph) AirbnbApplication.instance(getContext()).component()).inject(this);
        this.upsellRow.setText((CharSequence) getResources().getString(C0880R.string.ro_accept_sheet_success_upsell, new Object[]{Integer.valueOf(3)}));
        return view;
    }

    @OnClick
    public void onDoneClicked() {
        if (this.onAcceptListener != null) {
            this.onAcceptListener.onDoneWithAccept();
            return;
        }
        getActivity().setResult(-1);
        getActivity().finish();
    }

    @OnClick
    public void onUpsellClicked() {
        this.instantBookUpsellManager.onTappedOnUpsell(this.provider.getListing().getId());
        startActivity(ManageListingIntents.intentForExistingListingSetting(getContext(), this.provider.getListing().getId(), SettingDeepLink.InstantBook));
    }
}
