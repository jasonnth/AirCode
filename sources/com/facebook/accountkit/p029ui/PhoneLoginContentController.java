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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.facebook.accountkit.C3354R;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.internal.AccountKitController;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.internal.Utility;
import com.facebook.accountkit.p029ui.LoginFlowBroadcastReceiver.Event;
import com.facebook.accountkit.p029ui.PhoneCountryCodeAdapter.ValueData;
import com.facebook.accountkit.p029ui.SkinManager.Skin;
import com.facebook.accountkit.p029ui.StaticContentFragmentFactory.StaticContentFragment;
import com.facebook.accountkit.p029ui.TextContentFragment.NextButtonTextProvider;
import com.facebook.accountkit.p029ui.TitleFragmentFactory.TitleFragment;

/* renamed from: com.facebook.accountkit.ui.PhoneLoginContentController */
final class PhoneLoginContentController extends ContentControllerBase implements ButtonContentController {
    /* access modifiers changed from: private */
    public static final ButtonType DEFAULT_BUTTON_TYPE = ButtonType.NEXT;
    /* access modifiers changed from: private */
    public static final LoginFlowState LOGIN_FLOW_STATE = LoginFlowState.PHONE_NUMBER_INPUT;
    /* access modifiers changed from: private */
    public BottomFragment bottomFragment;
    private ButtonType buttonType = DEFAULT_BUTTON_TYPE;
    private StaticContentFragment centerFragment;
    private TitleFragment footerFragment;
    private TitleFragment headerFragment;
    private OnCompleteListener onCompleteListener;
    /* access modifiers changed from: private */
    public TextFragment textFragment;
    /* access modifiers changed from: private */
    public TopFragment topFragment;

    /* renamed from: com.facebook.accountkit.ui.PhoneLoginContentController$BottomFragment */
    public static final class BottomFragment extends ContentFragment {
        private static final String RETRY_KEY = "retry";
        private Button nextButton;
        private boolean nextButtonEnabled;
        private ButtonType nextButtonType = PhoneLoginContentController.DEFAULT_BUTTON_TYPE;
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
            return PhoneLoginContentController.LOGIN_FLOW_STATE;
        }

        /* access modifiers changed from: 0000 */
        public boolean isKeyboardFragment() {
            return true;
        }

        /* access modifiers changed from: protected */
        public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(C3354R.layout.com_accountkit_fragment_phone_login_bottom, container, false);
            if (!ViewUtility.isSkin(getUIManager(), Skin.CONTEMPORARY)) {
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
                            BottomFragment.this.onCompleteListener.onNext(v.getContext(), Buttons.PHONE_LOGIN_NEXT.name());
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
                return C3354R.string.com_accountkit_button_resend_sms;
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

    /* renamed from: com.facebook.accountkit.ui.PhoneLoginContentController$OnCompleteListener */
    interface OnCompleteListener {
        void onNext(Context context, String str);
    }

    /* renamed from: com.facebook.accountkit.ui.PhoneLoginContentController$PhoneNumberSource */
    enum PhoneNumberSource {
        UNKNOWN,
        APP_SUPPLIED_PHONE_NUMBER,
        APP_SUPPLIED_AND_DEVICE_PHONE_NUMBER,
        DEVICE_PHONE_NUMBER,
        DEVICE_PHONE_NUMBER_AND_APP_NUMBER_NOT_SUPPLIED,
        DEVICE_PHONE_NUMBER_NOT_SUPPLIED
    }

    /* renamed from: com.facebook.accountkit.ui.PhoneLoginContentController$TextFragment */
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
            return PhoneLoginContentController.LOGIN_FLOW_STATE;
        }

        /* access modifiers changed from: 0000 */
        public boolean isKeyboardFragment() {
            return false;
        }

        /* access modifiers changed from: protected */
        public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(C3354R.layout.com_accountkit_fragment_phone_login_text, container, false);
        }

        /* access modifiers changed from: protected */
        public Spanned getText(String nextButtonText) {
            return Html.fromHtml(getString(C3354R.string.com_accountkit_phone_login_text, new Object[]{nextButtonText, ACCOUNT_KIT_URL}));
        }
    }

    /* renamed from: com.facebook.accountkit.ui.PhoneLoginContentController$TopFragment */
    public static final class TopFragment extends ContentFragment {
        private static final String APP_SUPPLIED_PHONE_NUMBER_KEY = "appSuppliedPhoneNumber";
        private static final String DEFAULT_COUNTRY_CODE_NUMBER = "defaultCountryCodeNumber";
        private static final String DEVICE_PHONE_NUMBER_KEY = "devicePhoneNumber";
        private static final String INITIAL_COUNTRY_CODE_VALUE_KEY = "initialCountryCodeValue";
        private static final String LAST_PHONE_NUMBER = "lastPhoneNumber";
        private static final String READ_PHONE_STATE_ENABLED = "readPhoneStateEnabled";
        private static final String SMS_BLACKLIST_KEY = "smsBlacklist";
        private static final String SMS_WHITELIST_KEY = "smsWhitelist";
        /* access modifiers changed from: private */
        public CountryCodeSpinner countryCodeView;
        /* access modifiers changed from: private */
        public boolean isPhoneNumberValid;
        /* access modifiers changed from: private */
        public OnCompleteListener onCompleteListener;
        /* access modifiers changed from: private */
        public OnPhoneNumberChangedListener onPhoneNumberChangedListener;
        /* access modifiers changed from: private */
        public EditText phoneNumberView;

        /* renamed from: com.facebook.accountkit.ui.PhoneLoginContentController$TopFragment$OnPhoneNumberChangedListener */
        public interface OnPhoneNumberChangedListener {
            void onPhoneNumberChanged();
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
            return PhoneLoginContentController.LOGIN_FLOW_STATE;
        }

        /* access modifiers changed from: 0000 */
        public boolean isKeyboardFragment() {
            return false;
        }

        /* access modifiers changed from: protected */
        public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(C3354R.layout.com_accountkit_fragment_phone_login_top, container, false);
        }

        /* access modifiers changed from: protected */
        public void onViewReadyWithState(View view, Bundle viewState) {
            PhoneNumber phoneNumber;
            super.onViewReadyWithState(view, viewState);
            PhoneNumber appSuppliedPhoneNumber = getAppSuppliedPhoneNumber();
            PhoneNumber lastPhoneNumber = (PhoneNumber) getViewState().getParcelable(LAST_PHONE_NUMBER);
            final Activity activity = getActivity();
            if (activity != null && (activity instanceof AccountKitActivity)) {
                this.countryCodeView = (CountryCodeSpinner) view.findViewById(C3354R.C3356id.com_accountkit_country_code);
                this.phoneNumberView = (EditText) view.findViewById(C3354R.C3356id.com_accountkit_phone_number);
                if (this.countryCodeView != null) {
                    PhoneCountryCodeAdapter countryCodeAdapter = new PhoneCountryCodeAdapter(activity, getUIManager(), getSmsBlacklist(), getSmsWhitelist());
                    this.countryCodeView.setAdapter(countryCodeAdapter);
                    if (lastPhoneNumber != null) {
                        phoneNumber = lastPhoneNumber;
                    } else {
                        phoneNumber = appSuppliedPhoneNumber;
                    }
                    ValueData initialCountryCodeValue = countryCodeAdapter.getInitialValue(phoneNumber, getDefaultCountryCodeValue());
                    setInitialCountryCodeValue(initialCountryCodeValue);
                    this.countryCodeView.setSelection(initialCountryCodeValue.position);
                    this.countryCodeView.setOnSpinnerEventsListener(new OnSpinnerEventsListener() {
                        public void onSpinnerOpened() {
                            Logger.logUICountryCode(true, ((ValueData) TopFragment.this.countryCodeView.getSelectedItem()).countryCode);
                            ViewUtility.hideKeyboard(activity);
                        }

                        public void onSpinnerClosed() {
                            Logger.logUICountryCode(false, ((ValueData) TopFragment.this.countryCodeView.getSelectedItem()).countryCode);
                            TopFragment.this.getViewState().putParcelable(TopFragment.LAST_PHONE_NUMBER, TopFragment.this.getPhoneNumber());
                            ViewUtility.showKeyboard(TopFragment.this.phoneNumberView);
                        }
                    });
                    if (isReadPhoneStateEnabled() && appSuppliedPhoneNumber == null) {
                        setDevicePhoneNumber(Utility.readPhoneNumberIfAvailable(getActivity().getApplicationContext(), ((ValueData) this.countryCodeView.getSelectedItem()).countryCode));
                    }
                }
                if (this.phoneNumberView != null) {
                    this.phoneNumberView.addTextChangedListener(new TextWatcher() {
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }

                        public void afterTextChanged(Editable s) {
                            boolean isPhoneNumberValid = TopFragment.this.phoneNumberView.getText().length() != 0;
                            if (isPhoneNumberValid != TopFragment.this.isPhoneNumberValid) {
                                TopFragment.this.isPhoneNumberValid = isPhoneNumberValid;
                            }
                            if (TopFragment.this.onPhoneNumberChangedListener != null) {
                                TopFragment.this.onPhoneNumberChangedListener.onPhoneNumberChanged();
                            }
                            TopFragment.this.getViewState().putParcelable(TopFragment.LAST_PHONE_NUMBER, TopFragment.this.getPhoneNumber());
                        }
                    });
                    this.phoneNumberView.setOnEditorActionListener(new OnEditorActionListener() {
                        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                            if (actionId != 5 || !TopFragment.this.isPhoneNumberValid) {
                                return false;
                            }
                            if (TopFragment.this.onCompleteListener != null) {
                                TopFragment.this.onCompleteListener.onNext(v.getContext(), Buttons.PHONE_LOGIN_NEXT_KEYBOARD.name());
                            }
                            return true;
                        }
                    });
                    this.phoneNumberView.setRawInputType(18);
                    String devicePhoneNumber = getDevicePhoneNumber();
                    if (lastPhoneNumber != null) {
                        this.phoneNumberView.setText(lastPhoneNumber.getPhoneNumber());
                    } else if (appSuppliedPhoneNumber != null) {
                        this.phoneNumberView.setText(appSuppliedPhoneNumber.getPhoneNumber());
                    } else if (!Utility.isNullOrEmpty(devicePhoneNumber)) {
                        this.phoneNumberView.setText(devicePhoneNumber);
                    }
                    this.phoneNumberView.setSelection(this.phoneNumberView.getText().length());
                }
            }
        }

        public void setOnCompleteListener(OnCompleteListener onCompleteListener2) {
            this.onCompleteListener = onCompleteListener2;
        }

        public void setReadPhoneStateEnabled(boolean isReadPhoneStateEnabled) {
            getViewState().putBoolean(READ_PHONE_STATE_ENABLED, isReadPhoneStateEnabled);
        }

        public boolean isReadPhoneStateEnabled() {
            return getViewState().getBoolean(READ_PHONE_STATE_ENABLED);
        }

        public PhoneNumber getAppSuppliedPhoneNumber() {
            return (PhoneNumber) getViewState().getParcelable(APP_SUPPLIED_PHONE_NUMBER_KEY);
        }

        /* access modifiers changed from: private */
        public void setAppSuppliedPhoneNumber(PhoneNumber appSuppliedPhoneNumber) {
            getViewState().putParcelable(APP_SUPPLIED_PHONE_NUMBER_KEY, appSuppliedPhoneNumber);
        }

        public String getDefaultCountryCodeValue() {
            return getViewState().getString(DEFAULT_COUNTRY_CODE_NUMBER);
        }

        /* access modifiers changed from: private */
        public void setDefaultCountryCodeValue(String defaultCountryCodeValue) {
            getViewState().putString(DEFAULT_COUNTRY_CODE_NUMBER, defaultCountryCodeValue);
        }

        public String[] getSmsBlacklist() {
            return getViewState().getStringArray(SMS_BLACKLIST_KEY);
        }

        /* access modifiers changed from: private */
        public void setSmsBlacklist(String[] smsBlacklist) {
            getViewState().putStringArray(SMS_BLACKLIST_KEY, smsBlacklist);
        }

        public String[] getSmsWhitelist() {
            return getViewState().getStringArray(SMS_WHITELIST_KEY);
        }

        /* access modifiers changed from: private */
        public void setSmsWhitelist(String[] smsWhitelist) {
            getViewState().putStringArray(SMS_WHITELIST_KEY, smsWhitelist);
        }

        public String getDevicePhoneNumber() {
            return getViewState().getString(DEVICE_PHONE_NUMBER_KEY);
        }

        private void setDevicePhoneNumber(String devicePhoneNumber) {
            getViewState().putString(DEVICE_PHONE_NUMBER_KEY, devicePhoneNumber);
        }

        public ValueData getInitialCountryCodeValue() {
            return (ValueData) getViewState().getParcelable(INITIAL_COUNTRY_CODE_VALUE_KEY);
        }

        private void setInitialCountryCodeValue(ValueData initialCountryCodeValue) {
            getViewState().putParcelable(INITIAL_COUNTRY_CODE_VALUE_KEY, initialCountryCodeValue);
        }

        public PhoneNumber getPhoneNumber() {
            try {
                ValueData countryCodeValue = (ValueData) this.countryCodeView.getSelectedItem();
                return new PhoneNumber(countryCodeValue.countryCode, this.phoneNumberView.getText().toString(), countryCodeValue.countryCodeSource);
            } catch (IllegalArgumentException e) {
                return null;
            }
        }

        public boolean isPhoneNumberValid() {
            return this.isPhoneNumberValid;
        }

        public void setOnPhoneNumberChangedListener(OnPhoneNumberChangedListener onPhoneNumberChangedListener2) {
            this.onPhoneNumberChangedListener = onPhoneNumberChangedListener2;
        }
    }

    PhoneLoginContentController(AccountKitConfiguration configuration) {
        super(configuration);
        AccountKitController.initializeLogin();
    }

    public BottomFragment getBottomFragment() {
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
            setCenterFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState(), C3354R.layout.com_accountkit_fragment_phone_login_center));
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
        return this.topFragment.phoneNumberView;
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
            setHeaderFragment(TitleFragmentFactory.create(this.configuration.getUIManager(), C3354R.string.com_accountkit_phone_login_title, new String[0]));
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
                    if (PhoneLoginContentController.this.bottomFragment == null) {
                        return null;
                    }
                    return PhoneLoginContentController.this.textFragment.getResources().getText(PhoneLoginContentController.this.bottomFragment.getNextButtonTextId()).toString();
                }
            });
        }
    }

    public TopFragment getTopFragment() {
        if (this.topFragment == null) {
            setTopFragment(new TopFragment());
        }
        return this.topFragment;
    }

    public void setTopFragment(ContentFragment fragment) {
        if (fragment instanceof TopFragment) {
            this.topFragment = (TopFragment) fragment;
            this.topFragment.getViewState().putParcelable(ViewStateFragment.UI_MANAGER_KEY, this.configuration.getUIManager());
            this.topFragment.setOnPhoneNumberChangedListener(new OnPhoneNumberChangedListener() {
                public void onPhoneNumberChanged() {
                    PhoneLoginContentController.this.updateNextButton();
                }
            });
            this.topFragment.setOnCompleteListener(getOnCompleteListener());
            if (this.configuration != null) {
                if (this.configuration.getInitialPhoneNumber() != null) {
                    this.topFragment.setAppSuppliedPhoneNumber(this.configuration.getInitialPhoneNumber());
                }
                if (this.configuration.getDefaultCountryCode() != null) {
                    this.topFragment.setDefaultCountryCodeValue(this.configuration.getDefaultCountryCode());
                }
                if (this.configuration.getSmsBlacklist() != null) {
                    this.topFragment.setSmsBlacklist(this.configuration.getSmsBlacklist());
                }
                if (this.configuration.getSmsWhitelist() != null) {
                    this.topFragment.setSmsWhitelist(this.configuration.getSmsWhitelist());
                }
                this.topFragment.setReadPhoneStateEnabled(this.configuration.isReadPhoneStateEnabled());
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
            this.headerFragment.setTitleResourceId(C3354R.string.com_accountkit_phone_login_retry_title, new String[0]);
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

    /* access modifiers changed from: protected */
    public void logImpression() {
        String str = null;
        if (this.topFragment != null && this.bottomFragment != null) {
            ValueData initialCountryCodeValue = this.topFragment.getInitialCountryCodeValue();
            String str2 = initialCountryCodeValue == null ? null : initialCountryCodeValue.countryCode;
            if (initialCountryCodeValue != null) {
                str = initialCountryCodeValue.countryCodeSource;
            }
            Logger.logUIPhoneLoginShown(str2, str, this.bottomFragment.getRetry());
        }
    }

    private OnCompleteListener getOnCompleteListener() {
        if (this.onCompleteListener == null) {
            this.onCompleteListener = new OnCompleteListener() {
                public void onNext(Context context, String buttonName) {
                    if (PhoneLoginContentController.this.topFragment != null && PhoneLoginContentController.this.bottomFragment != null) {
                        PhoneNumber phoneNumber = PhoneLoginContentController.this.topFragment.getPhoneNumber();
                        if (phoneNumber != null) {
                            Logger.logUIPhoneLoginInteraction(buttonName, PhoneLoginContentController.getPhoneNumberSource(phoneNumber, PhoneLoginContentController.this.topFragment.getAppSuppliedPhoneNumber(), PhoneLoginContentController.this.topFragment.getDevicePhoneNumber()).name(), phoneNumber);
                            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.PHONE_LOGIN_COMPLETE).putExtra(LoginFlowBroadcastReceiver.EXTRA_PHONE_NUMBER, phoneNumber));
                        }
                    }
                }
            };
        }
        return this.onCompleteListener;
    }

    /* access modifiers changed from: private */
    public void updateNextButton() {
        if (this.topFragment != null && this.bottomFragment != null) {
            this.bottomFragment.setNextButtonEnabled(this.topFragment.isPhoneNumberValid());
            this.bottomFragment.setNextButtonType(getButtonType());
        }
    }

    static PhoneNumberSource getPhoneNumberSource(PhoneNumber submittedPhoneNumber, PhoneNumber appSuppliedPhoneNumber, String devicePhoneNumber) {
        if (submittedPhoneNumber == null) {
            return PhoneNumberSource.UNKNOWN;
        }
        if (!Utility.isNullOrEmpty(devicePhoneNumber)) {
            if (appSuppliedPhoneNumber != null && devicePhoneNumber.equals(appSuppliedPhoneNumber.getRawPhoneNumber()) && devicePhoneNumber.equals(submittedPhoneNumber.getRawPhoneNumber())) {
                return PhoneNumberSource.APP_SUPPLIED_AND_DEVICE_PHONE_NUMBER;
            }
            if (devicePhoneNumber.equals(submittedPhoneNumber.getRawPhoneNumber())) {
                return PhoneNumberSource.DEVICE_PHONE_NUMBER;
            }
        }
        if (appSuppliedPhoneNumber != null && appSuppliedPhoneNumber.equals(submittedPhoneNumber)) {
            return PhoneNumberSource.APP_SUPPLIED_PHONE_NUMBER;
        }
        if (devicePhoneNumber == null && appSuppliedPhoneNumber == null) {
            return PhoneNumberSource.DEVICE_PHONE_NUMBER_AND_APP_NUMBER_NOT_SUPPLIED;
        }
        return PhoneNumberSource.DEVICE_PHONE_NUMBER_NOT_SUPPLIED;
    }
}
