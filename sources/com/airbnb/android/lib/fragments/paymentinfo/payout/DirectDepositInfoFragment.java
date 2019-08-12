package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;

public class DirectDepositInfoFragment extends BasePaymentInfoFragment {
    @BindView
    AirToolbar toolbar;

    public static DirectDepositInfoFragment newInstance() {
        return new DirectDepositInfoFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_direct_deposit_info, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        return view;
    }

    /* access modifiers changed from: 0000 */
    @OnClick
    public void onNextButtonClick() {
        getNavigationController().doneWithDirectDepositInfo();
    }
}
