package com.airbnb.android.lib.adapters.settings;

import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.utils.BuildHelper;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.utils.SearchPricingUtil;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.epoxy.AirEpoxyController;

public class AccountSettingsEpoxyController extends AirEpoxyController {
    StandardRowEpoxyModel_ aboutRow;
    private AirbnbAccountManager accountManager;
    StandardRowEpoxyModel_ advancedSettingsRow;
    private CurrencyFormatter currencyFormatter;
    StandardRowEpoxyModel_ currencySettingsRow;
    private Listener listener;
    StandardRowEpoxyModel_ logoutRow;
    StandardRowEpoxyModel_ notificationSettingsRow;
    StandardRowEpoxyModel_ paymentInfoRow;
    StandardRowEpoxyModel_ payoutSettingsRow;
    StandardRowEpoxyModel_ searchSettingsRow;
    StandardRowEpoxyModel_ sendFeedbackRow;
    ToolbarSpacerEpoxyModel_ spacerRow;
    StandardRowEpoxyModel_ switchAccountRow;

    public interface Listener {
        void onAboutClicked();

        void onAdvancedSettingsClicked();

        void onCurrencyClicked();

        void onLogoutClicked();

        void onNotificationSettingsClicked();

        void onPaymentInfoClick();

        void onPayoutsClicked();

        void onSearchSettingsClicked();

        void onSendFeedbackClicked();

        void onSwitchAccountClicked();

        void onSwitchAccountExplanationClicked();
    }

    public AccountSettingsEpoxyController(AirbnbAccountManager accountManager2, CurrencyFormatter currencyFormatter2, Listener listener2) {
        this.accountManager = accountManager2;
        this.currencyFormatter = currencyFormatter2;
        this.listener = listener2;
        requestModelBuild();
    }

    public void updateCurrencyRow() {
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        setupSpacerRow();
        setupNotificationSettingRow();
        setupPayoutSettingRow();
        setupPaymentInfoRow();
        setupCurrencySettingsRow();
        setupAboutRow();
        setupSearchSettingsRow();
        setupAdvancedSettingRow();
        setupSwitchAccountRow();
        setupSendFeedbackRow();
        setupLogoutRow();
    }

    private boolean shouldShowSearchSettings() {
        return this.accountManager.isCurrentUserAuthorized() && !SearchPricingUtil.isTotalPricingRequired() && Experiments.isTotalPriceSettingEnabled();
    }

    private void setupCurrencySettingsRow() {
        this.currencySettingsRow.title(C0880R.string.settings_currency).actionText((CharSequence) this.currencyFormatter.getLocalCurrencyString()).clickListener(AccountSettingsEpoxyController$$Lambda$1.lambdaFactory$(this)).addTo(this);
    }

    private void setupSendFeedbackRow() {
        this.sendFeedbackRow.title(C0880R.string.feedback_dialog_send_feedback).clickListener(AccountSettingsEpoxyController$$Lambda$2.lambdaFactory$(this)).addTo(this);
    }

    private void setupSearchSettingsRow() {
        this.searchSettingsRow.title(C0880R.string.search_settings).clickListener(AccountSettingsEpoxyController$$Lambda$3.lambdaFactory$(this)).addIf(shouldShowSearchSettings(), (EpoxyController) this);
    }

    private void setupAdvancedSettingRow() {
        this.advancedSettingsRow.title(C0880R.string.advanced_settings).clickListener(AccountSettingsEpoxyController$$Lambda$4.lambdaFactory$(this)).addTo(this);
    }

    private void setupPaymentInfoRow() {
        this.paymentInfoRow.title(C0880R.string.payment_info_title).clickListener(AccountSettingsEpoxyController$$Lambda$5.lambdaFactory$(this)).addIf(BuildHelper.isFuture(), (EpoxyController) this);
    }

    private void setupNotificationSettingRow() {
        this.notificationSettingsRow.title(C0880R.string.notifications).clickListener(AccountSettingsEpoxyController$$Lambda$6.lambdaFactory$(this)).addIf(this.accountManager.isCurrentUserAuthorized(), (EpoxyController) this);
    }

    private void setupPayoutSettingRow() {
        this.payoutSettingsRow.title(C0880R.string.host_payout_method).clickListener(AccountSettingsEpoxyController$$Lambda$7.lambdaFactory$(this)).addIf(Trebuchet.launch(TrebuchetKeys.NEW_PAYOUT_FLOW_V1_ENABLED, true), (EpoxyController) this);
    }

    private void setupAboutRow() {
        this.aboutRow.title(C0880R.string.airbnb_title_about).clickListener(AccountSettingsEpoxyController$$Lambda$8.lambdaFactory$(this)).addTo(this);
    }

    private void setupSwitchAccountRow() {
        this.switchAccountRow.title(C0880R.string.switch_account_cell_text).clickListener(AccountSettingsEpoxyController$$Lambda$9.lambdaFactory$(this)).longClickListener(AccountSettingsEpoxyController$$Lambda$10.lambdaFactory$(this)).addIf(this.accountManager.hasAccountSwitcher(), (EpoxyController) this);
    }

    private void setupLogoutRow() {
        this.logoutRow.clickListener(AccountSettingsEpoxyController$$Lambda$11.lambdaFactory$(this)).title(C0880R.string.log_out).addIf(this.accountManager.isCurrentUserAuthorized(), (EpoxyController) this);
    }

    private void setupSpacerRow() {
        this.spacerRow.addTo(this);
    }
}
