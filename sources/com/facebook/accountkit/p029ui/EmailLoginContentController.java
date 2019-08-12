package com.facebook.accountkit.p029ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.C3354R;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.internal.Utility;
import com.facebook.accountkit.p029ui.LoginFlowBroadcastReceiver.Event;
import com.facebook.accountkit.p029ui.SkinManager.Skin;
import com.facebook.accountkit.p029ui.StaticContentFragmentFactory.StaticContentFragment;
import com.facebook.accountkit.p029ui.TextContentFragment.NextButtonTextProvider;
import com.facebook.accountkit.p029ui.TitleFragmentFactory.TitleFragment;
import java.util.List;

/* renamed from: com.facebook.accountkit.ui.EmailLoginContentController */
final class EmailLoginContentController extends ContentControllerBase implements ButtonContentController {
    /* access modifiers changed from: private */
    public static final ButtonType DEFAULT_BUTTON_TYPE = ButtonType.NEXT;
    /* access modifiers changed from: private */
    public static final LoginFlowState LOGIN_FLOW_STATE = LoginFlowState.EMAIL_INPUT;
    /* access modifiers changed from: private */
    public BottomFragment bottomFragment;
    private ButtonType buttonType = DEFAULT_BUTTON_TYPE;
    private StaticContentFragment centerFragment;
    private TitleFragment footerFragment;
    /* access modifiers changed from: private */
    public TitleFragment headerFragment;
    private OnCompleteListener onCompleteListener;
    /* access modifiers changed from: private */
    public TextFragment textFragment;
    /* access modifiers changed from: private */
    public TopFragment topFragment;

    /* renamed from: com.facebook.accountkit.ui.EmailLoginContentController$BottomFragment */
    public static final class BottomFragment extends ContentFragment {
        private static final String RETRY_KEY = "retry";
        private Button nextButton;
        private boolean nextButtonEnabled;
        private ButtonType nextButtonType = EmailLoginContentController.DEFAULT_BUTTON_TYPE;
        /* access modifiers changed from: private */
        public OnCompleteListener onCompleteListener;

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

        /* access modifiers changed from: 0000 */
        public LoginFlowState getLoginFlowState() {
            return EmailLoginContentController.LOGIN_FLOW_STATE;
        }

        /* access modifiers changed from: 0000 */
        public boolean isKeyboardFragment() {
            return true;
        }

        /* access modifiers changed from: protected */
        public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(C3354R.layout.com_accountkit_fragment_email_login_bottom, container, false);
            UIManager uiManager = getUIManager();
            if (!(uiManager instanceof SkinManager) || ((SkinManager) uiManager).getSkin() != Skin.CONTEMPORARY) {
                return view;
            }
            View btn = view.findViewById(C3354R.C3356id.com_accountkit_next_button);
            ((ViewGroup) view).removeView(btn);
            View view2 = btn;
            view2.setLayoutParams(new LayoutParams(-1, -2));
            return view2;
        }

        /* access modifiers changed from: protected */
        public void onViewReadyWithState(View view, Bundle viewState) {
            super.onViewReadyWithState(view, viewState);
            this.nextButton = (Button) view.findViewById(C3354R.C3356id.com_accountkit_next_button);
            if (this.nextButton != null) {
                this.nextButton.setEnabled(this.nextButtonEnabled);
                this.nextButton.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        if (BottomFragment.this.onCompleteListener != null) {
                            BottomFragment.this.onCompleteListener.onNext(v.getContext(), Buttons.EMAIL_LOGIN_NEXT.name());
                        }
                    }
                });
            }
            updateButtonText();
        }

        public void setNextButtonEnabled(boolean enabled) {
            this.nextButtonEnabled = enabled;
            if (this.nextButton != null) {
                this.nextButton.setEnabled(enabled);
            }
        }

        public void setNextButtonType(ButtonType buttonType) {
            this.nextButtonType = buttonType;
            if (this.nextButton != null) {
                this.nextButton.setText(buttonType.getValue());
            }
        }

        public int getNextButtonTextId() {
            if (getRetry()) {
                return C3354R.string.com_accountkit_resend_email_text;
            }
            return this.nextButtonType.getValue();
        }

        public void setOnCompleteListener(OnCompleteListener onCompleteListener2) {
            this.onCompleteListener = onCompleteListener2;
        }

        public boolean getRetry() {
            return getViewState().getBoolean("retry", false);
        }

        public void setRetry(boolean retry) {
            getViewState().putBoolean("retry", retry);
            updateButtonText();
        }

        private void updateButtonText() {
            if (this.nextButton != null) {
                this.nextButton.setText(getNextButtonTextId());
            }
        }
    }

    /* renamed from: com.facebook.accountkit.ui.EmailLoginContentController$EmailSourceAppSupplied */
    enum EmailSourceAppSupplied {
        NO_APP_SUPPLIED_EMAIL,
        APP_SUPPLIED_EMAIL_CHANGED,
        APP_SUPPLIED_EMAIL_USED
    }

    /* renamed from: com.facebook.accountkit.ui.EmailLoginContentController$EmailSourceSelected */
    enum EmailSourceSelected {
        NO_SELECTABLE_EMAILS,
        SELECTED_CHANGED,
        SELECTED_NOT_USED,
        SELECTED_USED
    }

    /* renamed from: com.facebook.accountkit.ui.EmailLoginContentController$OnCompleteListener */
    public interface OnCompleteListener {
        void onNext(Context context, String str);
    }

    /* renamed from: com.facebook.accountkit.ui.EmailLoginContentController$TextFragment */
    public static final class TextFragment extends TextContentFragment {
        private static final String ACCOUNT_KIT_URL = "https://www.accountkit.com/faq";

        public /* bridge */ /* synthetic */ int getContentPaddingBottom() {
            return super.getContentPaddingBottom();
        }

        public /* bridge */ /* synthetic */ int getContentPaddingTop() {
            return super.getContentPaddingTop();
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

        public /* bridge */ /* synthetic */ void onStart() {
            super.onStart();
        }

        public /* bridge */ /* synthetic */ void setContentPaddingBottom(int i) {
            super.setContentPaddingBottom(i);
        }

        public /* bridge */ /* synthetic */ void setContentPaddingTop(int i) {
            super.setContentPaddingTop(i);
        }

        public /* bridge */ /* synthetic */ void setNextButtonTextProvider(NextButtonTextProvider nextButtonTextProvider) {
            super.setNextButtonTextProvider(nextButtonTextProvider);
        }

        /* access modifiers changed from: 0000 */
        public LoginFlowState getLoginFlowState() {
            return EmailLoginContentController.LOGIN_FLOW_STATE;
        }

        /* access modifiers changed from: 0000 */
        public boolean isKeyboardFragment() {
            return false;
        }

        /* access modifiers changed from: protected */
        public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(C3354R.layout.com_accountkit_fragment_email_login_text, container, false);
        }

        /* access modifiers changed from: protected */
        public Spanned getText(String nextButtonText) {
            return Html.fromHtml(getString(C3354R.string.com_accountkit_email_login_text, new Object[]{nextButtonText, AccountKit.getApplicationName(), ACCOUNT_KIT_URL}));
        }
    }

    /* renamed from: com.facebook.accountkit.ui.EmailLoginContentController$TopFragment */
    public static final class TopFragment extends ContentFragment {
        private static final String APP_SUPPLIED_EMAIL_KEY = "appSuppliedEmail";
        private static final String SELECTED_EMAIL_KEY = "selectedEmail";
        /* access modifiers changed from: private */
        public AutoCompleteTextView emailView;
        /* access modifiers changed from: private */
        public OnCompleteListener onCompleteListener;
        /* access modifiers changed from: private */
        public OnEmailChangedListener onEmailChangedListener;

        /* renamed from: com.facebook.accountkit.ui.EmailLoginContentController$TopFragment$OnEmailChangedListener */
        public interface OnEmailChangedListener {
            void onEmailChanged();
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

        /* access modifiers changed from: 0000 */
        public LoginFlowState getLoginFlowState() {
            return EmailLoginContentController.LOGIN_FLOW_STATE;
        }

        /* access modifiers changed from: 0000 */
        public boolean isKeyboardFragment() {
            return false;
        }

        /* access modifiers changed from: protected */
        public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(C3354R.layout.com_accountkit_fragment_email_login_top, container, false);
        }

        /* access modifiers changed from: protected */
        public void onViewReadyWithState(View view, Bundle viewState) {
            super.onViewReadyWithState(view, viewState);
            this.emailView = (AutoCompleteTextView) view.findViewById(C3354R.C3356id.com_accountkit_email);
            if (this.emailView != null) {
                this.emailView.addTextChangedListener(new TextWatcher() {
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    public void afterTextChanged(Editable s) {
                        if (TopFragment.this.onEmailChangedListener != null) {
                            TopFragment.this.onEmailChangedListener.onEmailChanged();
                        }
                    }
                });
                this.emailView.setOnEditorActionListener(new OnEditorActionListener() {
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId != 5 || Utility.isNullOrEmpty(TopFragment.this.getEmail())) {
                            return false;
                        }
                        if (TopFragment.this.onCompleteListener != null) {
                            TopFragment.this.onCompleteListener.onNext(v.getContext(), Buttons.EMAIL_LOGIN_NEXT_KEYBOARD.name());
                        }
                        return true;
                    }
                });
                this.emailView.setInputType(33);
                Activity activity = getActivity();
                List<String> deviceEmails = Utility.getDeviceEmailsIfAvailable(activity.getApplicationContext());
                if (deviceEmails != null) {
                    this.emailView.setAdapter(new ArrayAdapter(activity, 17367050, deviceEmails));
                    this.emailView.setOnItemClickListener(new OnItemClickListener() {
                        public void onItemClick(AdapterView<?> adapterView, View arg1, int pos, long id) {
                            TopFragment.this.setSelectedEmail(TopFragment.this.emailView.getText().toString());
                        }
                    });
                }
                String appSuppliedEmail = getAppSuppliedEmail();
                if (!Utility.isNullOrEmpty(appSuppliedEmail)) {
                    this.emailView.setText(appSuppliedEmail);
                    this.emailView.setSelection(appSuppliedEmail.length());
                }
            }
        }

        public String getAppSuppliedEmail() {
            return getViewState().getString(APP_SUPPLIED_EMAIL_KEY);
        }

        public void setAppSuppliedEmail(String appSuppliedEmail) {
            getViewState().putString(APP_SUPPLIED_EMAIL_KEY, appSuppliedEmail);
        }

        public String getEmail() {
            if (this.emailView == null) {
                return null;
            }
            return this.emailView.getText().toString();
        }

        public void setOnCompleteListener(OnCompleteListener onCompleteListener2) {
            this.onCompleteListener = onCompleteListener2;
        }

        public void setOnEmailChangedListener(OnEmailChangedListener onEmailChangedListener2) {
            this.onEmailChangedListener = onEmailChangedListener2;
        }

        public String getSelectedEmail() {
            return getViewState().getString(SELECTED_EMAIL_KEY);
        }

        public void setSelectedEmail(String selectedEmail) {
            getViewState().putString(SELECTED_EMAIL_KEY, selectedEmail);
        }

        public void setShowErrorIcon(boolean showErrorIcon) {
            if (this.emailView != null) {
                if (showErrorIcon) {
                    this.emailView.setCompoundDrawablesWithIntrinsicBounds(0, 0, C3354R.C3355drawable.com_accountkit_error_exclamation, 0);
                } else {
                    this.emailView.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                }
            }
        }
    }

    EmailLoginContentController(AccountKitConfiguration configuration) {
        super(configuration);
    }

    public ContentFragment getBottomFragment() {
        if (this.bottomFragment == null) {
            setBottomFragment(new BottomFragment());
        }
        return this.bottomFragment;
    }

    public void setBottomFragment(ContentFragment fragment) {
        if (fragment instanceof BottomFragment) {
            this.bottomFragment = (BottomFragment) fragment;
            this.bottomFragment.getViewState().putParcelable(ViewStateFragment.UI_MANAGER_KEY, this.configuration.getUIManager());
            this.bottomFragment.setOnCompleteListener(getOnCompleteListener());
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
            setCenterFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState(), C3354R.layout.com_accountkit_fragment_email_login_center));
        }
        return this.centerFragment;
    }

    public void setCenterFragment(ContentFragment fragment) {
        if (fragment instanceof StaticContentFragment) {
            this.centerFragment = (StaticContentFragment) fragment;
        }
    }

    public View getFocusView() {
        if (this.topFragment == null) {
            return null;
        }
        return this.topFragment.emailView;
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
            this.headerFragment = TitleFragmentFactory.create(this.configuration.getUIManager(), C3354R.string.com_accountkit_email_login_title, new String[0]);
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
            setTextFragment(new TextFragment());
        }
        return this.textFragment;
    }

    public void setTextFragment(ContentFragment fragment) {
        if (fragment instanceof TextFragment) {
            this.textFragment = (TextFragment) fragment;
            this.textFragment.getViewState().putParcelable(ViewStateFragment.UI_MANAGER_KEY, this.configuration.getUIManager());
            this.textFragment.setNextButtonTextProvider(new NextButtonTextProvider() {
                public String getNextButtonText() {
                    if (EmailLoginContentController.this.bottomFragment == null) {
                        return null;
                    }
                    return EmailLoginContentController.this.textFragment.getResources().getText(EmailLoginContentController.this.bottomFragment.getNextButtonTextId()).toString();
                }
            });
        }
    }

    public ContentFragment getTopFragment() {
        if (this.topFragment == null) {
            setTopFragment(new TopFragment());
        }
        return this.topFragment;
    }

    public void setTopFragment(ContentFragment fragment) {
        if (fragment instanceof TopFragment) {
            this.topFragment = (TopFragment) fragment;
            this.topFragment.getViewState().putParcelable(ViewStateFragment.UI_MANAGER_KEY, this.configuration.getUIManager());
            this.topFragment.setOnEmailChangedListener(new OnEmailChangedListener() {
                public void onEmailChanged() {
                    EmailLoginContentController.this.updateNextButton();
                }
            });
            this.topFragment.setOnCompleteListener(getOnCompleteListener());
            if (!(this.configuration == null || this.configuration.getInitialEmail() == null)) {
                this.topFragment.setAppSuppliedEmail(this.configuration.getInitialEmail());
            }
            updateNextButton();
        }
    }

    public boolean isTransient() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public void setRetry() {
        if (this.headerFragment != null) {
            this.headerFragment.setTitleResourceId(C3354R.string.com_accountkit_email_login_retry_title, new String[0]);
        }
        if (this.bottomFragment != null) {
            this.bottomFragment.setRetry(true);
        }
        if (this.textFragment != null) {
            this.textFragment.updateText();
        }
    }

    public void onResume(Activity activity) {
        super.onResume(activity);
        ViewUtility.showKeyboard(getFocusView());
    }

    /* access modifiers changed from: private */
    public void updateNextButton() {
        if (this.topFragment != null && this.bottomFragment != null) {
            this.bottomFragment.setNextButtonEnabled(!Utility.isNullOrEmpty(this.topFragment.getEmail()));
            this.bottomFragment.setNextButtonType(getButtonType());
        }
    }

    /* access modifiers changed from: protected */
    public void logImpression() {
        if (this.bottomFragment != null) {
            Logger.logUIEmailLoginShown(this.bottomFragment.getRetry());
        }
    }

    private OnCompleteListener getOnCompleteListener() {
        if (this.onCompleteListener == null) {
            this.onCompleteListener = new OnCompleteListener() {
                public void onNext(Context context, String buttonName) {
                    if (EmailLoginContentController.this.topFragment != null) {
                        String email = EmailLoginContentController.this.topFragment.getEmail();
                        if (email != null) {
                            String email2 = email.trim();
                            if (!Patterns.EMAIL_ADDRESS.matcher(email2).matches()) {
                                EmailLoginContentController.this.topFragment.setShowErrorIcon(true);
                                if (EmailLoginContentController.this.headerFragment != null) {
                                    EmailLoginContentController.this.headerFragment.setTitleResourceId(C3354R.string.com_accountkit_email_invalid, new String[0]);
                                    return;
                                }
                                return;
                            }
                            EmailLoginContentController.this.topFragment.setShowErrorIcon(false);
                            Logger.logUIEmailLoginInteraction(buttonName, EmailLoginContentController.getEmailAppSuppliedSource(EmailLoginContentController.this.topFragment.getAppSuppliedEmail(), email2).name(), EmailLoginContentController.getEmailSourceSelected(EmailLoginContentController.this.topFragment.getSelectedEmail(), email2, Utility.getDeviceEmailsIfAvailable(EmailLoginContentController.this.topFragment.getActivity().getApplicationContext())).name(), email2);
                            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.EMAIL_LOGIN_COMPLETE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EMAIL, email2));
                        }
                    }
                }
            };
        }
        return this.onCompleteListener;
    }

    static EmailSourceAppSupplied getEmailAppSuppliedSource(String appSuppliedEmail, String submittedEmail) {
        if (Utility.isNullOrEmpty(appSuppliedEmail)) {
            return EmailSourceAppSupplied.NO_APP_SUPPLIED_EMAIL;
        }
        if (appSuppliedEmail.equals(submittedEmail)) {
            return EmailSourceAppSupplied.APP_SUPPLIED_EMAIL_USED;
        }
        return EmailSourceAppSupplied.APP_SUPPLIED_EMAIL_CHANGED;
    }

    static EmailSourceSelected getEmailSourceSelected(String selectedEmail, String submittedEmail, List<String> availableEmails) {
        if (!Utility.isNullOrEmpty(selectedEmail)) {
            if (selectedEmail.equals(submittedEmail)) {
                return EmailSourceSelected.SELECTED_USED;
            }
            return EmailSourceSelected.SELECTED_CHANGED;
        } else if (availableEmails == null || availableEmails.isEmpty()) {
            return EmailSourceSelected.NO_SELECTABLE_EMAILS;
        } else {
            return EmailSourceSelected.SELECTED_NOT_USED;
        }
    }
}
