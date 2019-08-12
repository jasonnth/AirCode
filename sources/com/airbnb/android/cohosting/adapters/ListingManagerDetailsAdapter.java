package com.airbnb.android.cohosting.adapters;

import android.content.Context;
import android.support.p000v4.util.ArrayMap;
import android.text.SpannableString;
import android.view.View;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.controllers.CohostManagementDataController;
import com.airbnb.android.cohosting.utils.CohostingConstants;
import com.airbnb.android.cohosting.utils.CohostingUtil;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.intents.ThreadFragmentIntents;
import com.airbnb.android.core.models.CohostingContract;
import com.airbnb.android.core.models.CohostingNotification.MuteType;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.core.utils.MiscUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.ButtonBarEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InfoPanelRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.views.OptionsMenuFactory;
import com.airbnb.android.utils.CallHelper;
import com.airbnb.android.utils.EmailUtils;
import com.airbnb.android.utils.TextUtil;
import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.List;

public class ListingManagerDetailsAdapter extends AirEpoxyAdapter {
    private ButtonBarEpoxyModel_ buttonBar;
    private final Context context;
    private final CohostManagementDataController controller;
    private final ListingManager listingManager;
    CohostingManagementJitneyLogger logger;
    /* access modifiers changed from: private */
    public boolean showChatButton;
    /* access modifiers changed from: private */
    public boolean showContractRow;
    /* access modifiers changed from: private */
    public boolean showEmailButton;
    /* access modifiers changed from: private */
    public boolean showMakePrimaryHostRow;
    /* access modifiers changed from: private */
    public boolean showNotification;
    /* access modifiers changed from: private */
    public boolean showPhoneButton;
    private boolean showPrimaryHostInfoPanel;
    /* access modifiers changed from: private */
    public boolean showRemoveCohostRow;
    /* access modifiers changed from: private */
    public boolean showTransactionHistoryRow;
    /* access modifiers changed from: private */
    public boolean showTransferMoneyRow;

    public ListingManagerDetailsAdapter(Context context2, CohostManagementDataController controller2, String managerId) {
        super(true);
        ((CohostingGraph) CoreApplication.instance(context2).component()).inject(this);
        this.context = context2;
        this.controller = controller2;
        this.listingManager = controller2.getListingManager(managerId);
        setupHeaderRow();
        setupViewsAfterHeaderRow();
        this.logger.logListingManagerDetailsPageImpression(new ArrayMap<String, Boolean>() {
            {
                put(CohostingManagementJitneyLogger.SHOW_CHAT_BUTTON, Boolean.valueOf(ListingManagerDetailsAdapter.this.showChatButton));
                put(CohostingManagementJitneyLogger.SHOW_EMAIL_BUTTON, Boolean.valueOf(ListingManagerDetailsAdapter.this.showEmailButton));
                put(CohostingManagementJitneyLogger.SHOW_PHONE_BUTTON, Boolean.valueOf(ListingManagerDetailsAdapter.this.showPhoneButton));
                put(CohostingManagementJitneyLogger.SHOW_CONTRACT_ROW, Boolean.valueOf(ListingManagerDetailsAdapter.this.showContractRow));
                put(CohostingManagementJitneyLogger.SHOW_TRANSACTION_HISTORY_ROW, Boolean.valueOf(ListingManagerDetailsAdapter.this.showTransactionHistoryRow));
                put(CohostingManagementJitneyLogger.SHOW_TRANSFER_MONEY_ROW, Boolean.valueOf(ListingManagerDetailsAdapter.this.showTransferMoneyRow));
                put(CohostingManagementJitneyLogger.SHOW_MAKE_PRIMARY_HOST_ROW, Boolean.valueOf(ListingManagerDetailsAdapter.this.showMakePrimaryHostRow));
                put(CohostingManagementJitneyLogger.SHOW_REMOVE_COHOST_ROW, Boolean.valueOf(ListingManagerDetailsAdapter.this.showRemoveCohostRow));
                put(CohostingManagementJitneyLogger.SHOW_NOTIFICATION_ROW, Boolean.valueOf(ListingManagerDetailsAdapter.this.showNotification));
            }
        }, controller2.getCohostingContext(), this.listingManager);
    }

    private void setupViewsAfterHeaderRow() {
        setPermissionsForActions();
        if (this.showPrimaryHostInfoPanel) {
            setupPrimaryHostPanel();
        }
        if (this.showChatButton || this.showEmailButton || this.showPhoneButton) {
            setupButtonBar();
        }
        if (this.showContractRow) {
            setupSharedEarningRow();
        }
        if (this.showTransactionHistoryRow) {
            setupTransactionHistoryRow();
        }
        if (this.showTransferMoneyRow) {
            setupSendOrRequestMoneyRow();
        }
        if (this.showMakePrimaryHostRow) {
            setupMakePrimaryHostRow();
        }
        if (this.showNotification) {
            setupNotificationRow();
        }
        if (this.showRemoveCohostRow) {
            setupRemoveCohostRow();
        }
    }

    private void setPermissionsForActions() {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5 = false;
        this.showPrimaryHostInfoPanel = this.listingManager.isIsPrimaryHost().booleanValue();
        this.showChatButton = isListingAdminViewingOthers() || isCohostViewingListingAdmin();
        this.showEmailButton = isListingManagerViewingOthers();
        this.showPhoneButton = isListingManagerViewingOthers();
        if (isListingAdminViewingOthers() || isCohostViewingSelf()) {
            z = true;
        } else {
            z = false;
        }
        this.showContractRow = z;
        this.showTransactionHistoryRow = hasContract();
        if ((!isListingAdminViewingOthers() || !this.controller.canListingManagerAccessResolutionCenter(this.listingManager)) && (!isCohostViewingListingAdmin() || !this.controller.canCurrentUserAccessResolutionCenter())) {
            z2 = false;
        } else {
            z2 = true;
        }
        this.showTransferMoneyRow = z2;
        if (this.listingManager.isIsPrimaryHost().booleanValue() || !this.controller.isCurrentUserListingAdmin()) {
            z3 = false;
        } else {
            z3 = true;
        }
        this.showMakePrimaryHostRow = z3;
        if (!FeatureToggles.showCohostingNotification() || !this.listingManager.getId().equals(this.controller.getListingManagerIdOfCurrentUser()) || this.listingManager.isIsPrimaryHost().booleanValue()) {
            z4 = false;
        } else {
            z4 = true;
        }
        this.showNotification = z4;
        if (isListingAdminViewingOthers() || isCohostViewingSelf()) {
            z5 = true;
        }
        this.showRemoveCohostRow = z5;
    }

    private void setupHeaderRow() {
        String subTitle;
        DocumentMarqueeEpoxyModel_ headerRow = new DocumentMarqueeEpoxyModel_();
        if (this.listingManager.isIsListingAdmin().booleanValue()) {
            subTitle = this.context.getString(C5658R.string.cohosting_listing_admin_text);
        } else {
            subTitle = CohostingUtil.getCohostCreationTimeStr(this.context, this.listingManager.getCreatedAt(), this.controller);
        }
        headerRow.titleText((CharSequence) this.listingManager.getUser().getFirstName()).captionText((CharSequence) subTitle).userImageUrl(this.listingManager.getUser().getPictureUrl());
        addModel(headerRow);
    }

    private void setupPrimaryHostPanel() {
        boolean isListingAdminViewingSelf;
        boolean isNonListingAdminViewingSelf;
        SpannableString textWithLink;
        InfoPanelRowEpoxyModel_ infoPanel = new InfoPanelRowEpoxyModel_();
        String learnMoreText = this.context.getString(C5658R.string.learn_more_info_text);
        if (!this.controller.isCurrentUserListingAdmin() || !this.listingManager.isIsListingAdmin().booleanValue()) {
            isListingAdminViewingSelf = false;
        } else {
            isListingAdminViewingSelf = true;
        }
        if (this.controller.isCurrentUserListingAdmin() || !this.controller.getListingManagerIdOfCurrentUser().equals(this.listingManager.getId())) {
            isNonListingAdminViewingSelf = false;
        } else {
            isNonListingAdminViewingSelf = true;
        }
        if (isListingAdminViewingSelf) {
            textWithLink = new SpannableString(TextUtil.fromHtmlSafe(this.context.getString(C5658R.string.primary_host_explanation_listing_admin_sees_themself, new Object[]{learnMoreText})));
        } else if (isNonListingAdminViewingSelf) {
            textWithLink = new SpannableString(TextUtil.fromHtmlSafe(this.context.getString(C5658R.string.primary_host_explanation_cohost_see_themself, new Object[]{learnMoreText})));
        } else {
            textWithLink = new SpannableString(TextUtil.fromHtmlSafe(this.context.getString(C5658R.string.primary_host_explanation_see_others, new Object[]{this.listingManager.getUser().getFirstName(), this.listingManager.getUser().getFirstName(), learnMoreText})));
        }
        infoPanel.titleRes(C5658R.string.primary_host_title).content(textWithLink.toString()).linkText(learnMoreText).linkOnClickListener(ListingManagerDetailsAdapter$$Lambda$1.lambdaFactory$(this));
        addModel(infoPanel);
    }

    private void setupButtonBar() {
        this.buttonBar = new ButtonBarEpoxyModel_();
        if (this.showChatButton) {
            setupChatButton();
        }
        if (this.showEmailButton) {
            setupEmailButton();
        }
        if (this.showPhoneButton) {
            setupPhoneButton();
        }
        addModel(this.buttonBar);
    }

    private void setupChatButton() {
        this.buttonBar.addButton(C5658R.string.chat, C5658R.C5659drawable.icon_line_message, ListingManagerDetailsAdapter$$Lambda$2.lambdaFactory$(this, this.listingManager.getThreadId()));
    }

    static /* synthetic */ void lambda$setupChatButton$1(ListingManagerDetailsAdapter listingManagerDetailsAdapter, long threadId, View v) {
        listingManagerDetailsAdapter.logger.logChatButtonClicked(listingManagerDetailsAdapter.controller.getCohostingContext(), listingManagerDetailsAdapter.listingManager);
        listingManagerDetailsAdapter.context.startActivity(ThreadFragmentIntents.newIntent(listingManagerDetailsAdapter.context, threadId, InboxType.Host));
    }

    private void setupEmailButton() {
        this.buttonBar.addButton(C5658R.string.email, C5658R.C5659drawable.icon_line_email, ListingManagerDetailsAdapter$$Lambda$3.lambdaFactory$(this, this.listingManager.getUser().getEmailAddress()));
    }

    static /* synthetic */ void lambda$setupEmailButton$2(ListingManagerDetailsAdapter listingManagerDetailsAdapter, String managerEmail, View v) {
        listingManagerDetailsAdapter.logger.logEmailButtonClicked(listingManagerDetailsAdapter.controller.getCohostingContext(), listingManagerDetailsAdapter.listingManager);
        EmailUtils.send(listingManagerDetailsAdapter.context, managerEmail, listingManagerDetailsAdapter.context.getString(C5658R.string.contact_from_airbnb), null);
    }

    private void setupPhoneButton() {
        this.buttonBar.addButton(C5658R.string.phone, C5658R.C5659drawable.icon_line_phone, ListingManagerDetailsAdapter$$Lambda$4.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setupPhoneButton$3(ListingManagerDetailsAdapter listingManagerDetailsAdapter, View v) {
        listingManagerDetailsAdapter.logger.logPhoneButtonClicked(listingManagerDetailsAdapter.controller.getCohostingContext(), listingManagerDetailsAdapter.listingManager);
        listingManagerDetailsAdapter.openPhoneOptions();
    }

    private void setupSharedEarningRow() {
        CohostingContract contract = this.listingManager.getContract();
        if (!hasContract() && this.controller.isCurrentUserListingAdmin()) {
            displayNonMarketplaceContractRowWhenOff();
        } else if (hasContract() && contract.getSource() == 2) {
            displayMarketplaceContractRow();
        } else if (hasContract()) {
            displayNonMarketplaceContractRowWhenOn();
        }
    }

    private void setupTransactionHistoryRow() {
        StandardRowEpoxyModel_ transactionHistoryRow = new StandardRowEpoxyModel_();
        transactionHistoryRow.title(C5658R.string.cohosting_transaction_history_link_text).disclosure().clickListener(ListingManagerDetailsAdapter$$Lambda$5.lambdaFactory$(this));
        addModel(transactionHistoryRow);
    }

    static /* synthetic */ void lambda$setupTransactionHistoryRow$4(ListingManagerDetailsAdapter listingManagerDetailsAdapter, View v) {
        listingManagerDetailsAdapter.logger.logTransactionHistoryRowClicked(listingManagerDetailsAdapter.controller.getCohostingContext(), listingManagerDetailsAdapter.listingManager);
        listingManagerDetailsAdapter.context.startActivity(ReactNativeIntents.intentForHostStatsTransactions(listingManagerDetailsAdapter.context));
    }

    private void setupSendOrRequestMoneyRow() {
        StandardRowEpoxyModel_ sendOrRequestMoneyRow = new StandardRowEpoxyModel_();
        sendOrRequestMoneyRow.title(C5658R.string.cohosting_send_or_request_money_link_text).disclosure().clickListener(ListingManagerDetailsAdapter$$Lambda$6.lambdaFactory$(this));
        addModel(sendOrRequestMoneyRow);
    }

    static /* synthetic */ void lambda$setupSendOrRequestMoneyRow$5(ListingManagerDetailsAdapter listingManagerDetailsAdapter, View v) {
        listingManagerDetailsAdapter.logger.logResolutionCenterRowClicked(listingManagerDetailsAdapter.controller.getCohostingContext(), listingManagerDetailsAdapter.listingManager);
        CohostingUtil.goToResolutionCenter(listingManagerDetailsAdapter.context);
    }

    private void setupMakePrimaryHostRow() {
        StandardRowEpoxyModel_ makePrimaryHostRow = new StandardRowEpoxyModel_();
        makePrimaryHostRow.title(C5658R.string.make_primary_host_link_text).clickListener(ListingManagerDetailsAdapter$$Lambda$7.lambdaFactory$(this));
        addModel(makePrimaryHostRow);
    }

    static /* synthetic */ void lambda$setupMakePrimaryHostRow$6(ListingManagerDetailsAdapter listingManagerDetailsAdapter, View v) {
        listingManagerDetailsAdapter.logger.logMakePrimaryHostRowClicked(listingManagerDetailsAdapter.controller.getCohostingContext(), listingManagerDetailsAdapter.listingManager);
        listingManagerDetailsAdapter.controller.actionExecutor.makePrimaryHost(listingManagerDetailsAdapter.listingManager.getId());
    }

    private void setupNotificationRow() {
        StandardRowEpoxyModel_ notificationRow = new StandardRowEpoxyModel_();
        notificationRow.title(C5658R.string.cohosting_notification).clickListener(ListingManagerDetailsAdapter$$Lambda$8.lambdaFactory$(this));
        if (this.controller.isCurrentUserListingAdmin()) {
            notificationRow.actionText(this.controller.getMuteType() == MuteType.UNMUTED ? C5658R.string.cohosting_notification_info_row_all : C5658R.string.cohosting_notification_info_row_monthly);
        } else {
            notificationRow.actionText(this.controller.getMuteType() == MuteType.UNMUTED ? C5658R.string.info_row_switch_on : C5658R.string.info_row_switch_off);
        }
        addModel(notificationRow);
    }

    static /* synthetic */ void lambda$setupNotificationRow$7(ListingManagerDetailsAdapter listingManagerDetailsAdapter, View v) {
        listingManagerDetailsAdapter.logger.logNotificationRowClicked(listingManagerDetailsAdapter.controller.getCohostingContext(), listingManagerDetailsAdapter.listingManager);
        listingManagerDetailsAdapter.controller.actionExecutor.setNotification(listingManagerDetailsAdapter.listingManager.getId());
    }

    private void setupRemoveCohostRow() {
        LinkActionRowEpoxyModel_ removeCohostRow = new LinkActionRowEpoxyModel_();
        removeCohostRow.textRes(this.controller.isCurrentUserListingAdmin() ? C5658R.string.cohosting_remove_cohost_link_text : C5658R.string.cohosting_remove_yourself_link_text).clickListener(ListingManagerDetailsAdapter$$Lambda$9.lambdaFactory$(this));
        addModel(removeCohostRow);
    }

    static /* synthetic */ void lambda$setupRemoveCohostRow$8(ListingManagerDetailsAdapter listingManagerDetailsAdapter, View v) {
        listingManagerDetailsAdapter.logger.logRemoveCohostRowClicked(listingManagerDetailsAdapter.controller.getCohostingContext(), listingManagerDetailsAdapter.listingManager);
        listingManagerDetailsAdapter.controller.actionExecutor.removeCohost(listingManagerDetailsAdapter.listingManager);
    }

    private void displayNonMarketplaceContractRowWhenOff() {
        StandardRowEpoxyModel_ sharedEarningRow = new StandardRowEpoxyModel_();
        sharedEarningRow.title(C5658R.string.cohosting_shared_earnings_title).infoText(C5658R.string.info_row_switch_off).clickListener(ListingManagerDetailsAdapter$$Lambda$10.lambdaFactory$(this));
        addModel(sharedEarningRow);
    }

    static /* synthetic */ void lambda$displayNonMarketplaceContractRowWhenOff$9(ListingManagerDetailsAdapter listingManagerDetailsAdapter, View v) {
        listingManagerDetailsAdapter.logger.logOpenShareEarningsModal(listingManagerDetailsAdapter.controller.getCohostingContext(), listingManagerDetailsAdapter.listingManager);
        listingManagerDetailsAdapter.controller.actionExecutor.shareEarnings(listingManagerDetailsAdapter.listingManager.getId());
    }

    private void displayMarketplaceContractRow() {
        StandardRowEpoxyModel_ sharedEarningRow = new StandardRowEpoxyModel_();
        sharedEarningRow.title(C5658R.string.cohosting_services_and_fees_title).clickListener(ListingManagerDetailsAdapter$$Lambda$11.lambdaFactory$(this)).disclosure();
        displayContractItems(sharedEarningRow, this.listingManager.getContract());
        addModel(sharedEarningRow);
    }

    static /* synthetic */ void lambda$displayMarketplaceContractRow$10(ListingManagerDetailsAdapter listingManagerDetailsAdapter, View v) {
        listingManagerDetailsAdapter.logger.logOpenContractDetailPage(listingManagerDetailsAdapter.controller.getCohostingContext(), listingManagerDetailsAdapter.listingManager);
        listingManagerDetailsAdapter.controller.actionExecutor.cohostingContractDetails(listingManagerDetailsAdapter.listingManager.getContract().getId(), listingManagerDetailsAdapter.listingManager.getId());
    }

    private void displayNonMarketplaceContractRowWhenOn() {
        StandardRowEpoxyModel_ sharedEarningRow = new StandardRowEpoxyModel_();
        sharedEarningRow.title(C5658R.string.cohosting_shared_earnings_title);
        if (this.controller.isCurrentUserListingAdmin()) {
            sharedEarningRow.actionText(C5658R.string.info_row_switch_on).clickListener(ListingManagerDetailsAdapter$$Lambda$12.lambdaFactory$(this));
        }
        displayContractItems(sharedEarningRow, this.listingManager.getContract());
        addModel(sharedEarningRow);
    }

    static /* synthetic */ void lambda$displayNonMarketplaceContractRowWhenOn$11(ListingManagerDetailsAdapter listingManagerDetailsAdapter, View v) {
        listingManagerDetailsAdapter.logger.logOpenStopShareEarningsModal(listingManagerDetailsAdapter.controller.getCohostingContext(), listingManagerDetailsAdapter.listingManager);
        listingManagerDetailsAdapter.controller.actionExecutor.stopShareEarnings(listingManagerDetailsAdapter.listingManager.getId());
    }

    private List<String> setupServicesAndFeesDetail(CohostingContract contract) {
        String string;
        List<String> contractFees = new ArrayList<>();
        if (contract.getFormattedHostingFee(this.context) != null) {
            contractFees.add(this.context.getString(C5658R.string.cohosting_share_of_earnings, new Object[]{contract.getFormattedHostingFee(this.context)}));
        }
        if (contract.getMinMaxFeeStr(this.context) != null) {
            contractFees.add(contract.getMinMaxFeeStr(this.context));
        }
        if (contract.isIncludeCleaningFee().booleanValue()) {
            if (this.controller.getListingManagerIdOfCurrentUser().equals(this.listingManager.getId())) {
                string = this.context.getString(C5658R.string.cohosting_cleaning_fees_for_yourself);
            } else {
                string = this.context.getString(C5658R.string.cohosting_cleaning_fees, new Object[]{this.listingManager.getUser().getFirstName()});
            }
            contractFees.add(string);
        }
        contractFees.add(String.format(this.context.getString(C5658R.string.cohosting_contract_start_time), new Object[]{contract.getStartDate().getDateString(this.context)}));
        if (contract.getSource() == 2) {
            if (contract.getEndDate() != null) {
                contractFees.add(String.format(this.context.getString(C5658R.string.cohosting_contract_end_time), new Object[]{contract.getEndDate().getDateString(this.context)}));
            } else {
                contractFees.add(this.context.getString(C5658R.string.cohosting_contract_end_time_ongoing));
            }
        }
        return contractFees;
    }

    private void displayContractItems(StandardRowEpoxyModel_ sharedEarningRow, CohostingContract contract) {
        List<String> contractFees = setupServicesAndFeesDetail(contract);
        sharedEarningRow.subTitleMaxLine(contractFees.size()).subtitle((CharSequence) Joiner.m1896on("\n").join((Iterable<?>) contractFees));
    }

    private void openPhoneOptions() {
        String phoneNum = this.listingManager.getUser().getPhone();
        OptionsMenuFactory.forItems(this.context, (T[]) CohostingConstants.getPhoneOperations()).setTitleTransformer(ListingManagerDetailsAdapter$$Lambda$13.lambdaFactory$(this, phoneNum)).setListener(ListingManagerDetailsAdapter$$Lambda$14.lambdaFactory$(this, phoneNum)).buildAndShow();
    }

    /* access modifiers changed from: private */
    public String getPhoneOperationText(String operation, String phoneNum) {
        char c = 65535;
        switch (operation.hashCode()) {
            case 3045982:
                if (operation.equals("call")) {
                    c = 0;
                    break;
                }
                break;
            case 3059573:
                if (operation.equals(CohostingConstants.COPY)) {
                    c = 2;
                    break;
                }
                break;
            case 3556653:
                if (operation.equals("text")) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return this.context.getString(C5658R.string.call);
            case 1:
                return this.context.getString(C5658R.string.message);
            case 2:
                return this.context.getString(C5658R.string.copy_phone_number, new Object[]{phoneNum});
            default:
                throw new UnsupportedOperationException(operation);
        }
    }

    /* access modifiers changed from: private */
    public void handlePhoneOptionClick(String operation, String phoneNum) {
        char c = 65535;
        switch (operation.hashCode()) {
            case 3045982:
                if (operation.equals("call")) {
                    c = 0;
                    break;
                }
                break;
            case 3059573:
                if (operation.equals(CohostingConstants.COPY)) {
                    c = 2;
                    break;
                }
                break;
            case 3556653:
                if (operation.equals("text")) {
                    c = 1;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                CallHelper.dial(this.context, phoneNum);
                return;
            case 1:
                CallHelper.text(this.context, phoneNum);
                return;
            case 2:
                MiscUtils.copyToClipboard(this.context, phoneNum);
                return;
            default:
                throw new UnsupportedOperationException(operation);
        }
    }

    private boolean isListingAdminViewingSelf() {
        return this.controller.isCurrentUserListingAdmin() && this.controller.getListingManagerIdOfCurrentUser().equals(this.listingManager.getId());
    }

    private boolean isListingAdminViewingOthers() {
        return this.controller.isCurrentUserListingAdmin() && !this.controller.getListingManagerIdOfCurrentUser().equals(this.listingManager.getId());
    }

    private boolean isCohostViewingSelf() {
        return !this.controller.isCurrentUserListingAdmin() && this.controller.getListingManagerIdOfCurrentUser().equals(this.listingManager.getId());
    }

    private boolean isCohostViewingListingAdmin() {
        return !this.controller.isCurrentUserListingAdmin() && this.listingManager.isIsListingAdmin().booleanValue();
    }

    private boolean isListingManagerViewingOthers() {
        return !isListingAdminViewingSelf() && !isCohostViewingSelf();
    }

    private boolean hasContract() {
        return this.listingManager.getContract() != null;
    }
}
