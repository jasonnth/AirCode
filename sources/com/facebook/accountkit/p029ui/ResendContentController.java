package com.facebook.accountkit.p029ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.p000v4.content.LocalBroadcastManager;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.facebook.accountkit.C3354R;
import com.facebook.accountkit.internal.AccountKitController;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.internal.ExperimentationConfiguration;
import com.facebook.accountkit.internal.Feature;
import com.facebook.accountkit.p029ui.LoginFlowBroadcastReceiver.Event;
import com.facebook.accountkit.p029ui.StaticContentFragmentFactory.StaticContentFragment;
import com.facebook.accountkit.p029ui.TitleFragmentFactory.TitleFragment;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* renamed from: com.facebook.accountkit.ui.ResendContentController */
final class ResendContentController extends ContentControllerBase {
    /* access modifiers changed from: private */
    public static final LoginFlowState LOGIN_FLOW_STATE = LoginFlowState.RESEND;
    private BottomFragment bottomFragment;
    private StaticContentFragment centerFragment;
    private TitleFragment footerFragment;
    private TitleFragment headerFragment;
    private StaticContentFragment textFragment;
    private StaticContentFragment topFragment;

    /* renamed from: com.facebook.accountkit.ui.ResendContentController$BottomFragment */
    public static final class BottomFragment extends ContentFragment {
        private static final String FACEBOOK_NOTIFICATION_CHANNEL = (TAG + ".FACEBOOK_NOTIFICATION_CHANNEL");
        private static final String RESEND_TIME_KEY = (TAG + ".RESEND_TIME_KEY");
        private static final String TAG = BottomFragment.class.getSimpleName();
        private static final String VOICE_CALLBACK_NOTIFICATION_CHANNEL = (TAG + ".VOICE_CALLBACK_NOTIFICATION_CHANNEL");
        private CountDownTimer countDownTimer;
        /* access modifiers changed from: private */
        public OnCompleteListener onCompleteListener;
        private String phoneNumber;
        private TextView verifyPhoneNumberView;

        /* renamed from: com.facebook.accountkit.ui.ResendContentController$BottomFragment$OnCompleteListener */
        public interface OnCompleteListener {
            void onConfirmationCodeCallback(Context context);

            void onEdit(Context context);

            void onFacebookNotification(Context context);

            void onResend(Context context);
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

        /* access modifiers changed from: protected */
        public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(C3354R.layout.com_accountkit_fragment_resend_bottom, container, false);
        }

        /* access modifiers changed from: 0000 */
        public LoginFlowState getLoginFlowState() {
            return ResendContentController.LOGIN_FLOW_STATE;
        }

        /* access modifiers changed from: 0000 */
        public boolean isKeyboardFragment() {
            return false;
        }

        public boolean areFacebookNotificationsEnabled() {
            return getViewState().getBoolean(FACEBOOK_NOTIFICATION_CHANNEL);
        }

        public boolean areVoiceCallbackNotificationsEnabled() {
            return getViewState().getBoolean(VOICE_CALLBACK_NOTIFICATION_CHANNEL);
        }

        public void setNotificationChannels(List<NotificationChannel> notificationChannels) {
            getViewState().putBoolean(FACEBOOK_NOTIFICATION_CHANNEL, notificationChannels.contains(NotificationChannel.FACEBOOK));
            getViewState().putBoolean(VOICE_CALLBACK_NOTIFICATION_CHANNEL, notificationChannels.contains(NotificationChannel.VOICE_CALLBACK));
            updateNotificationViews();
        }

        public long getResendTime() {
            return getViewState().getLong(RESEND_TIME_KEY);
        }

        public void setResendTime(long resendTime) {
            getViewState().putLong(RESEND_TIME_KEY, resendTime);
            updateResendView();
        }

        /* access modifiers changed from: protected */
        public void onViewReadyWithState(View view, Bundle viewState) {
            int callbackDetailsTextResId;
            super.onViewReadyWithState(view, viewState);
            View resendButton = view.findViewById(C3354R.C3356id.com_accountkit_resend_button);
            this.verifyPhoneNumberView = (TextView) view.findViewById(C3354R.C3356id.com_accountkit_accountkit_verify_number);
            if (resendButton != null) {
                resendButton.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        Logger.logUIResendInteraction(Buttons.TRY_AGAIN.name());
                        if (BottomFragment.this.onCompleteListener != null) {
                            BottomFragment.this.onCompleteListener.onResend(v.getContext());
                        }
                    }
                });
            }
            Button facebookNotificationButton = (Button) view.findViewById(C3354R.C3356id.com_accountkit_send_in_fb_button);
            setButtonText(facebookNotificationButton, C3354R.string.com_accountkit_button_send_code_in_fb, C3354R.string.com_accountkit_button_send_code_in_fb_details);
            facebookNotificationButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    Logger.logUIResendInteraction(Buttons.FB_NOTIFICATION.name());
                    if (BottomFragment.this.onCompleteListener != null) {
                        BottomFragment.this.onCompleteListener.onFacebookNotification(v.getContext());
                    }
                }
            });
            Button callCodeButton = (Button) view.findViewById(C3354R.C3356id.com_accountkit_send_in_phone_call);
            ExperimentationConfiguration ec = AccountKitController.getExperimentationConfiguration();
            final boolean showAlternateButtonText = ec.getBooleanValue(Feature.CALLBACK_BUTTON_ALTERNATE_TEXT);
            if (!ec.exists() || !showAlternateButtonText) {
                callbackDetailsTextResId = C3354R.string.com_accountkit_button_send_code_in_call_details;
            } else {
                callbackDetailsTextResId = C3354R.string.com_accountkit_button_send_code_in_call_from_facebook_details;
            }
            setButtonText(callCodeButton, C3354R.string.com_accountkit_button_send_code_in_call, callbackDetailsTextResId);
            callCodeButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    String name;
                    if (showAlternateButtonText) {
                        name = Buttons.CONFIRMATION_CODE_CALLBACK.name();
                    } else {
                        name = Buttons.CONFIRMATION_CODE_CALLBACK_ALTERNATE.name();
                    }
                    Logger.logUIResendInteraction(name);
                    if (BottomFragment.this.onCompleteListener != null) {
                        BottomFragment.this.onCompleteListener.onConfirmationCodeCallback(v.getContext());
                    }
                }
            });
            updateViewStates();
        }

        private void setButtonText(Button button, int text, int subtext) {
            SpannableStringBuilder span = new SpannableStringBuilder(getString(text)).append("\n");
            span.setSpan(new TypefaceSpan("sans-serif-medium"), 0, span.length(), 33);
            int start = span.length();
            span.append(getString(subtext));
            span.setSpan(new TypefaceSpan("sans-serif-light"), start, span.length(), 33);
            span.setSpan(new ForegroundColorSpan(ViewUtility.getButtonTextColor(button.getContext(), getUIManager())), start, span.length(), 33);
            button.setText(span);
        }

        public void setPhoneNumber(String phoneNumber2) {
            this.phoneNumber = phoneNumber2;
            updatePhoneNumberView();
        }

        public void setOnCompleteListener(OnCompleteListener onCompleteListener2) {
            this.onCompleteListener = onCompleteListener2;
        }

        public void onStart() {
            super.onStart();
            updateViewStates();
        }

        private void updateViewStates() {
            updatePhoneNumberView();
            updateNotificationViews();
            updateResendView();
        }

        private void updatePhoneNumberView() {
            if (isAdded() && this.phoneNumber != null) {
                SpannableString ss = new SpannableString(getString(C3354R.string.com_accountkit_code_sent_to, new Object[]{this.phoneNumber}));
                ClickableSpan clickSpan = new ClickableSpan() {
                    public void onClick(View widget) {
                        Logger.logUIResendInteraction(Buttons.EDIT_NUMBER.name());
                        if (BottomFragment.this.onCompleteListener != null) {
                            BottomFragment.this.onCompleteListener.onEdit(widget.getContext());
                        }
                    }

                    public void updateDrawState(TextPaint ds) {
                        super.updateDrawState(ds);
                        ds.setColor(ViewUtility.getButtonColor(BottomFragment.this.getActivity(), BottomFragment.this.getUIManager()));
                        ds.setUnderlineText(false);
                    }
                };
                int start = ss.toString().indexOf(this.phoneNumber);
                ss.setSpan(clickSpan, start, start + this.phoneNumber.length(), 33);
                this.verifyPhoneNumberView.setText(ss);
                this.verifyPhoneNumberView.setMovementMethod(LinkMovementMethod.getInstance());
            }
        }

        private void updateNotificationViews() {
            int i;
            int i2 = 8;
            View view = getView();
            if (view != null) {
                view.findViewById(C3354R.C3356id.com_accountkit_send_in_fb_button).setVisibility(areFacebookNotificationsEnabled() ? 0 : 8);
                View findViewById = view.findViewById(C3354R.C3356id.com_accountkit_send_in_phone_call);
                if (areVoiceCallbackNotificationsEnabled()) {
                    i = 0;
                } else {
                    i = 8;
                }
                findViewById.setVisibility(i);
                View findViewById2 = view.findViewById(C3354R.C3356id.com_accountkit_other_ways_textview);
                if (areFacebookNotificationsEnabled() || areVoiceCallbackNotificationsEnabled()) {
                    i2 = 0;
                }
                findViewById2.setVisibility(i2);
            }
        }

        private void updateResendView() {
            View view = getView();
            if (view != null) {
                View resendView = view.findViewById(C3354R.C3356id.com_accountkit_resend_button);
                if (resendView != null) {
                    final Button resendButton = (Button) resendView;
                    long waitTimeMillis = getResendTime() - System.currentTimeMillis();
                    if (waitTimeMillis < 0) {
                        resendButton.setText(C3354R.string.com_accountkit_button_resend_sms);
                        resendButton.setEnabled(true);
                        return;
                    }
                    resendButton.setEnabled(false);
                    this.countDownTimer = new CountDownTimer(waitTimeMillis, TimeUnit.SECONDS.toMillis(1)) {
                        public void onTick(long millisUntilFinished) {
                            if (BottomFragment.this.isAdded()) {
                                resendButton.setText(BottomFragment.this.getString(C3354R.string.com_accountkit_button_resend_code_in, new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished))}));
                            }
                        }

                        public void onFinish() {
                            resendButton.setText(C3354R.string.com_accountkit_button_resend_sms);
                            resendButton.setEnabled(true);
                        }
                    };
                    this.countDownTimer.start();
                }
            }
        }

        public void onPause() {
            super.onPause();
            if (this.countDownTimer != null) {
                this.countDownTimer.cancel();
            }
        }
    }

    /* renamed from: com.facebook.accountkit.ui.ResendContentController$HeaderFragment */
    public static final class HeaderFragment extends TitleFragment {
        public static HeaderFragment create(UIManager uiManager, int titleResourceId, String... args) {
            HeaderFragment titleFragment = new HeaderFragment();
            titleFragment.getViewState().putParcelable(ViewStateFragment.UI_MANAGER_KEY, uiManager);
            titleFragment.setTitleResourceId(titleResourceId, args);
            return titleFragment;
        }

        /* access modifiers changed from: protected */
        public void onViewReadyWithState(View view, Bundle viewState) {
            super.onViewReadyWithState(view, viewState);
            this.titleView.setGravity(16);
        }
    }

    ResendContentController(AccountKitConfiguration configuration) {
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
            this.bottomFragment.setOnCompleteListener(new OnCompleteListener() {
                public void onEdit(Context context) {
                    LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.PHONE_RESEND));
                }

                public void onResend(Context context) {
                    LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.PHONE_RESEND));
                }

                public void onFacebookNotification(Context context) {
                    LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.PHONE_RESEND_FACEBOOK_NOTIFICATION));
                }

                public void onConfirmationCodeCallback(Context context) {
                    LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.PHONE_RESEND_VOICE_CALL_NOTIFICATION));
                }
            });
        }
    }

    public ContentFragment getCenterFragment() {
        if (this.centerFragment == null) {
            setCenterFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState()));
        }
        return this.centerFragment;
    }

    public void setCenterFragment(ContentFragment fragment) {
        if (fragment instanceof StaticContentFragment) {
            this.centerFragment = (StaticContentFragment) fragment;
        }
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
            setHeaderFragment(HeaderFragment.create(this.configuration.getUIManager(), C3354R.string.com_accountkit_resend_title, new String[0]));
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
            this.textFragment = StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState());
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
            setTopFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState()));
        }
        return this.topFragment;
    }

    public void setTopFragment(ContentFragment fragment) {
        if (fragment instanceof StaticContentFragment) {
            this.topFragment = (StaticContentFragment) fragment;
        }
    }

    /* access modifiers changed from: protected */
    public void logImpression() {
        Logger.logUIResend(true);
    }

    public void setPhoneNumber(String phoneNumber) {
        if (this.bottomFragment != null) {
            this.bottomFragment.setPhoneNumber(phoneNumber);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setNotificationChannels(List<NotificationChannel> notificationChannels) {
        if (this.bottomFragment != null) {
            this.bottomFragment.setNotificationChannels(notificationChannels);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setResendTime(long resendTime) {
        if (this.bottomFragment != null) {
            this.bottomFragment.setResendTime(resendTime);
        }
    }
}
