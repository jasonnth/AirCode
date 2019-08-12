package com.airbnb.android.lib.fragments.paymentinfo;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.adapters.PaymentInfoAdapter;
import com.airbnb.android.lib.adapters.PaymentInfoAdapter.PaymentInfoAdapterInterface;
import com.airbnb.android.lib.fragments.paymentinfo.payout.BasePaymentInfoFragment;
import com.airbnb.p027n2.components.AirToolbar;

public class PaymentInfoFragment extends BasePaymentInfoFragment implements PaymentInfoAdapterInterface {
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static PaymentInfoFragment newInstance() {
        return new PaymentInfoFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_payment_info, container, false);
        ButterKnife.bind(this, view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(new PaymentInfoAdapter(this));
        this.recyclerView.setHasFixedSize(true);
        return view;
    }

    public void onPaymentMethodsClicked() {
    }

    public void onPayoutsClicked() {
        getNavigationController().onPayoutRowClicked();
    }
}
