package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;

public class AddPayoutLandingFragment extends BasePaymentInfoFragment {
    @BindView
    AirToolbar toolbar;

    public static AddPayoutLandingFragment newInstance() {
        return new AddPayoutLandingFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_add_payout_landing, container, false);
        ButterKnife.bind(this, view);
        setToolbar(this.toolbar);
        return view;
    }

    @OnClick
    public void onNextButtonClick() {
        getNavigationController().doneWithLanding();
    }
}
