package com.airbnb.android.lib.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.aireventlogger.AirbnbEventLogger;
import com.airbnb.android.core.authentication.AuthorizedAccountHelper;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.intents.EntryActivityIntents;
import com.airbnb.android.core.intents.PayoutActivityIntents;
import com.airbnb.android.core.models.Currency;
import com.airbnb.android.core.requests.CurrenciesRequest;
import com.airbnb.android.core.responses.CurrenciesResponse;
import com.airbnb.android.core.utils.ShakeFeedbackSensorListener;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.PaymentInfoActivity;
import com.airbnb.android.lib.activities.SettingsActivity;
import com.airbnb.android.lib.adapters.settings.AccountSettingsEpoxyController;
import com.airbnb.android.lib.adapters.settings.AccountSettingsEpoxyController.Listener;
import com.airbnb.android.lib.fragments.CurrencySelectorDialogFragment.OnCurrencySelectedListener;
import com.airbnb.android.utils.AirbnbConstants;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import java.util.List;
import p032rx.Observer;

public class AccountSettingsFragment extends AirFragment implements OnCurrencySelectedListener {
    private static final int DIALOG_REQ_ACCOUNT_SWITCHER = 10003;
    private static final int DIALOG_REQ_LOGOUT = 10001;
    private static final int DIALOG_REQ_PUSH_MESSAGE = 10002;
    private static final String FRAGMENT_CURRENCIES = "currency_dialog";
    private static final int MAX_PROMPT_ACCOUNT_SWITCH_COUNT = 2;
    private static final String TAG = AccountSettingsFragment.class.getSimpleName();
    final RequestListener<CurrenciesResponse> currencyRequestListener = new C0699RL().onResponse(AccountSettingsFragment$$Lambda$1.lambdaFactory$(this)).onError(AccountSettingsFragment$$Lambda$2.lambdaFactory$(this)).build();
    private AccountSettingsEpoxyController epoxyController;
    private Listener listener = new Listener() {
        public void onSendFeedbackClicked() {
            AccountSettingsFragment.this.shakeFeedbackSensorListener.showFeedbackDialog(AccountSettingsFragment.this.getActivity());
        }

        public void onSearchSettingsClicked() {
            AccountSettingsFragment.this.getSettingsActivity().showFragment(SearchSettingsFragment.newInstance());
        }

        public void onAdvancedSettingsClicked() {
            AccountSettingsFragment.this.getSettingsActivity().showFragment(AdvancedSettingsFragment.newInstance());
        }

        public void onPaymentInfoClick() {
            AccountSettingsFragment.this.startActivity(PaymentInfoActivity.intent(AccountSettingsFragment.this.getActivity()));
        }

        public void onNotificationSettingsClicked() {
            AccountSettingsFragment.this.getSettingsActivity().showFragment(NotificationSettingsFragment.newInstance());
        }

        public void onCurrencyClicked() {
            if (!AccountSettingsFragment.this.requestManager.hasRequest((BaseRequestListener<T>) AccountSettingsFragment.this.currencyRequestListener, CurrenciesRequest.class)) {
                new CurrenciesRequest(AccountSettingsFragment.this.getContext()).withListener((Observer) AccountSettingsFragment.this.currencyRequestListener).execute(AccountSettingsFragment.this.requestManager);
            }
        }

        public void onPayoutsClicked() {
            AccountSettingsFragment.this.startActivity(PayoutActivityIntents.forManagePayoutMethods(AccountSettingsFragment.this.getActivity()));
        }

        public void onAboutClicked() {
            AccountSettingsFragment.this.getSettingsActivity().showFragment(AboutFragment.newInstance());
        }

        public void onSwitchAccountClicked() {
            AccountSettingsFragmentPermissionsDispatcher.showAccountSwitcherDialogWithCheck(AccountSettingsFragment.this);
        }

        public void onSwitchAccountExplanationClicked() {
            ZenDialog.createSingleButtonDialog(C0880R.string.switch_account_cell_text, AccountSettingsFragment.this.getContext().getString(C0880R.string.switch_account_tooltip_message) + "\n\n" + AccountSettingsFragment.this.getContext().getString(C0880R.string.switch_account_prompt_body_push_info), 0).show(AccountSettingsFragment.this.getFragmentManager(), (String) null);
        }

        public void onLogoutClicked() {
            SharedPreferences prefs = AccountSettingsFragment.this.mPreferences.getGlobalSharedPreferences();
            AccountSettingsFragment.this.onLogout(prefs, prefs.getInt(AirbnbConstants.PREFS_ACCOUNTS_SWITCHER_PROMPT_COUNT, 0));
        }
    };
    @BindView
    RecyclerView recyclerView;
    ShakeFeedbackSensorListener shakeFeedbackSensorListener;
    @BindView
    AirToolbar toolbar;

    public static Fragment newInstance() {
        return new AccountSettingsFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_recycler_view_with_toolbar, container, false);
        bindViews(view);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        setToolbar(this.toolbar);
        this.toolbar.setTitle(C0880R.string.account_settings);
        this.epoxyController = new AccountSettingsEpoxyController(this.mAccountManager, this.mCurrencyHelper, this.listener);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        return view;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.Settings;
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions2, grantResults);
        AccountSettingsFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    /* access modifiers changed from: 0000 */
    public void onPermissionDenied() {
    }

    public void showAccountSwitcherDialog() {
        SwitchAccountDialogFragment.newInstance(this.mAccountManager.getCurrentUserId(), getActivity(), this).showAllowingStateLoss(getFragmentManager(), null);
        AirbnbEventLogger.track("android_eng", Strap.make().mo11639kv(Trebuchet.KEY_ACCOUNT_SWITCHER, "click"));
    }

    public void onContactsPermissionNeverAskAgain() {
        new SnackbarWrapper().view(getView()).title(getString(C0880R.string.contacts_permission_required), true).body(getString(C0880R.string.contacts_permission_required_body)).duration(0).action(getString(C0880R.string.snackbar_settings_button), AccountSettingsFragment$$Lambda$3.lambdaFactory$(this)).buildAndShow();
    }

    public void onCurrencySelected(Currency selected) {
        this.mCurrencyHelper.setCurrency(selected.getCode(), true, false);
        this.epoxyController.updateCurrencyRow();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SwitchAccountDialogFragment.DIALOG_REQ_SWITCH_COMPLETE /*8999*/:
                startActivity(HomeActivityIntents.intentForDefaultTab(getActivity()));
                return;
            case DIALOG_REQ_LOGOUT /*10001*/:
                AuthorizedAccountHelper.get(getActivity()).removeCurrentUser();
                this.mAirbnbApi.logout();
                startActivity(EntryActivityIntents.newIntent(getActivity()));
                getActivity().finishAffinity();
                return;
            case DIALOG_REQ_PUSH_MESSAGE /*10002*/:
                ZenDialog.builder().withTitle(C0880R.string.push_notifications).withBodyText(C0880R.string.switch_account_prompt_body_push_info).withSingleButton(C0880R.string.okay, (int) DIALOG_REQ_ACCOUNT_SWITCHER, (Fragment) this).create().show(getFragmentManager(), (String) null);
                return;
            case DIALOG_REQ_ACCOUNT_SWITCHER /*10003*/:
                this.listener.onSwitchAccountClicked();
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }

    /* access modifiers changed from: private */
    public SettingsActivity getSettingsActivity() {
        return (SettingsActivity) getActivity();
    }

    /* access modifiers changed from: private */
    public void onLogout(SharedPreferences prefs, int promptCount) {
        if (!this.mAccountManager.hasAccountSwitcher() || promptCount >= 2) {
            r7 = DIALOG_REQ_LOGOUT;
            ZenDialog.builder().withBodyText(C0880R.string.log_out_warning_prompt_message).withDualButton(C0880R.string.cancel, 0, C0880R.string.logout, (int) DIALOG_REQ_LOGOUT, (Fragment) this).create().show(getFragmentManager(), TAG);
            return;
        }
        ZenDialog.builder().withBodyText(C0880R.string.switch_account_prompt_body).withDualButton(C0880R.string.logout, (int) DIALOG_REQ_LOGOUT, C0880R.string.switch_account_prompt_button, (int) DIALOG_REQ_PUSH_MESSAGE, (Fragment) this).create().show(getFragmentManager(), (String) null);
        prefs.edit().putInt(AirbnbConstants.PREFS_ACCOUNTS_SWITCHER_PROMPT_COUNT, promptCount + 1).apply();
    }

    /* access modifiers changed from: private */
    public void updateCurrency(List<Currency> currencies) {
        Currency currency = new Currency();
        currency.setCode(this.mCurrencyHelper.getLocalCurrencyString());
        CurrencySelectorDialogFragment prev = (CurrencySelectorDialogFragment) getFragmentManager().findFragmentByTag(FRAGMENT_CURRENCIES);
        if (prev != null) {
            prev.dismiss();
        }
        ZenDialog dialog = CurrencySelectorDialogFragment.newInstance(currencies.indexOf(currency), currencies);
        dialog.setTargetFragment(this, 0);
        dialog.showAllowingStateLoss(getFragmentManager(), FRAGMENT_CURRENCIES);
    }
}
