package com.airbnb.android.cohosting.epoxycontrollers;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.controllers.CohostManagementDataController;
import com.airbnb.android.cohosting.shared.CohostingPaymentsSection;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;

public class CohostingShareEarningsEpoxyController extends CohostingPaymentsBaseEpoxyController {
    private final Context context;
    private final CohostManagementDataController controller;
    DocumentMarqueeEpoxyModel_ headerRow;
    private final Listener listener;
    private final ListingManager listingManager;
    CohostingManagementJitneyLogger logger;
    SimpleTextRowEpoxyModel_ shareEarningsIntro;
    SimpleTextRowEpoxyModel_ terms;
    SimpleTextRowEpoxyModel_ warningRow;

    public interface Listener {
        void enableShareButton(boolean z);
    }

    public CohostingShareEarningsEpoxyController(Context context2, Listener listener2, CohostManagementDataController controller2, ListingManager listingManager2, CurrencyFormatter currencyHelper, Bundle savedInstanceState) {
        super(savedInstanceState);
        this.context = context2;
        this.controller = controller2;
        this.listingManager = listingManager2;
        this.listener = listener2;
        if (savedInstanceState == null) {
            initializeCohostingPaymentSettings(currencyHelper.getLocalCurrencyString());
        }
        ((CohostingGraph) CoreApplication.instance(context2).component()).inject(this);
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        setupHeader();
        setupShareEarningsIntro();
        setupShareEarningsRow();
        setupWarningRow();
    }

    public void choosePaymentType() {
        this.controller.actionExecutor.choosePaymentType(getCohostingPaymentSettings().feeType(), this.listingManager.getUser().getFirstName());
    }

    public void onShareEarningsToggled(boolean isChecked) {
        super.onShareEarningsToggled(isChecked);
        updateShareButton();
    }

    public void changePercent(Integer amount) {
        super.changePercent(amount);
        updateShareButton();
    }

    public void changeMinimumFee(Integer amount) {
        super.changeMinimumFee(amount);
        updateShareButton();
    }

    public void changeFixedAmount(Integer amount) {
        super.changeFixedAmount(amount);
        updateShareButton();
    }

    public void enableCleaningFee(boolean isChecked) {
        super.enableCleaningFee(isChecked);
        updateShareButton();
    }

    private void setupHeader() {
        this.headerRow.titleText((CharSequence) this.context.getString(C5658R.string.cohosting_share_earnings_title, new Object[]{this.listingManager.getUser().getFirstName()})).addTo(this);
    }

    private void setupShareEarningsIntro() {
        this.shareEarningsIntro.textRes(C5658R.string.cohosting_invite_a_friend_shared_earnings_intro_fee_options).showDivider(true).addTo(this);
    }

    private void setupShareEarningsRow() {
        CohostingPaymentsSection.addPaymentSectionToEpoxyController(this, getCohostingPaymentSettings());
    }

    private void setupWarningRow() {
        if (getCohostingPaymentSettings().hasSetPayout()) {
            if (hasPaidListingManager()) {
                ListingManager paidListingManager = this.controller.getPaidListingManager();
                this.warningRow.coloredText(this.context.getString(C5658R.string.cohosting_share_earnings_warning_title, new Object[]{paidListingManager.getUser().getFirstName()})).description(this.context.getString(C5658R.string.cohosting_share_earnings_warning_content, new Object[]{paidListingManager.getUser().getFirstName()}));
            } else if (hasPaidPendingInvite()) {
                this.warningRow.coloredTextRes(C5658R.string.cohosting_share_earnings_warning_title_due_to_pending_invite).descriptionRes(C5658R.string.cohosting_share_earnings_warning_content_due_to_pending_invite);
            } else {
                return;
            }
            this.warningRow.color(C5658R.color.n2_arches).hasColoredText(true).small().showDivider(true).onBind(CohostingShareEarningsEpoxyController$$Lambda$1.lambdaFactory$(this)).addTo(this);
        }
    }

    public void changeFeeTypeIfPossible() {
        if (this.controller.getFeeType() == null) {
            updateShareButton();
            return;
        }
        setFeeType(this.controller.getFeeType());
        this.controller.setFeeType(null);
        updateShareButton();
    }

    /* access modifiers changed from: protected */
    public void updateShareButton() {
        this.listener.enableShareButton(getCohostingPaymentSettings().hasSetPayout());
    }

    private boolean hasPaidListingManager() {
        return this.controller.getPaidListingManager() != null;
    }

    private boolean hasPaidPendingInvite() {
        return this.controller.getPaidCohostInvitation() != null;
    }
}
