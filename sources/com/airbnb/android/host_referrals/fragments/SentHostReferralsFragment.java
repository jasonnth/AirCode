package com.airbnb.android.host_referrals.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.HostReferralReferrerInfo;
import com.airbnb.android.core.models.Referree;
import com.airbnb.android.host_referrals.C6405R;
import com.airbnb.android.host_referrals.epoxycontrollers.SentHostReferralsEpoxyController;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.flipboard.bottomsheet.BottomSheetLayout;
import icepick.State;
import java.util.ArrayList;

public class SentHostReferralsFragment extends AirFragment {
    private static final String ARG_REFERREES = "referrees";
    private static final String ARG_REFERRER_INFO = "referrer_info";
    private SentHostReferralsEpoxyController epoxyController;
    @BindView
    AirRecyclerView recyclerView;
    @State
    ArrayList<Referree> referrees;
    @State
    HostReferralReferrerInfo referrerInfo;
    @BindView
    BottomSheetLayout rootBottomSheetLayout;

    public static SentHostReferralsFragment newInstance(ArrayList<Referree> referrees2, HostReferralReferrerInfo referrerInfo2) {
        return (SentHostReferralsFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new SentHostReferralsFragment()).putParcelableArrayList(ARG_REFERREES, referrees2)).putParcelable(ARG_REFERRER_INFO, referrerInfo2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6405R.layout.fragment_host_referrals, container, false);
        bindViews(view);
        this.referrees = getArguments().getParcelableArrayList(ARG_REFERREES);
        this.referrerInfo = (HostReferralReferrerInfo) getArguments().getParcelable(ARG_REFERRER_INFO);
        this.epoxyController = new SentHostReferralsEpoxyController(getContext(), this.referrees, this.referrerInfo);
        this.recyclerView.setEpoxyControllerAndBuildModels(this.epoxyController);
        return view;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.SentHostReferrals;
    }
}
