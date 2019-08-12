package com.airbnb.android.registration;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseResponse;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.analytics.RegistrationAnalytics;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.responses.AccountResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.registration.models.AccountLoginData;
import com.airbnb.android.registration.models.AccountRegistrationData;
import com.airbnb.android.registration.models.AccountRegistrationStep;
import com.airbnb.android.registration.requests.AccountCreationRequest;
import com.airbnb.android.registration.requests.CreateSocialSignupRequest;
import com.airbnb.android.registration.responses.SocialSignupResponse;
import com.airbnb.android.utils.BundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.google.common.collect.ImmutableList;
import icepick.State;
import java.util.ArrayList;
import p032rx.Observer;

public class AccountRegistrationActivity extends AirActivity implements AccountRegistrationController {
    private static final String EXTRA_INITIAL_ACCOUNT_REGISTRATION_DATA = "extra_initial_account_registration_data";
    public static final String EXTRA_RESULT_ACCOUNT_REGISTRATION_DATA = "extra_result_account_registration_data";
    public static final String EXTRA_RESULT_ACCOUNT_SIGN_IN_DATA = "extra_result_account_sign_in_data";
    @State
    AccountRegistrationData accountRegistrationData;
    final RequestListener<AccountResponse> createAccountRequestListener = new AccountCreationRequestListener();
    final RequestListener<SocialSignupResponse> createSocialAccountRequestListener = new AccountCreationRequestListener();
    @BindView
    View fragmentContainer;
    @BindView
    LoaderFrame loaderFrame;
    @State
    ArrayList<AccountRegistrationStep> registrationSteps;
    @BindView
    AirToolbar toolbar;

    private class AccountCreationRequestListener<T extends BaseResponse> extends RequestListener<T> {
        private AccountCreationRequestListener() {
        }

        public void onResponse(T t) {
            RegistrationAnalytics.trackRequestResponseSuccess(RegistrationAnalytics.SIGN_UP_RESPONSE, AccountRegistrationActivity.this.accountRegistrationData.getRegistrationServiceForAnalytics(), AccountRegistrationActivity.getNavigationAnalyticsTag(), AccountRegistrationActivity.this);
            AccountRegistrationActivity.this.stopLoader();
            AccountRegistrationActivity.this.setResult(-1, new Intent().putExtra(AccountRegistrationActivity.EXTRA_RESULT_ACCOUNT_REGISTRATION_DATA, AccountRegistrationActivity.this.accountRegistrationData));
            AccountRegistrationActivity.this.finish();
        }

        public void onErrorResponse(AirRequestNetworkException e) {
            RegistrationAnalytics.trackRequestResponseFailure(RegistrationAnalytics.SIGN_UP_RESPONSE, AccountRegistrationActivity.this.accountRegistrationData.getRegistrationServiceForAnalytics(), AccountRegistrationActivity.getNavigationAnalyticsTag(), (NetworkException) e);
            AccountRegistrationActivity.this.stopLoader();
            NetworkUtil.tryShowErrorWithSnackbar(AccountRegistrationActivity.this.fragmentContainer, e, C1562R.string.sign_up_error, C1562R.string.sign_up_error_message);
        }
    }

    public static Intent newIntent(Context context) {
        return newIntent(context, AccountRegistrationData.builder().build());
    }

    public static Intent newIntent(Context context, AccountRegistrationData initialData) {
        return new Intent(context, AccountRegistrationActivity.class).putExtra(EXTRA_INITIAL_ACCOUNT_REGISTRATION_DATA, initialData);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C1562R.layout.activity_registration);
        ButterKnife.bind((Activity) this);
        setToolbar(this.toolbar);
        AccountRegistrationData initialData = (AccountRegistrationData) getIntent().getParcelableExtra(EXTRA_INITIAL_ACCOUNT_REGISTRATION_DATA);
        if (savedInstanceState == null) {
            this.accountRegistrationData = initialData;
            initializeSteps();
            showFragmentForStepWithAnimation((AccountRegistrationStep) this.registrationSteps.get(0));
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.navigationAnalytics.trackImpressionExplicitly(getNavigationAnalyticsTag(), null);
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    private void initializeSteps() {
        if (this.accountRegistrationData.isSocialSignUp()) {
            this.registrationSteps = new ArrayList<>(ImmutableList.m1287of(AccountRegistrationStep.Names, AccountRegistrationStep.AccountIdentifier, AccountRegistrationStep.Birthday));
        } else {
            this.registrationSteps = new ArrayList<>(ImmutableList.m1288of(AccountRegistrationStep.Names, AccountRegistrationStep.AccountIdentifier, AccountRegistrationStep.Password, AccountRegistrationStep.Birthday));
        }
    }

    public void onStepFinished(AccountRegistrationStep step, AccountRegistrationData data) {
        int stepIndex = getStepIndex(step);
        this.accountRegistrationData = AccountRegistrationData.updateAccountDataForStep(step, this.accountRegistrationData, data);
        if (stepIndex + 1 < this.registrationSteps.size()) {
            showFragmentForStepWithAnimation((AccountRegistrationStep) this.registrationSteps.get(stepIndex + 1));
            return;
        }
        startLoader();
        if (this.accountRegistrationData.isSocialSignUp()) {
            CreateSocialSignupRequest.forCreatingSocialAccount(this.accountRegistrationData).withListener((Observer) this.createSocialAccountRequestListener).execute(this.requestManager);
        } else {
            AccountCreationRequest.forAccountRegistration(this.accountRegistrationData).withListener((Observer) this.createAccountRequestListener).execute(this.requestManager);
        }
        RegistrationAnalytics.trackClickEvent(RegistrationAnalytics.SIGN_UP_REQUEST_BUTTON, this.accountRegistrationData.getRegistrationServiceForAnalytics(), NavigationTag.RegistrationBirthday);
    }

    public void switchToLoginWithLoginData(AccountLoginData data) {
        setResult(-1, new Intent().putExtra(EXTRA_RESULT_ACCOUNT_SIGN_IN_DATA, data));
        finish();
    }

    private int getStepIndex(AccountRegistrationStep step) {
        return this.registrationSteps.indexOf(step);
    }

    private void showFragmentForStepWithAnimation(AccountRegistrationStep step) {
        showFragment(Fragment.instantiate(this, registrationStepToFragmentClassName(step), ((BundleBuilder) new BundleBuilder().putParcelable(BaseRegistrationFragment.ARG_ACCOUNT_REG_DATA, this.accountRegistrationData)).toBundle()), C1562R.C1564id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }

    private static String registrationStepToFragmentClassName(AccountRegistrationStep step) {
        switch (step) {
            case AccountIdentifier:
                return AccountIdentifierRegistrationFragment.class.getCanonicalName();
            case Password:
                return EditPasswordRegistrationFragment.class.getCanonicalName();
            case Names:
                return EditNamesRegistrationFragment.class.getCanonicalName();
            case Birthday:
                return EditBirthdayRegistrationFragment.class.getCanonicalName();
            default:
                throw new IllegalArgumentException("Invalid step " + step);
        }
    }

    public void showFragment(Fragment fragment, boolean addToBackStack) {
        showFragment(fragment, C1562R.C1564id.content_container, FragmentTransitionType.SlideInFromSide, addToBackStack);
    }

    private void startLoader() {
        this.loaderFrame.startAnimation();
    }

    /* access modifiers changed from: private */
    public void stopLoader() {
        this.loaderFrame.finishImmediate();
    }

    /* access modifiers changed from: protected */
    public boolean denyRequireAccountFromChild() {
        return true;
    }

    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(C1562R.C1564id.content_container);
        if (fragment == null || !fragment.getChildFragmentManager().popBackStackImmediate()) {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: private */
    public static NavigationTag getNavigationAnalyticsTag() {
        return NavigationTag.RegistrationSignup;
    }
}
