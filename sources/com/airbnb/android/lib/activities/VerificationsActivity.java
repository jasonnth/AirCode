package com.airbnb.android.lib.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.activities.SolitAirActivity;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.intents.LoginActivityIntents;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.Verification;
import com.airbnb.android.core.models.verifications.VerificationsState;
import com.airbnb.android.core.responses.VerificationsResponse;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.webintent.WebIntentMatcherConstants;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.enums.VerificationFlowToLayoutId;
import com.airbnb.android.lib.fragments.verifications.CompleteVerificationFragment;
import com.airbnb.android.lib.fragments.verifications.EmailVerificationFragment;
import com.airbnb.android.lib.fragments.verifications.PhoneVerificationFragment;
import com.airbnb.android.lib.fragments.verifications.PhotoVerificationFragment;
import com.airbnb.android.lib.fragments.verifications.WelcomeVerificationFragment;
import com.airbnb.android.lib.utils.VerificationsPoller;
import com.airbnb.android.lib.utils.VerificationsPoller.VerificationsListener;
import com.airbnb.android.lib.views.DotsProgressBar;
import icepick.State;
import java.util.List;

public class VerificationsActivity extends SolitAirActivity {
    private static final String ARG_LISTING = "arguments_listing";
    private static final String ARG_VERIFICATIONS = "argument_verifications";
    private static final String ARG_VERIFICATIONS_FLOW = "argument_verifications_flow";
    public static final String EXTRA_DEBUG_FLAG = "extra_debug_flag";
    private static final int REQUEST_CODE_ACCOUNT_ACTIVATION = 40000;
    public static final String RESULT_EXTRA_VERIFICATION_FLOW = "extra_verification_flow";
    private final VerificationsListener activityVerificationsListener = new VerificationsListener() {
        public void onReceivedVerifications(VerificationsResponse response) {
            VerificationsActivity.this.onUpdatedVerifications(response);
            if (VerificationsActivity.this.fragmentVerificationsListener != null) {
                VerificationsActivity.this.fragmentVerificationsListener.onReceivedVerifications(response);
            }
        }
    };
    @State
    boolean debugEnabled;
    private boolean didLogIn;
    @BindView
    DotsProgressBar dotsProgressBar;
    /* access modifiers changed from: private */
    public VerificationsListener fragmentVerificationsListener;
    @State
    boolean isDotsProgressBarDisplayed;
    @State
    boolean isVerificationsStateUninitialized;
    @State
    Listing listing;
    @State
    VerificationFlow verificationFlow;
    private VerificationsPoller verificationsPoller;
    @State
    VerificationsState verificationsState;

    public static Intent intent(Context context) {
        Intent intent = new Intent(context, VerificationsActivity.class);
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_VERIFICATIONS_FLOW, VerificationFlow.Onboarding.ordinal());
        intent.putExtras(arguments);
        return intent;
    }

    @Deprecated
    public static Intent intent(Context context, VerificationsState verificationsState2) {
        Intent intent = new Intent(context, VerificationsActivity.class);
        Bundle arguments = new Bundle();
        arguments.putInt(ARG_VERIFICATIONS_FLOW, VerificationFlow.Onboarding.ordinal());
        arguments.putParcelable(ARG_VERIFICATIONS, verificationsState2);
        intent.putExtras(arguments);
        return intent;
    }

    public static Intent intentForListing(Context context, Listing listing2, VerificationsState verificationsState2, VerificationFlow flow) {
        Intent intent = new Intent(context, VerificationsActivity.class);
        Bundle arguments = new Bundle();
        arguments.putParcelable(ARG_LISTING, listing2);
        arguments.putParcelable(ARG_VERIFICATIONS, verificationsState2);
        arguments.putInt(ARG_VERIFICATIONS_FLOW, flow.ordinal());
        intent.putExtras(arguments);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (!isWaitingForInitialVerificationsState()) {
            doneWithVerification(false);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind((Activity) this);
        displayDebugFeaturesIfEnabled();
        if (savedInstanceState == null) {
            this.listing = (Listing) getIntent().getParcelableExtra(ARG_LISTING);
            this.verificationsState = (VerificationsState) getIntent().getParcelableExtra(ARG_VERIFICATIONS);
            this.verificationFlow = VerificationFlow.values()[getIntent().getIntExtra(ARG_VERIFICATIONS_FLOW, -1)];
            if (verifyIsSignedIn()) {
                showInitialFragment();
            }
        }
        setUpDotsProgressBar();
        setUpVerificationsPoller();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.didLogIn) {
            showInitialFragment();
        }
        this.verificationsPoller.start();
        showSpinnerUntilVerificationsStateInitialized();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.verificationsPoller.stop();
        showLoader(false);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode != REQUEST_CODE_ACCOUNT_ACTIVATION) {
            super.onActivityResult(requestCode, resultCode, data);
        } else if (resultCode == -1) {
            this.didLogIn = true;
        } else {
            finish();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (!BuildHelper.isDebugFeaturesEnabled()) {
            return false;
        }
        getMenuInflater().inflate(C0880R.C0883menu.debug_verifications, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == C0880R.C0882id.debug_verifications_success) {
            handleDebugAction(true);
            return true;
        } else if (itemId != C0880R.C0882id.debug_verifications_error) {
            return super.onOptionsItemSelected(item);
        } else {
            handleDebugAction(false);
            return true;
        }
    }

    public void onBackPressed() {
        if (isWaitingForInitialVerificationsState()) {
            finishActivity(false);
        } else if (this.verificationsState.hasSkippedVerifications()) {
            this.dotsProgressBar.backUpCurrentDot(this.verificationsState.getCurrentVerificationIndex());
            this.verificationsState.moveToPreviousVerification();
            showVerification(this.verificationsState.getCurrentVerification(), true);
        } else {
            finishActivity(false);
        }
    }

    private boolean isWaitingForInitialVerificationsState() {
        return this.verificationsState == null;
    }

    private void showInitialFragment() {
        if (VerificationFlowToLayoutId.find(this.verificationFlow).getWelcomeLayoutId() != -1) {
            showWelcomeVerification();
            showOrHideDotsProgressBar(false, false);
            return;
        }
        showVerification(this.verificationsState.getCurrentVerification(), false);
        showOrHideDotsProgressBar(true, false);
    }

    private void finishActivity(boolean didComplete) {
        Intent data = new Intent();
        data.putExtra("extra_verification_flow", this.verificationFlow.ordinal());
        setResult(didComplete ? -1 : 0, data);
        finish();
    }

    public void doneWithWelcome() {
        if (this.verificationsState.hasIncompleteVerifications()) {
            showVerification(this.verificationsState.getCurrentVerification(), false);
            showOrHideDotsProgressBar(true, true);
            return;
        }
        showCompleteVerification();
    }

    public void doneWithComplete() {
        if (shouldStartMainActivityAfterComplete()) {
            startActivity(HomeActivityIntents.intentForDefaultTab(this));
        }
        finishActivity(true);
    }

    private boolean shouldStartMainActivityAfterComplete() {
        if (getIntent().getBooleanExtra(WebIntentMatcherConstants.IS_WEB_LINK, false) || getIntent().getBooleanExtra("is_deep_link_flag", false)) {
            return true;
        }
        return false;
    }

    public void doneWithVerification(boolean skipped) {
        if (!skipped) {
            this.verificationsState.markCurrentVerificationAsComplete();
            this.dotsProgressBar.completeCurrentDot(this.verificationsState.getCurrentVerificationIndex());
        } else {
            this.dotsProgressBar.skipCurrentDot(this.verificationsState.getCurrentVerificationIndex());
        }
        if (this.verificationsState.isLastVerification()) {
            showOrHideDotsProgressBar(false, false);
            if (VerificationFlowToLayoutId.find(this.verificationFlow).getCompleteLayoutId() != -1) {
                showCompleteVerification();
            } else {
                finishActivity(true);
            }
        } else {
            showOrHideDotsProgressBar(true, false);
            this.verificationsState.moveToNextVerification();
            showVerification(this.verificationsState.getCurrentVerification(), false);
        }
    }

    /* access modifiers changed from: protected */
    public void showFragment(Fragment fragment, boolean isPrevious) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (getSupportFragmentManager().findFragmentById(C0880R.C0882id.content_container) != null) {
            ft.setCustomAnimations(isPrevious ? C0880R.anim.fragment_enter_pop : C0880R.anim.fragment_enter, isPrevious ? C0880R.anim.fragment_exit_pop : C0880R.anim.fragment_exit, C0880R.anim.fragment_enter_pop, C0880R.anim.fragment_exit_pop);
        }
        ft.replace(C0880R.C0882id.content_container, fragment).commit();
    }

    private void showVerification(Verification verificationToDisplay, boolean isPrevious) {
        switch (verificationToDisplay.getType()) {
            case Photo:
                showFragment(PhotoVerificationFragment.newInstance(this.listing, this.verificationFlow), isPrevious);
                return;
            case Email:
                showFragment(EmailVerificationFragment.newInstance(), isPrevious);
                return;
            case Phone:
                showFragment(PhoneVerificationFragment.newInstance(), isPrevious);
                return;
            default:
                throw new IllegalStateException("Invalid Verification of value: " + verificationToDisplay.getId());
        }
    }

    private void showWelcomeVerification() {
        WelcomeVerificationFragment fragment;
        if (this.verificationFlow == VerificationFlow.Booking) {
            fragment = WelcomeVerificationFragment.newInstanceForBooking(this.listing);
        } else {
            fragment = WelcomeVerificationFragment.newInstance();
        }
        showFragment(fragment, true);
    }

    private void showCompleteVerification() {
        CompleteVerificationFragment fragment;
        if (this.verificationFlow == VerificationFlow.Booking) {
            fragment = CompleteVerificationFragment.newInstanceForBooking();
        } else {
            fragment = CompleteVerificationFragment.newInstance();
        }
        showFragment(fragment, true);
    }

    /* access modifiers changed from: protected */
    public int getLayoutId() {
        return C0880R.layout.activity_verifications;
    }

    private void setUpDotsProgressBar() {
        if (this.verificationsState != null) {
            this.dotsProgressBar.setUpDots(this.verificationsState.size(), this.verificationsState.numberOfCompletedVerifications(), this.verificationsState.getCurrentVerificationIndex());
        }
        showOrHideDotsProgressBar(this.isDotsProgressBarDisplayed, false);
    }

    public void registerVerificationsListener(VerificationsListener fragmentVerificationsListener2) {
        if (this.fragmentVerificationsListener != null) {
            throw new IllegalStateException("There should only be one fragment registered as a Verifications Listener");
        }
        this.fragmentVerificationsListener = fragmentVerificationsListener2;
    }

    public void deregisterVerificationsListener() {
        this.fragmentVerificationsListener = null;
    }

    private void setUpVerificationsPoller() {
        this.verificationsPoller = new VerificationsPoller(this.requestManager, this.verificationFlow.getRequestFilter(), this.activityVerificationsListener);
    }

    /* access modifiers changed from: private */
    public void onUpdatedVerifications(VerificationsResponse response) {
        setVerificationsState(response.accountActivationVerifications.getVerificationGroups().getAccountActivation().getItems());
        if (this.isVerificationsStateUninitialized) {
            this.isVerificationsStateUninitialized = false;
            showLoader(false);
            showCompleteIfCompletedVerifications();
        }
        setUpDotsProgressBar();
    }

    private void setVerificationsState(List<Verification> verifications) {
        if (this.verificationsState == null) {
            this.verificationsState = VerificationsState.initialize(verifications);
        } else {
            this.verificationsState.updateVerifications(verifications);
        }
    }

    private void showCompleteIfCompletedVerifications() {
        if (!this.verificationsState.hasIncompleteVerifications()) {
            showCompleteVerification();
        }
    }

    private void showSpinnerUntilVerificationsStateInitialized() {
        if (isWaitingForInitialVerificationsState()) {
            showLoader(true);
            this.isVerificationsStateUninitialized = true;
        }
    }

    private void handleDebugAction(boolean success) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(C0880R.C0882id.content_container);
        if (fragment instanceof PhotoVerificationFragment) {
            ((PhotoVerificationFragment) fragment).debugHandleResult(success);
        } else if (success) {
            doneWithVerification(false);
        }
    }

    private void showOrHideDotsProgressBar(boolean isDisplayed, boolean fade) {
        this.isDotsProgressBarDisplayed = isDisplayed;
        if (!isDisplayed || !fade) {
            this.dotsProgressBar.setVisibility(isDisplayed ? 0 : 4);
        } else {
            this.dotsProgressBar.fadeInView();
        }
    }

    public int getNumCompletedVerifications() {
        return this.verificationsState.numberOfCompletedVerifications();
    }

    public int getNumVerifications() {
        return this.verificationsState.size();
    }

    private void displayDebugFeaturesIfEnabled() {
        this.debugEnabled = getIntent().getBooleanExtra(EXTRA_DEBUG_FLAG, false);
        if (this.debugEnabled) {
            getSupportActionBar().show();
        } else {
            getSupportActionBar().hide();
        }
    }

    public boolean isSkipEnabled() {
        return this.verificationFlow.isSkipEnabled();
    }

    private boolean verifyIsSignedIn() {
        boolean isSignedIn = this.accountManager.isCurrentUserAuthorized();
        if (!isSignedIn) {
            startActivityForResult(LoginActivityIntents.intent(this), REQUEST_CODE_ACCOUNT_ACTIVATION);
        }
        return isSignedIn;
    }
}
