package com.airbnb.android.identity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.content.ContextCompat;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.RequestManager;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.analytics.IdentityJitneyLogger;
import com.airbnb.android.core.analytics.IdentityJitneyLogger.Element;
import com.airbnb.android.core.analytics.NavigationLogging;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.enums.VerificationFlow;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.identity.AccountVerificationStep;
import com.airbnb.android.core.intents.AccountVerificationActivityIntents;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.requests.UpdateMemoryRequest;
import com.airbnb.android.core.requests.UpdatePhoneNumberRequest;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.identity.utils.AccountVerificationUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.collections.SheetState;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.RefreshLoader;
import com.airbnb.p027n2.components.SheetProgressBar;
import com.airbnb.paris.Paris;
import com.google.common.collect.ImmutableMap;
import icepick.State;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AccountVerificationActivity extends AirActivity implements AccountVerificationController {
    protected static final int RC_BACK = 1003;
    private static final int RC_CANCEL_EXIT = 1001;
    private static final int RC_CONFIRM_EXIT = 1002;
    private static final Map<AccountVerificationStep, String> verificationStepToFragment = ImmutableMap.builder().put(AccountVerificationStep.ProfilePhoto, AccountVerificationProfilePhotoFragment.class.getCanonicalName()).put(AccountVerificationStep.Phone, AccountVerificationPhoneNumberInputFragment.class.getCanonicalName()).put(AccountVerificationStep.Email, AccountVerificationEmailInputFragment.class.getCanonicalName()).put(AccountVerificationStep.OfflineId, AccountVerificationOfflineIdInfoFragment.class.getCanonicalName()).put(AccountVerificationStep.Selfie, AccountVerificationSelfieFragment.class.getCanonicalName()).put(AccountVerificationStep.PhotoReview, AccountVerificationSelfieConfirmFragment.class.getCanonicalName()).put(AccountVerificationStep.DeviceNotSupported, AccountVerificationDeviceNotSupportedFragment.class.getCanonicalName()).build();
    private static final Map<AccountVerificationStep, String> verificationStepToFragmentV2 = ImmutableMap.builder().put(AccountVerificationStep.OfflineId, IdentityGovIdFragment.class.getCanonicalName()).put(AccountVerificationStep.Selfie, IdentitySelfieInfoFragment.class.getCanonicalName()).put(AccountVerificationStep.SelfieCapture, IdentitySelfieCaptureFragment.class.getCanonicalName()).put(AccountVerificationStep.PhotoReview, IdentitySelfieReviewFragment.class.getCanonicalName()).build();
    @State
    AccountVerificationStep currentStep;
    private final Handler handler = new Handler();
    @State
    User host;
    IdentityJitneyLogger identityJitneyLogger;
    @State
    boolean isModal;
    @State
    boolean isMoveToLastStep;
    AccountVerificationOfflineIdController offlineIdController;
    @BindView
    SheetProgressBar progressBar;
    @BindView
    RefreshLoader refreshLoader;
    @State
    ArrayList<AccountVerificationStep> requiredSteps;
    @State
    ArrayList<String> selfiePhotoFilePaths;
    @BindView
    AirToolbar toolbar;
    @State
    VerificationFlow verificationFlow;
    @State
    User verificationUser;
    final RequestListener<Object> verifyConfirmationCodeListener = new C0699RL().onResponse(AccountVerificationActivity$$Lambda$1.lambdaFactory$()).onError(AccountVerificationActivity$$Lambda$2.lambdaFactory$()).build();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        boolean z;
        super.onCreate(savedInstanceState);
        setContentView(C6533R.layout.activity_identity_flows);
        ButterKnife.bind((Activity) this);
        ((IdentityGraph) CoreApplication.instance(this).component()).inject(this);
        setToolbar(this.toolbar);
        Paris.style(this.refreshLoader).applyInverse();
        if (savedInstanceState == null) {
            this.requiredSteps = getIntent().getParcelableArrayListExtra(AccountVerificationActivityIntents.EXTRA_REQUIRED_VERIFICATION_STEPS);
            this.verificationFlow = (VerificationFlow) getIntent().getSerializableExtra("extra_verification_flow");
            this.verificationUser = (User) getIntent().getParcelableExtra(AccountVerificationActivityIntents.EXTRA_VERIFICATION_USER);
            this.host = (User) getIntent().getParcelableExtra("extra_host");
            String phoneVerificationCode = getIntent().getStringExtra(AccountVerificationActivityIntents.EXTRA_PHONE_VERIFICATION_CODE);
            this.selfiePhotoFilePaths = getIntent().getStringArrayListExtra(AccountVerificationActivityIntents.EXTRA_SELFIE_PHOTOS_FILE_PATH);
            this.isMoveToLastStep = getIntent().getBooleanExtra(AccountVerificationActivityIntents.EXTRA_MOVE_TO_LAST_STEP, false);
            this.isModal = getIntent().getBooleanExtra(AccountVerificationActivityIntents.EXTRA_IS_MODAL, false);
            updateVerificationSteps();
            if (this.requiredSteps.contains(AccountVerificationStep.Selfie)) {
                UpdateMemoryRequest.forVerifiedIDReplacement().execute(NetworkUtil.singleFireExecutor());
            }
            if (phoneVerificationCode != null && !this.requestManager.hasRequest((BaseRequestListener<T>) this.verifyConfirmationCodeListener, UpdatePhoneNumberRequest.class)) {
                UpdatePhoneNumberRequest.verifyPhoneNumber(phoneVerificationCode).withListener(this.verifyConfirmationCodeListener).execute(this.requestManager);
            }
            this.currentStep = this.isMoveToLastStep ? (AccountVerificationStep) this.requiredSteps.get(this.requiredSteps.size() - 1) : (AccountVerificationStep) this.requiredSteps.get(0);
            showFragmentForCurrentStep();
        }
        SheetProgressBar sheetProgressBar = this.progressBar;
        if (this.verificationFlow == VerificationFlow.Booking) {
            z = true;
        } else {
            z = false;
        }
        ViewUtils.setVisibleIf((View) sheetProgressBar, z);
        updateProgressBar(false);
    }

    private void updateVerificationSteps() {
        if ((this.requiredSteps.contains(AccountVerificationStep.OfflineId) || this.requiredSteps.contains(AccountVerificationStep.Selfie)) && !AccountVerificationUtils.isPhotoCapturePossible(this, this.requiredSteps)) {
            this.requiredSteps.remove(AccountVerificationStep.OfflineId);
            this.requiredSteps.remove(AccountVerificationStep.Selfie);
            this.requiredSteps.add(AccountVerificationStep.DeviceNotSupported);
        } else if (this.requiredSteps.contains(AccountVerificationStep.Selfie)) {
            if (this.verificationFlow.isIdentityRedesign()) {
                this.requiredSteps.add(AccountVerificationStep.SelfieCapture);
            }
            this.requiredSteps.add(AccountVerificationStep.PhotoReview);
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1002 && resultCode == -1) {
            trackFragmentButtonClick("confirm_close_button");
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onHomeActionPressed() {
        popBackStackWithCheck();
    }

    public void onBackPressed() {
        popBackStackWithCheck();
    }

    /* access modifiers changed from: private */
    public void popBackStackWithCheck() {
        KeyboardUtils.dismissSoftKeyboard((Activity) this);
        Fragment fragment = getSupportFragmentManager().findFragmentById(C6533R.C6535id.content_container);
        if (this.currentStep != null && !(fragment instanceof IdentityLoaderFragment)) {
            this.identityJitneyLogger.logClick(this.verificationFlow, getUser(), this.currentStep.toIdentityVerificationType(), this.currentStep.toPage(), Element.navigation_button_back);
        }
        if (getSupportFragmentManager().popBackStackImmediate()) {
            Fragment fragment2 = getSupportFragmentManager().findFragmentById(C6533R.C6535id.content_container);
            if (fragment2 instanceof IdentityLoaderFragment) {
                ((IdentityLoaderFragment) fragment2).show();
                return;
            }
            return;
        }
        if (this.isMoveToLastStep) {
            int currentStepIndex = getCurrentStepIndex();
            if (currentStepIndex > 0) {
                this.currentStep = (AccountVerificationStep) this.requiredSteps.get(currentStepIndex - 1);
                showFragmentForPopBackFromCurrentStep();
                return;
            }
        }
        setResult(1003);
        finish();
    }

    public void onStepFinished(AccountVerificationStep step, boolean fromOnActivityResult) {
        int currentStepIndex = this.requiredSteps.indexOf(step);
        if (currentStepIndex + 1 < this.requiredSteps.size()) {
            this.currentStep = (AccountVerificationStep) this.requiredSteps.get(currentStepIndex + 1);
            updateProgressBar(true);
            if (fromOnActivityResult) {
                this.handler.post(AccountVerificationActivity$$Lambda$3.lambdaFactory$(this));
            } else {
                showFragmentForCurrentStep();
            }
        } else if (!this.verificationFlow.showFinishFragment()) {
            Intent data = new Intent();
            data.putExtra(AccountVerificationActivityIntents.EXTRA_SELFIE_PHOTOS_FILE_PATH, this.selfiePhotoFilePaths);
            data.putExtra("extra_verification_flow", this.verificationFlow.ordinal());
            setResult(-1, data);
            finish();
        } else if (fromOnActivityResult) {
            this.handler.post(AccountVerificationActivity$$Lambda$4.lambdaFactory$(this));
        } else {
            showFinishFragment();
        }
    }

    /* access modifiers changed from: private */
    public void showFinishFragment() {
        showFragment(instantiateFragment(getFinishFragmentName()), C6533R.C6535id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }

    private Fragment instantiateFragment(String name) {
        return ((FragmentBundleBuilder) FragmentBundler.make(Fragment.instantiate(this, name)).putSerializable("arg_verification_flow", this.verificationFlow)).build();
    }

    private String getFinishFragmentName() {
        return this.verificationFlow.isIdentityRedesign() ? IdentityFinishFragment.class.getCanonicalName() : AccountVerificationFinishFragment.class.getCanonicalName();
    }

    private void updateProgressBar(boolean animate) {
        this.progressBar.setProgress(Math.max(((float) getCurrentStepIndex()) / ((float) this.requiredSteps.size()), 0.02f), animate);
    }

    private int getCurrentStepIndex() {
        return this.requiredSteps.indexOf(this.currentStep);
    }

    public void showPreviousStep() {
        this.handler.post(AccountVerificationActivity$$Lambda$5.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public void showFragmentForCurrentStep() {
        showFragmentForStep(instantiateFragment(getVerificationStepToFragment(this.currentStep)), this.currentStep);
    }

    private void showFragmentForPopBackFromCurrentStep() {
        showFragmentForStepPerformingPopBackStackAnimation(((FragmentBundleBuilder) FragmentBundler.make(instantiateFragment(getVerificationStepToFragment(this.currentStep))).putSerializable("arg_verification_flow", this.verificationFlow)).build(), this.currentStep);
    }

    public void showFragmentForStep(Fragment fragment, AccountVerificationStep step) {
        showFragment(fragment, C6533R.C6535id.content_container, FragmentTransitionType.SlideInFromSide, !this.isMoveToLastStep, fragment.getClass().getSimpleName());
    }

    public void showFragmentForStepPerformingPopBackStackAnimation(Fragment fragment, AccountVerificationStep step) {
        showFragment(fragment, C6533R.C6535id.content_container, FragmentTransitionType.SlideInFromSidePop, false);
    }

    public void setState(SheetState state) {
        this.progressBar.setColor(ContextCompat.getColor(this, state.progressBarColor));
    }

    public void setSelfieFilePathsForPreview(List<String> selfieFilePaths) {
        this.selfiePhotoFilePaths = (ArrayList) selfieFilePaths;
    }

    public List<String> getSelfieFilePaths() {
        return this.selfiePhotoFilePaths;
    }

    public AirToolbar getAirToolbar() {
        return this.toolbar;
    }

    public void onLicensePhotosCaptured(String frontPhotoPath, String backPhotoPath, String barCode) {
        this.offlineIdController.handleMiSnapResults(frontPhotoPath, backPhotoPath, barCode);
    }

    public void showLoader(boolean show) {
        this.refreshLoader.setVisibility(show ? 0 : 8);
    }

    private void trackFragmentButtonClick(String target) {
        AirFragment currentFragment = (AirFragment) getSupportFragmentManager().findFragmentById(C6533R.C6535id.content_container);
        if (currentFragment != null) {
            AccountVerificationAnalytics.trackButtonClick(currentFragment.getNavigationTrackingTag(), target);
        }
    }

    public void initOfflineIdController(Bundle savedInstanceState, AirFragment fragment, RequestManager requestManager, NavigationLogging navigationAnalytics) {
        this.offlineIdController = new AccountVerificationOfflineIdController(savedInstanceState, this, fragment, requestManager, this.handler, navigationAnalytics, this.verificationFlow);
    }

    public AccountVerificationOfflineIdController getOfflineIdController() {
        return this.offlineIdController;
    }

    public boolean isModal() {
        return this.isModal;
    }

    public boolean isFirstStep() {
        if (getCurrentStepIndex() != 0) {
            return false;
        }
        return getVerificationStepToFragment(this.currentStep).equals(getSupportFragmentManager().findFragmentById(C6533R.C6535id.content_container).getClass().getCanonicalName());
    }

    private String getVerificationStepToFragment(AccountVerificationStep step) {
        if (!this.verificationFlow.isIdentityRedesign() || !verificationStepToFragmentV2.containsKey(step)) {
            return (String) verificationStepToFragment.get(step);
        }
        return (String) verificationStepToFragmentV2.get(step);
    }

    public IdentityJitneyLogger getIdentityJitneyLogger() {
        return this.identityJitneyLogger;
    }

    public User getUser() {
        return this.verificationUser == null ? this.accountManager.getCurrentUser() : this.verificationUser;
    }

    public User getHost() {
        return this.host;
    }
}
