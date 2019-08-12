package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;

public class NoPayoutOptionFragment extends BasePaymentInfoFragment {
    @BindView
    AirToolbar toolbar;

    public static NoPayoutOptionFragment newInstance() {
        return new NoPayoutOptionFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_no_payout_option, container, false);
        ButterKnife.bind(this, view);
        setToolbar(this.toolbar);
        return view;
    }
}
