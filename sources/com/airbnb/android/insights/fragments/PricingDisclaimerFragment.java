package com.airbnb.android.insights.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.insights.C6552R;
import com.airbnb.p027n2.components.AirToolbar;

public class PricingDisclaimerFragment extends AirFragment {
    @BindView
    AirToolbar toolbar;

    public static PricingDisclaimerFragment newInstance() {
        return new PricingDisclaimerFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C6552R.layout.fragment_disclaimer, container, false);
        bindViews(v);
        this.toolbar.setNavigationOnClickListener(PricingDisclaimerFragment$$Lambda$1.lambdaFactory$(this));
        return v;
    }
}
