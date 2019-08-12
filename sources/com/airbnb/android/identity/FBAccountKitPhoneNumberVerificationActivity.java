package com.airbnb.android.identity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.CoreGraph;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.analytics.AccountVerificationAnalytics;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.requests.UpdatePhoneNumberRequest;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.LoaderFrame;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.AccountKitLoginResult;
import com.facebook.accountkit.p029ui.AccountKitActivity;
import com.facebook.accountkit.p029ui.AccountKitActivity.ResponseType;
import com.facebook.accountkit.p029ui.AccountKitConfiguration.AccountKitConfigurationBuilder;
import com.facebook.accountkit.p029ui.LoginType;
import com.facebook.accountkit.p029ui.ThemeUIManager;

public class FBAccountKitPhoneNumberVerificationActivity extends AirActivity {
    public static int APP_REQUEST_CODE = 617;
    private static String FB_PHONE_VERIFICATION_RESPONSE = "fb_phone_verification_response";
    @BindView
    LoaderFrame loaderFrame;
    final RequestListener<Object> phoneNumberVerificationListener = new C0699RL().onResponse(FBAccountKitPhoneNumberVerificationActivity$$Lambda$1.lambdaFactory$(this)).onError(FBAccountKitPhoneNumberVerificationActivity$$Lambda$2.lambdaFactory$(this)).onComplete(FBAccountKitPhoneNumberVerificationActivity$$Lambda$3.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$0(FBAccountKitPhoneNumberVerificationActivity fBAccountKitPhoneNumberVerificationActivity, Object response) {
        fBAccountKitPhoneNumberVerificationActivity.setResult(-1);
        Toast.makeText(fBAccountKitPhoneNumberVerificationActivity, fBAccountKitPhoneNumberVerificationActivity.resourceManager.getString(C6533R.string.account_verification_phone_number_with_fb_account_kit_success), 1).show();
        AccountVerificationAnalytics.trackRequestSuccess(NavigationTag.VerificationPhoneFB, FB_PHONE_VERIFICATION_RESPONSE);
    }

    static /* synthetic */ void lambda$new$1(FBAccountKitPhoneNumberVerificationActivity fBAccountKitPhoneNumberVerificationActivity, AirRequestNetworkException e) {
        Toast.makeText(fBAccountKitPhoneNumberVerificationActivity, fBAccountKitPhoneNumberVerificationActivity.getErrorMessage(e), 1).show();
        AccountVerificationAnalytics.trackRequestFailure(NavigationTag.VerificationPhoneFB, FB_PHONE_VERIFICATION_RESPONSE);
    }

    private String getErrorMessage(AirRequestNetworkException e) {
        if (NetworkUtil.errorCode(e).intValue() == 500) {
            return this.resourceManager.getString(C6533R.string.account_verification_phone_number_with_fb_account_kit_error);
        }
        return NetworkUtil.getErrorMessage(this, e, C6533R.string.account_verification_phone_number_with_fb_account_kit_error);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, FBAccountKitPhoneNumberVerificationActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((CoreGraph) CoreApplication.instance(this).component()).inject((AirActivity) this);
        setContentView(C6533R.layout.activity_fb_phone_verification);
        ButterKnife.bind((Activity) this);
        startFBAccountKitPhoneNumberVerification();
    }

    public void startFBAccountKitPhoneNumberVerification() {
        Intent intent = new Intent(this, AccountKitActivity.class);
        AccountKitConfigurationBuilder configurationBuilder = new AccountKitConfigurationBuilder(LoginType.PHONE, ResponseType.TOKEN);
        configurationBuilder.setUIManager(new ThemeUIManager(C6533R.C6538style.AirbnbAccountKit));
        intent.putExtra(AccountKitActivity.ACCOUNT_KIT_ACTIVITY_CONFIGURATION, configurationBuilder.build());
        startActivityForResult(intent, APP_REQUEST_CODE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        AccountKitLoginResult loginResult = AccountKit.loginResultWithIntent(data);
        if (requestCode == APP_REQUEST_CODE) {
            if (loginResult.getError() != null) {
                String toastMessage = loginResult.getError().getErrorType().getMessage();
                Toast.makeText(this, toastMessage, 1).show();
                AccountVerificationAnalytics.trackFBAccountKitPhoneNumberVerificationActions("login_failed_" + toastMessage);
            } else if (loginResult.wasCancelled()) {
                AccountVerificationAnalytics.trackFBAccountKitPhoneNumberVerificationActions("verify_canceled");
            } else if (loginResult.getAccessToken() != null) {
                AccountVerificationAnalytics.trackFBAccountKitPhoneNumberVerificationActions("get_access_token_success");
            } else {
                AccountVerificationAnalytics.trackFBAccountKitPhoneNumberVerificationActions("get_access_token_failed");
            }
        }
        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            public void onSuccess(Account account) {
                if (FBAccountKitPhoneNumberVerificationActivity.this.isFinishing() || FBAccountKitPhoneNumberVerificationActivity.this.supportIsDestroyed()) {
                    BugsnagWrapper.notify((Throwable) new IllegalStateException("FB Accountkit get account success after activity being destroyed"));
                } else if (!TextUtils.isEmpty(String.valueOf(account.getPhoneNumber()))) {
                    UpdatePhoneNumberRequest.autoVerifyPhoneNumberWithFBAccountKit(account.getPhoneNumber().toString()).withListener(FBAccountKitPhoneNumberVerificationActivity.this.phoneNumberVerificationListener).execute(FBAccountKitPhoneNumberVerificationActivity.this.requestManager);
                    AccountVerificationAnalytics.trackFBAccountKitPhoneNumberVerificationActions("verification_retrieve_phone_number_success");
                } else {
                    AccountVerificationAnalytics.trackFBAccountKitPhoneNumberVerificationActions("verification_retrieve_phone_number_success_with_empty_phone_number");
                    FBAccountKitPhoneNumberVerificationActivity.this.finish();
                }
            }

            public void onError(AccountKitError error) {
                if (FBAccountKitPhoneNumberVerificationActivity.this.isFinishing() || FBAccountKitPhoneNumberVerificationActivity.this.supportIsDestroyed()) {
                    BugsnagWrapper.notify((Throwable) new IllegalStateException("FB Accountkit get account failed after activity being destroyed"));
                    return;
                }
                if (!TextUtils.isEmpty(error.getUserFacingMessage())) {
                    Toast.makeText(FBAccountKitPhoneNumberVerificationActivity.this, error.getUserFacingMessage(), 0).show();
                }
                AccountVerificationAnalytics.trackFBAccountKitPhoneNumberVerificationActions("verification_retrieve_phone_number_error_" + error.getUserFacingMessage());
                FBAccountKitPhoneNumberVerificationActivity.this.finish();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.loaderFrame.finishImmediate();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.loaderFrame.startAnimation();
    }
}
