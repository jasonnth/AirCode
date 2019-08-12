package com.facebook.accountkit.p029ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.p000v4.content.LocalBroadcastManager;
import android.view.View;
import com.facebook.accountkit.C3354R;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.p029ui.LoginFlowBroadcastReceiver.Event;
import com.facebook.accountkit.p029ui.StaticContentFragmentFactory.StaticContentFragment;
import com.facebook.accountkit.p029ui.TitleFragmentFactory.TitleFragment;

/* renamed from: com.facebook.accountkit.ui.AccountVerifiedContentController */
public class AccountVerifiedContentController extends ContentControllerBase {
    private static final int COMPLETION_UI_DURATION_MS = 2000;
    private StaticContentFragment bottomFragment;
    private StaticContentFragment centerFragment;
    /* access modifiers changed from: private */
    public Handler delayedTransitionHandler;
    /* access modifiers changed from: private */
    public Runnable delayedTransitionRunnable;
    private TitleFragment footerFragment;
    private TitleFragment headerFragment;
    private StaticContentFragment textFragment;
    private StaticContentFragment topFragment;

    AccountVerifiedContentController(AccountKitConfiguration configuration) {
        super(configuration);
    }

    public void onResume(final Activity activity) {
        super.onResume(activity);
        cancelTransition();
        this.delayedTransitionHandler = new Handler();
        this.delayedTransitionRunnable = new Runnable() {
            public void run() {
                LocalBroadcastManager.getInstance(activity).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.ACCOUNT_VERIFIED_COMPLETE));
                AccountVerifiedContentController.this.delayedTransitionHandler = null;
                AccountVerifiedContentController.this.delayedTransitionRunnable = null;
            }
        };
        this.delayedTransitionHandler.postDelayed(this.delayedTransitionRunnable, 2000);
    }

    /* access modifiers changed from: protected */
    public void logImpression() {
        Logger.logUIAccountVerified(true);
    }

    public void onPause(Activity activity) {
        cancelTransition();
        super.onPause(activity);
    }

    public ContentFragment getBottomFragment() {
        if (this.bottomFragment == null) {
            setBottomFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState()));
        }
        return this.bottomFragment;
    }

    public void setBottomFragment(ContentFragment fragment) {
        if (fragment instanceof StaticContentFragment) {
            this.bottomFragment = (StaticContentFragment) fragment;
        }
    }

    public ContentFragment getCenterFragment() {
        if (this.centerFragment == null) {
            setCenterFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState(), C3354R.layout.com_accountkit_fragment_sent_code_center));
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
            setHeaderFragment(TitleFragmentFactory.create(this.configuration.getUIManager(), C3354R.string.com_accountkit_account_verified, new String[0]));
        }
        return this.headerFragment;
    }

    public void setHeaderFragment(TitleFragment fragment) {
        this.headerFragment = fragment;
    }

    public LoginFlowState getLoginFlowState() {
        return LoginFlowState.ACCOUNT_VERIFIED;
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

    private void cancelTransition() {
        if (this.delayedTransitionHandler != null && this.delayedTransitionRunnable != null) {
            this.delayedTransitionHandler.removeCallbacks(this.delayedTransitionRunnable);
            this.delayedTransitionRunnable = null;
            this.delayedTransitionHandler = null;
        }
    }
}
