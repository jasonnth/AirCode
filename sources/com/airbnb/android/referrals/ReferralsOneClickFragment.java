package com.airbnb.android.referrals;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.apprater.AppRaterController;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.GrayUser;
import com.airbnb.android.core.models.ReferralStatusForMobile;
import com.airbnb.android.core.requests.CreateReferralsRequest;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.referrals.ReferralsComponent.Builder;
import com.airbnb.android.referrals.adapters.ReferralsPostReviewController;
import com.airbnb.android.referrals.analytics.ReferralsAnalytics;
import com.airbnb.android.sharing.referral.SharingManager;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import com.flipboard.bottomsheet.BottomSheetLayout;
import icepick.State;
import java.util.ArrayList;

public class ReferralsOneClickFragment extends AirFragment {
    private static final String GRAY_USERS_KEY = "gray_users_key";
    private static final String REFERRAL_STATUS_KEY = "referral_status_key";
    AppRaterController appRaterController;
    ReferralsPostReviewController controller;
    @BindView
    CoordinatorLayout coordinatorLayout;
    @BindView
    FixedDualActionFooter footer;
    @State
    ArrayList<GrayUser> grayUsers;
    @State
    ArrayList<GrayUser> pendingInvites;
    @BindView
    AirRecyclerView recyclerView;
    @State
    ReferralStatusForMobile referralStatus;
    @BindView
    BottomSheetLayout rootBottomSheetLayout;
    @BindView
    AirToolbar toolbar;

    public static ReferralsOneClickFragment newInstance(ReferralStatusForMobile referralStatus2, ArrayList<GrayUser> grayUsers2) {
        return (ReferralsOneClickFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new ReferralsOneClickFragment()).putParcelable(REFERRAL_STATUS_KEY, referralStatus2)).putParcelableArrayList(GRAY_USERS_KEY, grayUsers2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((ReferralsBindings) CoreApplication.instance(getContext()).componentProvider()).referralsComponentProvider().get()).build().inject(this);
        if (savedInstanceState == null) {
            this.referralStatus = (ReferralStatusForMobile) getArguments().getParcelable(REFERRAL_STATUS_KEY);
            this.grayUsers = getArguments().getParcelableArrayList(GRAY_USERS_KEY);
            this.pendingInvites = new ArrayList<>(this.grayUsers.size());
        }
        this.controller = new ReferralsPostReviewController(getContext(), this.referralStatus, this.grayUsers, ReferralsOneClickFragment$$Lambda$1.lambdaFactory$(this));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C1532R.layout.fragment_referrals_post_review, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        this.footer.setButtonOnClickListener(ReferralsOneClickFragment$$Lambda$2.lambdaFactory$(this));
        this.footer.setSecondaryButtonOnClickListener(ReferralsOneClickFragment$$Lambda$3.lambdaFactory$(this));
        this.recyclerView.setEpoxyControllerAndBuildModels(this.controller);
        return view;
    }

    public void onMoreOptionsLinkClicked() {
        this.appRaterController.incrementSignificantEvent();
        ReferralsAnalytics.trackShareLinkClick();
        SharingManager sharingManager = new SharingManager(getActivity(), this.referralStatus.getLink(), SharingManager.REFERRALS);
        String userName = this.mAccountManager.getCurrentUser().getFirstName();
        sharingManager.withTitle(getString(C1532R.string.referral_new_title, this.referralStatus.getOfferReceiverCreditLocalized())).withBodyNoSharingURL(getString(C1532R.string.referral_new_body, this.referralStatus.getOfferReceiverCreditLocalized())).withDescription(getString(C1532R.string.referral_new_description, userName)).share(this.rootBottomSheetLayout);
    }

    public void onPause() {
        super.onPause();
        sendInvites();
    }

    /* access modifiers changed from: private */
    public void onDoneButtonClicked() {
        sendInvites();
        getAirActivity().finish();
    }

    private synchronized void sendInvites() {
        if (!this.pendingInvites.isEmpty()) {
            new CreateReferralsRequest(this.pendingInvites).execute(NetworkUtil.singleFireExecutor());
        }
        this.pendingInvites.clear();
    }

    /* access modifiers changed from: private */
    public synchronized void onUndoButtonClicked(int index, GrayUser grayUser) {
        this.pendingInvites.remove(grayUser);
        this.controller.addGrayUser(grayUser, index);
        this.controller.requestModelBuild();
    }

    public synchronized void inviteButtonClicked(int index, GrayUser grayUser) {
        this.pendingInvites.add(grayUser);
        this.controller.removeGrayUser(grayUser);
        this.controller.requestModelBuild();
        new SnackbarWrapper().view(this.coordinatorLayout).title(C1532R.string.referrals_sending_invite, false).action(C1532R.string.referrals_undo_send_invite, ReferralsOneClickFragment$$Lambda$4.lambdaFactory$(this, index, grayUser)).duration(0).buildAndShow();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.Referrals;
    }
}
