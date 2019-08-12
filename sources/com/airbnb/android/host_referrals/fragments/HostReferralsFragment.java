package com.airbnb.android.host_referrals.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.HostReferralReferrerInfo;
import com.airbnb.android.host_referrals.C6405R;
import com.airbnb.android.host_referrals.HostReferralsGraph;
import com.airbnb.android.host_referrals.activities.HostReferralsActivity;
import com.airbnb.android.host_referrals.epoxycontrollers.HostReferralsEpoxyController;
import com.airbnb.android.host_referrals.epoxycontrollers.HostReferralsEpoxyController.Listener;
import com.airbnb.android.host_referrals.logging.HostReferralLogger;
import com.airbnb.android.sharing.referral.SharingManager;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.flipboard.bottomsheet.BottomSheetLayout;
import icepick.State;

public class HostReferralsFragment extends AirFragment implements Listener {
    private static final String ARG_REFERRAL_INFO = "info";
    private HostReferralsActivity activity;
    private HostReferralsEpoxyController epoxyController;
    HostReferralLogger hostReferralLogger;
    @BindView
    AirRecyclerView recyclerView;
    @State
    HostReferralReferrerInfo referrerInfo;
    @BindView
    BottomSheetLayout rootBottomSheetLayout;

    public static HostReferralsFragment newInstance(HostReferralReferrerInfo info) {
        return (HostReferralsFragment) ((FragmentBundleBuilder) FragmentBundler.make(new HostReferralsFragment()).putParcelable(ARG_REFERRAL_INFO, info)).build();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.activity = (HostReferralsActivity) context;
        } catch (ClassCastException e) {
            throw new ClassCastException("Parent activity must be a HostReferralsActivity");
        }
    }

    public void onDetach() {
        super.onDetach();
        this.activity = null;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C6405R.layout.fragment_host_referrals, container, false);
        bindViews(v);
        ((HostReferralsGraph) CoreApplication.instance(getContext()).component()).inject(this);
        this.referrerInfo = (HostReferralReferrerInfo) getArguments().getParcelable(ARG_REFERRAL_INFO);
        this.epoxyController = new HostReferralsEpoxyController(getContext(), this.referrerInfo, this);
        this.recyclerView.setEpoxyControllerAndBuildModels(this.epoxyController);
        return v;
    }

    public void onShareYourLinkClicked() {
        this.hostReferralLogger.logHostReferralAction("ShareYourLink");
        SharingManager sharingManager = new SharingManager(getActivity(), this.referrerInfo.getLink(), SharingManager.REFERRALS);
        String userName = this.mAccountManager.getCurrentUser().getFirstName();
        sharingManager.withTitle(getString(C6405R.string.host_referral_link_title)).withBodyNoSharingURL(getString(C6405R.string.host_referral_link_body)).withDescription(getString(C6405R.string.host_referral_link_description, userName)).share(this.rootBottomSheetLayout);
    }

    public void onTravelCreditClicked() {
        this.hostReferralLogger.logHostReferralAction("ViewEarnedCredit");
        this.activity.onTravelCreditClicked();
    }

    public void onTermsClicked() {
        this.hostReferralLogger.logHostReferralAction("ViewTermsAndConditions");
        this.activity.onTermsClicked();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.HostReferrals;
    }
}
