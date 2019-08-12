package com.airbnb.android.cohosting.adapters;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.controllers.CohostManagementDataController;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineFormattedIntegerInputRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SwitchRowEpoxyModel_;
import com.airbnb.p027n2.components.SwitchStyle;
import icepick.State;

public class CohostingShareEarningsAdapter extends AirEpoxyAdapter {
    private final Context context;
    private final CohostManagementDataController controller;
    private final DocumentMarqueeEpoxyModel_ headerRow = new DocumentMarqueeEpoxyModel_();
    @State
    boolean includeCleaningFee;
    private final Listener listener;
    private final ListingManager listingManager;
    CohostingManagementJitneyLogger logger;
    private final InlineFormattedIntegerInputRowEpoxyModel_ percentRow = InlineFormattedIntegerInputRowEpoxyModel_.forIntegerPercentage();
    @State
    int percentage;
    private final SimpleTextRowEpoxyModel_ shareEarningsIntro = new SimpleTextRowEpoxyModel_();
    private final SwitchRowEpoxyModel_ switchRow = new SwitchRowEpoxyModel_();
    private final SimpleTextRowEpoxyModel_ warningRow = new SimpleTextRowEpoxyModel_();

    public interface Listener {
        void updateShareButtonVisibility(boolean z);
    }

    public CohostingShareEarningsAdapter(Context context2, Listener listener2, CohostManagementDataController controller2, ListingManager listingManager2, Bundle savedInstanceState) {
        this.context = context2;
        this.controller = controller2;
        this.listingManager = listingManager2;
        this.listener = listener2;
        if (savedInstanceState == null) {
            this.percentage = 0;
            this.includeCleaningFee = false;
        } else {
            onRestoreInstanceState(savedInstanceState);
        }
        enableDiffing();
        setupRows();
        ((CohostingGraph) CoreApplication.instance(context2).component()).inject(this);
    }

    private void setupRows() {
        setupHeader();
        setupShareEarningsIntro();
        setupPercentRow();
        setupSwitchRow();
        setupWarningRow();
    }

    private void setupHeader() {
        this.headerRow.titleText((CharSequence) this.context.getString(C5658R.string.cohosting_share_earnings_title, new Object[]{this.listingManager.getUser().getFirstName()}));
        addModel(this.headerRow);
    }

    private void setupShareEarningsIntro() {
        this.shareEarningsIntro.text(this.context.getString(C5658R.string.cohosting_share_earnings_content_with_cohost, new Object[]{this.listingManager.getUser().getFirstName()})).showDivider(true);
        addModel(this.shareEarningsIntro);
    }

    private void setupPercentRow() {
        this.percentRow.titleRes(C5658R.string.share_of_earnings_title).inputAmount(Integer.valueOf(this.percentage)).amountChangedListener(CohostingShareEarningsAdapter$$Lambda$1.lambdaFactory$(this));
        addModel(this.percentRow);
    }

    private void setupSwitchRow() {
        this.switchRow.titleRes(C5658R.string.cohosting_invite_a_friend_switch_row).style(SwitchStyle.Filled).checked(this.includeCleaningFee).checkedChangeListener(CohostingShareEarningsAdapter$$Lambda$2.lambdaFactory$(this)).showDivider(false);
        addModel(this.switchRow);
    }

    private void setupWarningRow() {
        if (this.controller.hasPaidListingManager()) {
            setupWarningOfPaidListingManager(this.controller.getPaidListingManager());
        } else if (this.controller.hasPaidCohostInvitation()) {
            setupWarningOfPaidCohostInvitation();
        }
        displayWarningIfNeeded();
    }

    private void setupWarningOfPaidListingManager(ListingManager paidListingManager) {
        this.warningRow.coloredText(this.context.getString(C5658R.string.cohosting_share_earnings_warning_title, new Object[]{paidListingManager.getUser().getFirstName()})).description(this.context.getString(C5658R.string.cohosting_share_earnings_warning_content_with_cohost_name, new Object[]{this.listingManager.getUser().getFirstName(), paidListingManager.getUser().getFirstName()})).color(C5658R.color.n2_arches).hasColoredText(true).small().showDivider(false);
        addModel(this.warningRow);
    }

    private void setupWarningOfPaidCohostInvitation() {
        this.warningRow.coloredTextRes(C5658R.string.cohosting_share_earnings_warning_title_due_to_pending_invite).descriptionRes(C5658R.string.cohosting_share_earnings_warning_content_due_to_pending_invite).color(C5658R.color.n2_arches).hasColoredText(true).small().showDivider(false);
        addModel(this.warningRow);
    }

    private boolean hasSetPayout() {
        return (this.percentRow.inputAmount() != null && this.percentRow.inputAmount().intValue() > 0) || this.switchRow.checked();
    }

    private void displayWarningIfNeeded() {
        if (this.controller.hasPaidListingManagerOrPaidCohostInvitation()) {
            if (!this.warningRow.isShown() && hasSetPayout()) {
                showModel(this.warningRow);
                this.logger.logContractExistAlertImpression(this.controller.getCohostingContext(), this.listingManager);
                this.switchRow.showDivider(true);
                notifyModelsChanged();
            } else if (this.warningRow.isShown() && !hasSetPayout()) {
                hideModel(this.warningRow);
                this.switchRow.showDivider(false);
                notifyModelsChanged();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void updateShareButtonAvailability() {
        this.listener.updateShareButtonVisibility(hasSetPayout());
        displayWarningIfNeeded();
    }

    public int getPercentage() {
        return SanitizeUtils.zeroIfNull(this.percentRow.inputAmount());
    }

    public boolean getIncludeCleaningFee() {
        return this.switchRow.checked();
    }

    public void onSaveInstanceState(Bundle outState) {
        this.percentage = SanitizeUtils.zeroIfNull(this.percentRow.inputAmount());
        this.includeCleaningFee = this.switchRow.checked();
        super.onSaveInstanceState(outState);
    }

    public boolean hasChanged() {
        return getPercentage() != 0 || getIncludeCleaningFee();
    }
}
