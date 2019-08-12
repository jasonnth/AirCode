package com.facebook.accountkit.p029ui;

import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.accountkit.AccessToken;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitException;
import com.facebook.accountkit.EmailLoginModel;
import com.facebook.accountkit.EmailLoginTracker;
import com.facebook.accountkit.LoginResult;

/* renamed from: com.facebook.accountkit.ui.ActivityEmailHandler */
final class ActivityEmailHandler extends ActivityHandler {
    public static final Creator<ActivityEmailHandler> CREATOR = new Creator<ActivityEmailHandler>() {
        public ActivityEmailHandler createFromParcel(Parcel source) {
            return new ActivityEmailHandler(source);
        }

        public ActivityEmailHandler[] newArray(int size) {
            return new ActivityEmailHandler[size];
        }
    };

    public ActivityEmailHandler(AccountKitConfiguration configuration) {
        super(configuration);
    }

    public EmailLoginTracker getLoginTracker(final AccountKitActivity activity) {
        if (getEmailTracker() == null) {
            this.tracker = new EmailLoginTracker() {
                /* access modifiers changed from: protected */
                public void onStarted(EmailLoginModel loginModel) {
                    if (activity.getContentController() instanceof SendingCodeContentController) {
                        activity.pushState(LoginFlowState.SENT_CODE, null);
                    }
                }

                /* access modifiers changed from: protected */
                public void onSuccess(EmailLoginModel loginModel) {
                    ContentController contentController = activity.getContentController();
                    if ((contentController instanceof EmailVerifyContentController) || (contentController instanceof VerifyingCodeContentController)) {
                        activity.pushState(LoginFlowState.VERIFIED, null);
                        activity.setFinalAuthState(loginModel.getFinalAuthState());
                        activity.setAccessToken(loginModel.getAccessToken());
                        activity.setAuthorizationCode(loginModel.getCode());
                        activity.setLoginResult(LoginResult.SUCCESS);
                        AccessToken accessToken = loginModel.getAccessToken();
                        if (accessToken != null) {
                            activity.setTokenRefreshIntervalInSeconds(accessToken.getTokenRefreshIntervalSeconds());
                        }
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                C34011.this.finishActivity();
                            }
                        }, 2000);
                    }
                }

                /* access modifiers changed from: protected */
                public void onError(AccountKitException exception) {
                    activity.pushError(exception.getError());
                }

                /* access modifiers changed from: protected */
                public void onCancel(EmailLoginModel loginModel) {
                    activity.setNewLoginFlowManagerAndHandler(null);
                }

                /* access modifiers changed from: private */
                public void finishActivity() {
                    activity.sendResult();
                }
            };
        }
        return getEmailTracker();
    }

    public void onEmailLoginComplete(AccountKitActivity activity, EmailLoginFlowManager emailManager, String email) {
        activity.pushState(LoginFlowState.SENDING_CODE, null);
        emailManager.setEmail(email);
        emailManager.logInWithEmail(this.configuration.getResponseType(), this.configuration.getInitialAuthState());
    }

    public void onEmailVerifyRetry(final AccountKitActivity activity) {
        AccountKit.cancelLogin();
        activity.popBackStack(LoginFlowState.EMAIL_INPUT, new OnPopListener() {
            public void onContentPopped() {
                ActivityEmailHandler.this.emailVerifySetRetry(activity);
            }
        });
    }

    /* access modifiers changed from: private */
    public void emailVerifySetRetry(AccountKitActivity activity) {
        ContentController contentController = activity.getContentController();
        if (contentController instanceof EmailLoginContentController) {
            ((EmailLoginContentController) contentController).setRetry();
        }
    }

    public void onSentCodeComplete(AccountKitActivity activity) {
        if (activity.getContentController() instanceof SentCodeContentController) {
            activity.pushState(LoginFlowState.EMAIL_VERIFY, null);
        }
    }

    public void onAccountVerifiedComplete(AccountKitActivity accountKitActivity) {
    }

    protected ActivityEmailHandler(Parcel parcel) {
        super(parcel);
    }

    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
    }

    private EmailLoginTracker getEmailTracker() {
        return (EmailLoginTracker) this.tracker;
    }
}
