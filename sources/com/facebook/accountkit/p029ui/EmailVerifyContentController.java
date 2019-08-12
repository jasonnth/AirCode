package com.facebook.accountkit.p029ui;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import com.facebook.accountkit.C3354R;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.p029ui.LoginFlowBroadcastReceiver.Event;
import com.facebook.accountkit.p029ui.StaticContentFragmentFactory.StaticContentFragment;
import com.facebook.accountkit.p029ui.TitleFragmentFactory.TitleFragment;

/* renamed from: com.facebook.accountkit.ui.EmailVerifyContentController */
final class EmailVerifyContentController extends ContentControllerBase {
    /* access modifiers changed from: private */
    public static final LoginFlowState LOGIN_FLOW_STATE = LoginFlowState.EMAIL_VERIFY;
    private BottomFragment bottomFragment;
    private StaticContentFragment centerFragment;
    private TitleFragment footerFragment;
    private TitleFragment headerFragment;
    private StaticContentFragment textFragment;
    private StaticContentFragment topFragment;

    /* renamed from: com.facebook.accountkit.ui.EmailVerifyContentController$BottomFragment */
    public static final class BottomFragment extends ContentFragment {
        /* access modifiers changed from: private */
        public OnCompleteListener onCompleteListener;

        /* renamed from: com.facebook.accountkit.ui.EmailVerifyContentController$BottomFragment$OnCompleteListener */
        interface OnCompleteListener {
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

        /* access modifiers changed from: protected */
        public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(C3354R.layout.com_accountkit_fragment_email_verify_bottom, container, false);
        }

        /* access modifiers changed from: 0000 */
        public LoginFlowState getLoginFlowState() {
            return EmailVerifyContentController.LOGIN_FLOW_STATE;
        }

        /* access modifiers changed from: 0000 */
        public boolean isKeyboardFragment() {
            return false;
        }

        /* access modifiers changed from: protected */
        public void onViewReadyWithState(View view, Bundle viewState) {
            super.onViewReadyWithState(view, viewState);
            View retryButton = view.findViewById(C3354R.C3356id.com_accountkit_retry_email_button);
            if (retryButton != null) {
                retryButton.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        Logger.logUIEmailVerifyInteraction(Buttons.SEND_NEW_EMAIL.name());
                        if (BottomFragment.this.onCompleteListener != null) {
                            BottomFragment.this.onCompleteListener.onRetry(v.getContext());
                        }
                    }
                });
            }
            Button checkEmail = (Button) view.findViewById(C3354R.C3356id.com_accountkit_check_email_button);
            if (checkEmail != null) {
                checkEmail.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent("android.intent.action.MAIN");
                        intent.addCategory("android.intent.category.APP_EMAIL");
                        intent.addFlags(1073741824);
                        Logger.logUIEmailVerifyInteraction(Buttons.OPEN_EMAIL.name());
                        try {
                            BottomFragment.this.startActivity(intent);
                        } catch (ActivityNotFoundException e) {
                        }
                    }
                });
            }
        }

        public void setOnCompleteListener(OnCompleteListener onCompleteListener2) {
            this.onCompleteListener = onCompleteListener2;
        }
    }

    EmailVerifyContentController(AccountKitConfiguration configuration) {
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
                public void onRetry(Context context) {
                    LocalBroadcastManager.getInstance(context).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.EMAIL_VERIFY_RETRY));
                }
            });
        }
    }

    public ContentFragment getCenterFragment() {
        if (this.centerFragment == null) {
            setCenterFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState(), C3354R.layout.com_accountkit_fragment_email_verify_center));
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
            this.footerFragment = new TitleFragment();
        }
        return this.footerFragment;
    }

    public void setFooterFragment(TitleFragment fragment) {
        this.footerFragment = fragment;
    }

    public TitleFragment getHeaderFragment() {
        if (this.headerFragment == null) {
            this.headerFragment = TitleFragmentFactory.create(this.configuration.getUIManager(), C3354R.string.com_accountkit_email_verify_title, new String[0]);
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
        Logger.logUIEmailVerify(true);
    }
}
