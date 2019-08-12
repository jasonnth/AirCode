package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.HeroMarquee;

public class PayoutAddedFragment extends BasePaymentInfoFragment {
    @BindView
    HeroMarquee heroMarquee;

    public static PayoutAddedFragment newInstance() {
        return new PayoutAddedFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_payout_added, container, false);
        bindViews(view);
        this.heroMarquee.setFirstButtonClickListener(PayoutAddedFragment$$Lambda$1.lambdaFactory$(this));
        return view;
    }
}
