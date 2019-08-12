package com.airbnb.android.lib.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentTransaction;
import android.support.p002v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.TranslateAnimation;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics;
import com.airbnb.android.core.analytics.VerifiedIdAnalytics.VerifiedIdStrapper;
import com.airbnb.android.core.intents.VerifiedIdActivityIntents;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.VerificationRequirements;
import com.airbnb.android.core.requests.VerificationsRequest;
import com.airbnb.android.core.responses.VerificationsResponse;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.ChinaUtils;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.DebugSettings.DebugVerification;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.core.views.LoaderFrame.LoaderFrameDisplay;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.content.VerifiedIdDeepLinkParser;
import com.airbnb.android.lib.fragments.verifiedid.BaseVerifiedIdFragment;
import com.airbnb.android.lib.fragments.verifiedid.BaseVerifiedIdFragment.AnimationCompletionListener;
import com.airbnb.android.lib.fragments.verifiedid.OfflineIdFragment;
import com.airbnb.android.lib.fragments.verifiedid.OnlineIdFragment;
import com.airbnb.android.lib.fragments.verifiedid.VerifiedIdCompletedFragment;
import com.airbnb.android.lib.fragments.verifiedid.VerifiedIdDialogSummaryFragment;
import com.airbnb.android.lib.fragments.verifiedid.VerifiedIdDialogSummaryFragment.VerifiedIdDialogFragment;
import com.airbnb.android.lib.fragments.verifiedid.WelcomeScreenFragment;
import com.airbnb.android.lib.handlerthread.VerificationsPoller;
import com.airbnb.android.lib.views.StepProgressBar;
import com.airbnb.android.login.utils.LoginUtils;
import com.airbnb.android.utils.Strap;
import com.facebook.FacebookSdk;
import icepick.State;

public class VerifiedIdActivity extends AirActivity implements VerifiedIdStrapper, LoaderFrameDisplay, AnimationCompletionListener, VerifiedIdDialogFragment {
    public static final int COMPLETE_PROFILE_REQUEST = 39000;
    private static final String CURRENT_STEP = "current_step";
    private static final String DID_COMPLETE_PROFILE_EXTRA = "did_complete_profile";
    public static final String EMAIL_CONFIRMATION_CODE_EXTRA = "email_code";
    private static final int INVALID_VERIFIED_ID_STEP = -1;
    private static final int MAX_RETRIES_TO_VERIFY_ONLINE_ID = 5;
    private static final String NUMBER_COMPLETED_STEPS_EXTRA = "number_completed_steps";
    private static final int PROGRESS_BAR_ANIMATION_DURATION = 500;
    public static final String TAG = VerifiedIdActivity.class.getSimpleName();
    private boolean mActivityInForeground;
    private int mCurrentNumberOnlineRetriesAllowed;
    private VerifiedIdStep mCurrentVerifiedIdStep;
    private DebugSettings mDebugSettings;
    private boolean mDidCompleteProfile;
    private boolean mDidPressVerifyMeBeforeReady;
    /* access modifiers changed from: private */
    public String mEmailConfirmationCode;
    private LoaderFrame mLoaderFrame;
    private int mNumberCompletedSteps;
    private StepProgressBar mProgressBar;
    /* access modifiers changed from: private */
    public Reservation mReservation;
    /* access modifiers changed from: private */
    public VerificationRequirements mVerificationRequirements;
    private VerificationsPoller mVerificationsPoller;
    @State
    boolean showSesameVerification;

    private enum VerifiedIdStep {
        WELCOME,
        BASIC,
        OFFLINE,
        ONLINE,
        COMPLETE
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(C0880R.layout.activity_verified_id);
        setSupportActionBar((Toolbar) findViewById(C0880R.C0882id.toolbar));
        this.mLoaderFrame = (LoaderFrame) findViewById(C0880R.C0882id.loading_overlay);
        setupActionBar(C0880R.string.verified_id_title, new Object[0]);
        handleWebIntent();
        this.mDebugSettings = AirbnbApplication.instance(this).component().debugSettings();
        if (savedInstanceState == null) {
            this.mVerificationRequirements = (VerificationRequirements) getIntent().getParcelableExtra(VerifiedIdActivityIntents.VERIFICATIONS_EXTRA);
            this.mNumberCompletedSteps = countCompletedVerifications(this.mVerificationRequirements);
            this.mDidCompleteProfile = false;
            this.mReservation = (Reservation) getIntent().getParcelableExtra("reservation");
            this.mCurrentVerifiedIdStep = VerifiedIdStep.WELCOME;
        } else {
            this.mNumberCompletedSteps = savedInstanceState.getInt(NUMBER_COMPLETED_STEPS_EXTRA);
            this.mVerificationRequirements = (VerificationRequirements) savedInstanceState.getParcelable(VerifiedIdActivityIntents.VERIFICATIONS_EXTRA);
            this.mReservation = (Reservation) savedInstanceState.getParcelable("reservation");
            this.mEmailConfirmationCode = savedInstanceState.getString(EMAIL_CONFIRMATION_CODE_EXTRA);
            this.mDidCompleteProfile = savedInstanceState.getBoolean(DID_COMPLETE_PROFILE_EXTRA);
            int stepOrdinal = savedInstanceState.getInt(CURRENT_STEP, -1);
            this.mCurrentVerifiedIdStep = stepOrdinal > -1 ? VerifiedIdStep.values()[stepOrdinal] : null;
        }
        this.mDidPressVerifyMeBeforeReady = false;
        this.mCurrentNumberOnlineRetriesAllowed = -1;
        initializeProgressBar();
        setProgressBarState();
        if (savedInstanceState == null) {
            showStep();
            this.showSesameVerification = ChinaUtils.isUserInChinaOrPrefersChineseLanguage();
        }
    }

    private void handleWebIntent() {
        VerifiedIdDeepLinkParser dispatch = new VerifiedIdDeepLinkParser(getIntent());
        if (dispatch.hasPath("users/confirm_email")) {
            handleVerifiedIdIntents(dispatch.getQueryParameter("code"));
        } else if (dispatch.hasPath("verify") || dispatch.hasPath(Trebuchet.CHECKPOINT)) {
            handleVerifiedIdIntents(null);
        }
    }

    private void handleVerifiedIdIntents(final String emailConfirmationCode) {
        displayLoaderFrame(true);
        new VerificationsRequest(new NonResubscribableRequestListener<VerificationsResponse>() {
            public void onResponse(VerificationsResponse response) {
                if (!VerifiedIdActivity.this.isFinishing()) {
                    VerifiedIdActivity.this.displayLoaderFrame(false);
                    VerifiedIdActivity.this.mEmailConfirmationCode = emailConfirmationCode;
                    VerifiedIdActivity.this.mVerificationRequirements = response.checkpointVerifications;
                    VerifiedIdActivity.this.mReservation = response.reservation;
                    VerifiedIdActivityIntents.validateReservation(VerifiedIdActivity.this.mReservation);
                    VerifiedIdActivity.this.getSupportFragmentManager().popBackStack((String) null, 1);
                    VerifiedIdActivity.this.initializeProgressBar();
                    VerifiedIdActivity.this.showStep();
                }
            }

            public void onErrorResponse(AirRequestNetworkException error) {
                VerifiedIdActivity.this.displayLoaderFrame(false);
                NetworkUtil.toastGenericNetworkError(VerifiedIdActivity.this);
                VerifiedIdActivity.this.navigateUp();
            }
        }).execute(this.requestManager);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.mActivityInForeground = true;
        this.mVerificationsPoller = new VerificationsPoller(VerifiedIdActivity$$Lambda$1.lambdaFactory$(this));
        this.mVerificationsPoller.startPolling();
    }

    /* access modifiers changed from: protected */
    public void onPostResume() {
        super.onPostResume();
        if (this.mDidCompleteProfile) {
            showStep();
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mActivityInForeground = false;
        this.mVerificationsPoller.cancelPolling();
    }

    public void onBackPressed() {
        VerifiedIdAnalytics.trackBackPressed(getVerifiedIdAnalyticsStrap());
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            if (this.mProgressBar.getVisibility() == 0) {
                animateProgressBar(false);
            }
            this.mCurrentVerifiedIdStep = VerifiedIdStep.WELCOME;
            this.mProgressBar.setVisibility(4);
        } else {
            setResult(0);
        }
        super.onBackPressed();
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(NUMBER_COMPLETED_STEPS_EXTRA, this.mNumberCompletedSteps);
        outState.putParcelable(VerifiedIdActivityIntents.VERIFICATIONS_EXTRA, this.mVerificationRequirements);
        outState.putParcelable("reservation", this.mReservation);
        outState.putString(EMAIL_CONFIRMATION_CODE_EXTRA, this.mEmailConfirmationCode);
        outState.putBoolean(DID_COMPLETE_PROFILE_EXTRA, this.mDidCompleteProfile);
        outState.putInt(CURRENT_STEP, this.mCurrentVerifiedIdStep.ordinal());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0880R.C0883menu.verified_id_summary, menu);
        menu.findItem(C0880R.C0882id.verified_id_summary_item).setVisible(true);
        return true;
    }

    public void showNextStep() {
        String verificationHeader;
        if (!VerifiedIdStep.WELCOME.equals(this.mCurrentVerifiedIdStep) || this.mVerificationRequirements != null) {
            VerifiedIdStep nextStep = nextStep();
            if (nextStep.equals(this.mCurrentVerifiedIdStep)) {
                return;
            }
            if (this.mCurrentVerifiedIdStep == VerifiedIdStep.OFFLINE || this.mCurrentVerifiedIdStep == VerifiedIdStep.ONLINE) {
                BaseVerifiedIdFragment fragment = (BaseVerifiedIdFragment) getContentFragment();
                if (fragment != null) {
                    if (this.mCurrentVerifiedIdStep == VerifiedIdStep.OFFLINE) {
                        VerifiedIdAnalytics.trackOfflineConfirmView(getVerifiedIdAnalyticsStrap());
                        VerifiedIdAnalytics.trackHealth("offline", "finish");
                    } else {
                        VerifiedIdAnalytics.trackOnlineConfirmView(getVerifiedIdAnalyticsStrap());
                        VerifiedIdAnalytics.trackHealth("online", "finish");
                    }
                    this.mCurrentNumberOnlineRetriesAllowed = -1;
                    displayLoaderFrame(false);
                    if (this.mCurrentVerifiedIdStep == VerifiedIdStep.OFFLINE) {
                        verificationHeader = getString(C0880R.string.verified_id_offline_verified);
                    } else {
                        verificationHeader = getString(C0880R.string.verified_id_online_verified);
                    }
                    fragment.animateCompletion(verificationHeader, fragment.getVerificationSuccessDescription());
                    return;
                }
                return;
            }
            incrementToStep(nextStep);
            return;
        }
        displayLoaderFrame(true);
        this.mDidPressVerifyMeBeforeReady = true;
    }

    private void incrementToStep(VerifiedIdStep step) {
        displayLoaderFrame(false);
        this.mCurrentVerifiedIdStep = step;
        showStep();
    }

    public void didCompleteAnimation() {
        incrementToStep(nextStep());
    }

    public VerifiedIdStep nextStep() {
        if (!this.mVerificationRequirements.basicVerificationsComplete() && !this.mDidCompleteProfile && (!BuildHelper.isDebugFeaturesEnabled() || !basicInformationCompleteWithBypass())) {
            return VerifiedIdStep.BASIC;
        }
        if (!this.mVerificationRequirements.offlineVerificationComplete() && (!BuildHelper.isDebugFeaturesEnabled() || !this.mDebugSettings.isUserVerifiedWithVerification(DebugVerification.OFFLINE_ID))) {
            return VerifiedIdStep.OFFLINE;
        }
        if (this.mVerificationRequirements.onlineVerificationComplete() || (BuildHelper.isDebugFeaturesEnabled() && this.mDebugSettings.isUserVerifiedWithVerification(DebugVerification.ONLINE_ID))) {
            return VerifiedIdStep.COMPLETE;
        }
        return VerifiedIdStep.ONLINE;
    }

    private int getInitialCaptionForOfflineIdStep() {
        return this.showSesameVerification ? C0880R.string.verified_id_connect_sesame_credit_title : C0880R.string.verified_id_spb_scan_official_id;
    }

    public void updateProgressBarTitle(int title) {
        this.mProgressBar.setCaption(title);
    }

    public void setProgressBarState() {
        switch (this.mCurrentVerifiedIdStep) {
            case WELCOME:
                this.mProgressBar.setVisibility(4);
                return;
            case BASIC:
                this.mEmailConfirmationCode = null;
                return;
            case OFFLINE:
                animateProgressBar(true);
                this.mProgressBar.incrementToStep(4);
                this.mProgressBar.setCaption(getInitialCaptionForOfflineIdStep());
                return;
            case ONLINE:
                animateProgressBar(true);
                this.mProgressBar.incrementToStep(5);
                this.mProgressBar.setCaption(C0880R.string.verified_id_spb_verify_online_id);
                return;
            case COMPLETE:
                animateProgressBar(true);
                this.mProgressBar.incrementToStep(6);
                this.mProgressBar.setCaption(C0880R.string.verified_id_spb_complete);
                return;
            default:
                return;
        }
    }

    public void showStep() {
        switch (this.mCurrentVerifiedIdStep) {
            case WELCOME:
                showWelcome();
                this.mProgressBar.setVisibility(4);
                return;
            case BASIC:
                showCompleteProfile();
                this.mEmailConfirmationCode = null;
                return;
            case OFFLINE:
                animateProgressBar(true);
                showOffline();
                this.mProgressBar.incrementToStep(4);
                this.mProgressBar.setCaption(getInitialCaptionForOfflineIdStep());
                return;
            case ONLINE:
                animateProgressBar(true);
                showOnline();
                this.mProgressBar.incrementToStep(5);
                this.mProgressBar.setCaption(C0880R.string.verified_id_spb_verify_online_id);
                return;
            case COMPLETE:
                animateProgressBar(true);
                this.mProgressBar.incrementToStep(6);
                this.mProgressBar.setCaption(C0880R.string.verified_id_spb_complete);
                showComplete();
                return;
            default:
                showWelcome();
                return;
        }
    }

    public void showWelcome() {
        showFragment(WelcomeScreenFragment.newInstance(this.mEmailConfirmationCode != null, this.mReservation));
    }

    public void showCompleteProfile() {
        if (this.mActivityInForeground) {
            startActivityForResult(CompleteProfileActivity.intentForVerifiedId(this, this.mNumberCompletedSteps, this.mVerificationRequirements, this.mReservation, this.mEmailConfirmationCode), COMPLETE_PROFILE_REQUEST);
        }
    }

    public void showOffline() {
        showFragment(OfflineIdFragment.newInstance(this.showSesameVerification));
    }

    public void showOnline() {
        showFragment(OnlineIdFragment.newInstance());
    }

    public void showComplete() {
        showFragment(VerifiedIdCompletedFragment.newInstance(this.mReservation));
    }

    public void showFragment(Fragment f) {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (getContentFragment() != null) {
            ft.setCustomAnimations(C0880R.anim.fragment_enter, C0880R.anim.fragment_exit, C0880R.anim.fragment_enter_pop, C0880R.anim.fragment_exit_pop);
            ft.addToBackStack(f.getTag());
        }
        ft.replace(C0880R.C0882id.content_container, f);
        ft.commit();
    }

    private Fragment getContentFragment() {
        return getSupportFragmentManager().findFragmentById(C0880R.C0882id.content_container);
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == C0880R.C0882id.verified_id_summary_item && this.mVerificationRequirements != null) {
            showChecklist();
        }
        return super.onOptionsItemSelected(item);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case LoginUtils.REQUEST_CODE_GOOGLE /*23456*/:
                if (resultCode == -1) {
                    String authToken = data.getExtras().getString(LoginUtils.GOOGLE_AUTHTOKEN_EXTRA);
                    Fragment fragment = getContentFragment();
                    if (fragment instanceof OnlineIdFragment) {
                        ((OnlineIdFragment) fragment).onGooglePermissionsCompleted(authToken);
                        return;
                    }
                    return;
                }
                return;
            case COMPLETE_PROFILE_REQUEST /*39000*/:
                if (resultCode == -1) {
                    this.mDidCompleteProfile = true;
                    if (!this.mVerificationRequirements.offlineVerificationComplete()) {
                        this.mCurrentVerifiedIdStep = VerifiedIdStep.OFFLINE;
                        return;
                    } else if (!this.mVerificationRequirements.onlineVerificationComplete()) {
                        this.mCurrentVerifiedIdStep = VerifiedIdStep.ONLINE;
                        return;
                    } else {
                        this.mCurrentVerifiedIdStep = VerifiedIdStep.COMPLETE;
                        return;
                    }
                } else if (resultCode == 0) {
                    this.mCurrentVerifiedIdStep = VerifiedIdStep.WELCOME;
                    return;
                } else {
                    return;
                }
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }

    /* access modifiers changed from: private */
    public void handleVerificationsResponse(VerificationRequirements verifications) {
        Log.i(TAG, verifications.toString());
        if (this.mActivityInForeground) {
            this.mVerificationRequirements = verifications;
            this.mNumberCompletedSteps = countCompletedVerifications(verifications);
            if (this.mDidPressVerifyMeBeforeReady || !(VerifiedIdStep.WELCOME == this.mCurrentVerifiedIdStep || VerifiedIdStep.COMPLETE == this.mCurrentVerifiedIdStep)) {
                showNextStep();
                this.mDidPressVerifyMeBeforeReady = false;
            }
            if (this.mCurrentNumberOnlineRetriesAllowed == 0) {
                Fragment fragment = getContentFragment();
                if (fragment instanceof OnlineIdFragment) {
                    ((OnlineIdFragment) fragment).notifyServerFailureToConnect();
                }
                this.mCurrentNumberOnlineRetriesAllowed--;
            } else if (this.mCurrentNumberOnlineRetriesAllowed > 0) {
                this.mCurrentNumberOnlineRetriesAllowed--;
            }
        }
    }

    private int countCompletedVerifications(VerificationRequirements verifications) {
        int completedSteps = 1;
        if (verifications == null) {
            return 1;
        }
        if (verifications.emailVerificationComplete()) {
            completedSteps = 1 + 1;
        }
        if (verifications.profilePhotoComplete()) {
            completedSteps++;
        }
        if (verifications.phoneComplete()) {
            completedSteps++;
        }
        if (verifications.offlineVerificationComplete()) {
            completedSteps++;
        }
        if (verifications.onlineVerificationComplete()) {
            completedSteps++;
        }
        return completedSteps;
    }

    /* access modifiers changed from: private */
    public void initializeProgressBar() {
        this.mProgressBar = (StepProgressBar) findViewById(C0880R.C0882id.step_progress_bar);
        this.mProgressBar.initializeView(6, this.mNumberCompletedSteps);
    }

    public int getNumberCompletedStep() {
        return this.mNumberCompletedSteps;
    }

    private void animateProgressBar(boolean makeVisible) {
        TranslateAnimation animation;
        if (makeVisible && this.mProgressBar.getVisibility() == 0) {
            return;
        }
        if (makeVisible || this.mProgressBar.getVisibility() != 4) {
            if (makeVisible) {
                animation = new TranslateAnimation(0.0f, 0.0f, (float) (-this.mProgressBar.getHeight()), 0.0f);
                this.mProgressBar.setVisibility(0);
            } else {
                animation = new TranslateAnimation(0.0f, 0.0f, 0.0f, (float) (-this.mProgressBar.getHeight()));
                this.mProgressBar.setVisibility(4);
            }
            animation.setDuration(500);
            animation.setFillAfter(true);
            this.mProgressBar.startAnimation(animation);
        }
    }

    public void completeVerifiedIdActivity() {
        setResult(-1);
        finish();
    }

    public long getReservationId() {
        if (this.mReservation != null) {
            return this.mReservation.getId();
        }
        return -1;
    }

    public String getReservationIdStringForAnalytics() {
        if (this.mReservation != null) {
            return String.valueOf(this.mReservation.getId());
        }
        return null;
    }

    public VerificationRequirements getCheckpointVerifications() {
        return this.mVerificationRequirements;
    }

    public Strap getVerifiedIdAnalyticsStrap() {
        return Strap.make().mo11639kv("reservation_id", getReservationIdStringForAnalytics());
    }

    public void waitForOnlineId() {
        displayLoaderFrame(true);
        this.mCurrentNumberOnlineRetriesAllowed = 5;
    }

    public void showChecklist() {
        VerifiedIdDialogSummaryFragment.newInstance(this.mVerificationRequirements).show(getSupportFragmentManager(), (String) null);
    }

    public void displayLoaderFrame(boolean show) {
        LoaderFrame loaderFrame = getLoaderFrame();
        if (show) {
            loaderFrame.setVisibility(0);
            loaderFrame.startAnimation();
            return;
        }
        loaderFrame.setVisibility(8);
        loaderFrame.finish();
    }

    /* access modifiers changed from: protected */
    public LoaderFrame getLoaderFrame() {
        return this.mLoaderFrame;
    }

    private boolean basicInformationCompleteWithBypass() {
        return (this.mVerificationRequirements.profilePhotoComplete() || this.mDebugSettings.isUserVerifiedWithVerification(DebugVerification.PROFILE_PICTURE)) && (this.mVerificationRequirements.emailVerificationComplete() || this.mDebugSettings.isUserVerifiedWithVerification(DebugVerification.EMAIL)) && (this.mVerificationRequirements.phoneComplete() || this.mDebugSettings.isUserVerifiedWithVerification(DebugVerification.PHONE));
    }
}
