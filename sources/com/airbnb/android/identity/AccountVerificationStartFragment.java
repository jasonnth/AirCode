package com.airbnb.android.identity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.analytics.FiveAxiomAnalytics;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Element;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Page;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.identity.IdentityVerificationUtil;
import com.airbnb.android.core.intents.AccountVerificationActivityIntents;
import com.airbnb.android.core.intents.AccountVerificationStartActivityIntents;
import com.airbnb.android.core.intents.FiveAxiomIntents;
import com.airbnb.android.core.intents.HelpCenterIntents;
import com.airbnb.android.core.models.GovernmentIdResult;
import com.airbnb.android.core.models.GovernmentIdResult.Status;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.google.common.collect.ImmutableList;
import icepick.State;
import java.util.ArrayList;
import java.util.Iterator;

public class AccountVerificationStartFragment extends AirFragment implements AccountVerificationStartListener {
    private static final int RC_VERIFICATION_FLOW = 174;
    private static final int RC_VERIFICATION_FLOW_5A = 175;
    private static final int RC_VERIFICATION_FLOW_5A_CHINA = 176;
    @State
    VerificationFlow flow;
    @State
    GovernmentIdResult governmentIdResult;
    @State
    User host;
    @State
    boolean isMoveToLast;
    @State
    long listingId;
    @State
    String p4Steps;
    @State
    ArrayList<AccountVerificationStep> requiredSteps;
    @State
    boolean useIdentityFlow;
    @State
    User verificationUser;

    public static Intent newIntentForDebug(Context context) {
        Check.state(BuildHelper.isDevelopmentBuild());
        return AccountVerificationStartActivityIntents.newIntentForDebug(context, VerificationFlow.Booking, new ArrayList<>(ImmutableList.m1289of(AccountVerificationStep.ProfilePhoto, AccountVerificationStep.Phone, AccountVerificationStep.Email, AccountVerificationStep.OfflineId, AccountVerificationStep.Selfie)));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            this.listingId = getArguments().getLong(AccountVerificationStartActivityIntents.ARG_LISTING_ID);
            this.requiredSteps = getArguments().getParcelableArrayList(AccountVerificationStartActivityIntents.ARG_REQUIRED_VERIFICATION_STEPS);
            this.host = (User) getArguments().getParcelable(AccountVerificationStartActivityIntents.ARG_HOST);
            this.verificationUser = (User) getArguments().getParcelable(AccountVerificationStartActivityIntents.ARG_VERIFICATION_USER);
            this.flow = (VerificationFlow) getArguments().getSerializable("arg_verification_flow");
            this.governmentIdResult = (GovernmentIdResult) getArguments().getParcelable(AccountVerificationStartActivityIntents.ARG_GOVERNMENT_ID_RESULT);
            this.useIdentityFlow = !this.flow.showFiveAxiomIntro() || !IdentityVerificationUtil.isFiveAxioms(this.requiredSteps);
            this.isMoveToLast = getArguments().getBoolean(AccountVerificationStartActivityIntents.ARG_IS_MOVE_TO_LAST_STEP, false);
            this.p4Steps = getArguments().getString(AccountVerificationStartActivityIntents.ARG_P4_STEPS);
        }
        if (this.flow.isFromNewP4IdentityStep() && FeatureToggles.canSkipIdentity()) {
            setHasOptionsMenu(true);
        }
        if (this.useIdentityFlow) {
            return onCreateViewVerificationOneDotOne();
        }
        return onCreateViewVerification();
    }

    /* access modifiers changed from: protected */
    public int getAirToolbarTheme() {
        if (this.useIdentityFlow && !this.flow.isWhiteBackground()) {
            return 2;
        }
        return 3;
    }

    private View onCreateViewVerification() {
        AccountVerificationStart verificationStart = new AccountVerificationStart(getContext());
        verificationStart.setData(this.host, this.mAccountManager.getCurrentUser(), this.flow, this.requiredSteps, this, this.p4Steps);
        return verificationStart;
    }

    private View onCreateViewVerificationOneDotOne() {
        Status status = this.governmentIdResult == null ? null : this.governmentIdResult.getStatusFromString();
        boolean hasGovernmentIdError = status != null && status == Status.Denied;
        boolean selfieOnly = this.requiredSteps.contains(AccountVerificationStep.Selfie) && !this.requiredSteps.contains(AccountVerificationStep.OfflineId);
        User user = ((AccountVerificationStartActivity) getActivity()).getUser();
        if (selfieOnly || hasGovernmentIdError) {
            ((AccountVerificationStartActivity) getActivity()).getIdentityJitneyLogger().logImpression(this.flow, user, null, selfieOnly ? Page.selfie_only_intro : Page.verification_error_intro);
            return new AccountVerificationStartIncomplete(getContext()).setData(this.host, this.mAccountManager.getCurrentUser(), this.flow, selfieOnly, this.governmentIdResult, this);
        } else if (this.requiredSteps.isEmpty()) {
            ((AccountVerificationStartActivity) getActivity()).getIdentityJitneyLogger().logImpression(this.flow, user, null, status != Status.Approved ? Page.pending_intro : Page.all_set_intro);
            return new AccountVerificationStartComplete(getContext()).setData(this.flow, status != Status.Approved, this);
        } else {
            AccountVerificationStartOneDotOne verificationStartOneDotOne = new AccountVerificationStartOneDotOne(getContext());
            verificationStartOneDotOne.setData(this.host, this.mAccountManager.getCurrentUser(), this.flow, this.requiredSteps, AccountVerificationStartFragment$$Lambda$1.lambdaFactory$(this, user), this, this.p4Steps);
            ((AccountVerificationStartActivity) getActivity()).getIdentityJitneyLogger().logImpression(this.flow, user, null, Page.provide_id_intro);
            return verificationStartOneDotOne;
        }
    }

    static /* synthetic */ void lambda$onCreateViewVerificationOneDotOne$0(AccountVerificationStartFragment accountVerificationStartFragment, User user, int linkIndex) {
        accountVerificationStartFragment.startActivity(HelpCenterIntents.intentForHelpCenterArticle(accountVerificationStartFragment.getContext(), HelpCenterArticle.VERIFIED_ID_LEARN_MORE).toIntent());
        ((AccountVerificationStartActivity) accountVerificationStartFragment.getActivity()).getIdentityJitneyLogger().logClick(accountVerificationStartFragment.flow, user, null, Page.provide_id_intro, Element.intro_link_help);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AirToolbar airToolbar = ((AccountVerificationStartActivity) getActivity()).getAirToolbar();
        airToolbar.setTheme(getAirToolbarTheme());
        if (this.flow.isAddedToOtherFlow()) {
            airToolbar.setNavigationIcon(1);
        } else {
            airToolbar.setNavigationIcon(2);
        }
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C6533R.C6536menu.skip, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C6533R.C6535id.menu_skip) {
            return super.onOptionsItemSelected(item);
        }
        ((AccountVerificationStartActivity) getActivity()).confirmSkipVerificationFlow();
        return true;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 174:
                handleVerificationResult(resultCode, data, false);
                return;
            case 175:
                handleVerificationResult(resultCode, data, true);
                return;
            case 176:
                handle5AChinaResult(resultCode, data);
                return;
            default:
                return;
        }
    }

    private void handle5AChinaResult(int resultCode, Intent data) {
        if (resultCode == -1) {
            FiveAxiomAnalytics.trackComplete();
        }
        getActivity().setResult(resultCode, data);
        getActivity().finish();
    }

    private void handleVerificationResult(int resultCode, Intent data, boolean is5A) {
        if (is5A && resultCode == -1) {
            FiveAxiomAnalytics.trackComplete();
        }
        if (resultCode != 1003) {
            getActivity().setResult(resultCode, data);
            getActivity().finish();
        }
    }

    public void onContinueClick() {
        ((AccountVerificationStartActivity) getActivity()).getIdentityJitneyLogger().logClick(this.flow, ((AccountVerificationStartActivity) getActivity()).getUser(), null, Page.provide_id_intro, Element.navigation_button_continue);
        if (this.requiredSteps.isEmpty()) {
            ((AccountVerificationStartActivity) getActivity()).finishOK();
            return;
        }
        AccountVerificationAnalytics.trackButtonClick(getNavigationTrackingTag(), RegistrationAnalytics.CONTINUE_BUTTON);
        if (this.flow.isFromNewP4IdentityStep() || !IdentityVerificationUtil.isFiveAxioms(this.requiredSteps)) {
            startGlobalVerificationFlow(174);
            return;
        }
        FiveAxiomAnalytics.trackLaunch();
        if (ChinaUtils.enableNew5AFlow()) {
            startChina5AVerificationFlow();
        } else {
            startGlobalVerificationFlow(175);
        }
    }

    private void startGlobalVerificationFlow(int requestCode) {
        startActivityForResult(AccountVerificationActivityIntents.newIntentForSteps(getContext(), this.requiredSteps, this.flow, this.host, this.verificationUser, null, this.isMoveToLast, false, null), requestCode);
    }

    private void startChina5AVerificationFlow() {
        startActivityForResult(FiveAxiomIntents.newIntent(getContext(), this.requiredSteps, this.flow, this.host), 176);
    }

    public NavigationTag getNavigationTrackingTag() {
        return this.useIdentityFlow ? NavigationTag.VerificationSimplifiedIntro : NavigationTag.VerificationChecklistStart;
    }

    public Strap getNavigationTrackingParams() {
        return this.flow.getNavigationTrackingParams(getContext()).mo11638kv("id_listing", this.listingId);
    }

    public void onResume() {
        super.onResume();
        ArrayList<String> incompleteVerifications = new ArrayList<>(this.requiredSteps.size());
        Iterator it = this.requiredSteps.iterator();
        while (it.hasNext()) {
            incompleteVerifications.add(((AccountVerificationStep) it.next()).toAccountVerificationString());
        }
        AccountVerificationAnalytics.trackNonNavImpression(getNavigationTrackingTag(), incompleteVerifications.toString());
    }
}
