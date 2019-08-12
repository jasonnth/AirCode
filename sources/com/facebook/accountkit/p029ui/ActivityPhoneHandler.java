package com.facebook.accountkit.p029ui;

import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitException;
import com.facebook.accountkit.LoginResult;
import com.facebook.accountkit.PhoneLoginModel;
import com.facebook.accountkit.PhoneLoginTracker;
import com.facebook.accountkit.PhoneNumber;
import com.facebook.accountkit.internal.AccountKitController;
import com.facebook.accountkit.internal.Utility;

/* renamed from: com.facebook.accountkit.ui.ActivityPhoneHandler */
final class ActivityPhoneHandler extends ActivityHandler {
    public static final Creator<ActivityPhoneHandler> CREATOR = new Creator<ActivityPhoneHandler>() {
        public ActivityPhoneHandler createFromParcel(Parcel source) {
            return new ActivityPhoneHandler(source);
        }

        public ActivityPhoneHandler[] newArray(int size) {
            return new ActivityPhoneHandler[size];
        }
    };
    /* access modifiers changed from: private */
    public SmsTracker smsTracker;

    ActivityPhoneHandler(AccountKitConfiguration configuration) {
        super(configuration);
    }

    public PhoneLoginTracker getLoginTracker(final AccountKitActivity activity) {
        if (getPhoneTracker() == null) {
            this.tracker = new PhoneLoginTracker() {
                /* access modifiers changed from: protected */
                public void onStarted(PhoneLoginModel loginModel) {
                    ContentController contentController = activity.getContentController();
                    if ((contentController instanceof SendingCodeContentController) || (contentController instanceof VerifyingCodeContentController)) {
                        if (loginModel.getNotificationChannel() == NotificationChannel.SMS) {
                            ActivityPhoneHandler.this.startSmsTrackerIfPossible(activity);
                        }
                        if (contentController instanceof SendingCodeContentController) {
                            activity.pushState(LoginFlowState.SENT_CODE, null);
                        } else {
                            activity.popBackStack(LoginFlowState.CODE_INPUT, new OnPopListener() {
                                public void onContentPopped() {
                                    ContentController contentController = activity.getContentController();
                                    if (contentController instanceof ConfirmationCodeContentController) {
                                        ((ConfirmationCodeContentController) contentController).setRetry(true);
                                    }
                                }
                            });
                        }
                    }
                }

                /* access modifiers changed from: protected */
                public void onAccountVerified(PhoneLoginModel loginModel) {
                    if (activity.getContentController() instanceof SendingCodeContentController) {
                        activity.pushState(LoginFlowState.ACCOUNT_VERIFIED, null);
                    }
                }

                /* access modifiers changed from: protected */
                public void onSuccess(PhoneLoginModel loginModel) {
                    ContentController contentController = activity.getContentController();
                    if ((contentController instanceof ConfirmationCodeContentController) || (contentController instanceof VerifyingCodeContentController)) {
                        activity.pushState(LoginFlowState.VERIFIED, null);
                        activity.setAuthorizationCode(loginModel.getCode());
                        activity.setAccessToken(loginModel.getAccessToken());
                        activity.setLoginResult(LoginResult.SUCCESS);
                        activity.setFinalAuthState(loginModel.getFinalAuthState());
                        AccessToken accessToken = loginModel.getAccessToken();
                        if (accessToken != null) {
                            activity.setTokenRefreshIntervalInSeconds(accessToken.getTokenRefreshIntervalSeconds());
                        }
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                C34051.this.finishActivity();
                            }
                        }, 2000);
                    }
                }

                /* access modifiers changed from: protected */
                public void onError(AccountKitException exception) {
                    activity.pushError(exception.getError());
                }

                /* access modifiers changed from: protected */
                public void onCancel(PhoneLoginModel loginModel) {
                    activity.setNewLoginFlowManagerAndHandler(null);
                }

                /* access modifiers changed from: private */
                public void finishActivity() {
                    activity.sendResult();
                }
            };
        }
        return getPhoneTracker();
    }

    /* access modifiers changed from: 0000 */
    public void onConfirmationCodeComplete(AccountKitActivity activity, PhoneLoginFlowManager loginFlowManager, String confirmationCode) {
        activity.pushState(LoginFlowState.VERIFYING_CODE, null);
        loginFlowManager.setConfirmationCode(confirmationCode);
    }

    /* access modifiers changed from: 0000 */
    public void onConfirmationCodeRetry(AccountKitActivity activity) {
        activity.pushState(LoginFlowState.RESEND, getResendOnPushListener());
    }

    private OnPushListener getResendOnPushListener() {
        final String phoneNumber;
        final PhoneLoginModel phoneLoginModel = AccountKit.getCurrentPhoneNumberLogInModel();
        if (phoneLoginModel != null) {
            phoneNumber = phoneLoginModel.getPhoneNumber().toString();
        } else {
            phoneNumber = null;
        }
        if (phoneNumber == null) {
            return null;
        }
        return new OnPushListener() {
            public void onContentControllerReady(ContentController contentController) {
                if (contentController instanceof ResendContentController) {
                    ResendContentController resendContentController = (ResendContentController) contentController;
                    resendContentController.setPhoneNumber(phoneNumber);
                    resendContentController.setNotificationChannels(ActivityPhoneHandler.this.configuration.getNotificationChannels());
                    resendContentController.setResendTime(phoneLoginModel.getResendTime());
                }
            }

            public void onContentPushed() {
            }
        };
    }

    /* access modifiers changed from: 0000 */
    public void onPhoneLoginComplete(AccountKitActivity activity, PhoneLoginFlowManager phoneManager, PhoneNumber phoneNumber) {
        activity.pushState(LoginFlowState.SENDING_CODE, null);
        phoneManager.logInWithPhoneNumber(phoneNumber, NotificationChannel.SMS, this.configuration.getResponseType(), this.configuration.getInitialAuthState());
    }

    /* access modifiers changed from: 0000 */
    public void onResend(AccountKitActivity activity) {
        AccountKit.cancelLogin();
        popToPhoneNumberInput(activity);
    }

    /* access modifiers changed from: private */
    public void popToPhoneNumberInput(final AccountKitActivity activity) {
        ContentController contentController = activity.getContentController();
        if (contentController instanceof ResendContentController) {
            activity.multiPopBackStack(new OnPopListener() {
                public void onContentPopped() {
                    ActivityPhoneHandler.this.popToPhoneNumberInput(activity);
                }
            });
        } else if (contentController instanceof ConfirmationCodeContentController) {
            activity.popBackStack(LoginFlowState.PHONE_NUMBER_INPUT, new OnPopListener() {
                public void onContentPopped() {
                    ActivityPhoneHandler.this.resendSetRetry(activity);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void resendSetRetry(AccountKitActivity activity) {
        ContentController contentController = activity.getContentController();
        if (contentController instanceof PhoneLoginContentController) {
            ((PhoneLoginContentController) contentController).setRetry();
            contentController.onResume(activity);
        }
    }

    /* access modifiers changed from: 0000 */
    public void onResendFacebookNotification(final AccountKitActivity activity, final PhoneLoginFlowManager phoneManager) {
        PhoneLoginModel phoneLoginModel = AccountKit.getCurrentPhoneNumberLogInModel();
        if (phoneLoginModel != null) {
            final PhoneNumber phoneNumber = phoneLoginModel.getPhoneNumber();
            activity.multiPopBackStack(new OnPopListener() {
                public void onContentPopped() {
                    activity.popBackStack(LoginFlowState.SENT_CODE, new OnPopListener() {
                        public void onContentPopped() {
                            activity.pushState(LoginFlowState.SENDING_CODE, null);
                            phoneManager.logInWithPhoneNumber(phoneNumber, NotificationChannel.FACEBOOK, ActivityPhoneHandler.this.configuration.getResponseType(), ActivityPhoneHandler.this.configuration.getInitialAuthState());
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: 0000 */
    public void onResendVoiceCallNotification(final AccountKitActivity activity, final PhoneLoginFlowManager phoneManager) {
        PhoneLoginModel phoneLoginModel = AccountKit.getCurrentPhoneNumberLogInModel();
        if (phoneLoginModel != null) {
            final PhoneNumber phoneNumber = phoneLoginModel.getPhoneNumber();
            activity.multiPopBackStack(new OnPopListener() {
                public void onContentPopped() {
                    activity.popBackStack(LoginFlowState.SENT_CODE, new OnPopListener() {
                        public void onContentPopped() {
                            activity.pushState(LoginFlowState.SENDING_CODE, null);
                            phoneManager.logInWithPhoneNumber(phoneNumber, NotificationChannel.VOICE_CALLBACK, ActivityPhoneHandler.this.configuration.getResponseType(), ActivityPhoneHandler.this.configuration.getInitialAuthState());
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: 0000 */
    public OnPushListener getConfirmationCodePushListener(final AccountKitActivity activity) {
        return new OnPushListener() {
            public void onContentControllerReady(ContentController contentController) {
                if (contentController instanceof ConfirmationCodeContentController) {
                    PhoneLoginModel phoneLoginModel = AccountKit.getCurrentPhoneNumberLogInModel();
                    if (phoneLoginModel != null) {
                        ConfirmationCodeContentController confirmationCodeContentController = (ConfirmationCodeContentController) contentController;
                        confirmationCodeContentController.setPhoneNumber(phoneLoginModel.getPhoneNumber());
                        confirmationCodeContentController.setNotificationChannel(phoneLoginModel.getNotificationChannel());
                        confirmationCodeContentController.setDetectedConfirmationCode(ActivityPhoneHandler.this.getLoginTracker(activity).getCode());
                    }
                }
            }

            public void onContentPushed() {
            }
        };
    }

    public void onSentCodeComplete(AccountKitActivity activity) {
        activity.pushState(LoginFlowState.CODE_INPUT, null);
    }

    public void onAccountVerifiedComplete(AccountKitActivity activity) {
        activity.pushState(LoginFlowState.CONFIRM_ACCOUNT_VERIFIED, null);
    }

    /* access modifiers changed from: 0000 */
    public void onConfirmSeamlessLogin(AccountKitActivity activity, PhoneLoginFlowManager loginFlowManager) {
        activity.pushState(LoginFlowState.CONFIRM_INSTANT_VERIFICATION_LOGIN, null);
        loginFlowManager.confirmSeamlessLogin();
    }

    /* access modifiers changed from: 0000 */
    public SmsTracker getSmsTracker() {
        return this.smsTracker;
    }

    /* access modifiers changed from: 0000 */
    public void startSmsTrackerIfPossible(final AccountKitActivity activity) {
        try {
            if (this.configuration.isReceiveSMSEnabled() && Utility.hasReceiveSmsPermissions(AccountKitController.getApplicationContext())) {
                if (this.smsTracker == null) {
                    this.smsTracker = new SmsTracker() {
                        /* access modifiers changed from: protected */
                        public void confirmationCodeReceived(String code) {
                            ContentController contentController = activity.getContentController();
                            if ((contentController instanceof SendingCodeContentController) || (contentController instanceof SentCodeContentController)) {
                                ActivityPhoneHandler.this.getPhoneTracker().setCode(code);
                            } else if (contentController instanceof ConfirmationCodeContentController) {
                                ((ConfirmationCodeContentController) contentController).setDetectedConfirmationCode(code);
                            }
                            ActivityPhoneHandler.this.smsTracker.stopTracking();
                        }
                    };
                }
                this.smsTracker.startTracking();
            }
        } catch (Exception e) {
        }
    }

    /* access modifiers changed from: 0000 */
    public void pauseSmsTracker() {
        if (this.smsTracker != null) {
            this.smsTracker.pauseTracking();
        }
    }

    /* access modifiers changed from: 0000 */
    public void stopSmsTracker() {
        if (this.smsTracker != null) {
            this.smsTracker.stopTracking();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean isSmsTracking() {
        return this.smsTracker != null && this.smsTracker.isTracking();
    }

    private ActivityPhoneHandler(Parcel parcel) {
        super(parcel);
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    /* access modifiers changed from: private */
    public PhoneLoginTracker getPhoneTracker() {
        return (PhoneLoginTracker) this.tracker;
    }
}
