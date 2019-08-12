package com.facebook.accountkit.p029ui;

import android.content.ClipData.Item;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.content.LocalBroadcastManager;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.facebook.accountkit.C3354R;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.internal.Utility;
import com.facebook.accountkit.p029ui.LoginFlowBroadcastReceiver.Event;
import com.facebook.accountkit.p029ui.NotifyingEditText.PasteListener;
import com.facebook.accountkit.p029ui.SkinManager.Skin;
import com.facebook.accountkit.p029ui.StaticContentFragmentFactory.StaticContentFragment;

/* renamed from: com.facebook.accountkit.ui.ConfirmationCodeContentController */
final class ConfirmationCodeContentController extends ContentControllerBase implements ButtonContentController {
    /* access modifiers changed from: private */
    public static final LoginFlowState LOGIN_FLOW_STATE = LoginFlowState.CODE_INPUT;
    private static final String NUMERIC_REGEX = "[0-9]+";
    /* access modifiers changed from: private */
    public PrivacyPolicyFragment bottomFragment;
    private ButtonType buttonType;
    private StaticContentFragment centerFragment;
    com.facebook.accountkit.p029ui.TitleFragmentFactory.TitleFragment footerFragment;
    TitleFragment headerFragment;
    private OnCompleteListener onCompleteListener;
    private StaticContentFragment textFragment;
    /* access modifiers changed from: private */
    public TopFragment topFragment;

    /* renamed from: com.facebook.accountkit.ui.ConfirmationCodeContentController$OnCompleteListener */
    private class OnCompleteListener implements OnCompleteListener, com.facebook.accountkit.p029ui.PrivacyPolicyFragment.OnCompleteListener {
        private OnCompleteListener() {
        }

        public void onNext(Context context, String buttonName) {
            if (ConfirmationCodeContentController.this.topFragment != null && ConfirmationCodeContentController.this.bottomFragment != null) {
                String confirmationCode = ConfirmationCodeContentController.this.topFragment.getConfirmationCode();
                Logger.logUIConfirmationCodeInteraction(buttonName, ConfirmationCodeContentController.this.topFragment.getDetectedConfirmationCode(), confirmationCode);
                LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.PHONE_CONFIRMATION_CODE_COMPLETE).putExtra(LoginFlowBroadcastReceiver.EXTRA_CONFIRMATION_CODE, confirmationCode));
            }
        }

        public void onRetry(Context context) {
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.PHONE_CONFIRMATION_CODE_RETRY));
        }

        public void onEdit(Context context) {
            LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.PHONE_RESEND));
        }
    }

    /* renamed from: com.facebook.accountkit.ui.ConfirmationCodeContentController$TitleFragment */
    public static final class TitleFragment extends com.facebook.accountkit.p029ui.TitleFragmentFactory.TitleFragment {
        private NotificationChannel notificationChannel;
        /* access modifiers changed from: private */
        public OnCompleteListener onCompleteListener;
        private PhoneNumber phoneNumber;
        private boolean retry = false;

        /* renamed from: com.facebook.accountkit.ui.ConfirmationCodeContentController$TitleFragment$OnCompleteListener */
        public interface OnCompleteListener {
            void onEdit(Context context);

            void onRetry(Context context);
        }

        public static TitleFragment create(UIManager uiManager, int titleResourceId, String... args) {
            TitleFragment titleFragment = new TitleFragment();
            titleFragment.getViewState().putParcelable(ViewStateFragment.UI_MANAGER_KEY, uiManager);
            titleFragment.setTitleResourceId(titleResourceId, args);
            return titleFragment;
        }

        public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(C3354R.layout.com_accountkit_fragment_title, container, false);
        }

        /* access modifiers changed from: 0000 */
        public void setPhoneNumber(PhoneNumber phoneNumber2) {
            this.phoneNumber = phoneNumber2;
            setPhoneNumberView();
        }

        /* access modifiers changed from: 0000 */
        public void setNotificationChannel(NotificationChannel notificationChannel2) {
            this.notificationChannel = notificationChannel2;
            setPhoneNumberView();
        }

        /* access modifiers changed from: 0000 */
        public void setRetry(boolean retry2) {
            this.retry = retry2;
            setPhoneNumberView();
        }

        /* access modifiers changed from: 0000 */
        public void setOnCompleteListener(OnCompleteListener onCompleteListener2) {
            this.onCompleteListener = onCompleteListener2;
        }

        /* access modifiers changed from: protected */
        public void onViewReadyWithState(View view, Bundle viewState) {
            super.onViewReadyWithState(view, viewState);
            setPhoneNumberView();
        }

        public void onResume() {
            super.onResume();
            setPhoneNumberView();
        }

        private void setPhoneNumberView() {
            if (isAdded() && this.notificationChannel != null) {
                switch (this.notificationChannel) {
                    case FACEBOOK:
                        setTitleResourceId(C3354R.string.com_accountkit_facebook_code_entry_title, new String[0]);
                        return;
                    case VOICE_CALLBACK:
                        setTitleResourceId(C3354R.string.com_accountkit_voice_call_code_entry_title, new String[0]);
                        return;
                    default:
                        if (this.retry) {
                            setTitleResourceId(C3354R.string.com_accountkit_verify_confirmation_code_title, new String[0]);
                            return;
                        } else if (this.phoneNumber != null) {
                            SpannableString span = new SpannableString(getString(C3354R.string.com_accountkit_enter_code_sent_to, new Object[]{this.phoneNumber.toString()}));
                            ClickableSpan clickSpan = new ClickableSpan() {
                                public void onClick(View widget) {
                                    Logger.logUIResendInteraction(Buttons.PHONE_NUMBER.name());
                                    if (TitleFragment.this.onCompleteListener != null) {
                                        TitleFragment.this.onCompleteListener.onEdit(widget.getContext());
                                    }
                                }

                                public void updateDrawState(TextPaint ds) {
                                    super.updateDrawState(ds);
                                    ds.setColor(ViewUtility.getButtonColor(TitleFragment.this.getActivity(), TitleFragment.this.getUIManager()));
                                    ds.setUnderlineText(false);
                                }
                            };
                            int start = span.toString().indexOf(this.phoneNumber.toString());
                            span.setSpan(clickSpan, start, start + this.phoneNumber.toString().length(), 33);
                            this.titleView.setText(span);
                            this.titleView.setMovementMethod(LinkMovementMethod.getInstance());
                            return;
                        } else {
                            return;
                        }
                }
            }
        }
    }

    /* renamed from: com.facebook.accountkit.ui.ConfirmationCodeContentController$TopFragment */
    public static final class TopFragment extends ContentFragment {
        private static final String DETECTED_CONFIRMATION_CODE_KEY = "detectedConfirmationCode";
        private static final String TEXT_UPDATED_KEY = "textUpdated";
        private EditText[] confirmationCodeViews;
        /* access modifiers changed from: private */
        public com.facebook.accountkit.p029ui.PrivacyPolicyFragment.OnCompleteListener onCompleteListener;
        /* access modifiers changed from: private */
        public OnConfirmationCodeChangedListener onConfirmationCodeChangedListener;

        /* renamed from: com.facebook.accountkit.ui.ConfirmationCodeContentController$TopFragment$OnConfirmationCodeChangedListener */
        interface OnConfirmationCodeChangedListener {
            void onConfirmationCodeChanged();
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
            return ConfirmationCodeContentController.LOGIN_FLOW_STATE;
        }

        /* access modifiers changed from: 0000 */
        public boolean isKeyboardFragment() {
            return true;
        }

        /* access modifiers changed from: protected */
        public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(C3354R.layout.com_accountkit_fragment_confirmation_code_top, container, false);
        }

        public View getFocusView() {
            EditText[] editTextArr;
            if (this.confirmationCodeViews == null) {
                return null;
            }
            for (EditText confirmationCodeView : this.confirmationCodeViews) {
                if (confirmationCodeView.getText().length() == 0) {
                    return confirmationCodeView;
                }
            }
            return null;
        }

        public void onResume() {
            super.onResume();
            ViewUtility.showKeyboard(getFocusView());
        }

        /* access modifiers changed from: protected */
        public void onViewReadyWithState(View view, Bundle viewState) {
            super.onViewReadyWithState(view, viewState);
            final EditText[] confirmationCodeViews2 = {(EditText) view.findViewById(C3354R.C3356id.com_accountkit_confirmation_code_1), (EditText) view.findViewById(C3354R.C3356id.com_accountkit_confirmation_code_2), (EditText) view.findViewById(C3354R.C3356id.com_accountkit_confirmation_code_3), (EditText) view.findViewById(C3354R.C3356id.com_accountkit_confirmation_code_4), (EditText) view.findViewById(C3354R.C3356id.com_accountkit_confirmation_code_5), (EditText) view.findViewById(C3354R.C3356id.com_accountkit_confirmation_code_6)};
            EditText editText = null;
            for (EditText confirmationCodeView : confirmationCodeViews2) {
                if (confirmationCodeView.getText().length() != 0) {
                    confirmationCodeView.clearFocus();
                } else if (editText == null) {
                    editText = confirmationCodeView;
                }
            }
            ViewUtility.showKeyboard(editText);
            this.confirmationCodeViews = confirmationCodeViews2;
            OnEditorActionListener onEditorActionListener = new OnEditorActionListener() {
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == 5 && TopFragment.this.isConfirmationCodeValid() && TopFragment.this.onCompleteListener != null) {
                        TopFragment.this.onCompleteListener.onNext(v.getContext(), Buttons.ENTER_CONFIRMATION_CODE_KEYBOARD.name());
                    }
                    return true;
                }
            };
            OnKeyListener onKeyListener = new OnKeyListener() {
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    EditText confirmationCodeView = (EditText) v;
                    if (keyCode >= 7 && keyCode <= 16 && event.getAction() == 0) {
                        confirmationCodeView.setText(Character.toString((char) event.getUnicodeChar()));
                        return true;
                    } else if (keyCode != 67 || event.getAction() != 0) {
                        return false;
                    } else {
                        if (confirmationCodeView.getText().length() == 0) {
                            EditText previous = TopFragment.this.focusOnPrevious(confirmationCodeView);
                            if (previous == null) {
                                return true;
                            }
                            previous.setText("");
                            return true;
                        }
                        confirmationCodeView.setText("");
                        return true;
                    }
                }
            };
            for (final EditText confirmationCodeView2 : confirmationCodeViews2) {
                confirmationCodeView2.setRawInputType(18);
                confirmationCodeView2.setOnEditorActionListener(onEditorActionListener);
                confirmationCodeView2.setOnKeyListener(onKeyListener);
                if (confirmationCodeView2 instanceof NotifyingEditText) {
                    NotifyingEditText notifyingEditText = (NotifyingEditText) confirmationCodeView2;
                    notifyingEditText.setOnSoftKeyListener(onKeyListener);
                    notifyingEditText.setPasteListener(new PasteListener() {
                        public void onTextPaste() {
                            char[] code = ConfirmationCodeContentController.getConfirmationCodeToPaste(TopFragment.this.getActivity());
                            if (code != null) {
                                for (int i = 0; i < code.length; i++) {
                                    confirmationCodeViews2[i].setText(String.valueOf(code[i]));
                                }
                            }
                        }
                    });
                }
                confirmationCodeView2.addTextChangedListener(new TextWatcher() {
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                    }

                    public void afterTextChanged(Editable s) {
                        if (!TopFragment.this.getTextUpdated()) {
                            TopFragment.this.setTextUpdated(true);
                            Logger.logUIConfirmationCodeInteraction(Buttons.CONFIRMATION_CODE_FIRST_DIGIT.name(), null);
                        }
                        if (s.length() == 1) {
                            TopFragment.this.focusOnNext(confirmationCodeView2);
                        }
                        if (TopFragment.this.onConfirmationCodeChangedListener != null) {
                            TopFragment.this.onConfirmationCodeChangedListener.onConfirmationCodeChanged();
                        }
                    }
                });
            }
            updateDetectedConfirmationCode();
        }

        public String getConfirmationCode() {
            if (this.confirmationCodeViews == null) {
                return null;
            }
            StringBuilder confirmationCode = new StringBuilder();
            for (EditText confirmationCodeView : this.confirmationCodeViews) {
                confirmationCode.append(confirmationCodeView.getText());
            }
            return confirmationCode.toString();
        }

        public String getDetectedConfirmationCode() {
            return getViewState().getString(DETECTED_CONFIRMATION_CODE_KEY);
        }

        public void setDetectedConfirmationCode(String detectedConfirmationCode) {
            getViewState().putString(DETECTED_CONFIRMATION_CODE_KEY, detectedConfirmationCode);
            updateDetectedConfirmationCode();
        }

        public void setOnCompleteListener(com.facebook.accountkit.p029ui.PrivacyPolicyFragment.OnCompleteListener onCompleteListener2) {
            this.onCompleteListener = onCompleteListener2;
        }

        public void setOnConfirmationCodeChangedListener(OnConfirmationCodeChangedListener onConfirmationCodeChangedListener2) {
            this.onConfirmationCodeChangedListener = onConfirmationCodeChangedListener2;
        }

        public void onRetry() {
            for (EditText confirmationCodeView : this.confirmationCodeViews) {
                confirmationCodeView.setText("");
            }
            if (this.confirmationCodeViews.length > 0) {
                this.confirmationCodeViews[0].requestFocus();
            }
        }

        /* access modifiers changed from: private */
        public boolean getTextUpdated() {
            return getViewState().getBoolean(TEXT_UPDATED_KEY, false);
        }

        /* access modifiers changed from: private */
        public void setTextUpdated(boolean textUpdated) {
            getViewState().putBoolean(TEXT_UPDATED_KEY, textUpdated);
        }

        private int getConfirmationCodeViewIndex(View view) {
            if (view != null) {
                int length = this.confirmationCodeViews.length;
                for (int i = 0; i < length; i++) {
                    if (this.confirmationCodeViews[i] == view) {
                        return i;
                    }
                }
            }
            return -1;
        }

        /* access modifiers changed from: private */
        public EditText focusOnNext(View currentView) {
            int confirmationCodeIndex = getConfirmationCodeViewIndex(currentView);
            if (confirmationCodeIndex < this.confirmationCodeViews.length - 1) {
                EditText nextView = this.confirmationCodeViews[confirmationCodeIndex + 1];
                nextView.requestFocus();
                return nextView;
            }
            this.confirmationCodeViews[this.confirmationCodeViews.length - 1].setSelection(1);
            return null;
        }

        /* access modifiers changed from: private */
        public EditText focusOnPrevious(View currentView) {
            int confirmationCodeIndex = getConfirmationCodeViewIndex(currentView);
            if (confirmationCodeIndex <= 0) {
                return null;
            }
            EditText previousView = this.confirmationCodeViews[confirmationCodeIndex - 1];
            previousView.requestFocus();
            return previousView;
        }

        public boolean isConfirmationCodeValid() {
            if (this.confirmationCodeViews == null) {
                return false;
            }
            for (EditText confirmationCodeView : this.confirmationCodeViews) {
                if (confirmationCodeView.getText().length() != 1) {
                    return false;
                }
            }
            return true;
        }

        private void updateDetectedConfirmationCode() {
            if (this.confirmationCodeViews != null) {
                String detectedConfirmationCode = getDetectedConfirmationCode();
                if (!Utility.isNullOrEmpty(detectedConfirmationCode)) {
                    int length = detectedConfirmationCode.length();
                    if (length == this.confirmationCodeViews.length) {
                        EditText[] editTextArr = this.confirmationCodeViews;
                        int length2 = editTextArr.length;
                        int i = 0;
                        while (i < length2) {
                            if (editTextArr[i].getText().length() == 0) {
                                i++;
                            } else {
                                return;
                            }
                        }
                        for (int i2 = 0; i2 < length; i2++) {
                            this.confirmationCodeViews[i2].setText(Character.toString(detectedConfirmationCode.charAt(i2)));
                        }
                        this.confirmationCodeViews[this.confirmationCodeViews.length - 1].setSelection(1);
                    }
                }
            }
        }
    }

    ConfirmationCodeContentController(AccountKitConfiguration configuration) {
        super(configuration);
        if (ViewUtility.isSkin(configuration.getUIManager(), Skin.CONTEMPORARY)) {
            this.buttonType = ButtonType.NEXT;
        } else {
            this.buttonType = ButtonType.CONTINUE;
        }
    }

    public ContentFragment getBottomFragment() {
        if (this.bottomFragment == null) {
            setBottomFragment(PrivacyPolicyFragment.create(this.configuration.getUIManager(), LOGIN_FLOW_STATE, getButtonType()));
        }
        return this.bottomFragment;
    }

    public void setBottomFragment(ContentFragment fragment) {
        if (fragment instanceof PrivacyPolicyFragment) {
            this.bottomFragment = (PrivacyPolicyFragment) fragment;
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
            setCenterFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState(), C3354R.layout.com_accountkit_fragment_confirmation_code_center));
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
        return this.topFragment.getFocusView();
    }

    public com.facebook.accountkit.p029ui.TitleFragmentFactory.TitleFragment getFooterFragment() {
        if (this.footerFragment == null) {
            setFooterFragment(TitleFragmentFactory.create(this.configuration.getUIManager()));
        }
        return this.footerFragment;
    }

    public void setFooterFragment(com.facebook.accountkit.p029ui.TitleFragmentFactory.TitleFragment fragment) {
        this.footerFragment = fragment;
    }

    public com.facebook.accountkit.p029ui.TitleFragmentFactory.TitleFragment getHeaderFragment() {
        if (this.headerFragment == null) {
            setHeaderFragment(TitleFragment.create(this.configuration.getUIManager(), C3354R.string.com_accountkit_confirmation_code_title, new String[0]));
        }
        return this.headerFragment;
    }

    public void setHeaderFragment(com.facebook.accountkit.p029ui.TitleFragmentFactory.TitleFragment fragment) {
        if (fragment instanceof TitleFragment) {
            this.headerFragment = (TitleFragment) fragment;
            this.headerFragment.setOnCompleteListener(getOnCompleteListener());
        }
    }

    public LoginFlowState getLoginFlowState() {
        return LoginFlowState.CODE_INPUT;
    }

    public ContentFragment getTextFragment() {
        if (this.textFragment == null) {
            setTextFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState()));
        }
        return this.textFragment;
    }

    public void setTextFragment(ContentFragment fragment) {
        if (fragment instanceof StaticContentFragment) {
            this.textFragment = (StaticContentFragment) fragment;
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
            this.topFragment.setOnConfirmationCodeChangedListener(new OnConfirmationCodeChangedListener() {
                public void onConfirmationCodeChanged() {
                    ConfirmationCodeContentController.this.updateNextButton();
                }
            });
            this.topFragment.setOnCompleteListener(getOnCompleteListener());
        }
    }

    public boolean isTransient() {
        return false;
    }

    /* access modifiers changed from: 0000 */
    public void setPhoneNumber(PhoneNumber phoneNumber) {
        if (this.headerFragment != null) {
            this.headerFragment.setPhoneNumber(phoneNumber);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setNotificationChannel(NotificationChannel notificationChannel) {
        if (this.headerFragment != null) {
            this.headerFragment.setNotificationChannel(notificationChannel);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setDetectedConfirmationCode(String detectedConfirmationCode) {
        if (this.topFragment != null) {
            this.topFragment.setDetectedConfirmationCode(detectedConfirmationCode);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setRetry(boolean retry) {
        if (this.headerFragment != null) {
            this.headerFragment.setRetry(retry);
        }
        if (this.bottomFragment != null) {
            this.bottomFragment.setRetry(retry);
        }
        if (retry && this.topFragment != null) {
            this.topFragment.onRetry();
        }
    }

    /* access modifiers changed from: private */
    public void updateNextButton() {
        if (this.topFragment != null && this.bottomFragment != null) {
            this.bottomFragment.setNextButtonEnabled(this.topFragment.isConfirmationCodeValid());
            this.bottomFragment.setNextButtonType(getButtonType());
        }
    }

    /* access modifiers changed from: protected */
    public void logImpression() {
        if (this.topFragment != null && this.bottomFragment != null) {
            Logger.logUIConfirmationCodeShown(this.bottomFragment.getRetry());
        }
    }

    private OnCompleteListener getOnCompleteListener() {
        if (this.onCompleteListener == null) {
            this.onCompleteListener = new OnCompleteListener();
        }
        return this.onCompleteListener;
    }

    /* access modifiers changed from: private */
    public static char[] getConfirmationCodeToPaste(Context context) {
        String pasteText = getCurrentPasteText(context);
        if (pasteText == null || pasteText.length() != 6 || !pasteText.matches(NUMERIC_REGEX)) {
            return null;
        }
        return pasteText.toCharArray();
    }

    private static String getCurrentPasteText(Context context) {
        if (context == null) {
            return null;
        }
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService("clipboard");
        if (!clipboard.hasPrimaryClip()) {
            return null;
        }
        Item item = clipboard.getPrimaryClip().getItemAt(0);
        if (item.getText() != null) {
            return item.getText().toString();
        }
        return null;
    }
}
