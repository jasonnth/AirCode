package com.airbnb.android.cohosting.epoxycontrollers;

import android.content.Context;
import android.os.Bundle;
import android.util.Patterns;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.shared.CohostingPaymentsSection;
import com.airbnb.android.cohosting.utils.CohostingConstants.FeeType;
import com.airbnb.android.cohosting.utils.CohostingUtil;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.CohostInvitation;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.core.utils.CurrencyFormatter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineInputWithContactPickerRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InlineMultilineInputRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.utils.TextWatcherUtils;
import com.airbnb.epoxy.EpoxyController;
import com.google.common.base.Strings;
import icepick.State;
import java.util.ArrayList;
import org.spongycastle.crypto.tls.CipherSuite;

public class CohostingInviteFriendEpoxyController extends CohostingPaymentsBaseEpoxyController {
    @State
    ArrayList<CohostInvitation> cohostInvitations;
    private final Context context;
    @State
    String email;
    InlineInputWithContactPickerRowEpoxyModel_ emailRow;
    DocumentMarqueeEpoxyModel_ headerRow;
    private final Listener listener;
    @State
    Listing listing;
    @State
    ArrayList<ListingManager> listingManagers;
    CohostingManagementJitneyLogger logger;
    /* access modifiers changed from: 0000 */
    @State
    public String message;
    InlineMultilineInputRowEpoxyModel_ messageRow;
    SectionHeaderEpoxyModel_ shareEarningsHeader;
    SimpleTextRowEpoxyModel_ shareEarningsIntro;
    SimpleTextRowEpoxyModel_ terms;
    SimpleTextRowEpoxyModel_ warningRow;

    public interface Listener {
        void choosePaymentType(FeeType feeType, String str);

        void enableInviteButton(boolean z);

        void openTermsOfServiceLink();

        void pickContact();
    }

    public CohostingInviteFriendEpoxyController(Context context2, Listener listener2, Listing listing2, ArrayList<ListingManager> listingManagers2, ArrayList<CohostInvitation> cohostInvitations2, CurrencyFormatter currencyHelper, Bundle savedInstanceState) {
        super(savedInstanceState);
        this.context = context2;
        this.listener = listener2;
        this.listing = listing2;
        this.listingManagers = listingManagers2;
        this.cohostInvitations = cohostInvitations2;
        if (savedInstanceState == null) {
            initializeCohostingPaymentSettings(currencyHelper.getLocalCurrencyString());
        }
        ((CohostingGraph) CoreApplication.instance(context2).component()).inject(this);
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        setupHeader();
        setupEmailRow();
        setupMessageRow();
        setupShareEarningsHeader();
        setupShareEarningsIntro();
        setupShareEarningsRow();
        setupWarningRow();
        setupTermsRow();
    }

    public void changeFeeTypeIfPossible(FeeType feeType) {
        if (feeType != null) {
            setFeeType(feeType);
        }
        updateInviteButtonAvailability(this.email);
    }

    public void setSelectedEmail(String email2) {
        this.email = email2;
        requestModelBuild();
    }

    public String getEmail() {
        return this.email == null ? "" : this.email.trim();
    }

    public String getMessage() {
        return this.message;
    }

    public boolean hasChanged() {
        return !Strings.isNullOrEmpty(getEmail()) || !Strings.isNullOrEmpty(getMessage()) || super.hasChanged();
    }

    public void choosePaymentType() {
        this.listener.choosePaymentType(getCohostingPaymentSettings().feeType(), null);
    }

    private void setupHeader() {
        this.headerRow.titleRes(C5658R.string.cohosting_invite_friend).addTo(this);
    }

    private void setupEmailRow() {
        this.emailRow.titleRes(C5658R.string.cohosting_invite_a_friend_email_address).input(this.email).inputType(CipherSuite.TLS_PSK_WITH_NULL_SHA256).addButtonClickListener(CohostingInviteFriendEpoxyController$$Lambda$1.lambdaFactory$(this)).textChangedListener(TextWatcherUtils.create(CohostingInviteFriendEpoxyController$$Lambda$2.lambdaFactory$(this))).updateModelData(false).addTo(this);
    }

    private void setupMessageRow() {
        this.messageRow.titleRes(C5658R.string.cohosting_invite_add_optional_message_title).hintRes(C5658R.string.cohosting_invite_add_optional_message_hint).input(this.message).inputChangedListener(CohostingInviteFriendEpoxyController$$Lambda$3.lambdaFactory$(this)).withOneLineLayout().addIf(FeatureToggles.showMessageFieldOnCohostInvite(), (EpoxyController) this);
    }

    private void setupShareEarningsHeader() {
        this.shareEarningsHeader.titleRes(C5658R.string.cohosting_invite_a_friend_shared_earnings).addTo(this);
    }

    private void setupShareEarningsIntro() {
        this.shareEarningsIntro.textRes(C5658R.string.cohosting_invite_a_friend_shared_earnings_intro_fee_options).addTo(this);
    }

    private void setupShareEarningsRow() {
        CohostingPaymentsSection.addPaymentSectionToEpoxyController(this, getCohostingPaymentSettings());
    }

    private void setupWarningRow() {
        if (getCohostingPaymentSettings().hasSetPayout()) {
            if (CohostingUtil.hasPaidListingManager(this.listingManagers)) {
                ListingManager paidListingManager = CohostingUtil.getPaidListingManager(this.listingManagers);
                this.warningRow.coloredText(this.context.getString(C5658R.string.cohosting_share_earnings_warning_title, new Object[]{paidListingManager.getUser().getFirstName()})).description(this.context.getString(C5658R.string.cohosting_share_earnings_warning_content, new Object[]{paidListingManager.getUser().getFirstName()}));
            } else if (CohostingUtil.hasPaidCohostInvitation(this.cohostInvitations)) {
                this.warningRow.coloredTextRes(C5658R.string.cohosting_share_earnings_warning_title_due_to_pending_invite).descriptionRes(C5658R.string.cohosting_share_earnings_warning_content_due_to_pending_invite);
            } else {
                return;
            }
            this.warningRow.color(C5658R.color.n2_arches).hasColoredText(true).small().showDivider(true).onBind(CohostingInviteFriendEpoxyController$$Lambda$4.lambdaFactory$(this)).addTo(this);
        }
    }

    private void setupTermsRow() {
        this.terms.description(this.context.getString(C5658R.string.cohosting_invite_new_cohost_disclaimer)).coloredText(this.context.getString(C5658R.string.cohosting_invite_new_cohost_terms)).color(C5658R.color.n2_text_color_muted).smallAndMuted().linkListener(CohostingInviteFriendEpoxyController$$Lambda$5.lambdaFactory$(this)).hasLinkedText(true).showDivider(false).addTo(this);
    }

    /* access modifiers changed from: private */
    public void updateInviteButtonAvailability(String newEmail) {
        this.email = newEmail;
        this.listener.enableInviteButton(getEmail().length() != 0 && Patterns.EMAIL_ADDRESS.matcher(getEmail()).matches());
        requestDelayedModelBuild(1000);
    }
}
