package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.p000v4.app.Fragment;
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
import com.airbnb.android.core.enums.CompleteProfileType;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.models.VerificationRequirements;
import com.airbnb.android.core.requests.SendEmailConfirmationCodeRequest;
import com.airbnb.android.core.responses.SendEmailConfirmationCodeResponse;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.DebugSettings.DebugVerification;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.core.views.LoaderFrame.LoaderFrameDisplay;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.completeprofile.CompleteProfileBaseFragment;
import com.airbnb.android.lib.fragments.completeprofile.CompleteProfileEmailFragment;
import com.airbnb.android.lib.fragments.completeprofile.CompleteProfilePhoneFragment;
import com.airbnb.android.lib.fragments.completeprofile.CompleteProfilePhotoFragment;
import com.airbnb.android.lib.fragments.verifiedid.VerifiedIdDialogSummaryFragment;
import com.airbnb.android.lib.fragments.verifiedid.VerifiedIdDialogSummaryFragment.VerifiedIdDialogFragment;
import com.airbnb.android.lib.handlerthread.VerificationsPoller;
import com.airbnb.android.lib.views.StepProgressBar;
import com.airbnb.android.utils.Strap;
import com.facebook.internal.AnalyticsEvents;
import java.util.List;

public class CompleteProfileActivity extends AirActivity implements VerifiedIdStrapper, LoaderFrameDisplay, VerifiedIdDialogFragment {
    private static final String EMAIL_CONFIRMATION_CODE_EXTRA = "email_confirmation_code";
    private static final String EMAIL_FAILED = "email_verification_failed";
    private static final String NUMBER_COMPLETED_STEPS_EXTRA = "number_completed_steps";
    private static final int NUMBER_STEPS_COMPLETE_PROFILE_ONLY = 3;
    private static final int NUMBER_STEPS_VERIFIED_ID = 6;
    private static final String PHONE_NUM_TO_VERIFY_EXTRA = "phone_number_to_verify";
    private static final String PROFILE_TYPE_EXTRA = "profile_type";
    private static final int PROGRESS_BAR_ANIMATION_DELAY = 500;
    private static final int PROGRESS_BAR_ANIMATION_DURATION = 500;
    private static final String RESERVATION_EXTRA = "reservation";
    public static final String TAG = CompleteProfileActivity.class.getSimpleName();
    private static final String VERIFICATIONS_EXTRA = "verifications";
    private Handler mAnimationHandler;
    private CompleteProfileType mCompleteProfileType;
    /* access modifiers changed from: private */
    public Verification mCurrentVerification;
    private DebugSettings mDebugSettings;
    private boolean mDidCompleteAllVerifications;
    private String mEmailConfirmationCode;
    private LoaderFrame mLoadingOverlay;
    private int mNumberCompletedSteps;
    private String mPhoneNumberToVerify;
    private StepProgressBar mProgressBar;
    private Reservation mReservation;
    private String mUserEmailAddress;
    private VerificationRequirements mVerificationRequirements;
    private VerificationsPoller mVerificationsPoller;

    private enum Verification {
        PHOTO,
        EMAIL,
        PHONE
    }

    public static Intent intentForType(Context c, CompleteProfileType type) {
        Intent intent = new Intent(c, CompleteProfileActivity.class);
        intent.putExtra(PROFILE_TYPE_EXTRA, type);
        return intent;
    }

    public static Intent intentForVerifyPhoneNumber(Context c, String formattedPhoneNum) {
        Intent intent = intentForType(c, CompleteProfileType.EDIT_PROFILE_VERIFY_PHONE);
        intent.putExtra(PHONE_NUM_TO_VERIFY_EXTRA, formattedPhoneNum);
        return intent;
    }

    public static Intent intentForVerifiedId(Context c, int numberCompletedSteps, VerificationRequirements verifications, Reservation reservation, String emailConfirmationCode) {
        Intent intent = intentForType(c, CompleteProfileType.VERIFIED_ID);
        intent.putExtra(NUMBER_COMPLETED_STEPS_EXTRA, numberCompletedSteps);
        intent.putExtra("verifications", verifications);
        intent.putExtra("reservation", reservation);
        intent.putExtra(EMAIL_CONFIRMATION_CODE_EXTRA, emailConfirmationCode);
        return intent;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mDebugSettings = AirbnbApplication.instance(this).component().debugSettings();
        this.mDidCompleteAllVerifications = false;
        if (savedInstanceState == null) {
            this.mVerificationRequirements = (VerificationRequirements) getIntent().getParcelableExtra("verifications");
            this.mCompleteProfileType = (CompleteProfileType) getIntent().getParcelableExtra(PROFILE_TYPE_EXTRA);
            this.mReservation = (Reservation) getIntent().getParcelableExtra("reservation");
            this.mEmailConfirmationCode = getIntent().getStringExtra(EMAIL_CONFIRMATION_CODE_EXTRA);
            this.mNumberCompletedSteps = getIntent().getIntExtra(NUMBER_COMPLETED_STEPS_EXTRA, 0);
            this.mPhoneNumberToVerify = getIntent().getStringExtra(PHONE_NUM_TO_VERIFY_EXTRA);
        } else {
            this.mVerificationRequirements = (VerificationRequirements) savedInstanceState.getParcelable("verifications");
            this.mCompleteProfileType = (CompleteProfileType) savedInstanceState.getParcelable(PROFILE_TYPE_EXTRA);
            this.mReservation = (Reservation) savedInstanceState.getParcelable("reservation");
            this.mEmailConfirmationCode = savedInstanceState.getString(EMAIL_CONFIRMATION_CODE_EXTRA);
            this.mNumberCompletedSteps = savedInstanceState.getInt(NUMBER_COMPLETED_STEPS_EXTRA, 0);
            this.mPhoneNumberToVerify = savedInstanceState.getString(PHONE_NUM_TO_VERIFY_EXTRA);
        }
        if (photoRequired()) {
            this.mCurrentVerification = Verification.PHOTO;
        } else if (emailRequired()) {
            this.mCurrentVerification = Verification.EMAIL;
        } else if (phoneRequired()) {
            this.mCurrentVerification = Verification.PHONE;
        } else {
            Log.e(TAG, "Expected at least one verification to complete!");
        }
        setContentView(C0880R.layout.activity_complete_profile);
        setSupportActionBar((Toolbar) findViewById(C0880R.C0882id.toolbar));
        setupActionBar(C0880R.string.title_complete_profile, new Object[0]);
        this.mAnimationHandler = new Handler();
        if (isManageListingFlow()) {
            setupActionBar(C0880R.string.title_complete_profile, new Object[0]);
        } else if (isVerifiedIdFlow()) {
            setupActionBar(C0880R.string.verified_id_title, new Object[0]);
        } else {
            setupActionBar(C0880R.string.title_phone_number, new Object[0]);
        }
        this.mLoadingOverlay = (LoaderFrame) findViewById(C0880R.C0882id.loading_overlay);
        initProgressBar();
        if (this.mCompleteProfileType.isEditProfileType()) {
            this.mProgressBar.setVisibility(8);
        } else {
            setProgressBarCaption();
        }
        if (savedInstanceState == null) {
            requestVerification();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EMAIL_CONFIRMATION_CODE_EXTRA, this.mEmailConfirmationCode);
        outState.putInt(NUMBER_COMPLETED_STEPS_EXTRA, this.mNumberCompletedSteps);
        outState.putParcelable("verifications", this.mVerificationRequirements);
        outState.putParcelable("reservation", this.mReservation);
        outState.putParcelable(PROFILE_TYPE_EXTRA, this.mCompleteProfileType);
        outState.putString(PHONE_NUM_TO_VERIFY_EXTRA, this.mPhoneNumberToVerify);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.mEmailConfirmationCode != null && this.mCurrentVerification == Verification.EMAIL) {
            sendEmailConfirmationCode();
        }
        this.mVerificationsPoller = new VerificationsPoller(CompleteProfileActivity$$Lambda$1.lambdaFactory$(this));
        this.mVerificationsPoller.startPolling();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.mVerificationsPoller.cancelPolling();
        this.mAnimationHandler.removeCallbacksAndMessages(null);
    }

    public void onBackPressed() {
        if (isVerifiedIdFlow()) {
            VerifiedIdAnalytics.trackBackPressed(getVerifiedIdAnalyticsStrap());
        }
        finish();
    }

    public void overridePendingTransition(int enterAnim, int exitAnim) {
        if (this.mDidCompleteAllVerifications) {
            super.overridePendingTransition(enterAnim, C0880R.anim.fragment_exit);
        } else {
            super.overridePendingTransition(enterAnim, exitAnim);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        if (!this.mCompleteProfileType.isEditProfileType()) {
            getMenuInflater().inflate(C0880R.C0883menu.verified_id_summary, menu);
            if (isManageListingFlow()) {
                menu.findItem(C0880R.C0882id.verified_id_summary_item).setVisible(false);
            } else if (isVerifiedIdFlow()) {
                menu.findItem(C0880R.C0882id.verified_id_summary_item).setVisible(true);
            }
        }
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == C0880R.C0882id.verified_id_summary_item) {
            showChecklist();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean photoRequired() {
        if (isVerifiedIdFlow()) {
            if (this.mVerificationRequirements.profilePhotoComplete() || (BuildHelper.isDebugFeaturesEnabled() && this.mDebugSettings.isUserVerifiedWithVerification(DebugVerification.PROFILE_PICTURE))) {
                return false;
            }
            return true;
        } else if (this.mCompleteProfileType.isEditProfileType() || !hasNoVerification(this.accountManager.getCurrentUser(), Verification.PHOTO)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean phoneRequired() {
        boolean z = false;
        if (!isVerifiedIdFlow()) {
            if (this.mCompleteProfileType.isEditProfileType() || hasNoVerification(this.accountManager.getCurrentUser(), Verification.PHONE)) {
                z = true;
            }
            return z;
        } else if (this.mVerificationRequirements.phoneComplete() || (BuildHelper.isDebugFeaturesEnabled() && this.mDebugSettings.isUserVerifiedWithVerification(DebugVerification.PHONE))) {
            return false;
        } else {
            return true;
        }
    }

    private boolean emailRequired() {
        if (isVerifiedIdFlow()) {
            if (this.mVerificationRequirements.emailVerificationComplete() || (BuildHelper.isDebugFeaturesEnabled() && this.mDebugSettings.isUserVerifiedWithVerification(DebugVerification.EMAIL))) {
                return false;
            }
            return true;
        } else if (this.mCompleteProfileType.isEditProfileType() || !hasNoVerification(this.accountManager.getCurrentUser(), Verification.EMAIL)) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean hasNoVerification(User currentUser, Verification verification) {
        if (verification == Verification.PHOTO) {
            if (!currentUser.hasProfilePic()) {
                return true;
            }
            return false;
        } else if (verification == Verification.EMAIL) {
            if (currentUser.getVerifications().contains("email")) {
                return false;
            }
            return true;
        } else if (verification != Verification.PHONE) {
            throw new IllegalArgumentException("Unknown verification type: " + verification);
        } else if (currentUser.getVerifications().contains("phone")) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean shouldShowActivity(User currentUser) {
        return hasNoVerification(currentUser, Verification.PHOTO) || hasNoVerification(currentUser, Verification.EMAIL) || hasNoVerification(currentUser, Verification.PHONE);
    }

    public boolean isVerifiedIdFlow() {
        return !this.mCompleteProfileType.isEditProfileType() && this.mVerificationRequirements != null;
    }

    public boolean isManageListingFlow() {
        return this.mCompleteProfileType.isManageListingType();
    }

    public void requestVerification() {
        boolean isEditPhoneNumber = true;
        getSupportFragmentManager().popBackStack();
        switch (this.mCurrentVerification) {
            case PHOTO:
                showFragment(CompleteProfilePhotoFragment.newInstance());
                return;
            case EMAIL:
                showFragment(CompleteProfileEmailFragment.newInstance());
                return;
            case PHONE:
                if (getType() != CompleteProfileType.EDIT_PROFILE_EDIT_PHONE) {
                    isEditPhoneNumber = false;
                }
                showFragment(CompleteProfilePhoneFragment.newInstance(isEditPhoneNumber));
                return;
            default:
                Log.i(TAG, "No verifications required");
                setResult(-1);
                this.mDidCompleteAllVerifications = true;
                finish();
                return;
        }
    }

    public void updateVerificationState() {
        switch (this.mCurrentVerification) {
            case PHOTO:
                if (emailRequired()) {
                    incrementVerificationStep(Verification.EMAIL, C0880R.string.verified_id_spb_confirm_email);
                    return;
                } else if (phoneRequired()) {
                    incrementVerificationStep(Verification.PHONE, C0880R.string.verified_id_spb_confirm_phone);
                    return;
                } else {
                    this.mDidCompleteAllVerifications = true;
                    setResult(-1);
                    finish();
                    return;
                }
            case EMAIL:
                updateUserEmail();
                if (phoneRequired()) {
                    incrementVerificationStep(Verification.PHONE, C0880R.string.verified_id_spb_confirm_phone);
                    return;
                }
                this.mDidCompleteAllVerifications = true;
                setResult(-1);
                finish();
                return;
            case PHONE:
                this.mDidCompleteAllVerifications = true;
                setResult(-1);
                finish();
                return;
            default:
                Log.e(TAG, "Invalid verification");
                return;
        }
    }

    private void incrementVerificationStep(Verification type, int progressBarCaptionResId) {
        this.mCurrentVerification = type;
        this.mProgressBar.incrementStep();
        this.mProgressBar.setCaption(progressBarCaptionResId);
    }

    public void completeVerification() {
        CompleteProfileBaseFragment currentFragment = (CompleteProfileBaseFragment) getSupportFragmentManager().findFragmentById(C0880R.C0882id.content_container);
        if (currentFragment == null) {
            return;
        }
        if (Verification.PHOTO == this.mCurrentVerification) {
            currentFragment.animateCompletion(getString(C0880R.string.complete_profile_photo_verified), getString(C0880R.string.complete_profile_looking_good));
            if (isVerifiedIdFlow()) {
                VerifiedIdAnalytics.trackPhotoConfirmView(getVerifiedIdAnalyticsStrap());
                VerifiedIdAnalytics.trackHealth(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_PHOTO, "finish");
            }
        } else if (Verification.EMAIL == this.mCurrentVerification) {
            currentFragment.animateCompletion(getString(C0880R.string.complete_profile_email_verified), this.mUserEmailAddress);
            if (isVerifiedIdFlow()) {
                VerifiedIdAnalytics.trackEmailConfirmView(getVerifiedIdAnalyticsStrap());
                VerifiedIdAnalytics.trackHealth("email", "finish");
            }
        } else if (Verification.PHONE == this.mCurrentVerification) {
            currentFragment.animateCompletion(getString(C0880R.string.complete_profile_phone_verified), this.accountManager.getCurrentUser().getPhone());
            if (isVerifiedIdFlow()) {
                VerifiedIdAnalytics.trackPhoneConfirmView(getVerifiedIdAnalyticsStrap());
                VerifiedIdAnalytics.trackHealth("phone", "finish");
            }
        } else {
            throw new IllegalStateException("CompleteProfileActivity needs to have a current verification state.");
        }
    }

    public void updateUserEmail() {
        List<String> verifications = this.accountManager.getCurrentUser().getVerifications();
        if (verifications != null) {
            verifications.add("email");
        }
    }

    public void setUserEmailAddress(String userEmailAddress) {
        this.mUserEmailAddress = userEmailAddress;
    }

    private void sendEmailConfirmationCode() {
        displayLoaderFrame(true);
        new SendEmailConfirmationCodeRequest(Long.toString(this.accountManager.getCurrentUser().getId()), this.mEmailConfirmationCode, new NonResubscribableRequestListener<SendEmailConfirmationCodeResponse>() {
            public void onResponse(SendEmailConfirmationCodeResponse response) {
                CompleteProfileActivity.this.displayLoaderFrame(false);
                if (CompleteProfileActivity.this.mCurrentVerification == Verification.EMAIL) {
                    CompleteProfileActivity.this.completeVerification();
                }
            }

            public void onErrorResponse(AirRequestNetworkException error) {
                CompleteProfileActivity.this.displayLoaderFrame(false);
                if (CompleteProfileActivity.this.isActivityResumed()) {
                    ZenDialog.createSingleButtonDialog(C0880R.string.email_verification_failed, C0880R.string.email_verification_failed_body, C0880R.string.okay).show(CompleteProfileActivity.this.getSupportFragmentManager(), CompleteProfileActivity.EMAIL_FAILED);
                }
            }
        }).execute(this.requestManager);
    }

    /* access modifiers changed from: private */
    public void handleVerificationsResponse(VerificationRequirements verifications) {
        Log.i(TAG, verifications.toString());
        this.mVerificationRequirements = verifications;
        if (this.mEmailConfirmationCode == null && Verification.EMAIL.equals(this.mCurrentVerification) && verifications.emailVerificationComplete()) {
            completeVerification();
        }
    }

    private void initProgressBar() {
        this.mProgressBar = (StepProgressBar) findViewById(C0880R.C0882id.step_progress_bar);
        if (this.mNumberCompletedSteps == 0) {
            this.mNumberCompletedSteps = numberCompletedSteps();
            this.mProgressBar.initializeView(3, this.mNumberCompletedSteps);
            return;
        }
        this.mProgressBar.initializeView(6, this.mNumberCompletedSteps);
    }

    public void animateShowProgressBar() {
        if (this.mProgressBar.getVisibility() == 8) {
            this.mProgressBar.setVisibility(0);
        } else if (this.mProgressBar.getVisibility() == 4) {
            this.mAnimationHandler.postDelayed(CompleteProfileActivity$$Lambda$2.lambdaFactory$(this), 500);
        }
    }

    static /* synthetic */ void lambda$animateShowProgressBar$0(CompleteProfileActivity completeProfileActivity) {
        TranslateAnimation animate = new TranslateAnimation(0.0f, 0.0f, (float) (-completeProfileActivity.mProgressBar.getHeight()), 0.0f);
        animate.setDuration(500);
        animate.setFillAfter(true);
        completeProfileActivity.mProgressBar.startAnimation(animate);
        completeProfileActivity.mProgressBar.setVisibility(0);
    }

    private void setProgressBarCaption() {
        if (this.mCurrentVerification == Verification.PHOTO) {
            this.mProgressBar.setCaption(C0880R.string.verified_id_spb_add_your_photo);
        } else if (this.mCurrentVerification == Verification.EMAIL) {
            this.mProgressBar.setCaption(C0880R.string.verified_id_spb_confirm_email);
        } else {
            this.mProgressBar.setCaption(C0880R.string.verified_id_spb_confirm_phone);
        }
    }

    public void showProgressBar() {
        if (!this.mCompleteProfileType.isEditProfileType()) {
            animateShowProgressBar();
        }
    }

    public CompleteProfileType getType() {
        return this.mCompleteProfileType;
    }

    public String getPhoneNumberToVerify() {
        return this.mPhoneNumberToVerify;
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (getSupportFragmentManager().findFragmentById(C0880R.C0882id.content_container) != null) {
            ft.setCustomAnimations(C0880R.anim.fragment_enter, C0880R.anim.fragment_exit, C0880R.anim.fragment_enter_pop, C0880R.anim.fragment_exit_pop);
        }
        ft.replace(C0880R.C0882id.content_container, fragment);
        ft.commitAllowingStateLoss();
    }

    private int numberCompletedSteps() {
        int completedSteps = 1;
        if (!photoRequired()) {
            completedSteps = 1 + 1;
        }
        if (!emailRequired()) {
            completedSteps++;
        }
        if (!phoneRequired()) {
            return completedSteps + 1;
        }
        return completedSteps;
    }

    public String getReservationId() {
        if (this.mReservation != null) {
            return String.valueOf(this.mReservation.getId());
        }
        return null;
    }

    public Strap getVerifiedIdAnalyticsStrap() {
        return Strap.make().mo11639kv("reservation_id", getReservationId());
    }

    public void showChecklist() {
        VerifiedIdDialogSummaryFragment.newInstance(this.mVerificationRequirements).show(getSupportFragmentManager(), (String) null);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        getSupportFragmentManager().findFragmentById(C0880R.C0882id.content_container).onActivityResult(65535 & requestCode, resultCode, data);
    }

    public void displayLoaderFrame(boolean show) {
        this.mLoadingOverlay.setVisibility(show ? 0 : 8);
        if (!show) {
            this.mLoadingOverlay.finish();
        } else {
            this.mLoadingOverlay.startAnimation();
        }
    }
}
