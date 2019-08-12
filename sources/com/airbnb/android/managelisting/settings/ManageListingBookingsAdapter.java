package com.airbnb.android.managelisting.settings;

import android.content.Context;
import android.text.TextUtils;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.enums.InstantBookingAllowedCategory;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.Incentive;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.core.utils.listing.ListedStatus;
import com.airbnb.android.core.utils.listing.ListingDisplayUtils;
import com.airbnb.android.core.viewcomponents.models.InlineTipRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LinkActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StandardRowEpoxyModel_;
import com.airbnb.android.listing.utils.ListingTextUtils;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.ManageListingGraph;
import com.airbnb.android.managelisting.settings.utils.CalendarRowUtils;
import com.airbnb.epoxy.EpoxyModel;
import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.List;

public class ManageListingBookingsAdapter extends ManageListingAdapter {
    private final Context context;
    DebugSettings debugSettings;
    private final SectionHeaderEpoxyModel_ header = new SectionHeaderEpoxyModel_().titleRes(C7368R.string.manage_listing_booking_settings_page_title);
    private final LoadingRowEpoxyModel loadingRow = new LoadingRowEpoxyModel_();

    ManageListingBookingsAdapter(Context context2, ManageListingDataController controller) {
        super(controller);
        this.context = context2;
        ((ManageListingGraph) CoreApplication.instance(context2).component()).inject(this);
        addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.header});
        controller.addListener(this);
    }

    public void dataLoading(boolean loading) {
        if (loading && !this.models.contains(this.loadingRow)) {
            removeAllAfterModel(this.header);
            addModel(this.loadingRow);
        } else if (!loading && this.models.contains(this.loadingRow)) {
            removeModel(this.loadingRow);
        }
    }

    public void dataUpdated() {
        removeAllAfterModel(this.header);
        Listing listing = this.controller.getListing();
        addModels((Collection<? extends EpoxyModel<?>>) getBookingRows(listing));
        addModels((Collection<? extends EpoxyModel<?>>) getPricingRows(listing));
        addModels((Collection<? extends EpoxyModel<?>>) CalendarRowUtils.getCalendarRows(this.context, this.controller.getListing(), this.controller.getCalendarRule(), this.controller.actionExecutor, this.controller.getNestedListingsById(), false));
        addModels((Collection<? extends EpoxyModel<?>>) getManagementRows(listing));
    }

    private List<EpoxyModel<?>> getBookingRows(Listing listing) {
        InlineTipRowEpoxyModel_ instantBookTipRow;
        InstantBookingAllowedCategory instantBookingCategory = InstantBookingAllowedCategory.fromKey(listing.getInstantBookingAllowedCategory());
        boolean instantBookEnabled = instantBookingCategory != InstantBookingAllowedCategory.Off;
        StandardRowEpoxyModel_ instantBookRow = new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_booking_item_instant_book).subtitle(instantBookingCategory.getManageListingLabel()).clickListener(ManageListingBookingsAdapter$$Lambda$1.lambdaFactory$(this)).disclosure();
        StandardRowEpoxyModel_ guestRequirementsRow = new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_booking_item_guest_requirements).clickListener(ManageListingBookingsAdapter$$Lambda$2.lambdaFactory$(this)).disclosure();
        StandardRowEpoxyModel_ houseRulesRow = new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_booking_item_house_rules).clickListener(ManageListingBookingsAdapter$$Lambda$3.lambdaFactory$(this)).disclosure();
        StandardRowEpoxyModel_ cancellationPolicyRow = new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_booking_item_cancellation_policy).clickListener(ManageListingBookingsAdapter$$Lambda$4.lambdaFactory$(this)).disclosure();
        StandardRowEpoxyModel_ prebookingMessageRow = new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_booking_item_prebooking_message).clickListener(ManageListingBookingsAdapter$$Lambda$5.lambdaFactory$(this)).actionText(TextUtils.isEmpty(listing.getInstantBookWelcomeMessage()) ? C7368R.string.add : C7368R.string.edit).show(instantBookEnabled);
        Incentive incentive = listing.getIBTrialIncentive();
        if (!Trebuchet.launch(TrebuchetKeys.IB_TRIAL_INCENTIVES) || incentive == null) {
            instantBookTipRow = new InlineTipRowEpoxyModel_().withNoTopPaddingLayout().textRes(C7368R.string.manage_listing_booking_item_instant_book_tip).linkRes(C7368R.string.manage_listing_booking_item_instant_book_tip_link).clickListener(ManageListingBookingsAdapter$$Lambda$7.lambdaFactory$(this));
        } else {
            instantBookTipRow = new InlineTipRowEpoxyModel_().withNoTopPaddingLayout().text(incentive.getDescription()).linkRes(C7368R.string.ml_ib_tooltip_action).clickListener(ManageListingBookingsAdapter$$Lambda$6.lambdaFactory$(this));
        }
        instantBookTipRow.show(!instantBookEnabled && !this.controller.ibPromoDismissed);
        if (FeatureToggles.showBetterFirstMessage()) {
            return Lists.newArrayList((E[]) new EpoxyModel[]{instantBookRow, instantBookTipRow, guestRequirementsRow, houseRulesRow, cancellationPolicyRow});
        }
        return Lists.newArrayList((E[]) new EpoxyModel[]{instantBookRow, instantBookTipRow, houseRulesRow, cancellationPolicyRow, prebookingMessageRow});
    }

    private List<EpoxyModel<?>> getPricingRows(Listing listing) {
        boolean smartPricingIsAvailable = listing.isSmartPricingAvailable();
        boolean smartPricingIsOn = listing.getDynamicPricingControls().isEnabled();
        boolean showHostPricingRules = FeatureToggles.showHostPricingRules();
        return Lists.newArrayList((E[]) new EpoxyModel[]{new SectionHeaderEpoxyModel_().titleRes(C7368R.string.manage_listing_booking_item_pricing_title), new StandardRowEpoxyModel_().title(C7368R.string.nightly_price_title).clickListener(ManageListingBookingsAdapter$$Lambda$8.lambdaFactory$(this)).subtitle((CharSequence) ListingTextUtils.getSmartPricingSummaryText(this.context, listing)).disclosure(), new InlineTipRowEpoxyModel_().withNoTopPaddingLayout().textRes(C7368R.string.manage_listing_booking_item_smart_pricing_tip).linkRes(C7368R.string.manage_listing_booking_item_smart_pricing_tip_link).clickListener(ManageListingBookingsAdapter$$Lambda$9.lambdaFactory$(this)).show(smartPricingIsAvailable && !smartPricingIsOn), new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_booking_item_extra_charges).clickListener(ManageListingBookingsAdapter$$Lambda$10.lambdaFactory$(this)).subtitle((CharSequence) ListingTextUtils.getFeesDescriptionText(this.context, listing)).disclosure(), new StandardRowEpoxyModel_().title(showHostPricingRules ? C7368R.string.manage_listing_booking_item_length_of_stay_discounts : C7368R.string.manage_listing_booking_item_long_term_discounts).clickListener(ManageListingBookingsAdapter$$Lambda$11.lambdaFactory$(this)).subtitle((CharSequence) ListingTextUtils.getDLengthOfStayDiscountDescriptionText(this.context, listing)).disclosure(), new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_booking_item_last_minute_discounts).clickListener(ManageListingBookingsAdapter$$Lambda$12.lambdaFactory$(this)).subtitle((CharSequence) ListingTextUtils.getLastMinuteDiscountDescriptionText(this.context, listing)).disclosure().show(showHostPricingRules), new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_booking_item_early_bird_discounts).clickListener(ManageListingBookingsAdapter$$Lambda$13.lambdaFactory$(this)).subtitle((CharSequence) ListingTextUtils.getEarlyBirdDiscountDescriptionText(this.context, listing)).disclosure().show(showHostPricingRules), new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_booking_item_currency).disclosure().clickListener(ManageListingBookingsAdapter$$Lambda$14.lambdaFactory$(this)), new LinkActionRowEpoxyModel_().textRes(C7368R.string.manage_listing_pricing_disclaimer_call_to_action).clickListener(ManageListingBookingsAdapter$$Lambda$15.lambdaFactory$(this))});
    }

    private List<EpoxyModel<?>> getManagementRows(Listing listing) {
        boolean z;
        SectionHeaderEpoxyModel_ managementTitleRow = new SectionHeaderEpoxyModel_().titleRes(C7368R.string.manage_listing_booking_item_management_title);
        StandardRowEpoxyModel_ cohostingRow = new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_booking_item_cohosts).clickListener(ManageListingBookingsAdapter$$Lambda$16.lambdaFactory$(this)).disclosure().show();
        StandardRowEpoxyModel_ disclosure = new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_booking_item_local_laws).clickListener(ManageListingBookingsAdapter$$Lambda$17.lambdaFactory$(this)).disclosure();
        if (!listing.shouldHideLegalInfo()) {
            z = true;
        } else {
            z = false;
        }
        List<EpoxyModel<?>> managementRows = Lists.newArrayList((E[]) new EpoxyModel[]{managementTitleRow, cohostingRow, disclosure.show(z)});
        if (ListingDisplayUtils.showCityRegistration(this.controller.getListingRegistrationProcess())) {
            managementRows.add(new StandardRowEpoxyModel_().titleRes(C7368R.string.manage_listing_booking_item_city_registration).clickListener(ManageListingBookingsAdapter$$Lambda$18.lambdaFactory$(this)).disclosure());
        } else if (listing.isRequiresLicense()) {
            managementRows.add(new StandardRowEpoxyModel_().titleRes(C7368R.string.manage_listing_booking_item_license_or_registration_number).subtitle((CharSequence) listing.getLicense()).clickListener(ManageListingBookingsAdapter$$Lambda$19.lambdaFactory$(this)).disclosure());
        }
        managementRows.add(new StandardRowEpoxyModel_().title(C7368R.string.manage_listing_booking_item_status).subtitle(ListingDisplayUtils.getStatusString(ListedStatus.calculate(listing))).clickListener(ManageListingBookingsAdapter$$Lambda$20.lambdaFactory$(this)).disclosure());
        return managementRows;
    }
}
