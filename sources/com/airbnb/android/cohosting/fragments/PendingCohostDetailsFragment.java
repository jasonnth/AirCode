package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.utils.CohostingConstants;
import com.airbnb.android.cohosting.utils.CohostingUtil;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import com.airbnb.android.core.models.CohostInvitation;
import com.airbnb.android.core.models.CohostingContract;
import com.airbnb.android.core.requests.DeleteCohostInvitationRequest;
import com.airbnb.android.core.requests.ResendCohostInvitationRequest;
import com.airbnb.android.core.responses.DeleteCohostInvitationResponse;
import com.airbnb.android.core.responses.ResendCohostInvitationResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.LinkActionRow;
import com.airbnb.p027n2.components.RefreshLoader;
import com.airbnb.p027n2.components.StandardRow;
import com.airbnb.p027n2.components.UserDetailsActionRow;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.State;
import java.util.ArrayList;
import java.util.List;
import p032rx.Observer;

public class PendingCohostDetailsFragment extends CohostManagementBaseFragment {
    @BindView
    LinkActionRow cancelInviteLink;
    final RequestListener<DeleteCohostInvitationResponse> deleteInvitationListener = new C0699RL().onResponse(PendingCohostDetailsFragment$$Lambda$1.lambdaFactory$(this)).onError(PendingCohostDetailsFragment$$Lambda$2.lambdaFactory$(this)).build();
    @State
    CohostInvitation invitation;
    CohostingManagementJitneyLogger logger;
    @BindView
    UserDetailsActionRow pendingCohostRow;
    @BindView
    RefreshLoader refreshLoader;
    final RequestListener<ResendCohostInvitationResponse> resendInvitationListener = new C0699RL().onResponse(PendingCohostDetailsFragment$$Lambda$3.lambdaFactory$(this)).onError(PendingCohostDetailsFragment$$Lambda$4.lambdaFactory$(this)).build();
    @BindView
    LinkActionRow resendInviteLink;
    @BindView
    StandardRow sharedEarningsRow;
    @BindView
    AirToolbar toolbar;

    static /* synthetic */ void lambda$new$0(PendingCohostDetailsFragment pendingCohostDetailsFragment, DeleteCohostInvitationResponse response) {
        pendingCohostDetailsFragment.toggleLoadingScreen(false);
        pendingCohostDetailsFragment.controller.removeCohostInvitation(pendingCohostDetailsFragment.invitation.getId());
        pendingCohostDetailsFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$2(PendingCohostDetailsFragment pendingCohostDetailsFragment, ResendCohostInvitationResponse response) {
        pendingCohostDetailsFragment.toggleLoadingScreen(false);
        pendingCohostDetailsFragment.invitation = response.cohostInvitation;
        pendingCohostDetailsFragment.controller.updateCohostInvitation(pendingCohostDetailsFragment.invitation);
        pendingCohostDetailsFragment.loadView();
        pendingCohostDetailsFragment.displayConfirmation(C5658R.string.cohosting_invite_sent_confirmation);
    }

    public static PendingCohostDetailsFragment create(long invitationId) {
        return (PendingCohostDetailsFragment) ((FragmentBundleBuilder) FragmentBundler.make(new PendingCohostDetailsFragment()).putLong(CohostingConstants.COHOST_INVITATION_ID_FIELD, invitationId)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z = false;
        View view = inflater.inflate(C5658R.layout.fragment_pending_cohost_details, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (savedInstanceState == null) {
            this.invitation = this.controller.getCohostInvitation(getArguments().getLong(CohostingConstants.COHOST_INVITATION_ID_FIELD));
        }
        if (this.invitation != null) {
            z = true;
        }
        Check.state(z, "Cohost invitation can not be null");
        loadView();
        ((CohostingGraph) CoreApplication.instance(getContext()).component()).inject(this);
        this.logger.logPendingCohostDetailsPageImpression(this.controller.getCohostingContext());
        return view;
    }

    @OnClick
    public void cancelInvite() {
        this.logger.logCancelInviteClicked(this.controller.getCohostingContext());
        toggleLoadingScreen(true);
        new DeleteCohostInvitationRequest(this.invitation.getId()).withListener((Observer) this.deleteInvitationListener).execute(this.requestManager);
    }

    @OnClick
    public void resendInvite() {
        this.logger.logReinviteButtonClicked(this.controller.getCohostingContext());
        toggleLoadingScreen(true);
        new ResendCohostInvitationRequest(this.invitation.getId()).withListener((Observer) this.resendInvitationListener).execute(this.requestManager);
    }

    private void loadView() {
        setupPendingCohostInfo();
        setupSharedEarningsRow();
        setupCancelInviteLink();
        toggleLoadingScreen(false);
    }

    private void setupPendingCohostInfo() {
        this.pendingCohostRow.setTitleText(this.invitation.getInviteeEmail());
        String status = CohostingUtil.getInvitationStatusStr(getContext(), this.invitation.getStatus());
        this.pendingCohostRow.setSubtitleText(status + " " + CohostingUtil.getInvitationExpirationTimeStr(getContext(), this.invitation.getExpirationTime()));
        this.pendingCohostRow.setImageResource(C5658R.C5659drawable.placeholder_profile);
    }

    private void setupSharedEarningsRow() {
        CohostingContract contract = this.invitation.getCohostingContract();
        if (contract == null) {
            this.sharedEarningsRow.setVisibility(8);
            return;
        }
        this.sharedEarningsRow.setVisibility(0);
        List<String> contractFees = new ArrayList<>();
        if (contract.getFormattedHostingFee(getContext()) != null) {
            contractFees.add(getString(C5658R.string.cohosting_receive_share_of_earnings_text, contract.getFormattedHostingFee(getContext())));
        }
        if (contract.getMinMaxFeeStr(getContext()) != null) {
            contractFees.add(contract.getMinMaxFeeStr(getContext()));
        }
        if (contract.isIncludeCleaningFee().booleanValue()) {
            contractFees.add(getString(C5658R.string.cohosting_receive_cleaning_fees_text));
        }
        this.sharedEarningsRow.setSubtitleMaxLine(contractFees.size());
        this.sharedEarningsRow.setSubtitleText((CharSequence) TextUtils.join("\n", contractFees));
    }

    private void toggleLoadingScreen(boolean showLoading) {
        int i;
        int i2;
        int i3 = 8;
        RefreshLoader refreshLoader2 = this.refreshLoader;
        if (showLoading) {
            i = 0;
        } else {
            i = 8;
        }
        refreshLoader2.setVisibility(i);
        LinkActionRow linkActionRow = this.resendInviteLink;
        if (showLoading) {
            i2 = 8;
        } else {
            i2 = 0;
        }
        linkActionRow.setVisibility(i2);
        LinkActionRow linkActionRow2 = this.cancelInviteLink;
        if (!showLoading) {
            i3 = 0;
        }
        linkActionRow2.setVisibility(i3);
    }

    private void displayConfirmation(int titleRes) {
        new SnackbarWrapper().view(getView()).title(titleRes, false).duration(0).buildAndShow();
    }

    private void setupCancelInviteLink() {
        switch (this.invitation.getStatus()) {
            case 1:
                this.cancelInviteLink.setText(C5658R.string.cancel_invite_text);
                return;
            case 2:
                this.cancelInviteLink.setText(C5658R.string.remove);
                return;
            default:
                this.cancelInviteLink.setVisibility(8);
                return;
        }
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }
}
