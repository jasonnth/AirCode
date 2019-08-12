package com.airbnb.android.cohosting.adapters;

import android.content.Context;
import android.os.Bundle;
import android.util.Patterns;
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
import com.airbnb.android.core.viewcomponents.models.InlineInputWithContactPickerRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SwitchRowEpoxyModel_;
import com.airbnb.android.utils.TextWatcherUtils;
import com.airbnb.p027n2.components.SwitchStyle;
import com.google.common.base.Strings;
import icepick.State;
import org.spongycastle.crypto.tls.CipherSuite;

public class CohostingInviteFriendAdapter extends AirEpoxyAdapter {
    private final Context context;
    private final CohostManagementDataController controller;
    @State
    String email;
    private final InlineInputWithContactPickerRowEpoxyModel_ emailRow = new InlineInputWithContactPickerRowEpoxyModel_();
    private final DocumentMarqueeEpoxyModel_ headerRow = new DocumentMarqueeEpoxyModel_();
    @State
    boolean includeCleaningFee;
    Listener listener;
    CohostingManagementJitneyLogger logger;
    private final InlineFormattedIntegerInputRowEpoxyModel_ percentRow = InlineFormattedIntegerInputRowEpoxyModel_.forIntegerPercentage();
    @State
    int percentage;
    private final SectionHeaderEpoxyModel_ shareEarningsHeader = new SectionHeaderEpoxyModel_();
    private final SimpleTextRowEpoxyModel_ shareEarningsIntro = new SimpleTextRowEpoxyModel_();
    private final SwitchRowEpoxyModel_ switchRow = new SwitchRowEpoxyModel_();
    private final SimpleTextRowEpoxyModel_ terms = new SimpleTextRowEpoxyModel_();
    private final SimpleTextRowEpoxyModel_ warningRow = new SimpleTextRowEpoxyModel_();

    public interface Listener {
        void enableInviteButton(boolean z);

        void openTermsOfServiceLink();

        void pickContact();
    }

    public CohostingInviteFriendAdapter(Context context2, Listener listener2, CohostManagementDataController controller2, Bundle savedInstanceState) {
        this.context = context2;
        this.listener = listener2;
        this.controller = controller2;
        if (savedInstanceState == null) {
            this.email = "";
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
        setupEmailRow();
        setupShareEarningsHeader();
        setupShareEarningsIntro();
        setupPercentRow();
        setupSwitchRow();
        setupWarningRow();
        setupTermsRow();
    }

    private void setupHeader() {
        this.headerRow.titleRes(C5658R.string.cohosting_invite_friend);
        addModel(this.headerRow);
    }

    private void setupEmailRow() {
        this.emailRow.titleRes(C5658R.string.cohosting_invite_a_friend_email_address).input(this.email).inputType(CipherSuite.TLS_PSK_WITH_NULL_SHA256).addButtonClickListener(CohostingInviteFriendAdapter$$Lambda$1.lambdaFactory$(this)).textChangedListener(TextWatcherUtils.create(CohostingInviteFriendAdapter$$Lambda$2.lambdaFactory$(this)));
        addModel(this.emailRow);
    }

    private void setupShareEarningsHeader() {
        this.shareEarningsHeader.titleRes(C5658R.string.cohosting_invite_a_friend_shared_earnings);
        addModel(this.shareEarningsHeader);
    }

    private void setupShareEarningsIntro() {
        this.shareEarningsIntro.textRes(C5658R.string.cohosting_invite_a_friend_shared_earnings_intro).showDivider(true);
        addModel(this.shareEarningsIntro);
    }

    private void setupPercentRow() {
        this.percentRow.titleRes(C5658R.string.share_of_earnings_title).inputAmount(Integer.valueOf(this.percentage)).amountChangedListener(CohostingInviteFriendAdapter$$Lambda$3.lambdaFactory$(this));
        addModel(this.percentRow);
    }

    private void setupSwitchRow() {
        this.switchRow.titleRes(C5658R.string.cohosting_invite_a_friend_switch_row).style(SwitchStyle.Filled).checked(this.includeCleaningFee).checkedChangeListener(CohostingInviteFriendAdapter$$Lambda$4.lambdaFactory$(this)).showDivider(true);
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
        this.warningRow.coloredText(this.context.getString(C5658R.string.cohosting_share_earnings_warning_title, new Object[]{paidListingManager.getUser().getFirstName()})).description(this.context.getString(C5658R.string.cohosting_share_earnings_warning_content, new Object[]{paidListingManager.getUser().getFirstName()})).color(C5658R.color.n2_arches).hasColoredText(true).small().showDivider(true);
        addModel(this.warningRow);
    }

    private void setupWarningOfPaidCohostInvitation() {
        this.warningRow.coloredText(this.context.getString(C5658R.string.cohosting_share_earnings_warning_title_due_to_pending_invite)).description(this.context.getString(C5658R.string.cohosting_share_earnings_warning_content_due_to_pending_invite)).color(C5658R.color.n2_arches).hasColoredText(true).small().showDivider(true);
        addModel(this.warningRow);
    }

    private void setupTermsRow() {
        this.terms.descriptionRes(C5658R.string.cohosting_invite_new_cohost_disclaimer).coloredTextRes(C5658R.string.cohosting_invite_new_cohost_terms).color(C5658R.color.n2_text_color_muted).smallAndMuted().linkListener(CohostingInviteFriendAdapter$$Lambda$5.lambdaFactory$(this)).hasLinkedText(true).showDivider(false);
        addModel(this.terms);
    }

    /* access modifiers changed from: private */
    public void displayWarningIfNeeded() {
        if (this.controller.hasPaidListingManagerOrPaidCohostInvitation()) {
            if (!this.warningRow.isShown() && hasSetPayout()) {
                showModel(this.warningRow);
                this.logger.logContractExistAlertImpression(this.controller.getCohostingContext(), null);
            } else if (this.warningRow.isShown() && !hasSetPayout()) {
                hideModel(this.warningRow);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateInviteButtonAvailability() {
        this.listener.enableInviteButton((this.emailRow.input() == null || this.emailRow.input().length() == 0 || !Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches()) ? false : true);
    }

    public void setSelectedEmail(String email2) {
        this.emailRow.input(email2);
        notifyModelChanged(this.emailRow);
    }

    public String getEmail() {
        return this.emailRow.input().toString().trim();
    }

    public int getPercentage() {
        return SanitizeUtils.zeroIfNull(this.percentRow.inputAmount());
    }

    public boolean getIncludeCleaningFee() {
        return this.switchRow.checked();
    }

    public void onSaveInstanceState(Bundle outState) {
        this.email = this.emailRow.input().toString();
        this.percentage = SanitizeUtils.zeroIfNull(this.percentRow.inputAmount());
        this.includeCleaningFee = this.switchRow.checked();
        super.onSaveInstanceState(outState);
    }

    private boolean hasSetPayout() {
        return (this.percentRow.inputAmount() != null && this.percentRow.inputAmount().intValue() > 0) || this.switchRow.checked();
    }

    public boolean hasChanged() {
        return !Strings.isNullOrEmpty(getEmail()) || getPercentage() != 0 || getIncludeCleaningFee();
    }
}
