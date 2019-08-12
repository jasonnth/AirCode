package com.airbnb.android.cohosting.adapters;

import android.content.Context;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.controllers.CohostManagementDataController;
import com.airbnb.android.core.enums.HelpCenterArticle;
import com.airbnb.android.core.models.CohostingContract;
import com.airbnb.android.core.models.CohostingContractService;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MapInterstitialEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.UserDetailsActionRowEpoxyModel_;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.p027n2.components.ListingInfoRowModel_;
import com.airbnb.p027n2.utils.MapOptions;
import com.airbnb.p027n2.utils.MapOptions.MarkerOptions;
import java.util.HashSet;
import java.util.Set;

public class CohostingContractAdapter extends AirEpoxyAdapter {
    private final StandardRowEpoxyModel_ cleaningFeeRow = new StandardRowEpoxyModel_();
    private final Context context;
    private CohostingContract contract;
    private final CohostManagementDataController controller;
    private final SectionHeaderEpoxyModel_ dateHeaderRow = new SectionHeaderEpoxyModel_();
    private final StandardRowEpoxyModel_ endDateRow = new StandardRowEpoxyModel_();
    private final SectionHeaderEpoxyModel_ feesHeaderRow = new SectionHeaderEpoxyModel_();
    private final DocumentMarqueeEpoxyModel_ headerRow = new DocumentMarqueeEpoxyModel_();
    private final StandardRowEpoxyModel_ hostingFeeRow = new StandardRowEpoxyModel_();
    private final StandardRowEpoxyModel_ listingAddress = new StandardRowEpoxyModel_();
    private final SectionHeaderEpoxyModel_ listingHeaderRow = new SectionHeaderEpoxyModel_();
    private final ListingInfoRowModel_ listingInfoRow = new ListingInfoRowModel_();
    private final MapInterstitialEpoxyModel_ listingMap = new MapInterstitialEpoxyModel_();
    private final StandardRowEpoxyModel_ maxFeeRow = new StandardRowEpoxyModel_();
    private final StandardRowEpoxyModel_ minFeeRow = new StandardRowEpoxyModel_();
    private final SectionHeaderEpoxyModel_ serviceHeaderRow = new SectionHeaderEpoxyModel_();
    private final StandardRowEpoxyModel_ startDateRow = new StandardRowEpoxyModel_();
    private final UserDetailsActionRowEpoxyModel_ userRow = new UserDetailsActionRowEpoxyModel_();

    public interface Listener {
        void logImpression();
    }

    public CohostingContractAdapter(Context context2, CohostingContract contract2, CohostManagementDataController controller2, Listener listener) {
        this.context = context2;
        this.contract = contract2;
        this.controller = controller2;
        if (contract2 != null) {
            update(contract2);
            listener.logImpression();
        }
    }

    public void update(CohostingContract contract2) {
        this.contract = contract2;
        setupRows();
    }

    private void setupRows() {
        setupHeader();
        setupUserRow();
        setupServiceRows();
        setupFeeRows();
        setupDateRows();
        setupListingRows();
    }

    private boolean isListingAdminViewingContract() {
        return this.controller.isCurrentUserListingAdmin();
    }

    private void setupHeader() {
        String string;
        DocumentMarqueeEpoxyModel_ documentMarqueeEpoxyModel_ = this.headerRow;
        if (isListingAdminViewingContract()) {
            string = this.context.getString(C5658R.string.cohosting_contract_title, new Object[]{this.contract.getCohost().getFirstName()});
        } else {
            string = this.context.getString(C5658R.string.cohosting_contract_title_for_yourself);
        }
        documentMarqueeEpoxyModel_.titleText((CharSequence) string).captionText((CharSequence) this.context.getString(C5658R.string.cohosting_contract_title_acceptance_date, new Object[]{this.contract.getAcceptanceDate().getDateString(this.context)})).withNoTopPaddingLayout();
        addModel(this.headerRow);
    }

    private void setupUserRow() {
        if (isListingAdminViewingContract()) {
            this.userRow.title(this.contract.getCohost().getFirstName()).subTitle(this.context.getString(C5658R.string.cohosting_contract_user_subtitle)).imageUrl(this.contract.getCohost().getPictureUrl()).showDivider(true);
        } else {
            this.userRow.title(this.contract.getOwner().getFirstName()).subTitle(this.context.getString(C5658R.string.cohosting_listing_admin_text)).imageUrl(this.contract.getOwner().getPictureUrl()).showDivider(true);
        }
        addModel(this.userRow);
    }

    private void setupServiceRows() {
        this.serviceHeaderRow.titleRes(C5658R.string.cohosting_contract_service_title).buttonTextRes(C5658R.string.cohosting_contract_service_details).buttonOnClickListener(CohostingContractAdapter$$Lambda$1.lambdaFactory$(this, isListingAdminViewingContract() ? HelpCenterArticle.COHOSTING_SERVICE_LISTING_ADMIN : HelpCenterArticle.COHOSTING_SERVICE_COHOST)).showDivider(false);
        addModel(this.serviceHeaderRow);
        setupServices();
    }

    private void setupServices() {
        Set<Integer> providedServices = new HashSet<>();
        for (int item : this.contract.getServices()) {
            providedServices.add(Integer.valueOf(item));
        }
        int numOfServices = CohostingContractService.size();
        for (int item2 = 1; item2 <= numOfServices; item2++) {
            addModel(new StandardRowEpoxyModel_().title(CohostingContractService.getTitleFromIndex(item2)).rowDrawableRes(providedServices.contains(Integer.valueOf(item2)) ? C5658R.C5659drawable.n2_ic_check_babu : C5658R.C5659drawable.n2_ic_x_grey).showDivider(true));
        }
    }

    private void setupFeeRows() {
        String string;
        this.feesHeaderRow.titleRes(C5658R.string.cohosting_contract_fees_title).showDivider(false);
        addModel(this.feesHeaderRow);
        if (this.contract.getFormattedHostingFee(this.context) != null) {
            this.hostingFeeRow.titleRes(C5658R.string.cohosting_contract_hosting_fee).infoText((CharSequence) this.contract.getFormattedHostingFee(this.context)).showDivider(true);
            addModel(this.hostingFeeRow);
        }
        if (this.contract.getFormattedMinimumFee() != null) {
            this.minFeeRow.titleRes(C5658R.string.cohosting_contract_min_fee).infoText((CharSequence) this.contract.getFormattedMinimumFee()).showDivider(true);
            addModel(this.minFeeRow);
        }
        if (this.contract.getFormattedMaximumFee() != null) {
            this.maxFeeRow.titleRes(C5658R.string.cohosting_contract_max_fee).infoText((CharSequence) this.contract.getFormattedMaximumFee()).showDivider(true);
            addModel(this.maxFeeRow);
        }
        if (this.contract.isIncludeCleaningFee().booleanValue()) {
            StandardRowEpoxyModel_ titleRes = this.cleaningFeeRow.titleRes(C5658R.string.cohosting_contract_cleaning_fee);
            if (isListingAdminViewingContract()) {
                string = this.context.getString(C5658R.string.cohosting_contract_cleaning_fee_paid);
            } else {
                string = this.context.getString(C5658R.string.cohosting_contract_cleaning_fee_paid_to_you);
            }
            titleRes.infoText((CharSequence) string).showDivider(true);
            addModel(this.cleaningFeeRow);
        }
    }

    private void setupDateRows() {
        String string;
        this.dateHeaderRow.titleRes(C5658R.string.cohosting_contract_date_title).showDivider(false);
        this.startDateRow.titleRes(C5658R.string.cohosting_contract_date_start_date).infoText((CharSequence) this.contract.getStartDate().getDateString(this.context)).showDivider(true);
        StandardRowEpoxyModel_ titleRes = this.endDateRow.titleRes(C5658R.string.cohosting_contract_date_end_date);
        if (this.contract.getEndDate() != null) {
            string = this.contract.getEndDate().getDateString(this.context);
        } else {
            string = this.context.getString(C5658R.string.cohosting_contract_date_end_date_ongoing);
        }
        titleRes.infoText((CharSequence) string).showDivider(true);
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.dateHeaderRow, this.startDateRow, this.endDateRow});
    }

    private void setupListingRows() {
        Listing listing = this.controller.getListing();
        this.listingHeaderRow.titleRes(C5658R.string.cohosting_contract_listing_title).showDivider(false);
        this.listingInfoRow.title(listing.getName()).subtitleText(listing.getPropertyType()).image(listing.getPictureUrl()).showDivider(false);
        this.listingMap.mapOptions(getMapOption(listing)).hideText(true);
        this.listingAddress.title((CharSequence) listing.getFormattedAddress()).titleMaxLine(3).showDivider(false);
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.listingHeaderRow, this.listingInfoRow, this.listingMap, this.listingAddress});
    }

    private MapOptions getMapOption(Listing listing) {
        return MapOptions.builder(AppLaunchUtils.isUserInChina()).center(listing.getLatLng()).marker(MarkerOptions.create(listing.getLatLng())).zoom(14).useDlsMapType(true).build();
    }
}
