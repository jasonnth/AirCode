package com.airbnb.android.lib.china5a.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.analytics.FiveAxiomAnalytics;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.models.User;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.SheetMarquee;
import com.airbnb.p027n2.components.SheetMarquee.Style;

public class VerificationCompleteFragment extends AirFragment {
    private static final String ARG_FLOW = "flow";
    private static final String ARG_HOST = "host";
    @BindView
    SheetMarquee marquee;

    public static Fragment newInstance(VerificationFlow flow, User host) {
        return ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new VerificationCompleteFragment()).putSerializable("flow", flow)).putParcelable("host", host)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C0880R.layout.fragment_5a_china_complete, container, false);
        bindViews(v);
        VerificationFlow flow = (VerificationFlow) getArguments().getSerializable("flow");
        User host = (User) getArguments().getParcelable("host");
        Style.BABU.setStyle(this.marquee);
        if (host != null) {
            this.marquee.setSubtitle(getString(C0880R.string.china_verification_complete_body, host.getFirstName()));
        } else {
            this.marquee.setSubtitle(flow.getText().getFinishSubtitle(getContext(), this.mAccountManager.getCurrentUser().getFirstName(), null));
        }
        return v;
    }

    @OnClick
    public void onConfirmed() {
        FiveAxiomAnalytics.trackClick("continue_booking");
        getActivity().finish();
    }
}
