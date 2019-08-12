package com.facebook.accountkit.p029ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.facebook.accountkit.C3354R;
import com.facebook.accountkit.internal.AccountKitController.Logger;
import com.facebook.accountkit.p029ui.LoginFlowBroadcastReceiver.Event;
import com.facebook.accountkit.p029ui.StaticContentFragmentFactory.StaticContentFragment;
import com.facebook.accountkit.p029ui.TitleFragmentFactory.TitleFragment;

/* renamed from: com.facebook.accountkit.ui.ErrorContentController */
final class ErrorContentController extends ContentControllerBase {
    /* access modifiers changed from: private */
    public static final LoginFlowState LOGIN_FLOW_STATE = LoginFlowState.ERROR;
    private BottomFragment bottomFragment;
    private StaticContentFragment centerFragment;
    private TitleFragment footerFragment;
    private TitleFragment headerFragment;
    private final LoginFlowState returnState;
    private StaticContentFragment textFragment;
    private StaticContentFragment topFragment;

    /* renamed from: com.facebook.accountkit.ui.ErrorContentController$BottomFragment */
    public static final class BottomFragment extends ContentFragment {
        /* access modifiers changed from: private */
        public static final String RETURN_LOGIN_FLOW_STATE = (TAG + ".RETURN_LOGIN_FLOW_STATE");

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
            return inflater.inflate(C3354R.layout.com_accountkit_fragment_error_bottom, container, false);
        }

        /* access modifiers changed from: 0000 */
        public LoginFlowState getLoginFlowState() {
            return ErrorContentController.LOGIN_FLOW_STATE;
        }

        /* access modifiers changed from: 0000 */
        public boolean isKeyboardFragment() {
            return false;
        }

        /* access modifiers changed from: protected */
        public void onViewReadyWithState(View view, final Bundle viewState) {
            super.onViewReadyWithState(view, viewState);
            View startOverButton = view.findViewById(C3354R.C3356id.com_accountkit_start_over_button);
            if (startOverButton != null) {
                startOverButton.setOnClickListener(new OnClickListener() {
                    public void onClick(View v) {
                        Logger.logUIErrorInteraction(Buttons.TRY_AGAIN.name());
                        LocalBroadcastManager.getInstance(v.getContext()).sendBroadcast(new Intent(LoginFlowBroadcastReceiver.ACTION_UPDATE).putExtra(LoginFlowBroadcastReceiver.EXTRA_EVENT, Event.ERROR_RESTART).putExtra(LoginFlowBroadcastReceiver.EXTRA_RETURN_LOGIN_FLOW_STATE, (Integer) viewState.get(BottomFragment.RETURN_LOGIN_FLOW_STATE)));
                    }
                });
            }
        }
    }

    ErrorContentController(LoginFlowState returnState2, AccountKitConfiguration configuration) {
        super(configuration);
        this.returnState = returnState2;
    }

    /* access modifiers changed from: 0000 */
    public LoginFlowState getReturnState() {
        return this.returnState;
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
            this.bottomFragment.getViewState().putInt(BottomFragment.RETURN_LOGIN_FLOW_STATE, this.returnState.ordinal());
        }
    }

    public ContentFragment getCenterFragment() {
        if (this.centerFragment == null) {
            setCenterFragment(StaticContentFragmentFactory.create(this.configuration.getUIManager(), getLoginFlowState(), C3354R.layout.com_accountkit_fragment_error_center));
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
            this.headerFragment = TitleFragmentFactory.create(this.configuration.getUIManager(), C3354R.string.com_accountkit_error_title, new String[0]);
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

    public void setErrorMessage(String errorMessage) {
        if (this.headerFragment != null) {
            this.headerFragment.setTitle(errorMessage);
        }
    }

    /* access modifiers changed from: protected */
    public void logImpression() {
        Logger.logUIError(true, this.configuration.getLoginType());
    }
}
