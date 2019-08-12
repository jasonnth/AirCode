package com.airbnb.android.identity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Element;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Page;
import com.airbnb.android.core.arguments.AccountVerificationStartFragmentArguments;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.identity.FetchIdentityController;
import com.airbnb.android.core.identity.FetchIdentityController.Listener;
import com.airbnb.android.core.identity.IdentityVerificationUtil;
import com.airbnb.android.core.intents.AccountVerificationActivityIntents;
import com.airbnb.android.core.intents.AccountVerificationStartActivityIntents;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.models.AccountVerification;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.util.ArrayList;
import java.util.Collection;

public class AccountVerificationStartActivity extends AirActivity implements Listener {
    private static final int RC_CANCEL_EXIT = 1001;
    private static final int RC_CONFIRM_EXIT = 1002;
    private static final int RC_IDENTITY_FLOW = 1006;
    public static final int RC_NOT_LOGGED_IN = 1004;
    private static final int RC_RN_INTRO = 1005;
    private static final int RC_SKIP_EXIT = 2001;
    private static final int RC_VERIFICATION_DEEP_LINK = 1003;
    @State
    Bundle bundle;
    private FetchIdentityController fetchIdentityController;
    IdentityJitneyLogger identityJitneyLogger;
    @BindView
    AirToolbar toolbar;
    @State
    boolean useIdentityFlow;
    @State
    VerificationFlow verificationFlow;
    @State
    ArrayList<AccountVerificationStep> verificationSteps;
    @State
    User verificationUser;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C6533R.layout.activity_transparent_action_bar);
        ButterKnife.bind((Activity) this);
        ((IdentityGraph) CoreApplication.instance(this).component()).inject(this);
        setToolbar(this.toolbar);
        if (savedInstanceState == null) {
            this.bundle = getIntent().getBundleExtra(AccountVerificationStartActivityIntents.EXTRA_BUNDLE);
            this.verificationUser = (User) this.bundle.getParcelable(AccountVerificationStartActivityIntents.ARG_VERIFICATION_USER);
            this.verificationFlow = (VerificationFlow) this.bundle.getSerializable("arg_verification_flow");
            this.verificationSteps = this.bundle.getParcelableArrayList(AccountVerificationStartActivityIntents.ARG_REQUIRED_VERIFICATION_STEPS);
            this.useIdentityFlow = !this.verificationFlow.showFiveAxiomIntro() || !IdentityVerificationUtil.isFiveAxioms(this.verificationSteps);
            if (this.accountManager.getCurrentUser() == null) {
                BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalArgumentException("AccountVerificationStartActivity launched with null current user, verificationFlow " + this.verificationFlow + ", verificationUser " + this.verificationUser + ", and verificationSteps " + this.verificationSteps));
                setResult(1004);
                finish();
            } else if (!ListUtils.isEmpty((Collection<?>) this.verificationSteps)) {
                showIntro();
            } else {
                this.fetchIdentityController = new FetchIdentityController(this.requestManager, (Listener) this, this.verificationFlow, savedInstanceState);
                this.fetchIdentityController.startFetchingIdentityVerificationState(this.accountManager.getCurrentUserId());
            }
        }
    }

    private void showIntro() {
        if (!this.useIdentityFlow || !this.verificationFlow.isIdentityRedesign()) {
            showFragment(Fragment.instantiate(this, AccountVerificationStartFragment.class.getCanonicalName(), this.bundle));
        } else {
            startActivityForResult(ReactNativeIntents.intentForVerificationInfo(this, this.verificationFlow.getIntroIdentityReactNativeInfoType()), 1005);
        }
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(C6533R.C6535id.content_container, fragment).commit();
    }

    public AirToolbar getAirToolbar() {
        return this.toolbar;
    }

    /* access modifiers changed from: protected */
    public void onHomeActionPressed() {
        popBackStackWithCheck();
    }

    public void onBackPressed() {
        popBackStackWithCheck();
    }

    private void popBackStackWithCheck() {
        if (getSupportFragmentManager().popBackStackImmediate()) {
            return;
        }
        if (this.verificationFlow.isAddedToOtherFlow() || ListUtils.isEmpty((Collection<?>) this.verificationSteps)) {
            this.identityJitneyLogger.logClick(this.verificationFlow, getUser(), null, Page.provide_id_intro, Element.navigation_button_back);
            finish();
            return;
        }
        confirmExitVerificationFlow();
    }

    private void confirmExitVerificationFlow() {
        trackFragmentButtonClick("close_button");
        ZenDialog.builder().withTitle(this.resourceManager.getString(C6533R.string.account_verification_exit_question)).withDualButton(C6533R.string.cancel, 1001, C6533R.string.account_verification_exit_confirm, 1002).create().show(getSupportFragmentManager(), (String) null);
    }

    public void confirmSkipVerificationFlow() {
        trackFragmentButtonClick("close_button");
        ZenDialog.builder().withTitle(this.resourceManager.getString(C6533R.string.account_verification_skip_question)).withDualButton(C6533R.string.cancel, 1001, C6533R.string.account_verification_exit_confirm, 2001).create().show(getSupportFragmentManager(), (String) null);
    }

    private void trackFragmentButtonClick(String target) {
        AirFragment currentFragment = (AirFragment) getSupportFragmentManager().findFragmentById(C6533R.C6535id.content_container);
        if (currentFragment != null) {
            AccountVerificationAnalytics.trackButtonClick(currentFragment.getNavigationTrackingTag(), target);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1006) {
            if (resultCode != 1003) {
                setResult(resultCode, data);
                finish();
            } else if (this.verificationFlow.isIdentityRedesign()) {
                showIntro();
            }
        } else if (requestCode == 1005) {
            if (resultCode == 0) {
                setResult(resultCode, data);
                finish();
            } else if (resultCode == -1) {
                startActivityForResult(AccountVerificationActivityIntents.newIntentForSteps(this, this.bundle.getParcelableArrayList(AccountVerificationStartActivityIntents.ARG_REQUIRED_VERIFICATION_STEPS), this.verificationFlow, (User) this.bundle.getParcelable(AccountVerificationStartActivityIntents.ARG_HOST), this.verificationUser, null, this.bundle.getBoolean(AccountVerificationStartActivityIntents.ARG_IS_MOVE_TO_LAST_STEP, false), false, null), 1006);
            }
        } else if (requestCode == 1002 && resultCode == -1) {
            trackFragmentButtonClick("confirm_close_button");
            this.identityJitneyLogger.logClick(this.verificationFlow, getUser(), null, Page.provide_id_intro, Element.navigation_button_cancel);
            finish();
        } else if (requestCode == 1003) {
            finish();
        } else if (requestCode == 2001) {
            trackFragmentButtonClick("skip_button");
            this.identityJitneyLogger.logClick(this.verificationFlow, getUser(), null, Page.provide_id_intro, Element.navigation_button_cancel);
            Intent i = new Intent();
            i.putExtra(IdentityVerificationUtil.ARG_SKIPPED, true);
            setResult(0, i);
            finish();
        }
    }

    public User getUser() {
        return this.verificationUser == null ? this.accountManager.getCurrentUser() : this.verificationUser;
    }

    public void onVerificationsFetchComplete(ArrayList<AccountVerification> incompleteVerifications) {
        this.verificationSteps = new ArrayList<>(FluentIterable.from((Iterable<E>) incompleteVerifications).transform(AccountVerificationStartActivity$$Lambda$1.lambdaFactory$()).toList());
        this.bundle = getIntent().getBundleExtra(AccountVerificationStartActivityIntents.EXTRA_BUNDLE);
        boolean isMoveToLastStep = this.bundle.getBoolean(AccountVerificationStartActivityIntents.ARG_IS_MOVE_TO_LAST_STEP, false);
        if (this.bundle.getString(AccountVerificationStartActivityIntents.ARG_FIRST_VERIFICATION_STEP) == null || ListUtils.isEmpty((Collection<?>) this.verificationSteps)) {
            this.bundle.putParcelableArrayList(AccountVerificationStartActivityIntents.ARG_REQUIRED_VERIFICATION_STEPS, this.verificationSteps);
            this.bundle.putParcelable(AccountVerificationStartActivityIntents.ARG_VERIFICATION_USER, this.fetchIdentityController.getVerificationUser());
            this.bundle.putParcelable(AccountVerificationStartActivityIntents.ARG_GOVERNMENT_ID_RESULT, this.fetchIdentityController.getGovernmentIdResult());
            showIntro();
            return;
        }
        startActivityForResult(AccountVerificationStartActivityIntents.newIntentForIncompleteVerifications(this, AccountVerificationStartFragmentArguments.builder().incompleteVerifications(incompleteVerifications).verificationUser(this.fetchIdentityController.getVerificationUser()).verificationFlow((VerificationFlow) this.bundle.getSerializable("arg_verification_flow")).firstVerificationStep(this.bundle.getString(AccountVerificationStartActivityIntents.ARG_FIRST_VERIFICATION_STEP)).governmentIdResult(this.fetchIdentityController.getGovernmentIdResult()).isMoveToLastStep(isMoveToLastStep).build()), 1003);
    }

    public void onVerificationsFetchError(NetworkException e) {
        NetworkUtil.toastGenericNetworkError(this);
    }

    public void finishOK() {
        setResult(-1);
        finish();
    }

    /* access modifiers changed from: protected */
    public IdentityJitneyLogger getIdentityJitneyLogger() {
        return this.identityJitneyLogger;
    }
}
