package com.airbnb.android.core.analytics;

import android.support.p000v4.util.ArrayMap;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.models.CohostingContract;
import com.airbnb.android.core.models.CohostingNotification.MuteType;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.jitney.event.logging.Cohosting.p059v1.CohostingSetPrimaryHostManageListingEvent;
import com.airbnb.jitney.event.logging.Cohosting.p059v1.CohostingShareEarningManageListingEvent;
import com.airbnb.jitney.event.logging.Cohosting.p059v1.CohostingUpdateNotificationManageListingEvent;
import com.airbnb.jitney.event.logging.Cohosting.p060v2.CohostingClickManageListingEvent;
import com.airbnb.jitney.event.logging.Cohosting.p060v2.CohostingCohostTabClickManageListingEvent;
import com.airbnb.jitney.event.logging.Cohosting.p060v2.CohostingCohostTabImpressionManageListingEvent.Builder;
import com.airbnb.jitney.event.logging.Cohosting.p060v2.CohostingImpressionListingManagerDetailEvent;
import com.airbnb.jitney.event.logging.Cohosting.p060v2.CohostingImpressionManageListingEvent;
import com.airbnb.jitney.event.logging.Cohosting.p061v3.CohostingInviteManageListingEvent;
import com.airbnb.jitney.event.logging.CohostingContext.p062v1.C1951CohostingContext;
import com.airbnb.jitney.event.logging.CohostingManageListingClickTarget.p065v2.C1955CohostingManageListingClickTarget;
import com.airbnb.jitney.event.logging.CohostingManageListingPage.p067v2.C1957CohostingManageListingPage;
import com.airbnb.jitney.event.logging.CohostingSourceFlow.p068v1.C1958CohostingSourceFlow;
import com.airbnb.jitney.event.logging.ListingManagerAttribute.p135v1.C2371ListingManagerAttribute;

public class CohostingManagementJitneyLogger extends BaseLogger {
    public static final String AMOUNT_CURRENCY = "amount_currency";
    public static final String FIXED_FEE = "fixed_fee";
    public static final String INVITED_USER_EMAIL = "invited_user_email";
    public static final String MINIMUM_FEE = "minimum_fee";
    public static final String PAY_CLEANING_FEES = "pay_cleaning_fees";
    public static final String PERCENTAGE_OF_SHARED_EARNINGS = "percent_of_shared_earnings";
    public static final String SHOW_CHAT_BUTTON = "show_chat_button";
    public static final String SHOW_CONTRACT_ROW = "show_contract_row";
    public static final String SHOW_EMAIL_BUTTON = "show_email_button";
    public static final String SHOW_MAKE_PRIMARY_HOST_ROW = "show_make_primary_host_row";
    public static final String SHOW_NOTIFICATION_ROW = "show_notification_row";
    public static final String SHOW_PHONE_BUTTON = "show_phone_button";
    public static final String SHOW_REMOVE_COHOST_ROW = "show_remove_cohost_row";
    public static final String SHOW_TRANSACTION_HISTORY_ROW = "show_transaction_history_row";
    public static final String SHOW_TRANSFER_MONEY_ROW = "show_transfer_money_row";

    public CohostingManagementJitneyLogger(LoggingContextFactory loggingContextFactory) {
        super(loggingContextFactory);
    }

    public void logCohostsTabInManageListingSettingPageImpressionForDlsML(long listingId) {
        publish(new Builder(context(), Long.valueOf(listingId)));
    }

    public void logListingManagersPickerPageImpression(C1951CohostingContext context) {
        logImpression(C1957CohostingManageListingPage.ListingManagerPicker, context);
    }

    public void logCohostsServiceInfoPageImpression(C1951CohostingContext context, C1958CohostingSourceFlow sourceFlow) {
        logImpression(C1957CohostingManageListingPage.WhatCanCohostsDoModal, getLoggingCohostingContext(null, sourceFlow, context));
    }

    public void logMakePrimaryHostModalImpression(C1951CohostingContext context, ListingManager manager) {
        logImpression(C1957CohostingManageListingPage.PrimaryHostModal, getLoggingCohostingContext(manager, null, context));
    }

    public void logNotificationSettingPageImpression(C1951CohostingContext context, ListingManager manager) {
        logImpression(C1957CohostingManageListingPage.NotificationDetailPage, getLoggingCohostingContext(manager, null, context));
    }

    public void logRemoveCohostModalImpression(C1951CohostingContext context, ListingManager manager) {
        logImpression(C1957CohostingManageListingPage.RemoveCohostModal, getLoggingCohostingContext(manager, null, context));
    }

    public void logCohostResignModalImpression(C1951CohostingContext context, ListingManager manager) {
        logImpression(C1957CohostingManageListingPage.CohostResignModal, getLoggingCohostingContext(manager, null, context));
    }

    public void logInviteAFriendIntroPageImpression(C1951CohostingContext context, C1958CohostingSourceFlow sourceFlow) {
        logImpression(C1957CohostingManageListingPage.InviteAFriendIntro, getLoggingCohostingContext(null, sourceFlow, context));
    }

    public void logListingManagerDetailsPageImpression(ArrayMap<String, Boolean> loggingParams, C1951CohostingContext context, ListingManager manager) {
        publish(new CohostingImpressionListingManagerDetailEvent.Builder(context(), (Boolean) loggingParams.get(SHOW_CHAT_BUTTON), (Boolean) loggingParams.get(SHOW_EMAIL_BUTTON), (Boolean) loggingParams.get(SHOW_PHONE_BUTTON), (Boolean) loggingParams.get(SHOW_CONTRACT_ROW), (Boolean) loggingParams.get(SHOW_TRANSACTION_HISTORY_ROW), (Boolean) loggingParams.get(SHOW_TRANSFER_MONEY_ROW), (Boolean) loggingParams.get(SHOW_MAKE_PRIMARY_HOST_ROW), (Boolean) loggingParams.get(SHOW_REMOVE_COHOST_ROW), getLoggingCohostingContext(manager, null, context), (Boolean) loggingParams.get(SHOW_NOTIFICATION_ROW)));
    }

    public void logInviteModalImpression(C1951CohostingContext context, C1958CohostingSourceFlow sourceFlow) {
        logImpression(C1957CohostingManageListingPage.InvitationModal, getLoggingCohostingContext(null, sourceFlow, context));
    }

    public void logContractExistAlertImpression(C1951CohostingContext context, ListingManager manager) {
        logImpression(C1957CohostingManageListingPage.ContractExistAlert, getLoggingCohostingContext(manager, null, context));
    }

    public void logContractDetailPageImpression(C1951CohostingContext context, ListingManager manager) {
        logImpression(C1957CohostingManageListingPage.ContractDetailPage, getLoggingCohostingContext(manager, null, context));
    }

    public void logShareEarningsModalImpression(C1951CohostingContext context, ListingManager manager) {
        logImpression(C1957CohostingManageListingPage.ShareEarningsModal, getLoggingCohostingContext(manager, null, context));
    }

    public void logStopShareEarningsModalImpression(C1951CohostingContext context, ListingManager manager) {
        logImpression(C1957CohostingManageListingPage.StopShareEarningsModal, getLoggingCohostingContext(manager, null, context));
    }

    public void logPendingCohostDetailsPageImpression(C1951CohostingContext context) {
        logImpression(C1957CohostingManageListingPage.PendingCohostsDetailPage, context);
    }

    public void logInviteFriendConfirmationImpression(C1951CohostingContext context, C1958CohostingSourceFlow sourceFlow) {
        logImpression(C1957CohostingManageListingPage.InvitationSentConfirmationPage, getLoggingCohostingContext(null, sourceFlow, context));
    }

    public void logCohostsTabInManageListingSettingClickedForDlsML(long listingId) {
        publish(new CohostingCohostTabClickManageListingEvent.Builder(context(), Long.valueOf(listingId)));
    }

    public void logInviteButtonFromListingManagerPickerPageClicked(C1951CohostingContext context) {
        logClick(C1955CohostingManageListingClickTarget.InviteButtonFromListingManagerPickerPage, context);
    }

    public void logInviteButtonFromIntroPageClicked(C1951CohostingContext context, C1958CohostingSourceFlow sourceFlow) {
        logClick(C1955CohostingManageListingClickTarget.InviteButtonFromIntroPage, getLoggingCohostingContext(null, sourceFlow, context));
    }

    public void logAddressBookButtonClicked(C1951CohostingContext context, C1958CohostingSourceFlow sourceFlow) {
        logClick(C1955CohostingManageListingClickTarget.AddressBookButton, getLoggingCohostingContext(null, sourceFlow, context));
    }

    public void logInvitationModalDismissed(C1951CohostingContext context, C1958CohostingSourceFlow sourceFlow) {
        logClick(C1955CohostingManageListingClickTarget.DismissInvitationModal, getLoggingCohostingContext(null, sourceFlow, context));
    }

    public void logWhatCanCohostsDoLinkClicked(C1951CohostingContext context, C1958CohostingSourceFlow sourceFlow) {
        logClick(C1955CohostingManageListingClickTarget.WhatCanCohostsDoLink, getLoggingCohostingContext(null, sourceFlow, context));
    }

    public void logWhatCanCohostsDoModalDismissed(C1951CohostingContext context, C1958CohostingSourceFlow sourceFlow) {
        logClick(C1955CohostingManageListingClickTarget.DismissWhatCanCohostsDoModal, getLoggingCohostingContext(null, sourceFlow, context));
    }

    public void logPendingInvitationRowClicked(C1951CohostingContext context) {
        logClick(C1955CohostingManageListingClickTarget.PendingInvitationRow, context);
    }

    public void logReinviteButtonClicked(C1951CohostingContext context) {
        logClick(C1955CohostingManageListingClickTarget.ReinviteButton, context);
    }

    public void logCancelInviteClicked(C1951CohostingContext context) {
        logClick(C1955CohostingManageListingClickTarget.CancelInvite, context);
    }

    public void logListingManagerRowClicked(C1951CohostingContext context, ListingManager manager) {
        logClick(C1955CohostingManageListingClickTarget.ListingManagerRow, getLoggingCohostingContext(manager, null, context));
    }

    public void logChatButtonClicked(C1951CohostingContext context, ListingManager manager) {
        logClick(C1955CohostingManageListingClickTarget.ChatButton, getLoggingCohostingContext(manager, null, context));
    }

    public void logEmailButtonClicked(C1951CohostingContext context, ListingManager manager) {
        logClick(C1955CohostingManageListingClickTarget.EmailButton, getLoggingCohostingContext(manager, null, context));
    }

    public void logPhoneButtonClicked(C1951CohostingContext context, ListingManager manager) {
        logClick(C1955CohostingManageListingClickTarget.PhoneButton, getLoggingCohostingContext(manager, null, context));
    }

    public void logOpenContractDetailPage(C1951CohostingContext context, ListingManager manager) {
        logClick(C1955CohostingManageListingClickTarget.OpenContractDetailPage, getLoggingCohostingContext(manager, null, context));
    }

    public void logOpenShareEarningsModal(C1951CohostingContext context, ListingManager manager) {
        logClick(C1955CohostingManageListingClickTarget.StartShareEarningsModal, getLoggingCohostingContext(manager, null, context));
    }

    public void logOpenStopShareEarningsModal(C1951CohostingContext context, ListingManager manager) {
        logClick(C1955CohostingManageListingClickTarget.StopShareEarningsModal, getLoggingCohostingContext(manager, null, context));
    }

    public void logShareEarningsButtonClicked(ArrayMap<String, Object> loggingParams, C1951CohostingContext context) {
        publish(new CohostingShareEarningManageListingEvent.Builder(context(), Long.valueOf(Long.valueOf((long) ((Integer) loggingParams.get("percent_of_shared_earnings")).intValue()).longValue()), Boolean.valueOf(((Boolean) loggingParams.get("pay_cleaning_fees")).booleanValue()), Long.valueOf(0), Long.valueOf(0), Long.valueOf(0), "", context));
    }

    public void logShareEarningsModalDismissed(C1951CohostingContext context, ListingManager manager) {
        logClick(C1955CohostingManageListingClickTarget.DismissStartShareEarningsModal, getLoggingCohostingContext(manager, null, context));
    }

    public void logStopShareEarningsButtonClicked(C1951CohostingContext context, ListingManager manager) {
        publish(new CohostingClickManageListingEvent.Builder(context(), C1955CohostingManageListingClickTarget.StopShareEarningsButton, getLoggingCohostingContext(manager, null, context)));
    }

    public void logInviteFriendConfirmationClick(C1951CohostingContext context, C1958CohostingSourceFlow sourceFlow) {
        logClick(C1955CohostingManageListingClickTarget.InvitationSentConfirmationPage, getLoggingCohostingContext(null, sourceFlow, context));
    }

    public void logStopShareEarningsModalDismissed(C1951CohostingContext context, ListingManager manager) {
        publish(new CohostingClickManageListingEvent.Builder(context(), C1955CohostingManageListingClickTarget.DismissStopShareEarningsModal, getLoggingCohostingContext(manager, null, context)));
    }

    public void logTransactionHistoryRowClicked(C1951CohostingContext context, ListingManager manager) {
        logClick(C1955CohostingManageListingClickTarget.TransactionHistoryRow, getLoggingCohostingContext(manager, null, context));
    }

    public void logResolutionCenterRowClicked(C1951CohostingContext context, ListingManager manager) {
        logClick(C1955CohostingManageListingClickTarget.ResolutionCenterRow, getLoggingCohostingContext(manager, null, context));
    }

    public void logMakePrimaryHostRowClicked(C1951CohostingContext context, ListingManager manager) {
        logClick(C1955CohostingManageListingClickTarget.MakePrimaryHostRow, getLoggingCohostingContext(manager, null, context));
    }

    public void logNotificationRowClicked(C1951CohostingContext context, ListingManager manager) {
        logClick(C1955CohostingManageListingClickTarget.NotificationRow, getLoggingCohostingContext(manager, null, context));
    }

    public void logSetPrimaryHostButtonClicked(C1951CohostingContext context, ListingManager manager, MuteType muteType) {
        if (muteType == null) {
            logClick(C1955CohostingManageListingClickTarget.SetPrimaryHostButton, getLoggingCohostingContext(manager, null, context));
        } else {
            publish(new CohostingSetPrimaryHostManageListingEvent.Builder(context(), Long.valueOf((long) muteType.ordinal()), getLoggingCohostingContext(manager, null, context)));
        }
    }

    public void logSaveNotificationSettingButtonClicked(C1951CohostingContext context, ListingManager manager, MuteType muteType) {
        publish(new CohostingUpdateNotificationManageListingEvent.Builder(context(), Long.valueOf((long) muteType.ordinal()), getLoggingCohostingContext(manager, null, context)));
    }

    public void logRemoveCohostRowClicked(C1951CohostingContext context, ListingManager manager) {
        logClick(C1955CohostingManageListingClickTarget.RemoveCohostRow, getLoggingCohostingContext(manager, null, context));
    }

    public void logRemoveCohostButtonClicked(C1951CohostingContext context, ListingManager manager) {
        logClick(C1955CohostingManageListingClickTarget.RemoveCohostButton, getLoggingCohostingContext(manager, null, context));
    }

    public void logRemoveCohostModalDismissed(C1951CohostingContext context, ListingManager manager) {
        logClick(C1955CohostingManageListingClickTarget.DismissRemoveCohostModal, getLoggingCohostingContext(manager, null, context));
    }

    public void logInviteButtonFromInvitationPageClicked(ArrayMap<String, Object> loggingParams, C1958CohostingSourceFlow sourceFlow, C1951CohostingContext context) {
        String email = (String) loggingParams.get(INVITED_USER_EMAIL);
        long percentage = Long.valueOf((long) ((Integer) loggingParams.get("percent_of_shared_earnings")).intValue()).longValue();
        long minimumFee = Long.valueOf((long) ((Integer) loggingParams.get("minimum_fee")).intValue()).longValue();
        long fixedFee = Long.valueOf((long) ((Integer) loggingParams.get("fixed_fee")).intValue()).longValue();
        String amountCurrency = (String) loggingParams.get("amount_currency");
        boolean payCleaningFees = ((Boolean) loggingParams.get("pay_cleaning_fees")).booleanValue();
        publish(new CohostingInviteManageListingEvent.Builder(context(), email, Long.valueOf(percentage), Boolean.valueOf(payCleaningFees), Long.valueOf(fixedFee), Long.valueOf(0), Long.valueOf(minimumFee), amountCurrency, getLoggingCohostingContext(null, sourceFlow, context)));
    }

    private void logImpression(C1957CohostingManageListingPage page, C1951CohostingContext context) {
        publish(new CohostingImpressionManageListingEvent.Builder(context(), page, context));
    }

    private void logClick(C1955CohostingManageListingClickTarget target, C1951CohostingContext context) {
        publish(new CohostingClickManageListingEvent.Builder(context(), target, context));
    }

    private C1951CohostingContext getLoggingCohostingContext(ListingManager manager, C1958CohostingSourceFlow sourceFlow, C1951CohostingContext context) {
        if (manager != null) {
            context = getCohostingContextWithListingManagerInfo(context, manager);
        }
        if (sourceFlow != null) {
            return getCohostingContextWithSourceFlow(context, sourceFlow);
        }
        return context;
    }

    private C1951CohostingContext getCohostingContextWithSourceFlow(C1951CohostingContext context, C1958CohostingSourceFlow sourceFlow) {
        return new C1951CohostingContext.Builder(context).source_flow(sourceFlow).build();
    }

    private C1951CohostingContext getCohostingContextWithListingManagerInfo(C1951CohostingContext context, ListingManager listingManager) {
        C2371ListingManagerAttribute.Builder builder = new C2371ListingManagerAttribute.Builder(Long.valueOf(listingManager.getUser().getId()));
        builder.is_primary_host(listingManager.isIsPrimaryHost());
        if (!listingManager.isIsListingAdmin().booleanValue()) {
            builder.acceptance_date(listingManager.getCreatedAt().getIsoDateString());
        }
        CohostingContract contract = listingManager.getContract();
        if (contract != null) {
            builder.contract_percent(Long.valueOf((long) contract.getPercentage())).contract_cleaning_fee(contract.isIncludeCleaningFee()).fixed_fee(Long.valueOf(contract.getFixedAmount())).maximum_fee(Long.valueOf(contract.getMaximumFee())).minimum_fee(Long.valueOf(contract.getMinimumFee())).amount_currency(contract.getAmountCurrency());
            if (contract.getStartDate() != null) {
                builder.contract_start_date(contract.getStartDate().getIsoDateString());
            }
            if (contract.getEndDate() != null) {
                builder.contract_end_date(contract.getEndDate().getIsoDateString());
            }
        }
        return new C1951CohostingContext.Builder(context).listing_manager_attribute(builder.build()).build();
    }
}
