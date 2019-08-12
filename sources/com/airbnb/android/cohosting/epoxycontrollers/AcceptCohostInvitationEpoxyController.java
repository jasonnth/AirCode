package com.airbnb.android.cohosting.epoxycontrollers;

import android.content.Context;
import android.os.Bundle;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.core.models.CohostInvitation;
import com.airbnb.android.core.models.CohostingContract;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.FixedActionFooterEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MapInterstitialEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTextRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SimpleTitleContentRowModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.UserDetailsActionRowEpoxyModel_;
import com.airbnb.epoxy.EpoxyController;
import com.airbnb.p027n2.components.ListingInfoRowModel_;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import com.airbnb.p027n2.utils.MapOptions;
import com.airbnb.p027n2.utils.MapOptions.CircleOptions;
import icepick.State;

public class AcceptCohostInvitationEpoxyController extends AirEpoxyController {
    FixedActionFooterEpoxyModel_ acceptButton;
    StandardRowEpoxyModel_ cleaningFeeRow;
    SimpleTitleContentRowModel_ cohostFirstFunctionRow;
    SimpleTitleContentRowModel_ cohostFourthFunctionRow;
    SectionHeaderEpoxyModel_ cohostFunctionIntroHeaderSection;
    SimpleTitleContentRowModel_ cohostSecondFunctionRow;
    SimpleTitleContentRowModel_ cohostThirdFunctionRow;
    private final Context context;
    SimpleTextRowEpoxyModel_ feeExplanationRow;
    SectionHeaderEpoxyModel_ feesHeaderRow;
    DocumentMarqueeEpoxyModel_ headerRow;
    StandardRowEpoxyModel_ hostingFeeRow;
    private final CohostInvitation invitation;
    @State
    boolean isLoading;
    SectionHeaderEpoxyModel_ listingHeaderRow;
    ListingInfoRowModel_ listingInfoRow;
    MapInterstitialEpoxyModel_ listingMap;
    StandardRowEpoxyModel_ maxFeeRow;
    StandardRowEpoxyModel_ minFeeRow;
    UserDetailsActionRowEpoxyModel_ userRow;

    public AcceptCohostInvitationEpoxyController(Context context2, CohostInvitation invitation2, Bundle savedInstanceState) {
        this.context = context2;
        this.invitation = invitation2;
        if (savedInstanceState == null) {
            this.isLoading = false;
        }
        requestModelBuild();
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        setupHeader();
        setupUserRow();
        setupFeeRows();
        setupListingRows();
        setupCohostFunctionExplanationSection();
    }

    private void setupHeader() {
        this.headerRow.titleText((CharSequence) this.context.getString(C5658R.string.cohosting_invitation_page_title, new Object[]{this.invitation.getInviter().getFirstName()})).captionText((CharSequence) this.invitation.getFormattedRemainingTime(this.context)).addTo(this);
    }

    private void setupUserRow() {
        this.userRow.title(this.invitation.getInviter().getFirstName()).subTitle(this.context.getString(C5658R.string.cohosting_invitation_page_creation_time_subtitle, new Object[]{Integer.valueOf(this.invitation.getInviter().getCreatedAt().getYear())})).imageUrl(this.invitation.getInviter().getProfilePicUrl()).showDivider(true).addTo(this);
    }

    private void setupFeeRows() {
        boolean z = true;
        CohostingContract contract = this.invitation.getCohostingContract();
        if (contract != null) {
            this.feesHeaderRow.titleRes(C5658R.string.cohosting_invitation_page_fees_header).showDivider(false).addTo(this);
            if (contract.getFormattedHostingFee(this.context) != null) {
                this.hostingFeeRow.titleRes(C5658R.string.cohosting_share_of_earnings_without_colon).infoText((CharSequence) contract.getFormattedHostingFee(this.context)).showDivider(true).addTo(this);
            }
            if (contract.getFormattedMinimumFee() != null) {
                this.minFeeRow.titleRes(C5658R.string.cohosting_payment_type_min_fee_title).infoText((CharSequence) contract.getFormattedMinimumFee()).showDivider(true).addTo(this);
            }
            if (contract.getFormattedMaximumFee() != null) {
                this.maxFeeRow.titleRes(C5658R.string.cohosting_contract_max_fee).infoText((CharSequence) contract.getFormattedMaximumFee()).showDivider(true).addTo(this);
            }
            if (contract.isIncludeCleaningFee().booleanValue()) {
                this.cleaningFeeRow.titleRes(C5658R.string.cohosting_contract_cleaning_fee).infoText((CharSequence) this.context.getString(C5658R.string.cohosting_contract_cleaning_fee_paid_to_you)).showDivider(true).addTo(this);
            }
            SimpleTextRowEpoxyModel_ showDivider = this.feeExplanationRow.textRes(C5658R.string.cohosting_invitation_fee_explanation_row).smallAndMuted().showDivider(true);
            if (contract.getPercentage() <= 0) {
                z = false;
            }
            showDivider.addIf(z, (EpoxyController) this);
        }
    }

    private void setupListingRows() {
        Listing listing = this.invitation.getListing();
        this.listingHeaderRow.titleRes(C5658R.string.cohosting_contract_listing_title).showDivider(false).addTo(this);
        this.listingInfoRow.title(listing.getNameOrPlaceholderName()).image(listing.getPictureUrl()).showDivider(false).addTo(this);
        this.listingMap.mapOptions(getMapOption(listing)).hideText(true).addTo(this);
    }

    private MapOptions getMapOption(Listing listing) {
        return MapOptions.builder(AppLaunchUtils.isUserInChina()).center(listing.getLatLng()).circle(CircleOptions.create(listing.getLatLng(), 800)).zoom(14).useDlsMapType(true).build();
    }

    private void setupCohostFunctionExplanationSection() {
        this.cohostFunctionIntroHeaderSection.titleRes(C5658R.string.cohosting_invitation_page_introduction_header).showDivider(false).addTo(this);
        addFunctionExplanationRowWithDivider(this.cohostFirstFunctionRow, C5658R.string.cohosting_invitation_page_title_functionality_1, C5658R.string.cohosting_invitation_page_content_functionality_1);
        addFunctionExplanationRowWithDivider(this.cohostSecondFunctionRow, C5658R.string.cohosting_invitation_page_title_functionality_2, C5658R.string.cohosting_invitation_page_content_functionality_2);
        addFunctionExplanationRowWithDivider(this.cohostThirdFunctionRow, C5658R.string.cohosting_invitation_page_title_functionality_3, C5658R.string.cohosting_invitation_page_content_functionality_3);
        addFunctionExplanationRowNoDivider(this.cohostFourthFunctionRow, C5658R.string.cohosting_invitation_page_title_functionality_4, C5658R.string.cohosting_invitation_page_content_functionality_4);
    }

    private void addFunctionExplanationRowNoDivider(SimpleTitleContentRowModel_ model, int titleRes, int subTitleRes) {
        addFunctionExplanationRow(model, titleRes, subTitleRes, false);
    }

    private void addFunctionExplanationRowWithDivider(SimpleTitleContentRowModel_ model, int titleRes, int subTitleRes) {
        addFunctionExplanationRow(model, titleRes, subTitleRes, true);
    }

    private void addFunctionExplanationRow(SimpleTitleContentRowModel_ model, int titleRes, int subTitleRes, boolean showDivider) {
        model.titleRes(titleRes).contentRes(subTitleRes).showDivider(showDivider);
        model.addTo(this);
    }
}
