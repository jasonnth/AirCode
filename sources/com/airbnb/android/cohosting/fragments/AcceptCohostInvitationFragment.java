package com.airbnb.android.cohosting.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.epoxycontrollers.AcceptCohostInvitationEpoxyController;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingInvitationJitneyLogger;
import com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.intents.AccountVerificationStartActivityIntents;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.models.CohostInvitation;
import com.airbnb.android.core.requests.AcceptCohostInvitationRequest;
import com.airbnb.android.core.requests.AccountVerificationsRequest;
import com.airbnb.android.core.responses.AcceptCohostInvitationResponse;
import com.airbnb.android.core.responses.AccountVerificationsResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.FixedActionFooterWithText;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.List;
import p032rx.Observer;

public class AcceptCohostInvitationFragment extends CohostInvitationBaseFragment {
    private static final int REQUEST_CODE_VERIFICATIONS = 1001;
    @BindView
    FixedActionFooterWithText acceptButtonRow;
    final RequestListener<AcceptCohostInvitationResponse> acceptCohostInvitationListener = new C0699RL().onResponse(AcceptCohostInvitationFragment$$Lambda$3.lambdaFactory$(this)).onError(AcceptCohostInvitationFragment$$Lambda$4.lambdaFactory$(this)).build();
    private AcceptCohostInvitationEpoxyController epoxyController;
    @State
    CohostInvitation invitation;
    CohostingInvitationJitneyLogger logger;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;
    final RequestListener<AccountVerificationsResponse> verificationListener = new C0699RL().onResponse(AcceptCohostInvitationFragment$$Lambda$1.lambdaFactory$(this)).onError(AcceptCohostInvitationFragment$$Lambda$2.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$2(AcceptCohostInvitationFragment acceptCohostInvitationFragment, AcceptCohostInvitationResponse response) {
        acceptCohostInvitationFragment.toggleAcceptButtonState(true);
        acceptCohostInvitationFragment.mAccountManager.incrementListingCount();
        acceptCohostInvitationFragment.controller.setListingAddressForInvitation(response.cohostInvitation);
        acceptCohostInvitationFragment.controller.actionExecutor.displayInvitationConfirmationPage();
    }

    static /* synthetic */ void lambda$new$3(AcceptCohostInvitationFragment acceptCohostInvitationFragment, AirRequestNetworkException e) {
        acceptCohostInvitationFragment.toggleAcceptButtonState(false);
        NetworkUtil.tryShowErrorWithSnackbar(acceptCohostInvitationFragment.getView(), e);
    }

    public static AcceptCohostInvitationFragment create() {
        return (AcceptCohostInvitationFragment) FragmentBundler.make(new AcceptCohostInvitationFragment()).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.invitation = this.controller.getCohostInvitation();
        this.epoxyController = new AcceptCohostInvitationEpoxyController(getContext(), this.invitation, savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((CohostingGraph) CoreApplication.instance().component()).inject(this);
        View view = inflater.inflate(C5658R.layout.fragment_accept_cohost_invitation, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        setupTermsOfService();
        this.acceptButtonRow.setButtonOnClickListener(AcceptCohostInvitationFragment$$Lambda$5.lambdaFactory$(this));
        logImpression();
        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == -1) {
            acceptInvitation();
        }
    }

    /* access modifiers changed from: private */
    public void launchVerificationFlowIfNeeded(List<AccountVerification> verifications) {
        List<AccountVerification> incompleteVerifications = FluentIterable.from((Iterable<E>) verifications).filter(AcceptCohostInvitationFragment$$Lambda$6.lambdaFactory$(this)).toList();
        if (ListUtils.isNullOrEmpty(incompleteVerifications)) {
            acceptInvitation();
        } else {
            startActivityForResult(AccountVerificationStartActivityIntents.newIntentForIncompleteVerifications(getContext(), getVerificationArguments(incompleteVerifications)), 1001);
        }
    }

    private AccountVerificationStartFragmentArguments getVerificationArguments(List<AccountVerification> incompleteVerifications) {
        return AccountVerificationStartFragmentArguments.builder().verificationFlow(VerificationFlow.CohostInvitation).host(this.controller.getInviter()).incompleteVerifications(incompleteVerifications).build();
    }

    private void acceptInvitation() {
        toggleAcceptButtonState(true);
        AcceptCohostInvitationRequest.forInvitation(this.invitation).withListener((Observer) this.acceptCohostInvitationListener).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void verifyUserAndAccept() {
        logClick();
        AccountVerificationsRequest.forFlow(VerificationFlow.CohostInvitation).withListener((Observer) this.verificationListener).execute(this.requestManager);
    }

    private void setupTermsOfService() {
        this.acceptButtonRow.setupLinkedText(getText(C5658R.string.cohosting_cohost_accept_invite_disclaimer), getText(C5658R.string.cohosting_invite_new_cohost_terms), C5658R.color.n2_babu, AcceptCohostInvitationFragment$$Lambda$7.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public boolean isVerificationRequired(AccountVerification verification) {
        return !verification.isComplete();
    }

    private void toggleAcceptButtonState(boolean isLoading) {
        this.acceptButtonRow.setButtonLoading(isLoading);
    }

    private void logImpression() {
        this.logger.logCohostingInvitationInviteDetailImpression(Long.valueOf(this.invitation.getId()), Long.valueOf(this.invitation.getInviter().getId()), this.invitation.getInviteeEmail(), Long.valueOf(this.mAccountManager.getCurrentUserId()), this.invitation.getInviteeIdentifierType(), this.invitation.getInviteeIdentifier(), this.invitation.getExpirationTime().getIsoDateString(), this.invitation.getListing());
    }

    private void logClick() {
        this.logger.logCohostingInvitationAcceptClick(Long.valueOf(this.invitation.getId()), Long.valueOf(this.invitation.getInviter().getId()), this.invitation.getInviteeEmail(), Long.valueOf(this.mAccountManager.getCurrentUserId()), this.invitation.getInviteeIdentifierType(), this.invitation.getInviteeIdentifier(), this.invitation.getExpirationTime().getIsoDateString(), this.invitation.getListing());
    }
}
