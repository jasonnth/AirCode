package com.facebook.accountkit.p029ui;

import android.view.View;
import com.facebook.accountkit.C3354R;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.p029ui.StaticContentFragmentFactory.StaticContentFragment;
import com.facebook.accountkit.p029ui.TitleFragmentFactory.TitleFragment;

/* renamed from: com.facebook.accountkit.ui.VerifyingCodeContentController */
final class VerifyingCodeContentController extends ContentControllerBase {
    private StaticContentFragment bottomFragment;
    private StaticContentFragment centerFragment;
    private TitleFragment footerFragment;
    private TitleFragment headerFragment;
    private StaticContentFragment textFragment;
    private StaticContentFragment topFragment;

    VerifyingCodeContentController(AccountKitConfiguration configuration) {
        super(configuration);
    }

    public boolean isTransient() {
        return false;
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
            setCenterFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState(), C3354R.layout.com_accountkit_fragment_verifying_code_center));
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
            setHeaderFragment(TitleFragmentFactory.create(this.configuration.getUIManager(), C3354R.string.com_accountkit_verify_title, new String[0]));
        }
        return this.headerFragment;
    }

    public void setHeaderFragment(TitleFragment fragment) {
        this.headerFragment = fragment;
    }

    public LoginFlowState getLoginFlowState() {
        return LoginFlowState.VERIFYING_CODE;
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
        Logger.logUIVerifyingCode(true, this.configuration.getLoginType());
    }
}
