package com.facebook.accountkit.p029ui;

import android.content.Context;
import android.content.Intent;
import android.support.p000v4.content.LocalBroadcastManager;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.C3354R;
import com.facebook.accountkit.PhoneLoginModel;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.internal.Utility;
import com.facebook.accountkit.p029ui.LoginFlowBroadcastReceiver.Event;
import com.facebook.accountkit.p029ui.PrivacyPolicyFragment.OnCompleteListener;
import com.facebook.accountkit.p029ui.TitleFragmentFactory.TitleFragment;

/* renamed from: com.facebook.accountkit.ui.ConfirmAccountVerifiedContentController */
final class ConfirmAccountVerifiedContentController extends ContentControllerBase implements ButtonContentController {
    private static final ButtonType DEFAULT_BUTTON_TYPE = ButtonType.CONTINUE;
    private static final LoginFlowState LOGIN_FLOW_STATE = LoginFlowState.ACCOUNT_VERIFIED;
    /* access modifiers changed from: private */
    public PrivacyPolicyFragment bottomFragment;
    private ButtonType buttonType = DEFAULT_BUTTON_TYPE;
    private ContentFragment centerFragment;
    TitleFragment footerFragment;
    TitleFragment headerFragment;
    private OnCompleteListener onCompleteListener;
    private ContentFragment textFragment;
    /* access modifiers changed from: private */
    public ContentFragment topFragment;

    /* renamed from: com.facebook.accountkit.ui.ConfirmAccountVerifiedContentController$BottomFragment */
    public static class BottomFragment extends PrivacyPolicyFragment {
        private static final String ACCOUNT_KIT_URL = "https://www.accountkit.com/faq";

        public static BottomFragment create(UIManager uiManager, LoginFlowState loginFlowState, ButtonType nextButtonType) {
            BottomFragment fragment = new BottomFragment();
            fragment.getViewState().putParcelable(ViewStateFragment.UI_MANAGER_KEY, uiManager);
            fragment.setLoginFlowState(loginFlowState);
            fragment.setNextButtonType(nextButtonType);
            return fragment;
        }

        /* access modifiers changed from: protected */
        public void updateTermsText(TextView termsTextView, CharSequence nextButtonText) {
            if (termsTextView != null && getActivity() != null) {
                PhoneLoginModel loginModel = AccountKit.getCurrentPhoneNumberLogInModel();
                if (loginModel == null || Utility.isNullOrEmpty(loginModel.getPrivacyPolicy())) {
                    termsTextView.setText(Html.fromHtml(getString(C3354R.string.com_accountkit_confirmation_code_agreement_instant_verification, new Object[]{nextButtonText, "https://m.facebook.com/terms", "https://m.facebook.com/about/privacy/", "https://m.facebook.com/help/cookies/update", ACCOUNT_KIT_URL})));
                } else if (!Utility.isNullOrEmpty(loginModel.getTermsOfService())) {
                    termsTextView.setText(Html.fromHtml(getString(C3354R.string.f3088x1afe87bc, new Object[]{nextButtonText, "https://m.facebook.com/terms", "https://m.facebook.com/about/privacy/", "https://m.facebook.com/help/cookies/update", loginModel.getPrivacyPolicy(), loginModel.getTermsOfService(), AccountKit.getApplicationName(), ACCOUNT_KIT_URL})));
                } else {
                    termsTextView.setText(Html.fromHtml(getString(C3354R.string.f3089x43e88c5c, new Object[]{nextButtonText, "https://m.facebook.com/terms", "https://m.facebook.com/about/privacy/", "https://m.facebook.com/help/cookies/update", loginModel.getPrivacyPolicy(), AccountKit.getApplicationName(), ACCOUNT_KIT_URL})));
                }
            }
        }
    }

    ConfirmAccountVerifiedContentController(AccountKitConfiguration configuration) {
        super(configuration);
    }

    public ContentFragment getBottomFragment() {
        if (this.bottomFragment == null) {
            setBottomFragment(BottomFragment.create(this.configuration.getUIManager(), LOGIN_FLOW_STATE, DEFAULT_BUTTON_TYPE));
        }
        return this.bottomFragment;
    }

    public void setBottomFragment(ContentFragment fragment) {
        if (fragment instanceof BottomFragment) {
            this.bottomFragment = (BottomFragment) fragment;
            this.bottomFragment.setOnCompleteListener(getOnCompleteListener());
            this.bottomFragment.setRetryVisible(false);
            updateNextButton();
        }
    }

    public ButtonType getButtonType() {
        return this.buttonType;
    }

    public void setButtonType(ButtonType buttonType2) {
        this.buttonType = buttonType2;
        updateNextButton();
    }

    public ContentFragment getCenterFragment() {
        if (this.centerFragment == null) {
            setCenterFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState()));
        }
        return this.centerFragment;
    }

    public void setCenterFragment(ContentFragment fragment) {
        this.centerFragment = fragment;
    }

    public View getFocusView() {
        return null;
    }

    public TitleFragment getFooterFragment() {
        if (this.footerFragment == null) {
            setFooterFragment(TitleFragmentFactory.create(this.configuration.getUIManager()));
        }
        return this.footerFragment;
    }

    public void setFooterFragment(TitleFragment fragment) {
        this.footerFragment = fragment;
    }

    public TitleFragment getHeaderFragment() {
        if (this.headerFragment == null) {
            setHeaderFragment(TitleFragmentFactory.create(this.configuration.getUIManager(), C3354R.string.com_accountkit_account_verified, new String[0]));
        }
        return this.headerFragment;
    }

    public void setHeaderFragment(TitleFragment fragment) {
        this.headerFragment = fragment;
    }

    public LoginFlowState getLoginFlowState() {
        return LOGIN_FLOW_STATE;
    }

    public ContentFragment getTextFragment() {
        if (this.textFragment == null) {
            setTextFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState()));
        }
        return this.textFragment;
    }

    public void setTextFragment(ContentFragment fragment) {
        this.textFragment = fragment;
    }

    public ContentFragment getTopFragment() {
        if (this.topFragment == null) {
            setTopFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState()));
        }
        return this.topFragment;
    }

    public void setTopFragment(ContentFragment fragment) {
        this.topFragment = fragment;
    }

    public boolean isTransient() {
        return false;
    }

    private void updateNextButton() {
        if (this.topFragment != null && this.bottomFragment != null) {
            this.bottomFragment.setNextButtonType(getButtonType());
        }
    }

    /* access modifiers changed from: protected */
    public void logImpression() {
        if (this.bottomFragment != null) {
            Logger.logUIConfirmAccountVerified(true);
        }
    }

    private OnCompleteListener getOnCompleteListener() {
        if (this.onCompleteListener == null) {
            this.onCompleteListener = new OnCompleteListener() {
                public void onNext(Context context, String buttonName) {
                    if (ConfirmAccountVerifiedContentController.this.topFragment != null && ConfirmAccountVerifiedContentController.this.bottomFragment != null) {
                        Logger.logUIConfirmSeamlessLoginInteraction(buttonName);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.CONFIRM_SEAMLESS_LOGIN));
                    }
                }

                public void onRetry(Context context) {
                }
            };
        }
        return this.onCompleteListener;
    }
}
