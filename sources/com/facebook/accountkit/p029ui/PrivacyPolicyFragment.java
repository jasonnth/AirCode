package com.facebook.accountkit.p029ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.C3354R;
import com.facebook.accountkit.PhoneLoginModel;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.internal.Utility;
import com.facebook.accountkit.p029ui.SkinManager.Skin;

/* renamed from: com.facebook.accountkit.ui.PrivacyPolicyFragment */
public class PrivacyPolicyFragment extends ContentFragment {
    protected static final String COOKIE_URL = "https://m.facebook.com/help/cookies/update";
    protected static final String DATA_URL = "https://m.facebook.com/about/privacy/";
    private static final String LOGIN_FLOW_STATE = "login_flow_state";
    private static final String NEXT_BUTTON_TYPE = "next_button_type";
    private static final String RETRY_BUTTON_VISIBLE = "retry button visible";
    private static final String RETRY_KEY = "retry";
    protected static final String TERMS_URL = "https://m.facebook.com/terms";
    private LoginFlowState loginFlowState;
    private Button nextButton;
    private boolean nextButtonEnabled = true;
    private ButtonType nextButtonType;
    /* access modifiers changed from: private */
    public OnCompleteListener onCompleteListener;
    private TextView retryButton;
    private boolean retryButtonVisible = true;
    private TextView termsText;

    /* renamed from: com.facebook.accountkit.ui.PrivacyPolicyFragment$OnCompleteListener */
    public interface OnCompleteListener {
        void onNext(Context context, String str);

        void onRetry(Context context);
    }

    public /* bridge */ /* synthetic */ void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    public /* bridge */ /* synthetic */ void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public /* bridge */ /* synthetic */ View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public /* bridge */ /* synthetic */ void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public static PrivacyPolicyFragment create(UIManager uiManager, LoginFlowState loginFlowState2, ButtonType nextButtonType2) {
        PrivacyPolicyFragment fragment = new PrivacyPolicyFragment();
        fragment.getViewState().putParcelable(ViewStateFragment.UI_MANAGER_KEY, uiManager);
        fragment.setLoginFlowState(loginFlowState2);
        fragment.setNextButtonType(nextButtonType2);
        return fragment;
    }

    public LoginFlowState getLoginFlowState() {
        return this.loginFlowState;
    }

    /* access modifiers changed from: 0000 */
    public boolean isKeyboardFragment() {
        return true;
    }

    /* access modifiers changed from: protected */
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(C3354R.layout.com_accountkit_fragment_confirmation_code_bottom, container, false);
        if (ViewUtility.isSkin(getUIManager(), Skin.CONTEMPORARY)) {
            View btn = view.findViewById(C3354R.C3356id.com_accountkit_next_button);
            ((ViewGroup) btn.getParent()).removeView(btn);
            View space = view.findViewById(C3354R.C3356id.com_accountkit_space);
            ((ViewGroup) space.getParent()).removeView(space);
            view.addView(space);
            view.addView(btn);
        }
        return view;
    }

    /* access modifiers changed from: protected */
    public void onViewReadyWithState(View view, Bundle viewState) {
        super.onViewReadyWithState(view, viewState);
        this.nextButtonType = ButtonType.values()[viewState.getInt(NEXT_BUTTON_TYPE)];
        this.loginFlowState = LoginFlowState.values()[viewState.getInt(LOGIN_FLOW_STATE)];
        this.retryButtonVisible = viewState.getBoolean(RETRY_BUTTON_VISIBLE, true);
        this.nextButton = (Button) view.findViewById(C3354R.C3356id.com_accountkit_next_button);
        this.retryButton = (TextView) view.findViewById(C3354R.C3356id.com_accountkit_retry_button);
        if (this.nextButton != null) {
            this.nextButton.setEnabled(this.nextButtonEnabled);
            this.nextButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    if (PrivacyPolicyFragment.this.onCompleteListener != null) {
                        PrivacyPolicyFragment.this.onCompleteListener.onNext(v.getContext(), Buttons.ENTER_CONFIRMATION_CODE.name());
                    }
                }
            });
            this.nextButton.setText(this.nextButtonType.getValue());
        }
        if (this.retryButton != null) {
            this.retryButton.setVisibility(this.retryButtonVisible ? 0 : 8);
            this.retryButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Logger.logUIResendInteraction(Buttons.DID_NOT_GET_CODE.name());
                    if (PrivacyPolicyFragment.this.onCompleteListener != null) {
                        PrivacyPolicyFragment.this.onCompleteListener.onRetry(v.getContext());
                    }
                }
            });
            this.retryButton.setTextColor(ViewUtility.getButtonColor(getActivity(), getUIManager()));
        }
        this.termsText = (TextView) view.findViewById(C3354R.C3356id.com_accountkit_confirmation_code_agreement);
        if (this.termsText != null) {
            this.termsText.setMovementMethod(new CustomLinkMovement(new OnURLClickedListener() {
                public void onURLClicked(String url) {
                    Logger.logUIConfirmationCodeInteraction(Buttons.POLICY_LINKS.name(), url);
                }
            }));
        }
        updateTermsText(this.termsText, this.nextButton.getText());
    }

    public void onStart() {
        super.onStart();
        updateTermsText(this.termsText, this.nextButton.getText());
    }

    public void setNextButtonType(ButtonType buttonType) {
        this.nextButtonType = buttonType;
        getViewState().putInt(NEXT_BUTTON_TYPE, this.nextButtonType.ordinal());
        if (this.nextButton != null) {
            this.nextButton.setText(buttonType.getValue());
        }
    }

    public void setNextButtonEnabled(boolean enabled) {
        this.nextButtonEnabled = enabled;
        if (this.nextButton != null) {
            this.nextButton.setEnabled(enabled);
        }
    }

    public void setRetryVisible(boolean visible) {
        this.retryButtonVisible = visible;
        getViewState().putBoolean(RETRY_BUTTON_VISIBLE, this.retryButtonVisible);
        if (this.retryButton != null) {
            this.retryButton.setVisibility(visible ? 0 : 8);
        }
    }

    public void setOnCompleteListener(OnCompleteListener onCompleteListener2) {
        this.onCompleteListener = onCompleteListener2;
    }

    public boolean getRetry() {
        return getViewState().getBoolean("retry", false);
    }

    public void setRetry(boolean retry) {
        getViewState().putBoolean("retry", retry);
    }

    /* access modifiers changed from: protected */
    public void updateTermsText(TextView termsTextView, CharSequence nextButtonText) {
        if (termsTextView != null && getActivity() != null) {
            PhoneLoginModel loginModel = AccountKit.getCurrentPhoneNumberLogInModel();
            if (loginModel == null || Utility.isNullOrEmpty(loginModel.getPrivacyPolicy())) {
                termsTextView.setText(getConfirmationCodeAgreementText(nextButtonText));
            } else if (!Utility.isNullOrEmpty(loginModel.getTermsOfService())) {
                termsTextView.setText(Html.fromHtml(getString(C3354R.string.f3087xaaa1945c, new Object[]{nextButtonText, TERMS_URL, DATA_URL, COOKIE_URL, loginModel.getPrivacyPolicy(), loginModel.getTermsOfService(), AccountKit.getApplicationName()})));
            } else {
                termsTextView.setText(Html.fromHtml(getString(C3354R.string.com_accountkit_confirmation_code_agreement_app_privacy_policy, new Object[]{nextButtonText, TERMS_URL, DATA_URL, COOKIE_URL, loginModel.getPrivacyPolicy(), AccountKit.getApplicationName()})));
            }
        }
    }

    @SuppressLint({"StringFormatMatches"})
    private Spanned getConfirmationCodeAgreementText(CharSequence nextButtonText) {
        return Html.fromHtml(getString(C3354R.string.com_accountkit_confirmation_code_agreement, new Object[]{nextButtonText, TERMS_URL, DATA_URL, COOKIE_URL}));
    }

    /* access modifiers changed from: protected */
    public void setLoginFlowState(LoginFlowState loginFlowState2) {
        this.loginFlowState = loginFlowState2;
        getViewState().putInt(LOGIN_FLOW_STATE, loginFlowState2.ordinal());
    }
}
